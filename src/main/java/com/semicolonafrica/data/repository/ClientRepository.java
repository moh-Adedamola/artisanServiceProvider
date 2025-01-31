package com.semicolonafrica.data.repository;

import com.semicolonafrica.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
