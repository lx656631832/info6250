package edu.neu.info6250.pojo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "FirstName")
	
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Department")
	private String department;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="Selected_session",
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns=@JoinColumn(name="session_id")
			)	
	private List<Session> sessionList;
	
	public Student() {
		
	}
	
	

	public Student(int id, String firstName, String lastName, String email, String password, List<Session> sessionList, String department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.sessionList = sessionList;
		this.department = department;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Session> getCourses() {
		return sessionList;
	}

	public void setCourses(List<Session> courses) {
		this.sessionList = courses;
	}
    public void add(Session tempSession) {
		
		if (sessionList == null) {
			sessionList = new ArrayList<Session>();
		}
		
		sessionList.add(tempSession);
		
	}
	
	public void delete(Session deSession) {
            Iterator<Session> it = sessionList.iterator();
            while(it.hasNext()){
                Session see = it.next();
                if(isEqual(see, deSession)){
                    it.remove();
                }
            }
	}
        
        public boolean isEqual(Session a, Session b) {
		if(a.getId() == b.getId()) {
			return true;
		}else
		return false;
	}
}
