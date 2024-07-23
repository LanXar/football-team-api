package com.example.footballteamapi.service;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.repository.PlayerRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    // Get players by team ID with pagination
    @Transactional
    public Page<Player> getPlayersByTeamId(Long teamId, Pageable pageable) {
        return playerRepository.findAllByTeamId(teamId, pageable);
    }

    // Get player by ID
    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    // Get all players with pagination
    @Transactional
    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }
    
    // Create a new player
    @Transactional
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    // Update player details
    @Transactional
    public Player updatePlayer(Long id, Player playerDetails) {
        Player player = playerRepository.findById(id).orElseThrow();
        player.setName(playerDetails.getName());
        player.setPosition(playerDetails.getPosition());
        return playerRepository.save(player);
    }

    // Delete a player by ID
    @Transactional
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }
}
