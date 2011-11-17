package com.google.code.caffeine;

import com.google.common.base.Throwables;
import org.apache.commons.lang.LocaleUtils;
import org.simpleframework.http.Response;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

public class ServletResponseAdapter implements HttpServletResponse {

    public ServletResponseAdapter(Response response) {
        this.response = response;
    }

    @Override
    public String getCharacterEncoding() {
        return this.response.getContentType().getCharset();
    }

    @Override
    public String getContentType() {
        return this.response.getContentType().toString();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        final OutputStream outputStream = this.response.getOutputStream();
        return new ServletOutputStream() {
            @Override
            public void write(final int b)
                    throws IOException
            {
                outputStream.write(b);
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(this.response.getOutputStream());
    }

    @Override
    public void setCharacterEncoding(String charset) {
        this.response.getContentType().setCharset(charset);
    }

    @Override
    public void setContentLength(int len) {
        this.response.setContentLength(len);
    }

    @Override
    public void setContentType(String type) {
        this.response.getContentType().setPrimary(type);
    }

    @Override
    public void setBufferSize(int size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBufferSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flushBuffer() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetBuffer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCommitted() {
        return this.response.isCommitted();
    }

    @Override
    public void reset() {
        try {
            this.response.reset();
        }
        catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void setLocale(Locale loc) {
        this.response.add("Content-Language", loc.toString());
    }

    @Override
    public Locale getLocale() {
        return LocaleUtils.toLocale(this.response.getValue("Content-Language"));
    }

    @Override
    public void addCookie(final Cookie cookie)
    {
        this.response.setCookie(Cookies.simpleCookie().apply(cookie));
    }

    @Override
    public boolean containsHeader(final String name)
    {
        return this.response.contains(name);
    }

    @Override
    public String encodeURL(final String url)
    {
        return url;
    }

    @Override
    public String encodeRedirectURL(final String url)
    {
        return url;
    }

    @Override
    public String encodeUrl(final String url)
    {
        return url;
    }

    @Override
    public String encodeRedirectUrl(final String url)
    {
        return url;
    }

    @Override
    public void sendError(
            final int sc,
            final String msg)
            throws IOException
    {
        this.response.setCode(sc);
        this.response.getPrintStream().print(msg);
    }

    @Override
    public void sendError(final int sc)
            throws IOException
    {
        this.response.setCode(sc);
    }

    @Override
    public void sendRedirect(final String location)
            throws IOException
    {
        this.response.setCode(302);
    }

    @Override
    public void setDateHeader(
            final String name,
            final long date)
    {
        this.response.setDate(name, date);
    }

    @Override
    public void addDateHeader(
            final String name,
            final long date)
    {
        this.response.addDate(name, date);
    }

    @Override
    public void setHeader(
            final String name,
            final String value)
    {
        this.response.set(name, value);
    }

    @Override
    public void addHeader(
            final String name,
            final String value)
    {
        this.response.add(name, value);
    }

    @Override
    public void setIntHeader(
            final String name,
            final int value)
    {
        this.response.set(name, value);
    }

    @Override
    public void addIntHeader(
            final String name,
            final int value)
    {
        this.response.add(name, value);
    }

    @Override
    public void setStatus(final int sc)
    {
        this.response.setCode(sc);
    }

    @Override
    public void setStatus(
            final int sc,
            final String sm)
    {
        this.response.setCode(sc);
        this.response.setText(sm);
    }

    private final Response response;

}
