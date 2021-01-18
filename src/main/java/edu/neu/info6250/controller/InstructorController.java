package edu.neu.info6250.controller;

import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.info6250.dao.InstructorDao;
import edu.neu.info6250.pojo.Course;
import edu.neu.info6250.pojo.Session;
import org.apache.commons.lang3.StringEscapeUtils;



@Controller
@RequestMapping("/teacher")
public class InstructorController {
	
    @Autowired
    private InstructorDao dao;
	
    @RequestMapping(value = "/welcome.htm", method = RequestMethod.GET)
    public String showWelcome(){
        return "welcome";
    }
	
    @RequestMapping(value = "/header.htm", method = RequestMethod.GET)
    public String showHeader(){
        return "header";
    }
	
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String showTeacherIndex(){
        return "teacher/index";
    }
	
    @RequestMapping(value = "/menu.htm", method = RequestMethod.GET)
    public String showTeacherMenu(){
        return "teacher/menu";
    }
	
    @RequestMapping(value = "/showAddSession.htm", method = RequestMethod.GET)
    public String showAddSession(HttpServletRequest request){
        //get the coures list from the dao
	List<Course> courseList = dao.getCouseList();
        request.setAttribute("courseList", courseList);
        return "teacher/addSession";
    }
	@RequestMapping(value = "/addSession.htm", method = RequestMethod.POST)
	public String processAddSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
            //in case of script injecting
                if(request.getAttribute("unsafe_request") == "true"){
                    request.setAttribute("msg4", "Illegal information detected, please re-enter");
                    List<Course> courseList = dao.getCouseList();
                    request.setAttribute("courseList", courseList);
                    return "teacher/addSession";
                }
            //get parameter from front end
	    String courseName = request.getParameter("courseName");
	    String location = request.getParameter("location");
	    String time = request.getParameter("time");
	    String department = request.getParameter("department");
	    String email = (String) request.getSession().getAttribute("email");
            //in case of empty value input
                if(StringUtils.isBlank(department) || StringUtils.isBlank(time) || StringUtils.isBlank(location) ){
                    request.setAttribute("msg5", "Empty information detected, please re-enter");
                    List<Course> courseList = dao.getCouseList();
                    request.setAttribute("courseList", courseList);
                    return "teacher/addSession";

                }
                //get the session the instructor added before
                List<Session> mySessionList = dao.viewMySession(email);
                //in case of add the same session
                for(Session session:mySessionList){
                    if(session.getCourse().getName().equals(courseName) && session.getLocation().equals(location) && session.getTime().equals(time) && session.getDepartment().equals(department)){
                        request.setAttribute("msg5", "This course already exists, you cannot add it repeatedly");
                        List<Course> courseList = dao.getCouseList();
                        request.setAttribute("courseList", courseList);
                        return "teacher/addSession";
                    }
                }
		dao.addSession(courseName, email, location, department, time);
		return "teacher/addResult";
	}
	@RequestMapping(value = "/viewSession.htm", method = RequestMethod.GET)
	public String viewSession(HttpServletRequest request, HttpServletResponse response) {
		String email = (String) request.getSession().getAttribute("email");
		//get the session related to the instructor
		List<Session> mySession = dao.viewMySession(email);
		
		request.setAttribute("sessionList", mySession);
		return "teacher/viewMySession";
	}
	@RequestMapping(value = "/deleteSession.htm", method = RequestMethod.GET)
	public void deleteSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		
		dao.deleteMySession(id);

		response.sendRedirect(request.getContextPath()+"/teacher/viewSession.htm");
		
	}
	
	@RequestMapping(value = "/showUpdateSession.htm", method = RequestMethod.GET)
	public String updateSession(HttpServletRequest request, HttpServletResponse response) {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		Session session = dao.getTheSession(id);
		request.setAttribute("session", session);
		
		List<Course> courseList = dao.getCouseList();
		request.setAttribute("courseList", courseList);
		
		return "teacher/updateSession";
	}
	
	@RequestMapping(value = "/updateSession.htm", method = RequestMethod.POST)
	public String updateAddSession(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String courseName = request.getParameter("courseName");
		String location = request.getParameter("location");
		String time = request.getParameter("time");
		String department = request.getParameter("department");
		String email = (String) request.getSession().getAttribute("email");
		String idString = request.getParameter("id");
                if(request.getAttribute("unsafe_request") == "true"){
                    request.setAttribute("msg4", "Illegal information detected, please re-enter");
                    List<Course> courseList = dao.getCouseList();
		    request.setAttribute("courseList", courseList);
                    request.setAttribute("location", location);
                    request.setAttribute("time", time);
                    request.setAttribute("department", department);
                    request.setAttribute("id", idString);
                    return "teacher/updateSession";
                }
                if(StringUtils.isBlank(department) || StringUtils.isBlank(time) || StringUtils.isBlank(location)){
                    request.setAttribute("msg5", "Empty information detected, please re-enter");
                    List<Course> courseList = dao.getCouseList();
		    request.setAttribute("courseList", courseList);
                    request.setAttribute("location", location);
                    request.setAttribute("time", time);
                    request.setAttribute("department", department);
                    request.setAttribute("id", idString);
                    return "teacher/updateSession";
                }
                
		int id = Integer.parseInt(idString);
		
		Session session = dao.getTheSession(id);
		
		dao.updateSession(session, courseName, email, location, department, time);
		return "teacher/addResult";
	}
}
