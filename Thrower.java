import java.util.Random;

/**
 * The automated opponent of the user Player.
 * 
 * When asked for a throw, it asks its brain, the ThrowerBrain, to analyze the Player's throw pattern, and
 * consults its brain for what to throw next. The throw is more likely to yield a favoriable win-loss percentage compared to
 * a randomly throwing Thrower.
 * 
 * If ThrowerBrain (AI) is deactivated, the Thrower would throw randomly. The user has the option to do so through interacting with the Talker
 * at the beginning of the program. In fact, the Thrower, upon being initiated, would commission the Talker to ask the user whether
 * its AI should be deactivated, and the Talker would pass back its response with a boolean value.  
 * Initiates a normal ThrowerBrain unless the user wants it to be deactivated, in which case it would
 * initiate a DeactivatedThrowerBrain that only spits out random throws.
 * 
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 *
 */
public class Thrower {

	/**
	 * The constructor.
	 * Commissions the Talker to ask the user whether
	 * Thrower's ThrowerBrain should be deactivated, and the Talker would pass back its response with a boolean value.
	 * Initiates a normal ThrowerBrain unless the user wants it to be deactivated, in which case it would
	 * initiate a DeactivatedThrowerBrain that only spits out random throws.
	 * @param myGameRecord the GameRecord in the game.
	 */
	public Thrower(GameRecord myGameRecord){
		this.myGameRecord = myGameRecord;
		if (Talker.isThrowerBrainDeactivated()) {
			myBrain = new DeactivatedThrowerBrain(myGameRecord);
		} else {
			myBrain = new ThrowerBrain(myGameRecord);
		}
	}

	/**
	 * Gives the ThrowerBrain the command to analyze the Player's last throw.
	 * Asks the ThrowerBrain to come up with a strategized throw. 
	 * Informs the Judge of the Thrower's throw.
	 * If it is only the first two rounds (consults the GameRecord), simply throws a random throw.
	 * @param myJudge the judge in the game.
	 */
	public void makeThrow(Judge myJudge){
		char myThrow;
		if (myGameRecord.getNumRounds() <= 2) {
			myThrow = myBrain.randomThrow();
		} else {
			myBrain.analyze();
			myThrow = myBrain.suggest();
		}
		myJudge.setThrow(myThrow, this);
	}

	/**
	 * The "brain" that helps the Thrower to come up with strategized throws to increase the chance of winning against the Player.
	 */
	private ThrowerBrain myBrain;
	/**
	 * The GameRecord in the game.
	 */
	private GameRecord myGameRecord;


}
