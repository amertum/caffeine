package caffeine.servlight.springweb;

import caffeine.servlight.ServletContainerAdapter;
import caffeine.servlight.Servlight;
import org.simpleframework.http.core.Container;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SpringWebServlight
        implements Servlight
{

    public SpringWebServlight(
            final String contextPath,
            final int port,
            final String contextConfigLocation)
    {
        this.contextPath = contextPath;
        this.port = port;
        this.contextConfigLocation = contextConfigLocation;
    }

    @Override
    public void start()
            throws IOException, ServletException
    {
        this.servlet = new DispatcherServlet();
        {
            final MockServletConfig servletConfig = new MockServletConfig("test");
            servletConfig.addInitParameter("contextConfigLocation", this.contextConfigLocation);
            this.servlet.init(servletConfig);
        }
        
        final Container container = new ServletContainerAdapter(this.servlet, this.contextPath);
        this.connection = new SocketConnection(container);

        this.connection.connect(new InetSocketAddress(this.port));
    }

    @Override
    public void stop()
            throws IOException
    {
        if (this.connection != null) {
            this.connection.close();
        }
        if (this.servlet != null) {
            this.servlet.destroy();
        }
    }

    private final String contextPath;
    private final int port;
    private final String contextConfigLocation;

    private Connection connection;
    private DispatcherServlet servlet;
    
}
