package Main;

import static Main.Utils.print;
import  static Main.Utils.randomTime;

public class Student extends Thread  {
    private String stu_name;
    private LaserPrinter printer;

    public Student(ThreadGroup group, String stu_name, LaserPrinter printer) {
        super(group,"thread has"+ stu_name);
        this.stu_name = stu_name;
        this.printer = printer;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public LaserPrinter getPrinter() {
        return printer;
    }

    public void setPrinter(LaserPrinter printer) {
        this.printer = printer;

    }

    @Override
    public void run() {
        Document CWK1 = new Document(stu_name, "cwk1", 50);
        Document CWK2 = new Document(stu_name, "cwk2", 20);
        Document CWK3 = new Document(stu_name, "cwk3", 15);
        Document CWK4 = new Document(stu_name, "cwk4", 25);
        Document CWK5 = new Document(stu_name, "cwk5", 10);

        // print documents
        try {
            print("waiting to print " + CWK1.getDocumentName() + " of " + CWK1.getUserID() + ". Number of pages: "+ CWK1.getNumberOfPages());
            printer.printDocument(CWK1);
            sleep(randomTime());

            print("waiting to print " + CWK2.getDocumentName() + " of " + CWK2.getUserID()+ ". Number of pages: "+ CWK2.getNumberOfPages());
            printer.printDocument(CWK2);
            sleep(randomTime());

            print("waiting to print " + CWK3.getDocumentName() + " of " + CWK3.getUserID()+ ". Number of pages: "+ CWK3.getNumberOfPages());
            printer.printDocument(CWK3);
            sleep(randomTime());

            print("waiting to print " + CWK4.getDocumentName() + " of " + CWK4.getUserID()+ ". Number of pages: "+ CWK4.getNumberOfPages());
            printer.printDocument(CWK4);
            sleep(randomTime());

            print("waiting to print " + CWK5.getDocumentName() + " of " + CWK5.getUserID()+ ". Number of pages: "+ CWK5.getNumberOfPages());
            printer.printDocument(CWK5);
            sleep(randomTime());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



