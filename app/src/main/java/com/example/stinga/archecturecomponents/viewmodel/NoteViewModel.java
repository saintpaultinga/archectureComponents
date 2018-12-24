package com.example.stinga.archecturecomponents.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.stinga.archecturecomponents.entity.Note;
import com.example.stinga.archecturecomponents.repository.NoteRepository;

import java.util.List;




public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void inserNote(Note note) {
        repository.insert(note);
    }

    public void updateNote(Note note) {
        repository.update(note);
    }

    public void deleteNote(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
