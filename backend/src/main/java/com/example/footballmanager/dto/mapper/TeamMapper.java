package com.example.footballmanager.dto.mapper;

import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.repository.PlayerRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    private final PlayerRepository playerRepository;

    public TeamMapper(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Team mapToModel(TeamRequestDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setCountry(dto.getCountry());
        team.setCommission(dto.getCommission());
        team.setBalance(dto.getBalance());
        team.setPlayers(dto.getPlayerIds().stream()
                .map(playerRepository::getReferenceById)
                .collect(Collectors.toList()));
        return team;
    }

    public TeamResponseDto mapToDto(Team team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setCountry(team.getCountry());
        dto.setBalance(team.getBalance());
        dto.setPlayerIds(team.getPlayers().stream()
                .map(Player::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
