import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Arrays;

public class Login {

	public JFrame frame;
	private JTextField v1login;
	private JPasswordField v1pass;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(247, 247, 247));
		// frame.getContentPane().setBackground(SystemColor.controlLtHighlight);
		// frame.getContentPane().setFont(new Font("Sylfaen", Font.PLAIN, 30));
		frame.setBounds(160, 40, 1179, 781);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		
		
		JPanel pan = new JPanel();
		frame.getContentPane().add(pan);
		pan.setBounds(10, 10, 100, 80);
		DisplayImage(pan, "resize1.png");

		// #b1 7b 50
		// rgb(177, 123, 80)
		// rgb(165, 100, 52)
		// trajan
		// Sylfaean
		// Sitka
		// Seoge Script

		JLabel icon_label = new JLabel("Athilekh");
		icon_label.setForeground(new Color(165, 100, 52));
		icon_label.setFont(new Font("Segoe Script", Font.BOLD, 24));
		icon_label.setBounds(111, 36, 218, 43);
		frame.getContentPane().add(icon_label);

		// rgb(112, 181, 201)

		JPanel icon_divider = new JPanel();
		icon_divider.setBackground(new Color(112, 181, 201));
		icon_divider.setBounds(0, 100, 1175, 5);
		frame.getContentPane().add(icon_divider);
		
		
		/*loginPan = new JPanel();
		loginPan.setBounds(38, 349, 817, 245);
		loginPan.add(loginPan);
		loginPan.setLayout(null);
		loginPan.setBackground(new Color(247, 247, 247));
		LineBorder border = new LineBorder(new Color(255, 255, 255), 5);
		loginPan.setBorder(border);*/
		
		JPanel loginPan = new JPanel();
		loginPan.setBounds(158, 289, 817, 245);
		frame.getContentPane().add(loginPan);
		loginPan.setLayout(null);
		loginPan.setBackground(new Color(247, 247, 247));
		LineBorder border = new LineBorder(new Color(255, 255, 255), 5);
		loginPan.setBorder(border);
		
		JLabel l1login = new JLabel("LOGIN:");
		l1login.setBounds(257, 94, 112, 32);
		loginPan.add(l1login);
		l1login.setForeground(new Color(0, 36, 71));
		l1login.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
		
		
		
		
		v1login = new JTextField();
		v1login.setBounds(379, 94, 197, 28);
		loginPan.add(v1login);
		
		v1pass = new JPasswordField();
		v1pass.setBounds(379, 124, 197, 28);
		loginPan.add(v1pass);
		
		JLabel l1pass = new JLabel("PASSWORD:");
		l1pass.setBounds(257, 124, 112, 32);
		loginPan.add(l1pass);
		l1pass.setForeground(new Color(0, 36, 71));
		l1pass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
		JButton bsubmit = new JButton("SUBMIT");
		bsubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ResultSet r = DBConnect.getInstance().runFetchQuery("SELECT * from Foreigner LEFT JOIN Login using (foreigner_ID) where foreigner_ID='"+v1login.getText()+"' and password='"+myAccount.getHash(Arrays.toString(v1pass.getPassword()))+"';");
				try {
					while(r.next()) {
					
						myAccount window = new myAccount(v1login.getText());
						window.frame.setVisible(true);
						frame.dispose();
					
					}
				}catch(Exception p) {
					p.printStackTrace();
				}
				
				
			}
		});
		
		
		bsubmit.setForeground(Color.LIGHT_GRAY);
		bsubmit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		bsubmit.setBounds(610, 170, 167, 42);
		bsubmit.setBackground(new Color(0, 36, 71));
		loginPan.add(bsubmit);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(231,231,231));
		panel.setBounds(0, 105, 1175, 645);
		frame.getContentPane().add(panel);
		
		
		
		

	}
	private void DisplayImage(JPanel jp, String url) {

		JLabel jl = new JLabel();
		jl.setBackground(SystemColor.inactiveCaptionBorder);
		ImageIcon i = new ImageIcon(getClass().getResource(url));
		// i = new ImageIcon(i.getImage().getScaledInstance(jp.getWidth(),
		// jp.getHeight(), Image.SCALE_SMOOTH));
		jl.setIcon(i);
		jp.add(jl);

	}
}
