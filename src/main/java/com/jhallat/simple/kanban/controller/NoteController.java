package com.jhallat.simple.kanban.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhallat.simple.kanban.model.Note;
import com.jhallat.simple.kanban.repository.NoteRepository;
import com.jhallat.simple.kanban.security.CurrentUserUtility;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1")
public class NoteController {

	private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	private CurrentUserUtility currentUserUtility;
	
	@Autowired
	private NoteRepository noteRepository;
	
	
	@GetMapping("notes")
	public List<Note> getNotes(@RequestHeader HttpHeaders headers) {
		
		long userId = currentUserUtility.getCurrentUserId(headers);
		return noteRepository.findByUserId(userId);
	}
	
	@PostMapping("notes")
	@ApiOperation(value="Add a note",
    notes="Adds a notes and returns the newly created note.")
	public Note addNote(@Valid @RequestBody Note note, @RequestHeader HttpHeaders headers) {
		long userId = currentUserUtility.getCurrentUserId(headers);
		note.setUserId(userId);
		return noteRepository.saveAndFlush(note);
	}
	
	@PutMapping("notes/{id}")
	public Note updateNote(@PathVariable("id") int id, @RequestBody Note note, @RequestHeader HttpHeaders headers) {
		
		long userId = currentUserUtility.getCurrentUserId(headers);
		Optional<Note> searchResult = noteRepository.findById(id);
		if (searchResult.isPresent()) {
			Note currentNote = searchResult.get();
			currentNote.setText(note.getText());
			currentNote.setStatusId(note.getStatusId());
			currentNote.setUserId(userId);
			return noteRepository.saveAndFlush(currentNote);
		} else {
			//TODO This should return an error
			return addNote(note, headers);
		}
		
	}
}
