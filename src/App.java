public class App {
	public static void main(String[] args) {
		int[] listeningPorts = new int[] {8000, 8002};
		Node n0 = new Node(8000, 8001, listeningPorts);
		new Thread(n0).start();
		Node n1 = new Node(8002, 8003, listeningPorts);
		new Thread(n1).start();
	}
}