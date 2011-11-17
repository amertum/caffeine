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
            final Servlet servlet,
            final String contextPath
    ) {
        this.servlet = servlet;
        this.contextPath = contextPath;
    }

    @Override
    public void handle(
            final Request request,
            final Response response
    ) {
        try {
            final ServletRequestAdapter requestAdapter = new ServletRequestAdapter(request);
            requestAdapter.setAttribute("javax.servlet.include.context_path", this.contextPath);
            final ServletResponseAdapter responseAdapter = new ServletResponseAdapter(response);

            this.servlet.service(requestAdapter, responseAdapter);
        } catch (ServletException e) {
            throw Throwables.propagate(e);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    private final Servlet servlet;
    private final String contextPath;

}
