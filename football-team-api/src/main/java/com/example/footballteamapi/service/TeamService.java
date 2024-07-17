package com.example.footballteamapi.service;

import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
}
