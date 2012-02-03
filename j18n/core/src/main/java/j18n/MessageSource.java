package j18n;

import java.util.Locale;

public interface MessageSource {

    String getMessage(String code, Object[] args, String defaultMessage, Locale locale);

}
