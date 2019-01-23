CREATE TABLE status (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	category varchar(15),
	code varchar(10),
	description VARCHAR(25),
	primary key (id)
);

INSERT INTO backlog_status (category, code, description) VALUES ('backlog', 'active', 'Active');
INSERT INTO backlog_status (category, code, description) VALUES ('backlog','cancelled', 'Cancelled');
INSERT INTO backlog_status (category, code, description) VALUES ('backlog','workflow', 'In Workflow');

INSERT INTO workflow_status (category, code, description) VALUES ('workflow', 'ready', 'Ready');
INSERT INTO workflow_status (category, code, description) VALUES ('workflow','inprogress', 'In Progress');
INSERT INTO workflow_status (category, code, description) VALUES ('workflow','done', 'Done');
INSERT INTO workflow_status (category, code, description) VALUES ('workflow','cancelled', 'Cancelled');

INSERT INTO backlog_status (category, code, description) VALUES ('goal', 'active', 'Active');
INSERT INTO backlog_status (category, code, description) VALUES ('goal','cancelled', 'Cancelled');


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

CREATE TABLE goal {
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id
	description varchar(250),
	expectation text,
	alternatives text,
	strategy text
	primary key (id)
};