/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicare;

/**
 *
 * @author EvelynP
 */
public class PatientManager 
{
    public String patientName;
    public String patientSurname;
    public String patientID;
    public int age;
    public String gender;
    public String medicalCondition;
    public String patientCategory;
    //Constructor
    public PatientManager(String name, String surname, String ID, int age, String gender, String condition, String category)
    {
        this.patientName=name;
        this.patientSurname=surname;
        this.patientID=ID;
        this.age=age;
        this.gender=gender;
        this.medicalCondition=condition;
        this.patientCategory=category;
    }
    
}
