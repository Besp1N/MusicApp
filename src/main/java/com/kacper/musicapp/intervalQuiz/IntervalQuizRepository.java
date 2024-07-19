package com.kacper.musicapp.intervalQuiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalQuizRepository extends JpaRepository<IntervalQuiz, Integer>
{
}
