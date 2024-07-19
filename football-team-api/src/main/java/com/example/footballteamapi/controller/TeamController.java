package com.example.footballteamapi.controller;

import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public Page<Team> getAllTeams(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "name") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return teamService.getAllTeams(pageable);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }
    
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Page<Team>> getAllTeams(Pageable pageable) {
    //     Page<Team> teams = teamService.getAllTeams(pageable);
    //     return new ResponseEntity<>(teams, HttpStatus.OK);
    // }

    // @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Team> createTeam(@RequestBody Team team) {
    //     Team createdTeam = teamService.createTeam(team);
    //     return new ResponseEntity<>(createdTeam, HttpStatus.OK);
    // }
}
