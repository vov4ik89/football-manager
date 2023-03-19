package com.example.footballmanager.service;

import com.example.footballmanager.model.Team;

public interface TeamService {
    Team get(Long id);

    Team add(Team team);

    Team update(Team team);

    void transferPlayer(Long playerId, Long teamToId);
}
