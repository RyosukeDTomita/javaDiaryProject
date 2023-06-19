package diary.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface of Bean.
 * 
 * @author iceman
 * @version 1.0
 */
public interface ExecuteInterface {
    /**
     * @param request  HTTP request
     * @param response HTTP response
     * @return String "success" or "failure".
     */
    public String execute(HttpServletRequest request, HttpServletResponse response);
}
