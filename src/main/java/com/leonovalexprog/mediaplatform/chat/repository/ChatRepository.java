package com.leonovalexprog.mediaplatform.chat.repository;

import com.leonovalexprog.mediaplatform.chat.model.Chat;
import com.leonovalexprog.mediaplatform.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query(value = "select case when count(c) > 0 then true else false end " +
            "from Chat as c " +
            "where (c.firstUser = ?1 and c.secondUser = ?2) or " +
            "(c.firstUser = ?2 and c.secondUser = ?1)")
    boolean existsByUsers(User firstUser, User secondUser);

    @Query(value = "select c from Chat as c " +
            "where (c.firstUser = ?1 and c.secondUser = ?2) or " +
            "(c.firstUser = ?2 and c.secondUser = ?1)")
    Chat findByUsers(User firstUser, User secondUser);
}
