package com.kacper.musicapp.interval;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "intervals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interval
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(
            name = "interval_name",
            nullable = false
    )
    private String intervalName;

    @Column(
            name = "first_note",
            nullable = false
    )
    private String firstNote;

    @Column(
            name = "second_note",
            nullable = false
    )
    private String secondNote;

    @Column(
            name = "difficulty",
            nullable = false
    )
    private String difficulty;
}
