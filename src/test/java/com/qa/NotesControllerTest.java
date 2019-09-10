package com.qa;

import com.qa.controllers.NoteController;
import com.qa.models.Note;
import com.qa.repository.NotesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesControllerTest {

    @InjectMocks
    private NoteController noteController;

    @Mock
    private NotesRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllNotes(){
        List<Note> notesList = new ArrayList<>();
        Note note = new Note();
        note.setDescription("blah");
        note.setName("gneruibv yu");
        notesList.add(note);

        //makes it so that *when* something is called it does something
        when(repository.findAll()).thenReturn(notesList);

        assertEquals(noteController.listAllNotes().get(0).getName(), "gneruibv yu");
    }

    @Test
    public void testGetNoteByID(){
        Note note = new Note();
        note.setName("noot");
        note.setDescription("nyet");

        when(repository.findOne(0l)).thenReturn(note);

        assertEquals(noteController.getNote(0l).getName(), "noot");

    }

    @Test
    public void testAddNote(){
        List<Note> notesList = new ArrayList<>();
        Note note = new Note();
        note.setDescription("blah");
        note.setName("blyat");
        notesList.add(note);

        Note neet = new Note();
        neet.setDescription("blaviebnvh");
        neet.setName("blarwevyat");
        //notesList.add(neet);

        when(repository.saveAndFlush(neet)).thenReturn(neet);

        assertEquals(noteController.addNote(neet).getName(), "blarwevyat");
    }

    @Test
    public void testDelete(){

    }
}
