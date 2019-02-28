package com.egabi.blockchain.chequeClearing.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class PortalUser {

    private long userId;
    private String username;
    private String password;
    private boolean enabled;
    private String nationalID;
   // private Integer roleID;
    
   
    private UserRole role;
    
    
    public PortalUser() {
    }

    public PortalUser(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public PortalUser(String username, String password,
                      boolean enabled, UserRole userRole, String nationalID) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nationalID = nationalID;
        this.role=userRole;
    }

   
    @Column(name = "username", unique = true,
            nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password",
            nullable = false, length = 60)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_ROLE_ID")
    public UserRole getUserRole() {
        return this.role;
    }

    public void setUserRole(UserRole userRole) {
        this.role = userRole;
    }

    @Id
    @Column(name = "USERID",
    nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }
	@Column(name = "NATIONAL_ID",nullable = false)
	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}
	
//	@Column(name="ROLE_ID",nullable = false)
//	public Integer getRoleID() {
//		return roleID;
//	}
//
//	public void setRoleID(Integer roleID) {
//		this.roleID = roleID;
//	}
    
    
}
