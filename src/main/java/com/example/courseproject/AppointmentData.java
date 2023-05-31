package com.example.courseproject;

public class AppointmentData {
    private String id;
    private String doc;
    private String animal;
    private String date;
    private String complaint;
    private String diagnosis;
    private String therapy;
    private String cost;

    public AppointmentData(String id, String doc, String animal, String date, String complaint,
                           String diagnosis, String therapy, String cost) {
        this.id = id;
        this.doc = doc;
        this.animal = animal;
        this.date = date;
        this.complaint = complaint;
        this.diagnosis = diagnosis;
        this.therapy = therapy;
        this.cost = cost;
    }

    public AppointmentData() {
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

    public String getComplaint(){
        return complaint;
    }

    public void setComplaint(String complaint){
        this.complaint = complaint;
    }

    public String getDiagnosis(){
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }

    public String getTherapy(){
        return therapy;
    }

    public void setTherapy(String therapy){
        this.therapy = therapy;
    }

    public String getCost(){
        return cost;
    }

    public void setCost(String cost){
        this.cost = cost;
    }
}
