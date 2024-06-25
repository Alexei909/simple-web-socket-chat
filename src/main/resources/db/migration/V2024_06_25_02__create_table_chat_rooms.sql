CREATE TABLE IF NOT EXISTS chat_rooms (
    id           VARCHAR(255) PRIMARY KEY not null,
    chat_id      VARCHAR(255) not null,
    sender_id    VARCHAR(255) not null,
    recipient_id VARCHAR(255) not null,
    UNIQUE(id)
);