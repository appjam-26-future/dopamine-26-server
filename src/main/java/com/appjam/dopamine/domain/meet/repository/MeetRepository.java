package com.appjam.dopamine.domain.meet.repository;

import com.appjam.dopamine.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetRepository extends JpaRepository<User, Long> {
}
