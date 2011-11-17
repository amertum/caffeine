package caffeine.servlight;

import com.google.common.base.Function;

import javax.servlet.http.Cookie;

class Cookies {

    public static Function<org.simpleframework.http.Cookie, Cookie> servletCookie()
    {
        return new Function<org.simpleframework.http.Cookie, Cookie>() {

            @Override
            public Cookie apply(
                    final org.simpleframework.http.Cookie cookie)
            {
                final Cookie result = new Cookie(cookie.getName(), cookie.getValue());
                result.setDomain(cookie.getDomain());
                result.setMaxAge(cookie.getExpiry());
                result.setPath(cookie.getPath());
                result.setSecure(cookie.getSecure());
                result.setVersion(cookie.getVersion());

                return result;
            }
        };
    }

    public static Function<Cookie, org.simpleframework.http.Cookie> simpleCookie()
    {
        return new Function<Cookie, org.simpleframework.http.Cookie>() {

            @Override
            public org.simpleframework.http.Cookie apply(
                    final Cookie cookie)
            {
                org.simpleframework.http.Cookie result = new org.simpleframework.http.Cookie(cookie.getName(),
                                                                                             cookie.getValue());
                result.setDomain(cookie.getDomain());
                result.setExpiry(cookie.getMaxAge());
                result.setPath(cookie.getPath());
                result.setSecure(cookie.getSecure());
                result.setVersion(cookie.getVersion());

                return result;
            }
        };
    }

}
