package Main;

public class PrintingSystem {
    public static void main(String[] args) {
        // initialize
        ThreadGroup studentsGroup = new ThreadGroup("Student Group");
        ThreadGroup techniciansGroup = new ThreadGroup("Technician Group");

        LaserPrinter laserPrinter = new LaserPrinter("LP-2001", studentsGroup, techniciansGroup);

        Student student1 = new Student(studentsGroup, "John Smith", laserPrinter);
        Student student2 = new Student(studentsGroup, "Harry Wilson", laserPrinter);
        Student student3 = new Student(studentsGroup, "Brent Rivera", laserPrinter);
        Student student4 = new Student(studentsGroup, "Norman JOE", laserPrinter);

        PaperTechnician paperTechnician = new PaperTechnician(techniciansGroup, "Ben David", laserPrinter);
        TonerTechnician tonerTechnician = new TonerTechnician(techniciansGroup, "Andrew Smith", laserPrinter);

        // Starting all threads.
        student1.start();
        student2.start();
        student3.start();
        student4.start();
        paperTechnician.start();
        tonerTechnician.start();

        // wait for all threads to terminate.
        try {
            student1.join();
            student2.join();
            student3.join();
            student4.join();
            paperTechnician.join();
            tonerTechnician.join();

            // final status of the printer after terminating all the threads.
            System.out.println(laserPrinter.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
