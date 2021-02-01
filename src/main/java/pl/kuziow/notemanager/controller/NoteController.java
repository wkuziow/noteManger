package pl.kuziow.notemanager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kuziow.notemanager.dto.NoteCrumbDTO;
import pl.kuziow.notemanager.request.NoteCrumbRequest;
import pl.kuziow.notemanager.response.NoteCrumbREST;
import pl.kuziow.notemanager.response.OperationStatusModel;
import pl.kuziow.notemanager.response.RequestOperationName;
import pl.kuziow.notemanager.response.RequestOperationStatus;
import pl.kuziow.notemanager.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<NoteCrumbREST> getAllNotes() {
        List<NoteCrumbREST> noteCrumbRESTS = noteService.getListOfAllNotes();
        return noteCrumbRESTS;
    }

    @GetMapping(path = "/{noteId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<NoteCrumbREST> getHistoryOfNote(@PathVariable String noteId) {
        //todo error service chceck if noteId exists
        List<NoteCrumbREST> noteCrumbRESTS = noteService.getHistoryOfNote(noteId);
        return noteCrumbRESTS;
    }


        //TODO od tego zaczac cos sie zle ustawiaja noteId przy mapowaniu jak sie zwara lista w obu metodach get

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NoteCrumbREST postMethod(@RequestBody NoteCrumbRequest noteCrumbRequest) {
        //todo error service chech required fields
        ModelMapper modelMapper = new ModelMapper();
        NoteCrumbDTO noteCrumbDTO = modelMapper.map(noteCrumbRequest, NoteCrumbDTO.class);
        NoteCrumbREST noteCrumbREST = noteService.createNote(noteCrumbDTO);

        return noteCrumbREST;
    }


    @PutMapping(path = "/{noteId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NoteCrumbREST putMethod(@PathVariable String noteId, @RequestBody NoteCrumbRequest noteCrumbRequest) {
        //todo check if note with given id exists
        ModelMapper modelMapper = new ModelMapper();
        NoteCrumbDTO noteCrumbDTO = modelMapper.map(noteCrumbRequest, NoteCrumbDTO.class);
        NoteCrumbREST noteCrumbREST = noteService.updateNote(noteId, noteCrumbDTO);
        return noteCrumbREST;
    }

    @DeleteMapping(path = "/{noteId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String noteId) {
        //todo check if note with given id exists
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        noteService.deteleNote(noteId);
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return operationStatusModel;
    }
}
