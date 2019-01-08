-- release 0.0.1

CREATE TABLE IF NOT EXISTS playlists (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  title NOT NULL UNIQUE,
  created_at DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

INSERT INTO playlists (title)
  SELECT 'default' WHERE NOT EXISTS(SELECT 1 FROM playlists WHERE title = 'default');

CREATE TABLE IF NOT EXISTS playlist_files (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  playlist_id NOT NULL,
  created_at DATETIME NOT NULL DEFAULT (datetime('now','localtime')),
  path NOT NULL,

  FOREIGN KEY (playlist_id) REFERENCES playlists(id)
);

CREATE TABLE IF NOT EXISTS states (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  playlist_id INTEGER NOT NULL,
  playlist_file_id INTEGER,
  time_label_order VARCHAR NOT NULL DEFAULT ('ASC'),
  modified_at DATETIME NOT NULL DEFAULT (datetime('now','localtime')),

  FOREIGN KEY (playlist_id) REFERENCES playlists(id),
  FOREIGN KEY (playlist_file_id) REFERENCES playlist_files(id) ON DELETE SET NULL
);

INSERT INTO states (playlist_id)
  SELECT id FROM playlists
  WHERE title = 'default' AND (SELECT count(*) FROM states) = 0;