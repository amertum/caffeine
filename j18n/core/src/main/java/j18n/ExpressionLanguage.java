package j18n;

import java.lang.reflect.Field;

public interface ExpressionLanguage {

    Object[] evaluateArguments(Object entity, Field field, String[] arguments);

}
