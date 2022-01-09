import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Program Name: MainFrame.java 
 * Purpose: - frame has a menu, configuration panel, information panel, graphic panel and simulation panel.
 *          - The application will simulate a period of three weeks in which there will be 
 *             one infected person in the population at the start of the simulation with 
 *             corresponding  written and graphic information about the number of infected people ,
 *              partially vaccinated, fully vaccinated and recovered
 *            that will help the user to see how the disease can spread, based on certain assumptions 
 *            about the level of protection against the disease in the rest of the population.
 *
 * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */
public class MainFrame extends JFrame {
  JPanel leftPanel, rightPanel;
	Font font = new Font("Times New Roman", Font.PLAIN, 16);
	Simulator simulator ;
  User_Input input ;
  
  //constructor
  public MainFrame() {
		super("Pandemic Simulation App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2,3,3));
		this.setSize(1250, 700);
    loadMenu();

     simulator = new Simulator();
     input =  new User_Input(simulator);
    leftPanel = new JPanel(new GridLayout(2,1, 10,10));
    leftPanel.add(input);
		JPanel multiInfoPanel = new JPanel(new GridLayout(1,2,1,1));
		multiInfoPanel.add(new InformationPanel(simulator));
		multiInfoPanel.add(new GraphicPanel(simulator));
    leftPanel.add(multiInfoPanel);
    this.add(leftPanel);


    rightPanel = new JPanel(new BorderLayout());
    if(simulator != null) {
    rightPanel.add(simulator ,BorderLayout.CENTER); // simulator
    
    rightPanel.add(Simulator.CreateLabelsPanel(), BorderLayout.SOUTH);
    this.add(rightPanel);
    }
    else 
    {
    	rightPanel.add(new About() ,BorderLayout.CENTER); 
    }
		//this.pack();
		this.setVisible(true);
  }

  /**
   * Method Name: loadMenu
   * Purpose: create the Menu to enable  the user to start , pause , resume, report and see more information about the project
   *               also make some keyStrokes "shortcuts" to run the program
   * Parameter: void
   * return: void
   * */
  void loadMenu() {
	  JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		// create some File and Element menus
		JMenu appActionsMenu = new JMenu("Simulation");
		appActionsMenu.setFont(font);
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setFont(font);
		aboutMenu.addMenuListener(new AboutMenuListener());

		// Set the MNEMONICS for each menu so that they can be opened from the keyboard
		appActionsMenu.setMnemonic('S');
		aboutMenu.setMnemonic('A');
		// add the menus to the JMenuBar
		menuBar.add(appActionsMenu);

		JMenuItem startItem = appActionsMenu.add("Start");
		startItem.setFont(font);
		JMenuItem resumeItem = appActionsMenu.add("Resume");
		resumeItem.setFont(font);
		JMenuItem pauseItem = appActionsMenu.add("Pause");
		pauseItem.setFont(font);
		JMenuItem reportItem = appActionsMenu.add("Report");
		reportItem.setFont(font);
		startItem.setAccelerator(KeyStroke.getKeyStroke('T', Event.CTRL_MASK));
		resumeItem.setAccelerator(KeyStroke.getKeyStroke('M', Event.CTRL_MASK));
		
		
		pauseItem.setAccelerator(KeyStroke.getKeyStroke('P', Event.CTRL_MASK));
		reportItem.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));


		menuBar.add(aboutMenu);
		startItem.addActionListener(new MenuItemsListener() );
		pauseItem.addActionListener(new MenuItemsListener() );
		resumeItem.addActionListener(new MenuItemsListener() );
		reportItem.addActionListener(new MenuItemsListener() ); 
  }
  
  
  /**
  * class Name: AboutMenuListener 
  * Purpose:  an inner class implements MenuListener
  * */
  
  class AboutMenuListener implements MenuListener {

		@Override
		public void menuSelected(MenuEvent e)
		{
			About about = new About();
			
		}

		@Override
		public void menuDeselected(MenuEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuCanceled(MenuEvent e)
		{
			// TODO Auto-generated method stub
			
		}

   
  }
 /**
  * class Name: MenuItemsListener 
  * Purpose:  an inner class implements ActionListener
  * */
  
  private class MenuItemsListener implements ActionListener
  {

      @Override
      public void actionPerformed(ActionEvent ev)
      {
          if(ev.getActionCommand().equals("Start") )
          {
          	input.CheckUserInput();
          	
    				}
          
          else  if(ev.getActionCommand().equals("Pause"))
          {
              simulator.pause();
          }
         
          else if(ev.getActionCommand().equals("Resume"))
          {
              simulator.resume();
          }
          
          else if(ev.getActionCommand().equals("Report"))
          {
          	ReportResults report =  new ReportResults(simulator);
          }
          
          

      }//end actionPerformed()

  }//end inner class
  
  	
  
    //main method
	public static void main(String[] args) {
		new MainFrame();
	}





	
};
