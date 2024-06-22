package com.appjam.dopamine.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Setter
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

    /*
        자가진단 점수
    */
    @Column
    private int testScore;

    /*
        사람 만나는 횟수 (설문에서)
    */
    @Column
    private int meetCount;

    /*
        운동 횟수 (설문에서)
    */
    @Column
    private int exerciseCount;

    /*
        스크린 타임 평균
    */
    @Column
    private int screenTimeAvg;

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
