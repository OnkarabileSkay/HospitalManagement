package medicare;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MediCare 
{
    static ArrayList<PatientManager> patientsRecord = new ArrayList<>();
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
      
        System.out.println("Welcome to MediCare Hospital");
        while (true)
        {
            System.out.println("");
            System.out.print("Enter '1' to see the MANUE or Enter Any Key To EXIT:");
            int choice= scanner.nextInt();
            if (choice==1)
            {
                System.out.println("");
                hospitalManu(scanner);
            }
            else
            { 
                System.out.println("");
                System.out.println("Goodbay... Have a lovely day");
                break;
            }
        }
    }
    public static void hospitalManu(Scanner scanner)
    {
        System.out.println("");
        System.out.println("1: Register a new patient");
        System.out.println("2: Search for a patient using their patient ID");
        System.out.println("3: Update an existing patient's details");
        System.out.println("4: Delete a patient's record");
        System.out.println("5: Display all registered patients");
        System.out.println("6: Display all bads");
        System.out.println("0: Exit");
        System.out.print("You Choice:");
        int manuChoice= scanner.nextInt();
        if (manuChoice==1)
        {
            register(scanner);
            System.out.println("\nThe Patient is Succesfully added");
        }
        else if (manuChoice==2)
        {
            searchPatientID();
        }
        else if (manuChoice==3)
        {
            updateRecord();
        }
        else if (manuChoice==6)
        {
            badslayout();
        }
    }
    public static void register(Scanner scanner)
    {
        System.out.println("");
        String category=" ";
        scanner.nextLine();
        System.out.println();
        System.out.println("Registering a new Patient");
        System.out.print("Enter the Name of the patient: ");
        String name=scanner.nextLine();
        System.out.print("Enter the Patients's Surname: ");
        String surname=scanner.nextLine();
        System.out.print("Enter the Patients's ID number: ");
        String ID=scanner.nextLine();
        String gender="";
        while (true)
        {
            System.out.println("Choose the patient's gender from the below list: ");
            System.out.println("1: Male");
            System.out.println("2: Femal");
            System.out.print("You Choice:");
            int choice=scanner.nextInt();
            if (choice==1)
            {
                gender="Male";
                System.out.println("The patient "+name+" is a "+gender+" person");
                break;
            }
            else if (choice==2)
            {
                gender="Female";
                System.out.println("The patient "+name+" is a "+gender+" person");
                break;
            }
            else 
            {
                System.out.println("Please choose between those 2 options");
            }
        }
        
        System.out.print("Enter the patient's age: ");
        int age=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the patients's medical problem:");
        String condition=scanner.nextLine();
        while (true)
        {
            System.out.println("Choose the following options for the patients catagory: ");
            System.out.println("1: Inpatient");
            System.out.println("2: Outpatient");
            System.out.println("3: Emergancy");
            System.out.print("Enter the option: ");
            int catagoryOption=scanner.nextInt();
            if (catagoryOption==1)
            {
                category="Inpatient";
                System.out.println("The patient "+name+"'s catagory is "+category);
                break;
            }
            else if (catagoryOption==2)
            {
                category="Outpatient";
                System.out.println("The patient "+name+"'s catagory is "+category);
                break;
            }
            else if (catagoryOption==3)
            {
                category="Emergancy";
                System.out.println("The patient "+name+"'s catagory is "+category);
                System.out.println("");
                //System.out.println("The Patient is Succesfully added");
                break;
            }
            else
            {
                System.out.println("Please Enter only one option between those 3 only");
            }
        }
        
        PatientManager patient= new PatientManager(name,surname,ID,age,gender,condition,category);
        patientsRecord.add(patient);
    }
    public static void searchPatientID()
    {
        Scanner input =new Scanner(System.in);
        int stopLoop=9;
        while(stopLoop !=0)
        {
        System.out.println("");
        System.out.println("");
        System.out.println("Searcing a patient");
        System.out.print("Enter the patient's ID to search for:");
        String IDsearch=input.nextLine();
        boolean found=false;
        for (int index=0 ;index<patientsRecord.size() ;index++)
        {
            PatientManager patient = patientsRecord.get(index);
            if (patient.patientID.equals(IDsearch))
            {
                System.out.println("\nPatient Found");
                System.out.println("Name: " + patient.patientName); 
                System.out.println("Surname: "+ patient.patientSurname);
                System.out.println("ID: " + patient.patientID);
                System.out.println("Age: " + patient.age);
                System.out.println("Gender: " + patient.gender);
                System.out.println("Condition: " + patient.medicalCondition);
                System.out.println("Category: " + patient.patientCategory);
                found=true;
                break;
            }
        }
        if(found==false)
            {
                System.out.println("\nPatient with an id ' "+IDsearch+" ' was not found on our hospital");
            }
        System.out.println("\nDo you want to search again?(Yes Or No)");
        String searchChoice=input.nextLine();
        if (searchChoice.equalsIgnoreCase("yes"))
        {
            stopLoop=2;
        }
        else if(searchChoice.equalsIgnoreCase("no"))
        {
            stopLoop=0;
        }
        //return;
        }
    }
    public static void badslayout()
    {
        String[][] badLyout = new String[4][5];
        int numbers = 0;
        for (int a = 0; a < badLyout.length; a++)
        {
            for (int i = 0; i < badLyout[a].length; i++)
            {
                numbers = numbers + 1;
                if (numbers < 10) 
                {
                    badLyout[a][i] = "B0" + numbers; 
                } 
                else 
                {
                    badLyout[a][i] = "B" + numbers;
                }
            }
        }
        // Display in grid format
        for (int row = 0; row < badLyout.length; row++)
        {
            for (int col = 0; col < badLyout[row].length; col++)
            {
                System.out.print(badLyout[row][col] + " ");
            }
            System.out.println(); 
        }
        
    }
    public static void updateRecord() 
    {
        boolean foundID = false;
        boolean loopCounter=false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Enter the patient's ID to Update");
        String update = scanner.nextLine();
        for (int i = 0; i < patientsRecord.size(); i++) 
        {
            PatientManager patient = patientsRecord.get(i);
            if (patient.patientID.equals(update)) 
            {
                foundID = true;
                while(loopCounter==false)
                {
                    System.out.println("What would you like to update?");
                    System.out.println("1: Patient's Name");
                    System.out.println("2: Patient's Surname");
                    System.out.println("3: Patient ID");
                    System.out.println("4: Patient's Age");
                    System.out.println("5: Patient's Gender");
                    System.out.println("6: Patient's Condition");
                    System.out.println("7: Patient's Category");
                    System.out.println("");
                    System.out.print("Enter the number of your choice: ");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();
                    // CLEAR THE BUFFER (Consumes the leftover \n from nextInt)
                    if (updateChoice == 1) 
                    {
                        System.out.println("Old name: " + patient.patientName);
                        System.out.print("New name: ");
                        patient.patientName = scanner.nextLine(); // Now correctly waits for user input
                    } 
                    else if (updateChoice == 2) 
                    {
                        System.out.println("Old surname: " + patient.patientSurname);
                        System.out.print("New surname: ");
                        patient.patientSurname = scanner.nextLine();
                    } 
                    else if (updateChoice == 3) 
                    {
                        System.out.println("Old ID: " + patient.patientID);
                        System.out.print("New ID: ");
                        patient.patientID = scanner.nextLine();
                    } 
                    else if (updateChoice == 4) 
                    {
                        System.out.println("Old age: " + patient.age + " years");
                        System.out.print("New age: ");
                        patient.age = scanner.nextInt();
                        scanner.nextLine(); // CLEAR BUFFER
                    } 
                    else if (updateChoice == 5) 
                    {
                        System.out.println("Old gender: " + patient.gender);
                    while (true) 
                    {
                        System.out.println("Choose the patient's gender from the below list: ");
                        System.out.println("1: Male");
                        System.out.println("2: Female");
                        System.out.print("New Gender: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // CLEAR BUFFER
                    if (choice == 1) 
                    {   
                        patient.gender = "Male";
                        break;
                    } 
                    else if (choice == 2) 
                    {
                        patient.gender = "Female";
                        break;
                    } 
                    else 
                    {
                        System.out.println("Please choose between those 2 options");
                    }
                    }
                    }
                }
                
                System.out.println("\nDo you want to update again?(Yes Or No)");
                String updatechoice=scanner.nextLine();
                if (updatechoice.equalsIgnoreCase("yes"))
                    {
                        loopCounter=false;
                    }
                else if(updatechoice.equalsIgnoreCase("no"))
                {
                    loopCounter=true;
                }
                break; // Stop loop after patient is updated
            }
        }
        if (!foundID) 
        {
            System.out.println("The Patient with ID " + update + " was not found");
        }

    }
}
