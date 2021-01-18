package edu.neu.info6250.dao;

import static edu.neu.info6250.dao.DAO.close;
import static edu.neu.info6250.dao.DAO.getSession;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import edu.neu.info6250.pojo.Session;
import edu.neu.info6250.pojo.Student;
import org.hibernate.HibernateException;

@Repository
public class StudentDao extends DAO{
	public Student checkLogin(String un, String up) {
		String hql = "FROM Student WHERE email = :un AND password = :up";
		Query query = getSession().createQuery(hql);
		query.setString("un", un);
		query.setString("up",up);
		return (Student) query.uniqueResult();
	}
        public Student getStudent(String email){
                String hql = "FROM Student WHERE email = :un";
		Query query = getSession().createQuery(hql);
		query.setString("un", email);
		return (Student) query.uniqueResult();
        }
	
	public List<Session> getSessionList(String department){
		String hql = "from Session where department = :de";
		Query<Session> query = getSession().createQuery(hql);
		query.setString("de", department);
		return query.list();
	}
	public void addSession(int[] sessionId, String email) {
		begin();
		String hql1 = "from Session where id = :id";
		String hql2 = "from Student where email = :email";
		Query query = getSession().createQuery(hql2);
		query.setString("email", email);
		Student student = (Student)query.uniqueResult();
		for(int i = 0; i < sessionId.length; i++) {
			int id = sessionId[i];
			Query qy = getSession().createQuery(hql1);
			qy.setInteger("id",id);
			Session session = (Session)qy.uniqueResult();
			student.add(session);
		}
		commit();
		close();
	}
        public boolean checkCredit(int[] sessionId, Student student){

            List<Session> sessionList = student.getCourses();
            int credit = 0;
            for(Session session: sessionList){
                credit += session.getCourse().getCredit();
            }
            String hql = "from Session where id = :id";
            for(int i = 0; i < sessionId.length; i++) {
			int id = sessionId[i];
			Query qy = getSession().createQuery(hql);
			qy.setInteger("id",id);
			Session session = (Session)qy.uniqueResult();
			credit += session.getCourse().getCredit();
		}
            if(credit > 8) return false;
            else return true;
        }
	public void deleteMySession(int id, Student student) throws HibernateException{
		try {
			begin();
			String hql = "FROM Session where id = :id";
			Query query = getSession().createQuery(hql);
			query.setInteger("id", id);
			Session session = (Session) query.uniqueResult();
			session.delete(student);
                        student.delete(session);
			commit();
			close();
			
		}catch(HibernateException e) {
			throw new HibernateException("wrong");
		}		
	}
}
