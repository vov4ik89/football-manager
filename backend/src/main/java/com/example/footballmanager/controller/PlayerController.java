package com.example.footballmanager.controller;

import com.example.footballmanager.dto.mapper.PlayerMapper;
import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    public PlayerController(PlayerMapper playerMapper, PlayerService playerService) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get player by id")
    public PlayerResponseDto get(@PathVariable Long id) {
        Player player = playerService.get(id);
        return playerMapper.mapToDto(player);
    }

    @PostMapping
    @ApiOperation("Add a new player")
    public PlayerResponseDto create(@RequestBody PlayerRequestDto requestDto) {
        Player player = playerMapper.mapToModel(requestDto);
        playerService.add(player);
        return playerMapper.mapToDto(player);
    }

    @PostMapping("/{id}")
    @ApiOperation("Update player by id")
    public PlayerResponseDto update(@PathVariable Long id,
                                    @RequestBody PlayerRequestDto requestDto) {
        Player player = playerMapper.mapToModel(requestDto);
        player.setId(id);
        playerService.update(player);
        return playerMapper.mapToDto(player);
    }
}
