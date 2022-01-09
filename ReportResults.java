
/**
 * Program Name:ReportResults.java 
 * Purpose: a class used to show live information about the simulation such as the the percentages of infected, number of partially vaccinated who infected, 
 *             number of non vaccinated, number of fully vaccinated and the percentages of dead classified according to the immunity status. . 
  * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportResults extends JFrame {
	private Label infectedLabel, nonVaccinatedInfectedLabel, partiallyVaccinatedInfecteLabel,
			fullyVaccinatedInfectedLabel, recoveredLabel, unvaccinateddeadLabel, partiallyVaccinatedDeadLabel,
			fullyVaccinatedDeadLabel, deadLabel;
	Font font = new Font("Times New Roman", Font.BOLD, 50);

	public ReportResults(Simulator simulator) {
		super("Report Pandemic Simulation App");
		this.theSimulator = simulator;
		this.setForeground(Color.pink);
		this.setFont(font);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 780);
		this.setLayout(new GridLayout(9, 2, 3, 3));
		// let's create labels for the names and data
		infectedLabel = new Label();
		nonVaccinatedInfectedLabel = new Label();
		partiallyVaccinatedInfecteLabel = new Label();
		fullyVaccinatedInfectedLabel = new Label();
		recoveredLabel = new Label();
		unvaccinateddeadLabel = new Label();
		deadLabel = new Label();
		partiallyVaccinatedDeadLabel = new Label();
		fullyVaccinatedDeadLabel = new Label();
		this.add(new Label("Percentage of the total population that contracted the disease:"));
		this.add(infectedLabel);
		this.add(new Label("Percentage of unvaccinated persons who contracted the disease:"));
		this.add(nonVaccinatedInfectedLabel);
		this.add(new Label("Percentage of partially-vaccinated persons who contracted the disease:"));
		this.add(partiallyVaccinatedInfecteLabel);
		this.add(new Label("Percentage of fully-vaccinated persons who contracted the disease:"));
		this.add(fullyVaccinatedInfectedLabel);
		this.add(new Label("Percentage of all those who contracted the disease that recovered.:"));
		this.add(recoveredLabel);
		this.add(new Label("Percentage of infected people who died:"));
		this.add(deadLabel);
		this.add(new Label("Percentage of unvaccinated people who died:"));
		this.add(unvaccinateddeadLabel);
		this.add(new Label("Percentage of Partially vaccinated people who died:"));
		this.add(partiallyVaccinatedDeadLabel);
		this.add(new Label("Percentage of Fully vaccinated people who died:"));
		this.add(fullyVaccinatedDeadLabel);
		// let's create a timer to refresh all data
		Timer timer = new Timer(Simulator.LAG_TIME * 10, new RefreshListener());
		timer.start();
		this.setVisible(true);
	}

	private Simulator theSimulator;
	
	/**
	  * class Name: RefreshListener 
	  * Purpose:  an inner class implements ActionListener
	  * */

	private class RefreshListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			infectedLabel.setText(Double.toString(theSimulator.getOfInfectedPercentage()) + " %");
			nonVaccinatedInfectedLabel.setText(Double.toString(theSimulator.getNonVaccinatedInfectedPercentage()) + " %");
			partiallyVaccinatedInfecteLabel.setText(Double.toString(theSimulator.getOneShotInfectedPercentage()) + " %");
			fullyVaccinatedInfectedLabel.setText(Double.toString(theSimulator.getTwoShotInfectedPercentage()) + " %");
			recoveredLabel.setText(Double.toString(theSimulator.getRecoveredPercentage()) + " %");
			deadLabel.setText(Double.toString(theSimulator.getDeadPercentage()) + " %");
			unvaccinateddeadLabel.setText(Double.toString(theSimulator.getDeadPercentageUnvaccinated()) + " %");
			partiallyVaccinatedDeadLabel.setText(Double.toString(theSimulator.getDeadPercentagePartiallyVaccinated()) + " %");
			fullyVaccinatedDeadLabel.setText(Double.toString( theSimulator.getDeadPercentageFullyVaccinated()) + " %");
		}
	}
}
// end class