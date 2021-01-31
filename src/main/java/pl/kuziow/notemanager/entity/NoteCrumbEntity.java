package pl.kuziow.notemanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class NoteCrumbEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String noteCrumbId;

    @Column(nullable = false, length = 50)
    @Size(max = 50, message = "To long, maximum is 50 characters")
    @NotEmpty(message = "This field cannot be empty")
    private String title;

    @Column(nullable = false, length = 160)
    @Size(max = 160, message = "To long, maximum is 160 characters")
    @NotEmpty(message = "This field cannot be empty")
    private String content;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "note_entity_id")
    private NoteEntity noteEntity;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

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
