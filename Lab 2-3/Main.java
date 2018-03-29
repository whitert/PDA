package test1;

import java.util.LinkedList;

public class Main {
	 public static void main(String args[]) {
		 LinkedList<Integer> sharedQueue = new LinkedList<Integer>(); //Creating shared object
	      
	       Producer producer=new Producer(sharedQueue);
	       Consumer consumer=new Consumer(sharedQueue);
	      
	        Thread producerThread = new Thread(producer, "ProducerThread");
	        Thread consumerThread = new Thread(consumer, "ConsumerThread");
	        producerThread.start();
	        consumerThread.start();
	        try {
				producerThread.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
				consumerThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}