package com.leonovalexprog.mediaplatform.chat.model;

import com.leonovalexprog.mediaplatform.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueFirstUserAndSecondUser", columnNames =
        {"first_user", "second_user"})})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "first_user")
    private User firstUser;

    @OneToOne
    @JoinColumn(name = "second_user")
    private User secondUser;
}
