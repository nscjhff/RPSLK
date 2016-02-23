/**
 * Decide the win and loss between 2 throws by looking for the throw (represented by char constants in Talker)
 * in a string that represents either the list of preys or the list of predators for a particular throw. (The string is a constant in Talker class).
 * The Thrower passes its choice of throw to the Judge first. Then, the Player passes its choice of Throw.
 * Then, GameRunner would ask the Judge to decide the round. 
 * After deciding a round, the Judge passes the two throw options and the result to GameRecord, which records it in an instance of Round.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class Judge {

	/**
	 * Constructor. Gives Judge a reference of the GameRecord.
	 * @param myGameRecord the GameRecord in the game.
	 */
	public Judge (GameRecord myGameRecord){
		this.myGameRecord = myGameRecord;
		subs = new char[2];
	}

	/**
	 * To be called by the Thrower (and Thrower only) when it wants to submit a throw.
	 * @param myThrow the throw choice
	 * @param verify to verify that the throw choice is submitted by a thrower.
	 */
	public void setThrow (char myThrow, Thrower verify){
		subs[0] = myThrow;
	}

	/**
	 * To be called by the Player (and Player only) when it wants to submit a throw.
	 * @param myThrow the throw choice
	 * @param verify to verify that the throw choice is submitted by a Player.
	 */
	public void setThrow (char myThrow, Player verify){
		subs[1] = myThrow;
	}
	
	/**
	 * Decides who won (or if there is a draw) and records into GameRecord.
	 * Precondition: both thrower and player has submitted their throws into subs[].
	 */
	public void decideAndRecord(){
		short result = decide();
		recordRound(result);
	}

	/**
	 * Decides whether the player won, the thrower won, or if there was a draw. It does so
	 * by looking for the Player's throw choice (represented by char constants in Talker)
	 * in a string that represents either the list of preys or the list of predators for the Thrower's throw.
	 */
	private short decide(){
		String allChoices = ""+Talker.ROCK + Talker.PAPER + Talker.SCISSORS + Talker.LIZARD + Talker.SPOCK;
		int throwerIndex = allChoices.indexOf(subs[0]);
		switch (throwerIndex){
		case 0: {
			if (Talker.ROCK_PREY.indexOf(subs[1]) > -1){
				return Talker.THROWER_WIN;
			}else if (Talker.ROCK_PREDATOR.indexOf(subs[1]) > -1){
				return Talker.PLAYER_WIN;
			}else{
				return Talker.DRAW;
			}
		}
		case 1: {
			if (Talker.PAPER_PREY.indexOf(subs[1]) > -1){
				return Talker.THROWER_WIN;
			}else if (Talker.PAPER_PREDATOR.indexOf(subs[1]) > -1){
				return Talker.PLAYER_WIN;
			}else{
				return Talker.DRAW;
			}
		}
		case 2: {
			if (Talker.SCISSORS_PREY.indexOf(subs[1]) > -1){
				return Talker.THROWER_WIN;
			}else if (Talker.SCISSORS_PREDATOR.indexOf(subs[1]) > -1){
				return Talker.PLAYER_WIN;
			}else{
				return Talker.DRAW;
			}
		}
		case 3: {
			if (Talker.LIZARD_PREY.indexOf(subs[1]) > -1){
				return Talker.THROWER_WIN;
			}else if (Talker.LIZARD_PREDATOR.indexOf(subs[1]) > -1){
				return Talker.PLAYER_WIN;
			}else{
				return Talker.DRAW;
			}
		}
		case 4: {
			if (Talker.SPOCK_PREY.indexOf(subs[1]) > -1){
				return Talker.THROWER_WIN;
			}else if (Talker.SPOCK_PREDATOR.indexOf(subs[1]) > -1){
				return Talker.PLAYER_WIN;
			}else{
				return Talker.DRAW;
			}
		}
		}
		return -1;
	}

	/**
	 * Records the two throws and the result into the GameRecord.
	 */
	private void recordRound(short result){
		myGameRecord.addRound(subs[0], subs[1], result);
	}

	/**
	 * The GameRecord in the game.
	 */
	private GameRecord myGameRecord;
	
	/**
	 * An array that holds the two throw choice submissions from the Thrower and Player (in that order).
	 */
	private char[] subs; 
}
