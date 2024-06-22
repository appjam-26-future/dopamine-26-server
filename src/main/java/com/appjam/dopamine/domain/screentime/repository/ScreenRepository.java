package com.appjam.dopamine.domain.screentime.repository;

import com.appjam.dopamine.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<User, Long> {
}
