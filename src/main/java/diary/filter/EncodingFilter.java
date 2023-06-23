package diary.filter;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Encoding filter.
 * 
 * @author iceman
 * @version 1.0
 */
public class EncodingFilter implements Filter {
    private String encoding = null;

    /**
     * This method is called when initializes instance.
     *
     * @param fConfig {@code FilterConfig}
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        ResourceBundle rb = ResourceBundle.getBundle("system");
        this.encoding = rb.getString("encoding");
    }

    /**
     * This method is called when destroy instance.
     */
    public void destroy() {
    }

    /**
     * This method is called when Filter Class is called.
     * 
     * @param request  {@code HttpServletRequest}
     * @param response {@code HttpServletResponse}
     * @param chain    {@code FilterChain}
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // run before doGet() or doPost().
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
        // run after doGet() or doPost().
    }

}
