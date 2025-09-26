public class Node implements Runnable {
	int receivePort;
	
	int sendPort;
	
	int[] destinations;
	
	Node() {
		this(8000, 8001, new int[] {8000, 8002});
	}
	Node(int rP, int sP, int[] d) {
		receivePort = rP;
		sendPort = sP;
		destinations = d;
	}
	
	public void run() {
		try {
			//GUI object
			MessageGUI gui = new MessageGUI("Port: " + String.valueOf(sendPort));
			
			//MessageEvent will send packets to certain ports when a GUI button is pressed
			MessageEvent me = new MessageEvent(sendPort, destinations, gui.getInputField());
			gui.attachListener(me);
			
			//For receiving packets
			PortListener pl = new PortListener(receivePort, gui, me.getSocket());
			pl.listen();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}