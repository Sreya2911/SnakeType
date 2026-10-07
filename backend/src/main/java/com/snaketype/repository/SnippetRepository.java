package com.snaketype.repository;

import com.snaketype.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {

    List<Snippet> findByLanguageId(Long languageId);

    List<Snippet> findByLanguageIdAndComplexity(Long languageId, int complexity);
}