package com.egabi.blockchain.chequeClearing.entities;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole{

    private Integer userRoleId;
   // private PortalUser portalUser;
    private String role;

    public UserRole() {
    }

    public UserRole(String role) {
        //this.portalUser = portalUser;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ROLE_ID",
            unique = true, nullable = false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    public PortalUser getPortalUser() {
//        return this.portalUser;
//    }
//
//    public void setPortalUser(PortalUser portalUser) {
//        this.portalUser = portalUser;
//    }

    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

