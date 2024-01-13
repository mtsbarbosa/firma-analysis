CREATE TABLE IF NOT EXISTS votes (
                                     id uuid DEFAULT uuid_generate_v4(),
                                     poll_id varchar(40),
                                     member_id varchar(40) references members(id),
                                     member_city varchar(40),
                                     options integer array,

                                     PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_votes_member_id ON votes (member_id);
CREATE INDEX IF NOT EXISTS idx_votes_member_city ON votes (member_city);
