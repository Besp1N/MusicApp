package com.kacper.musicapp.intervalQuestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kacper.musicapp.interval.Interval;
import com.kacper.musicapp.intervalQuiz.IntervalQuiz;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name = "interval_questions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntervalQuestion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "interval_id", referencedColumnName = "id")
    private Interval interval;

    @Column(
            nullable = false,
            name = "difficulty"
    )
    private String difficulty;

    @Column(
            nullable = false,
            name = "option1"
    )
    private String option1;

    @Column(
            nullable = false,
            name = "option2"
    )
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private IntervalQuiz quiz;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervalQuestion that = (IntervalQuestion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(difficulty, that.difficulty) &&
                Objects.equals(option1, that.option1) &&
                Objects.equals(option2, that.option2) &&
                Objects.equals(option3, that.option3) &&
                Objects.equals(option4, that.option4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, difficulty, option1, option2, option3, option4);
    }

}
