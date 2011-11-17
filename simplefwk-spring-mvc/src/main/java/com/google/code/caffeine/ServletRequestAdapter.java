package com.google.code.caffeine;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import org.simpleframework.http.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.*;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class ServletRequestAdapter implements ServletRequest {

    public ServletRequestAdapter(Request request) {
        this.request = request;
    }

    @Override
    public Object getAttribute(String name) {
        return this.request.getAttribute(name);
    }

    @Override
    public Enumeration getAttributeNames() {
        return Iterators.asEnumeration(this.request.getAttributes().keySet().iterator());
    }

    @Override
    public String getCharacterEncoding() {
        return this.request.getContentType().getCharset();
    }

    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getContentLength() {
        return this.request.getContentLength();
    }

    @Override
    public String getContentType() {
        return this.request.getContentType().toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final InputStream inputStream = this.request.getInputStream();

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    @Override
    public String getParameter(String name) {
        try {
            return this.request.getParameter(name);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public Enumeration getParameterNames() {
        return Iterators.asEnumeration(this.request.getQuery().keySet().iterator());
    }

    @Override
    public String[] getParameterValues(String name) {
        return Iterables.toArray(this.request.getQuery().getAll(name), String.class);
    }

    @Override
    public Map getParameterMap() {
        return this.request.getQuery();
    }

    @Override
    public String getProtocol() {
        return this.request.getValue("Protocol");
    }

    @Override
    public String getScheme() {
        return this.request.getAddress().getScheme();
    }

    @Override
    public String getServerName() {
        return this.request.getAddress().getDomain();
    }

    @Override
    public int getServerPort() {
        return this.request.getAddress().getPort();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), getCharacterEncoding()));
    }

    @Override
    public String getRemoteAddr() {
        return this.request.getAddress().toString();
    }

    @Override
    public String getRemoteHost() {
        return this.request.getAddress().getDomain();
    }

    @Override
    public void setAttribute(String name, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAttribute(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Locale getLocale() {
        return Iterables.getFirst(this.request.getLocales(), null);
    }

    @Override
    public Enumeration getLocales() {
        return Iterators.asEnumeration(this.request.getLocales().iterator());
    }

    @Override
    public boolean isSecure() {
        return this.request.isSecure();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    @Override
    public String getRealPath(String path) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return this.request.getAddress().getPort();
    }

    @Override
    public String getLocalName() {
        return this.request.getClientAddress().getHostName();
    }

    @Override
    public String getLocalAddr() {
        return this.request.getClientAddress().getAddress().toString();
    }

    @Override
    public int getLocalPort() {
        return this.request.getClientAddress().getPort();
    }

    private final Request request;

}
