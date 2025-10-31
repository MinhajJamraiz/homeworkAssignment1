import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class LogManager {

    //create folder for all log files
    private static final String LOG_FOLDER = "logs";
    private String filename;
    private String filenamebin;    
    private static File folder;
    //method creates folder if not present 
    public  void createLogFolder() {
        folder = new File(LOG_FOLDER);
        if (!folder.exists()) {
            folder.mkdir(); // create folder
            System.out.println("Log folder created: " + LOG_FOLDER);
        }
    }
    public void CreateLogFile(String logTarget) {
    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // create file name with date
        filename = LOG_FOLDER + "/"+logTarget +"_" +date + ".txt";
        filenamebin = LOG_FOLDER + "/"+logTarget+"_" + date + ".bin";
    }
    //method to write messages to a log file 
    public void writeLog(String message) {
       // createLogFolder(); // make sure log folder is there

        //get date
        
        // append message to file instead of overwriting old logs
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(message + "\n" );
            System.out.println("Message saved to log file");
        } catch (IOException e) {
            System.err.println("Error writing log: " + e.getMessage());
        }
    }
    // Byte stream - write log
    public  void writeLogBytes(String message) {
        //createLogFolder();

        try (FileOutputStream out = new FileOutputStream(filenamebin, true)) {
            out.write((message + "\n").getBytes()); // convert String to bytes
            System.out.println("Message saved to log file (byte stream)");
        } catch (IOException e) {
            System.err.println("Error writing log bytes: " + e.getMessage());
        }
    }

    //method to read and print log file 
public  void readLog(String date) {
        //use pattern
        String filename = LOG_FOLDER + "/log_" + date + ".txt";
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Log file for " + date + " does not exist.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Log file for " + date + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading log: " + e.getMessage());
        }
    }
// Byte stream - read log
public  void readLogBytes(String date) {
        //user regex
        String filename = LOG_FOLDER + "/Storage" + date + ".bin";
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Binary log file for " + date + " does not exist.");
            return;
        }

        try (FileInputStream in = new FileInputStream(filename)) {
            System.out.println("Binary log file for " + date + " (byte stream):");
            int b;
            while ((b = in.read()) != -1) {
                System.out.print((char) b); // convert byte back to char for printing
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error reading log bytes: " + e.getMessage());
        }
    }
    //delete log file
    public static void deleteLog(String date) {
        // use pattern
        String filename = LOG_FOLDER + "/log_" + date + ".txt";
        File file = new File(filename);
        if (file.delete()) {
            System.out.println("Log file deleted ");
        } else {
            System.out.println("Failed to delete log file");
        }
    }

    //move log file to archive folder
    public static void archiveLog(String date) {
        // Use pattern
        String filename = LOG_FOLDER + "/log_" + date + ".txt";
        File oldFile = new File(filename);
        File archiveFolder = new File(LOG_FOLDER + "/archive");
            archiveFolder.mkdir(); // create archive folder if not present
        
        File newFile = new File(archiveFolder, "log_" + date + ".txt");
        if (oldFile.renameTo(newFile)) {
            System.out.println("Log file archived successfully.");
        } else {
            System.out.println("Failed to archive log file.");
        }
    }
    public void listFilesByPattern(String filePattern) {

        Pattern pattern = Pattern.compile(filePattern);

        String[] matchingFiles = folder.list((dir, name) -> pattern.matcher(name).matches());

        if (matchingFiles == null || matchingFiles.length == 0) {
            System.out.println("No files matching the pattern found.");
            return;
        }

        System.out.println("Files matching pattern '" + filePattern + "':");
        for (String filename : matchingFiles) {
            System.out.println(filename);
        }
    }
    
}

