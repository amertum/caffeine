package j18n;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Localized {

    String value();

    String[] args() default {};

    String defaultMessage() default "";

}
