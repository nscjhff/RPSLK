/**
 * A record unit for a single round of playing.
 * Should only be created by GameRecord.
 * Records who threw what and the outcome.
 * Can be passed to Thrower and AutomatedPlayer for analysis,
 * and to Talker for printing information to the user.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class Round {

	/**
	 * Constructor. Saves the various parameter values.
	 * @param playerChoice the user's throw
	 * @param throwerChoice the thrower's throw
	 * @param result who won, or if the game resulted in a draw
	 */
	public Round(char throwerChoice, char playerChoice, short result) {
		super();
		this.throwerChoice = throwerChoice;
		this.playerChoice = playerChoice;
		this.result = result;
	}
	
	/**
	 * Returns the thrower's throw
	 * @return the thrower's throw
	 */
	public char getThrowerChoice() {
		return throwerChoice;
	}
	
	
	/**
	 * Returns the player's throw
	 * @return the player's throw
	 */
	public char getPlayerChoice() {
		return playerChoice;
	}

	/**
	 * returns who won or if there is a draw
	 * @return who won or if there is a draw
	 */
	public short getResult() {
		return result;
	}

	/**
	 * The throw choice made by the Player in this round. Uses the char constants for throw choices in the Talker class.
	 */
	private char playerChoice;
	/**
	 * The throw choice made by the Thrower in this round. Uses the char constants for throw choices in the Talker class.
	 */
	private char throwerChoice;
	/**
	 * The result: who won, or if there is a draw. Uses the short constants for results in the Talker class.
	 */
	private short result;
	
	
}
