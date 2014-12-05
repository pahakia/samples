package pahakia.samples.spring.gplus.config;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hres = (HttpServletResponse) response;

        String uri = hreq.getRequestURI();
        String contextPath = hreq.getContextPath();
        String path = uri.substring(contextPath.length());
        if (path.equals("/") || path.startsWith("/public") || path.startsWith("/login") || path.equals("/loggedin")
                || path.equals("/gplus")) {
            chain.doFilter(request, response);
            return;
        }
        if (hreq.getSession().getAttribute("token") == null) {
            String orig = uri;
            if (hreq.getQueryString() != null && !hreq.getQueryString().trim().isEmpty()) {
                orig += "?" + hreq.getQueryString().trim();
            }
            hres.sendRedirect("/login?o=" + Base64.getEncoder().encodeToString(orig.getBytes()));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
