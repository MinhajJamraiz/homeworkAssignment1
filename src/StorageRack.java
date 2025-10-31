import java.util.*;

//interface for StorageRack
interface Storage {
    void addParcel(Parcel parcel);
}
interface Removable extends Storage {
    void removeParcel(String parcelID);
    
}
//StorageRack implements both interfaces
class StorageRack implements Removable {
 // Attributes
 String rackID;
 List<StorageSlot> slots; //flexible number of slots(grow and shrink as needed at runtime)
 Location location; 

 // Constructor
    StorageRack(String rackID, int slotsCount) {
        this.rackID = rackID;
        this.slots = new ArrayList<>(); //initialize the list
        for (int i = 0; i <= slotsCount; i++) { //add provided slots to the list
            slots.add(new StorageSlot(rackID + "-S" + i)); //initialize slots with unique IDs
        }

    }

 //methods 1
 @Override
    public void addParcel(Parcel parcel) {
        for (StorageSlot slot : slots) {
            if (!slot.isOccupied) {
                slot.isOccupied = true; //mark slot as occupied
                System.out.println("Parcel " + parcel.parcelID + " added at Slot " + slot.slotID);
                return;
            }
        }
        System.out.println("No available slots in Rack " + rackID);
    }
 //methods 2
    @Override
    public void removeParcel(String parcelID) {
        for(StorageSlot slot : slots) {
            if (!slot.isOccupied){
                slot.isOccupied = false; //mark slot as free
                System.out.println("Parcel " + parcelID + " removed from Slot " + slot.slotID);
                return;
            }
        }
        System.out.println("Parcel " + parcelID + " not found in Rack " + rackID);

        
    }

}
