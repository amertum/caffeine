package j18n;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Locale;

import static java.util.Locale.ENGLISH;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalizerTest {

    @Test
    public void testLocalizerFieldBinding()
    {
        assertLocalizer(Localizer.BindingMode.FIELD, ENGLISH);
    }

    @Test
    public void testLocalizerFieldBindingWithNoLocale()
    {
        assertLocalizer(Localizer.BindingMode.FIELD, null);
    }

    @Test
    public void testLocalizerPropertyBinding()
    {
        assertLocalizer(Localizer.BindingMode.PROPERTY, ENGLISH);
    }

    @Test
    public void testLocalizerPropertyBindingWithNoLocale()
    {
        assertLocalizer(Localizer.BindingMode.PROPERTY, null);
    }

    @Test
    public void testLocalizerSetterBinding()
    {
        assertLocalizer(Localizer.BindingMode.SETTER, ENGLISH);
    }

    @Test
    public void testLocalizerSetterBindingWithNoLocale()
    {
        assertLocalizer(Localizer.BindingMode.SETTER, null);
    }

    @Test
    @Ignore
    public void testLocalizerIntegration()
    {
        // TODO
    }

    private void assertLocalizer(
            final Localizer.BindingMode bindingMode,
            final Locale locale)
    {
        final Localizer localizer = new Localizer(bindingMode, messageSource, expressionLanguage);

        final Entity entity = new Entity();
        localizer.localize(entity, locale);

        assertThat(entity.getValueNotLocalized()).isNull();
        final String suffix = (locale == null)
                ? "none"
                : "en";
        assertThat(entity.getValue()).isEqualTo("value-" + suffix);
        assertThat(entity.getSuperValue()).isEqualTo("superValue-" + suffix);
        assertThat(entity.getValueWithArgs()).isEqualTo("valueWithArgs-" + suffix);
        assertThat(entity.getValueWithDefaultValue()).isEqualTo("valueWithDefaultValue-" + suffix);
        assertThat(entity.getValueWithArgsAndDefaultValue()).isEqualTo("valueWithArgsAndDefaultValue-" + suffix);
    }

    @Before
    public void initMocks() {
        final String[] args = new String[] {
                "arg1", "arg2"
        };

        expressionLanguage = mock(ExpressionLanguage.class);
        when(expressionLanguage.evaluateArguments(any(Entity.class), any(Field.class), any(String[].class))).thenReturn(args);

        messageSource = mock(MessageSource.class);
        when(messageSource.getMessage("entity.value", null, "", ENGLISH)).thenReturn("value-en");
        when(messageSource.getMessage("entity.value", null, "", null)).thenReturn("value-none");
        when(messageSource.getMessage("superEntity.value", null, "", ENGLISH)).thenReturn("superValue-en");
        when(messageSource.getMessage("superEntity.value", null, "", null)).thenReturn("superValue-none");
        when(messageSource.getMessage("entity.valueWithArgs", args, "", ENGLISH)).thenReturn("valueWithArgs-en");
        when(messageSource.getMessage("entity.valueWithArgs", args, "", null)).thenReturn("valueWithArgs-none");
        when(messageSource.getMessage("entity.valueWithDefaultValue", null, "defaultValue", ENGLISH)).thenReturn("valueWithDefaultValue-en");
        when(messageSource.getMessage("entity.valueWithDefaultValue", null, "defaultValue", null)).thenReturn("valueWithDefaultValue-none");
        when(messageSource.getMessage("entity.valueWithArgsAndDefaultValue", args, "defaultValue {} {}", ENGLISH)).thenReturn("valueWithArgsAndDefaultValue-en");
        when(messageSource.getMessage("entity.valueWithArgsAndDefaultValue", args, "defaultValue {} {}", null)).thenReturn("valueWithArgsAndDefaultValue-none");
    }

    private ExpressionLanguage expressionLanguage;
    private MessageSource messageSource;

}
