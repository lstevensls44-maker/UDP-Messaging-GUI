import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JTextField;

public class MessageEvent implements ActionListener {
	private byte buf[];
	private DatagramSocket sSocket;
	private DatagramPacket sPacket;
	private int[] destinations;
	private JTextField input;
	
	MessageEvent(int sPort, int[] destinations, JTextField input) {
		try {
			buf = "test".getBytes("UTF8");
			sSocket = new DatagramSocket(sPort);
			sPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 9999);
			this.destinations = destinations;
			this.input = input;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			buf = input.getText().getBytes("UTF8");
			sPacket.setData(buf);
			sPacket.setLength(buf.length);
			for(int dest: destinations) {
				sPacket.setPort(dest);
				sSocket.send(sPacket);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void closeSocket() {
		sSocket.close();
	}

}