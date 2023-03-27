package com.example.footballmanager;

import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.TeamService;
import com.example.footballmanager.service.impl.TeamServiceImpl;
import com.example.footballmanager.util.TransferValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TeamServiceImplTest {
    private static TeamService teamService;
    private static PlayerRepository playerRepository;
    private static TeamRepository teamRepository;
    private Player player;
    private Team team1;
    private Team team2;

    @BeforeAll
    static void beforeAll() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        teamRepository = Mockito.mock(TeamRepository.class);
        TransferValidator transferValidator = new TransferValidator();
        teamService = new TeamServiceImpl(playerRepository, teamRepository, transferValidator);
    }

    @BeforeEach
    void setUp() {
        player = new Player();
        player.setId(1L);
        player.setFirstName("Bob");
        player.setLastName("Smith");
        player.setAge(20);
        team1 = new Team();
        team1.setName("Real");
        List<Player> list1 = new ArrayList<>();
        list1.add(player);
        team1.setPlayers(list1);
        team1.setBalance(BigDecimal.valueOf(10000));
        team1.setCommission(5);
        team2 = new Team();
        team1.setName("Barcelona");
        List<Player> list2= new ArrayList<>();
        team2.setPlayers(list2);
        team2.setBalance(BigDecimal.valueOf(20000));
        team2.setCommission(3);
    }

    @Test
    void validTransfer_Ok() {
        player.setExperience(12);
        Mockito.when(playerRepository.getReferenceById(1L)).thenReturn(player);
        Mockito.when(teamRepository.getReferenceById(1L)).thenReturn(team1);
        Mockito.when(teamRepository.getReferenceById(2L)).thenReturn(team2);
        teamService.transferPlayer(1L, 2L);
        Assertions.assertEquals(BigDecimal.valueOf(10000.0), team2.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(20000.0), team1.getBalance());
    }

    @Test
    void validTransferCheckBalance_Ok() {
        player.setExperience(12);
        Mockito.when(playerRepository.getReferenceById(1L)).thenReturn(player);
        Mockito.when(teamRepository.getReferenceById(1L)).thenReturn(team1);
        Mockito.when(teamRepository.getReferenceById(2L)).thenReturn(team2);
        teamService.transferPlayer(1L, 2L);
        Assertions.assertEquals(BigDecimal.valueOf(10000.0), team2.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(20000.0), team1.getBalance());
    }

    @Test
    void validTransferCheckTeams_Ok() {
        player.setExperience(12);
        Mockito.when(playerRepository.getReferenceById(1L)).thenReturn(player);
        Mockito.when(teamRepository.getReferenceById(1L)).thenReturn(team1);
        Mockito.when(teamRepository.getReferenceById(2L)).thenReturn(team2);
        teamService.transferPlayer(1L, 2L);
        Assertions.assertEquals(List.of(player), team2.getPlayers());
        Assertions.assertEquals(List.of(), team1.getPlayers());
    }
}
