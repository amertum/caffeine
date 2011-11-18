package caffeine.servlight;

import caffeine.servlight.springweb.SpringWebServlight;
import com.google.common.io.ByteStreams;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static org.apache.http.params.HttpConnectionParams.setConnectionTimeout;
import static org.apache.http.params.HttpConnectionParams.setSoTimeout;
import static org.fest.assertions.Assertions.assertThat;

public class ServletContainerAdapterTest {

    @Test
    public void testHandleSpringXml()
            throws Exception
    {
        final SpringWebServlight servlight = new SpringWebServlight("/test", 9595, "classpath:/hello-servlet.xml");
        servlight.start();

        try {
            final HttpClient httpClient = new DefaultHttpClient();
            setSoTimeout(httpClient.getParams(), 1000);
            setConnectionTimeout(httpClient.getParams(), 2000);

            final HttpResponse httpResponse = httpClient.execute(new HttpGet("http://localhost:9595/test/hello/world"));
            final byte[] content = ByteStreams.toByteArray(httpResponse.getEntity().getContent());
            assertThat(new String(content, "UTF-8")).isEqualTo("world");
        }
        finally {
            servlight.stop();
        }
    }

}
