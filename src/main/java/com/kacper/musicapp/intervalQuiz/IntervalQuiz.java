package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "interval_quiz")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntervalQuiz
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IntervalQuestion> questions;


}
