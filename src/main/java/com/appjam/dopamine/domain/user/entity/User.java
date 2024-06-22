package com.appjam.dopamine.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        이메일
    */
    @Column(nullable = false)
    private String email;

    /*
        패스워드
    */
    @Column(nullable = false)
    private String password;

    /*
        이름
    */
    @Column(nullable = false)
    private String name;

    public static User registerUser(
            String email,
            String password,
            String name
    ) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.name = name;

        return user;
    }
}
