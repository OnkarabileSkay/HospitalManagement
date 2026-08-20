package medicare;

import java.util.ArrayList;
import java.util.Scanner;

enum patientCategory
{
    Inpatient,
    Outpatient,
    Emergency
}

public class MediCare 
{
    static ArrayList<PatientManager> patientsRecord = new ArrayList<>();
    static boolean[] bedStatus = new boolean[20];

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to MediCare Hospital");
        while (true)
        {
            System.out.println();
            System.out.print("Enter '1' to see the MENU or Enter Any Key To EXIT:");
            if (!scanner.hasNextInt())
            {
                System.out.println("\nGoodbye... Have a lovely day");
                break;
            }
            int choice = scanner.nextInt();
            if (choice == 1)
            {
                System.out.println("");
                hospitalManu(scanner);
            }
            else
            { 
                System.out.println("");
                System.out.println("Goodbye... Have a lovely day");
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
        System.out.println("6: Display all beds");
        System.out.println("7: Display full report");
        System.out.println("0: Exit");
        System.out.print("Your Choice: ");
        int manuChoice = scanner.nextInt();
        if (manuChoice == 1)
        {
            register(scanner);
            System.out.println("\nThe Patient is Successfully added");
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
        else if (manuChoice == 7)
        {
            displayReport();
        }
        else if (manuChoice == 0)
        {
            System.out.println("Returning to Home...");
        }
    }

    public static void register(Scanner scanner)
    {
        System.out.println("");
        scanner.nextLine();
        System.out.println("Registering a new Patient");
        System.out.print("Enter the Name of the patient: ");
        String name = scanner.nextLine();
        System.out.print("Enter the Patient's Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the Patient's ID number: ");
        String ID = scanner.nextLine();
        String gender = "";
        while (true)
        {
            System.out.println("Choose the patient's gender from the below list: ");
            System.out.println("1: Male");
            System.out.println("2: Female");
            System.out.print("Your Choice: ");
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
        System.out.println("Enter the patient's medical problem:");
        String condition = scanner.nextLine();
        patientCategory newPatientCategory = null;
        
        while (true)
        {
            System.out.println("Choose the following options for the patient category: ");
            System.out.println("1: Inpatient");
            System.out.println("2: Outpatient");
            System.out.println("3: Emergency");
            System.out.print("Enter the option: ");
            int categoryOption = scanner.nextInt();
            scanner.nextLine();
            
            if (categoryOption == 1)
            {
                newPatientCategory = patientCategory.Inpatient;
                System.out.println("The patient " + name + "'s category is " + newPatientCategory);
                
                System.out.print("Enter Ward Number: ");
                String ward = scanner.nextLine();
                
                int allocatedBed = allocateBed(scanner);
                if (allocatedBed != -1) {
                    Inpatient inpatient = new Inpatient(name, surname, ID, age, gender, condition, newPatientCategory, ward, allocatedBed);
                    patientsRecord.add(inpatient);
                } else {
                    PatientManager patient = new PatientManager(name, surname, ID, age, gender, condition, newPatientCategory);
                    patientsRecord.add(patient);
                }
                break;
            }
            else if (categoryOption == 2)
            {
                newPatientCategory = patientCategory.Outpatient;
                System.out.println("The patient " + name + "'s category is " + newPatientCategory);
                PatientManager patient = new PatientManager(name, surname, ID, age, gender, condition, newPatientCategory);
                patientsRecord.add(patient);
                break;
            }
            else if (categoryOption == 3)
            {
                newPatientCategory = patientCategory.Emergency;
                System.out.println("The patient " + name + "'s category is " + newPatientCategory);
                PatientManager patient = new PatientManager(name, surname, ID, age, gender, condition, newPatientCategory);
                patientsRecord.add(patient);
                break;
            }
            else
            {
                System.out.println("Please enter an option between 1 and 3.");
            }
        }
    }

    private static int allocateBed(Scanner scanner) {
        boolean availableFound = false;
        for (boolean status : bedStatus) {
            if (!status) {
                availableFound = true;
                break;
            }
        }

        if (!availableFound) {
            System.out.println("No beds are available for allocation.");
            return -1;
        }

        while (true) {
            System.out.print("Enter Bed Number to allocate (1 to 20): ");
            int bedNum = scanner.nextInt();
            if (bedNum >= 1 && bedNum <= 20) {
                if (!bedStatus[bedNum - 1]) {
                    bedStatus[bedNum - 1] = true;
                    System.out.println("Bed B" + String.format("%02d", bedNum) + " allocated successfully.");
                    return bedNum;
                } else {
                    System.out.println("Bed B" + String.format("%02d", bedNum) + " is already occupied. Choose another.");
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
            System.out.println();
            System.out.println("Searching a patient");
            System.out.print("Enter the patient's ID to search for: ");
            String IDsearch = input.nextLine();
            boolean found = false;
            for (int index = 0; index < patientsRecord.size(); index++)
            {
                PatientManager patient = patientsRecord.get(index);
                if (patient.patientID.equals(IDsearch))
                {
                    System.out.println("\nPatient Found\n");
                    patient.displayDetails();
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                System.out.println("\nPatient with ID '" + IDsearch + "' was not found in our hospital.");
            }
            System.out.println("\nDo you want to search again? (Yes Or No)");
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
        System.out.println("0: Return To Main Menu");
        System.out.print("Enter Choice: ");
        int option = scanner.nextInt();

        if (option == 1)
        {
            int totalAvailable = 0;
            int bedIndex = 0;
            System.out.println("\nAvailable Beds");
            for (int r = 0; r < 4; r++)
            {
                for (int c = 0; c < 5; c++)
                {
                    if (!bedStatus[bedIndex]) 
                    {
                        System.out.print(badLyout[r][c] + " ");
                        totalAvailable++;
                    } 
                    else 
                    {
                        System.out.print("[Use] ");
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
            System.out.println("\nOccupied Beds");
            for (int r = 0; r < 4; r++)
            {
                for (int c = 0; c < 5; c++)
                {
                    if (bedStatus[bedIndex]) 
                    {
                        System.out.print("[Use] ");
                        totalOccupied++;
                    } 
                    else 
                    {
                        System.out.print("[---] ");
                    }
                    bedIndex++;
                }
                System.out.println();
            }
            System.out.println("\nTotal beds in use: " + totalOccupied);
        }
        else if(option == 0)
        {
            System.out.println();
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
        System.out.println();
        System.out.print("Enter the patient's ID to Update: ");
        String update = scanner.nextLine();
        
        for (int i = 0; i < patientsRecord.size(); i++) 
        {
            PatientManager patient = patientsRecord.get(i);
            if (patient.patientID.equals(update)) 
            {
                foundID = true;
                System.out.println("\nPatient Found");
                System.out.println("\nCurrent Details");
                patient.displayDetails();
                
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
                        scanner.nextLine();
                        continue;
                    }
                    
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (updateChoice == 1) 
                    {
                        System.out.println("\nOld name: " + patient.patientName);
                        System.out.print("New name: ");
                        patient.patientName = scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
                    } 
                    else if (updateChoice == 2) 
                    {
                        System.out.println("\nOld surname: " + patient.patientSurname);
                        System.out.print("New surname: ");
                        patient.patientSurname = scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
                    } 
                    else if (updateChoice == 3) 
                    {
                        System.out.println("\nOld ID: " + patient.patientID);
                        System.out.print("New ID: ");
                        patient.patientID = scanner.nextLine();
                        System.out.println("\nUpdate successfully captured.");
                    } 
                    else if (updateChoice == 4) 
                    {
                        System.out.println("\nOld age: " + patient.age + " years");
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
                        System.out.println("\nOld Condition: " + patient.medicalCondition);
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
                                patient.Category = patientCategory.Inpatient;
                                System.out.println("\nUpdate successfully captured.");
                                break;
                            }
                            else if (catagoryOption == 2)
                            {
                                patient.Category = patientCategory.Outpatient;
                                System.out.println("\nUpdate successfully captured.");
                                break;
                            }
                            else if (catagoryOption == 3)
                            {
                                patient.Category = patientCategory.Emergency;
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
                        patient.displayDetails();
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
        System.out.println();
        System.out.print("Enter the patient's ID to delete: ");
        String deleteID = scanner.nextLine();
        boolean delete = false;

        for (int i = 0; i < patientsRecord.size(); i++)
        {
            if (patientsRecord.get(i).patientID.equals(deleteID))
            {
                System.out.println("\nPatient with ID '" + deleteID + "' was found!\n");
                PatientManager patient = patientsRecord.get(i);
                patient.displayDetails();
                
                patientsRecord.remove(i);
                delete = true;
                System.out.println("\nPatient record with ID '" + deleteID + "' successfully deleted.");
                break;
            }
        }

        if (!delete)
        {
            System.out.println("\nPatient with ID '" + deleteID + "' was not found.");
        }
    }

    public static void displayAllPatients()
    {
        System.out.println();
        if (patientsRecord.isEmpty())
        {
            System.out.println("No patients currently registered in our hospital.");
        }
        else
        {
            System.out.println(" List of All Registered Patients ");
            for (int i = 0; i < patientsRecord.size(); i++)
            {
                PatientManager patient = patientsRecord.get(i);
                System.out.println("\nPatient " + (i + 1) + " : ");
                patient.displayDetails();
            }
        }
    }

    public static void displayReport()
    {
        System.out.println("\nMEDICARE HOSPITAL FULL REPORT");
        System.out.println("\nPATIENT REPORT");
        if (patientsRecord.isEmpty()) 
        {
            System.out.println("No patients currently registered.");
        } 
        else 
        {
            for (int i = 0; i < patientsRecord.size() - 1; i++) 
            {
                for (int j = 0; j < patientsRecord.size() - i - 1; j++) 
                {
                    String surname1 = patientsRecord.get(j).patientSurname;
                    String surname2 = patientsRecord.get(j + 1).patientSurname;
                    
                    if (surname1.compareToIgnoreCase(surname2) > 0) 
                    {
                        PatientManager temp = patientsRecord.get(j);
                        patientsRecord.set(j, patientsRecord.get(j + 1));
                        patientsRecord.set(j + 1, temp);
                    }
                }
            }
            
            for (int i = 0; i < patientsRecord.size(); i++) 
            {
                PatientManager patient = patientsRecord.get(i);
                System.out.println("Patient " + (i + 1) + ": " + patient.patientSurname + ", " + patient.patientName 
                        + " | ID: " + patient.patientID + " | Category: " + patient.Category);
            }
        }

        int inpatients = 0;
        int outpatients = 0;
        int emergency = 0;

        for (int i = 0; i < patientsRecord.size(); i++) 
        {
            PatientManager patient = patientsRecord.get(i);
            if (patient.Category == patientCategory.Inpatient) 
            {
                inpatients++;
            } 
            else if (patient.Category == patientCategory.Outpatient) 
            {
                outpatients++;
            } 
            else if (patient.Category == patientCategory.Emergency) 
            {
                emergency++;
            }
        }

        System.out.println("\nCategory Total:");
        System.out.println("1: Total Registered Patients : " + patientsRecord.size());
        System.out.println("2: Inpatients: " + inpatients);
        System.out.println("3: Outpatients: " + outpatients);
        System.out.println("4: Emergency: " + emergency);
        int bedsOccupied = 0;
        for (int i = 0; i < bedStatus.length; i++) 
        {
            if (bedStatus[i]) 
            {
                bedsOccupied++;
            }
        }
        int totalBeds = bedStatus.length;
        int bedsAvailable = totalBeds - bedsOccupied;
        double occupancyPercentage = ((double) bedsOccupied / totalBeds) * 100;

        System.out.println("\n WARD BED REPORT");
        System.out.println("1: Total Beds : " + totalBeds);
        System.out.println("2: Beds Occupied : " + bedsOccupied);
        System.out.println("3: Beds Available : " + bedsAvailable);
        System.out.printf("4: Occupancy Percentage: %.2f%%\n", occupancyPercentage);
    }
}