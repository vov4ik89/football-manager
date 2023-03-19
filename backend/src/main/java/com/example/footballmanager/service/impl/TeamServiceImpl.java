package com.example.footballmanager.service.impl;

import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.TeamService;
import com.example.footballmanager.util.TransferValidator;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private static final double EXPERIENCE_CONST = 10000;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TransferValidator transferValidator;

    public TeamServiceImpl(PlayerRepository playerRepository,
                           TeamRepository teamRepository,
                           TransferValidator transferValidator) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.transferValidator = transferValidator;
    }

    @Override
    public Team get(Long id) {
        return teamRepository.getReferenceById(id);
    }

    @Override
    public Team add(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void transferPlayer(Long playerId, Long teamToId) {
        Player player = playerRepository.getReferenceById(playerId);
        Team teamFrom = player.getTeam();
        Team teamTo = teamRepository.getReferenceById(teamToId);
        BigDecimal cost = calculateCost(player, teamFrom);
        transferValidator.validateTransfer(teamFrom, cost);
        teamFrom.getPlayers().remove(player);
        teamTo.getPlayers().add(player);
        teamFrom.setBalance(teamFrom.getBalance().add(cost));
        teamTo.setBalance(teamTo.getBalance().subtract(cost));
        update(teamTo);
        update(teamFrom);
    }

    private BigDecimal calculateCost(Player player, Team teamFrom) {
        double price = player.getExperience() * EXPERIENCE_CONST / player.getAge();
        return BigDecimal.valueOf(price + price * teamFrom.getCommission() / 100);
    }
}
