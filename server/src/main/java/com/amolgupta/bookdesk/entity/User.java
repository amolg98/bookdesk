package com.amolgupta.bookdesk.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(	name = "Users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT user from User user")
})
public class User {
    
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @JsonProperty("email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @JsonProperty("password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "User_Roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles; 

    @OneToOne(mappedBy = "user")
    @Transient
    private Booking booking;

    /**
     * Default Constructor
     */
    public User() {}

    /**
     * Constructor
	 * @param id
	 */
	public User(String username, String email, String password) {
		this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
	}

	/**
     * @return the userId
     */
    public String getId() {
        return id;
    }

    /**
     * @param userId the userId to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * @param booking the booking to set
     */
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    /**
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [booking=" + booking + ", email=" + email + ", id=" + id + ", password=" + password + ", username="
                + username + "]";
    }
        
}
