/**
 * Runs the game!
 * Ends the game when sentinel character is typed. When sentinel character is typed a SentinelException would
 * 	be thrown to this class. The class then changes a boolean variable that would break the while loop which
 * 	keeps the game running.
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class GameRunner {

	/**
	 * The main method of the program. 
	 * Initiates the GameRecord of the game, the Thrower, the Player, and the Judge.
	 * Would ask the user if the Player should be automated, and if the Thrower's AI should be deactivated. (do so through the Talker)
	 * Prints a welcome message that explains the rules.
	 * Carries on the game until the method catches a SentinelException.  
	 */
	public static void main(String[] args){
		myGameRecord = new GameRecord();
		myThrower = new Thrower(myGameRecord);
		myJudge = new Judge(myGameRecord);

		if (Talker.isPlayerAutomated()) {
			makeAutomatedPlayer();
		} else {
			myPlayer = new Player();
		}

		Talker.printWelcome();
		boolean gameContinue = true;
		while(gameContinue){
			try{
				myThrower.makeThrow(myJudge);
				myPlayer.makeThrow(myJudge);
				myJudge.decideAndRecord();
				Talker.printLastRound(myGameRecord);
			}
			catch (SentinelException e){
				gameContinue = false;
				Talker.printGameResult(myGameRecord);
			}
		}
	}


	/**
	 * Asks the user for the type of AutomatedPlayer that should be created (do so through the Talker)
	 * Creates an AutomatedPlayer for myPlayer according to the user's request.
	 */
	private static void makeAutomatedPlayer() {
		int aPType = Talker.askAutomatedPlayerType();
		int lifespan = Talker.askAutomatedPlayerLifespan();
		if (aPType == Talker.REPEATER_PLAYER) {
			myPlayer = new RepeaterPlayer(myGameRecord, lifespan);
		} else if (aPType == Talker.ROTATOR_PLAYER) {
			myPlayer = new RotatorPlayer(myGameRecord, lifespan);
		} else if (aPType == Talker.REFLECTOR_PLAYER) {
			myPlayer = new ReflectorPlayer(myGameRecord, lifespan);
		} else if (aPType == Talker.RANDOMIZER_PLAYER) {
			myPlayer = new RandomizerPlayer(myGameRecord, lifespan);
		} else if (aPType == Talker.MIXER_PLAYER) {
			myPlayer = new MixerPlayer(myGameRecord, lifespan);
		}
	}
	
	/**
	 * The constant for the sentinel character.
	 */
	public static final String SENTINEL = "z"; 
	/**
	 * The GameRecord of the game.
	 */
	private static GameRecord myGameRecord;
	/**
	 * The Thrower playing the game.
	 */
	private static Thrower myThrower;
	/**
	 * The Player playing the game.
	 */
	private static Player myPlayer;
	/**
	 * The Judge that decides win/losses.
	 */
	private static Judge myJudge;
}
