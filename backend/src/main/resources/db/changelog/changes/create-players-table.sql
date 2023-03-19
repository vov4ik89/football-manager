--liquibase formatted sql
--changeset <postgres>:<create-players-table>

CREATE TABLE IF NOT EXISTS players
(
    id             bigserial PRIMARY KEY,
    firstname      varchar(255),
    lastname       varchar(255),
    age            integer not null,
    experience     integer not null,
    team_id        bigint CONSTRAINT players_fk REFERENCES teams
    );

--rollback DROP TABLE players;