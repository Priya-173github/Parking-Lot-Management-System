import java.util.*;

// CRUD
public class ParkingLot {
    String parkingLotId;
    List<List<Slot>> slots;

    ParkingLot(String parkingLotId, int nfloors, int noofslotsperfloor) {
        this.parkingLotId = parkingLotId;
        slots = new ArrayList<>();
        for (int i = 0; i < nfloors; i++) {
            slots.add(new ArrayList<>());
            List<Slot> floorSlot = slots.get(i);
            floorSlot.add(new Slot("truck"));
            floorSlot.add(new Slot("bike"));
            floorSlot.add(new Slot("bike"));

            for (int j = 3; j < noofslotsperfloor; j++) {
                slots.get(i).add(new Slot("car"));
            }
        }
    }

    String parkVehicle(String type, String regNo, String color) { // Create
        Vehicle vehicle = new Vehicle(type, regNo, color);

        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.type == type && slot.vehicle == null) {
                    slot.vehicle = vehicle;
                    slot.ticketId = generateTicketId(i + 1, j + 1);
                    return slot.ticketId;
                }
            }
        }
        System.out.println("Slot not available for given type of vehicle");
        return null;
    }

    void unPark(String ticketId) { // Delete
        String[] extract = ticketId.split("_");
        int flr_index = Integer.parseInt(extract[1]) - 1;
        int slot_index = Integer.parseInt(extract[2]) - 1;
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                if (i == flr_index && j == slot_index) {
                    Slot slot = slots.get(i).get(j);
                    slot.vehicle = null;
                    slot.ticketId = null;
                    System.out.println("Vehicle Unparked");
                }
            }
        }
    }

    private String generateTicketId(int flr, int slno) {
        return parkingLotId + "_" + flr + "_" + slno;
    }

    int getNoOfOpenSlots(String type) { // Read
        int n = 0;
        for (List<Slot> floor : slots) {
            for (Slot slot : floor) {
                if (slot.vehicle == null && slot.type.equals(type)) {
                    n++;
                }
            }
        }

        return n;
    }

    void DisplayOpenSlots(String type) { // Read
        System.out.println("Available slots for " + type);
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.vehicle == null && slot.type.equals(type)) {
                    System.out.println("Floor: " + (i + 1) + " Slot: " + (j + 1));
                }
            }
        }
    }

    void DisplayOccupiedSlots(String type) { // Read
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.vehicle != null && slot.type.equals(type)) {
                    System.out.println("Floor: " + (i + 1) + " Slot no: " + (j + 1));
                }
            }
        }
    }

    // moveVehicle("MH-01-2920", 2, 4)
    void moveVehicle(String ticketId, int newFloor, int newSlot) { // Update
        String[] extract = ticketId.split("_");
        int flr = Integer.parseInt(extract[1]) - 1;
        int slot_idx = Integer.parseInt(extract[2]) - 1;

        if (newFloor >= 1 && newFloor <= slots.size() && newSlot >= 1 && newSlot <= slots.get(newFloor - 1).size()) {
            Slot newSlotObj = slots.get(newFloor - 1).get(newSlot - 1);
            if (newSlotObj.vehicle == null) {
                Slot oldSlot = slots.get(flr).get(slot_idx);
                newSlotObj.vehicle = oldSlot.vehicle;
                newSlotObj.ticketId = oldSlot.ticketId;
                oldSlot.vehicle = null;
                oldSlot.ticketId = null;
                System.out.println("Vehicle moved to slot " + newSlot + " and floor " + newFloor);
            }

            else {
                System.out.println("New slot is already occupied");
            }
        } else {
            System.out.println("Invalid new slot");
        }
    }
}