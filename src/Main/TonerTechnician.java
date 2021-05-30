package Main;
import static Main.Utils.randomTime;

public class TonerTechnician extends Thread {
    private String tonerTechnicianName;
    private LaserPrinter printer;

    public TonerTechnician(ThreadGroup group, String tonerTechnicianName, LaserPrinter printer) {
        super(group, "Thread of student " + tonerTechnicianName);
        this.tonerTechnicianName = tonerTechnicianName;
        this.printer = printer;
    }

    public String getTonerTechnicianName() {
        return tonerTechnicianName;
    }

    public void setTonerTechnicianName(String tonerTechnicianName) {
        this.tonerTechnicianName = tonerTechnicianName;
    }

    public LaserPrinter getPrinter() {
        return printer;
    }

    public void setPrinter(LaserPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread of toner technician '" + getTonerTechnicianName() + "' started. toners will replace by: "+ getTonerTechnicianName());

            printer.replaceTonerCartridge();
            sleep(randomTime());

            printer.replaceTonerCartridge();
            sleep(randomTime());

            printer.replaceTonerCartridge();
            sleep(randomTime());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
