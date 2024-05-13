package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNotes extends AppCompatActivity {
    EditText EdiSub, EdiNotes;
    Button save;
    Database DB;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        EdiSub = findViewById(R.id.editTextText);
        EdiNotes = findViewById(R.id.editTextText2);
        save = findViewById(R.id.button2);
        context = this;
        DB = new Database(context);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjects = EdiSub.getText().toString();
                String note = EdiNotes.getText().toString();
                Notes notes = new Notes(subjects,note);
                DB.AddNotes(notes);
                Intent intent = new Intent(AddNotes.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}