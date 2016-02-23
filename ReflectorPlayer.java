/**
 * Throws the throw that Thrower has thrown in the previous round.
 * If it is the first round (this info would come from GameRecord), this Player calls RandomizerPlayer to randomly pick a throw.
 * Passes its throw choice to the Judge.
 * The win percentage it produces tends to be slightly higher than 40%.
 *  To test the win percentage, set its lifespan to 10,000 when you run the program. This is a large enough factor to show pattern,
 * and runtime isn't very long.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class ReflectorPlayer extends AutomatedPlayer {

	/**
	 * The constructor. Saves the GameRecord and the desired lifespan.
	 * @param myGameRecord the game record in the game
	 * @param lifespan the number of rounds this AutomatedPlayer should last for
	 */
	public ReflectorPlayer(GameRecord myGameRecord, int lifespan) {
		super(myGameRecord, lifespan);
	}

	/**
	 * Makes a reflector throw and passes it to the judge of the game.
	 */
	@Override
	public void makeThrow(Judge myJudge) throws SentinelException{
		char myThrow = reflectorThrow();
		myJudge.setThrow(myThrow, this);
		roundsLeft--;
		if (roundsLeft == -1) throw new SentinelException();
	}

}
