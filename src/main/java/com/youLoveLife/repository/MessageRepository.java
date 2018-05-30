package com.youLoveLife.repository;

import com.youLoveLife.domain.AppUser;
import com.youLoveLife.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
