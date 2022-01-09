
 /**
*  Program Name:Simulator.java
 * Purpose:     The application will simulate a period of three weeks in which there will be 
 *              one infected person in the population at the start of the simulation. 
 *             - Then, this infected person will interact with the rest of the population 
 *              and we will be able to see how the disease can spread, based on certain assumptions 
 *              about the level of protection against the disease in the rest of the population 
 *              ( i.e. from being vaccinated, or from having already had the disease and thereby acquiring natural immunity).
 *
 * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

//import Simulator.PersonListener;
public class Simulator extends JPanel {
	public final static int WIDTH = 600, HEIGHT = 600;// size of JPanel
	public static final int IMG_DIM = 10; // size of ball to be drawn
	public static final int LAG_TIME = 200; // time in milliseconds between re-paints
	
	// constructor
	public Simulator() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBorder(new LineBorder(Color.blue));
		this.setBackground(Color.WHITE);
	}

	int no_immunity_count ;
	int natural_imm_count ;
	int one_shot_count;
	int two_shot_count;
	
	
	/**
   * Method Name: Start 
   * Purpose:    used to start the simulation depending on the the user inputs. It populate the array of type Person in the information which are received 
   *               from the user then call functions to draw the circles and put them in different places on the panel.  
   * Parameter: int , int , int , int , int
   * return:     void
   * */
	public void Start(int populationNum, int noImmInt, int oneShotInt, int twoShotsInt, int naturalImmInt) {
		age = 0;
		person = new Person[populationNum];
		no_immunity_count = populationNum * noImmInt / 100;
		natural_imm_count = populationNum * naturalImmInt / 100;
		one_shot_count = populationNum * oneShotInt / 100;
		two_shot_count = populationNum * twoShotsInt / 100;
		
		//for drawing
		int no_immunity_count1 = populationNum * noImmInt / 100;
		int natural_imm_count1 = populationNum * naturalImmInt / 100;
		int one_shot_count1 = populationNum * oneShotInt / 100;
		int two_shot_count1 = populationNum * twoShotsInt / 100;

		int diff = (no_immunity_count1 + natural_imm_count1 + one_shot_count1 + two_shot_count1) - User_Input.populationNum;
		no_immunity_count += diff;

		for (int x = 0; x < User_Input.populationNum; ++x) {
			if (no_immunity_count1 > 0) {
				person[x] = new Person((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), true, false, 1);
				no_immunity_count1--;
			} else if (natural_imm_count1 > 0) {
				person[x] = new Person((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), true, false, 4);
				natural_imm_count1--;
			} else if (one_shot_count1 > 0) {
				person[x] = new Person((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), true, false, 2);
				one_shot_count1--;
			} else if (two_shot_count1 > 0) {
				person[x] = new Person((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), true, false, 3);
				two_shot_count1--;
			}
		}
		person[0].setInfected(true);

		// create Timer and register a listener for it.
		this.time = new Timer(LAG_TIME, new PersonListener());
		this.time.start();
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		if (person != null) {
			for (int x = 0; x < person.length; x++) {
				g.setColor(person[x].getColor());
				g.fillOval(person[x].getxCoordinate(), person[x].getyCoordinate(), IMG_DIM, IMG_DIM);
			}

			g.setColor(Color.black);
			g.drawString(Integer.toString(++age), 0, 0);

			if (age > 450)
				this.time.stop();
		}
	}
	/**
   * Method Name: CreateLabelsPanel 
   * Purpose:    Building  a panel below the simulation which contains visual labels to illustrate  what does each color represents. 
   * Parameter:  void
   * return:     JPanel
   * */

	  public static JPanel CreateLabelsPanel() {
		JPanel result = new JPanel();
		JLabel lbl = new JLabel("\u2B24 No Immunity");
		lbl.setForeground(Color.blue);
		result.add(lbl); // Unicode Full Circle
		lbl = new JLabel("\u2B24 First Dose");
		lbl.setForeground(Color.cyan);
		result.add(lbl);
		lbl = new JLabel("\u2B24 Second Dose");
		lbl.setForeground(Color.yellow);
		result.add(lbl);
		lbl = new JLabel("\u2B24 Natural Immunity");
		lbl.setForeground(Color.green);
		result.add(lbl);
		lbl = new JLabel("\u2B24 Infected");
		lbl.setForeground(Color.red);
		result.add(lbl);
		lbl = new JLabel("\u2B24 Dead");
		lbl.setForeground(Color.black);
		result.add(lbl);

		return result;
	}
	  /**
	   * class Name: PersonListener 
	   * Purpose:  an inner class implements ActionListener
	   * */

	private class PersonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == time) {
				for (Person p : person) {
					if (p.isAlive()) {
						int initialImmunity = p.getImmunityStatus();
						p.move();
						if (!p.isAlive()) {
							switch (initialImmunity) {
								case 1:
									++countDiedStatus1;
									break;
								case 2:
									++countDiedStatus2;
									break;
								case 3:
									++countDiedStatus3;
									break;
							}
						} else if (p.getImmunityStatus() == 4 && initialImmunity != 4) {
							// recovered person
							++countRecovered;
						}
					}
				}

				for (int i = 0; i < person.length; ++i) {
					for (int j = 0; j < person.length; j++) {
						if (i != j) {
							if(!person[i].isInfected()) {
							if (person[i].checkCollision(person[j]) && person[j].isInfected()) {
								//if the person gets infected, we need to count them
								
								if(person[i].isInfected()) {
									//if(countInfected < person.length)
								 countInfected++;
								
								switch (person[i].getImmunityStatus()) {
									case 1:
										++countUnvaccinatedInfected;
										break;
									case 2:
										++countOneShotInfected;
										break;
									case 3:
										++countFullyVaccinatedInfected;
										break;
								
							 }//end switch
								}
							}//end if
						}//end if
						}
					}//end foe j
				}//end for i

				repaint();
			} //end time

		}// end method

	}// end inner class
	
	
	/**
  * Method Name: getAge 
  * Purpose:     to get the age of the simulation
  * Parameter:   void
  * return:     int
  * */
	public int getAge() {
		return this.age;
	}
	
	/**
	  * Method Name: getInfectedCount 
	  * Purpose:     to get the number of infected people
	  * Parameter:   void
	  * return:     int
	  * */
	public int getInfectedCount() {
		return countInfected;
	}

	
	/**
   * Method Name: getNonVaccinatedInfectedCount 
   * Purpose:     count the non vaccinated people who are infected 
   * Parameter:  void
   * return:     int
   * */
	public int getNonVaccinatedInfectedCount() {
		return countUnvaccinatedInfected;
	}
	
	/**
   * Method Name: getNonVaccinatedCount 
   * Purpose:     count the non vaccinated people
   * Parameter:  void
   * return:     int
   * */
	public int getNonVaccinatedCount() {
		int count = 0;
		for (Person p : this.person) {
			if (p.getImmunityStatus() == 1 || p.getImmunityStatus() == 4)
				++count;
		}
		return count;
	}

	/**
   * Method Name: getOneShotInfectedCount 
   * Purpose:     count the infected people who has one shot vaccination
   * Parameter:  void
   * return:     int
   * */
	public int getOneShotInfectedCount() {
		return countOneShotInfected;
	}

	/**
   * Method Name: getTwoShotInfectedCount 
   * Purpose:     count the infected people who has two shot vaccination
   * Parameter:  void
   * return:     int
   * */
	public int getTwoShotInfectedCount() {
		return countFullyVaccinatedInfected;
	}
	
	/**
   * Method Name: getOneShotCount 
   * Purpose:     count the people who has one shot vaccination
   * Parameter:  void
   * return:     int
   * */
	public int getOneShotCount() {
		int count = 0;
		for (Person p : this.person) {
			if (p.getImmunityStatus() == 2)
				++count;
		}
		return count;
	}
	
	/**
   * Method Name: getTwoShotCount 
   * Purpose:     count the people who has two shot vaccination
   * Parameter:  void
   * return:     int
   */
	public int getTwoShotCount() {
		int count = 0;
		for (Person p : this.person) {
			if (p.getImmunityStatus() == 3)
				++count;
		}
		return count;
	}

	/**
   * Method Name: getRecoveredCount 
   * Purpose: count the recovered people after infection
   * Parameter: void
   * return: int
   * */
	public int getRecoveredCount() {
		return countRecovered;
	}
	/**
  * Method Name: getDeadCount 
  * Purpose: calculate the number of dead people
  * Parameter: void
  * return: int
  * */

	public int getDeadCount() {
		int count = 0;
		for (Person p : this.person) {
			if (!p.isAlive())
				++count;
		}
		return count;
	}
  //method to pause the simulation
	public void pause() {
		Ispause = true;
		time.stop();
	}

	//method to resume the simulation
	public void resume() {
		if (age < 450)
			time.start();
	}

	/**
   * Method Name: getOfInfectedPercentage 
   * Purpose:  calculate the percentage of the total population that contracted the disease
   * Parameter: void
   * return: double
   * */
	public double getOfInfectedPercentage() {
		//System.out.println(getInfectedCount());
		return (double) Math.round((((double) getInfectedCount() / ((double) this.person.length)) * 100) * 100) / 100;
	}

	/**
   * Method Name: getNonVaccinatedInfectedPercentage 
   * Purpose:  calculate the percentage of  infected people  who are non vaccinated .
   * Parameter: void
   * return: double
   * */
	public double getNonVaccinatedInfectedPercentage() {
		return (double) Math.round((((double) getNonVaccinatedInfectedCount() / ((double) no_immunity_count)) * 100) * 100) / 100;
		
	}
	/**
   * Method Name: getOneShotInfectedPercentage 
   * Purpose:  calculate the percentage of infected people who are partially-vaccinated 
   * Parameter: void
   * return: double
   * */

	public double getOneShotInfectedPercentage() {

		return (double) Math.round((((double) getOneShotInfectedCount() / ((double) one_shot_count)) * 100) * 100) / 100;
	}
	/**
   * Method Name: getTwoShotInfectedPercentage 
   * Purpose:  calculate the percentage of infected people who are fully-vaccinated . 
   * Parameter: void
   * return: double
   * */

	public double getTwoShotInfectedPercentage() {
		return (double) Math.round((((double) getTwoShotInfectedCount() / ((double) two_shot_count)) * 100) * 100) / 100;
		//return Math.round(getTwoShotInfectedCount() * 10000.0 / two_shot_count) / 100;
	}
	/**
   * Method Name: getRecoveredPercentage 
   * Purpose:  calculate the percentage of all those who contracted the disease that recovered
   * Parameter: void
   * return: double
   * */

	public double getRecoveredPercentage() {
		return (double) Math.round((((double) getRecoveredCount() / (double) (this.getInfectedCount())) * 100) * 100) / 100;

	}
	/**
   * Method Name: getDeadPercentage 
   * Purpose:  calculate the percentage of all those who contracted the disease that died. 
   * Parameter: void
   * return: double
   * */

	public double getDeadPercentage() {
		return Math.round((((double) getDeadCount() / (double) (this.getInfectedCount())) * 100) * 100) / 100;
	}
	

	/**
   * Method Name: getDeadPercentageUnvaccinated 
   * Purpose:  calculate the percentage of non Vaccinated people who contracted the disease that died 
   * Parameter: void
   * return: double
   * */
	public double getDeadPercentageUnvaccinated() {
		
		return Math.round((countDiedStatus1 / (double)no_immunity_count * 100) * 100 ) / 100;
	}

	/**
   * Method Name: getDeadPercentagePartiallyVaccinated 
   * Purpose:  calculate the percentage of partially Vaccinated people who contracted the disease that died 
   * Parameter: void
   * return: double
   * */
	public double getDeadPercentagePartiallyVaccinated() {
		return Math.round((countDiedStatus2 / (double)one_shot_count * 100) * 100 ) / 100;
		
	}

	/**
   * Method Name: getDeadPercentageFullyVaccinated 
   * Purpose:  calculate the percentage of fullVaccinated people who contracted the disease that died 
   * Parameter: void
   * return: double
   * */
	public double getDeadPercentageFullyVaccinated() {
		return Math.round((countDiedStatus3 / (double)two_shot_count * 100) * 100 ) / 100;
	
	}

	// of screen
	private Timer time;// Timer class object that will fire events every LAG_TIME
	// interval
	private int age;
	// REVISION : declare Ball objects here in class scope
	// public static int population;
	private Person[] person = new Person[0];
	boolean Ispause = false;
  //variables
	int countInfected = 1;
	int countUnvaccinatedInfected = 1;
	int countOneShotInfected = 0;
	int countFullyVaccinatedInfected = 0;
	int countRecovered = 0;
	int countDiedStatus1 = 0;
	int countDiedStatus2 = 0;
	int countDiedStatus3 = 0;
}
// end class