package com.kacper.musicapp.repository;

import com.kacper.musicapp.model.IntervalQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervalQuestionsRepository extends JpaRepository<IntervalQuestions, Integer>
{
}
