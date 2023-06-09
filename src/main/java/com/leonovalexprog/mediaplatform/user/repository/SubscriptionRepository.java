package com.leonovalexprog.mediaplatform.user.repository;

import com.leonovalexprog.mediaplatform.user.model.Subscription;
import com.leonovalexprog.mediaplatform.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface
SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    @Query(value = "select case when count(s) > 0 then true else false end " +
            "from Subscription as s " +
            "where s.user = ?1 and s.userFriend = ?2")
    boolean existsByUserAndFriend(User user, User userFriend);

    @Query(value = "select case when count(s) > 0 then true else false end " +
            "from Subscription as s " +
            "where ((s.user = ?1 and s.userFriend = ?2) or " +
            "(s.user = ?2 and s.userFriend = ?1)) and " +
            "s.isApproved = true")
    boolean areFriends(User user, User userFriends);

    Subscription findByUserAndUserFriend(User user, User friend);

    List<Subscription> findAllByUser(User user);
}
