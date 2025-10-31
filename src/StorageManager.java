import java.util.*;

abstract class StorageManager {
    List<StorageRack> racks; //list of racks
    Map<Parcel, Location> parcelLocations; // Map to track every parcel locations
    // Constructor
    StorageManager() {
        racks = new ArrayList<>();
        parcelLocations = new HashMap<>();
    }

    // Abstract Methods, will be implemented in subclasses
     abstract void storeParcel(Parcel parcel);
     abstract Parcel retrieveParcel(String parcelID);
     abstract Location findAvailableSlot();
}

//child class for creating methods
class WarehouseStorageManager extends StorageManager {
    // Implement abstract methods
    @Override
    void storeParcel(Parcel parcel) {
        System.out.println("Storing parcel: " + parcel.parcelID);

        // find an available slot
        Location freeLoc = findAvailableSlot();
        if (freeLoc != null) {
            // mark slot as occupied
            freeLoc.slot.isOccupied = true;
            // store parcel to that location
            parcelLocations.put(parcel, freeLoc);

            String message = "Parcel " + parcel.parcelID + " stored in Rack " + freeLoc.rack.rackID + " at slot " + freeLoc.slot.slotID;
            System.out.println(message);
        
            //log file entry
            LogManager.writeLog(message);
        
        } else {
            System.out.println("No empty slots available to store the parcel.");
        }
       
    }

    @Override
   Parcel retrieveParcel(String parcelID) {
        // check each parcel in the map
        for (Parcel p : parcelLocations.keySet()) {
            if (p.parcelID.equals(parcelID)) {
                //get location 
                Location loc = parcelLocations.get(p);
                //free slot
                loc.slot.isOccupied = false;
                //remove from map
                parcelLocations.remove(p);

                String message = ("Parcel " + parcelID + " retrieved from Rack " + loc.rack.rackID + " from Slot " + loc.slot.slotID);
                System.out.println(message);

                //log file entry
                LogManager.writeLog(message);

                return p;
        
            }
        }
        System.out.println("Parcel not found!");
        return null;
    }

    @Override
    Location findAvailableSlot() {
        //search each rack
        for (StorageRack rack : racks) {
            //search each slot in the rack
            for (StorageSlot slot : rack.slots) {
                if (!slot.isOccupied) {
                    //found empty slot, return its location
                    return new Location(rack, slot);
                }
            }
        }
    //no empty slots found
    return null;
    }
}
