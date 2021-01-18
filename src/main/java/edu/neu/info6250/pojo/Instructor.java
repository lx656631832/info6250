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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name ="Teacher")
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="Fname")
	private String firstName;
	
	@Column(name="Lname")
	private String lastName;
	
	@Column(name="email")
	private String emial;
	
	@Column(name="password")
	private String password;
	
	
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="instructor",
	   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
				 CascadeType.DETACH, CascadeType.REFRESH})
    private List<Session> session;
	
	public Instructor() {
		
	}

	public Instructor(int id, String firstName, String lastName, String emial, String password, List<Session> session) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emial = emial;
		this.password = password;
		this.session = session;
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

	public String getEmial() {
		return emial;
	}

	public void setEmial(String emial) {
		this.emial = emial;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Session> getSession() {
		return session;
	}

	public void setSession(List<Session> session) {
		this.session = session;
	}
	
	public void add(Session tempSession) {
		
		if (session == null) {
			session = new ArrayList<Session>();
		}
		
		session.add(tempSession);
		
		tempSession.setInstructor(this);
	}

	public void delete(Session deSession) {
            Iterator<Session> it = session.iterator();
            while(it.hasNext()){
                Session se = it.next();
                if(isEqual(se, deSession)){
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
