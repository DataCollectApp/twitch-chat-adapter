CREATE TABLE event
(
    id          UUID PRIMARY KEY,
    event_type  VARCHAR NOT NULL,
    object_type VARCHAR NOT NULL,
    version     VARCHAR NOT NULL,
    time        VARCHAR NOT NULL,
    raw_data    VARCHAR NOT NULL
);

CREATE TABLE active_channel
(
    ID          SERIAL PRIMARY KEY,
    CHANNEL     VARCHAR NOT NULL,
    JOINED_TIME VARCHAR NOT NULL
);
