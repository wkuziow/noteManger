package pl.kuziow.notemanager.dto;


import pl.kuziow.notemanager.entity.NoteCrumbEntity;

import java.time.LocalDateTime;
import java.util.List;

public class NoteDTO {

    private long id;

    private String noteId;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private List<NoteCrumbEntity> noteCrumbEntityList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<NoteCrumbEntity> getNoteCrumbEntityList() {
        return noteCrumbEntityList;
    }

    public void setNoteCrumbEntityList(List<NoteCrumbEntity> noteCrumbEntityList) {
        this.noteCrumbEntityList = noteCrumbEntityList;
    }
}
