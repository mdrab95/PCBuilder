package com.pcbuilder.model;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTablesAndFillItWithData {
    String path = "src/com/pcbuilder/files/"; // default path to 'files' directory
    private void insertData() {
        Connection con = null;
        try {
            // Load file with db driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Create connection to db
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pcbuilder;create=true", "pcbuilder", "pcbuilder");
            // Create the statement
            Statement statement = con.createStatement();
            // Create all tables. If table does not exist then fill it with data
            createAllTables(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFound exception: " + cnfe.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
                System.err.println("SQL exception: " + sqle.getMessage());
            }
        }
    }

    /**
     * Create all tables
     * @param statement statement
     */
    private void createAllTables(Statement statement){
        // Tworzymy tabele CPU
        createCpuTable(statement);
        // Tworzymy tabele CPU Cooler
        createCpuCoolerTable(statement);
        // Tworzymy tabele MOBO
        createMoboTable(statement);
        // Tworzymy tabele GPU
        createGpuTable(statement);
        // Tworzymy tabele RAM
        createRamTable(statement);
        // Tworzymy tabele SSD
        createSsdTable(statement);
        // Tworzymy tabele HDD
        createHddTable(statement);
        // Tworzymy tabele PSU
        createPsuTable(statement);
        // Tworzymy tabele CASE
        createCaseTable(statement);
    }

    /**
     * Creates CPU table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createCpuTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE CPU " +
                    "(id INTEGER, brand VARCHAR(20), socket VARCHAR(20), name VARCHAR(20), family VARCHAR(20), technology INTEGER, " +
                    "numberOfCores INTEGER, numberOfThreads INTEGER, isUnlocked INTEGER, integratedGfx INTEGER, speed DOUBLE, boostSpeed DOUBLE, cacheL2 DOUBLE," +
                    "cacheL3 DOUBLE, tdp INTEGER, wattage INTEGER, packageType VARCHAR(15), isTheCpuCoolerIncluded INTEGER, price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("CPU Table created");
            cpuDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file cpu.txt");}

    }

    /**
     * Creates CPU Cooler table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createCpuCoolerTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE CPUCOOLER" +
                    "(id INTEGER, brand VARCHAR(30), name VARCHAR(40), manufacturerCode VARCHAR(30), sockets VARCHAR(100), design VARCHAR(20), depth DOUBLE, " +
                    "width DOUBLE, height DOUBLE, weight INTEGER , airFlow DOUBLE, heatPipes INTEGER , numberOfFans INTEGER, fanSizeX DOUBLE, fanSizeY DOUBLE, " +
                    "fanSizeHeight DOUBLE, minFanSpeed INTEGER, maxFanSpeed INTEGER, minFanNoise INTEGER, maxFanNoise INTEGER,  wattage INTEGER, connectorType VARCHAR(10), " +
                    "price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("CPU Cooler Table created");
            cpuCoolerDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file cpucooler.txt");}
    }

    /**
     * Creates MOBO table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createMoboTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE MOBO" +
                    "(id INTEGER, brand VARCHAR(20), serialNumber VARCHAR(40), name VARCHAR(40), formFactor VARCHAR(30), socket VARCHAR(10), " +
                    "chipset VARCHAR(10), ramStandard VARCHAR(10), maxRam INTEGER , ramSlots INTEGER, maxRamSpeed INTEGER, connectors VARCHAR(250), audio VARCHAR (20)," +
                    "wattage INTEGER, price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("MOBO Table created");
            moboDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file mobo.txt");}
    }

    /**
     * Creates GPU table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createGpuTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE GPU" +
                    "(id INTEGER, chipManufacturer VARCHAR(20), manufacturer VARCHAR(20), series VARCHAR(30), name VARCHAR(40), manufacturerCode VARCHAR(30), architecture VARCHAR(20), " +
                    "cardLength DOUBLE, technology INTEGER, interfaceType VARCHAR(25), memorySize INTEGER, memorySizeClass VARCHAR(30), memoryType VARCHAR(10), speed INTEGER, boostSpeed INTEGER," +
                    "memorySpeed DOUBLE, external6Pin INTEGER, external8Pin INTEGER, dpConnectors INTEGER, hdmiConnectors INTEGER, dviConnectors INTEGER, vgaConnectors INTEGER, tdp INTEGER, " +
                    "wattage INTEGER, isCooledPassive INTEGER, hasBackplate INTEGER, price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("GPU Table created");
            gpuDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file gpu.txt");}
    }


    /**
     * Creates RAM table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createRamTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE RAM" +
                    "(id INTEGER, brand VARCHAR(20), serialNumber VARCHAR(20), name VARCHAR(40), standard VARCHAR(10)," +
                    "memorySize INTEGER, numberOfModules INTEGER, singleModuleSize INTEGER, ramType VARCHAR(10), memoryClock INTEGER, casLatency INTEGER, voltage DOUBLE," +
                    "wattage INTEGER, hasRadiator INTEGER, hasLighting INTEGER, price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("RAM Table created");
            ramDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file ram.txt");}
    }


    /**
     * Creates SSD table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createSsdTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE SSD" +
                    "(id INTEGER, brand VARCHAR(20), serialNumber VARCHAR(30), name VARCHAR(30), interfaceType VARCHAR(10), " +
                    "formFactor VARCHAR(15), protocol VARCHAR (10), capacity INTEGER, readSpeed INTEGER, writeSpeed INTEGER, memoryType VARCHAR(3), tbw INTEGER," +
                    "controllerManufacturer VARCHAR(30), controllerModel VARCHAR(50), wattage INTEGER, hasRadiator INTEGER, price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("SSD Table created");
            ssdDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file ram.txt");}
    }

    /**
     * Creates HDD table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createHddTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE HDD" +
                    "(id INTEGER, brand VARCHAR(20), serialNumber VARCHAR(30), name VARCHAR(30), interfaceType VARCHAR(10), " +
                    "formFactor VARCHAR(15), rotationalSpeed INTEGER, capacity INTEGER, readSpeed INTEGER, writeSpeed INTEGER, cacheSize INTEGER," +
                    "wattage INTEGER, price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("HDD Table created");
            hddDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file ram.txt");}
    }

    /**
     * Creates PSU table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createPsuTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE PSU" +
                    "(id INTEGER, brand VARCHAR(20), name VARCHAR(40), manufacturerCode VARCHAR(20), specification VARCHAR(10), certificate80Plus VARCHAR(20), " +
                    "coolingMode VARCHAR(30), protection VARCHAR(100), fanSize DOUBLE, depth DOUBLE, isModular INTEGER, wattage INTEGER, price INTEGER," +
                    "imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("PSU Table created");
            psuDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file ram.txt");}
    }

    /**
     * Creates CASE table and fills it with data (if table does not exist).
     *
     * @param statement sql statement
     */
    private void createCaseTable(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE PCCASE" +
                    "(id INTEGER, brand VARCHAR(20), serialNumber VARCHAR(20), name VARCHAR(40), formFactor VARCHAR(40), type VARCHAR(10), psuPosition VARCHAR(20), " +
                    "height DOUBLE, width DOUBLE, depth DOUBLE, weight DOUBLE, maxCpuCoolerHeight DOUBLE, external525 INTEGER, internal25 INTEGER, internal35 INTEGER," +
                    "frontFanNumber INTEGER, maxFrontFanNumber INTEGER, frontFanSize INTEGER, topFanNumber INTEGER, maxTopFanNumber INTEGER, topFanSize INTEGER," +
                    "rearFanNumber  INTEGER, maxRearFanNumber INTEGER, rearFanSize INTEGER, price INTEGER, imagePath VARCHAR (50), smallImagePath VARCHAR(50))");
            System.out.println("CASE Table created");
            caseDataInit(statement);
        } catch (SQLException sqle) {
            System.err.println("SQL exception: " + sqle.getMessage());
        } catch (IOException e) { System.out.println("Can't load data from file ram.txt");}
    }

    /**
     * Loads CPU data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void cpuDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i =1;
        try {
            name = "cpu.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[]data = line.split(";");
                int cpuIsUnlocked = 0;
                String cpuIsUnlockedString = data[7];
                if (cpuIsUnlockedString.equals("true"))
                    cpuIsUnlocked = 1;
                int cpuHasIntegratedGraphicCard = 0;
                String cpuHasIntegratedGraphicCardString = data[8];
                if (cpuHasIntegratedGraphicCardString.equals("true"))
                    cpuHasIntegratedGraphicCard = 1;
                int cpuIsTheCpuCoolerIncluded = 0;
                String cpuIsTheCpuCoolerIncludedString = data[16];
                if (cpuIsTheCpuCoolerIncludedString.equals("true"))
                    cpuIsTheCpuCoolerIncluded = 1;
                String command = "INSERT INTO CPU VALUES ("+ i + ",'" + data[0] + "','" + data[1]+ "','" + data[2]+ "','" +data[3]+ "'," +Integer.parseInt(data[4])+ "," +Integer.parseInt(data[5])+ "," + Integer.parseInt(data[6])+ "," +
                        cpuIsUnlocked+ "," + cpuHasIntegratedGraphicCard+ "," + Double.parseDouble(data[9])+ "," + Double.parseDouble(data[10])+ "," + Double.parseDouble(data[11])+ "," + Double.parseDouble(data[12])+ "," +
                        Integer.parseInt(data[13])+ "," + Integer.parseInt(data[14])+ ",'" + data[15]+ "'," +
                        cpuIsTheCpuCoolerIncluded+ "," + Integer.parseInt(data[17])+ ",'" + data[18]+ "','" + data[19] + "')";
                try {
                    statement.executeUpdate(command);
                    
                } catch (SQLException e) {
                    System.out.println("Bad cpu data in line: " + i);
                }
                i++;
            }
            System.out.println("CPU data loaded.");
        }
        catch (IOException e) {}
    }

    /**
     * Loads CPU Cooler data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void cpuCoolerDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i =1;
        try {
            name = "cpuCooler.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[]data = line.split(";");
                    String command = "INSERT INTO CPUCOOLER VALUES ("+ i + ",'" + data[0] + "','" + data[1]+ "','" + data[2]+ "','" + data[3]+ "','" + data[4]+ "'," + Double.parseDouble(data[5])+ "," + Double.parseDouble(data[6])+ "," +
                            Double.parseDouble(data[7])+ "," + Integer.parseInt(data[8])+ "," + Double.parseDouble(data[9])+ "," + Integer.parseInt(data[10])+ "," + Integer.parseInt(data[11])+ "," +
                            Double.parseDouble(data[12])+ "," + Double.parseDouble(data[13])+ "," + Double.parseDouble(data[14])+ "," + Integer.parseInt(data[15])+ "," +
                            Integer.parseInt(data[16])+ "," + Integer.parseInt(data[17])+ "," + Integer.parseInt(data[18])+ "," + Integer.parseInt(data[19])+ ",'" +
                            data[20]+ "'," + Integer.parseInt(data[21])+ ",'" + data[22]+ "','" + data[23] +"')";
                try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad cpu cooler data in line: " + i);
                }
                i++;
            }
            System.out.println("CPU Cooler data loaded.");
        }
        catch (IOException e) {}
    }

    /**
     * Loads MOBO data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void moboDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i = 1;
        try {
            name = "mobo.txt";
            File file = new File(path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String command = "INSERT INTO MOBO VALUES (" + i + ",'" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3]
                        + "','" + data[4] + "','" + data[5] + "','" + data[6] + "'," + Integer.parseInt(data[7]) + "," + Integer.parseInt(data[8])
                        + "," + Integer.parseInt(data[9]) + ",'" + data[10] + "','" + data[11] + "',5," + Integer.parseInt(data[12]) + ",'" + data[13] + "','" + data[14] + "')";
                try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad MOBO data in line: " + i);
                }
                i++;
            }
            System.out.println("MOBO data loaded.");
        } catch (IOException e) {}
    }

    /**
     * Loads GPU data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void gpuDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i =1;
        try {
            name = "gpu.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[]data = line.split(";");
                String gpuIsCooledPassiveString = data[22];
                int gpuIsCooledPassive = 0;
                if (gpuIsCooledPassiveString.equals("true"))
                    gpuIsCooledPassive = 1;
                String gpuHasBackplateString = data[23];
                int gpuHasBackplate = 0;
                if (gpuHasBackplateString.equals("true"))
                    gpuHasBackplate = 1;
                String memorySizeClass="1";
                int memorySize = Integer.valueOf(data[9]);
                if (memorySize <= 1 || memorySize >=8) {
                    if (memorySize <=1)
                        memorySizeClass = "1";
                    if (memorySize >= 8)
                        memorySizeClass = "8";
                }
                else {
                    memorySizeClass = String.valueOf(memorySize);
                }

                String command = "INSERT INTO GPU VALUES ("+ i + ",'" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "','" + data[4] + "','" + data[5] + "'," + Double.parseDouble(data[6]) + "," +
                        Integer.parseInt(data[7]) + ",'" + data[8] + "'," + Integer.parseInt(data[9]) + ",'" + memorySizeClass + "','" + data[10] + "'," + Integer.parseInt(data[11]) + "," +
                        Integer.parseInt(data[12]) + "," + Double.parseDouble(data[13]) + "," + Integer.parseInt(data[14]) + "," + Integer.parseInt(data[15]) + "," +
                        Integer.parseInt(data[16]) + "," + Integer.parseInt(data[17]) + "," + Integer.parseInt(data[18]) + "," + Integer.parseInt(data[19]) + "," +
                        Integer.parseInt(data[20]) + "," + Integer.parseInt(data[21]) + "," + gpuIsCooledPassive + "," + gpuHasBackplate + "," + Integer.parseInt(data[24]) + ",'" + data[25] + "','" + data[26]+"')";
                try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad GPU data in line: " + i);
                }
                i++;
            }
            System.out.println("GPU data loaded.");
        }
        catch (IOException e) {}
    }

    /**
     * Loads RAM data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void ramDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i = 1;
        try {
            name = "ram.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[]data = line.split(";");
                int hasRadiator = 0;
                if (data[12] == "true")
                    hasRadiator = 1;
                int hasLighting = 0;
                if (data[13] == "true")
                    hasLighting = 1;
                String command = "INSERT INTO RAM VALUES (" + i + ",'" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "'," + Integer.parseInt(data[4]) + "," +
                        Integer.parseInt(data[5]) + "," + Integer.parseInt(data[6]) + ",'" + data[7] + "'," + Integer.parseInt(data[8]) + "," + Integer.parseInt(data[9]) + "," +
                        Double.parseDouble(data[10]) + "," +  Integer.parseInt(data[11]) + "," + hasRadiator + "," + hasLighting + "," + Integer.parseInt(data[14]) + ",'" + data[15] + "','" + data[16] + "')";
                try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad RAM data in line: " + i);
                }
                i++;
            }
            System.out.println("RAM data loaded.");
        } catch (IOException e) {}
    }

    /**
     * Loads SSD data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void ssdDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i = 1;
        try {
            name = "ssd.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[]data = line.split(";");
                int hasRadiator = 0;
                if (data[14] == "true")
                    hasRadiator = 1;
                String command = "INSERT INTO SSD VALUES (" + i + ",'" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "','" + data[4] + "','" +
                    data[5] + "'," + Integer.parseInt(data[6]) + "," + Integer.parseInt(data[7]) + "," + Integer.parseInt(data[8]) + ",'" + data[9] + "'," + Integer.parseInt(data[10]) + ",'" +
                data[11] + "','" + data[12] + "'," + Integer.parseInt(data[13]) + "," + hasRadiator + "," + Integer.parseInt(data[15]) + ",'" + data[16] + "','" + data[17] + "')";
                 try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad SSD data in line: " + i);
                }
                i++;
            }
            System.out.println("SSD data loaded.");
        } catch (IOException e) {}
    }

    /**
     * Loads HDD data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void hddDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i = 1;
        try {
            name = "hdd.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                String command = "INSERT INTO HDD VALUES (" + i + ",'" + data[0] + "','" + data[1] + "','" +
                    data[2] + "','" + data[3] + "','" + data[4] + "'," + Integer.parseInt(data[5]) + "," + Integer.parseInt(data[6]) + "," +
                    Integer.parseInt(data[7]) + "," + Integer.parseInt(data[8]) + "," + Integer.parseInt(data[9]) + "," +
                    Integer.parseInt(data[10]) + "," + Integer.parseInt(data[11]) + ",'" + data[12] + "','" + data[13] + "')";
                try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad HDD data in line: " + i);
                }
                i++;
            }
            System.out.println("HDD data loaded.");
        } catch (IOException e) {}
    }

    /**
     * Loads PSU data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void caseDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i = 1;
        try {
            name = "case.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String command = "INSERT INTO PCCASE VALUES (" + i + ",'" + data[0] + "','" + data[1] + "','" +
                    data[2] + "','" + data[3] + "','" + data[4] + "','" + data[5] + "'," + Double.parseDouble(data[6]) + "," +
                    Double.parseDouble(data[7]) + "," + Double.parseDouble(data[8]) + "," + Double.parseDouble(data[9]) + "," +
                    Double.parseDouble(data[10]) + "," + Integer.parseInt(data[11]) + "," + Integer.parseInt(data[12]) + "," +
                    Integer.parseInt(data[13]) + "," + Integer.parseInt(data[14]) + "," + Integer.parseInt(data[15]) + "," +
                    Integer.parseInt(data[16]) + "," + Integer.parseInt(data[17]) + "," + Integer.parseInt(data[18]) + "," +
                    Integer.parseInt(data[19]) + "," + Integer.parseInt(data[20]) + "," + Integer.parseInt(data[21]) + "," +
                    Integer.parseInt(data[22]) + "," + Integer.parseInt(data[23]) + ",'" + data[24] + "','" + data[25] + "')";

                try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad Case data in line: " + i);
                }
                i++;
            }
            System.out.println("Case data loaded.");
        } catch (IOException e) {}
    }

    /**
     * Loads Case data from txt file
     * @param statement statement
     * @throws IOException exception
     */
    private void psuDataInit(Statement statement) throws IOException {
        String line;
        String name = "";
        int i = 1;
        try {
            name = "psu.txt";
            File file = new File (path + name);
            InputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[]data = line.split(";");
                String psuIsModularString = data[9];
                int psuIsModular = 0;
                if (psuIsModularString.equals("true"))
                    psuIsModular = 1;

                String command = "INSERT INTO PSU VALUES (" + i + ",'" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "','" + data[4] + "','" + data[5] + "','" +
                        data[6] + "'," + Double.parseDouble(data[7]) + "," + Double.parseDouble(data[8]) + "," + psuIsModular + "," + Integer.parseInt(data[10]) + "," +
                        Integer.parseInt(data[11]) + ",'" + data[12] + "','" + data[13] + "')";

                try {
                    statement.executeUpdate(command);

                } catch (SQLException e) {
                    System.out.println("Bad PSU data in line: " + i);
                }
                i++;
            }
            System.out.println("PSU data loaded.");
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        CreateTablesAndFillItWithData mdb = new CreateTablesAndFillItWithData();
        mdb.insertData();
    }
}