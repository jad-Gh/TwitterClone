package com.example.twitterclone.Tweet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {

    Page<Tweet> findByTextContainingIgnoreCaseAndCreatedAtBetween(String text, LocalDateTime minDate,LocalDateTime maxDate, Pageable pageable);

}
