package com.example.footballmanager.service.impl;

import com.example.footballmanager.model.Player;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player get(Long id) {
        return playerRepository.getReferenceById(id);
    }

    @Override
    public Player add(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }
}
