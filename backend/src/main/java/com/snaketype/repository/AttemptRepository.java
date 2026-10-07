package com.snaketype.repository;

import com.snaketype.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    List<Attempt> findByUserIdOrderByCompletedAtDesc(Long userId);

    List<Attempt> findByUserIdAndLanguageIdOrderByCompletedAtDesc(
            Long userId, Long languageId);
}