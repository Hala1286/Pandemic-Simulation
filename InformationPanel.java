/**
 * Program Name:InformationPanel.java 
 * Purpose: a class used to show live information about the simulation such as the the number of infected, number of partially vaccinated who infected, 
 *             number of non vaccinated, number of fully vaccinated. 
  * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPanel extends JPanel {

  private JLabel infectedLabel, nonVaccinatedInfectedLabel, partiallyVaccinatedInfecteLabel, fullyVaccinatedInfectedLabel,
      recoveredLabel, deadLabel;

  //constructor
   public InformationPanel(Simulator simulator) {
    this.theSimulator = simulator;
    this.setLayout(new GridLayout(6,2));
    this.setBackground(Color.WHITE);
    // let's create labels for the names and data
    infectedLabel = new JLabel("" , JLabel.CENTER);
    nonVaccinatedInfectedLabel = new JLabel("" , JLabel.CENTER);
    partiallyVaccinatedInfecteLabel = new JLabel("" , JLabel.CENTER);
    fullyVaccinatedInfectedLabel = new JLabel("" , JLabel.CENTER);
    recoveredLabel = new JLabel("" , JLabel.CENTER);
    deadLabel = new JLabel("" , JLabel.CENTER);


    this.add(new JLabel("Infected:"));
    this.add(infectedLabel);
    this.add(new JLabel("Non-vaccinated Infected:"));
    this.add(nonVaccinatedInfectedLabel);
    this.add(new JLabel("One-shot Infected:"));
    this.add(partiallyVaccinatedInfecteLabel);
    this.add(new JLabel("Two-shot Infected:"));
    this.add(fullyVaccinatedInfectedLabel);
    this.add(new JLabel("Recovered:"));
    this.add(recoveredLabel);
    this.add(new JLabel("Died:"));
    this.add(deadLabel);
    // let's create a timer to refresh all data
    Timer timer = new Timer(Simulator.LAG_TIME * 10, new RefreshListener());
    timer.start();
  }

  private Simulator theSimulator;
  
  
  /**
   * class Name: RefreshListener 
   * Purpose:  an inner class implements ActionListener
   * */

  private class RefreshListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      infectedLabel.setText(Integer.toString(theSimulator.getInfectedCount()));
      nonVaccinatedInfectedLabel.setText(Integer.toString(theSimulator.getNonVaccinatedInfectedCount()));
      partiallyVaccinatedInfecteLabel.setText(Integer.toString(theSimulator.getOneShotInfectedCount()));
      fullyVaccinatedInfectedLabel.setText(Integer.toString(theSimulator.getTwoShotInfectedCount()));
      recoveredLabel.setText(Integer.toString(theSimulator.getRecoveredCount()));
      deadLabel.setText(Integer.toString(theSimulator.getDeadCount()));
    }
  }

};
