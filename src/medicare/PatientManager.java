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
    public patientCategory Category;
    //Constructor
    public PatientManager(String name, String surname, String ID, int age, String gender, String condition, patientCategory Category)
    {
        this.patientName=name;
        this.patientSurname=surname;
        this.patientID=ID;
        this.age=age;
        this.gender=gender;
        this.medicalCondition=condition;
        this.Category=Category;
    }
    // Getters and Setters
    public String getPatientID() 
    { 
        return patientID; 
    }
    public void setPatientID(String patientID) 
    { 
        this.patientID = patientID; 
    }

    public String getFirstName() 
    { 
        return patientName; 
    }
    public void setFirstName(String firstName) 
    { 
        this.patientName = firstName; 
    }

    public String getLastName() 
    { 
        return patientSurname; 
    }
    public void setLastName(String lastName) 
    { 
        this.patientSurname = lastName; 
    }

    public int getAge() 
    { 
        return age; 
    }
    public void setAge(int age) 
    { 
        this.age = age; 
    }

    public String getGender() 
    { 
        return gender; 
    }
    public void setGender(String gender) 
    { 
        this.gender = gender; 
    }

    public String getMedicalCondition() 
    { 
        return medicalCondition; 
    }
    public void setMedicalCondition(String medicalCondition) 
    { 
        this.medicalCondition = medicalCondition; 
    }
    
}
