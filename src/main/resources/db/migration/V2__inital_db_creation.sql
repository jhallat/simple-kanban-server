CREATE TABLE status (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	category varchar(15),
	code varchar(10),
	description VARCHAR(25),
	primary key (id)
);

INSERT INTO status (category, code, description) VALUES ('backlog', 'active', 'Active');
INSERT INTO status (category, code, description) VALUES ('backlog','cancelled', 'Cancelled');
INSERT INTO status (category, code, description) VALUES ('backlog','workflow', 'In Workflow');

INSERT INTO status (category, code, description) VALUES ('workflow', 'ready', 'Ready');
INSERT INTO status (category, code, description) VALUES ('workflow','inprogress', 'In Progress');
INSERT INTO status (category, code, description) VALUES ('workflow','done', 'Done');
INSERT INTO status (category, code, description) VALUES ('workflow','cancelled', 'Cancelled');

INSERT INTO status (category, code, description) VALUES ('goal', 'active', 'Active');
INSERT INTO status (category, code, description) VALUES ('goal','cancelled', 'Cancelled');


CREATE TABLE backlog_task (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id INT NOT NULL,
	description VARCHAR(250),
	primary key (id)	
);

CREATE TABLE workflow_task (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id INT NOT NULL,
	description VARCHAR(250),
	primary key (id)	
);

CREATE TABLE goal (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id INT NOT NULL,
	description VARCHAR(250),
	expectation TEXT,
	alternatives TEXT,
	strategy TEXT,
	primary key (id)
);