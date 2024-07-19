package com.example.footballteamapi.controller;

import com.example.footballteamapi.model.Team;
import com.example.footballteamapi.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
class TeamControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
    }

    /**
     * @throws Exception
     */

    //1.me auto pernaei
    // @Test
    // void testGetAllTeams() throws Exception {
    //     Team team = new Team();
    //     team.setId(1L);
    //     team.setName("Nice FC");
    //     team.setAcronym("NFC");
    //     team.setBudget(5000000.00);
    //     team.setPlayers(Collections.emptyList());

    //     Pageable pageable = PageRequest.of(0, 10);
    //     Page<Team> page = new PageImpl<>(Collections.singletonList(team), pageable, 1);
    //     when(teamService.getAllTeams(pageable)).thenReturn(page);

    //     // mockMvc.perform(get("/api/teams")
    //     //         .param("page", "0")
    //     //         .param("size", "10")
    //     //         .param("sortBy", "name"))
    //     //         .andExpect(status().isOk())
    //     //         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //     //         .andExpect(content().json("[{\"id\":1,\"name\":\"Nice FC\",\"acronym\":\"NFC\",\"budget\":5000000.00,\"players\":[]}]"));

    //2.me auto den pernaei
    //     String jsonResponse = mockMvc.perform(get("/api/teams")
    //         .param("page", "0")
    //         .param("size", "10")
    //         .param("sortBy", "name"))
    //         .andExpect(status().isOk())
    //         .andReturn()
    //         .getResponse()
    //         .getContentAsString();

    //     System.out.println("Response: " + jsonResponse);
    // }

    @Test
    void testGetAllTeams() throws Exception {
        Team team = new Team();
        team.setId(1L);
        team.setName("Nice FC");
        team.setAcronym("NFC");
        team.setBudget(5000000.00);
        team.setPlayers(Collections.emptyList());

        Pageable pageable = PageRequest.of(0, 10);
        Page<Team> page = new PageImpl<>(Collections.singletonList(team), pageable, 1);
        when(teamService.getAllTeams(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/teams")
                .param("page", "0")
                .param("size", "10")
                .param("sortBy", "name"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"content\":[{\"id\":1,\"name\":\"Nice FC\",\"acronym\":\"NFC\",\"budget\":5000000.00,\"players\":[]}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":10,\"paged\":true,\"unpaged\":false},\"totalPages\":1,\"totalElements\":1,\"last\":true,\"first\":true,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":1,\"size\":10,\"number\":0,\"empty\":false}"));
    }

    @Test
    void testCreateTeam() throws Exception {
        String teamJson = "{\"name\":\"Nice FC\",\"acronym\":\"NFC\",\"budget\":5000000.00,\"players\":[]}";

        mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(teamJson))
                .andExpect(status().isOk());
    }
}
