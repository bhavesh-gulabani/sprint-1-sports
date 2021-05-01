package com.cg.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@SequenceGenerator(name = "userSequence", initialValue = 101, allocationSize = 1)

//@MappedSuperclass
@Entity
@Table(name = "Test_System_user", uniqueConstraints = {
	      @UniqueConstraint(columnNames = "email", name = "uniqueEmailConstraint")})
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
	private long id;
	@Email(message = "Invalid email")
	private String email;
	@NotBlank(message = "Password cannot be blank")
	//@JsonProperty(access = Access.WRITE_ONLY )
	private String password;
	private String role;
	
	public User() {
		super();
	}
	
	public User(long id, @NotBlank(message = "Email cannot be blank") @Email String email,
			@NotBlank(message = "Password cannot be blank") String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(@NotBlank(message = "Email cannot be blank") @Email String email,
			@NotBlank(message = "Password cannot be blank") String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}	
}
