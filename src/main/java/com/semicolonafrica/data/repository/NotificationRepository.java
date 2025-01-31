package com.semicolonafrica.data.repository;

import com.semicolonafrica.data.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
