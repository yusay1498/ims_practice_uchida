package com.example.demo_jpa;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface JpaCardRepository
        extends JpaRepository<Card, Integer> {
    List<Card> findAllByLevel(Integer level);
}
