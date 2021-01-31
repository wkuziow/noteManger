package pl.kuziow.notemanager.service;

import pl.kuziow.notemanager.dto.NoteCrumbDTO;
import pl.kuziow.notemanager.response.NoteCrumbREST;

public interface NoteService {
    NoteCrumbREST createNote(NoteCrumbDTO noteCrumbDTO);
}
