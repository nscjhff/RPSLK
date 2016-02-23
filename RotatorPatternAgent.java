/**
 * rotatorPatternAgent only exist in the ThrowerBrain.
 * This PatternAgent only guesses the rotator pattern.
 * Based on the Player's second to last throw in GameRecord, it guesses what the Player's most recent throw would be, assuming that the Player
 * 	throws according to a rotator's pattern. 
 * When asked, the PatternAgent can also suggest a throw (for the Thrower) based on its prediction of the Player's pattern. 
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */

public class RotatorPatternAgent extends PatternAgent {

	/**
	 * Guesses what the player's most current throw would be, assuming that the Player throws according to a rotator's pattern. 
	 * @param playersSecondToLastThrow the player's second to last throw.
	 * @return conjecture as to what the player's most current throw would be.
	 */
	public char guessPlayersLastThrow (char playersSecondToLastThrow) {
		char[] allThrowChoices = Talker.getThrowChoices();
		int index = getIndexInArray(playersSecondToLastThrow, allThrowChoices); //Player's second to last throw's index
		return allThrowChoices[index+1];
	}
	
	/**
	 * Suggests a throw (for the Thrower) based on the assumption that the Player throws according to a rotator's pattern.
	 * @param playersLastThrow the player's last throw.
	 * @return suggested throw for the Thrower.
	 */
	public char suggest (char playersLastThrow) {
		char[] allThrowChoices = Talker.getThrowChoices();
		int index = getIndexInArray(playersLastThrow, allThrowChoices); //Player's last throw's index
		char nextPlayerThrow = allThrowChoices[index+1];
		return getPredator (nextPlayerThrow);
		}
	
	/**
	 * Finds the index of a target in a given array. Returns -1 if target is not found in the array.
	 * @param target the target to search for.
	 * @param array the array to search in.
	 * @return the index of target in array. (-1 if target not found).
	 */
	private int getIndexInArray (char target, char[] array) {
		for (int i = 0; i < array.length-1; i++) {
			if (target == array[i]) {
				return i;
			}
		}
		return -1;
	}
	
}
