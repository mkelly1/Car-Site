package com.example.demo;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleID;

    @Column(unique=true)
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<User> users;

    public Role(){}
    public Role(String role){
        this.role = role;
    }

    public long getId() {
        return roleID;
    }

    public void setId(long id) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
