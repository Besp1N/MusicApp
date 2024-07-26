package com.kacper.musicapp.interval;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Objects.equals(id, interval.id) &&
                Objects.equals(intervalName, interval.intervalName) &&
                Objects.equals(firstNote, interval.firstNote) &&
                Objects.equals(secondNote, interval.secondNote) &&
                Objects.equals(difficulty, interval.difficulty) &&
                Objects.equals(filePath, interval.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, intervalName, firstNote, secondNote, difficulty, filePath);
    }

}
