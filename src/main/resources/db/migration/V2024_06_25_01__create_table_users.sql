CREATE TABLE IF NOT EXISTS users (
    nick_name VARCHAR(100) PRIMARY KEY not null,
    full_name VARCHAR(100) not null,
    status    VARCHAR(20) not null,
    UNIQUE(nick_name)
);