CREATE TABLE IF NOT EXISTS events (
                                     id uuid DEFAULT uuid_generate_v4(),
                                     title varchar(100) NOT NULL,
                                     city varchar(40) NOT NULL,
                                     type varchar(40) NOT NULL,
                                     name varchar(100) NOT NULL,
                                     description varchar(255) NOT NULL,
                                     scheduled_to timestamp NOT NULL,
                                     created_at timestamp NOT NULL,
                                     poll boolean DEFAULT false,
                                     poll_id varchar(40),
                                     options_length integer,

                                     PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_events_city ON events (city);
CREATE INDEX IF NOT EXISTS idx_events_type ON events (type);
