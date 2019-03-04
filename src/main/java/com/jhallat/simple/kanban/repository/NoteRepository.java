package com.jhallat.simple.kanban.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhallat.simple.kanban.dto.NoteDTO;

public interface NoteRepository extends JpaRepository<NoteDTO, Integer> {
	
	List<NoteDTO> findByUserId(long userId);

}
