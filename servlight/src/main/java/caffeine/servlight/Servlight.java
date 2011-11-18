package caffeine.servlight;

import javax.servlet.ServletException;
import java.io.IOException;

public interface Servlight {
    
    void start()
            throws IOException, ServletException;

    void stop()
            throws IOException;
    
}
