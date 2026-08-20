package com.gabriel.challenge.domain.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="users")
@Entity(name="users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="usersId")
public class Users {

    @Id @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id")
    private String usersId;

    private String username;

    private String pass;

    public String getUsersId() {
        return this.usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Users(RequestUsers requestUsers) {
        this.username = requestUsers.username();
        this.pass = requestUsers.pass();
    }

}
