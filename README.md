**Title:** Hospital Simulation with Semaphore-Based Synchronization

**Description:**
This Java project simulates a hospital's patient intake and treatment process using semaphore-based synchronization. The simulation includes intake nurses managing patient queues at the front desk and emergency department, as well as treatment doctors handling patient treatments based on severity.

**Hospital Class:**
The `Hospital` class encapsulates the hospital's functionality, including multiple semaphores for synchronization and queues for managing patient intake and treatment. It provides methods for intake at the front desk and emergency department, as well as processing by intake nurses and treatment doctors.

**Semaphore-Based Synchronization:**
The hospital uses semaphores to control access to critical sections of code, ensuring that only one thread can execute certain operations at a time. Semaphores are used to manage access to queues and prevent race conditions during patient intake and treatment.

**Patient Intake and Treatment:**
The simulation generates random patient intakes with varying severity levels and types (emergency, general, appointment). Intake nurses manage patient queues, while treatment doctors prioritize and treat patients based on severity, simulating real-world hospital scenarios.

**Multi-Threading:**
The simulation utilizes multiple threads to model concurrent processes within the hospital. Nurse threads handle patient intake, while doctor threads manage patient treatment. This multi-threaded approach ensures efficient resource utilization and responsiveness in handling patient requests.

**Main Simulation Class:**
The `HospitalSimulation` class serves as the entry point of the simulation. It initializes the hospital, generates patient intakes, and starts nurse and doctor threads to manage patient queues and treatments, respectively.

**Conclusion:**
By employing semaphore-based synchronization, this hospital simulation demonstrates effective coordination and management of patient intake and treatment processes in a multi-threaded environment. The project highlights the importance of concurrency control in ensuring safe and efficient operations in complex systems like hospitals.
