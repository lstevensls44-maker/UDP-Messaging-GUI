import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MessageGUI {
	private JFrame frame;
	private JPanel basePanel;
	private JPanel messagePanel;
	private JScrollPane messageScrollPane;
	private JPanel inputPanel;
	private JTextField inputField;
	private JButton inputButton;
	
	MessageGUI(String frameName) {
		frame = new JFrame(frameName);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		
		
		messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		
		messageScrollPane = new JScrollPane(messagePanel);
		messageScrollPane.setPreferredSize(new Dimension(300, 300));
		
		basePanel.add(messageScrollPane, BorderLayout.NORTH);
		
		inputPanel = new JPanel();
		inputField = new JTextField("Enter Message");
		inputField.setPreferredSize(new Dimension(250, 40));
		String inputButtonString = """
				<html>
					<center>
						<p>Send</p>
						<p>Message</p>
					</center>
				</html>
				""";
		inputButton = new JButton(inputButtonString);
		inputButton.setPreferredSize(new Dimension(50, 40));
		inputButton.setMargin(new Insets(0,0,0,0));
		inputButton.setFont(new Font("Times New Roman", 0, 10));
		inputPanel.add(inputField);
		inputPanel.add(inputButton);
		
		basePanel.add(inputPanel, BorderLayout.SOUTH);
		
		frame.getContentPane().add(basePanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void close() {
		frame.dispose();
	}
	
	public JTextField getInputField() {
		return inputField;
	}
	
	public void displayMessage(String s) {
		messagePanel.add(new JLabel(s));
		messagePanel.validate();
		return;
	}
	
	public void attachListener(ActionListener a) {
		inputButton.addActionListener(a);
	}
}