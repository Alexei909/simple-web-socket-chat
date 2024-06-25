package com.example.ws_chat.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "status")
    private Status status;
    
}
