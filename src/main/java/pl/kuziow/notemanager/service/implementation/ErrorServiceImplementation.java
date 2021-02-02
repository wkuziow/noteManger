package pl.kuziow.notemanager.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kuziow.notemanager.entity.NoteEntity;
import pl.kuziow.notemanager.exceptions.ErrorMessages;
import pl.kuziow.notemanager.exceptions.NoteServiceException;
import pl.kuziow.notemanager.repository.NoteRepository;
import pl.kuziow.notemanager.request.NoteCrumbRequest;
import pl.kuziow.notemanager.service.ErrorService;

@Service
public class ErrorServiceImplementation implements ErrorService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public void noteExists(String noteId) {
        NoteEntity noteEntity = noteRepository.findByNoteId(noteId);
        if (noteEntity == null) {
            throw new NoteServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

    }

    @Override
    public void checkRequiredFields(NoteCrumbRequest noteCrumbRequest) {
        if (noteCrumbRequest.getTitle() == null ||
                noteCrumbRequest.getTitle().isEmpty() ||

                noteCrumbRequest.getContent() == null ||
                noteCrumbRequest.getContent().isEmpty()) {
            throw new NoteServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

    }
}
