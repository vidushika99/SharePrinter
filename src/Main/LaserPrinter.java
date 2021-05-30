package Main;

import static  Main.Utils.print;

public class LaserPrinter  implements ServicePrinter {


        private String printerID;
        private int currentPaperLevel = ServicePrinter.Full_Paper_Tray;
        private int numberOfDocPrinted ;
        private int tonerLevel = ServicePrinter.Full_Toner_Level;
        private ThreadGroup studentsGroup;
        private ThreadGroup technicianGroup;

    public LaserPrinter(String printerID, ThreadGroup studentsGroup, ThreadGroup technicianGroup) {
        this.printerID = printerID;
        this.studentsGroup = studentsGroup;
        this.technicianGroup = technicianGroup;
    }

    public String getPrinterID() {
        return printerID;
    }

    public void setPrinterID(String printerID) {
        this.printerID = printerID;
    }

    public int getCurrentPaperLevel() {
        return currentPaperLevel;
    }

    public void setCurrentPaperLevel(int currentPaperLevel) {
        this.currentPaperLevel = currentPaperLevel;
    }

    public int getNumberOfDocPrinted() {
        return numberOfDocPrinted;
    }

    public void setNumberOfDocPrinted(int numberOfDocPrinted) {
        this.numberOfDocPrinted = numberOfDocPrinted;
    }

    public int getTonerLevel() {
        return tonerLevel;
    }

    public void setTonerLevel(int tonerLevel) {
        this.tonerLevel = tonerLevel;
    }

    public ThreadGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(ThreadGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public ThreadGroup getTechnicianGroup() {
        return technicianGroup;
    }

    public void setTechnicianGroup(ThreadGroup technicianGroup) {
        this.technicianGroup = technicianGroup;
    }

    @Override
        public synchronized void replaceTonerCartridge () {
        // check toner level of the printer and replace toner cartridge if the toner level is not enough to print.
        try {
            while (tonerLevel > ServicePrinter.Minimum_Toner_Level) {
                if(studentsGroup.activeCount() == 0) {
                    break;
                }

                System.out.println("Check toner level of the printer ");
                System.out.println("Printer already have enough toner level to proceed . Trying again in 5 seconds.");
                wait(5000);    // wait for 5 seconds before checking if it can be refilled it again.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(studentsGroup.activeCount() != 0) {
            System.out.println("Check toner level of the printer ");
            tonerLevel = ServicePrinter.Full_Toner_Level;
            System.out.println("-- Toner Replaced --");
            print(toString());
            print("");
        }else{
            System.out.println("Exit from toner replacing process since all the printing processes are completed. ");
        }

        // notify others
        notifyAll();
        }

        @Override
        public synchronized void refillPaper () {
        // check paper level of the printer and refill papers if the paper level is not enough to print.
            int paperBalance;
            int hasToFill;
            try {
                while ((currentPaperLevel + ServicePrinter.SheetsPerPack) > ServicePrinter.Full_Paper_Tray) {
                    System.out.println("Check paper level of the printer ");
                    System.out.println("Printer already have enough papers to proceed. Trying again in 5 seconds.");
                    wait(5000);
                }

                print(toString());
                System.out.println("Check paper level of the printer ");
                paperBalance = ServicePrinter.Full_Paper_Tray - currentPaperLevel;
                hasToFill = paperBalance / ServicePrinter.SheetsPerPack;
                currentPaperLevel = currentPaperLevel + (hasToFill * ServicePrinter.SheetsPerPack);
                System.out.println("--Paper Refiled--");
                print(toString());
                print("");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // notify others
            notifyAll();

        }

        @Override
        public synchronized void printDocument (Document document){
        // if there is enough toner and paper print the documents
           int pageCount= document.getNumberOfPages();
            try {
                print(toString());
                System.out.println("Printing " + document.getDocumentName() + " of " + document.getUserID());
                for (int i = 0; i < pageCount; i++) {
                    if (currentPaperLevel > 0 && tonerLevel > 0) {
                        currentPaperLevel--;
                        tonerLevel--;
                    } else {
                        System.out.println("Paper or toner level is not enough to proceed");
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numberOfDocPrinted++;
            print(toString());
            print("");

            // notify others
            notifyAll();

        }

        @Override
        public String toString () {
            return "LaserPrinter{" +
                    "printer ID='" + printerID + '\'' +
                    ",Paper Level=" + currentPaperLevel +
                    ", Document Printed=" + numberOfDocPrinted +
                    ", Toner Level=" + tonerLevel +
                    '}';
        }
    }
