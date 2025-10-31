//import java.time.LocalTime;

public class Main {
    public static void main(String[] args){
        WarehouseStorageManager manager = new WarehouseStorageManager();
        //Create some racks and add to manager
        StorageRack rack1 = new StorageRack("R1", 5);
        StorageRack rack2 = new StorageRack("R2", 5);
        manager.racks.add(rack1);
        manager.racks.add(rack2);
        //Create some parcels
        Parcel parcel1 = new Parcel("P1", 2.5, "New York",  1);
        Parcel parcel2 = new Parcel("P2", 1.0, "Los Angeles", 2);
    
        //testing StorageManager methods
        //Store parcels
        manager.storeParcel(parcel1);
        manager.storeParcel(parcel2);
        //Retrieve a parcel
        manager.retrieveParcel("P1");

        //attempt to retrieve a non-existing parcel
        manager.retrieveParcel("P3");

        //Test StorageRack methods directly
        //remove a parcel directly from rack
        rack2.removeParcel("P2");
        //add parcel
        rack2.addParcel(parcel2);
        TaskManager task_manager = new TaskManager();
        task_manager.createloadTask("FR-5000", 1);
        task_manager.createUnLoadTask(2);
        task_manager.processPendingTasks();

        //read today's log
        //LogManager.readLog("2025-10-29");
        LogManager Reading_log;
        Reading_log = new LogManager();
        Reading_log.createLogFolder();
        Reading_log.readLogBytes("2025-10-29");
        // List all Task logs
        Reading_log.listFilesByPattern("Task_.*\\.txt");

        // List all logs
        Reading_log.listFilesByPattern(".*\\.txt");
        //delete today's log
        // LogManager.deleteLog("2025-10-29");
        // String today =(LocalTime.now().toString());
        // LogManager.writeLog("System Started");
        // LogManager.readLog(today);
    }
}
