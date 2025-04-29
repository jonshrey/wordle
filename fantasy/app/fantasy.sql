CREATE TABLE Teams (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    matches INTEGER DEFAULT 0,
    wins INTEGER DEFAULT 0,
    losses INTEGER DEFAULT 0,
    noresults INTEGER DEFAULT 0,
    points INTEGER DEFAULT 0
);

CREATE TABLE Players (
    id INTEGER PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    team_id INTEGER NOT NULL,
    value INTEGER DEFAULT 100,
    FOREIGN KEY(team_id) REFERENCES Teams(id)
);

INSERT INTO Teams(name) VALUES('Chennai Super Kings');
INSERT INTO Players(first_name, last_name, team_id) VALUES(
    'MS',
    'Dhoni',
    (SELECT id FROM Teams WHERE name = 'Chennai Super Kings')
);