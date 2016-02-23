import java.util.ArrayList;

/**
 * ReflectorPatternAgent only exist in the ThrowerBrain.
 * This PatternAgent only guesses the reflector pattern.
 * Based on the Player's second to last throw in GameRecord, it guesses what the Player's most recent throw would be, assuming that the Player
 * 	throws according to a reflector's pattern. 
 * When asked, the PatternAgent can also suggest a throw (for the Thrower) based on its prediction of the Player's pattern.
 * Note, although the ReflectorPatternAgent is given a reference to GameRecord, it only uses GameRecord to obtain the thrower's last and 
 * 	second to last throws, and would not use it to cheat in the guessPlayersLastThrow method.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */

public class ReflectorPatternAgent extends PatternAgent {
	
	/**
	 * Constructor. Note: this is the only PatternAgent with a constructor, because it needs reference to the GameRecord
	 * to access what the Thrower threw in the previous rounds.
	 * @param myGameRecord the GameRecord in the game
	 */
	public ReflectorPatternAgent(GameRecord myGameRecord) {
		this.myGameRecord = myGameRecord;
	}
	
	/**
	 * Guesses what the player's most current throw would be, assuming that the Player throws according to a reflector's pattern. 
	 * @param playersSecondToLastThrow the player's second to last throw.
	 * @return conjecture as to what the player's most current throw would be.
	 */
	public char guessPlayersLastThrow (char playersSecondToLastThrow) {
		ArrayList<Round> allRounds = myGameRecord.getRounds();
		char throwersSecondToLastThrow = allRounds.get(allRounds.size()-2).getThrowerChoice();
		return throwersSecondToLastThrow;
	}
	
	/**
	 * Suggests a throw (for the Thrower) based on the assumption that the Player throws according to a reflector's pattern.
	 * @param playersLastThrow the player's last throw.
	 * @return suggested throw for the Thrower.
	 */
	public char suggest (char playersLastThrow) {
		ArrayList<Round> allRounds = myGameRecord.getRounds();
		char throwersLastThrow = allRounds.get(allRounds.size()-1).getThrowerChoice();
		return getPredator(throwersLastThrow);
	}

	/**
	 * The GameRecord in the game.
	 */
	private GameRecord myGameRecord;
	
}
