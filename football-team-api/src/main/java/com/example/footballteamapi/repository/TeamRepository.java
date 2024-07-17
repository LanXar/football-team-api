package com.example.footballteamapi.repository;

import com.example.footballteamapi.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}