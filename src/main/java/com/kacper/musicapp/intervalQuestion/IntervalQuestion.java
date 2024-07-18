package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.interval.Interval;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
public class IntervalQuestion
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "interval_id", referencedColumnName = "id", nullable = false)
    private Interval interval;

    @Column(nullable = false, name = "ans1")
    private String ans1;

    @Column(nullable = false, name = "ans2")
    private String ans2;

    @Column(nullable = false, name = "ans3")
    private String ans3;

    @Column(nullable = false, name = "ans4")
    private String ans4;
}
