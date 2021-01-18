package edu.neu.info6250.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.info6250.dao.InstructorDao;
import edu.neu.info6250.dao.StudentDao;
import edu.neu.info6250.pojo.Instructor;
import edu.neu.info6250.pojo.Student;
import org.apache.commons.lang3.StringUtils;

@Controller
public class LoginController {
	@Autowired
	private InstructorDao insDao;
	@Autowired
	private StudentDao stuDao;
        
	@RequestMapping(value = "/checklogin.htm", method = RequestMethod.POST)
	public String handlePostRequest(HttpServletRequest request, HttpServletResponse response) {
            //in case of script injecting
                if(request.getAttribute("unsafe_request") == "true"){
                    request.setAttribute("msg3", "Illegal information detected, please re-enter");
                    return "home";
                }
		String role = request.getParameter("role");
		String uname = request.getParameter("email");
		String upassword = request.getParameter("password");

                if(StringUtils.isBlank(uname) || StringUtils.isBlank(upassword)){
                    request.setAttribute("msg2", "The email or password cannot be empty! ");
			return "home";
                }
		if(role.equals("instructor")) {
			Instructor user = insDao.checkLogin(uname, upassword);
			if(user == null ) {
				request.setAttribute("msg1", "The email and password does not match! ");
				return "home";
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("email", uname);
				session.setAttribute("user", user);
				return "teacher/index";
			}
		}else if(role.equals("student")) {
			Student user = stuDao.checkLogin(uname, upassword);
			if(user == null ) {
				request.setAttribute("msg1", "The email and password does not match! ");
				return "home";
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("email", uname);
				session.setAttribute("user", user);
				return "student/index";
			}
		}else {
			request.setAttribute("msg1", "You have to choose a role");
			return "home";
		}


	}
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public void logoutController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        session.invalidate();
	        request.setAttribute("msg3","You have logged out safely!");
	        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request,response);
	}
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String homeMapping() {
		return "home";
	}
}
