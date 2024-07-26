package com.kacper.musicapp.userAnswer;

import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import com.kacper.musicapp.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name = "user_answers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private IntervalQuestion question;

    @Column(nullable = false)
    private String givenAnswer;

    @Column(nullable = false)
    private boolean isCorrect;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnswer that = (UserAnswer) o;
        return isCorrect == that.isCorrect &&
                Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(question, that.question) &&
                Objects.equals(givenAnswer, that.givenAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, question, givenAnswer, isCorrect);
    }


}
