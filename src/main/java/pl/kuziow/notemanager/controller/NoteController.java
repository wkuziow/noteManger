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
import pl.kuziow.notemanager.service.ErrorService;
import pl.kuziow.notemanager.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    ErrorService errorService;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<NoteCrumbREST> getAllNotes() {
        return noteService.getListOfAllNotes();
    }

    @GetMapping(path = "/{noteId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<NoteCrumbREST> getHistoryOfNote(@PathVariable String noteId) {
        errorService.noteExists(noteId);
        return noteService.getHistoryOfNote(noteId);
    }


    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NoteCrumbREST postMethod(@RequestBody NoteCrumbRequest noteCrumbRequest) {
        errorService.checkRequiredFields(noteCrumbRequest);
        ModelMapper modelMapper = new ModelMapper();
        NoteCrumbDTO noteCrumbDTO = modelMapper.map(noteCrumbRequest, NoteCrumbDTO.class);

        return noteService.createNote(noteCrumbDTO);
    }


    @PutMapping(path = "/{noteId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NoteCrumbREST putMethod(@PathVariable String noteId, @RequestBody NoteCrumbRequest noteCrumbRequest) {
        errorService.noteExists(noteId);
        ModelMapper modelMapper = new ModelMapper();
        NoteCrumbDTO noteCrumbDTO = modelMapper.map(noteCrumbRequest, NoteCrumbDTO.class);
        return noteService.updateNote(noteId, noteCrumbDTO);
    }

    @DeleteMapping(path = "/{noteId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String noteId) {
        errorService.noteExists(noteId);
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        noteService.deteleNote(noteId);
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return operationStatusModel;
    }
}
