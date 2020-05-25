import java.util.ArrayList;
import java.util.Scanner;

public class TruckManagement {
    public static ArrayList<Truck> mainQueue = new ArrayList();
    public static ArrayList<Truck> queue1 = new ArrayList();
    public static ArrayList<Truck> queue2 = new ArrayList();

    public static void main(String[] args) {
        while(true){
            //go further with one unit of time
            try{
                step();
            }
            catch (Exception e){
                System.out.println("The program has been stopped.");
                System.out.println(e);
                break;
            }
            //print actual status of queue
            status();

            //decrement waiting time for each truck in each queueu
            for(Truck t : queue1){
                t.waitingTime --;
            }
            for(Truck t : queue2){
                t.waitingTime --;
            }
            for(Truck t : mainQueue){
                t.waitingTime --;
            }
        }

    }
    public static void arrive(int weight){
        Truck truck = new Truck(weight);
        //set proper waiting time for an arriving truck
        if(mainQueue.isEmpty() && !queue1.isEmpty() && !queue2.isEmpty()){
            if(queue1.get(queue1.size() - 1).waitingTime < queue2.get(queue2.size() - 1).waitingTime){
                truck.waitingTime = queue1.get(queue1.size() - 1).waitingTime + truck.getWeight();
            }
            else{
                truck.waitingTime = queue1.get(queue1.size() - 1).waitingTime + truck.getWeight();
            }
        }
        else if(mainQueue.isEmpty() && (queue1.isEmpty() || queue2.isEmpty())){
            truck.waitingTime = truck.getWeight();
        }
        else{
            truck.waitingTime = mainQueue.get(mainQueue.size() - 1).waitingTime + truck.getWeight();
        }
        //add truck to main queue
        mainQueue.add(truck);
    }

    public static int getTimeById(int truckId){
        //check if there is a truck with the entered id in queues
        //if yes, return waiting time for the truck
        for(Truck truck : queue1){
            if(truck.getTruckId() == truckId) {
                return truck.waitingTime;
            }
        }
        for(Truck truck : queue2){
            if(truck.getTruckId() == truckId) {
                return truck.waitingTime;
            }
        }
        for(Truck truck : mainQueue){
            if(truck.getTruckId() == truckId) {
                return truck.waitingTime;
            }
        }
        return -1;

    }

    public static void status(){
        System.out.println("---------- Customs clearance status ----------");
        if(queue1.isEmpty()){
            System.out.println("The first queue is empty");
        }
        else{
            int numberOfTrucks1 = queue1.size();
            int time1 = queue1.get(numberOfTrucks1 - 1).waitingTime;
            System.out.println("Number of cars in the first queue: " + numberOfTrucks1);
            System.out.println("Waiting time in the first queue: " + time1);
            for(Truck t : queue1){
                System.out.println("TruckId: " + t.getTruckId() + " ---> Waiting time: " + t.waitingTime);
            }
        }
        if(queue2.isEmpty()){
            System.out.println("The second queue is empty");
        }
        else{
            int numberOfTrucks2 = queue2.size();
            int time2 = queue2.get(numberOfTrucks2 - 1).waitingTime;
            System.out.println("Number of cars in the second queue: " + numberOfTrucks2);
            System.out.println("Waiting time in the second queue: " + time2);
            for(Truck t : queue2){
                System.out.println("TruckId: " + t.getTruckId() + " ---> Waiting time: " + t.waitingTime);
            }
        }
    }

    public static void step(){
        System.out.println("Enter weight of a truck or press \'0\' to check waiting time for a chosen truck");
        Scanner scanner = new Scanner(System.in);
        int input;
        try{
            input = scanner.nextInt();
            if(input == 0){
                System.out.println("Enter the chosen trunk's id: ");
                int id = scanner.nextInt();
                int time = getTimeById(id);
                if(time == -1){
                    System.out.println("There isn't any truck with such id in a queue");
                }
                else{
                    System.out.println("Waiting time for truck with id " + id + " is " + time);
                }
                System.out.println("Enter weight of a truck");
                input = scanner.nextInt();
            }
            if(input < 0)
                throw new IllegalArgumentException("Weight must be greater than 0!");
            arrive(input);
        }
        catch (Exception e){
            throw e;
        }
        //if both queues are empty add new truck to first queue, set proper waiting time and remove it from main queue
        if(queue1.isEmpty() && queue2.isEmpty()){
            queue1.add(mainQueue.get(0));
            Truck addedTruck = queue1.get(0);
            addedTruck.waitingTime = addedTruck.getWeight();
            mainQueue.remove(0);
        }
        else{
            //if only one queue is empty, add new truck to the second one
            if (queue1.isEmpty() && !queue2.isEmpty()){
                queue1.add(mainQueue.get(0));
                Truck addedTruck = queue1.get(0);
                addedTruck.waitingTime = addedTruck.getWeight();

            }
            else if(!queue1.isEmpty() && queue2.isEmpty()){
                queue2.add(mainQueue.get(0));
                Truck addedTruck = queue2.get(0);
                addedTruck.waitingTime = addedTruck.getWeight();

            }
            else{
                //if both queues aren't empty and aren't full, add new truck to the queue with shorter waiting time
                if(queue1.size() < 5 && queue2.size() < 5){
                    if(queue1.get(queue1.size() - 1).waitingTime > queue2.get(queue2.size() - 1).waitingTime){
                        queue2.add(mainQueue.get(0));
                        Truck addedTruck = queue2.get(queue2.size() - 1);
                        addedTruck.waitingTime = queue2.get(queue2.size() - 2).waitingTime + addedTruck.getWeight();
                    }

                    else{
                        queue1.add(mainQueue.get(0));
                        Truck addedTruck = queue1.get(queue1.size() - 1);
                        addedTruck.waitingTime = queue1.get(queue1.size() - 2).waitingTime + addedTruck.getWeight();
                    }

                }
                //if one of the queue is full, but second one isn't full, add new truck to the second one
                else if(queue1.size() < 5 && queue2.size() == 5){
                    queue1.add(mainQueue.get(0));
                    Truck addedTruck = queue1.get(queue1.size() - 1);
                    addedTruck.waitingTime = queue1.get(queue1.size() - 2).waitingTime + addedTruck.getWeight();
                }
                else if(queue1.size() == 5 && queue2.size() < 5){
                    queue2.add(mainQueue.get(0));
                    Truck addedTruck = queue2.get(queue2.size() - 1);
                    addedTruck.waitingTime = queue2.get(queue2.size() - 2).waitingTime + addedTruck.getWeight();
                }
            }
            //if waiting time for a first truck is 0, remove it
            if(queue1.get(0).waitingTime == 0){
                queue1.remove(0);
            }
            if(queue2.get(0).waitingTime == 0){
                queue2.remove(0);
            }
            //the first truck from main queue has been added to first or second queue
            //remove the truck from main queue
            mainQueue.remove(0);
        }

    }

}
