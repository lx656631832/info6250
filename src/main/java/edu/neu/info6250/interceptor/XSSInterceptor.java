/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.info6250.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author xinlu
 */
public class XSSInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       
        //Write Code here 
        try{
            Enumeration<String> paramNames = request.getParameterNames();
            while(paramNames.hasMoreElements()){
                String key = (String)paramNames.nextElement();
                String val = request.getParameter(key);
                
                if(xssCheck(val)){
                    request.setAttribute("unsafe_request", "true");
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return true;
    }
    
    private boolean xssCheck(String value) {
        if (value != null) {
            //"\"<script(.*?)>\""
           return (value.matches("<script>(.*?)</script>") || value.matches("\"<script(.*?)>\""));
        }
        return false;
    }
    private boolean nullCheck(String value){
        if(value == null) return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }


}