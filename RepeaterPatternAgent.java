/**
 * RepeaterPatternAgent only exist in the ThrowerBrain.
 * This PatternAgent only guesses the repeater pattern.
 * Based on the Player's second to last throw in GameRecord, it guesses what the Player's most recent throw would be, assuming that the Player
 * 	throws according to a Repeater's pattern. 
 * When asked, the PatternAgent can also suggest a throw (for the Thrower) based on its prediction of the Player's pattern. 
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */

public class RepeaterPatternAgent extends PatternAgent {

	/**
	 * Guesses what the player's most current throw would be, assuming that the Player throws according to a Repeater's pattern. 
	 * @param playersSecondToLastThrow the player's second to last throw.
	 * @return conjecture as to what the player's most current throw would be.
	 */
	public char guessPlayersLastThrow (char playersSecondToLastThrow) {
		return playersSecondToLastThrow;
	}
	
	/**
	 * Suggests a throw (for the Thrower) based on the assumption that the Player throws according to a Repeater's pattern.
	 * @param playersLastThrow the player's last throw.
	 * @return suggested throw for the Thrower.
	 */
	public char suggest (char playersLastThrow) {
		return getPredator(playersLastThrow);
	}
	
}
