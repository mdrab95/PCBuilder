import com.pcbuilder.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.*;
import java.io.*;

/**
 * The main class of the server
 *
 * TCP SERVER BASE ALGORITHM CREATED BY:
 * @author Gall Anonim
 * @version 1.2
 *
 * Query handling:
 * Michał Drab
 *
 */
public class TCPServer {

    /**
     * port number
     */
    final int PORT = 8887;
    
     /**
     * field represents the socket waiting for client connections
     */
    private ServerSocket serverSocket;

    /**
     * Creates the server socket
     * @throws IOException when port is already bound
     */
    TCPServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }
    
    /**
     * The main application method
     * @param args all parameters are ignored
     * @throws IOException exception
     */
    public static void main(String args[]) throws IOException {

        Socket socket = null;
        
        TCPServer tcpServer = null;
        try {
            tcpServer = new TCPServer();
            System.out.println("Server started");

            while (true) {
                socket = tcpServer.serverSocket.accept();
                try {
                    SingleService singleService = new SingleService(socket);
                    singleService.realize();
                } catch (IOException e) {
                    socket.close();
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            tcpServer.serverSocket.close();
        }
    }
}

/**
 * The server class servicing a single connection
 */
class SingleService {

    /**
     * socket representing connection to the client
     */
    private Socket socket;
    /**
     * buffered input character stream
     */
    private BufferedReader in;
    /**
     * Formatted output character stream
     */
    private PrintWriter out;

    /**
     * The constructor of instance of the SingleService class. Use the socket as
     * a parameter.
     *
     * @param socket socket representing connection to the client
     */
    public SingleService(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
    }

    //region lists
    private final ObservableList<ModelCPU> cpuList = FXCollections.observableArrayList();
    private final ObservableList<ModelGPU> gpuList = FXCollections.observableArrayList();
    private final ObservableList<ModelCPUCooler> cpuCoolerList = FXCollections.observableArrayList();
    private final ObservableList<ModelMOBO> moboList = FXCollections.observableArrayList();
    private final ObservableList<ModelSSD> ssdList = FXCollections.observableArrayList();
    private final ObservableList<ModelHDD> hddList = FXCollections.observableArrayList();
    private final ObservableList<ModelRAM> ramList = FXCollections.observableArrayList();
    private final ObservableList<ModelPSU> psuList = FXCollections.observableArrayList();
    private final ObservableList<ModelCase> caseList = FXCollections.observableArrayList();
    ModelDataLoaderAndFilter dlaf = new ModelDataLoaderAndFilter();
    //

    //region Data Loader\

    /**
     * loads all data
     */
    private void dataLoading() {
        cpuList.addAll(dlaf.cpuDataLoader());
        cpuCoolerList.addAll(dlaf.cpuCoolerDataLoader());
        moboList.addAll(dlaf.moboDataLoader());
        gpuList.addAll(dlaf.gpuDataLoader());
        ramList.addAll(dlaf.ramDataLoader());
        ssdList.addAll(dlaf.ssdDataLoader());
        hddList.addAll(dlaf.hddDataLoader());
        psuList.addAll(dlaf.psuDataLoader());
        caseList.addAll(dlaf.caseDataLoader());
    }

    /**
     * prints command tree (help)
     */
    private void printCommandTree(){
        out.println("10 OK - HELP");
        out.println("List of commands: ");
        out.println("--> SHOW");
        out.println("------> CPU");
        out.println("------> CPU COOLER");
        out.println("------> MOBO");
        out.println("------> GPU");
        out.println("------> RAM");
        out.println("------> SSD");
        out.println("------> HDD");
        out.println("------> PSU");
        out.println("------> CASE");
        out.println("-----------> GO");
        out.println("--> FIND");
        out.println("------> CPU");
        out.println("------> CPU COOLER");
        out.println("------> MOBO");
        out.println("------> GPU");
        out.println("------> RAM");
        out.println("------> SSD");
        out.println("------> HDD");
        out.println("------> PSU");
        out.println("------> CASE");
        out.println("-----------> GO");
        out.println("----------------> [YOUR DATA]");
        out.println("---------------------> GO");
        out.println("--> QUIT");
    }

    private void help () {
        printCommandTree();
    }

    /**
     * runs show process
     * @param showWhat item type
     * @return respond string
     */
    private String show (String showWhat) {
        String rspnd = "";
        switch (showWhat.toUpperCase()) {
            case "CPU":
                rspnd = "31 OK - CPU. Write GO to continue:";
                break;
            case "CPU COOLER":
                rspnd = "32 OK - CPU COOLER. Write GO to continue:";
                break;
            case "MOBO":
                rspnd = "33 OK - MOBO. Write GO to continue:";
                break;
            case "GPU":
                rspnd = "34 OK - GPU. Write GO to continue:";
                break;
            case "RAM":
                rspnd = "35 OK - RAM. Write GO to continue:";
                break;
            case "SSD":
                rspnd = "36 OK - SSD. Write GO to continue:";
                break;
            case "HDD":
                rspnd = "37 OK - HDD. Write GO to continue:";
                break;
            case "PSU":
                rspnd = "38 OK - PSU. Write GO to continue:";
                break;
            case "CASE":
                rspnd = "39 OK - CASE. Write GO to continue:";
                break;
            default:
                rspnd = "02 ERROR. Bad command.";
                break;
        }
        return rspnd;
    }

    /**
     * shows items list
     * @param showWhat item type
     */
    private void showLists (String showWhat) {
        String option = showWhat.substring(0,2);
        switch (option) {
            case "31":
                out.println("41 OK - CPU List: ");
                for(ModelCPU item : cpuList) out.println((padRight(item.getBrand(), 8) + padRight(item.getFamily()
                        + " ", 10) + padRight(item.getName(),8) + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                break;
            case "32":
                out.println("42 OK - CPU Cooler List: ");
                for(ModelCPUCooler item : cpuCoolerList) out.println((padRight(item.getBrand(), 15) + padRight(item.getName()
                        + " ", 28) + padRight(String.valueOf(item.getPrice()), 4)) + " PLN");
                break;
            case "33":
                out.println("43 OK - MOBO List: ");
                for(ModelMOBO item : moboList) out.println((padRight(item.getBrand(), 10) + padRight(item.getChipset()
                        + " ", 5) + padRight(item.getName(),19) + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                break;
            case "34":
                out.println("44 OK - GPU List: ");
                for(ModelGPU item : gpuList) out.println((padRight(item.getManufacturer(), 7) + padRight(item.getseries()
                        + " ", 11) + padRight(item.getName(),14) + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                break;
            case "35":
                out.println("45 OK - RAM List: ");
                for(ModelRAM item : ramList) out.println((padRight(item.getBrand(), 10) + padRight(item.getName()
                        + " ", 14) + padRight(item.getStandard(), 6) +padRight(String.valueOf(item.getMemorySize()) + "GB",5)
                        + padRight(String.valueOf(" in " + item.getNumberOfModules()) + " modules ", 15)
                        + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                break;
            case "36":
                out.println("46 OK - SSD List: ");
                for(ModelSSD item : ssdList) out.println((padRight(item.getBrand(), 9) + padRight(item.getName()
                        + " ", 15) + padRight(String.valueOf(item.getCapacity()) + "GB",8) + padRight("(" + item.getSerialNumber() + ") ", 25)
                        + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                break;
            case "37":
                out.println("47 OK - HDD List: ");
                for(ModelHDD item : hddList) out.println((padRight(item.getBrand(), 17) + padRight(item.getName()
                        + " ", 11) + padRight(String.valueOf(item.getCapacity() + "GB"),8) + padRight(String.valueOf(item.getPrice()), 5)) + " PLN");
                break;
            case "38":
                out.println("48 OK - PSU List: ");
                for(ModelPSU item : psuList) out.println((padRight(item.getBrand(), 14) + padRight(item.getName()
                        + " ", 20) + padRight(String.valueOf(item.getWattage()) + "W",6) + padRight(String.valueOf(item.getPrice()), 5)) + " PLN");
                break;
            case "39":
                out.println("49 OK - CASE List: ");
                for(ModelCase item : caseList) out.println((padRight(item.getBrand(), 15)
                        + padRight(item.getName(),38) + padRight(String.valueOf(item.getPrice()), 5)) + " PLN");
                break;
            default:
                out.println("04 ERROR. Unexpected error.");
                break;
        }
    }

    /**
     * runs search process
     * @param findWhat searched item type
     * @param searchedValue searched value
     */
    private void searchData (String findWhat, String searchedValue) {
        String option = findWhat.substring(0,2);
        int opt = Integer.valueOf(option) + 40;
        switch (opt) {
            case 71:
                out.println("71 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredCpuList(searchedValue);
                break;
            case 72:
                out.println("72 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredCpuCoolerList(searchedValue);
                break;
            case 73:
                out.println("73 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredMoboList(searchedValue);
                break;
            case 74:
                out.println("74 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredGpuList(searchedValue);
                break;
            case 75:
                out.println("75 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredRamList(searchedValue);
                break;
            case 76:
                out.println("76 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredSsdList(searchedValue);
                break;
            case 77:
                out.println("77 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredHddList(searchedValue);
                break;
            case 78:
                out.println("78 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredPsuList(searchedValue);
                break;
            case 79:
                out.println("79 OK - YOUR INQUIRY: " + searchedValue);
                showFilteredCaseList(searchedValue);
                break;
            default:
                out.println("04 ERROR. Unexpected error.");
                break;
        }
    }

    /**
     * prints searched CPUs
     * @param searchedValue searched value
     */
    private void showFilteredCpuList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelCPU item : cpuList) {
            String itemData = item.getBrand() + " " + item.getFamily() + " " + item.getName() + " " + item.getBrand()
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 8) + padRight(item.getFamily()
                        + " ", 10) + padRight(item.getName(), 8) + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched CPU Coolers
     * @param searchedValue searched value
     */
    private void showFilteredCpuCoolerList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelCPUCooler item : cpuCoolerList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getManufacturerCode() + " " + item.getBrand()
                    + " " + item.getManufacturerCode();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 15) + padRight(item.getName()
                        + " ", 28) + padRight(String.valueOf(item.getPrice()), 4)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched MOBOs
     * @param searchedValue searched value
     */
    private void showFilteredMoboList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelMOBO item : moboList) {
            String itemData = item.getBrand() + " " + item.getChipset() + " " + item.getName() + " " + item.getBrand()
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 10) + padRight(item.getChipset()
                        + " ", 5) + padRight(item.getName(), 19) + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched GPUs
     * @param searchedValue searched value
     */
    private void showFilteredGpuList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelGPU item : gpuList) {
            String itemData = item.getManufacturer() + " " + item.getseries() + " " + item.getName() + " " + item.getManufacturer()
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getManufacturer(), 7) + padRight(item.getseries()
                        + " ", 11) + padRight(item.getName(), 14) + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched RAM
     * @param searchedValue searched value
     */
    private void showFilteredRamList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelRAM item : ramList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getStandard() + " " + item.getMemorySize() + "GB "
                    + item.getBrand() + " " + item.getName() + " " + item.getMemorySize() + "GB " + item.getMemoryClock() + "MHz "
                    + item.getBrand() + " " + item.getMemorySize() + "GB " + item.getMemoryClock() + "GB " + item.getStandard()
                    + " " + item.getSerialNumber();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 10) + padRight(item.getName()
                        + " ", 14) + padRight(item.getStandard(), 6) + padRight(String.valueOf(item.getMemorySize()) + "GB", 5)
                        + padRight(String.valueOf(" in " + item.getNumberOfModules()) + " modules ", 15)
                        + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched SSDs
     * @param searchedValue searched value
     */
    private void showFilteredSsdList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelSSD item : ssdList) {
            String itemData = item.getBrand() + " " + item.getFormFactor() + " " + item.getName() + " " + item.getCapacity() + "GB "
                    + item.getBrand() + " " + item.getName() + " " + item.getFormFactor() + " " + item.getCapacity() + "GB "
                    + item.getBrand() + " " + item.getFormFactor() + " " + item.getBrand() + " " + item.getCapacity() + "GB "
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 9) + padRight(item.getName()
                        + " ", 15) + padRight(String.valueOf(item.getCapacity()) + "GB", 8) + padRight("(" + item.getSerialNumber() + ") ", 25)
                        + padRight(String.valueOf(item.getPrice()), 6)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched HDDs
     * @param searchedValue searched value
     */
    private void showFilteredHddList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelHDD item : hddList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getCapacity() + "GB " + item.getBrand() + " " + item.getCapacity() + "GB"
                    + " " + item.getName();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 17) + padRight(item.getName()
                        + " ", 11) + padRight(String.valueOf(item.getCapacity() + "GB"), 8) + padRight(String.valueOf(item.getPrice()), 5)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched PSUs
     * @param searchedValue searched value
     */
    private void showFilteredPsuList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelPSU item : psuList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getWattage() + "W " + item.getBrand() + item.getWattage() + "W "
                    + item.getBrand() + " " + item.getName() + " " + item.getWattage() + " " + item.getBrand() + item.getWattage() + " ";
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 14) + padRight(item.getName()
                        + " ", 20) + padRight(String.valueOf(item.getWattage()) + "W", 6) + padRight(String.valueOf(item.getPrice()), 5)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * prints searched cases
     * @param searchedValue searched value
     */
    private void showFilteredCaseList(String searchedValue) {
        boolean foundNothing = true;
        for (ModelCase item : caseList) {
            String itemData = item.getBrand() + " " + item.getName() + " " + item.getFormFactor();
            if (itemData.toUpperCase().contains(searchedValue.toUpperCase())) {
                out.println((padRight(item.getBrand(), 15)
                        + padRight(item.getName(), 38) + padRight(String.valueOf(item.getPrice()), 5)) + " PLN");
                foundNothing = false;
            }
        }
        if (foundNothing == true)
            foundNothing();
    }

    /**
     * Send to user Nothing was found message.
     */
    private void foundNothing(){
        out.println("80. Nothing was found.");
    }


    /**
     * Realizes the service
     */
    public void realize() {
        try {
            out.println(new java.util.Date().toString());
            out.println("Say hi, then enter the command: (HELP - list of commands)");
            dataLoading();

            while (true) {
                String str = in.readLine();
                if (str.toUpperCase().equals("QUIT")) {
                    break;
                }
                if (str.toUpperCase().equals("HELP")) {
                    help();
                }
                if (str.toUpperCase().equals("SHOW")) {
                    out.println("20 OK - SHOW [CPU / CPU COOLER / MOBO / GPU / RAM / SSD / HDD / PSU / CASE]");
                    String showWhat = in.readLine();
                    String respond = show(showWhat);
                    out.println(respond);
                    if (respond.substring(0,2).equals("02")) {}
                    else {
                        String writeGo = in.readLine();
                        if (writeGo.toUpperCase().equals("GO")) {
                            showLists(respond);
                        } else {
                            out.println("03 ERROR. User did not confirm command.");
                        }
                    }
                }

                if (str.toUpperCase().equals("FIND")) {
                    out.println("50 OK - FIND [CPU / CPU COOLER / MOBO / GPU / RAM / SSD / HDD / PSU / CASE]");
                    String findWhat = in.readLine();
                    String respond = show(findWhat);
                    out.println(respond);
                    if (respond.substring(0,2).equals("02")) {}
                    else {
                        String writeGo = in.readLine();
                        if (writeGo.toUpperCase().equals("GO")) {
                            out.println("60 OK - Type data to start searching.");
                            String searchedData = in.readLine();
                            out.println("70 OK - Type GO to continue.");
                            String writeSecondGo = in.readLine();
                            if (writeSecondGo.toUpperCase().equals("GO")){
                                searchData(respond, searchedData);
                            }
                            else {
                                out.println("03 ERROR. User did not confirm command.");
                            }
                        } else {
                            out.println("03 ERROR. User did not confirm command.");
                        }
                    }
                }
                if ((str.toUpperCase().equals("QUIT") || str.toUpperCase().equals("HELP") || str.toUpperCase().equals("SHOW") || str.toUpperCase().equals("FIND")) == false){
                    out.println("01 ERROR. Bad command.");
                }
                out.println("\n------> Enter the command: ");
            }
            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * This method formats strings (right padding).
     * @param s string
     * @param n number of chars
     * @return formatted string
     */
    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * This method formats strings (left padding).
     * @param s string
     * @param n number of chars
     * @return formatted string
     */
    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }
}
