package com.appjam.dopamine.domain.exercise.repository;

import com.appjam.dopamine.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<User, Long> {
}
