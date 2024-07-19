package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.interval.Interval;
import com.kacper.musicapp.intervalQuiz.IntervalQuiz;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private IntervalQuiz quiz;
}
