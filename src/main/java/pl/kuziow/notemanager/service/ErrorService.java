package pl.kuziow.notemanager.service;

import pl.kuziow.notemanager.request.NoteCrumbRequest;

public interface ErrorService {
    void noteExists(String noteId);

    void checkRequiredFields(NoteCrumbRequest noteCrumbRequest);
}
