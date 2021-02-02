This is a RESTful app for managing notes.
It provides following endpoints:

GET: /note-manager/note/
app responses with the list of all notes

GET: /note-manager/note/{noteId}
app responses with the history of changes on the given note

PUSH: /note-manager/note/
json structure:
{
"title": "",
"content": ""
}
creates new note

PUT: /note-manager/note/{noteId}
json structure:
{
"title": "",
"content": ""
}
updates note with given ID

DELETE: /note-manager/note/{noteId}
app deletes note with given id
