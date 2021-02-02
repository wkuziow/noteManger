package pl.kuziow.notemanager.service.implementation;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public NoteCrumbREST createNote(NoteCrumbDTO noteCrumbDTO) {

        NoteEntity noteEntity = new NoteEntity();
        String noteId = utils.generateNoteId(30);
        noteEntity.setNoteId(noteId);
        noteEntity.prePersist();
        NoteEntity savedNoteEntity = noteRepository.save(noteEntity);

        NoteCrumbEntity savedNoteCrumbEntity = createAndSaveNoteCrumbEntity(noteCrumbDTO, savedNoteEntity);

        return createNoteCrumbREST(noteId, savedNoteCrumbEntity);
    }

    public NoteCrumbEntity createAndSaveNoteCrumbEntity(NoteCrumbDTO noteCrumbDTO, NoteEntity savedNoteEntity) {
        NoteCrumbEntity noteCrumbEntity = modelMapper.map(noteCrumbDTO, NoteCrumbEntity.class);
        noteCrumbEntity.setNoteCrumbId(utils.generateNoteCrumbId(30));
        noteCrumbEntity.prePersist();

        noteCrumbEntity.setNoteEntity(savedNoteEntity);
        return noteCrumbRepository.save(noteCrumbEntity);
    }

    public NoteCrumbREST createNoteCrumbREST(String noteId, NoteCrumbEntity savedNoteCrumbEntity) {
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
        noteEntity.preUpdate();
        NoteEntity savedNoteEntity = noteRepository.save(noteEntity);

        //ModelMapper modelMapper = new ModelMapper();
        NoteCrumbEntity savedNoteCrumbEntity = createAndSaveNoteCrumbEntity(noteCrumbDTO, savedNoteEntity);

        return createNoteCrumbREST(noteId, savedNoteCrumbEntity);

    }

    @Override
    public List<NoteCrumbREST> getListOfAllNotes() {
        List<NoteCrumbEntity> noteCrumbEntityList = noteRepository.findAll().stream()
                .filter(e -> !e.isDeleted())
                .map(c -> c.getNoteCrumbEntityList().stream().
                        sorted(Comparator.comparing(NoteCrumbEntity::getCreatedOn).reversed())
                        .collect(Collectors.toList())
                        .get(0)).
                        collect(Collectors.toList());
        List<NoteCrumbREST> noteCrumbRESTS = new ArrayList<>();

       // ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        for (NoteCrumbEntity e : noteCrumbEntityList) {
            String noteId = e.getNoteEntity().getNoteId();
            createNoteCrumRestAndAddToList(noteCrumbRESTS, e, noteId);
        }
        return noteCrumbRESTS;


    }

    public void createNoteCrumRestAndAddToList(List<NoteCrumbREST> noteCrumbRESTS, NoteCrumbEntity e, String noteId) {
        NoteCrumbREST noteCrumbREST = createNoteCrumbREST(noteId, e);
        noteCrumbRESTS.add(noteCrumbREST);
    }

    @Override
    public List<NoteCrumbREST> getHistoryOfNote(String noteId) {
        List<NoteCrumbEntity> noteCrumbEntityList = noteRepository.findByNoteId(noteId).getNoteCrumbEntityList();
        List<NoteCrumbREST> noteCrumbRESTS = new ArrayList<>();

        //ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        for (NoteCrumbEntity e : noteCrumbEntityList) {
            createNoteCrumRestAndAddToList(noteCrumbRESTS, e, noteId);
        }
        return noteCrumbRESTS;
    }
}
