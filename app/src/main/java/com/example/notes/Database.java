package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "NotesDB";
    private static final int DB_VERSION = 6;

    private static final String TABLE_NAME = "MyNotes";
    private static final String ID_COL ="Id";
    private static final String SUBJECT_COL ="Subjects";
    private static final String NOTES_COL ="Notes";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SUBJECT_COL + " TEXT,"
                + NOTES_COL + " TEXT)";
        db.execSQL(query);
    }

    public void AddNotes(Notes notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBJECT_COL,notes.getSubjects());
        values.put(NOTES_COL,notes.getNotes());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<Notes>getAllNotes() {
        List<Notes> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Notes note = new Notes();
                note.setId(cursor.getInt(0));
                note.setSubjects(cursor.getString(1));
                note.setNotes(cursor.getString(2));

                notes.add(note);
            } while (cursor.moveToNext());

        }
        return notes;
    }
    public void DeleteNotes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"Id = ?",new String[]{String.valueOf(id)});
        db.close();
    }
    public Notes getSingleNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID_COL,SUBJECT_COL,NOTES_COL},ID_COL+"=?",new String[]{String.valueOf(id)},null,null,null);

        Notes note;
        if(cursor != null) {
            cursor.moveToFirst();
            note = new Notes(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            return note;
        }
        return null;
    }
    public int UpdateNotes(Notes notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBJECT_COL,notes.getSubjects());
        values.put(NOTES_COL,notes.getNotes());

        int status = db.update(TABLE_NAME,values,ID_COL+"=?",new String[]{String.valueOf(notes.getId())});
        db.close();
        return status;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }
}
