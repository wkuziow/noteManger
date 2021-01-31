package pl.kuziow.notemanager.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kuziow.notemanager.dto.NoteCrumbDTO;
import pl.kuziow.notemanager.request.NoteCrumbRequest;
import pl.kuziow.notemanager.response.NoteCrumbREST;
import pl.kuziow.notemanager.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping( //TODO get list of all notes (last crumb for each note
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String getMethod() {
        return "get method triggered";
    }

    //todo get history of a note

    @PostMapping( //todo create new note by posting new note Crumb
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public NoteCrumbREST postMethod(@RequestBody NoteCrumbRequest noteCrumbRequest){
        //todo error service chech required fields
        ModelMapper modelMapper = new ModelMapper();
        NoteCrumbDTO noteCrumbDTO = modelMapper.map(noteCrumbRequest, NoteCrumbDTO.class);
        NoteCrumbREST noteCrumbREST = noteService.createNote(noteCrumbDTO);

        return noteCrumbREST;
    }



    @PutMapping( //todo adit note by adding new crumb
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String putMethod() {
        return "put method was triggered";
    }

    @DeleteMapping( //todo delete note by changing boolean
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deleteUser() {
        return "delete method was triggered";
    }
}
