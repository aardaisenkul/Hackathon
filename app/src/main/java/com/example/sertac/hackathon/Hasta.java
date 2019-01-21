package com.example.sertac.hackathon;

import java.io.Serializable;

/**
 * This class helps for creating patient objects
 */
public class Hasta implements Serializable {
    private String name;
    private int age;
    private String period;
    private String gender;
    private String treatment;
    private int tc_no;
    private int[] symptoms;  // this integer array holds symptoms as 0s and 1s(if 0 there isn't that symptom if 1 he shows that symptom)
    //also symptoms are enumerated like symptoms[0] is fever, symptoms[1] is headache etc...


    /**
     * Constructor for creating patient object
     * @param name
     * @param age
     * @param period
     * @param gender
     * @param treatment
     * @param tc_no
     * @param symptoms
     */
    public Hasta(String name, int age, String period, String gender, String treatment,int tc_no, int[] symptoms) {
        this.name = name;
        this.age = age;
        this.period = period;
        this.gender = gender;
        this.treatment = treatment;
        this.tc_no = tc_no;
        this.symptoms = symptoms;
    }

    /**
     * This method returns how many symptoms is same with "that patient"
     * @param that patient which is compared
     * @return (int)# of symptoms that matched
     */
    public int eslesmeMiktari(Hasta that){
        int count=0;
        for (int i = 0; i< this.symptoms.length; i++) {
            if((this.symptoms[i] == 1) && (this.symptoms[i] == that.symptoms[i])){

                count++;

            }
        }
        return count;
    }

    //Getter and setter methods

    public void setDiseases(int[] hastaliklar){
        this.symptoms = hastaliklar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public int getTc_no() {
        return tc_no;
    }

    public void setTc_no(int tc_no) {
        this.tc_no = tc_no;
    }

    public int[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(int[] symptoms) {
        this.symptoms = symptoms;
    }
}
