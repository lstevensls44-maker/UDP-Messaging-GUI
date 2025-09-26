import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PortListener {
	private DatagramSocket rSocket;
	private DatagramPacket rPacket;
	private MessageGUI gui;
	private DatagramSocket messageEventSocket;
	
	boolean isListening = false;
	
	PortListener(int port, MessageGUI gui, DatagramSocket m) {
		try {
			rSocket = new DatagramSocket(port);
			rPacket = new DatagramPacket(new byte[1024], 1024);
			this.gui = gui;
			messageEventSocket = m;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listen() {
		try {
			isListening = true;
			String message;
			while(isListening) {
				rSocket.receive(rPacket);
				message = processPacket(rPacket);
				if(message.equals(""))
					isListening = false;
				gui.displayMessage(message);
			}
			gui.displayMessage("terminating...");
			rSocket.close();
			messageEventSocket.close();
			Thread.sleep(1500);
			gui.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String processPacket(DatagramPacket p) {
		String prefix = "Error";
		String message = "";
		try {
			prefix = p.getAddress().toString() + "/" + p.getPort();
			message = new String(p.getData(), p.getOffset(), p.getLength(), "UTF8");
			if(message.equals("")) {
				return "";
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return prefix + ": " + message;
	}
}