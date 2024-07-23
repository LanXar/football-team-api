package com.example.footballteamapi.service;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.repository.PlayerRepository;
import com.example.footballteamapi.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeamServiceTest {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTeams() {
        Pageable pageable = PageRequest.of(0, 10);
        Team team1 = new Team();
        team1.setId(1L);
        team1.setName("Nice");
        team1.setAcronym("NFC");
        team1.setBudget(1000000.0);

        Team team2 = new Team();
        team2.setId(2L);
        team2.setName("Paris");
        team2.setAcronym("PSG");
        team2.setBudget(2000000.0);

        Page<Team> page = new PageImpl<>(Arrays.asList(team1, team2), pageable, 2);

        when(teamRepository.findAll(pageable)).thenReturn(page);

        Page<Team> result = teamService.getAllTeams(pageable);
        assertEquals(2, result.getTotalElements());
        assertEquals("Nice", result.getContent().get(0).getName());
    }

    @Test
    public void testGetTeamById() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Nice");
        team.setAcronym("NFC");
        team.setBudget(1000000.0);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Optional<Team> result = teamService.getTeamById(1L);
        assertTrue(result.isPresent());
        assertEquals("Nice", result.get().getName());
    }

    @Test
    public void testCreateTeam() {
        Team team = new Team();
        team.setName("Nice");
        team.setAcronym("NFC");
        team.setBudget(1000000.0);

        when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.createTeam(team);
        assertNotNull(result);
        assertEquals("Nice", result.getName());
    }

    @Test
    public void testAddPlayerToTeam() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Nice");
        team.setAcronym("NFC");
        team.setBudget(1000000.0);

        Player player = new Player();
        player.setName("John Doe");
        player.setPosition("Forward");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(playerRepository.save(player)).thenReturn(player);

        Player result = teamService.addPlayerToTeam(1L, player);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testGetPlayersByTeamId() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Nice");
        team.setAcronym("NFC");
        team.setBudget(1000000.0);

        Player player1 = new Player();
        player1.setName("John Doe");
        player1.setPosition("Forward");
        player1.setTeam(team);

        Player player2 = new Player();
        player2.setName("Jane Doe");
        player2.setPosition("Midfielder");
        player2.setTeam(team);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Player> page = new PageImpl<>(Arrays.asList(player1, player2), pageable, 2);

        when(playerRepository.findAllByTeamId(1L, pageable)).thenReturn(page);

        Page<Player> result = teamService.getPlayersByTeamId(1L, pageable);
        assertEquals(2, result.getTotalElements());
        assertEquals("John Doe", result.getContent().get(0).getName());
    }
}

