package main.java.sg.dlt.elevator.component;

import main.java.sg.dlt.elevator.request.InternalRequest;

import java.util.*;


public class Processor implements Comparator<InternalRequest>{

    public PriorityQueue<InternalRequest> getRequests(int currentfloor){
        PriorityQueue<InternalRequest> Pqueue = new PriorityQueue<>(5,new Processor());
        Scanner sc = new Scanner(System.in);
        boolean inputFlag = true;
        int destinationfloor;

        System.out.println("Enter destination floors ,Enter -1 as end of inputs");
        while(inputFlag)
        {
            destinationfloor = sc.nextInt();
            if(destinationfloor == -1)
                inputFlag = false;
            else{
                InternalRequest internalRequest = new InternalRequest(currentfloor, destinationfloor, (int) System.nanoTime());
                Pqueue.add(internalRequest);
            }
        }
        return Pqueue;
    }



    @Override

    public int compare(InternalRequest a, InternalRequest b) {
        if((a.getDestinationFloor()-a.getCurrentfloor())>(b.getDestinationFloor()-a.getCurrentfloor()))
            return 1;
        else if((a.getDestinationFloor()-a.getCurrentfloor())<(b.getDestinationFloor()-a.getCurrentfloor()))
            return-1;
        return 0;
    }

    public void decideFloor(){

    }

}
