package test1;

import java.util.LinkedList;


class Consumer implements Runnable {
	private LinkedList<Integer> sharedQueue;
	private int product;

	public Consumer(LinkedList<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (sharedQueue) {
				while (sharedQueue.size() == 0) {
					System.out.println("Queue is empty " + "");

					try {
						sharedQueue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				product = sharedQueue.pop();
				sharedQueue.notify();
			}
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("CONSUMED: " + product);
		}
	}
}