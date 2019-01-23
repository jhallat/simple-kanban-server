CREATE TABLE backlog_status (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	code varchar(10),
	description VARCHAR(25),
	primary key (id)
);

INSERT INTO backlog_status (code, description) VALUES ('active', 'Active');
INSERT INTO backlog_status (code, description) VALUES ('cancelled', 'Cancelled');
INSERT INTO backlog_status (code, description) VALUES ('workflow', 'In Workflow');

CREATE TABLE backlog_task (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id INT NOT NULL,
	description VARCHAR(250),
	primary key (id)	
);

CREATE TABLE workflow_status (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	code varchar(10),
	description VARCHAR(25),
	primary key (id)
);

INSERT INTO workflow_status (code, description) VALUES ('ready', 'Ready');
INSERT INTO workflow_status (code, description) VALUES ('inprogress', 'In Progress');
INSERT INTO workflow_status (code, description) VALUES ('done', 'Done');
INSERT INTO workflow_status (code, description) VALUES ('cancelled', 'Cancelled');

CREATE TABLE workflow_task (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id INT NOT NULL,
	description VARCHAR(250),
	primary key (id)	
);