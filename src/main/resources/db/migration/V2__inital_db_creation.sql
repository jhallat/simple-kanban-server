CREATE TABLE status (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	category varchar(15),
	code varchar(10),
	description VARCHAR(25),
	initial TINYINT(1),
	primary key (id)
);

INSERT INTO status (category, code, description, initial) VALUES ('backlog', 'active', 'Active', 1);
INSERT INTO status (category, code, description, initial) VALUES ('backlog','cancelled', 'Cancelled', 0);
INSERT INTO status (category, code, description, initial) VALUES ('backlog','workflow', 'In Workflow', 0);

INSERT INTO status (category, code, description, initial) VALUES ('workflow', 'ready', 'Ready', 1);
INSERT INTO status (category, code, description, initial) VALUES ('workflow','inprogress', 'In Progress', 0);
INSERT INTO status (category, code, description, initial) VALUES ('workflow','done', 'Done', 0);
INSERT INTO status (category, code, description, initial) VALUES ('workflow','cancelled', 'Cancelled', 0);

INSERT INTO status (category, code, description, initial) VALUES ('goal', 'onhold', 'On Hold', 1);
INSERT INTO status (category, code, description, initial) VALUES ('goal', 'active', 'Active', 0);
INSERT INTO status (category, code, description, initial) VALUES ('goal', 'completed', 'Completed', 0);
INSERT INTO status (category, code, description, initial) VALUES ('goal', 'cancelled', 'Cancelled', 0);


CREATE TABLE backlog_task (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	creation_date DATE,
	status_id INT NOT NULL,
	description VARCHAR(250),
	primary key (id)	
);

CREATE TABLE workflow_task (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	creation_date DATE,
	status_id INT NOT NULL,
	status_date DATE,
	description VARCHAR(250),
	primary key (id)	
);

CREATE TABLE goal (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id INT NOT NULL,
	priority INT NOT NULL,
	creation_date DATE,
	description VARCHAR(250),
	expectation TEXT,
	alternatives TEXT,
	strategy TEXT,
	primary key (id)
);