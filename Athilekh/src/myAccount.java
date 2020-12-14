import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.font.TextAttribute;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;



public class myAccount {

	private JFrame frame;
	private String resize2;
	private JLabel daysLabel;
	private JTextField v2gender;
	private JTextField v2phone;
	private JTextField v2blood;
	private JTextField v2email;
	private JTextField v2dOB;
	private JTextField v2name;
	private JTextField v2newPass;
	private JTextField v2oldPass;
	private JTextField v2ID;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_24;
	private JTextField v3duratn;
	private JTextField textField_26;
	
	
	
	public static String f_name;
	public static String f_ID, f_fullName, f_passNo, f_countryName, f_phoneNo, f_gender, f_address, f_dOB, f_marrital, f_email, f_blood, f_religionName;
	private static String f_addBuildNo, f_addStreetNo, f_addCity, f_addState;
	private JTextField v2streetName;
	private JTextField v2cityName;
	private JTextField v2stateName;
	
	/**
	 * Launch the application.
	 */
	
	static HashMap<String,String> religions = new HashMap<String,String>();
	
	public static void main(String[] args) {	
		
		
		f_ID = args[0];
		
		//Fetching name
		try {
			
			ResultSet rs = DBConnect.getInstance().runFetchQuery("SELECT * FROM Foreigner where Foreigner_ID='"+f_ID+"';");
			
			while(rs.next()) 
				f_name = rs.getString("name_firstName");
			
			
			if(f_name==null)
				return;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//Fetching joined details
		try {
			ResultSet rs = DBConnect.getInstance().runFetchQuery(" SELECT * FROM foreigner LEFT JOIN country USING (country_ID) LEFT JOIN passport USING (foreigner_ID) LEFT JOIN email USING (foreigner_ID) LEFT JOIN religion using (religionID); ");
			
			while(rs.next()) { 
				
				f_fullName = rs.getString("name_firstName")+" "+rs.getString("name_middleName")+" "+rs.getString("name_lastName");
				f_passNo = rs.getString("passport_no");
				f_countryName = rs.getString("country_name");
				f_gender = rs.getString("gender");
				f_address = rs.getString("address_buildingNo")+" "+rs.getString("address_streetName")+",\n"+rs.getString("address_city")+", "+rs.getString("address_state");
				
				f_addBuildNo = rs.getString("address_buildingNo");
				f_addStreetNo = rs.getString("address_streetName");
				f_addCity = rs.getString("address_city");
				f_addState = rs.getString("address_state");
				
				f_dOB = rs.getString("dateOfBirth");
				f_marrital = rs.getString("marital_status");
				f_email = rs.getString("email");
				f_blood =  rs.getString("bloodGroup");
				f_religionName = rs.getString("religionName");
				

			}
			
			rs = DBConnect.getInstance().runFetchQuery("SELECT * FROM phoneno where Foreigner_ID='"+f_ID+"';  ");
			f_phoneNo = "";
			while(rs.next()) {
				f_phoneNo += rs.getString("phoneNo")+"\n";
				
			}
			
			rs = DBConnect.getInstance().runFetchQuery("SELECT * FROM religion");
			
			while(rs.next()) {
				
				religions.put(rs.getString("religionName"), rs.getString("religionID"));
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		//Inserting values
		/*
		try {
			DBConnect db = DBConnect.getInstance();
			db.runInsertQuery("INSERT INTO Foreigner values(  '20201101' ,'unmarried' ,'female' ,'AB-', '1998-04-15' ,'mZq3t6w9z$C&F)J@' ,'Kangna' ,'' ,'Kashyap' ,'41B', 'Sector 7, Chankyapuri', 'Delhi', 'Delhi', '0002','0002'  )");

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		
		//Updating data 
		try {
			DBConnect db = DBConnect.getInstance();
			db.runManipulationQuery(" UPDATE Foreigner set name_firstName='Anurag', name_lastName='Basu' where Foreigner_ID='20201212' ");

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myAccount window = new myAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		 
	}
	
	
	//THIS IS THE FUNCTION.. TO CHECK WHETHER A DATE IS VALID OR NOT...  True-> valid date.... False-> invalid date  ... :)
	
	public boolean validDate(int date,int month,int year) {
		try {
			LocalDate.of(year, month, date);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * Create the application.
	 */
	public myAccount() {
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
		
		// #b1 7b 50
		// rgb(177, 123, 80)
		//rgb(165, 100, 52)
		//trajan
		//Sylfaean
		//Sitka
		//Seoge Script
		
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
		
		JLabel changePassword = new JLabel("change password");
		changePassword.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		changePassword.setHorizontalAlignment(SwingConstants.CENTER);
		changePassword.setBounds(1059, 43, 106, 21);
		frame.getContentPane().add(changePassword);
		
		Font font = changePassword.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		changePassword.setFont(font.deriveFont(attributes));
		
		JLabel welcome = new JLabel("Welcome, ");
		welcome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		welcome.setHorizontalAlignment(SwingConstants.LEFT);
		welcome.setBounds(960, 11, 78, 23);
		frame.getContentPane().add(welcome);
		
		JLabel username = new JLabel(f_name);
		username.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
		username.setBounds(1039, 10, 115, 29);
		frame.getContentPane().add(username);
		
		//rgb(0, 36, 71)
		
		JPanel leftsidePanel = new JPanel();
		leftsidePanel.setBackground(new Color(0, 36, 71));
		leftsidePanel.setBounds(0, 102, 274, 648);
		frame.getContentPane().add(leftsidePanel);
		leftsidePanel.setLayout(null);
		
		JLabel myPersonal = new JLabel("My Personal Details");
		
		
		JLabel accountDashboard = new JLabel("Account Dashbard");
		accountDashboard.setForeground(new Color(204, 204, 204));
		
		
		accountDashboard.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		accountDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		leftsidePanel.add(accountDashboard);
		accountDashboard.setBounds(0, 246, 274, 27);
		myPersonal.setForeground(new Color(204, 204, 204));
		myPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		myPersonal.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		myPersonal.setBounds(0, 285, 274, 27);
		leftsidePanel.add(myPersonal);
		
		JLabel editDetails = new JLabel("Edit Details");
		
		editDetails.setForeground(new Color(204, 204, 204));
		editDetails.setHorizontalAlignment(SwingConstants.CENTER);
		editDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		editDetails.setBounds(0, 324, 274, 27);
		leftsidePanel.add(editDetails);
		
		JLabel travelog = new JLabel("My Travelog");
		travelog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Font font = travelog.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
				travelog.setFont(font.deriveFont(attributes));
			}
		});
		travelog.setForeground(new Color(204, 204, 204));
		travelog.setHorizontalAlignment(SwingConstants.CENTER);
		travelog.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		travelog.setBounds(0, 363, 274, 27);
		leftsidePanel.add(travelog);
		
		JLabel applyforVisit = new JLabel("Apply for Visit");
		applyforVisit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Font font = applyforVisit.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
				applyforVisit.setFont(font.deriveFont(attributes));
			}
		});
		applyforVisit.setForeground(new Color(204, 204, 204));
		applyforVisit.setHorizontalAlignment(SwingConstants.CENTER);
		applyforVisit.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		applyforVisit.setBounds(0, 402, 274, 27);
		leftsidePanel.add(applyforVisit);
		
		JPanel trypanel = new JPanel();
		trypanel.setBounds(275, 101, 900, 652);
		frame.getContentPane().add(trypanel);
		trypanel.setLayout(new CardLayout(0, 0));
		
		
		JPanel accdashPan = new JPanel();
		trypanel.add(accdashPan, "name_497056365022900");
		accdashPan.setLayout(null);
		accdashPan.setBackground(new Color(231,231,231));
		
		JLabel heading = new JLabel("My Dashboard");
		heading.setBounds(0, 45, 901, 42);
		accdashPan.add(heading);
		heading.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setAlignmentY(0.0f);
		
		JLabel days = new JLabel("10");
		days.setBounds(326, 148, 159, 128);
		accdashPan.add(days);
		days.setHorizontalAlignment(SwingConstants.CENTER);
		days.setFont(new Font("Myriad Pro Light", Font.PLAIN, 80));
		days.setBackground(new Color(247, 247, 247));
		days.setForeground(new Color(0, 0, 102));
		
		daysLabel = new JLabel("days left");
		daysLabel.setBounds(480, 195, 129, 42);
		accdashPan.add(daysLabel);
		daysLabel.setHorizontalAlignment(SwingConstants.LEFT);
		daysLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		accdashPan.setLayout(null);
		
		
		//rgb(255, 248, 170)
		//rgb(254, 250, 202)
		//rgb(203, 218, 219)
		//rgb(238, 219, 219)
		//rgb(224, 224, 222)
		//rgb(227, 201, 201)
		//rgb(242, 230, 230)
		//rgb(253, 225, 89)
		//rgb(254, 250, 202)
		
		//rgb(155, 157, 160)
		
		
		//112, 181, 201 -> name
		
		
		JPanel accEntryPan = new JPanel();
		accEntryPan.setBounds(38, 349, 817, 245);
		accdashPan.add(accEntryPan);
		accEntryPan.setLayout(null);
		accEntryPan.setBackground(new Color(247, 247, 247));
		LineBorder border = new LineBorder(new Color(255, 255, 255), 5);
		accEntryPan.setBorder(border);
		
		JLabel labName = new JLabel("Name");
		
		labName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labName.setBounds(45, 25, 59, 28);
		accEntryPan.add(labName);
		
		JLabel labPassno = new JLabel("Passport No.");
		labPassno.setForeground(Color.GRAY);
		labPassno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labPassno.setBounds(45, 58, 110, 28);
		accEntryPan.add(labPassno);
		
		JLabel labCountry = new JLabel("Country");
		labCountry.setForeground(Color.GRAY);
		labCountry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labCountry.setBounds(425, 124, 70, 28);
		accEntryPan.add(labCountry);
		
		
		
		
		JLabel labDttmarvl = new JLabel("Date of Arrival");
		labDttmarvl.setForeground(Color.GRAY);
		labDttmarvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labDttmarvl.setBounds(425, 25, 118, 28);
		accEntryPan.add(labDttmarvl);
		
		JLabel labDuratn = new JLabel("Duration");
		labDuratn.setForeground(Color.GRAY);
		labDuratn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labDuratn.setBounds(425, 91, 118, 28);
		accEntryPan.add(labDuratn);
		
		
		
		JLabel vName = new JLabel(f_fullName);
		vName.setForeground(new Color(112, 181, 201));
		vName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vName.setBounds(200, 25, 215, 28);
		accEntryPan.add(vName);
		
		labName.setForeground(Color.GRAY);
		
		JLabel vPassno = new JLabel(f_passNo);
		vPassno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vPassno.setBounds(200, 58, 215, 28);
		accEntryPan.add(vPassno);
		vPassno.setForeground(new Color(112, 181, 201));
		
		JLabel vdtArvl = new JLabel("10-11-2020");
		vdtArvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vdtArvl.setBounds(660, 25, 130, 28);
		accEntryPan.add(vdtArvl);
		vdtArvl.setForeground(new Color(112, 181, 201));
		
		JLabel vDuratn = new JLabel(" 1 year");
		vDuratn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vDuratn.setBounds(660, 91, 130, 28);
		accEntryPan.add(vDuratn);
		vDuratn.setForeground(new Color(112, 181, 201));
		
		JLabel col1 = new JLabel(":");
		col1.setForeground(Color.GRAY);
		col1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1.setBounds(165, 25, 14, 28);
		accEntryPan.add(col1);
		col1.setForeground(Color.BLACK);
		JLabel col1_2 = new JLabel(":");
		col1_2.setForeground(Color.GRAY);
		col1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_2.setBounds(165, 58, 14, 28);
		accEntryPan.add(col1_2);
		col1_2.setForeground(Color.BLACK);
		
		JLabel col1_3 = new JLabel(":");
		col1_3.setForeground(Color.GRAY);
		col1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_3.setBounds(623, 124, 14, 28);
		accEntryPan.add(col1_3);
		col1_3.setForeground(Color.BLACK);
		
		JLabel col1_5 = new JLabel(":");
		col1_5.setForeground(Color.GRAY);
		col1_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5.setBounds(623, 25, 14, 28);
		accEntryPan.add(col1_5);
		col1_5.setForeground(Color.BLACK);
		
		JLabel col1_6 = new JLabel(":");
		col1_6.setForeground(Color.GRAY);
		col1_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_6.setBounds(623, 91, 14, 28);
		accEntryPan.add(col1_6);
		col1_6.setForeground(Color.BLACK);
		
		JLabel vCountry = new JLabel(f_countryName);
		//vCountry.setForeground(Color.GRAY);
		vCountry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vCountry.setBounds(660, 124, 135, 28);
		accEntryPan.add(vCountry);
		vCountry.setForeground(new Color(112, 181, 201));
		
		JLabel labVisa = new JLabel("VISA");
		labVisa.setForeground(Color.GRAY);
		labVisa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labVisa.setBounds(45, 124, 110, 28);
		accEntryPan.add(labVisa);
		
		JLabel col1_4_1 = new JLabel(":");
		col1_4_1.setForeground(new Color(0, 0, 0));
		col1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_4_1.setBounds(165, 124, 14, 28);
		accEntryPan.add(col1_4_1);
		
		JLabel vVisa = new JLabel("STUDENT");
		vVisa.setForeground(new Color(112, 181, 201));
		vVisa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vVisa.setBounds(202, 124, 213, 28);
		accEntryPan.add(vVisa);
		
		JLabel labDttmdeprt = new JLabel("Date of Departure");
		labDttmdeprt.setForeground(Color.GRAY);
		labDttmdeprt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labDttmdeprt.setBounds(425, 58, 188, 28);
		accEntryPan.add(labDttmdeprt);
		
		JLabel col1_5_1 = new JLabel(":");
		col1_5_1.setForeground(new Color(0, 0, 0));
		col1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5_1.setBounds(623, 58, 14, 28);
		accEntryPan.add(col1_5_1);
		
		JLabel vdtDept = new JLabel("NULL");
		vdtDept.setForeground(new Color(112, 181, 201));
		vdtDept.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vdtDept.setBounds(660, 58, 130, 28);
		accEntryPan.add(vdtDept);
		
		JLabel labTicketNo = new JLabel("Ticket No.");
		labTicketNo.setForeground(Color.GRAY);
		labTicketNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labTicketNo.setBounds(45, 91, 110, 28);
		accEntryPan.add(labTicketNo);
		
		JLabel col1_7_2 = new JLabel(":");
		col1_7_2.setForeground(new Color(0, 0, 0));
		col1_7_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_7_2.setBounds(166, 91, 14, 28);
		accEntryPan.add(col1_7_2);
		
		JLabel vTicket = new JLabel("XYZ9279382A08");
		vTicket.setForeground(new Color(112, 181, 201));
		vTicket.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vTicket.setBounds(200, 91, 215, 28);
		accEntryPan.add(vTicket);
		
		JPanel mypersonPan = new JPanel();
		trypanel.add(mypersonPan, "name_497101350644300");
		mypersonPan.setLayout(null);
		mypersonPan.setBackground(new Color(231,231,231));
		
		JPanel persnlDetailPanel = new JPanel();
		persnlDetailPanel.setLayout(null);
		persnlDetailPanel.setBackground(new Color(247, 247, 247));
		persnlDetailPanel.setBounds(29, 213, 817, 309);
		mypersonPan.add(persnlDetailPanel);
		LineBorder bord = new LineBorder(new Color(255, 255, 255), 5);
		persnlDetailPanel.setBorder(bord);
		
		JLabel l1ID = new JLabel("ID");
		l1ID.setForeground(Color.GRAY);
		l1ID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1ID.setBounds(46, 25, 33, 28);
		persnlDetailPanel.add(l1ID);
		
		JLabel l1email = new JLabel("Email");
		l1email.setForeground(Color.GRAY);
		l1email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1email.setBounds(425, 191, 141, 28);
		persnlDetailPanel.add(l1email);
		
		JLabel l1Gender = new JLabel("Gender");
		l1Gender.setForeground(Color.GRAY);
		l1Gender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1Gender.setBounds(46, 91, 70, 28);
		persnlDetailPanel.add(l1Gender);
		
		JLabel l1DoB = new JLabel("Date of Birth");
		l1DoB.setForeground(Color.GRAY);
		l1DoB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1DoB.setBounds(425, 25, 118, 28);
		persnlDetailPanel.add(l1DoB);
		
		JLabel l1PhoneNo = new JLabel("Phone No");
		l1PhoneNo.setForeground(Color.GRAY);
		l1PhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1PhoneNo.setBounds(425, 91, 118, 28);
		persnlDetailPanel.add(l1PhoneNo);
		
		JLabel l1bloodG = new JLabel("Blood Group");
		l1bloodG.setForeground(Color.GRAY);
		l1bloodG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1bloodG.setBounds(425, 158, 141, 28);
		persnlDetailPanel.add(l1bloodG);
		
		JLabel l1religion = new JLabel("Religion");
		l1religion.setForeground(Color.GRAY);
		l1religion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1religion.setBounds(46, 58, 98, 28);
		persnlDetailPanel.add(l1religion);
		
		JLabel v1ID = new JLabel(f_ID);
		v1ID.setHorizontalAlignment(SwingConstants.LEFT);
		v1ID.setForeground(new Color(112, 181, 201));
		v1ID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1ID.setBounds(187, 25, 196, 28);
		
		persnlDetailPanel.add(v1ID);
		
		JTextArea v1email = new JTextArea(f_email);
		v1email.setLineWrap(true);
		v1email.setWrapStyleWord(true);
		v1email.setForeground(new Color(112, 181, 201));
		v1email.setBackground(new Color(247, 247, 247));
		v1email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		//v1Phoneno.set(187, 25, 189, 28);
		v1email.setBounds(611, 191, 188, 62);
		persnlDetailPanel.add(v1email);
		
		JLabel v1doB = new JLabel(f_dOB);
		v1doB.setForeground(new Color(112, 181, 201));
		v1doB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1doB.setBounds(611, 25, 196, 28);
		persnlDetailPanel.add(v1doB);
		
		JTextArea v1PhoneNo = new JTextArea(f_phoneNo);
		v1PhoneNo.setForeground(new Color(112, 181, 201));
		v1PhoneNo.setBackground(new Color(247,247,247));
		v1PhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1PhoneNo.setBounds(611, 91, 196, 61);
		persnlDetailPanel.add(v1PhoneNo);
		
		JLabel v1bloodG = new JLabel(f_blood);
		v1bloodG.setForeground(new Color(112, 181, 201));
		v1bloodG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1bloodG.setBounds(611, 158, 196, 28);
		persnlDetailPanel.add(v1bloodG);
		
		JLabel v1religion = new JLabel(f_religionName);
		v1religion.setForeground(new Color(112, 181, 201));
		v1religion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1religion.setBounds(187, 58, 189, 28);
		persnlDetailPanel.add(v1religion);
		
		JLabel col1_1_1 = new JLabel(":");
		col1_1_1.setForeground(new Color(0, 0, 0));
		col1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_1_1.setBounds(152, 25, 14, 28);
		persnlDetailPanel.add(col1_1_1);
		
		JLabel col1_2_1 = new JLabel(":");
		col1_2_1.setForeground(new Color(0, 0, 0));
		col1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_2_1.setBounds(152, 58, 14, 28);
		persnlDetailPanel.add(col1_2_1);
		
		JLabel col1_3_1 = new JLabel(":");
		col1_3_1.setForeground(new Color(0, 0, 0));
		col1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_3_1.setBounds(152, 91, 14, 28);
		persnlDetailPanel.add(col1_3_1);
		
		JLabel col1_5_2 = new JLabel(":");
		col1_5_2.setForeground(new Color(0, 0, 0));
		col1_5_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5_2.setBounds(576, 25, 14, 28);
		persnlDetailPanel.add(col1_5_2);
		
		JLabel col1_6_1 = new JLabel(":");
		col1_6_1.setForeground(new Color(0, 0, 0));
		col1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_6_1.setBounds(576, 91, 14, 28);
		persnlDetailPanel.add(col1_6_1);
		
		JLabel col1_7_1 = new JLabel(":");
		col1_7_1.setForeground(new Color(0, 0, 0));
		col1_7_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_7_1.setBounds(576, 158, 14, 28);
		persnlDetailPanel.add(col1_7_1);
		
		JLabel col1_8_1 = new JLabel(":");
		col1_8_1.setForeground(new Color(0, 0, 0));
		col1_8_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_8_1.setBounds(576, 191, 14, 28);
		persnlDetailPanel.add(col1_8_1);
		
		JLabel v1Gender = new JLabel(f_gender);
		v1Gender.setForeground(new Color(112, 181, 201));
		v1Gender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1Gender.setBounds(187, 91, 189, 28);
		persnlDetailPanel.add(v1Gender);
		
		JLabel l1Address = new JLabel("Address");
		l1Address.setForeground(Color.GRAY);
		l1Address.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1Address.setBounds(46, 125, 98, 28);
		persnlDetailPanel.add(l1Address);
		
		JLabel col1_4_1_1 = new JLabel(":");
		col1_4_1_1.setForeground(new Color(0, 0, 0));
		col1_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_4_1_1.setBounds(152, 124, 14, 28);
		persnlDetailPanel.add(col1_4_1_1);
		
		JLabel l1marritalStatus = new JLabel("Marrital Status");
		l1marritalStatus.setForeground(Color.GRAY);
		l1marritalStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1marritalStatus.setBounds(425, 58, 188, 28);
		persnlDetailPanel.add(l1marritalStatus);
		
		JLabel col1_5_1_1 = new JLabel(":");
		col1_5_1_1.setForeground(new Color(0, 0, 0));
		col1_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5_1_1.setBounds(576, 58, 14, 28);
		persnlDetailPanel.add(col1_5_1_1);
		
		JLabel v1marritalStatus = new JLabel(f_marrital);
		v1marritalStatus.setForeground(new Color(112, 181, 201));
		v1marritalStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1marritalStatus.setBounds(611, 58, 196, 28);
		persnlDetailPanel.add(v1marritalStatus);
		
		
		JTextArea v1address = new JTextArea(f_address);
		v1address.setWrapStyleWord(true);
		v1address.setLineWrap(true);
		v1address.setEditable(false);
		v1address.setForeground(new Color(112, 181, 201));
		v1address.setBackground(persnlDetailPanel.getBackground());
		v1address.setFont(new Font("Tahoma", Font.PLAIN, 18));
		v1address.setBounds(189, 124, 215, 89);
		
		persnlDetailPanel.add(v1address);
		
		
		JLabel lblMyPersonalDetails = new JLabel("My Personal Details");
		lblMyPersonalDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMyPersonalDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyPersonalDetails.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		lblMyPersonalDetails.setAlignmentY(0.0f);
		lblMyPersonalDetails.setBounds(0, 45, 901, 42);
		mypersonPan.add(lblMyPersonalDetails);
		
		//rgb(200, 226, 249)
		
		JPanel editPan = new JPanel();
		trypanel.add(editPan, "name_497745390804800");
		editPan.setLayout(null);
		editPan.setBackground(new Color(231,231,231));
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(0, 5, 400, 700));
		panel.setLayout(null);
		panel.setBackground(new Color(231,231,231));
		panel.setPreferredSize(new Dimension(600, 800));
		
		
		JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 5, 890, 690);
		scrollPane.setPreferredSize(new Dimension(600, 650));
		editPan.add(scrollPane);
		
		
		JLabel lblMyPersonalDetails_1 = new JLabel("Edit Details");
		lblMyPersonalDetails_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMyPersonalDetails_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyPersonalDetails_1.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		lblMyPersonalDetails_1.setAlignmentY(0.0f);
		lblMyPersonalDetails_1.setBounds(0, 45, 901, 42);
		panel.add(lblMyPersonalDetails_1);
		
		JLabel l2pernlDet = new JLabel("Personal Details");
		l2pernlDet.setHorizontalTextPosition(SwingConstants.CENTER);
		l2pernlDet.setHorizontalAlignment(SwingConstants.LEFT);
		l2pernlDet.setForeground(new Color(25, 25, 112));
		l2pernlDet.setFont(new Font("Corbel", Font.PLAIN, 30));
		l2pernlDet.setAlignmentY(0.0f);
		l2pernlDet.setBounds(30, 108, 859, 42);
		panel.add(l2pernlDet);
		
		JPanel persnlDetailPanel_1 = new JPanel();
		persnlDetailPanel_1.setLayout(null);
		persnlDetailPanel_1.setBackground(new Color(247, 247, 247));
		persnlDetailPanel_1.setBounds(30, 160, 820, 322);
		panel.add(persnlDetailPanel_1);
		
		JLabel l2Phoneno = new JLabel("Phone No.");
		l2Phoneno.setForeground(Color.GRAY);
		l2Phoneno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2Phoneno.setBounds(425, 195, 93, 28);
		persnlDetailPanel_1.add(l2Phoneno);
		
		JLabel l2Gender = new JLabel("Gender");
		l2Gender.setForeground(Color.GRAY);
		l2Gender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2Gender.setBounds(32, 195, 70, 28);
		persnlDetailPanel_1.add(l2Gender);
		
		JLabel l2DoB = new JLabel("Date of Birth");
		l2DoB.setForeground(Color.GRAY);
		l2DoB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2DoB.setBounds(425, 25, 118, 28);
		persnlDetailPanel_1.add(l2DoB);
		
		JLabel l2email = new JLabel("Email");
		l2email.setForeground(Color.GRAY);
		l2email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2email.setBounds(425, 91, 118, 28);
		persnlDetailPanel_1.add(l2email);
		
		JLabel l2bloodG = new JLabel("Blood Group");
		l2bloodG.setForeground(Color.GRAY);
		l2bloodG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2bloodG.setBounds(425, 124, 145, 28);
		persnlDetailPanel_1.add(l2bloodG);
		
		JLabel l2religion = new JLabel("Religion");
		l2religion.setForeground(Color.GRAY);
		l2religion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2religion.setBounds(425, 157, 138, 28);
		persnlDetailPanel_1.add(l2religion);
		
		JLabel col1_2_1_1 = new JLabel(":");
		col1_2_1_1.setForeground(Color.BLACK);
		col1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_2_1_1.setBounds(577, 195, 14, 28);
		persnlDetailPanel_1.add(col1_2_1_1);
		
		JLabel col1_3_1_1 = new JLabel(":");
		col1_3_1_1.setForeground(Color.BLACK);
		col1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_3_1_1.setBounds(152, 196, 14, 28);
		persnlDetailPanel_1.add(col1_3_1_1);
		
		JLabel col1_5_2_1 = new JLabel(":");
		col1_5_2_1.setForeground(Color.BLACK);
		col1_5_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5_2_1.setBounds(577, 25, 14, 28);
		persnlDetailPanel_1.add(col1_5_2_1);
		
		JLabel col1_6_1_1 = new JLabel(":");
		col1_6_1_1.setForeground(Color.BLACK);
		col1_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_6_1_1.setBounds(577, 91, 14, 28);
		persnlDetailPanel_1.add(col1_6_1_1);
		
		JLabel col1_7_1_1 = new JLabel(":");
		col1_7_1_1.setForeground(Color.BLACK);
		col1_7_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_7_1_1.setBounds(577, 124, 14, 28);
		persnlDetailPanel_1.add(col1_7_1_1);
		
		JLabel col1_8_1_1 = new JLabel(":");
		col1_8_1_1.setForeground(Color.BLACK);
		col1_8_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_8_1_1.setBounds(577, 157, 14, 28);
		persnlDetailPanel_1.add(col1_8_1_1);
		
		JLabel l2add_buildNo = new JLabel("Address");
		l2add_buildNo.setForeground(Color.GRAY);
		l2add_buildNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2add_buildNo.setBounds(32, 58, 86, 28);
		persnlDetailPanel_1.add(l2add_buildNo);
		
		JLabel col1_4_1_1_1 = new JLabel(":");
		col1_4_1_1_1.setForeground(Color.BLACK);
		col1_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_4_1_1_1.setBounds(152, 58, 14, 28);
		persnlDetailPanel_1.add(col1_4_1_1_1);
		
		JLabel l2marrital = new JLabel("Marrital Status");
		l2marrital.setForeground(Color.GRAY);
		l2marrital.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2marrital.setBounds(425, 58, 145, 28);
		persnlDetailPanel_1.add(l2marrital);
		
		JLabel col1_5_1_1_1 = new JLabel(":");
		col1_5_1_1_1.setForeground(Color.BLACK);
		col1_5_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5_1_1_1.setBounds(577, 58, 14, 28);
		persnlDetailPanel_1.add(col1_5_1_1_1);
		
		JTextField v2address = new JTextField(f_addBuildNo);
		v2address.setToolTipText("Building No.");
		//v2address.set
		v2address.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2address.setBorder(new LineBorder(new Color(192, 192, 192)));
		v2address.setBounds(187, 58, 189, 28);
		persnlDetailPanel_1.add(v2address);
		
		v2gender = new JTextField(f_gender);
		v2gender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2gender.setEnabled(false);
		v2gender.setEditable(false);
		v2gender.setColumns(10);
		v2gender.setBackground(Color.WHITE);
		v2gender.setBounds(187, 196, 189, 28);
		persnlDetailPanel_1.add(v2gender);
		
		JTextArea v2phone = new JTextArea(f_phoneNo);
		v2phone.setWrapStyleWord(true);
		v2phone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2phone.setColumns(10);
		v2phone.setBounds(597, 195, 189, 59);
		v2phone.setBorder(new LineBorder(new Color(192, 192, 192)));
		persnlDetailPanel_1.add(v2phone);
		
		v2blood = new JTextField(f_blood);
		v2blood.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2blood.setEnabled(false);
		v2blood.setEditable(false);
		v2blood.setColumns(10);
		v2blood.setBackground(Color.WHITE);
		v2blood.setBounds(597, 124, 189, 28);
		persnlDetailPanel_1.add(v2blood);
		
		v2email = new JTextField(f_email);
		v2email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2email.setColumns(10);
		v2email.setBounds(597, 90, 189, 28);
		persnlDetailPanel_1.add(v2email);
		
		v2dOB = new JTextField(f_dOB);
		v2dOB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2dOB.setEnabled(false);
		v2dOB.setEditable(false);
		v2dOB.setColumns(10);
		v2dOB.setBackground(Color.WHITE);
		v2dOB.setBounds(597, 24, 189, 28);
		persnlDetailPanel_1.add(v2dOB);
		
		JLabel l2name = new JLabel("Name");
		l2name.setForeground(Color.GRAY);
		l2name.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2name.setBounds(32, 25, 93, 28);
		persnlDetailPanel_1.add(l2name);
		
		JLabel col1_2_1_1_1 = new JLabel(":");
		col1_2_1_1_1.setForeground(Color.BLACK);
		col1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_2_1_1_1.setBounds(152, 26, 14, 28);
		persnlDetailPanel_1.add(col1_2_1_1_1);
		
		v2name = new JTextField(f_fullName);
		v2name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2name.setEnabled(false);
		v2name.setEditable(false);
		v2name.setColumns(10);
		v2name.setBackground(Color.WHITE);
		v2name.setBounds(187, 25, 189, 28);
		persnlDetailPanel_1.add(v2name);
		
		v2streetName = new JTextField(f_addStreetNo);
		v2streetName.setToolTipText("Street");
		v2streetName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2streetName.setBorder(new LineBorder(new Color(192, 192, 192)));
		v2streetName.setBounds(187, 92, 189, 28);
		persnlDetailPanel_1.add(v2streetName);
		
		v2cityName = new JTextField(f_addCity);
		v2cityName.setToolTipText("City");
		v2cityName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2cityName.setBorder(new LineBorder(new Color(192, 192, 192)));
		v2cityName.setBounds(187, 126, 189, 28);
		persnlDetailPanel_1.add(v2cityName);
		
		v2stateName = new JTextField(f_addState);
		v2stateName.setToolTipText("State");
		v2stateName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2stateName.setBorder(new LineBorder(new Color(192, 192, 192)));
		v2stateName.setBounds(187, 160, 189, 28);
		persnlDetailPanel_1.add(v2stateName);
		
		JRadioButton v2married = new JRadioButton("Married");
		v2married.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2married.setBounds(597, 52, 101, 37);
		v2married.setBackground(new Color(247,247,247));
		persnlDetailPanel_1.add(v2married);
		
		JRadioButton v2unmarried = new JRadioButton("Unmarried");
		v2unmarried.setFont(new Font("Tahoma", Font.PLAIN, 13));
		v2unmarried.setBounds(704, 52, 101, 37);
		v2unmarried.setBackground(new Color(247,247,247));
		persnlDetailPanel_1.add(v2unmarried);
		
		if(f_marrital.equals("married"))
			v2married.setSelected(true);		
		else
			v2unmarried.setSelected(true);
		
		ButtonGroup g1 = new ButtonGroup();
		g1.add(v2married);
		g1.add(v2unmarried);
		
		
		JComboBox v2religions = new JComboBox(religions.keySet().toArray());
		v2religions.setBounds(597, 158, 189, 28);
		v2religions.setSelectedItem(f_religionName);
		persnlDetailPanel_1.add(v2religions);
		
		
		
		JLabel l2loginDet = new JLabel("Login Details");
		l2loginDet.setHorizontalTextPosition(SwingConstants.CENTER);
		l2loginDet.setHorizontalAlignment(SwingConstants.LEFT);
		l2loginDet.setForeground(new Color(25, 25, 112));
		l2loginDet.setFont(new Font("Corbel", Font.PLAIN, 30));
		l2loginDet.setAlignmentY(0.0f);
		l2loginDet.setBounds(30, 522, 224, 42);
		panel.add(l2loginDet);
		
		JPanel persnlDetailPanel_1_1 = new JPanel();
		persnlDetailPanel_1_1.setLayout(null);
		persnlDetailPanel_1_1.setBackground(new Color(247, 247, 247));
		persnlDetailPanel_1_1.setBounds(30, 574, 830, 140);
		panel.add(persnlDetailPanel_1_1);
		
		JLabel l2ID = new JLabel("ID");
		l2ID.setForeground(Color.GRAY);
		l2ID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2ID.setBounds(32, 24, 33, 28);
		persnlDetailPanel_1_1.add(l2ID);
		
		JLabel l2oldPass = new JLabel("Old Password");
		l2oldPass.setForeground(Color.GRAY);
		l2oldPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2oldPass.setBounds(34, 55, 118, 28);
		persnlDetailPanel_1_1.add(l2oldPass);
		
		JLabel l1newPass = new JLabel("New Password");
		l1newPass.setForeground(Color.GRAY);
		l1newPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1newPass.setBounds(419, 24, 118, 28);
		persnlDetailPanel_1_1.add(l1newPass);
		
		JLabel col1_1_1_1_1 = new JLabel(":");
		col1_1_1_1_1.setForeground(Color.BLACK);
		col1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_1_1_1_1.setBounds(152, 25, 14, 28);
		persnlDetailPanel_1_1.add(col1_1_1_1_1);
		
		JLabel col1_2_1_1_2 = new JLabel(":");
		col1_2_1_1_2.setForeground(Color.BLACK);
		col1_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_2_1_1_2.setBounds(152, 55, 14, 28);
		persnlDetailPanel_1_1.add(col1_2_1_1_2);
		
		JLabel col1_3_1_1_1 = new JLabel(":");
		col1_3_1_1_1.setForeground(Color.BLACK);
		col1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_3_1_1_1.setBounds(571, 27, 14, 28);
		persnlDetailPanel_1_1.add(col1_3_1_1_1);
		
		JLabel l2confirmPass = new JLabel("Confirm Password");
		l2confirmPass.setForeground(Color.GRAY);
		l2confirmPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2confirmPass.setBounds(419, 59, 144, 28);
		persnlDetailPanel_1_1.add(l2confirmPass);
		
		JLabel col1_4_1_1_1_1 = new JLabel(":");
		col1_4_1_1_1_1.setForeground(Color.BLACK);
		col1_4_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_4_1_1_1_1.setBounds(571, 59, 14, 28);
		persnlDetailPanel_1_1.add(col1_4_1_1_1_1);
		
		JPasswordField v2conPass = new JPasswordField();
		v2conPass.setBorder(new LineBorder(new Color(192, 192, 192)));
		v2conPass.setBounds(606, 65, 189, 28);
		persnlDetailPanel_1_1.add(v2conPass);
		
		v2newPass = new JPasswordField();
		v2newPass.setColumns(10);
		v2newPass.setBackground(Color.WHITE);
		v2newPass.setBounds(606, 27, 189, 28);
		persnlDetailPanel_1_1.add(v2newPass);
		
		v2oldPass = new JPasswordField();
		v2oldPass.setColumns(10);
		v2oldPass.setBounds(187, 59, 189, 28);
		persnlDetailPanel_1_1.add(v2oldPass);
		
		//if(v2oldPass.equals())
		
		v2ID = new JTextField(f_ID);
		v2ID.setEnabled(false);
		v2ID.setEditable(false);
		v2ID.setColumns(10);
		v2ID.setBackground(Color.WHITE);
		v2ID.setBounds(187, 24, 189, 28);
		persnlDetailPanel_1_1.add(v2ID);
		
		JButton b2save = new JButton("SAVE");
		b2save.setForeground(Color.LIGHT_GRAY);
		b2save.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		b2save.setBounds(670, 680, 150, 34);
		b2save.setBackground(new Color(0, 36, 71));
		panel.add(b2save);
		
		JPanel travelPan = new JPanel();
		trypanel.add(travelPan, "name_497750311800400");
		travelPan.setLayout(null);
		travelPan.setBackground(new Color(231,231,231));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(53, 100, 783, 111);
		travelPan.add(panel_1);
		
		
		JPanel applyPan = new JPanel();
		trypanel.add(applyPan, "name_497754296084800");
		applyPan.setLayout(null);
		applyPan.setBackground(new Color(231,231,231));
		
		JLabel l1applyPan = new JLabel("Application Form");
		l1applyPan.setHorizontalTextPosition(SwingConstants.CENTER);
		l1applyPan.setHorizontalAlignment(SwingConstants.CENTER);
		l1applyPan.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		l1applyPan.setAlignmentY(0.0f);
		l1applyPan.setBounds(0, 45, 901, 42);
		applyPan.add(l1applyPan);
		
		JPanel persnlDetailPanel_1_2 = new JPanel();
		persnlDetailPanel_1_2.setLayout(null);
		persnlDetailPanel_1_2.setBackground(new Color(247, 247, 247));
		persnlDetailPanel_1_2.setBounds(29, 213, 817, 245);
		applyPan.add(persnlDetailPanel_1_2);
		
		JLabel l1ID_2_2 = new JLabel("VISA");
		l1ID_2_2.setForeground(Color.GRAY);
		l1ID_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1ID_2_2.setBounds(36, 34, 57, 28);
		persnlDetailPanel_1_2.add(l1ID_2_2);
		
		JLabel l3bookingNo = new JLabel("Booking No.");
		l3bookingNo.setForeground(Color.GRAY);
		l3bookingNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l3bookingNo.setBounds(36, 101, 110, 28);
		persnlDetailPanel_1_2.add(l3bookingNo);
		
		JLabel l1Gender_2_2 = new JLabel("Gender");
		l1Gender_2_2.setForeground(Color.GRAY);
		l1Gender_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1Gender_2_2.setBounds(36, 134, 70, 28);
		persnlDetailPanel_1_2.add(l1Gender_2_2);
		
		JLabel l3DoArr = new JLabel("Date of Arrival");
		l3DoArr.setForeground(Color.GRAY);
		l3DoArr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l3DoArr.setBounds(425, 50, 118, 28);
		persnlDetailPanel_1_2.add(l3DoArr);
		
		JLabel l1email_2_2 = new JLabel("Email");
		l1email_2_2.setForeground(Color.GRAY);
		l1email_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1email_2_2.setBounds(425, 116, 118, 28);
		persnlDetailPanel_1_2.add(l1email_2_2);
		
		JLabel l1bloodG_2_2 = new JLabel("Blood Group");
		l1bloodG_2_2.setForeground(Color.GRAY);
		l1bloodG_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1bloodG_2_2.setBounds(425, 149, 145, 28);
		persnlDetailPanel_1_2.add(l1bloodG_2_2);
		
		JLabel l1religion_2_2 = new JLabel("Religion");
		l1religion_2_2.setForeground(Color.GRAY);
		l1religion_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1religion_2_2.setBounds(425, 182, 145, 28);
		persnlDetailPanel_1_2.add(l1religion_2_2);
		
		JLabel col1_1_1_1_2 = new JLabel(":");
		col1_1_1_1_2.setForeground(Color.BLACK);
		col1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_1_1_1_2.setBounds(156, 35, 14, 28);
		persnlDetailPanel_1_2.add(col1_1_1_1_2);
		
		JLabel col1_2_1_1_3 = new JLabel(":");
		col1_2_1_1_3.setForeground(Color.BLACK);
		col1_2_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_2_1_1_3.setBounds(156, 102, 14, 28);
		persnlDetailPanel_1_2.add(col1_2_1_1_3);
		
		JLabel col1_3_1_1_2 = new JLabel(":");
		col1_3_1_1_2.setForeground(Color.BLACK);
		col1_3_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_3_1_1_2.setBounds(156, 135, 14, 28);
		persnlDetailPanel_1_2.add(col1_3_1_1_2);
		
		JLabel col1_5_2_1_2 = new JLabel(":");
		col1_5_2_1_2.setForeground(Color.BLACK);
		col1_5_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5_2_1_2.setBounds(577, 50, 14, 28);
		persnlDetailPanel_1_2.add(col1_5_2_1_2);
		
		JLabel col1_6_1_1_2 = new JLabel(":");
		col1_6_1_1_2.setForeground(Color.BLACK);
		col1_6_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_6_1_1_2.setBounds(577, 116, 14, 28);
		persnlDetailPanel_1_2.add(col1_6_1_1_2);
		
		JLabel col1_7_1_1_2 = new JLabel(":");
		col1_7_1_1_2.setForeground(Color.BLACK);
		col1_7_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_7_1_1_2.setBounds(577, 149, 14, 28);
		persnlDetailPanel_1_2.add(col1_7_1_1_2);
		
		JLabel col1_8_1_1_2 = new JLabel(":");
		col1_8_1_1_2.setForeground(Color.BLACK);
		col1_8_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_8_1_1_2.setBounds(577, 182, 14, 28);
		persnlDetailPanel_1_2.add(col1_8_1_1_2);
		
		JLabel l1Address_2_2 = new JLabel("Address");
		l1Address_2_2.setForeground(Color.GRAY);
		l1Address_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1Address_2_2.setBounds(36, 168, 86, 28);
		persnlDetailPanel_1_2.add(l1Address_2_2);
		
		JLabel col1_4_1_1_1_2 = new JLabel(":");
		col1_4_1_1_1_2.setForeground(Color.BLACK);
		col1_4_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_4_1_1_1_2.setBounds(156, 168, 14, 28);
		persnlDetailPanel_1_2.add(col1_4_1_1_1_2);
		
		JLabel l3duratn = new JLabel("Duration");
		l3duratn.setForeground(Color.GRAY);
		l3duratn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l3duratn.setBounds(425, 83, 145, 28);
		persnlDetailPanel_1_2.add(l3duratn);
		
		JLabel col1_5_1_1_1_2 = new JLabel(":");
		col1_5_1_1_1_2.setForeground(Color.BLACK);
		col1_5_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_5_1_1_1_2.setBounds(577, 83, 14, 28);
		persnlDetailPanel_1_2.add(col1_5_1_1_1_2);
		
		JTextArea textArea_1_1_2 = new JTextArea();
		textArea_1_1_2.setBorder(new LineBorder(new Color(192, 192, 192)));
		textArea_1_1_2.setBounds(191, 168, 189, 28);
		persnlDetailPanel_1_2.add(textArea_1_1_2);
		
		textField_18 = new JTextField();
		textField_18.setEnabled(false);
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBackground(Color.WHITE);
		textField_18.setBounds(191, 135, 189, 28);
		persnlDetailPanel_1_2.add(textField_18);
		
		//////////////////////
		
		 
	     
		///////////////////////
		
		
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		textField_19.setBounds(191, 101, 189, 28);
		persnlDetailPanel_1_2.add(textField_19);
		
		textField_20 = new JTextField();
		textField_20.setColumns(10);
		textField_20.setBounds(597, 183, 189, 28);
		persnlDetailPanel_1_2.add(textField_20);
		
		textField_21 = new JTextField();
		textField_21.setEnabled(false);
		textField_21.setEditable(false);
		textField_21.setColumns(10);
		textField_21.setBackground(Color.WHITE);
		textField_21.setBounds(597, 149, 189, 28);
		persnlDetailPanel_1_2.add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setColumns(10);
		textField_22.setBounds(597, 115, 189, 28);
		persnlDetailPanel_1_2.add(textField_22);
		
		textField_24 = new JTextField();
		textField_24.setEnabled(false);
		textField_24.setEditable(false);
		textField_24.setColumns(10);
		textField_24.setBackground(Color.WHITE);
		textField_24.setBounds(191, 34, 189, 28);
		persnlDetailPanel_1_2.add(textField_24);
		
		v3duratn = new JTextField();
		v3duratn.setColumns(10);
		v3duratn.setBounds(597, 82, 189, 28);
		persnlDetailPanel_1_2.add(v3duratn);
		
		JLabel l3passNo = new JLabel("Passport No.");
		l3passNo.setForeground(Color.GRAY);
		l3passNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l3passNo.setBounds(36, 66, 110, 28);
		persnlDetailPanel_1_2.add(l3passNo);
		
		JLabel col1_2_1_1_1_2 = new JLabel(":");
		col1_2_1_1_1_2.setForeground(Color.BLACK);
		col1_2_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		col1_2_1_1_1_2.setBounds(156, 67, 14, 28);
		persnlDetailPanel_1_2.add(col1_2_1_1_1_2);
		
		textField_26 = new JTextField();
		textField_26.setEnabled(false);
		textField_26.setEditable(false);
		textField_26.setColumns(10);
		textField_26.setBackground(Color.WHITE);
		textField_26.setBounds(191, 66, 189, 28);
		persnlDetailPanel_1_2.add(textField_26);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(597, 46, 50, 28);
		persnlDetailPanel_1_2.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(654, 46, 50, 28);
		persnlDetailPanel_1_2.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(710, 46, 75, 28);
		persnlDetailPanel_1_2.add(comboBox_2);
		
		JLabel l3dd = new JLabel("DD");
		l3dd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3dd.setBounds(611, 10, 27, 44);
		persnlDetailPanel_1_2.add(l3dd);
		
		JLabel l3mm = new JLabel("MM");
		l3mm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3mm.setBounds(666, 10, 27, 44);
		persnlDetailPanel_1_2.add(l3mm);
		
		JLabel l3yy = new JLabel("YY");
		l3yy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3yy.setBounds(735, 10, 27, 44);
		persnlDetailPanel_1_2.add(l3yy);
		
		
		accountDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Font font = accountDashboard.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
				
				accountDashboard.setFont(font.deriveFont(attributes));
				CardLayout cl = (CardLayout)trypanel.getLayout();
				
				cl.show(trypanel, "name_497056365022900");
			}
		});
		
		myPersonal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Font font = myPersonal.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
				myPersonal.setFont(font.deriveFont(attributes));
				
				myPersonal.setFont(font.deriveFont(attributes));
				CardLayout cl = (CardLayout)trypanel.getLayout();
				
				cl.show(trypanel, "name_497101350644300");
			}
		});
		
		editDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Font font = editDetails.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
				editDetails.setFont(font.deriveFont(attributes));
				
				editDetails.setFont(font.deriveFont(attributes));
				CardLayout cl = (CardLayout)trypanel.getLayout();
				
				cl.show(trypanel, "name_497745390804800");
			}
		});

		
		
		/**JPanel daysLeft = new JPanel();
		daysLeft.setBounds(531, 237, 396, 356);
		daysLeft.setBackground(new Color(247, 247, 247));
		frame.getContentPane().add(daysLeft);
		daysLeft.setLayout(new CardLayout(0, 0));
		
		
		JLabel lblNewLabel = new JLabel("10");
		
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		daysLeft.add(lblNewLabel, "name_495109710828700");
		
		DisplayImage(daysLeft, "resize2.png");
		*/
		
	
	}
	
	private void DisplayImage(JPanel jp, String url) {
		
	    JLabel jl=new JLabel();
	    jl.setBackground(SystemColor.inactiveCaptionBorder);
	    ImageIcon i = new ImageIcon(getClass().getResource(url));
	    //i = new ImageIcon(i.getImage().getScaledInstance(jp.getWidth(), jp.getHeight(), Image.SCALE_SMOOTH));
	    jl.setIcon(i);
	    jp.add(jl);
	    
	}
	
	private String getHash(String s) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte by[] = md.digest(s.getBytes());
			String t = Base64.getEncoder().encodeToString(by);
			return t;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
