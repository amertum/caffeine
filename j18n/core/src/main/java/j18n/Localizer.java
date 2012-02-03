package j18n;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Localizer {

    public Localizer(
            final BindingMode bindingMode,
            final MessageSource messageSource,
            final ExpressionLanguage expressionLanguage)
    {
        this.bindingMode = bindingMode;
        this.messageSource = messageSource;
        this.expressionLanguage = expressionLanguage;
    }

    public <T> void localize(
            final T entity,
            final Locale locale)
    {
        if (entity == null) {
            return;
        }

        final Class<?> clazz = entity.getClass();
        for (final Field field : getFields(clazz)) {
            if (field.isAnnotationPresent(Localized.class)) {
                final Localized localized = field.getAnnotation(Localized.class);

                final String code = localized.value();
                final String[] arguments = localized.args();
                final String defaultMessage = localized.defaultMessage();

                final Object[] targetArguments = (arguments == null || arguments.length == 0)
                        ? null
                        : this.expressionLanguage.evaluateArguments(entity, field, arguments);

                //System.out.println(field.getName() + "@" + Localized.class.getSimpleName() + "(" + code + ", " + defaultMessage + ", "  + locale + ", " + ((targetArguments == null) ? targetArguments : Arrays.asList(targetArguments)) + ")");

                final String message = this.messageSource.getMessage(code, targetArguments, defaultMessage, locale);
                this.bindingMode.setValue(field, entity, message);
            }
        }
    }

    private Collection<Field> getFields(final Class<?> clazz) {
        if (clazz == null) {
            return Arrays.asList();
        }

        final List<Field> fields = new ArrayList<Field>(Arrays.asList(clazz.getDeclaredFields()));
        fields.addAll(this.getFields(clazz.getSuperclass()));

        return fields;
    }

    private Locale getLocale(final String localeValue) {
        for (final Locale locale : Locale.getAvailableLocales()) {
            if (locale.toString().equals(localeValue)) {
                return locale;
            }
        }

        return null;
    }

    private final BindingMode bindingMode;
    private final MessageSource messageSource;
    private final ExpressionLanguage expressionLanguage;

    public enum BindingMode {

        FIELD {
            @Override
            public void setValue(Field field, Object object, Object value) {
                try {
                    field.setAccessible(true);
                    field.set(object, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        },

        PROPERTY {
            @Override
            public void setValue(Field field, Object object, Object value) {
                try {
                    for (final PropertyDescriptor descriptor : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
                        if (field.getName().equals(descriptor.getName())) {
                            descriptor.getWriteMethod().invoke(object, value);
                            return;
                        }
                        // TODO else log debug
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (IntrospectionException e) {
                    throw new RuntimeException(e);
                }
            }
        },

        SETTER {
            @Override
            public void setValue(Field field, Object object, Object value) {
                try {
                    final String name = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    final Method method = this.getSetterMethod(object.getClass(), name, field.getType());
                    if (method != null) {
                        method.invoke(object, value);
                    }
                    // TODO else log debug
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        public abstract void setValue(Field field, Object object, Object value);

        protected Method getSetterMethod(
                final Class<?> clazz,
                final String name,
                final Class<?> type)
                throws NoSuchMethodException
        {
            if (clazz == null) {
                return null;
            }

            Method method = null;
            try {
                method = clazz.getDeclaredMethod(name, type);
            }
            catch (final NoSuchMethodException e) {
                method = this.getSetterMethod(clazz.getSuperclass(), name, type);
            }

            return method;
        }

    }

}
