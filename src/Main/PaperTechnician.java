package Main;
import static Main.Utils.randomTime;

public class PaperTechnician extends Thread{

    private String paperTechnicianName;
    private LaserPrinter printer;

    public PaperTechnician(ThreadGroup group, String paperTechnicianName, LaserPrinter printer) {
        super(group, "Thread of paper technician " + paperTechnicianName);
        this.paperTechnicianName = paperTechnicianName;
        this.printer = printer;
    }

    public String getPaperTechnicianName() {
        return paperTechnicianName;
    }

    public void setPaperTechnicianName(String paperTechnicianName) {
        this.paperTechnicianName = paperTechnicianName;
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
            System.out.println("Thread of paper technician '" + getPaperTechnicianName() + "' started. papers will replace by: "+ getPaperTechnicianName());

            sleep(randomTime());
            printer.refillPaper();

            sleep(randomTime());
            printer.refillPaper();

            sleep(randomTime());
            printer.refillPaper();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
