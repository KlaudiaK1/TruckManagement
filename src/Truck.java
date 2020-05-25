public class Truck {
    private int weight;
    private int truckId;
    public static int counter = 1;
    public int waitingTime = 0;

    public Truck(int weight){
        this.weight = weight;
        this.truckId = counter;
        this.counter ++;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

}
