package main.java.sg.dlt.elevator;

import main.java.sg.dlt.elevator.component.Car;
import main.java.sg.dlt.elevator.request.InternalRequest;
import java.util.PriorityQueue;


public class Elevator {

    private int sourcefloor;

    public int getSourcefloor() {
        return sourcefloor;
    }

    public void setSourcefloor(int sourcefloor) {
        this.sourcefloor = sourcefloor;
    }

    public void start(){
        System.out.println("Elevator Starts...");
        Car car = new Car();
        car.start();
}
}
