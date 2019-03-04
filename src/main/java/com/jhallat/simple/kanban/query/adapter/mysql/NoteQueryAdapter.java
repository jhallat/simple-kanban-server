package com.jhallat.simple.kanban.query.adapter.mysql;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.jhallat.simple.kanban.domain.Note;
import com.jhallat.simple.kanban.dto.NoteDTO;
import com.jhallat.simple.kanban.model.Status;
import com.jhallat.simple.kanban.query.port.NoteQuery;
import com.jhallat.simple.kanban.repository.NoteRepository;
import com.jhallat.simple.kanban.repository.StatusRepository;

@Component
@ConditionalOnProperty(name = "query.adapter.type" , havingValue="mysql")
public class NoteQueryAdapter implements NoteQuery {

	private static final String NOTE_CATEGORY = "note";
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired 
	//TODO Replace with StatusQuery
	private StatusRepository statusRepository;
	
	private Map<Integer, String> statusMap = null;
	
	private void initializeStatusMap() {
		List<Status> statuses = statusRepository.findByCategory(NOTE_CATEGORY);
		this.statusMap = statuses.stream().collect(Collectors.toMap(Status::getId, Status::getCode));
	}
	
	/* (non-Javadoc)
	 * @see com.jhallat.simple.kanban.query.adapter.mysql.NoteQuery#findByUserId(java.lang.String)
	 */
	@Override
	public List<Note> findByUserId(String userid) {
		
		if (this.statusMap == null) {
			initializeStatusMap();
		}
		
		long numericUserId = Long.parseLong(userid);
		
		List<NoteDTO> noteDTOs = this.noteRepository.findByUserId(numericUserId);
		return noteDTOs.stream().map(item -> {
			Note note = new Note();
			note.setNoteId(Long.toString(item.getId()));
			String code = this.statusMap.get(item.getStatusId());
			note.setStatusCode(code);
			note.setText(item.getText());
			return note;
		}).collect(Collectors.toList());

	}
	
}
