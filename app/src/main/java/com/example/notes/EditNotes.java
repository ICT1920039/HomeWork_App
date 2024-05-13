package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNotes extends AppCompatActivity {
    EditText subject,Note;
    Button save;
    Database DB;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        context = this;
        subject = findViewById(R.id.editTextText3);
        Note = findViewById(R.id.editTextText4);
        save = findViewById(R.id.button4);
        DB = new Database(context);


        final String id = getIntent().getStringExtra("id");
        Notes notes = DB.getSingleNote(Integer.parseInt(id));

        subject.setText(notes.getSubjects());
        Note.setText(notes.getNotes());
        System.out.println(id);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectText = subject.getText().toString();
                String NoteText = Note.getText().toString();

                Notes notes = new Notes(Integer.parseInt(id),subjectText,NoteText);
                int state =DB.UpdateNotes(notes);
                System.out.println(state);
                Intent intent = new Intent(EditNotes.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}