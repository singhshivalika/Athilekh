package defaultt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JScrollPane;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.table.TableColumn;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class officialls extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf4;
	private JTextField tf5;
	private ButtonGroup bg=new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					officialls frame = new officialls();
					frame.setBounds(160, 40, 1179, 781);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public officialls() {
		setTitle("ATHILEKH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1139, 783);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//bg.add(b);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 303, 1170, 295);
		panel_1.setBackground(new Color(173,216,230));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton R1 = new JRadioButton("Category1");
		R1.setBounds(156, 43, 103, 21);
		panel_1.add(R1);
		bg.add(R1);
		
		JRadioButton R3 = new JRadioButton("Category2");
		R3.setBounds(156, 121, 103, 21);
		panel_1.add(R3);
		bg.add(R3);
		
		JRadioButton R4 = new JRadioButton("Category3");
		R4.setBounds(156, 206, 103, 21);
		panel_1.add(R4);
		bg.add(R4);
		
		JLabel image_label1 = new JLabel("");
		image_label1.setIcon(new ImageIcon("C:\\Users\\lenovo\\Downloads\\icon\\resize1.png"));
		image_label1.setBounds(5, 15, 100, 85);
		
		tf1 = new JTextField();
		tf1.setBackground(Color.WHITE);
		tf1.setBounds(566, 42, 119, 24);
		panel_1.add(tf1);
		tf1.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 110, 1153, 10);
		panel.setBackground(new Color(112, 181, 201));
		
		JLabel text_label = new JLabel("Athilekh");
		text_label.setBounds(133, 39, 127, 40);
		text_label.setFont(new Font("Segoe Script", Font.BOLD, 24));
		text_label.setForeground(new Color(165, 100, 52));
		
		
		JButton pdf = new JButton("PDF");
		pdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				pdf.setBackground(new Color(240,240,240));

			}
		});
		pdf.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				pdf.setBackground(new Color(173,216,230));
			}
		});
		
		
		pdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int left=Integer.parseInt(tf1.getText());
//				String str1=tf4.getText();
				try {
					String filename="C:\\Users\\lenovo\\Desktop\\Athilekh.pdf";
					Document document =new Document();
					
					PdfWriter.getInstance(document,new FileOutputStream(filename));
					document.open();
					//add para
					LocalDate localDate = LocalDate.now();  //getting current date
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
					String formattedString = localDate.format(formatter);
					Paragraph p=new Paragraph(formattedString);
					p.setAlignment(Paragraph.ALIGN_RIGHT);    
					document.add(new Paragraph(p));
					document.add(Image.getInstance("C:\\Users\\lenovo\\Downloads\\icon\\resize1.png"));
					Paragraph pp=new Paragraph("REPORT",FontFactory.getFont(FontFactory.TIMES_BOLD,25f));
					pp.setAlignment(Paragraph.ALIGN_CENTER);
					document.add(new Paragraph(pp));
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					DBConnection obJDBConnection = new DBConnection();
					Connection connection = obJDBConnection.getConnection();
					PreparedStatement ps1=null,ps2=null,ps3=null;
					ResultSet rs1=null,rs2=null,rs3=null;
					PdfPTable table= new PdfPTable(12);
					table.setWidthPercentage(100);
					table.setWidths(new int[]{2,6,5,5,5,5,6,5,4,5,5,9});
					PdfPCell c1= new PdfPCell(new Phrase("ID",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c1.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c1);
					PdfPCell c2=new PdfPCell(new Phrase("First Name",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c2.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c2);
					PdfPCell c3=new PdfPCell(new Phrase("Last Name",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c3.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c3);
					PdfPCell c4=new PdfPCell(new Phrase("Gender",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c4.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c4);
					PdfPCell c5=new PdfPCell(new Phrase("Building no.",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c5.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c5);
					PdfPCell c6=new PdfPCell(new Phrase("Street Name",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c6.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c6);
					PdfPCell c7=new PdfPCell(new Phrase("City",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c7.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c7);
					PdfPCell c8=new PdfPCell(new Phrase("State",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c8.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c8);
					PdfPCell c9=new PdfPCell(new Phrase("Post days",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c9.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c9);
					PdfPCell c12=new PdfPCell(new Phrase("Remaining days",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c12.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c12);
					PdfPCell c11=new PdfPCell(new Phrase("Date of arrival",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c11.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c11);
					PdfPCell c10=new PdfPCell(new Phrase("Email",FontFactory.getFont(FontFactory.COURIER_BOLD,5.0f)));
					c10.setBackgroundColor(BaseColor.ORANGE);
					table.addCell(c10);
					
					
					
					if(R1.isSelected()) {
					document.add(new Paragraph("The below table shows visitor's information based on number of days left of his/her:",FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,12f)));
					document.add(new Paragraph(" "));
					
					int left=Integer.parseInt(tf1.getText());
					String query1="SELECT f.foreigner_ID ,f.name_firstName ,f.name_lastName , f.gender ,f.address_buildingNo, f.address_streetName, f.address_city , f.address_state ,e.email FROM Foreigner f, Email e WHERE f.foreigner_ID IN (SELECT v.foreigner_ID FROM visitor v, Visa vi WHERE (vi.duration-DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl)="+left+" )) AND e.foreigner_ID=f.foreigner_ID";
					ps1=connection.prepareStatement(query1);
					rs1=ps1.executeQuery();
					String query2="SELECT (DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl)) as post_days,(vi.duration-DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl)) as remaining_days FROM Visitor v,visa vi WHERE (vi.duration-DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl))="+left+" AND v.foreigner_ID IN (SELECT foreigner_ID FROM foreigner)";
					ps2=connection.prepareStatement(query2);
					rs2=ps2.executeQuery();
					String query3="SELECT v.dt_tm_arvl FROM visitor v,visa vi WHERE (vi.duration-DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl))="+left;
					ps3=connection.prepareStatement(query3);
					rs3=ps3.executeQuery();
					
					table.setHeaderRows(1);
					
					while(rs1.next() && rs2.next() && rs3.next())
					{
						String id=rs1.getString("foreigner_ID");
						PdfPCell s1=new PdfPCell(new Phrase(id,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s1);
						String fname=rs1.getString("name_firstName");
						PdfPCell s2=new PdfPCell(new Phrase(fname,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s2);
						String lname=rs1.getString("name_lastName");
						PdfPCell s3=new PdfPCell(new Phrase(lname,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s3);
						String gen=rs1.getString("gender");
						PdfPCell s4=new PdfPCell(new Phrase(gen,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s4);
						String bno=rs1.getString("address_buildingNo");
						PdfPCell s5=new PdfPCell(new Phrase(bno,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s5);
						String sname=rs1.getString("address_streetName");
						PdfPCell s6=new PdfPCell(new Phrase(sname,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s6);
						String city=rs1.getString("address_city");
						PdfPCell s7=new PdfPCell(new Phrase(city,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s7);
						String state=rs1.getString("address_state");
						PdfPCell s8=new PdfPCell(new Phrase(state,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s8);
						String pday=rs2.getString("post_days");
						PdfPCell s9=new PdfPCell(new Phrase(pday,FontFactory.getFont(FontFactory.COURIER,8f)));//post days
						table.addCell(s9);
						String rday=String.valueOf(left);     //remaining days
						PdfPCell s10=new PdfPCell(new Phrase(rday,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s10);
						String date_arr=rs3.getString("dt_tm_arvl");
						PdfPCell s11=new PdfPCell(new Phrase(date_arr,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s11);
						String email=rs1.getString("email");
						PdfPCell s12=new PdfPCell(new Phrase(email,FontFactory.getFont(FontFactory.COURIER,8f)));
						table.addCell(s12);
						
					}
					document.add(table);
					}
					
					if(R3.isSelected()) {
						document.add(new Paragraph("The below table shows visitor's information based on first name entered",FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,12f)));
						document.add(new Paragraph(" "));

						String str1=tf4.getText();
						String query1="SELECT f.foreigner_ID,f.name_firstName,f.name_middleName,f.name_lastName,f.gender,f.address_buildingNo, f.address_streetName,f.address_city,f.address_state,e.email FROM foreigner f,email e WHERE name_firstName = '"+str1+"' AND e.foreigner_ID=f.foreigner_ID";
						ps1=connection.prepareStatement(query1);
						rs1=ps1.executeQuery();
						String query2="SELECT (DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl)) as post_days,(vi.duration-DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl)) as remaining_days FROM Visitor v,visa vi WHERE v.foreigner_ID=(SELECT foreigner_ID FROM foreigner WHERE name_firstName='"+str1+"')";
						ps2=connection.prepareStatement(query2);
						rs2=ps2.executeQuery();
					//	String query3="SELECT dt_tm_arvl FROM Visitor vi WHERE vi.Foreigner";
						//ps3=connection.prepareStatement(query3);
						//rs3=ps3.executeQuery();
						
						table.setHeaderRows(1);
						
						while(rs1.next() && rs2.next())
						{
							com.itextpdf.text.Font fs=FontFactory.getFont(FontFactory.COURIER_BOLD,6.7f);
							String id=rs1.getString("foreigner_ID");
							PdfPCell s1=new PdfPCell(new Phrase(id,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s1);
							String fname=rs1.getString("name_firstName");
							PdfPCell s2=new PdfPCell(new Phrase(fname,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s2);
							String lname=rs1.getString("name_lastName");
							PdfPCell s3=new PdfPCell(new Phrase(lname,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s3);
							String gen=rs1.getString("gender");
							PdfPCell s4=new PdfPCell(new Phrase(gen,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s4);
							String bno=rs1.getString("address_buildingNo");
							PdfPCell s5=new PdfPCell(new Phrase(bno,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s5);
							String sname=rs1.getString("address_streetName");
							PdfPCell s6=new PdfPCell(new Phrase(sname,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s6);
							String city=rs1.getString("address_city");
							PdfPCell s7=new PdfPCell(new Phrase(city,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s7);
							String state=rs1.getString("address_state");
							PdfPCell s8=new PdfPCell(new Phrase(state,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s8);
							String pday=rs2.getString("post_days");      //post days
							PdfPCell s9=new PdfPCell(new Phrase(pday,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s9);
							String rday=rs2.getString("remaining_days");     //remaining days
							PdfPCell s10=new PdfPCell(new Phrase(rday,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s10);
							String date_arr="HEF";
							PdfPCell s11=new PdfPCell(new Phrase(date_arr,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(date_arr);
							String email=rs1.getString("email");
							PdfPCell s12=new PdfPCell(new Phrase(email,FontFactory.getFont(FontFactory.COURIER,8f)));
							table.addCell(s12);
							
						}
						document.add(table);
						}
					
					if(R4.isSelected()) {
						document.add(new Paragraph("The below table shows visitor's information based on arrival date",FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,12f)));
						document.add(new Paragraph(" "));
						
						String str3=tf5.getText();
						String query1="SELECT f.foreigner_ID ,f.name_firstName ,f.name_lastName , f.gender ,f.address_buildingNo, f.address_streetName, f.address_city , f.address_state ,e.email FROM Foreigner f, Email e WHERE e.foreigner_ID=f.foreigner_ID AND f.foreigner_ID=(SELECT foreigner_ID FROM Visitor WHERE dt_tm_arvl='"+str3+"')";
						ps1=connection.prepareStatement(query1);
						rs1=ps1.executeQuery();
						String query2="SELECT (DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl)) as post_days,(vi.duration-DATEDIFF(CURRENT_TIMESTAMP,v.dt_tm_arvl)) as remaining_days FROM Visitor v,visa vi WHERE dt_tm_arvl='"+str3+"' AND v.visa_ID=vi.visa_ID";
						ps2=connection.prepareStatement(query2);
						rs2=ps2.executeQuery();
					//	String query3="SELECT dt_tm_arvl FROM Visitor vi WHERE vi.Foreigner";
						//ps3=connection.prepareStatement(query3);
						//rs3=ps3.executeQuery();
						
						table.setHeaderRows(1);
						
						while(rs1.next() && rs2.next())
						{
							com.itextpdf.text.Font fs=FontFactory.getFont(FontFactory.COURIER_BOLD,6.7f);
							String id=rs1.getString("foreigner_ID");
							PdfPCell s1=new PdfPCell(new Phrase(id,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(id);
							String fname=rs1.getString("name_firstName");
							PdfPCell s2=new PdfPCell(new Phrase(fname,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s2);
							String lname=rs1.getString("name_lastName");
							PdfPCell s3=new PdfPCell(new Phrase(lname,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s3);
							String gen=rs1.getString("gender");
							PdfPCell s4=new PdfPCell(new Phrase(gen,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s4);
							String bno=rs1.getString("address_buildingNo");
							PdfPCell s5=new PdfPCell(new Phrase(bno,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s5);
							String sname=rs1.getString("address_streetName");
							PdfPCell s6=new PdfPCell(new Phrase(sname,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s6);
							String city=rs1.getString("address_city");
							PdfPCell s7=new PdfPCell(new Phrase(city,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s7);
							String state=rs1.getString("address_state");
							PdfPCell s8=new PdfPCell(new Phrase(state,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s8);
							String pday=rs2.getString("post_days");      //post days
							PdfPCell s9=new PdfPCell(new Phrase(pday,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s9);
							String rday=rs2.getString("remaining_days");     //remaining days
							PdfPCell s10=new PdfPCell(new Phrase(rday,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s10);
							String date_arr="HEF";
							PdfPCell s11=new PdfPCell(new Phrase(date_arr,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(date_arr);
							String email=rs1.getString("email");
							PdfPCell s12=new PdfPCell(new Phrase(email,FontFactory.getFont(FontFactory.COURIER,8.5f)));
							table.addCell(s12);
							
						}
						document.add(table);
						}
					

					//add table
					//document.add(table);
					document.close();
					System.out.println("finished");
				}
				catch (Exception es) {
					System.out.println(es);
				}
			}
		});
		pdf.setBounds(722, 659, 100, 35);
		
		//table.setBounds(50,50,200,230);
		
		//this.setSize(300,400);
		//this.setVisible(true);
		
		JButton clear = new JButton("CLEAR");
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				clear.setBackground(new Color(240,240,240));
			}
		});
		clear.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				clear.setBackground(new Color(173,216,230));
			}
		});
		clear.setBounds(255, 659, 100, 35);
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf1.setText("");
				tf4.setText("");
				tf5.setText("");
				
			}
		});
		
		JButton exit = new JButton("EXIT");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setBackground(new Color(240,240,240));

			}
		});
		exit.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				exit.setBackground(new Color(173,216,230));

			}
		});
		exit.setBounds(485, 659, 106, 35);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
	//	contentPane.add(label1);
	//	contentPane.add(tf1);
		contentPane.add(image_label1);
		contentPane.add(text_label);
		contentPane.add(panel);
		contentPane.add(clear);
		contentPane.add(exit);
		contentPane.add(pdf);
		
		JLabel lblNewLabel = new JLabel("Getting Visitor's Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(385, 172, 303, 29);
		contentPane.add(lblNewLabel);
		
		JLabel label2 = new JLabel("No. of days left:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label2.setBounds(390, 40, 135, 24);
		panel_1.add(label2);
		
		JLabel label4 = new JLabel("First Name:");
		label4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label4.setBounds(390, 116, 135, 24);
		panel_1.add(label4);
		
		tf4 = new JTextField();
		tf4.setBackground(Color.WHITE);
		tf4.setBounds(566, 120, 119, 24);
		panel_1.add(tf4);
		tf4.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Arrival date:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(390, 203, 140, 21);
		panel_1.add(lblNewLabel_1);
		
		tf5 = new JTextField();
		tf5.setBounds(567, 205, 118, 24);
		panel_1.add(tf5);
		tf5.setColumns(10);
		
		JLabel label5 = new JLabel("SORT BY");
		label5.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label5.setBounds(156, 10, 103, 21);
		panel_1.add(label5);
		
		
	}
}
