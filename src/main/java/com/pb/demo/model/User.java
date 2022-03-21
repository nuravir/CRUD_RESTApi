package com.pb.demo.model;	

import java.io.Serializable;
//all the relevant imports are listed here.
import java.time.LocalDate; 
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.data.redis.core.RedisHash;

/*
 *  The model class for CRUD repository. The class 
 *  User is mapped to the table "user" in our mysql 
 *  db and Id is used as the primary key. @NotBlack,
 *  @Size, @NotNull, and @Past help define constraints
 *  on the columns of the table.
 */

//Entity renders this class suitable for storage in a relational database.
@Entity						
//Table denotes this class as the primary table that will be stored in MySQL.
@Table(name = "user")
public class User implements Serializable {
	

	//designation of id as the primary key and its auto generation.
	private @Id @GeneratedValue(strategy=GenerationType.AUTO) long id;	
	@NotBlank(message="The first name is required!")
	@Size(min=2, message="Please write more than one letter for first name.")
	private String firstname;
	@NotBlank(message="The last name is required!")
	@Size(min=2,message="Please write more than one letter for first name.")
	private String lastname;
	@NotBlank(message="The phone number is required!")
	@Size(min=6,max=10, message="Phone number is too long or short.")
	private String mobileno;
	@NotNull(message="The date of birth is required!")
	@Past(message="The date of birth must be in the past.")
	private LocalDate dob;
		
	//Blank constructor
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstname, id, lastname, mobileno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(firstname, other.firstname) && id == other.id && Objects.equals(lastname, other.lastname)
				&& Objects.equals(mobileno, other.mobileno);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", mobileno=" + mobileno
				+ "]";
	}

}
