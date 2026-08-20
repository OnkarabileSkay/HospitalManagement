/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicare;

/**
 *
 * @author EvelynP
 */

public class Inpatient extends Patient 
{
    private String wardNum;
    private int bedNum;

    // Constructor super() to initialize inherited attributes
    public Inpatient(String name, String surname, String ID, int age, String gender, String condition, patientCategory Category, String wardNumber, int bedNumber) 
    {
        //super() mathod to initialize inherited attributes
        super(name, surname, ID, age, gender, condition, Category);
        this.wardNum = wardNumber;
        this.bedNum = bedNumber;
    }

    // Getters and Setters for Inpatient attributes
    public String getWardNumber() 
    {
        return wardNum;
    }

    public void setWardNumber(String wardNumber) 
    {
        this.wardNum = wardNumber;
    }

    public int getBedNumber() 
    {
        return bedNum;
    }

    public void setBedNumber(int bedNumber) 
    {
        this.bedNum = bedNumber;
    }

    // Overriding displayDetails to include ward and bed information
    @Override
    public void displayDetails() 
    {
        super.displayDetails();
        System.out.println("Ward Number: " + wardNum);
        System.out.println("Bed Number: B" + String.format("%02d", bedNum));
    }
}