package com.example.footballteamapi.service;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.repository.TeamRepository;
import com.example.footballteamapi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    // Get a paginated list of all teams
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    // Get a team by its ID
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    // Create a new team
    @Transactional
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    // Add a player to an existing team
    @Transactional
    public Player addPlayerToTeam(Long teamId, Player player) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        player.setTeam(team);
        return playerRepository.save(player);
    }

    // Get players of a specific team
    public Page<Player> getPlayersByTeamId(Long teamId, Pageable pageable) {
        return playerRepository.findAllByTeamId(teamId, pageable);
    }

    // Update team details
    @Transactional
    public Team updateTeam(Long id, Team teamDetails) {
        Team team = teamRepository.findById(id).orElseThrow();
        team.setName(teamDetails.getName());
        team.setAcronym(teamDetails.getAcronym());
        team.setBudget(teamDetails.getBudget());
        return teamRepository.save(team);
    }

    // Delete a team by its ID
    @Transactional
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow();
        teamRepository.delete(team);
    }
}
