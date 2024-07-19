package com.kacper.musicapp.interval;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(
        name = "intervals",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"first_note", "second_note"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interval
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            nullable = false,
            name = "interval_name"
    )
    private String intervalName;

    @Column(
            nullable = false,
            name = "first_note"
    )
    private String firstNote;

    @Column(
            nullable = false,
            name = "second_note"
    )
    private String secondNote;

    @Column(
            nullable = false,
            name = "difficulty"
    )
    private String difficulty;

    @Column(
            nullable = false,
            name = "file_path"
    )
    private String filePath;
}
