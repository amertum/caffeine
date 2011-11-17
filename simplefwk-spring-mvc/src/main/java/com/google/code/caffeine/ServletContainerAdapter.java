package com.google.code.caffeine;

import com.google.common.base.Throwables;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

public class ServletContainerAdapter
        implements Container {

    public ServletContainerAdapter(
            final Servlet servlet
    ) {
        this.servlet = servlet;
    }

    @Override
    public void handle(
            final Request request,
            final Response response
    ) {
        try {
            this.servlet.service(new ServletRequestAdapter(request), new ServletResponseAdapter(response));
        } catch (ServletException e) {
            throw Throwables.propagate(e);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    private final Servlet servlet;

}
