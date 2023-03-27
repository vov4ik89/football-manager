package com.example.footballmanager.controller;

import com.example.footballmanager.dto.mapper.TeamMapper;
import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.TeamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamMapper teamMapper;
    private final TeamService teamService;

    public TeamController(TeamMapper teamMapper, TeamService teamService) {
        this.teamMapper = teamMapper;
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get team by id")
    public TeamResponseDto get(@PathVariable Long id) {
        Team team = teamService.get(id);
        return teamMapper.mapToDto(team);
    }

    @PostMapping
    @ApiOperation("Add a new team")
    public TeamResponseDto create(@RequestBody TeamRequestDto requestDto) {
        Team team = teamMapper.mapToModel(requestDto);
        teamService.add(team);
        return teamMapper.mapToDto(team);
    }

    @PostMapping("/{id}")
    @ApiOperation("Update team by id")
    public TeamResponseDto update(@PathVariable Long id,
                                  @RequestBody TeamRequestDto requestDto) {
        Team team = teamMapper.mapToModel(requestDto);
        team.setId(id);
        teamService.update(team);
        return teamMapper.mapToDto(team);
    }

    @GetMapping("/{id}/{teamToId}/transfer")
    @ApiOperation("Transfer player by id")
    public void transferPlayer(@PathVariable Long id,
                               @PathVariable Long teamToId) {
        teamService.transferPlayer(id, teamToId);
    }
}
