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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
@Entity
@Table(name = "Session")
public class Session {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="course_id")
	private Course course;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="teacher_id")
	private Instructor instructor;
	
	@Column(name="location")
	private String location;
	
	@Column(name="time")
	private String time;
	
	@Column(name="department")
	private String department;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="Selected_session",
			joinColumns=@JoinColumn(name="session_id"),
			inverseJoinColumns=@JoinColumn(name="student_id")
			)	
	private List<Student> stuList;
	
	public Session() {
		
	}

	public Session(Course course, Instructor instructor, String location, String time, String department) {

		this.course = course;
		this.instructor = instructor;
		this.location = location;
		this.time = time;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void delete(Student deStudent) {
            Iterator<Student> it = stuList.iterator();
            while(it.hasNext()){
                Student stu = it.next();
                if(isEqual(stu, deStudent)){
                    it.remove();
                }
            }
	}
        
        public boolean isEqual(Student a, Student b) {
		if(a.getId() == b.getId()) {
			return true;
		}else
		return false;
	}
}
