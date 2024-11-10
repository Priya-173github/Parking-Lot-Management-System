public class Main {
    public static void main(String[] args) {
        int nfloors = 4;
        int nSotsPerFloor = 6;
        ParkingLot parkingLot = new ParkingLot("PR1234", nfloors, nSotsPerFloor);
        System.out.println("No of open slots for car: " + parkingLot.getNoOfOpenSlots("car"));

        String ticket1 = parkingLot.parkVehicle("car", "MH-03", "red");
        String ticket2 = parkingLot.parkVehicle("car", "MH-04", "purple");

        System.out.println("Ticket1 : " + ticket1);
        System.out.println("Ticket2 : " + ticket2);

        System.out.println("After parking");
        parkingLot.DisplayOccupiedSlots("car");

        parkingLot.unPark(ticket2);
        parkingLot.DisplayOccupiedSlots("car");

        parkingLot.DisplayOpenSlots("truck");
        parkingLot.parkVehicle("truck", "TN-10-2352", "black");
        parkingLot.parkVehicle("truck", "MH-50-2599", "black");
        parkingLot.parkVehicle("truck", "DD-01-7007", "yellow");

        System.out.println();

        parkingLot.DisplayOccupiedSlots("truck");
        System.out.println("After parking 4th truck");
        parkingLot.parkVehicle("truck", "MH-50-2599", "black");
        parkingLot.parkVehicle("truck", "MH-20-2500", "black");

        System.out.println();
        parkingLot.moveVehicle(ticket1, 4, 4);


    }
}
