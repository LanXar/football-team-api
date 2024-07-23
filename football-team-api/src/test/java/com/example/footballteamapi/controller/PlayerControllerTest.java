package com.example.footballteamapi.controller;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.service.PlayerService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PlayerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void testGetPlayersByTeamId() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Player player1 = new Player(1L, "Player1", "Forward", null);
        Player player2 = new Player(2L, "Player2", "Midfielder", null);

        Page<Player> page = new PageImpl<>(Arrays.asList(player1, player2), pageable, 2);

        when(playerService.getPlayersByTeamId(1L, pageable)).thenReturn(page);

        mockMvc.perform(get("/api/players/team/1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Player1"))
                .andExpect(jsonPath("$.content[1].name").value("Player2"));

        verify(playerService, times(1)).getPlayersByTeamId(1L, pageable);
    }

    @Test
    public void testGetPlayerById() throws Exception {
        Player player = new Player(1L, "Player1", "Forward", null);

        when(playerService.getPlayerById(1L)).thenReturn(Optional.of(player));

        mockMvc.perform(get("/api/players/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Player1"));
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Player player1 = new Player(1L, "Player1", "Forward", null);
        Player player2 = new Player(2L, "Player2", "Midfielder", null);

        Page<Player> page = new PageImpl<>(Arrays.asList(player1, player2), pageable, 2);

        when(playerService.getAllPlayers(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/players")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Player1"))
                .andExpect(jsonPath("$.content[1].name").value("Player2"));

        verify(playerService, times(1)).getAllPlayers(pageable);
    }

    @Test
    public void testCreatePlayer() throws Exception {
        Player player = new Player(1L, "Player1", "Forward", null);
        when(playerService.createPlayer(any(Player.class))).thenReturn(player);

        String playerJson = "{ \"name\": \"Player1\", \"position\": \"Forward\" }";

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Player1"));

        verify(playerService, times(1)).createPlayer(any(Player.class));
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        Player updatedPlayer = new Player(1L, "Player1 Updated", "Forward", null);

        when(playerService.updatePlayer(eq(1L), any(Player.class))).thenReturn(updatedPlayer);

        String playerJson = "{ \"name\": \"Player1 Updated\", \"position\": \"Forward\" }";

        mockMvc.perform(put("/api/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Player1 Updated"));
    }

    @Test
    public void testDeletePlayer() throws Exception {
        doNothing().when(playerService).deletePlayerById(1L);

        mockMvc.perform(delete("/api/players/1"))
                .andExpect(status().isNoContent());
    }
}
