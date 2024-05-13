package com.example.notes;

public class Notes {
    private int id;
    private String Subjects,notes;

    public Notes() {
    }

    public Notes(int id, String subjects, String notes) {
        this.id = id;
        this.Subjects = subjects;
        this.notes = notes;
    }

    public Notes(String subjects, String notes) {
        this.Subjects = subjects;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjects() {
        return Subjects;
    }

    public void setSubjects(String subjects) {
        Subjects = subjects;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
