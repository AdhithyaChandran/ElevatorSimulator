package main.java.sg.dlt.elevator.component;

import main.java.sg.dlt.elevator.request.InternalRequest;

import java.util.*;


public class Processor implements Comparator<InternalRequest>{

    public PriorityQueue<InternalRequest> getRequests(int currentfloor){
        PriorityQueue<InternalRequest> Pqueue = new PriorityQueue<>(5,new Processor());
        Scanner sc = new Scanner(System.in);
        List<InternalRequest> internalRequests = new ArrayList();
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
                internalRequests.add(internalRequest);
                Pqueue.add(internalRequest);
            }
        }

       //return prioritisedRequests(Pqueue);
        return Pqueue;
    }


    //fot test
    public void getPriorityQueue(){

    }

    public Set<InternalRequest> prioritisedRequests(PriorityQueue internalRequests){
        Set<InternalRequest> prioritisedRequests = new LinkedHashSet<>();
        while(!internalRequests.isEmpty())
            prioritisedRequests.add((InternalRequest) internalRequests.remove());
        for(InternalRequest prioritisedRequest : prioritisedRequests)
            System.out.println(prioritisedRequest.getDestinationFloor());
        return  prioritisedRequests;
    }

    @Override

    public int compare(InternalRequest a, InternalRequest b) {
        if((a.getDestinationFloor()-a.getCurrentfloor())>(b.getDestinationFloor()-a.getCurrentfloor()))
            return 1;
        else if((a.getDestinationFloor()-a.getCurrentfloor())<(b.getDestinationFloor()-a.getCurrentfloor()))
            return-1;
        return 0;
    }

}
