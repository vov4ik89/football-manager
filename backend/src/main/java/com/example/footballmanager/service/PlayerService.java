package com.example.footballmanager.service;

import com.example.footballmanager.model.Player;

public interface PlayerService {
    Player get(Long id);

    Player add(Player player);

    Player update(Player player);
}
