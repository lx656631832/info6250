package edu.neu.info6250.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
        String url = request.getServletPath();    
        System.out.println("post URL："+url);
        if(!url.equals("")){
            
            String loginUser = (String)request.getSession().getAttribute("email");
            if(loginUser == null){
                
                System.out.println(">>>need to login in<<<");
                request.setAttribute("msg3","Login information not found, you need to log in at first");
    	        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request,response);
                return false;
            }
        }
        return true;
		//return super.preHandle(request, response, handler);
	}

	
}

