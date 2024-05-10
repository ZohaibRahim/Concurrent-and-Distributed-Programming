package Q3;
import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;
class Hospital {


    private Semaphore frontDeskSemaphore = new Semaphore(1, true);
    private Semaphore emergencySemaphore = new Semaphore(1, true);
    private Semaphore treatmentSemaphore = new Semaphore(1, true);

    private Queue<Intake> frontDeskQueue = new LinkedList<>();
    private Queue<Intake> emergencyQueue = new LinkedList<>();
    private Queue<Intake> emergencyTreatmentQueue = new LinkedList<>();
    private Queue<Intake> generalQueue = new LinkedList<>();
    private Queue<Intake> appointmentQueue = new LinkedList<>();

    public void intakeFrontDesk(Intake intake) {
        try {
            frontDeskSemaphore.acquire();
            System.out.println("Task created: " + intake.getPatientName());
            frontDeskQueue.add(intake);
            System.out.println("Entered into intake queue: " + intake.getPatientName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            frontDeskSemaphore.release();
        }
    }

    public void intakeEmergency(Intake intake) {
        try {
            emergencySemaphore.acquire();
            System.out.println("Task created: " + intake.getPatientName());
            emergencyQueue.add(intake);
            System.out.println("Entered into emergency queue: " + intake.getPatientName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            emergencySemaphore.release();
        }
    }

    public void processIntakeNurse() {
        while (true) {
            try {
                frontDeskSemaphore.acquire();
                emergencySemaphore.acquire();

                if (!emergencyQueue.isEmpty()) {
                    Intake intake = emergencyQueue.poll();
                    System.out.println("Processed by an intake nurse (emergency): " + intake.getPatientName());
                    determineQueue(intake);
                } else if (!frontDeskQueue.isEmpty()) {
                    Intake intake = frontDeskQueue.poll();
                    System.out.println("Processed by an intake nurse (front desk): " + intake.getPatientName());
                    determineQueue(intake);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                frontDeskSemaphore.release();
                emergencySemaphore.release();
            }
        }
    }

    private void determineQueue(Intake intake) {
        switch (intake.getType()) {
            case "Emergency":
                emergencyTreatmentQueue.add(intake);
                System.out.println("Moved between queues: " + intake.getPatientName());
                break;
            case "General":
                generalQueue.add(intake);
                System.out.println("Moved between queues: " + intake.getPatientName());
                break;
            case "Appointment":
                appointmentQueue.add(intake);
                System.out.println("Moved between queues: " + intake.getPatientName());
                break;
        }
    }

    public void processTreatmentDoctor() {
        while (true) {
            try {
                treatmentSemaphore.acquire();

                if (!emergencyTreatmentQueue.isEmpty()) {
                    Intake intake = emergencyTreatmentQueue.poll();
                    System.out.println("Started treatment by the doctor (emergency): " + intake.getPatientName());
                    Thread.sleep(intake.getSeverity() * 500);
                    System.out.println("Released: " + intake.getPatientName());
                } else if (!generalQueue.isEmpty()) {
                    Intake intake = generalQueue.poll();
                    System.out.println("Started treatment by the doctor (general): " + intake.getPatientName());
                    Thread.sleep(intake.getSeverity() * 500);
                    System.out.println("Released: " + intake.getPatientName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                treatmentSemaphore.release();
            }
        }
    }
}
