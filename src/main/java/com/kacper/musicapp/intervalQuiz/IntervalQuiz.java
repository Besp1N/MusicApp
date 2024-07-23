package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String difficulty;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<IntervalQuestion> questions;

    public void addQuestion(IntervalQuestion intervalQuestion) {
        questions.add(intervalQuestion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervalQuiz that = (IntervalQuiz) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
