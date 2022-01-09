/**
 * Program Name:GraphicPanel.java 
 * Purpose: a class used to show live graphic simulation illustrating the changes on the number of infected people breaking down on the immunity status
 * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicPanel extends JPanel {
  GraphicPanel(Simulator simulator) {
    this.theSimulator = simulator;
    Timer timer = new Timer(Simulator.LAG_TIME * 3, new RefreshListener());
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(this.font);
    if (this.scaleTop > 0) {
      int colWidth = this.getWidth() / 20;
      double scale = (this.getHeight() - 20) / (double)this.scaleTop;
      int bottom = this.getHeight();
      int y2;
      for (int x = 0; x < 19; ++x) {
        int x1 = x * colWidth;
        int x2 = x1 + colWidth;
        boolean showLabel1 = x % 4 == 0;
        boolean showLabel2 = x % 4 == 2;
        g.setColor(Color.blue);
        y2 = bottom - (int) (dataPoints[x + 1][0] * scale);
        g.drawLine(x1, bottom - (int) (dataPoints[x][0] * scale), x2, y2);
        if (showLabel1) {
          g.drawString(Integer.toString(dataPoints[x][0]), x2, y2);
        }
        g.setColor(Color.cyan);
        y2 = bottom - (int) (dataPoints[x + 1][1] * scale);
        g.drawLine(x1, bottom - (int) (dataPoints[x][1] * scale), x2, y2);
        if (showLabel2) {
          g.drawString(Integer.toString(dataPoints[x][1]), x2, y2);
        }
        g.setColor(Color.yellow);
        y2 = bottom - (int) (dataPoints[x + 1][2] * scale);
        g.drawLine(x1, bottom - (int) (dataPoints[x][2] * scale), x2, y2);
        if (showLabel1) {
          g.drawString(Integer.toString(dataPoints[x][2]), x2, y2);
        }
        g.setColor(Color.green);
        y2 = bottom - (int) (dataPoints[x + 1][3] * scale);
        g.drawLine(x1, bottom - (int) (dataPoints[x][3] * scale), x2, y2);
        if (showLabel2) {
          g.drawString(Integer.toString(dataPoints[x][3]), x2, y2);
        }
        g.setColor(Color.red);
        y2 = bottom - (int) (dataPoints[x + 1][4] * scale);
        g.drawLine(x1, bottom - (int) (dataPoints[x][4] * scale), x2, y2);
        if (showLabel1) {
          g.drawString(Integer.toString(dataPoints[x][4]), x2, y2);
        }
        g.setColor(Color.black);
        y2 = bottom - (int) (dataPoints[x + 1][5] * scale);
        g.drawLine(x1, bottom - (int) (dataPoints[x][5] * scale), x2, y2);
        if (showLabel2) {
          g.drawString(Integer.toString(dataPoints[x][5]), x2, y2);
        }
      }
    }
  }

  
  /**
   * class Name: RefreshListener 
   * Purpose:  an inner class implements ActionListener
   * */
  private class RefreshListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // shift to the left
      for (int x = 1; x < 20; ++x) {
        for (int y = 0; y < 6; ++y) {
          dataPoints[x - 1][y] = dataPoints[x][y];
        }
      }
      // get the new values on the leftmost column
      dataPoints[19][0] = theSimulator.getNonVaccinatedCount();
      dataPoints[19][1] = theSimulator.getOneShotCount();
      dataPoints[19][2] = theSimulator.getTwoShotCount();
      dataPoints[19][3] = theSimulator.getRecoveredCount();
      dataPoints[19][4] = theSimulator.getInfectedCount();
      dataPoints[19][5] = theSimulator.getDeadCount();

      // get the max
      scaleTop = 0;
      for (int x = 0; x < 20; ++x) {
        for (int y = 0; y < 6; ++y) {
          if (scaleTop < dataPoints[x][y]) {
            scaleTop = dataPoints[x][y];
          }
        }
      }
      repaint();
    }
  }
//attributes
  private Simulator theSimulator;
  private int scaleTop;
  private int dataPoints[][] = new int[20][6];
  private Font font = new Font("Arial", Font.PLAIN, 10);
}
