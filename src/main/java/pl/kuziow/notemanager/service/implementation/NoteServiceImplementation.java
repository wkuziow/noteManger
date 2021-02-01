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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void deteleNote(String noteId) {
        NoteEntity noteEntity = noteRepository.findByNoteId(noteId);
        noteEntity.setDeleted(true);
        noteRepository.save(noteEntity);
    }

    @Override
    public NoteCrumbREST updateNote(String noteId, NoteCrumbDTO noteCrumbDTO) {
        NoteEntity noteEntity = noteRepository.findByNoteId(noteId);
        ModelMapper modelMapper = new ModelMapper();
        NoteCrumbEntity noteCrumbEntity = modelMapper.map(noteCrumbDTO, NoteCrumbEntity.class);
        noteCrumbEntity.setNoteCrumbId(utils.generateNoteCrumbId(30));
        noteCrumbEntity.prePersist();
        noteEntity.preUpdate();

        NoteEntity savedNoteEntity = noteRepository.save(noteEntity);

        noteCrumbEntity.setNoteEntity(savedNoteEntity);

        NoteCrumbEntity savedNoteCrumbEntity = noteCrumbRepository.save(noteCrumbEntity);

        NoteCrumbREST noteCrumbREST = modelMapper.map(savedNoteCrumbEntity, NoteCrumbREST.class);
        noteCrumbREST.setNoteId(noteId);

        return noteCrumbREST;

    }

    @Override
    public List<NoteCrumbREST> getListOfAllNotes() {
        List<NoteCrumbEntity> noteCrumbEntityList = noteRepository.findAll().stream()
                .map(c -> c.getNoteCrumbEntityList().stream().
                        sorted(Comparator.comparing(NoteCrumbEntity::getCreatedOn).reversed())
                        .collect(Collectors.toList())
                        .get(0)).
                        collect(Collectors.toList());
        List<NoteCrumbREST> noteCrumbRESTS = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for (NoteCrumbEntity e : noteCrumbEntityList) {
            NoteCrumbREST noteCrumbREST = modelMapper.map(e, NoteCrumbREST.class);
            noteCrumbRESTS.add(noteCrumbREST);
        }
        return noteCrumbRESTS;


    }

    @Override
    public List<NoteCrumbREST> getHistoryOfNote(String noteId) {
        List<NoteCrumbEntity> noteCrumbEntityList = noteRepository.findByNoteId(noteId).getNoteCrumbEntityList();
        List<NoteCrumbREST> noteCrumbRESTS = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for (NoteCrumbEntity e : noteCrumbEntityList) {
            NoteCrumbREST noteCrumbREST = modelMapper.map(e, NoteCrumbREST.class);
            noteCrumbRESTS.add(noteCrumbREST);
        }
        return noteCrumbRESTS;
    }
}
