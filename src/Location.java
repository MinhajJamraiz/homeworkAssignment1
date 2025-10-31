public class Location {  //this id needed to track parcel locations
    // Attributes
    StorageRack rack;
    StorageSlot slot;

    // Constructor
    Location(StorageRack rack, StorageSlot slot) {
        this.rack = rack;
        this.slot = slot;
    }
}
