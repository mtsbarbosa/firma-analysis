CREATE TABLE IF NOT EXISTS availabilities (
                                     id uuid DEFAULT uuid_generate_v4(),
                                     title varchar(100) NOT NULL,
                                     name varchar(100) NOT NULL,
                                     description varchar(255) NOT NULL,
                                     dates timestamp array NOT NULL,
                                     created_at timestamp NOT NULL,
                                     poll_id varchar(40),
                                     options_length integer,

                                     PRIMARY KEY (id)
 );
 CREATE INDEX IF NOT EXISTS idx_availabilities_poll_id ON availabilities (poll_id);
