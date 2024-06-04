package com.upwork.webforumapp.repository;

import com.upwork.webforumapp.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface ThreadsRepository extends JpaRepository<Thread, Integer> {
    @Query(value = "SELECT t FROM Thread t WHERE t.categoryName IN (:categories) ORDER BY t.createdAt DESC")
    Page<Thread> findByCategories(@Param("categories") List<String> categories, Pageable pageable);

    @Query("SELECT t FROM Thread t LEFT JOIN t.posts p WHERE " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%', :text, '%')) OR " +
            "LOWER(p.text) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Thread> searchThreads(@Param("text") String text);
}
