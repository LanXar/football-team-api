package com.example.footballteamapi.controller;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TeamControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(teamController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void testGetAllTeams() throws Exception {
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

        when(teamService.getAllTeams(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/teams")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content[0].name").value("Nice"));
    }

    @Test
    public void testGetTeamById() throws Exception {
        Team team = new Team();
        team.setId(1L);
        team.setName("Nice");
        team.setAcronym("NFC");
        team.setBudget(1000000.0);

        when(teamService.getTeamById(1L)).thenReturn(Optional.of(team));

        mockMvc.perform(get("/api/teams/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nice"));
    }

    @Test
    public void testCreateTeam() throws Exception {
        Team team = new Team();
        team.setName("Nice");
        team.setAcronym("NFC");
        team.setBudget(1000000.0);

        when(teamService.createTeam(any(Team.class))).thenReturn(team);

        mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Nice\",\"acronym\":\"NFC\",\"budget\":1000000.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nice"));
    }

    @Test
    public void testAddPlayerToTeam() throws Exception {
        Player player = new Player();
        player.setName("John Doe");
        player.setPosition("Forward");

        when(teamService.addPlayerToTeam(eq(1L), any(Player.class))).thenReturn(player);

        mockMvc.perform(post("/api/teams/1/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"position\":\"Forward\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetPlayersByTeamId() throws Exception {
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

        when(teamService.getPlayersByTeamId(1L, pageable)).thenReturn(page);

        mockMvc.perform(get("/api/teams/1/players")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content[0].name").value("John Doe"));
    }

    @Test
    public void testUpdateTeam() throws Exception {
        Team updatedTeam = new Team(1L, "Nice Updated", "NFC", 1200000.0);

        when(teamService.updateTeam(eq(1L), any(Team.class))).thenReturn(updatedTeam);

        String teamJson = "{\"name\": \"Nice Updated\", \"acronym\": \"NFC\", \"budget\": 1200000.0 }";

        mockMvc.perform(put("/api/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nice Updated"));
    }

    @Test
    public void testDeleteTeam() throws Exception {
        doNothing().when(teamService).deleteTeam(1L);

        mockMvc.perform(delete("/api/teams/1"))
                .andExpect(status().isNoContent());
    }
}
