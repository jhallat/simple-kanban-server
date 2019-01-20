CREATE TABLE backlog_status (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	description VARCHAR(25),
	primary key (id)
);

INSERT INTO backlog_status (description) VALUES ('Active');
INSERT INTO backlog_status (description) VALUES ('Cancelled');
INSERT INTO backlog_status (description) VALUES ('In Workflow');

CREATE TABLE backlog_task (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	status_id INT NOT NULL,
	description VARCHAR(25),
	primary key (id)	
)