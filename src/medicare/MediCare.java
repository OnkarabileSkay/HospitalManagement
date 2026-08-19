package medicare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

enum patientCategory
{
    Inpaitent,
    OutPaitent,
    Emegency
}

public class MediCare 
{
    static ArrayList<PatientManager> patientsRecord = new ArrayList<>();
    
    // Track 20 beds (false = available, true = occupied [In Use])
    static boolean[] bedStatus = new boolean[20];

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to MediCare Hospital");
        while (true)
        {
            System.out.println("");
            System.out.print("Enter '1' to see the MENU or Enter Any Key To EXIT:");
            int choice = scanner.nextInt();
            if (choice == 1)
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
        int manuChoice = scanner.nextInt();
        if (manuChoice == 1)
        {
            register(scanner);
            System.out.println("\nThe Patient is Succesfully added");
        }
        else if (manuChoice == 2)
        {
            searchPatientID();
        }
        else if (manuChoice == 3)
        {
            updateRecord();
        }
        else if (manuChoice == 4)
        {
            deleteRecord();
        }
        else if (manuChoice == 5)
        {
            displayAllPatients();
        }
        else if (manuChoice == 6)
        {
            badslayout();
        }
    }

    public static void register(Scanner scanner)
    {
        System.out.println("");
        String category = " ";
        scanner.nextLine();
        System.out.println("");
        System.out.println("Registering a new Patient");
        System.out.print("Enter the Name of the patient: ");
        String name = scanner.nextLine();
        System.out.print("Enter the Patients's Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the Patients's ID number: ");
        String ID = scanner.nextLine();
        String gender = "";
        while (true)
        {
            System.out.println("Choose the patient's gender from the below list: ");
            System.out.println("1: Male");
            System.out.println("2: Femal");
            System.out.print("You Choice:");
            int choice = scanner.nextInt();
            if (choice == 1)
            {
                gender = "Male";
                System.out.println("The patient " + name + " is a " + gender + " person");
                break;
            }
            else if (choice == 2)
            {
                gender = "Female";
                System.out.println("The patient " + name + " is a " + gender + " person");
                break;
            }
            else 
            {
                System.out.println("Please choose between those 2 options");
            }
        }
        
        System.out.print("Enter the patient's age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the patients's medical problem:");
        String condition = scanner.nextLine();
        patientCategory newPatient = null;
        while (true)
        {
            System.out.println("Choose the following options for the patients catagory: ");
            System.out.println("1: Inpatient");
            System.out.println("2: Outpatient");
            System.out.println("3: Emergancy");
            System.out.print("Enter the option: ");
            int catagoryOption = scanner.nextInt();
            if (catagoryOption == 1)
            {
                newPatient = patientCategory.Inpaitent;
                System.out.println("The patient " + name + "'s catagory is " + newPatient);
                
                // Assign a bed if Inpatient
                allocateBed(scanner);
                break;
            }
            else if (catagoryOption == 2)
            {
                newPatient = patientCategory.OutPaitent;
                System.out.println("The patient " + name + "'s catagory is " + newPatient);
                break;
            }
            else if (catagoryOption == 3)
            {
                newPatient = patientCategory.Emegency;
                System.out.println("The patient " + name + "'s catagory is " + newPatient);
                break;
            }
            else
            {
                System.out.println("Please Enter only one option between those 3 only");
            }
        }
        
        PatientManager patient = new PatientManager(name, surname, ID, age, gender, condition, newPatient);
        patientsRecord.add(patient);
    }

    // Helper method to allocate an available bed to an Inpatient
    private static void allocateBed(Scanner scanner) {
        boolean availableFound = false;
        for (boolean status : bedStatus) {
            if (!status) {
                availableFound = true;
                break;
            }
        }

        if (!availableFound) {
            System.out.println("No beds are available for allocation.");
            return;
        }

        while (true) {
            System.out.print("Enter Bed Number to allocate (1 to 20): ");
            int bNum = scanner.nextInt();
            if (bNum >= 1 && bNum <= 20) {
                if (!bedStatus[bNum - 1]) {
                    bedStatus[bNum - 1] = true;
                    System.out.println("Bed B" + String.format("%02d", bNum) + " allocated successfully.");
                    break;
                } else {
                    System.out.println("Bed B" + String.format("%02d", bNum) + " is already occupied. Choose another.");
                }
            } else {
                System.out.println("Invalid bed number. Choose between 1 and 20.");
            }
        }
    }

    public static void searchPatientID()
    {
        Scanner input = new Scanner(System.in);
        int stopLoop = 1;
        while(stopLoop != 0)
        {
            System.out.println("");
            System.out.println("");
            System.out.println("Searcing a patient");
            System.out.print("Enter the patient's ID to search for:");
            String IDsearch = input.nextLine();
            boolean found = false;
            for (int index = 0; index < patientsRecord.size(); index++)
            {
                PatientManager patient = patientsRecord.get(index);
                if (patient.patientID.equals(IDsearch))
                {
                    System.out.println("\nPatient Found");
                    System.out.println("Name: " + patient.patientName); 
                    System.out.println("Surname: " + patient.patientSurname);
                    System.out.println("ID: " + patient.patientID);
                    System.out.println("Age: " + patient.age);
                    System.out.println("Gender: " + patient.gender);
                    System.out.println("Condition: " + patient.medicalCondition);
                    System.out.println("Category: " + patient.Category);
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                System.out.println("\nPatient with an id ' " + IDsearch + " ' was not found on our hospital");
            }
            System.out.println("\nDo you want to search again?(Yes Or No)");
            String searchChoice = input.nextLine();
            if (searchChoice.equalsIgnoreCase("yes"))
            {
                stopLoop = 2;
            }
            else if (searchChoice.equalsIgnoreCase("no"))
            {
                stopLoop = 0;
            }
        }
    }

    public static void badslayout()
    {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("\nHospital Ward Layout");
        for (int row = 0; row < badLyout.length; row++)
        {
            for (int col = 0; col < badLyout[row].length; col++)
            {
                System.out.print(badLyout[row][col] + " ");
            }
            System.out.println(); 
        }
        System.out.println("\nOptions:");
        System.out.println("1: Display Available Beds");
        System.out.println("2: Display Occupied Beds");
        System.out.println("0: Return To Main Manu");
        System.out.print("Enter Choice: ");
        int option = scanner.nextInt();

        if (option == 1)
        {
            int totalAvailable = 0;
            int bedIndex = 0;
            System.out.println("\n Available Beds");
            for (int r = 0; r < 4; r++)
            {
                for (int c = 0; c < 5; c++)
                {
                    if (!bedStatus[bedIndex]) 
                    { // Bed is available
                        System.out.print(badLyout[r][c] + " ");
                        totalAvailable++;
                    } 
                    else 
                    {
                        System.out.print("[In Use]"); // Hide occupied bed number
                    }
                    bedIndex++;
                }
                System.out.println();
            }
            System.out.println("\nTotal beds available: " + totalAvailable);
        }
        else if (option == 2)
        {
            int totalOccupied = 0;
            int bedIndex = 0;
            System.out.println("\n Occupied Beds");
            for (int r = 0; r < 4; r++)
            {
                for (int c = 0; c < 5; c++)
                {
                    if (bedStatus[bedIndex]) 
                    { // Bed is occupied
                        System.out.print("[In Use] ");
                        totalOccupied++;
                    } 
                    else 
                    {
                        System.out.print(" [-----] "); // Hide available bed number
                    }
                    bedIndex++;
                }
                System.out.println();
            }
            System.out.println("\nTotal beds in use: " + totalOccupied);
        }
        else if(option==0)
        {
            System.out.println("");
        }
        else
        {
            System.out.println("Invalid Option Selected.");
        }
    }

    public static void updateRecord() 
    {
        boolean foundID = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Enter the patient's ID to Update:");
        String update = scanner.nextLine();
        
        for (int i = 0; i < patientsRecord.size(); i++) 
        {
            PatientManager patient = patientsRecord.get(i);
            if (patient.patientID.equals(update)) 
            {
                foundID = true;
                System.out.println("\nPatient Found");
                System.out.println("\nCurrent Details");
                System.out.println("Name: " + patient.patientName); 
                System.out.println("Surname: " + patient.patientSurname);
                System.out.println("ID: " + patient.patientID);
                System.out.println("Age: " + patient.age);
                System.out.println("Gender: " + patient.gender);
                System.out.println("Condition: " + patient.medicalCondition);
                System.out.println("Category: " + patient.Category);
                
                boolean loopCounter = false;
                while (!loopCounter)
                {
                    System.out.println("\nWhat would you like to update?");
                    System.out.println("1: Patient's Name");
                    System.out.println("2: Patient's Surname");
                    System.out.println("3: Patient ID");
                    System.out.println("4: Patient's Age");
                    System.out.println("5: Patient's Gender");
                    System.out.println("6: Patient's Condition");
                    System.out.println("7: Patient's Category");
                    System.out.println("0: Return to Main Menu");
                    System.out.print("Enter the number of your choice: ");
                    
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // Clear invalid token
                        continue;
                    }
                    
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (updateChoice == 1) 
                    {
                        System.out.println("");
                        System.out.println("Old name: " + patient.patientName);
                        System.out.print("New name: ");
                        patient.patientName = scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
                    } 
                    else if (updateChoice == 2) 
                    {
                        System.out.println("");
                        System.out.println("Old surname: " + patient.patientSurname);
                        System.out.print("New surname: ");
                        patient.patientSurname = scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
                    } 
                    else if (updateChoice == 3) 
                    {
                        System.out.println("");
                        System.out.println("Old ID: " + patient.patientID);
                        System.out.print("New ID: ");
                        patient.patientID = scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
                    } 
                    else if (updateChoice == 4) 
                    {
                        System.out.println("");
                        System.out.println("Old age: " + patient.age + " years");
                        System.out.print("New age: ");
                        patient.age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
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
                            scanner.nextLine();
                            System.out.println("");
                            if (choice == 1) 
                            {   
                                patient.gender = "Male";
                                System.out.println("Update successfully captured.");
                                break;
                            } 
                            else if (choice == 2) 
                            {
                                patient.gender = "Female";
                                System.out.println("Update successfully captured.");
                                break;
                            } 
                            else 
                            {
                                System.out.println("Please choose between those 2 options");
                            }
                        }
                    }
                    else if (updateChoice == 6)
                    {
                        System.out.println("");
                        System.out.println("Old Condition: " + patient.medicalCondition);
                        System.out.print("New Condition: ");
                        patient.medicalCondition = scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
                    }
                    else if (updateChoice == 7)
                    {
                        while (true)
                        {
                            System.out.println("Choose the following options for the patient's category: ");
                            System.out.println("1: Inpatient");
                            System.out.println("2: Outpatient");
                            System.out.println("3: Emergency");
                            System.out.print("Enter the option: ");
                            int catagoryOption = scanner.nextInt();
                            scanner.nextLine();
                            if (catagoryOption == 1)
                            {
                                patient.Category = patientCategory.Inpaitent;
                                System.out.println("\nUpdate successfully captured.");
                                break;
                            }
                            else if (catagoryOption == 2)
                            {
                                patient.Category = patientCategory.OutPaitent;
                                System.out.println("\nUpdate successfully captured.");
                                break;
                            }
                            else if (catagoryOption == 3)
                            {
                                patient.Category = patientCategory.Emegency;
                                System.out.println("\nUpdate successfully captured.");
                                break;
                            }
                            else
                            {
                                System.out.println("Please enter an option between 1 and 3.");
                            }
                        }    
                    }
                    else if (updateChoice == 0)
                    {
                        break;
                    }

                    System.out.println("\nDo you want to update again? (Yes Or No)");
                    System.out.print("Your Choice: ");
                    String updatechoice = scanner.nextLine();
                    if (updatechoice.equalsIgnoreCase("no"))
                    {
                        loopCounter = true;
                        System.out.println("\nNew Details:");
                        System.out.println("Name: " + patient.patientName); 
                        System.out.println("Surname: " + patient.patientSurname);
                        System.out.println("ID: " + patient.patientID);
                        System.out.println("Age: " + patient.age);
                        System.out.println("Gender: " + patient.gender);
                        System.out.println("Condition: " + patient.medicalCondition);
                        System.out.println("Category: " + patient.Category);
                        System.out.println("\nReturning To Main Menu");
                    }
                }
                break;
            }
        }
        if (!foundID) 
        {
            System.out.println("\nThe Patient with ID " + update + " was not found.");
        }
    }

    public static void deleteRecord()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("Enter the patient's ID to delete: ");
        String deleteID = scanner.nextLine();
        boolean removed = false;

        for (int i = 0; i < patientsRecord.size(); i++)
        {
            if (patientsRecord.get(i).patientID.equals(deleteID))
            {
                PatientManager patient = patientsRecord.get(i);
                System.out.println("Name: " + patient.patientName);
                System.out.println("Surname: " + patient.patientSurname);
                System.out.println("ID: " + patient.patientID);
                System.out.println("Age: " + patient.age);
                System.out.println("Gender: " + patient.gender);
                System.out.println("Condition: " + patient.medicalCondition);
                System.out.println("Category: " + patient.Category);
                patientsRecord.remove(i);
                removed = true;
                System.out.println("\nPatient record with ID '" + deleteID + "' successfully deleted.");
                break;
            }
        }

        if (!removed)
        {
            System.out.println("\nPatient with ID '" + deleteID + "' was not found.");
        }
    }

    public static void displayAllPatients()
    {
        System.out.println("");
        if (patientsRecord.isEmpty())
        {
            System.out.println("No patients currently registered in our hopital.");
        }
        else
        {
            System.out.println(" List of All Registered Patients ");
            for (int i = 0; i < patientsRecord.size(); i++)
            {
                PatientManager patient = patientsRecord.get(i);
                System.out.println("\nPatient " + (i + 1) + " : ");
                System.out.println("Name: " + patient.patientName);
                System.out.println("Surname: " + patient.patientSurname);
                System.out.println("ID: " + patient.patientID);
                System.out.println("Age: " + patient.age);
                System.out.println("Gender: " + patient.gender);
                System.out.println("Condition: " + patient.medicalCondition);
                System.out.println("Category: " + patient.Category);
            }
        }
    }
}