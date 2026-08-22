package medicare;

import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.Comparator;
import static medicare.MediCare.beds;

import static org.junit.Assert.*;

public class HospitalManagementSystemTest {

    @Before
    public void setUp() {
        // Reset patient records and bed statuses before each test
        MediCare.patientsRecord.clear();
        for (int i = 0; i < MediCare.beds.length; i++) {
            MediCare.beds[i] = false;
        }
    }

    //Register a patient
    @Test
    public void testRegisterPatient() {
        Patient p = new Patient("Thabo", "Mashobane", "100", 30, "Male", "Flu", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p);

        assertEquals(1, MediCare.patientsRecord.size());
        assertEquals("100", MediCare.patientsRecord.get(0).getPatientID());
    }

    //Search for a patient
    @Test
    public void testSearchPatient() {
        Patient p1 = new Patient("Thabo", "Mashobane", "101", 25, "Male", "Fever", patientCategory.Outpatient);
        Patient p2 = new Patient("Oratilwe", "Kobe", "102", 50, "Female", "Asthma", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p1);
        MediCare.patientsRecord.add(p2);

        Patient found = null;
        for (Patient p : MediCare.patientsRecord) {
            if (p.getPatientID().equalsIgnoreCase("101")) {
                found = p;
                break;
            }
        }

        assertNotNull(found);
        assertEquals("Oratilwe", found.getFirstName());
    }

    //Update patient details
    @Test
    public void testUpdatePatientDetails() {
        Patient p = new Patient("Onkarabile", "Skhosana", "P103", 21, "Female", "Stable", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p);

        p.setMedicalCondition("Critical");

        assertEquals("Stable", MediCare.patientsRecord.get(0).getMedicalCondition());
    }

    //Delete a patient
    @Test
    public void testDeletePatient() {
        Patient p = new Patient("Mark", "Zucker", "P104", 38, "Male", "Corona", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p);

        MediCare.patientsRecord.remove(p);

        assertEquals(0, MediCare.patientsRecord.size());
    }

    //Allocate a bed
    @Test
    public void testAllocateBed() {
        Inpatient in = new Inpatient("Jane", "Smith", "P105", 45, "Female", "Fracture", patientCategory.Inpatient, "Ward 1", 1);
        MediCare.patientsRecord.add(in);
        MediCare.beds[in.getBedNumber() - 1] = true;

        assertTrue(MediCare.beds[0]);
    }

    //Release a bed
    @Test
    public void testReleaseBed() {
        Inpatient in = new Inpatient("Jane", "Smith", "P106", 45, "Female", "Fracture", patientCategory.Inpatient, "Ward A", 16);
        MediCare.patientsRecord.add(in);
        MediCare.beds[15] = true;

        //Release the bed
        MediCare.beds[in.getBedNumber() - 1] = false;

        assertFalse(MediCare.beds[15]);
    }

    //Prevent duplicate Patient IDs
    @Test
    public void testPreventDuplicatePatientID() {
        Patient p1 = new Patient("John", "Doe", "P107", 30, "Male", "Flu", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p1);

        String newID = "P107";
        boolean isDuplicate = false;

        for (Patient p : MediCare.patientsRecord) {
            if (p.getPatientID().equalsIgnoreCase(newID)) {
                isDuplicate = true;
                break;
            }
        }

        assertTrue("A patient with ID '" + newID + "' already exists. Please enter another ID", isDuplicate);
    }

    //Prevent allocating a in use bed
    @Test
    public void testPreventAllocatingInBed() {
        MediCare.beds[0] = true;

        int requestedBed = 1;
        boolean canAllocate = !MediCare.beds[requestedBed - 1];

        assertFalse("Cannot allocate bed 1 as it is already occupied", canAllocate);
    }

    //Prevent bed allocation when all beds are occupied
    @Test
    public void testPreventBedAllocationWhenAllBedsOccupied() {
        for (int i = 0; i < MediCare.beds.length; i++) {
            MediCare.beds[i] = true;
        }

        boolean hasAvailableBed = false;
        for (boolean status : MediCare.beds) {
            if (!status) {
                hasAvailableBed = true;
                break;
            }
        }

        assertFalse("No beds available when all are occupied", hasAvailableBed);
    }

    //Sort patients by surname or Patient ID
    @Test
    public void testSortPatientsBySurnameOrID() {
        Patient p1 = new Patient("Zack", "Zulu", "P110", 30, "Male", "Flu", patientCategory.Outpatient);
        Patient p2 = new Patient("Adam", "Apple", "P108", 25, "Male", "Fever", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p1);
        MediCare.patientsRecord.add(p2);

        Collections.sort(MediCare.patientsRecord, Comparator.comparing(Patient::getSurname));

        assertEquals("Apple", MediCare.patientsRecord.get(0).getSurname());
        assertEquals("Zulu", MediCare.patientsRecord.get(1).getSurname());
    }
}