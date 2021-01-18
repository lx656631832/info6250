package edu.neu.info6250.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.neu.info6250.dao.StudentDao;
import edu.neu.info6250.pojo.Course;
import edu.neu.info6250.pojo.Session;
import edu.neu.info6250.pojo.Student;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentDao dao;
	
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
        return "student/index";
    }
	
	@RequestMapping(value = "/menu.htm", method = RequestMethod.GET)
    public String showTeacherMenu(){
        return "student/menu";
    }
	
	@RequestMapping(value = "/showAddSession.htm", method = RequestMethod.GET)
    public String showAddSession(HttpServletRequest request){

        return "student/addSession";
    }
	
    @RequestMapping(value = "/viewSession.htm", method = RequestMethod.POST)
    public String viewSession(HttpServletRequest request){
		String department = request.getParameter("department");
		request.setAttribute("department", department);
		List<Session> sessions = dao.getSessionList(department);
		request.setAttribute("sessions", sessions);
		
        return "student/addSession";
    }
	
    @RequestMapping(value = "/addSession.htm", method = RequestMethod.POST)
    public String addSession(HttpServletRequest request){
        String department = request.getParameter("department");
        Student theStu = (Student) request.getSession().getAttribute("user");
        List<Session> sessionList = theStu.getCourses();
        //in case of add the course from other department 
        if(!theStu.getDepartment().equals(department)){
            request.setAttribute("msg1", "You cannot add the course from other department");
            return "student/addSession";
        }
		String email = (String) request.getSession().getAttribute("email");
		String[] sessionId = request.getParameterValues("selectedSessions");

		int[] id = new int[sessionId.length];

		for(int i = 0 ; i < id.length; i++) {
			id[i] = Integer.parseInt(sessionId[i]);
		}
                //in case of repeatedly add the same session 
                for(Session se : sessionList){
                    for(int i = 0; i< id.length; i++){
                        if(id[i] == se.getId()){
                             request.setAttribute("msg1", "You have choosen this course before, please do not choose it again");
                             return "student/addSession";
                        }
                    }
                }
                
               //set the limitation of the number of course a student can select
                if(!dao.checkCredit(id, theStu)){
                    request.setAttribute("msg1", "You have choosen the courses above credit limitation, please remove some course");
                    return "student/addSession";
                }
		dao.addSession(id, email);
        return "student/addSuccess";
    }
    @RequestMapping(value = "/viewMySession.htm", method = RequestMethod.GET)
    public String viewMySession(HttpServletRequest request, HttpServletResponse response){
        String email = (String) request.getSession().getAttribute("email");
        Student stu = dao.getStudent(email);
        List<Session> mySession = stu.getCourses();	
	request.setAttribute("sessionList", mySession);
        return "student/viewSession";
    }
    
    @RequestMapping(value = "/deleteSession.htm", method = RequestMethod.GET)
    public String deleteMySession(HttpServletRequest request, HttpServletResponse response){
       	String idString = request.getParameter("id");
	int id = Integer.parseInt(idString);
        //String email = (String) request.getSession().getAttribute("email");
        Student stu = (Student) request.getSession().getAttribute("user");
	dao.deleteMySession(id, stu);
        return "student/viewSession";
    }
	
}
