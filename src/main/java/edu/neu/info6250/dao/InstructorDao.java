package edu.neu.info6250.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import edu.neu.info6250.pojo.Course;
import edu.neu.info6250.pojo.Instructor;
import edu.neu.info6250.pojo.Session;


@Repository
public class InstructorDao extends DAO{
	
	public List<Course> getCouseList(){
		String hql = "FROM Course as course";
		Query<Course> query = getSession().createQuery(hql);

       //query.setLockMode("course",LockMode.UPGRADE);
		return  query.list();
	}
	
	public Session getTheSession(int id) {
		String hql = "FROM Session where id = :id";
		Query query = getSession().createQuery(hql);
		query.setInteger("id", id);
		return  (Session) query.uniqueResult();
	}
	
	public Instructor checkLogin(String un, String up) {
		String hql = "FROM Instructor WHERE email = :un AND password = :up";
		Query query = getSession().createQuery(hql);
		query.setString("un", un);
		query.setString("up",up);
		return (Instructor) query.uniqueResult();
	}
	
	public void addSession(String courseName, String email, String location, String department, String time) throws Exception {
		try {

			begin();
			String findCourseHql = "From Course where name = :courseName";
			String findInsHql = "From Instructor Where email = :email";
			Query query1 = getSession().createQuery(findCourseHql);
			Query query2 = getSession().createQuery(findInsHql);

			
			query1.setString("courseName", courseName);
			query2.setString("email", email);
			Course course = (Course) query1.uniqueResult();
			Instructor instructor = (Instructor) query2.uniqueResult();
			
			Session toAddSession = new Session(course, instructor, location, time, department);
			instructor.add(toAddSession);
			
			getSession().save(toAddSession);
			commit();
			close();
		}catch (HibernateException e) {
            throw new HibernateException("wrong");
            }
		
	}
	
	public void updateSession(Session theSession, String courseName, String email, String location, String department, String time) {
		try {
			begin();
//			String insId = theSession.
//			String hql = "from Instructor where session = :se";
//			Query query = getSession().createQuery(hql);
//			query.setEntity("se", theSession);
			Instructor ins = theSession.getInstructor();
			ins.delete(theSession);
			
			
			String findCourseHql = "From Course where name = :courseName";
			Query query1 = getSession().createQuery(findCourseHql);
			query1.setString("courseName", courseName);
			Course course = (Course) query1.uniqueResult();
			
			
			theSession.setCourse(course);
			theSession.setDepartment(department);
			theSession.setLocation(location);
			theSession.setTime(time);
			
			getSession().update(theSession);
			ins.add(theSession);
			commit();
			close();

		}catch (HibernateException e) {
            throw new HibernateException("wrong");
        }finally{
                    rollback();
                }
	}
	
	public List<Session> viewMySession(String email){
		String hql1 = "FROM Instructor WHERE email = :un";
		Query query = getSession().createQuery(hql1);
		query.setString("un", email);
		Instructor instructor = (Instructor) query.uniqueResult();

		
		String hql2 = "FROM Session WHERE instructor = :ins";
		Query<Session> query2 = getSession().createQuery(hql2);
		query2.setEntity("ins", instructor);
		return query2.list();
	}
	
	public void deleteMySession(int id) throws HibernateException{
		try {
			begin();
			String hql = "FROM Session where id = :id";
			Query query = getSession().createQuery(hql);
			query.setInteger("id", id);
			Session session = (Session) query.uniqueResult();
			
			getSession().delete(session);
			
			commit();
			close();
			
		}catch(HibernateException e) {
			throw new HibernateException("wrong");
		}		
	}
	
}
