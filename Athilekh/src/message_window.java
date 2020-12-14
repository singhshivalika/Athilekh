import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class message_window {

	private JFrame frame;
	String message;
	
	public message_window(String message) {
		this.message = message;
		initialize();
		this.frame.setVisible(true);
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
		frame = new JFrame();
		frame.setBounds(200, 300, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel msg = new JLabel(message);
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setBounds(205, 182, 142, 30);
		frame.getContentPane().add(msg);
		
	}
	
}
