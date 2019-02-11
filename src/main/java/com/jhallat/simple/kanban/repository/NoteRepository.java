package com.jhallat.simple.kanban.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	List<Note> findByUserId(long userId);

}
