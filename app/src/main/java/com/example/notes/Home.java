package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    Button add;
    ListView listview;
    Context context;
    Database DB;
    List<Notes> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;
        add = findViewById(R.id.button3);
        listview = findViewById(R.id.notes);
        notes = new ArrayList<>();

        DB = new Database(context);

        notes = DB.getAllNotes();
        NoteAdapter Adapter = new NoteAdapter(this,R.layout.items,notes);
        listview.setAdapter(Adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,AddNotes.class);
                startActivity(intent);
                finish();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Notes note = notes.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle(note.getSubjects());
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            DB.UpdateNotes(note);
                            Intent intent = new Intent(Home.this,Home.class);
                            startActivity(intent);
                            finish();
                    }
                });
                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Home.this, EditNotes.class);
                        intent.putExtra("id",String.valueOf(note.getId()));
                        startActivity(intent);
                        finish();

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DB.DeleteNotes(note.getId());
                        Intent intent = new Intent(Home.this,Home.class);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.show();
            }
        });

    }
}