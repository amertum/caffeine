package com.google.code.caffeine;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import org.simpleframework.http.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Iterables.transform;

public class ServletRequestAdapter
        implements HttpServletRequest
{

    public ServletRequestAdapter(Request request)
    {
        this(request, null);
    }

    public ServletRequestAdapter(Request request, String contextPath)
    {
        this.request = request;
        this.contextPath = contextPath;
    }

    @Override
    public Object getAttribute(String name)
    {
        return this.request.getAttribute(name);
    }

    @Override
    public Enumeration getAttributeNames()
    {
        return Iterators.asEnumeration(this.request.getAttributes().keySet().iterator());
    }

    @Override
    public String getCharacterEncoding()
    {
        return this.request.getContentType() == null
                ? Charsets.ISO_8859_1.toString()
                : this.request.getContentType().getCharset();
    }

    @Override
    public void setCharacterEncoding(String env)
            throws UnsupportedEncodingException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getContentLength()
    {
        return this.request.getContentLength();
    }

    @Override
    public String getContentType()
    {
        return this.request.getContentType().toString();
    }

    @Override
    public ServletInputStream getInputStream()
            throws IOException
    {
        final InputStream inputStream = this.request.getInputStream();

        return new ServletInputStream() {

            @Override
            public int read()
                    throws IOException
            {
                return inputStream.read();
            }
        };
    }

    @Override
    public String getParameter(String name)
    {
        try {
            return this.request.getParameter(name);
        }
        catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public Enumeration getParameterNames()
    {
        return Iterators.asEnumeration(this.request.getQuery().keySet().iterator());
    }

    @Override
    public String[] getParameterValues(String name)
    {
        return toArray(this.request.getQuery().getAll(name), String.class);
    }

    @Override
    public Map getParameterMap()
    {
        return this.request.getQuery();
    }

    @Override
    public String getProtocol()
    {
        return this.request.getValue("Protocol");
    }

    @Override
    public String getScheme()
    {
        return this.request.getAddress().getScheme();
    }

    @Override
    public String getServerName()
    {
        return this.request.getAddress().getDomain();
    }

    @Override
    public int getServerPort()
    {
        return this.request.getAddress().getPort();
    }

    @Override
    public BufferedReader getReader()
            throws IOException
    {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), getCharacterEncoding()));
    }

    @Override
    public String getRemoteAddr()
    {
        return this.request.getAddress().toString();
    }

    @Override
    public String getRemoteHost()
    {
        return this.request.getAddress().getDomain();
    }

    @Override
    public void setAttribute(
            String name,
            Object o)
    {
        this.request.getAttributes().put(name, o);
    }

    @Override
    public void removeAttribute(String name)
    {
        this.request.getAttributes().remove(name);
    }

    @Override
    public Locale getLocale()
    {
        return Iterables.getFirst(this.request.getLocales(), null);
    }

    @Override
    public Enumeration getLocales()
    {
        return Iterators.asEnumeration(this.request.getLocales().iterator());
    }

    @Override
    public boolean isSecure()
    {
        return this.request.isSecure();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path)
    {
        return null;
    }

    @Override
    public String getRealPath(String path)
    {
        return null;
    }

    @Override
    public int getRemotePort()
    {
        return this.request.getAddress().getPort();
    }

    @Override
    public String getLocalName()
    {
        return this.request.getClientAddress().getHostName();
    }

    @Override
    public String getLocalAddr()
    {
        return this.request.getClientAddress().getAddress().toString();
    }

    @Override
    public int getLocalPort()
    {
        return this.request.getClientAddress().getPort();
    }

    @Override
    public String getAuthType()
    {
        return null;
    }

    @Override
    public Cookie[] getCookies()
    {
        return toArray(transform(this.request.getCookies(), Cookies.servletCookie()), Cookie.class);
    }

    @Override
    public long getDateHeader(final String name)
    {
        return this.request.getDate(name);
    }

    @Override
    public String getHeader(final String name)
    {
        return this.request.getValue(name);
    }

    @Override
    public Enumeration getHeaders(final String name)
    {
        return Iterators.asEnumeration(this.request.getValues(name).iterator());
    }

    @Override
    public Enumeration getHeaderNames()
    {
        return Iterators.asEnumeration(this.request.getNames().iterator());
    }

    @Override
    public int getIntHeader(final String name)
    {
        return this.request.getInteger(name);
    }

    @Override
    public String getMethod()
    {
        return this.request.getMethod();
    }

    @Override
    public String getPathInfo()
    {
        return this.request.getPath().getPath();
    }

    @Override
    public String getPathTranslated()
    {
        return null;
    }

    @Override
    public String getContextPath()
    {
        return this.contextPath;
    }

    @Override
    public String getQueryString()
    {
        return this.request.getQuery().toString();
    }

    @Override
    public String getRemoteUser()
    {
        return null;
    }

    @Override
    public boolean isUserInRole(final String role)
    {
        return false;
    }

    @Override
    public Principal getUserPrincipal()
    {
        return null;
    }

    @Override
    public String getRequestedSessionId()
    {
        return null;
    }

    @Override
    public String getRequestURI()
    {
        return this.request.getAddress().toString();
    }

    @Override
    public StringBuffer getRequestURL()
    {
        return null;
    }

    @Override
    public String getServletPath()
    {
        return "";
    }

    @Override
    public HttpSession getSession(final boolean create)
    {
        return null;
    }

    @Override
    public HttpSession getSession()
    {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdValid()
    {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie()
    {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromURL()
    {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl()
    {
        return false;
    }

    private final Request request;
    private final String contextPath;

}
