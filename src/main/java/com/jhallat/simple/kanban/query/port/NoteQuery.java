package com.jhallat.simple.kanban.query.port;

import java.util.List;

import com.jhallat.simple.kanban.domain.Note;

public interface NoteQuery {

	List<Note> findByUserId(String userid);

}