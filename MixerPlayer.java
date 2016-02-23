/**
 * Throws the result from a random throw method among the 4 options.
 * Uses the same throwing method for the length of phaseLength, which the user specifies upon creating the class.
 * These methods are embedded in the higher abstract class AutomatedPlayer.
 * Passes its throw choice to the Judge.
 * Tends to produce a win percentage slightly less than 40%.
 *  To test the win percentage, set its lifespan to 10,000 when you run the program. This is a large enough factor to show pattern,
 * and runtime isn't very long.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class MixerPlayer extends AutomatedPlayer {

	/**
	 * The constructor. Asks the user 
	 * @param myGameRecord the game record in the game
	 * @param lifespan the number of rounds this AutomatedPlayer should last for
	 */
	public MixerPlayer(GameRecord myGameRecord, int lifespan) {
		super(myGameRecord, lifespan);
		this.phaseLength = Talker.askPhaseLength();
		roundsLeftBeforeChange = 0;
	}

	/**
	 * Makes a mixer throw and passes it to the judge of the game.
	 */
	@Override
	public void makeThrow(Judge myJudge) throws SentinelException{
		char myThrow = mixerThrow();
		myJudge.setThrow(myThrow, this);
		roundsLeft--;
		if (roundsLeft == -1) throw new SentinelException();
	}
	

	/**
	 * If roundsLeftBeforeChange = 0, picks a random throwing method among the 4 throwing methods and set roundsLeftBeforeChange to phaseLength.
	 * When roundsLeftBeforeChange > 0, returns result from the throwing method used for last time.
	 * @return result from a random throw method among the 4 options.
	 */
	private char mixerThrow() {
		if (roundsLeftBeforeChange < 1) {
			changeMethod();
		}
		roundsLeftBeforeChange--;
		if (throwerIndex == 0) {
			return repeaterThrow();
		} else if (throwerIndex == 1) {
			return rotatorThrow();
		} else if (throwerIndex == 2) {
			return reflectorThrow();
		} else {
			return randomizerThrow();
		}
	}
	
	/**
	 * Randomly chooses another throwing method (and changes throwerIndex).
	 * Sets roundsLeftBeforeChange to phaseLength.
	 */
	private void changeMethod() {
		roundsLeftBeforeChange = phaseLength;
		throwerIndex = myRandom.nextInt(4);
	}

	/**
	 * Keeps track of the phase length of the mixer, i.e. how many rounds
	 * should the mixer use a throwing method for.
	 */
	private int phaseLength;
	/**
	 * Keeps track of how many remaining rounds
	 * should pass before the mixer randomly picks another throwing method.
	 */
	private int roundsLeftBeforeChange;
	/**
	 * Keeps track of which throwing method is being used right now.
	 */
	private int throwerIndex;
	
}
