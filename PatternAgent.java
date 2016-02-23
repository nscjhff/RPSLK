/**
 * PatternAgents only exist in the ThrowerBrain.
 * Each PatternAgent is responsible for a possible pattern (reflector, rotator, or repeater).
 * Based on the Player's second to last throw in GameRecord, the PatternAgent guesses what the Player's most recent throw would be. 
 * When asked, the PatternAgent can also suggest a throw (for the Thrower) based on its prediction of the Player's pattern.
 * 
 * The PatternAgent also keeps track of a streak of correct guesses. When it guesses incorrectly the brain asks the agent to set its streak to 0.
 * 
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */

public abstract class PatternAgent {

	/**
	 * Guesses what the player's most current throw would be, based on the player's second to last throw.
	 * @param playersSecondToLastThrow the player's second to last throw.
	 * @return conjecture as to what the player's most current throw would be.
	 */
	public abstract char guessPlayersLastThrow (char playersSecondToLastThrow);
	
	/**
	 * Suggests a throw (for the Thrower) based on the PatternAgent's prediction of the Player's pattern. 
	 * @param playersLastThrow the player's last throw.
	 * @return suggested throw for the Thrower.
	 */
	public abstract char suggest (char playersLastThrow);
	
	/**
	 * Finds a predator for the given throw choice.
	 * @param subject the throw choice for whom the method is to find a predator.
	 * @return a predator for the subject.
	 */
	protected char getPredator (char subject) {
		if (subject == Talker.ROCK) {
			return Talker.ROCK_PREDATOR.charAt(0);
		} else if (subject == Talker.PAPER) {
			return Talker.PAPER_PREDATOR.charAt(0);
		} else if (subject == Talker.SCISSORS) {
			return Talker.SCISSORS_PREDATOR.charAt(0);
		} else if (subject == Talker.LIZARD) {
			return Talker.LIZARD_PREDATOR.charAt(0);
		} else {
			return Talker.SPOCK_PREDATOR.charAt(0);
		}
	}
	
	/**
	 * Add the PatternAgent's streak by 1.
	 */
	public void addStreak() {
		streak++;
	}
	
	/**
	 * Reset the streak to 0.
	 */
	public void resetStreak() {
		streak = 0;
	}
	
	/**
	 * Get the streak of the PatternAgent.
	 * @return streak of the PatternAgent
	 */
	public int getStreak() {
		return streak;
	}
	
	/**
	 * The streak of the agent. Only the ThrowerBrain is allowed to change it. 
	 */
	private int streak = 0;
}
