import java.awt.Color;

/**
 * Program Name:Person.java 
 * Purpose: A class that holds the attributes and methods which help in building person immunity status , infection status, colors to illustrate  each status 
 *             that will be used in the simulation
  * Coder: Hala Hammad  StuNo 0932199  Sec:02 & Hadeel Abuhajer  StuNo:0930796  Sec:02 & Behnaz Najafi  stuNo 0919374   Sec:01
 * Date: Jul 29, 2021
 */

public class Person {
	private static final int MAX_INFECTION_CYCLE = 150;
	private boolean isAlive;
	private boolean isInfected;
	private int immunityStatus;
	private Color color;
	private int xCoordinate, yCoordinate, xIncrementValue, yIncrementValue;
	private int cycleCounter;
	

	public Person(int xCoordinate, int yCoordinate, boolean isAlive, boolean isInfected, int immunityStatus) {
		this.cycleCounter = 0;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.isAlive = isAlive;
		this.isInfected = isInfected;
		this.immunityStatus = immunityStatus;
		this.setRandomVelocity();
		calculateColor();
	}

	void setRandomVelocity() {
		this.xIncrementValue = (int) (Math.random() * 10) - 5;
		this.yIncrementValue = (int) (Math.random() * 10) - 5;
	}

	/**
   * Method Name: move 
   * Purpose:    calculate the new positions for each object for each drawing cycle and check the  of person death according to  the immunity status . 
   * Parameter:  void
   * return:     void
   * */
	public void move() {
		xCoordinate += xIncrementValue;
		yCoordinate += yIncrementValue;

		// bounds checking
		// have we hit the East/West boundary?
		if (xCoordinate <= 0) {
			// we flip the horizontal velocity
			xIncrementValue = Math.abs(xIncrementValue);
		} else if (xCoordinate > Simulator.WIDTH - Simulator.IMG_DIM) {
			// we flip the horizontal velocity
			xIncrementValue = -Math.abs(xIncrementValue);
		}

		// have we hit the North/South boundary?
		if (yCoordinate <= 0) {
			// we flip the vertical velocity
			yIncrementValue = Math.abs(yIncrementValue);
		} else if (yCoordinate > Simulator.HEIGHT - Simulator.IMG_DIM) {
			yIncrementValue = -Math.abs(yIncrementValue);
		}

		// if the person is infected, we update its cycle
		if (isInfected) {
			// if the cycle is the last one,
			if (MAX_INFECTION_CYCLE == ++cycleCounter) {
				// we need to make a decision
				// will the person live or die?
				double chanceOfDying = 0.0;
				switch (immunityStatus) {
					case 1:
						chanceOfDying = 0.10;
						break;
					case 2:
						chanceOfDying = 0.05;
						break;
					case 3:
						chanceOfDying = 0.01;
						break;
					case 4:
						chanceOfDying = 0.003;
						break;
				}
				if (Math.random() <= chanceOfDying) {
					// we're sorry
					this.setAlive(false);
				} else {
					// happy you made it!
					this.setImmunityStatus(4);
					this.setInfected(false);
				}
			}
		}

	}// end move

	
	/**
   * Method Name: calculateColor 
   * Purpose: Helping method that I prefer to use to set the colors according to the immunity status 
   * Parameter: void
   * return: void
   * */
	private void calculateColor() {
		if (this.isAlive) {
			if (this.isInfected) {
				this.color = Color.red;
			} else
				switch (this.immunityStatus) {
					case 4:
						this.color = Color.green;
						break;
					case 1:
						this.color = Color.blue;
						break;
					case 2:
						this.color = Color.cyan;
						break;
					case 3:
						this.color = Color.yellow;
						break;
				}
		} else {
			this.color = Color.black;
		}
	}

	// getters and setters
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
		calculateColor();
		if (!isAlive) {
			xIncrementValue = 0;
			yIncrementValue = 0;
		}
	}

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		if (this.isInfected != isInfected) {
			this.isInfected = isInfected;
			calculateColor();
			cycleCounter = 0;
		}
	}

	public int getImmunityStatus() {
		return immunityStatus;
	}

	public void setImmunityStatus(int immunityStatus) {
		this.immunityStatus = immunityStatus;
		calculateColor();
	}

	public Color getColor() {
		return color;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getxIncrementValue() {
		return xIncrementValue;
	}

	public void setxIncrementValue(int xIncrementValue) {
		this.xIncrementValue = xIncrementValue;
	}

	public int getyIncrementValue() {
		return yIncrementValue;
	}

	public void setyIncrementValue(int yIncrementValue) {
		this.yIncrementValue = yIncrementValue;
	}

	public int getCycleCounter() {
		return cycleCounter;
	}
	
	/**
   * Method Name: checkCollision 
   * Purpose:     check to see if a collision has occurred between two objects. 
   *              If it has, you will need some code to determine what state variables 
   *              should be changed for the objects involved. 
   * Parameter:    Person 
   * return:       void
   * */
	
	public boolean checkCollision(Person other) {
		if (!this.isAlive) {
			return false;
		}

		int deltaX = this.xCoordinate - other.xCoordinate;
		int deltaY = this.yCoordinate - other.yCoordinate;

		if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) < 6) {
			// we have a collision; we split randomly
			this.setRandomVelocity();

			// is the person we collided with infected?
			if (!this.isInfected && other.isInfected) {
				// apply the rules of contagion
				double chanceOfTransmission = 0.0;
				switch (this.immunityStatus) {
					case 1:
						chanceOfTransmission = 0.8;
						break;
					case 2:
						chanceOfTransmission = 0.4;
						break;
					case 3:
						chanceOfTransmission = 0.1;
						break;
					case 4:
						chanceOfTransmission = 0.1;
						break;
				}
				if (Math.random() < chanceOfTransmission) {
					// sorry, you got the virus!
					this.setInfected(true);
				}
			}
			return true;
		}
		
		return false;
	}

}
// end class