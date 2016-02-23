/**
 * Throws a random throw among the 5 throw options.
 * Might be different for every round.
 * Passes its throw choice to the Judge.
 * The win percentage it produces tends to be slightly higher than 40%.
 * To test the win percentage, set its lifespan to 10,000 when you run the program. This is a large enough factor to show pattern,
 * and runtime isn't very long.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class RandomizerPlayer extends AutomatedPlayer {

	/**
	 * The constructor
	 * @param myGameRecord the game record in the game
	 * @param lifespan the number of rounds this AutomatedPlayer should last for
	 */
	public RandomizerPlayer(GameRecord myGameRecord, int lifespan) {
		super(myGameRecord, lifespan);
	}


	/**
	 * Makes a randomizer throw and passes it to the judge of the game.
	 */
	@Override
	public void makeThrow(Judge myJudge) throws SentinelException{
		char myThrow = randomizerThrow();
		myJudge.setThrow(myThrow, this);
		roundsLeft--;
		if (roundsLeft == -1) throw new SentinelException();
	}

}
