package com.example.footballmanager.dto.mapper;

import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    private final TeamService teamService;

    public PlayerMapper(TeamService teamService) {
        this.teamService = teamService;
    }

    public Player mapToModel(PlayerRequestDto dto) {
        Player player = new Player();
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setAge(dto.getAge());
        player.setExperience(dto.getExperience());
        player.setTeam(teamService.get(dto.getTeamId()));
        return player;
    }

    public PlayerResponseDto mapToDto(Player player) {
        PlayerResponseDto dto = new PlayerResponseDto();
        dto.setId(player.getId());
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        dto.setAge(player.getAge());
        dto.setExperience(player.getExperience());
        dto.setTeamId(player.getTeam().getId());
        return dto;
    }
}
