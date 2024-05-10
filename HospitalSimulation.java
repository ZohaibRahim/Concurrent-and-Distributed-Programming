package Q3;
import java.util.Random;
public class HospitalSimulation {
    private static final int nurses = 2;
    private static final int docs = 3;
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Creating 25 intakes with various severities and types
        Random rand = new Random();
        for (int i = 0; i < 25; i++) {
            int severity = rand.nextInt(5) + 1; // Severity between 1 and 5
            String[] types = {"Emergency", "General", "Appointment"};
            String type = types[rand.nextInt(types.length)];
            String patientName = "Patient" + (i + 1);
            Intake intake = new Intake(severity, type, patientName);

            if (type.equals("Emergency")) {
                hospital.intakeEmergency(intake);
            } else {
                hospital.intakeFrontDesk(intake);
            }
        }

        // Creating and starting nurse threads
        for (int i = 0; i < nurses; i++) {
            new Thread(hospital::processIntakeNurse).start();
        }

        // Creating and starting doctor threads
        for (int i = 0; i < docs; i++) {
            new Thread(hospital::processTreatmentDoctor).start();
        }
    }
}
