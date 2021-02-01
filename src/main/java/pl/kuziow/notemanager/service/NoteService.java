package pl.kuziow.notemanager.service;

import pl.kuziow.notemanager.dto.NoteCrumbDTO;
import pl.kuziow.notemanager.response.NoteCrumbREST;

import java.util.List;

public interface NoteService {
    NoteCrumbREST createNote(NoteCrumbDTO noteCrumbDTO);

    void deteleNote(String noteId);

    NoteCrumbREST updateNote(String noteId, NoteCrumbDTO noteCrumbDTO);

    List<NoteCrumbREST> getListOfAllNotes();

    List<NoteCrumbREST> getHistoryOfNote(String noteId);
}
