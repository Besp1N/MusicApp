package com.kacper.musicapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "intervals")
@AllArgsConstructor
@NoArgsConstructor
public class Intervals
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "interval_name")
    private String intervalName;

    @Column(name = "first_note")
    private String firstNote;

    @Column(name = "second_note")
    private String secondNote;
}
