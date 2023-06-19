package diary.filter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filter whether having session or not.
 * For Debug.
 * 
 * @author iceman
 * @version 1.0
 */
public class HaveSessionFilter implements Filter {

    public HaveSessionFilter() {
    }

    /**
     * This method is called when initialize instance.
     * 
     * @param fConfig
     * @throws ServletException
     */

    public void init(FilterConfig fConfig) throws ServletException {
    }

    /**
     * This method is called when destroy instance.
     */
    public void destroy() {
    }

    /**
     * This method is called when Filter Class is called.
     * 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(request, response);

        HttpSession session = ((HttpServletRequest) request).getSession(false); // no session, return null.

        System.out.println("1");
        if (session == null) {
            System.out.println("[DEBUG] HaveSessionFilter:: NO SESSION.");
        } else {
            System.out.println("[DEBUG] HaveSessionFilter:: SESSION EXISTS.");
            System.out.println("[DEBUG] HaveSessionFilter:: SESSION INFO");
            Enumeration<String> enums = session.getAttributeNames();

            while (enums.hasMoreElements()) {
                String key = enums.nextElement();
                Object obj = session.getAttribute(key);
                System.out.println("[DEBUG] HaveSessionFilter:: " + key + ": " + obj);
                if (obj instanceof List) {
                    for (Object o : (List<?>) obj) {
                        System.out.println("[DEBUG] HaveSessionFilter:: ListValue => " + o);
                        for (Field field : o.getClass().getDeclaredFields()) {
                            field.setAccessible(true);

                            try {
                                Object oValue = field.get(o);
                                System.out.println(
                                        "[DEBUG] HaveSessionFilter::     Value => " + field.getName() + " :: "
                                                + oValue);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            System.out.println("[DEBUG] HaveSessionFilter:: --------------------------------");

        }
    }

}
