package com.example.courseproject;

public class ProcedureData extends AppointmentData {
    private String id;
    private String doc;
    private String animal;
    private String date;
    private String procedure;

    public ProcedureData(String id, String doc, String animal, String date, String procedure) {
        this.id = id;
        this.doc = doc;
        this.animal = animal;
        this.date = date;
        this.procedure = procedure;
    }

    public ProcedureData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoc(){
        return doc;
    }

    public void setDoc(String doc){
        this.doc = doc;
    }

    public String getAnimal(){
        return animal;
    }

    public void setAnimal(String animal){
        this.animal = animal;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getProcedure(){
        return procedure;
    }

    public void setProcedure(String procedure){
        this.procedure = procedure;
    }
}
