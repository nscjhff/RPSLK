import java.util.Random;

/**
 * Player that chooses throw options according to one of the five patterns: repeater, reflector, rotator, randomizer, and mixer.
 * includes all the throwing methods that any type of an automated player would need.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public abstract class AutomatedPlayer extends Player {

	/**
	 * Constructor. Saves the indicated lifespan. 
	 * @param myGameRecord the game record in the game
	 * @param lifespan the rounds that should pass before the AutomatedPlayer ends the game
	 */
	public AutomatedPlayer(GameRecord myGameRecord, int lifespan) {
		super();
		this.myGameRecord = myGameRecord;
		this.roundsLeft = lifespan;
		myRandom = new Random();
		repeaterThrowChoice = chooseRandomThrow();
		rotatorThrowIndex = 0;
	}

	/**
	 * The sub-classes must have a method that makes a throw to the Judge.
	 * @param myJudge the judge in the game.
	 * @throws SentinelException if there is no more rounds for the AutomatedPlayer to work in
	 */
	public abstract void makeThrow(Judge myJudge) throws SentinelException;

	/**
	 * Randomly chooses a throw and returns the same throw every time the method is called.
	 * @return the same throw as the previous time.
	 */
	protected char repeaterThrow() {
		return repeaterThrowChoice;
	}

	/**
	 * Returns each of the 5 throw choices cyclically.
	 * @return a throw, following the previous throw in order.
	 */
	protected char rotatorThrow() {
		char[] options = Talker.getThrowChoices();
		if (rotatorThrowIndex == 5) {
			rotatorThrowIndex = 0;
		}
		char myThrow = options[rotatorThrowIndex];
		rotatorThrowIndex++;
		return myThrow;
	}

	/**
	 * Returns the throw that Thrower has thrown in the previous round.
	 * @return the throw that Thrower has thrown in the previous round.
	 */
	protected char reflectorThrow() {
		if (myGameRecord.getNumRounds() == 0) {
			return randomizerThrow();
		}
		Round lastRound = myGameRecord.getLastRound();
		char myThrow = lastRound.getThrowerChoice();
		return myThrow;
	}

	/**
	 * Returns a random throw among the 5 throw options.
	 * @return a random throw among the 5 throw options.
	 */
	protected char randomizerThrow() {
		int myThrowIndex = myRandom.nextInt(5);
		char[] options = Talker.getThrowChoices();
		char myThrow = options[myThrowIndex];
		return myThrow;
	}

	/**
	 * Randomly chooses a throw out of the 5 options.
	 * @return a random throw
	 */
	private char chooseRandomThrow() {
		int index = myRandom.nextInt(5);
		char[] options = Talker.getThrowChoices();
		return options[index];
	}

	/**
	 * The GameRecord of the game.
	 */
	protected GameRecord myGameRecord;
	/**
	 * A Random used to choose anything random, from a random throw to a random method (mixerThrow())
	 */
	protected Random myRandom;
	/**
	 * The rounds left for which the AutomatedPlayer must produce throw choices.
	 */
	protected int roundsLeft;
	/**
	 * Used only by the repeaterThrow() method. Keeps track of the throw choice that will be repeated.
	 */
	protected char repeaterThrowChoice;
	/**
	 * Used only by the rotatorThrow() method. Keeps track of the throw option produced for the last round,
	 * so that the method can correctly produce the throw option immediately following it for the current round.
	 */
	protected int rotatorThrowIndex;

}
