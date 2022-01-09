/**
 * Program Name:About.java
 * Purpose: Show Information about the program name , version, and coder names. 
 * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class About extends JFrame{
private JLabel appName , codername , appVer;
private JPanel appPanel , teamPanel; 
Font font1 = new Font("Times New Roman", Font.BOLD, 50);
Font font2 = new Font("Times New Roman", Font.ITALIC, 30);
Font font3 = new Font("Times New Roman", Font.BOLD, 35);

//constructor
  public About(){
	
	super("About");
	this.setForeground(Color.pink);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(800, 700);
	this.setLayout(new BorderLayout());
	buildAppPanel();
	this.add(appPanel , BorderLayout.CENTER);
	
	buildTeamPanel();
	this.add(teamPanel , BorderLayout.SOUTH);
	
	

	this.setVisible(true);
	this.setAlwaysOnTop(true);
	
}
 //helping method to build the team panel.
private void buildTeamPanel()
{
	teamPanel = new JPanel();
	teamPanel.setBackground(Color.PINK);
	teamPanel.setLayout(new GridLayout(2 ,1 ,50 ,50));
	JLabel coded = new JLabel(" Coded By:  " , JLabel.CENTER);
	teamPanel.add(coded);
	coded.setFont(font3);
	coded.setForeground(Color.darkGray);
	JPanel names = new JPanel();
	names.setBackground(Color.PINK);
	JLabel firstName =  new JLabel("Behnaz Najafi  ",JLabel.LEFT);
	names.add(firstName);
	JLabel secName =new JLabel(" Hadeel Abuhajer  " , JLabel.LEFT);
	names.add(secName);
	JLabel thirdName = new JLabel(" Hala Hammad " , JLabel.LEFT);
	names.add(thirdName);
	firstName.setFont(font2);
	firstName.setForeground(Color.GRAY);
	secName.setFont(font2);
	secName.setForeground(Color.GRAY);
	thirdName.setFont(font2);
	thirdName.setForeground(Color.GRAY);
	teamPanel.add(names);
	
}

//helping method to build the appPanel.
private void buildAppPanel()
{
	appPanel = new JPanel();
	appPanel.setBackground(Color.PINK);
	appPanel.setLayout(new GridLayout(3 , 1 ,4 ,4));
	appName = new JLabel("  Pandemic Simulator Application");
	appName.setForeground(Color.darkGray);
	appName.setFont(font1);
	appVer = new JLabel("Version 1.1" , JLabel.CENTER);
	appVer.setForeground(Color.GRAY);
	appVer.setFont(font2);
	
	
	appPanel.add(appName);
	appPanel.add(appVer);
}

	//end main
}
//end class