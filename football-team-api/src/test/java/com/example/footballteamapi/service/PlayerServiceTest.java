package com.example.footballteamapi.service;

import com.example.footballteamapi.model.Player;
import com.example.footballteamapi.repository.PlayerRepository;
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

public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPlayersByTeamId() {
        Player player1 = new Player(1L, "Player1", "Forward", null);
        Player player2 = new Player(2L, "Player2", "Midfielder", null);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Player> page = new PageImpl<>(Arrays.asList(player1, player2), pageable, 2);

        when(playerRepository.findAllByTeamId(1L, pageable)).thenReturn(page);

        Page<Player> result = playerService.getPlayersByTeamId(1L, pageable);
        assertEquals(2, result.getTotalElements());
        assertEquals("Player1", result.getContent().get(0).getName());
    }

    @Test
    public void testGetPlayerById() {
        Player player = new Player(1L, "Player1", "Forward", null);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        Optional<Player> result = playerService.getPlayerById(1L);
        assertTrue(result.isPresent());
        assertEquals("Player1", result.get().getName());
    }

    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player(1L, "Player1", "Forward", null);
        Player player2 = new Player(2L, "Player2", "Midfielder", null);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Player> page = new PageImpl<>(Arrays.asList(player1, player2), pageable, 2);

        when(playerRepository.findAll(pageable)).thenReturn(page);

        Page<Player> result = playerService.getAllPlayers(pageable);
        assertEquals(2, result.getTotalElements());
        assertEquals("Player1", result.getContent().get(0).getName());
    }

    @Test
    public void testCreatePlayer() {
        Player player = new Player();
        player.setName("Player1");
        player.setPosition("Forward");

        when(playerRepository.save(player)).thenReturn(player);

        Player result = playerService.createPlayer(player);
        assertNotNull(result);
        assertEquals("Player1", result.getName());
    }

    @Test
    public void testUpdatePlayer() {
        Player player = new Player(1L, "Player1", "Forward", null);

        Player updatedPlayer = new Player(1L, "Player1 Updated", "Forward", null);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(playerRepository.save(player)).thenReturn(updatedPlayer);

        Player result = playerService.updatePlayer(1L, updatedPlayer);
        assertNotNull(result);
        assertEquals("Player1 Updated", result.getName());
    }

    @Test
    public void testDeletePlayerById() {
        doNothing().when(playerRepository).deleteById(1L);

        playerService.deletePlayerById(1L);

        verify(playerRepository, times(1)).deleteById(1L);
    }
}
