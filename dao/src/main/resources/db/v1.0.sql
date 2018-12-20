-- release 0.0.1

CREATE TABLE IF NOT EXISTS playlists (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  title NOT NULL UNIQUE,
  created_at DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

INSERT INTO playlists (title)
  SELECT 'default' WHERE NOT EXISTS(SELECT 1 FROM playlists WHERE title = 'default');

CREATE TABLE IF NOT EXISTS states (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  playlist_id NOT NULL,
  modified_at DATETIME NOT NULL DEFAULT (datetime('now','localtime')),

  FOREIGN KEY (playlist_id) REFERENCES playlists(id)
);

INSERT INTO states (playlist_id)
  SELECT id FROM playlists
  WHERE title = 'default' AND (SELECT count(*) FROM states) = 0;

CREATE TABLE IF NOT EXISTS playlist_files (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  playlist_id NOT NULL,
  created_at DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
  path NOT NULL,

  FOREIGN KEY (playlist_id) REFERENCES playlists(id)
);