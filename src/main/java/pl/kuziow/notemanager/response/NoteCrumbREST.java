package pl.kuziow.notemanager.response;

import java.time.LocalDateTime;

public class NoteCrumbREST {

    private String noteId;

    private String noteCrumbId;

    private String title;

    private String content;

    private LocalDateTime createdOn;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteCrumbId() {
        return noteCrumbId;
    }

    public void setNoteCrumbId(String noteCrumbId) {
        this.noteCrumbId = noteCrumbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

}
