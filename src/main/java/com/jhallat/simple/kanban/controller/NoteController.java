package com.jhallat.simple.kanban.controller;

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

import com.jhallat.simple.kanban.command.port.NoteCommand;
import com.jhallat.simple.kanban.domain.Note;
import com.jhallat.simple.kanban.exception.NotFoundException;
import com.jhallat.simple.kanban.query.port.NoteQuery;
import com.jhallat.simple.kanban.security.CurrentUserUtility;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1")
public class NoteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	private CurrentUserUtility currentUserUtility;
	
	@Autowired
	private NoteQuery noteQuery;
	
	@Autowired
	private NoteCommand noteCommand;
	
	@GetMapping("notes")
	public List<Note> getNotes(@RequestHeader HttpHeaders headers) {
		
		long userId = currentUserUtility.getCurrentUserId(headers);
		return noteQuery.findByUserId(Long.toString(userId));
	}
	
	@PostMapping("notes")
	@ApiOperation(value="Add a note",
    notes="Adds a notes and returns the newly created note.")
	public Note addNote(@Valid @RequestBody Note note, @RequestHeader HttpHeaders headers) {
		long userId = currentUserUtility.getCurrentUserId(headers);
		return this.noteCommand.create(Long.toString(userId), note);
	}
	
	@PutMapping("notes/{id}")
	public Note updateNote(@PathVariable("id") int id, @RequestBody Note note, @RequestHeader HttpHeaders headers) throws NotFoundException {
		
		Optional<Note> result = this.noteCommand.update(note);
		if (result.isPresent()) {
			return result.get();
		}
		throw new NotFoundException(String.format("Note id = %s not found.", id));
		
	}
}
