package com.google.code.caffeine;

import com.google.common.io.ByteStreams;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.simpleframework.http.core.Container;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.DispatcherServlet;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ServletContainerAdapterTest {

    @Test
    public void testHandle()
            throws Exception
    {
        final DispatcherServlet servlet = new DispatcherServlet();
        final MockServletConfig servletConfig = new MockServletConfig("test");
        servletConfig.addInitParameter("contextConfigLocation", "classpath:/hello-servlet.xml");
        servlet.init(servletConfig);

        final Container container = new ServletContainerAdapter(servlet, "/test");
        final Connection connection = new SocketConnection(container);
        final SocketAddress address = new InetSocketAddress(9595);

        connection.connect(address);

        final HttpClient httpClient = new DefaultHttpClient();
        final HttpResponse httpResponse = httpClient.execute(new HttpGet("http://localhost:9595/test/hello/world"));
        final byte[] content = ByteStreams.toByteArray(httpResponse.getEntity().getContent());
        System.out.println(new String(content, "UTF-8"));

        connection.close();
    }

}
