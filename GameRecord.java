import java.util.ArrayList;

/**
 * Keeps track of all of the previous rounds played with instances of Rounds.
 * Has methods that can return all the rounds, the last round, or the number of rounds played.  
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class GameRecord {

	/**
	 * The constructor. Sets up the ArrayList rounds.
	 */
	public GameRecord() {
		rounds = new ArrayList<Round>();
	}
	
	/**
	 * Records a round of playing
	 * @param playerChoice the user's throw
	 * @param throwerChoice the thrower's throw
	 * @param result who won, or if the game resulted in a draw
	 */
	public void addRound(char throwerChoice, char playerChoice, short result){
		Round addition = new Round(throwerChoice ,playerChoice, result);
		rounds.add(addition);
	}
	
	/**
	 * Returns an ArrayList of all the rounds that were played
	 * @return an ArrayList of all the rounds that were played
	 */
	public ArrayList<Round> getRounds() {
		return rounds;
	}
	
	/**
	 * Returns the last round that was played
	 * @return the last round that was played
	 */
	public Round getLastRound() {
		return rounds.get(rounds.size()-1);
	}
	
	/**
	 * Returns the number of rounds already played.
	 * @return the number of rounds already played.
	 */
	public int getNumRounds() {
		return rounds.size();
	}
	
	/**
	 * The list of all rounds played.
	 */
	private ArrayList<Round> rounds;
	
}
