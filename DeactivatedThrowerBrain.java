import java.util.Random;

/**
 * The "brain" that chooses for the Thrower random throws.
 * 
 * Owned by Thrower.
 * 
 * Substitutes the normal ThrowerBrain when the user makes the request for this to be done.
 * 
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */

public class DeactivatedThrowerBrain extends ThrowerBrain{

	/**
	 * Constructor. Does not do anything.
	 * @param myGameRecord The GameRecord in the game, which this class does not use at all.
	 */
	public DeactivatedThrowerBrain(GameRecord myGameRecord) {
		super(myGameRecord);
	}
	
	/**
	 * This method, designed to override the normal analyze method, does not do anything.
	 */
	@Override
	public void analyze() {
		//empty
	}
	
	/**
	 * This method, designed to override the normal suggest method, simply suggests a random throw.
	 */
	@Override
	public char suggest() {
		return randomThrow();
	}

}
