package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class NoteAdapter extends ArrayAdapter {
    private Context context;
    private int resources;
    List<Notes>notes;

    NoteAdapter(Context context, int resources, List<Notes> notes){
        super(context,resources,notes);
        this.context = context;
        this.resources = resources;
        this.notes = notes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View row =inflater.inflate(resources,parent,false);

        TextView subject = row.findViewById(R.id.Subejct);
        TextView Note = row.findViewById(R.id.Notes);

        Notes note = notes.get(position);
        subject.setText(note.getSubjects());
        Note.setText(note.getNotes());
        return row;
    }
}
