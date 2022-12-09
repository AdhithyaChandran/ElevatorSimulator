package main.java.sg.dlt.elevator.component;

import main.java.sg.dlt.elevator.request.InternalRequest;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Car {

    private int currentfloor;

    private int overloadFlag;

    public int getOverloadFlag() {
        return overloadFlag;
    }

    public void setOverloadFlag(int overloadFlag) {
        this.overloadFlag = overloadFlag;
    }

    private Processor processor;

    private  Display display;

    public int getCurrentfloor() {
        return currentfloor;
    }

    public void setCurrentFloor(int currentfloor) {
        this.currentfloor = currentfloor;
    }

    public Car(int currentFloor) {
        this.currentfloor = currentFloor;
        processor = new Processor();
    }

    public Car() {
        this.currentfloor = 3;
        processor = new Processor();
        display = new Display();
    }

    public Processor getProcessor(){
        return processor;
    }

    public void open(){
        System.out.println("Door Opens...");
        if(getOverloadFlag()==1){
            loadCheck(generateRandomLoad());
        }
        try{
        Thread.sleep(3000);
        }catch(InterruptedException e){
            System.out.println("Exception : "+e);
        }
    }
    public void close(){
        System.out.println("Door Closes...");
    }

    public int generateRandomLoad(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    public void loadCheck(int load){
        if( generateRandomLoad() > 1050){
            System.out.println("Overload...");
            setOverloadFlag(1);
            open();
        }
        else
            close();

    }

    public void moveTo(PriorityQueue<InternalRequest> internalRequests){
        Scanner sc = new Scanner(System.in);
        /*for(InternalRequest internalRequest : internalRequests){
            while(getCurrentfloor()<internalRequest.getDestinationFloor()){
                internalRequest.setCurrentfloor(internalRequest.getCurrentfloor()+1);
                setCurrentFloor(internalRequest.getCurrentfloor());
                display.display(getCurrentfloor());

            }
            while(getCurrentfloor()>internalRequest.getDestinationFloor()){
                internalRequest.setCurrentfloor(internalRequest.getCurrentfloor()-1);
                setCurrentFloor(internalRequest.getCurrentfloor());
                display.display(getCurrentfloor());
            }
            if(getCurrentfloor()==internalRequest.getDestinationFloor()) {
                System.out.println("Destination floor :" + internalRequest.getDestinationFloor());
                System.out.println("Is there any internal input (Y/N)");

            }
        }*/
        while(!internalRequests.isEmpty()) {
            InternalRequest internalRequest = internalRequests.remove();
            while (getCurrentfloor() < internalRequest.getDestinationFloor()) {
                internalRequest.setCurrentfloor(internalRequest.getCurrentfloor() + 1);
                setCurrentFloor(internalRequest.getCurrentfloor());
                display.display(getCurrentfloor());

            }
            while (getCurrentfloor() > internalRequest.getDestinationFloor()) {
                internalRequest.setCurrentfloor(internalRequest.getCurrentfloor() - 1);
                setCurrentFloor(internalRequest.getCurrentfloor());
                display.display(getCurrentfloor());
            }
            if (getCurrentfloor() == internalRequest.getDestinationFloor()) {
                System.out.println("Destination floor :" + internalRequest.getDestinationFloor());
                System.out.println("Is there any internal input (Y/N)");

                if(sc.next().equalsIgnoreCase("y")){
                    System.out.println("Enter destination floors ,Enter -1 as end of inputs");
                    int input =sc.nextInt();
                    while(input!=-1){
                        InternalRequest internalRequestInput = new InternalRequest(currentfloor, input, (int) System.nanoTime());
                        internalRequests.add(internalRequestInput);
                        input =sc.nextInt();
                    }
                }



            }


        }

    }

    public void start(){
        open();
        int load = generateRandomLoad();
        loadCheck(load);
        PriorityQueue<InternalRequest> internalRequests = getProcessor().getRequests(getCurrentfloor());
        moveTo(internalRequests);
    }
}
