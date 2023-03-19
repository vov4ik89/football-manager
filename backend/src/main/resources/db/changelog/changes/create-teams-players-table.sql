--liquibase formatted sql
--changeset <postgres>:<create-teams_players-table>

CREATE TABLE IF NOT EXISTS teams_players
(
    team_id    bigint not null CONSTRAINT teams_players_tp_fk REFERENCES teams,
    player_id  bigint not null CONSTRAINT player_uq UNIQUE REFERENCES players
);

--rollback DROP TABLE teams_players;