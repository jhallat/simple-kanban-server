CREATE TABLE notes (
	id INT NOT NULL AUTO_INCREMENT,
	status_id INT,
	user_id INT,
	text TEXT,
	PRIMARY KEY (id)
);

INSERT INTO status (category, code, description, initial) VALUES ('note', 'active', 'Active', 1);
INSERT INTO status (category, code, description, initial) VALUES ('note','deleted', 'Deleted', 0);