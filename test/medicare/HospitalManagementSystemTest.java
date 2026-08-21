package medicare;

import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;

public class HospitalManagementSystemTest {

    @Before
    public void setUp() {
        // Reset patient records and bed statuses before each test
        MediCare.patientsRecord.clear();
        for (int i = 0; i < MediCare.bedStatus.length; i++) {
            MediCare.bedStatus[i] = false;
        }
    }

    // 1. Register a patient
    @Test
    public void testRegisterPatient() {
        Patient p = new Patient("John", "Doe", "P100", 30, "Male", "Flu", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p);

        assertEquals(1, MediCare.patientsRecord.size());
        assertEquals("P100", MediCare.patientsRecord.get(0).getPatientID());
    }

    // 2. Search for a patient
    @Test
    public void testSearchPatient() {
        Patient p1 = new Patient("Alex", "Ray", "P101", 25, "Male", "Fever", patientCategory.Outpatient);
        Patient p2 = new Patient("Mary", "Jane", "P102", 50, "Female", "Asthma", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p1);
        MediCare.patientsRecord.add(p2);

        Patient found = null;
        for (Patient p : MediCare.patientsRecord) {
            if (p.getPatientID().equalsIgnoreCase("P102")) {
                found = p;
                break;
            }
        }

        assertNotNull(found);
        assertEquals("Mary", found.getFirstName());
    }

    // 3. Update patient details
    @Test
    public void testUpdatePatientDetails() {
        Patient p = new Patient("Sarah", "Connor", "P103", 40, "Female", "Stable", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p);

        p.setMedicalCondition("Critical");

        assertEquals("Critical", MediCare.patientsRecord.get(0).getMedicalCondition());
    }

    // 4. Delete a patient
    @Test
    public void testDeletePatient() {
        Patient p = new Patient("Mark", "Zucker", "P104", 38, "Male", "Observation", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p);

        MediCare.patientsRecord.remove(p);

        assertEquals(0, MediCare.patientsRecord.size());
    }

    // 5. Allocate a bed
    @Test
    public void testAllocateBed() {
        Inpatient in = new Inpatient("Jane", "Smith", "P105", 45, "Female", "Fracture", patientCategory.Inpatient, "Ward A", 1);
        MediCare.patientsRecord.add(in);
        MediCare.bedStatus[in.getBedNumber() - 1] = true;

        assertTrue(MediCare.bedStatus[0]);
    }

    // 6. Release a bed
    @Test
    public void testReleaseBed() {
        Inpatient in = new Inpatient("Jane", "Smith", "P106", 45, "Female", "Fracture", patientCategory.Inpatient, "Ward A", 1);
        MediCare.patientsRecord.add(in);
        MediCare.bedStatus[0] = true;

        // Release the bed
        MediCare.bedStatus[in.getBedNumber() - 1] = false;

        assertFalse(MediCare.bedStatus[0]);
    }

    // 7. Prevent duplicate Patient IDs
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

        assertTrue("Duplicate ID detected correctly", isDuplicate);
    }

    // 8. Prevent allocating an occupied bed
    @Test
    public void testPreventAllocatingOccupiedBed() {
        MediCare.bedStatus[0] = true; // Bed 1 is already occupied

        int requestedBed = 1;
        boolean canAllocate = !MediCare.bedStatus[requestedBed - 1];

        assertFalse("Cannot allocate bed 1 as it is already occupied", canAllocate);
    }

    // 9. Prevent bed allocation when all beds are occupied
    @Test
    public void testPreventBedAllocationWhenAllBedsOccupied() {
        // Mark all beds as occupied
        for (int i = 0; i < MediCare.bedStatus.length; i++) {
            MediCare.bedStatus[i] = true;
        }

        boolean hasAvailableBed = false;
        for (boolean status : MediCare.bedStatus) {
            if (!status) {
                hasAvailableBed = true;
                break;
            }
        }

        assertFalse("No beds available when all are occupied", hasAvailableBed);
    }

    // 10. Sort patients by surname or Patient ID
    @Test
    public void testSortPatientsBySurnameOrID() {
        Patient p1 = new Patient("Zack", "Zulu", "P110", 30, "Male", "Flu", patientCategory.Outpatient);
        Patient p2 = new Patient("Adam", "Apple", "P108", 25, "Male", "Fever", patientCategory.Outpatient);
        MediCare.patientsRecord.add(p1);
        MediCare.patientsRecord.add(p2);

        // Sort by Surname
        Collections.sort(MediCare.patientsRecord, Comparator.comparing(Patient::getSurname));

        assertEquals("Apple", MediCare.patientsRecord.get(0).getSurname());
        assertEquals("Zulu", MediCare.patientsRecord.get(1).getSurname());
    }
}