package com.jhallat.simple.kanban.command.adapter.mysql;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.jhallat.simple.kanban.command.port.NoteCommand;
import com.jhallat.simple.kanban.domain.Note;
import com.jhallat.simple.kanban.dto.NoteDTO;
import com.jhallat.simple.kanban.model.Status;
import com.jhallat.simple.kanban.repository.NoteRepository;
import com.jhallat.simple.kanban.repository.StatusRepository;

@Component
@ConditionalOnProperty(name = "command.adapter.type" , havingValue="mysql")
public class NoteCommandAdapter implements NoteCommand {

	private static final String NOTE_CATEGORY = "note";
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired 
	//TODO Replace with StatusQuery
	private StatusRepository statusRepository;
	
	private Map<String, Integer> statusMap = null;
	
	private void initializeStatusMap() {
		List<Status> statuses = statusRepository.findByCategory(NOTE_CATEGORY);
		this.statusMap = statuses.stream().collect(Collectors.toMap(Status::getCode, Status::getId));
	}
	
	@Override
	public Note create(String userid, Note note) {

		if (this.statusMap == null) {
			initializeStatusMap();
		}
		
		NoteDTO noteDTO = new NoteDTO();
		noteDTO.setId(0);
		noteDTO.setUserId(Long.parseLong(userid));
		int statusId = this.statusMap.get(note.getStatusCode());
		noteDTO.setStatusId(statusId);
		noteDTO.setText(note.getText());
		NoteDTO savedNoteDTO = this.noteRepository.saveAndFlush(noteDTO);
		
		Note updatedNote = new Note();
		updatedNote.setNoteId(Integer.toString(savedNoteDTO.getId()));
		updatedNote.setStatusCode(note.getStatusCode());
		updatedNote.setText(note.getText());
		return updatedNote;
	}

	@Override
	public Optional<Note> update(Note note) {

		if (this.statusMap == null) {
			initializeStatusMap();
		}
		
		Optional<NoteDTO> searchResult = noteRepository.findById(Integer.parseInt(note.getNoteId()));
		if (searchResult.isPresent()) {
			NoteDTO currentNote = searchResult.get();
			currentNote.setText(note.getText());
			int statusId = this.statusMap.get(note.getStatusCode());
			currentNote.setStatusId(statusId);
			
			Note updatedNote = new Note();
			updatedNote.setNoteId(note.getNoteId());
			updatedNote.setStatusCode(note.getStatusCode());
			updatedNote.setText(note.getText());
			return Optional.of(updatedNote);
		} else {
			return Optional.ofNullable(null);
		}
	}

}
