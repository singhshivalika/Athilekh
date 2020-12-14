import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class officialAccount {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	static HashMap<String,String> category = new HashMap<String,String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					officialAccount window = new officialAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public officialAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(247, 247, 247));
		//frame.getContentPane().setBackground(SystemColor.controlLtHighlight);
		//frame.getContentPane().setFont(new Font("Sylfaen", Font.PLAIN, 30));
		frame.setBounds(160, 40, 1179, 781);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel pan = new JPanel();
		frame.getContentPane().add(pan);
		pan.setBounds(10, 10, 100, 80);
		DisplayImage(pan, "resize1.png");
		JLabel icon_label = new JLabel("Athilekh");
		icon_label.setForeground(new Color(165, 100, 52));
		icon_label.setFont(new Font("Segoe Script", Font.BOLD, 24));
		icon_label.setBounds(111, 36, 218, 43);
		frame.getContentPane().add(icon_label);
		
		//rgb(112, 181, 201)
		
		JPanel icon_divider = new JPanel();
		icon_divider.setBackground(new Color(112, 181, 201));
		icon_divider.setBounds(0, 100, 1175, 5);
		frame.getContentPane().add(icon_divider);
		
		JButton logout_button = new JButton("LOGOUT");
		logout_button.setForeground(new Color(0, 0, 0));
		logout_button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		logout_button.setBounds(1069, 67, 85, 21);
		frame.getContentPane().add(logout_button);
		
		JLabel l1sortBy = new JLabel("SORT BY:");
		l1sortBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l1sortBy.setBounds(54, 187, 208, 31);
		frame.getContentPane().add(l1sortBy);
	
		JComboBox dropBox = new JComboBox(category.keySet().toArray());
		dropBox.setBounds(597, 158, 189, 28);
		//dropBox.setSelectedItem(f_religionName);
		pan.add(dropBox);
		
	}
	private void DisplayImage(JPanel jp, String url) {
		
	    JLabel jl=new JLabel();
	    jl.setBackground(SystemColor.inactiveCaptionBorder);
	    ImageIcon i = new ImageIcon(getClass().getResource(url));
	    //i = new ImageIcon(i.getImage().getScaledInstance(jp.getWidth(), jp.getHeight(), Image.SCALE_SMOOTH));
	    jl.setIcon(i);
	    jp.add(jl);
	    
	}
}
