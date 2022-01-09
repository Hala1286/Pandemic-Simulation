import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program Name: UserInput.java
 * Purpose: frame allows the user to input certain parameters about a population in terms of how many people are vaccinated or non vaccinated , and recovered.
 * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */

public class User_Input extends JPanel {

	private JPanel upperPanel, bottomPanel, popComPanel, noImmComPanel, oneShotCombPanel, twoShotCombPanel,
			naturalComPanel;
	private JSlider noImmSlider, oneShotSlider, twoShotSlider, natSlider;
	private JLabel noImmLabel, oneShotLabel, twoShotLabel, natLabel , populationLb , nonVacLb , oneShotLb , twoShotLb;
	private JComboBox popD1, popD2, popD3, popD4; 
	private String[] numberArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	

	private Simulator theSimulator;

	private String pop;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu AppActionsMenu, aboutMenu;
	private JMenuItem startItem, stopItem, firstNameItem, secondNameItem, thirdNameItem;
	Font font = new Font("Times New Roman", Font.PLAIN, 16);

	// variables to save the inputs
	public static int populationNum;
	public static int noImmInt, oneShotInt, twoShotsInt, naturalImmInt;

	
	//constructor
	public User_Input(Simulator simulator) {
		theSimulator = simulator;
		// REVISION for extra text fields being added...change rows to 6 from 3
		this.setSize(400, 400);
		// this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		doUpperPanel();
		this.add(upperPanel, BorderLayout.CENTER);	
		doBottomPanel();
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
   //  void method to build the bottom panel 
 	private void doBottomPanel()
	{
		
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.magenta);
		bottomPanel.add(new Label("Population:", JLabel.LEFT));
		populationLb = new JLabel();
		populationLb.setForeground(Color.BLUE);
		bottomPanel.add(populationLb);
		bottomPanel.add(new Label("Non vaccinated:" , JLabel.LEFT));
		nonVacLb = new JLabel();
		nonVacLb.setForeground(Color.BLUE);
		bottomPanel.add(nonVacLb);
		//bottomPanel.add(new Label(Integer.toString(nonVaccinated)));
		bottomPanel.add(new Label("One Shot:" , JLabel.LEFT));
		oneShotLb = new JLabel();
		oneShotLb.setForeground(Color.cyan);
		bottomPanel.add(oneShotLb);
		bottomPanel.add(new Label("two Shots:" , JLabel.LEFT));
		
		twoShotLb = new JLabel();
		twoShotLb.setForeground(Color.yellow);
		bottomPanel.add(twoShotLb);
		
	
		
	}
   //void method to build the upper panel
	public void doUpperPanel() {
		upperPanel = new JPanel();
		upperPanel.setBackground(Color.pink);
		upperPanel.setLayout(new GridLayout(5, 2, 10, 10));
		upperPanel.add(new JLabel("Size of Population: ", JLabel.RIGHT));
		// add the population size by using JComboboxes
		popComPanel = new JPanel();
		popD1 = new JComboBox(numberArray);
		popD2 = new JComboBox(numberArray);
		popD3 = new JComboBox(numberArray);
		popD4 = new JComboBox(numberArray);

		// add the Jcomboboxes to the panel
		popComPanel.add(popD1);
		popComPanel.add(popD2);
		popComPanel.add(popD3);
		popComPanel.add(popD4);
		upperPanel.add(popComPanel);

		// add the no immunity level

		upperPanel.add(new JLabel("No Immunity: ", JLabel.RIGHT));
		noImmComPanel = new JPanel();
		
		noImmComPanel.setLayout(new BoxLayout(noImmComPanel, BoxLayout.Y_AXIS) );
		noImmSlider = new JSlider(JSlider.HORIZONTAL,0,100,0);
		noImmSlider.setMajorTickSpacing(50);
		noImmSlider.setPaintTicks(true);
		noImmSlider.setPaintLabels(true);
		noImmSlider.setAlignmentX(LEFT_ALIGNMENT);
		
		noImmLabel = new JLabel("0");
		noImmLabel.setAlignmentX(LEFT_ALIGNMENT);

	  noImmComPanel.add(noImmLabel);
		noImmComPanel.add(noImmSlider);
		upperPanel.add(noImmComPanel);
		

		
		//add one Shot level
		upperPanel.add(new JLabel("One Shot Vaccine: ",JLabel.RIGHT));
		oneShotCombPanel = new JPanel();
		
		oneShotCombPanel.setLayout(new BoxLayout(oneShotCombPanel, BoxLayout.Y_AXIS) );
		oneShotSlider = new JSlider(JSlider.HORIZONTAL,0,100,0);
		oneShotSlider.setMajorTickSpacing(50);
		oneShotSlider.setPaintTicks(true);
		oneShotSlider.setPaintLabels(true);
		oneShotSlider.setAlignmentX(LEFT_ALIGNMENT);
		
		oneShotLabel = new JLabel("0");
		oneShotLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		oneShotCombPanel.add(oneShotLabel);
		oneShotCombPanel.add(oneShotSlider);
		upperPanel.add(oneShotCombPanel);
		
		
		//add two shot level
		upperPanel.add(new JLabel("Two Shot Vaccination: ",JLabel.RIGHT));
		twoShotCombPanel = new JPanel();
		
		twoShotCombPanel.setLayout(new BoxLayout(twoShotCombPanel, BoxLayout.Y_AXIS) );
		twoShotSlider = new JSlider(JSlider.HORIZONTAL,0,100,0);
		twoShotSlider.setMajorTickSpacing(50);
		twoShotSlider.setPaintTicks(true);
		twoShotSlider.setPaintLabels(true);
		twoShotSlider.setAlignmentX(LEFT_ALIGNMENT);
		
		twoShotLabel = new JLabel("0");
		twoShotLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		twoShotCombPanel.add(twoShotLabel);
		twoShotCombPanel.add(twoShotSlider);
		upperPanel.add(twoShotCombPanel);
		
		//Add Natural immunity level
		upperPanel.add(new JLabel("Natural Immunity: ",JLabel.RIGHT));
		naturalComPanel = new JPanel();
		
		naturalComPanel.setLayout(new BoxLayout(naturalComPanel, BoxLayout.Y_AXIS) );
		natSlider = new JSlider(JSlider.HORIZONTAL,0,100,0);
		natSlider.setMajorTickSpacing(50);
		natSlider.setPaintTicks(true);
		natSlider.setPaintLabels(true);
		natSlider.setAlignmentX(LEFT_ALIGNMENT);
		
		natLabel = new JLabel("0");
		natLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		naturalComPanel.add(natLabel);
		naturalComPanel.add(natSlider);
		upperPanel.add(naturalComPanel);
				
		//Register listeners for the sliders
		SliderListener nanny = new SliderListener();
		noImmSlider.addChangeListener(nanny);
		oneShotSlider.addChangeListener(nanny);
		twoShotSlider.addChangeListener(nanny);
		natSlider.addChangeListener(nanny);
		
	}

	
	
	/**
	  * class Name: SliderListener 
	  * Purpose:  an inner class implements ChangeListener
	  * */
	private class SliderListener implements ChangeListener
	{
		
		@Override
		public void stateChanged(ChangeEvent e)
		{
		
			noImmInt = noImmSlider.getValue();
			pop = popD1.getSelectedItem().toString() + popD2.getSelectedItem().toString() + popD3.getSelectedItem().toString() + popD4.getSelectedItem().toString();
			populationLb.setText(pop); 
			
			oneShotInt = oneShotSlider.getValue();
			twoShotsInt = twoShotSlider.getValue();
			naturalImmInt = natSlider.getValue();
			
			oneShotLb.setText(Integer.toString(oneShotInt));
			twoShotLb.setText(Integer.toString(twoShotsInt)); 
			//blue = bSlider.getValue();
			nonVacLb.setText(Integer.toString(noImmInt + naturalImmInt));
			//Display each slider's value in its corresponding JLabel 
			noImmLabel.setText(noImmInt +"%");
			oneShotLabel.setText(oneShotInt +"%");
			twoShotLabel.setText(twoShotsInt +"%");
			natLabel.setText(naturalImmInt +"%");
			
		}
		
		
	}//end inner class
	
	/**
   * Method Name: CheckUserInput 
   * Purpose: to validate the user inputs for the percentages of the immunity to be exactly one hundred and the population value should be entered. 
   * Parameter: void
   * return: void
   * */
	public void CheckUserInput() {
		
		pop = popD1.getSelectedItem().toString() + popD2.getSelectedItem().toString() + popD3.getSelectedItem().toString() + popD4.getSelectedItem().toString();
		populationNum = Integer.parseInt(pop);
		 
		 if((noImmInt + oneShotInt + twoShotsInt + naturalImmInt) != 100) {
			 JOptionPane.showMessageDialog(null, "The sum of Level of Immunity percentages Should be 100%");
		 }
		 else if(populationNum ==0) {
			 JOptionPane.showMessageDialog(null, "Please Enter the population number!!");
		 }
		 else {
				theSimulator.Start(populationNum, noImmInt, oneShotInt, twoShotsInt, naturalImmInt);
		 }
		
	}
	
}
// end class
