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
    static ArrayList<Patient> patientsRecord = new ArrayList<>();
    static boolean[] bedStatus = new boolean[20];
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        //Welcome message
        System.out.println("Welcome to MediCare Hospital");
        //A LOOP THAT LOOPS THE MANU UNTIL THE USER ENTER ANY NUMBER BUT 1
        while (true)
        {
            System.out.println();
            System.out.print("Enter '1' to see the MENU or Enter Any Key To EXIT:");
            int choice = scanner.nextInt();
            //IF THE USER ENTER 1, IT WILL RUN THE MANU METHOD
            if (choice == 1)
            {
                System.out.println();
                hospitalManu(scanner);
            }
            else
            {
                //ELSE IF HE ENTERS ANY KEY IT WILL DISPLAY GOODBYE TEXT        
                System.out.println();
                System.out.println("Goodbye... Have a lovely day");
                break;
            }
        }
    }
    //MANU METHOD
    public static void hospitalManu(Scanner scanner)
    {
        while(true)
        {
            System.out.println();
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
                //RUNS THE REGISTER METHOD
                register(scanner);
            }
            else if (manuChoice == 2)
            {
                //RUNS THE METHOD THAT SEARCH FOR A PATIENT 
                searchPatientID();
            }
            else if (manuChoice == 3)
            {
                //RUNS THE METHOD THAT ALLOWS THE USER TO UPDATE 
                updateRecord();
            }
            else if (manuChoice == 4)
            {
                //RUNS A METHOD THAT DELETS A PATIENTS'S INFORMATION
                deleteRecord();
            }
                else if (manuChoice == 5)
            {
                //RUNS A METHOD THAT DISPLAYS ALL THE PATIENTS IN THE SYSTEM 
                displayAllPatients();
            }
            else if (manuChoice == 6)
            {
                ////RUNS A METHOD THAT DISPLAYS HOW THE WARD IS STRUCTURED
                badslayout();
            }
            else if (manuChoice == 7)
            {
                //IT RUNDS A METHOD THAT DISPLAY REPORTS
                displayReport(scanner);
            }
            else if (manuChoice == 0)
            {
                //IT EXITS THE LOOP
                System.out.println("Returning to Home...");
                break;
            }
        }
    }
   // Helper method to check if a patient ID already exists in the system
    public static boolean isDuplicateID(String ID)
    {
        for (Patient patient : patientsRecord)
        {
            if (patient.patientID.equalsIgnoreCase(ID))
            {
                return true;
            }
        }
        return false;
    }
    //METHOD THAT ALLOWS THE USER TO REGISTER A PATIENT IN THE HISPITAL 
    public static void register(Scanner scanner)
    {
        boolean loopControl=false;
        while (loopControl==false)
        {
            scanner.nextLine();
            System.out.println("Registering a new Patient");
            System.out.print("Enter the Name of the patient: ");
            String name = scanner.nextLine();
            System.out.print("Enter the Patient's Surname: ");
            String surname = scanner.nextLine();   
            // LOOPS UNTILL THE PATIENT ID IS ENTERED AND NOT A DUPLICATE
            String ID = "";
            while (true)
            {
                System.out.print("Enter the Patient's ID number: ");
                ID = scanner.nextLine();
                //IF STATEMENT THAT CHEACKS IF THE ENTERD ID IS A DUPLICATE OR NOT
                if (isDuplicateID(ID))
                {
                    System.out.println("A patient with ID '" + ID + "' already exists. Please enter another ID");
                    System.out.println();
                }
                else
                {
                    break;
                }
            }
            String gender = "";
            //LOOP THAT MAKES THE USER TO CHOOSE ONE OF THE GENDERS
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
            //LOOP THAT MAKES SURE THAT USER CHOOSE ONE CATEGORY FOR THE PATIENT
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
                    //IF PATIENT IS INPATIENT, THE SYSTEM WILL ALLOW THE USER TO SELECT A BED FOR HIM OR HER
                    newPatientCategory = patientCategory.Inpatient;
                    System.out.println("The patient " + name + "'s category is " + newPatientCategory);
                    System.out.print("Enter Ward Number: ");
                    String ward = scanner.nextLine();
                    //REASIGNING THE WARD TO BE 1 SINCE ITS ONLY ONE ROOM WITH 20 BEDS
                    ward="1";
                    //IT ALOCATES A BED TO A PATIENT 
                    int allocatedBed = allocateBed(scanner);
                    if (allocatedBed != -1) 
                    {
                        //IT SAVES THE PATIENT;S DETAILS TO THE ARRAYLIST
                        Inpatient inpatient = new Inpatient(name, surname, ID, age, gender, condition, newPatientCategory, ward, allocatedBed);
                        patientsRecord.add(inpatient);
                        System.out.println("\nThe Patient is Successfully added");
                    } 
                    else    
                    {
                        //IT SAVES THE PATIENT;S DETAILS TO THE ARRAYLIST IF IT IS OUTPATIENT OR EMEGENCY
                        Patient patient = new Patient(name, surname, ID, age, gender, condition, newPatientCategory);
                        patientsRecord.add(patient);
                        System.out.println("\nThe Patient is Successfully added");
                    }
                    break;
                }
                else if (categoryOption == 2)
                {
                    newPatientCategory = patientCategory.Outpatient;
                    System.out.println("The patient " + name + "'s category is " + newPatientCategory);
                    Patient patient = new Patient(name, surname, ID, age, gender, condition, newPatientCategory);
                    patientsRecord.add(patient);
                    System.out.println("\nThe Patient is Successfully added");
                    break;
                }
                else if (categoryOption == 3)
                {
                    newPatientCategory = patientCategory.Emergency;
                    System.out.println("The patient " + name + "'s category is " + newPatientCategory);
                    Patient patient = new Patient(name, surname, ID, age, gender, condition, newPatientCategory);
                    patientsRecord.add(patient);
                    System.out.println("\nThe Patient is Successfully added");
                    break;
                }
                else
                {
                    System.out.println("Please enter an option between 1 and 3.");
                }
            }
            //IT ENABLE THE USER TO ADD ANOTHER PATIENT WITJOUD GOING BACK TO MAIN MANU
            System.out.println("Do you want to register another Patient? (yes or no)");
            String loopAnswer=scanner.nextLine();
                if (loopAnswer.equalsIgnoreCase("yes"))
                {
                    loopControl=false;
                    System.out.println();
                }
                else if(loopAnswer.equalsIgnoreCase("no"))
                {
                    loopControl=true;
                    break;
                }
        }
    }
    //METHOD THAT ALLOCATE BEDS TO INPATIENTS
    private static int allocateBed(Scanner scanner) 
    {
        boolean availableFound = false;
        for (boolean status : bedStatus) 
        {
            if (!status) 
            {
                availableFound = true;
                break;
            }
        }
        if (!availableFound) 
        {
            System.out.println();
            System.out.println("No beds are available for allocation.");
            return -1;
        }
        while (true) 
        {
            System.out.println();
            System.out.print("Enter Bed Number to allocate (1 to 20): ");
            int bedNum = scanner.nextInt();
            if (bedNum >= 1 && bedNum <= 20) 
            {
                if (!bedStatus[bedNum - 1]) 
                {
                    bedStatus[bedNum - 1] = true;
                    System.out.println("Bed B" + String.format("%02d", bedNum) + " allocated successfully.");
                    return bedNum;
                } 
                else 
                {
                    System.out.println("Bed B" + String.format("%02d", bedNum) + " is already in use. Choose another option.");
                }
            } 
            else 
            {
                System.out.println("Invalid bed number. Choose between 1 to 20.");
            }
        }
    }
    //METHOD THA SEARCHES FOR A PATIENT USING THE PATIENT ID
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
                Patient patient = patientsRecord.get(index);
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
            //ALLOW THE USER TO SEARCH AGAIN WITHOUT GOING BACK TO ,MAIN MANU
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
    //METHOD THAT DISPLAY THE BEDS LAYOUT
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
        System.out.println();
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
    //METHOD THAT UPDATES A PATIENTS'S DETAILS
    public static void updateRecord() 
    {
        boolean foundID = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the patient's ID to Update: ");
        String update = scanner.nextLine();
        //A FOR LOOP THAT SEARCHES ALL THE IDS TO FIND THE SEARCHED ONE
        for (int i = 0; i < patientsRecord.size(); i++) 
        {
            Patient patient = patientsRecord.get(i);
            if (patient.patientID.equals(update)) 
            {
                foundID = true;
                System.out.println("\nPatient Found");
                System.out.println("\nCurrent Details");
                patient.displayDetails();
                boolean loopCounter = false;
                while (!loopCounter)
                {
                    //IT ALLOWS THE USER TO CHOSE WHAT THEY WANT TO CHANGE 
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
                        while (true)
                        {
                            System.out.print("New ID: ");
                            String newID = scanner.nextLine();
                            //IF STATEMENT THAT RESTRICTS THE USER TO PROVIDE DUPLICATE IDS
                            if (!newID.equalsIgnoreCase(patient.patientID) && isDuplicateID(newID))
                            {
                                System.out.println("Error: Patient ID '" + newID + "' is already assigned to another patient!");
                            }
                            else
                            {
                                patient.patientID = newID;
                                System.out.println("\nUpdate successfully captured.");
                                break;
                            }
                        }
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
                        //LOOP THAT MAKES DURE THAT THE PATIENT IS ONE OF THE 3 CATEGORIES
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
                    //IF THE USER ENTERED 0, THE LOOP WILL STOP
                    else if (updateChoice == 0)
                    {
                        break;
                    }
                    //ALLOWS THE USER TO UPDATE AGAIN WITHOUT GOING BACK TO MAIN MANU
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
        //IF THE ENTERD ID WAS NOT FOUND, IT WILL DISPLAY THIS TEXT
        if (!foundID) 
        {
            System.out.println("\nThe Patient with ID " + update + " was not found.");
        }
    }
    //METHOD THAT ALLOWS THE USER TO DELETE A PATIENT OR DISCHARGE A PATIENT
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
                Patient patient = patientsRecord.get(i);
                patient.displayDetails();
                //USING .REMOVE TO REMOVE THE PATIENTS'S DETAIL FROM THE ARRAYLIST
                patientsRecord.remove(i);
                delete = true;
                System.out.println("\nPatient record with ID '" + deleteID + "' successfully deleted.");
                break;
            }
        }
        //IF THE ENTER IF IS NOT FOUND, IT WILL DISPLAY THIS TEXT 
        if (!delete)
        {
            System.out.println("\nPatient with ID '" + deleteID + "' was not found.");
        }
    }
    //METHOD THAT DISPLAY ALL THE PATIENTS IN THE SYSTEM 
    public static void displayAllPatients()
    {
        System.out.println();
        //IF THE SYSTEM HAS NO PATIENTS, 
        if (patientsRecord.isEmpty())
        {
            //IT WILL DISPLAY THIS TEXT
            System.out.println("No patients currently registered in our hospital.");
        }
        else
        {
            //DISPLAYS ALL THE PATIENTS IN A FOR LOOP
            System.out.println(" List of All Registered Patients ");
            for (int i = 0; i < patientsRecord.size(); i++)
            {
                Patient patient = patientsRecord.get(i);
                System.out.println("\nPatient " + (i + 1) + " : ");
                //DISPLAYS ALL THE PATIENTS'S DETAILS
                patient.displayDetails();
            }
        }
    }
    //METHOD THAT GENERATES AND DISPLAY A FULL REPORT OF THE HOSPITAL
    public static void displayReport(Scanner scanner)
    {
        System.out.println("\nMEDICARE HOSPITAL FULL REPORT");
        System.out.println("\nPATIENT REPORT");
        if (patientsRecord.isEmpty()) 
        {
            System.out.println("No patients currently registered.");
        } 
        else 
        {
            System.out.println("How would you like to sort the patient report?");
            System.out.println("1: Sort by Surname");
            System.out.println("2: Sort by Patient ID");
            System.out.print("Enter choice: ");
            int sortChoice = scanner.nextInt();
            scanner.nextLine();
            //USING BURBLE SORTING 
            for (int i = 0; i < patientsRecord.size() - 1; i++) 
            {
                for (int j = 0; j < patientsRecord.size() - i - 1; j++) 
                {
                    boolean swap = false;
                    if (sortChoice == 2) 
                    {
                        String id1 = patientsRecord.get(j).patientID;
                        String id2 = patientsRecord.get(j + 1).patientID;
                        if (id1.compareToIgnoreCase(id2) > 0) 
                        {
                            swap = true;
                        }
                    } 
                    else 
                    {
                        String surname1 = patientsRecord.get(j).patientSurname;
                        String surname2 = patientsRecord.get(j + 1).patientSurname;
                        if (surname1.compareToIgnoreCase(surname2) > 0) 
                        {
                            swap = true;
                        }
                    }
                    if (swap) 
                    {
                        Patient temp = patientsRecord.get(j);
                        patientsRecord.set(j, patientsRecord.get(j + 1));
                        patientsRecord.set(j + 1, temp);
                    }
                }
            }
            for (int i = 0; i < patientsRecord.size(); i++) 
            {
                Patient patient = patientsRecord.get(i);
                System.out.println("Patient " + (i + 1) + ":");
                System.out.println("Surname: "  + patient.patientSurname);
                System.out.println("Name: "     + patient.patientName);
                System.out.println("ID: "       + patient.patientID);
                System.out.println("Category: " + patient.Category);
            }
        }
        int inpatients = 0;
        int outpatients = 0;
        int emergency = 0;
        for (int i = 0; i < patientsRecord.size(); i++) 
        {
            Patient patient = patientsRecord.get(i);
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
        //DISPLAYS THE CALCULATED VALUES
        System.out.println("\nCategory Total:");
        System.out.println("1: Total Registered Patients : " + patientsRecord.size());
        System.out.println("2: Inpatients: " + inpatients);
        System.out.println("3: Outpatients: " + outpatients);
        System.out.println("4: Emergency: " + emergency);
        int bedsInUse = 0;
        for (int i = 0; i < bedStatus.length; i++) 
        {
            if (bedStatus[i]) 
            {
                bedsInUse++;
            }
        }
        int totalBeds = bedStatus.length;
        int bedsAvailable = totalBeds - bedsInUse;
        double percentage = ((double) bedsInUse / totalBeds) * 100;
        System.out.println("\n WARD BED REPORT");
        System.out.println("1: Total Beds : " + totalBeds);
        System.out.println("2: Beds Occupied : " + bedsInUse);
        System.out.println("3: Beds Available : " + bedsAvailable);
        System.out.printf("4: Occupancy Percentage: %.2f%%\n", percentage);
    }
} 
