package com.example.footballteamapi.service;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.repository.TeamRepository;
import com.example.footballteamapi.repository.PlayerRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Player addPlayerToTeam(Long teamId, Player player) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            player.setTeam(team.get());
            return playerRepository.save(player);
        } else {
            throw new RuntimeException("Team not found");
        }
    }

    public Page<Player> getPlayersByTeamId(Long teamId, Pageable pageable) {
        return playerRepository.findAllByTeamId(teamId, pageable);
    }
}