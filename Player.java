/**
 * The virtualization of the user in the game.
 * When GameRunner asks Player to make a throw, it calls a method in the Talker class which
 * asks the user for a valid throw.
 * It then passes this throw to the judge.
 * Each game would have only one Player.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */

public class Player {
	
	/**
	 * Informs the judge of the throw that the user chooses.
	 * @param myJudge the judge in the game
	 */
	public void makeThrow(Judge myJudge) throws SentinelException{
		char myThrow = Talker.askThrow();
		myJudge.setThrow(myThrow, this);
	}

}
