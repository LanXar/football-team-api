package com.example.footballteamapi.repository;

import com.example.footballteamapi.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Page<Player> findAllByTeamId(Long teamId, Pageable pageable);
}