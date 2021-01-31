package pl.kuziow.notemanager.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kuziow.notemanager.Utils.Utils;
import pl.kuziow.notemanager.dto.NoteCrumbDTO;
import pl.kuziow.notemanager.entity.NoteCrumbEntity;
import pl.kuziow.notemanager.entity.NoteEntity;
import pl.kuziow.notemanager.repository.NoteCrumbRepository;
import pl.kuziow.notemanager.repository.NoteRepository;
import pl.kuziow.notemanager.response.NoteCrumbREST;
import pl.kuziow.notemanager.service.NoteService;

@Service
public class NoteServiceImplementation implements NoteService {

    @Autowired
    Utils utils;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteCrumbRepository noteCrumbRepository;

    @Override
    public NoteCrumbREST createNote(NoteCrumbDTO noteCrumbDTO) {

        ModelMapper modelMapper = new ModelMapper();

        NoteCrumbEntity noteCrumbEntity = modelMapper.map(noteCrumbDTO, NoteCrumbEntity.class);
        noteCrumbEntity.setNoteCrumbId(utils.generateNoteCrumbId(30));
        noteCrumbEntity.prePersist();

        NoteEntity noteEntity = new NoteEntity();
        String noteId = utils.generateNoteId(30);
        noteEntity.setNoteId(noteId);
        noteEntity.prePersist();
        NoteEntity savedNoteEntity = noteRepository.save(noteEntity);

        noteCrumbEntity.setNoteEntity(savedNoteEntity);
        NoteCrumbEntity savedNoteCrumbEntity = noteCrumbRepository.save(noteCrumbEntity);

        NoteCrumbREST noteCrumbREST = modelMapper.map(savedNoteCrumbEntity, NoteCrumbREST.class);
        noteCrumbREST.setNoteId(noteId);

        return noteCrumbREST;
    }
}
