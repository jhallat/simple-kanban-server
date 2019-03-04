package com.jhallat.simple.kanban.command.port;

import java.util.Optional;

import com.jhallat.simple.kanban.domain.Note;

public interface NoteCommand {

	Note create(String userid, Note note);
	Optional<Note> update(Note note);
	
}
