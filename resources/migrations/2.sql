CREATE TABLE IF NOT EXISTS members (
                                     id varchar(40),
                                     username varchar(100),
                                     first_name varchar(100) NOT NULL,
                                     last_name varchar(100),
                                     city varchar(40),

                                     PRIMARY KEY (id)
);
