package pl.kuziow.notemanager.exceptions;

public class NoteServiceException extends RuntimeException{

    public NoteServiceException(String message){
        super(message);
    }
}
