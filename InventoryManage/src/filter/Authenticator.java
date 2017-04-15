package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebFilter("/Authenticator")
public class Authenticator implements Filter {

    private FilterConfig filterConfig=null;
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse res=(HttpServletResponse) response;
    	String action=req.getParameter("action");
    	
    	User user=(User)req.getSession().getAttribute("user");

    	if(action==null)
    		action="viewitem";
    	
    	if ((user==null || user.equals(""))&&(!action.equals("register") && !action.equals("userlogin"))) 
        {
    		req.getSession().setAttribute("user", null); 
    		 
    		res.sendRedirect("user/login.jsp");
             
    		//System.out.println("login faild");
        }
    	else{
    	chain.doFilter(request, response);}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig=fConfig;
	}
	public Authenticator() {
       
    }

	
	public void destroy() {
		this.filterConfig=null;
	}

}
