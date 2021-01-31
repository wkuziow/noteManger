package pl.kuziow.notemanager.dto;

import pl.kuziow.notemanager.entity.NoteEntity;

import java.time.LocalDateTime;

public class NoteCrumbDTO {

    private long id;

    private String noteCrumbId;

    private String title;

    private String content;

    private LocalDateTime createdOn;

    private NoteEntity noteEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public NoteEntity getNoteEntity() {
        return noteEntity;
    }

    public void setNoteEntity(NoteEntity noteEntity) {
        this.noteEntity = noteEntity;
    }
}
