--liquibase formatted sql
--changeset <postgres>:<create-teams-table>

CREATE TABLE IF NOT EXISTS teams
(
    id            bigserial PRIMARY KEY,
    name          varchar(255),
    country       varchar(255),
    commission    integer not null,
    balance       numeric
    );

--rollback DROP TABLE teams;