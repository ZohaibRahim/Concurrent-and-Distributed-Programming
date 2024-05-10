package Q3;
class Intake {
    private static int count = 0;

    private int id;
    private int severity;
    private String type;
    private String patientName;

    public Intake(int severity, String type, String patientName) {
        this.id = ++count;
        this.severity = severity;
        this.type = type;
        this.patientName = patientName;
    }

    public int getId() {
        return id;
    }

    public int getSeverity() {
        return severity;
    }

    public String getType() {
        return type;
    }

    public String getPatientName() {
        return patientName;
    }
}
