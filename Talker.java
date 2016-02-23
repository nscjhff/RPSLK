import java.util.ArrayList;
import java.util.Scanner;

/**
 * Talker is a static class that gives instructions to the user and interprets the user's input.
 * It handles all of the console input and output in the game. 
 * The runner frequently calls methods in Talker to give the user information about the game, or to ask the user to make choices.
 * Player (when controlled by console) asks for the user's throw choices through the Talker.
 * 
 * Talker encapsulates all the throw options. When classes throughout the game mention the throw options, they do so through
 * 	char constants in Talker that represent the throw options. 
 * 
 * Talker encapsulates references to player win, thrower win, and draw. When classes throughout the game mention the throw options, 
 * 	they do so through constants in Talker. For example, when the Judge passes the result of a round to GameRecord to be recorded, 
 * 	the Judge passes a short constant from Talker. 
 * 	The three possible outcomes of a round are represented by short constants. Short is used to represent them so that these outcomes
 *  can be differentiated from various int values throughout the game. The specific values are chosen because they are unlikely to overlap
 *  with another value in the game. 
 * 
 * Talker encapsulates all the 5 types of AutomatedPlayers through int constants. These constants are only used when Talker, after 
 * 	obtaining the desired type of AutomatedPlayer from the user console, passes the selection to the Runner, which can then create the
 * 	specific type of AutomatedPlayer. The specific values are chosen because they are unlikely to overlap with another value in the game.
 * 
 * Talker encapsulates the rules by which the Judge can decide the winner between two throws. It does so by keeping String constants that each 
 * represents either the list of preys or the list of predators for a particular throw.
 * 
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 *
 */
public class Talker {

	/**
	 * Prints the welcome message (including the rules of the game) in the concole.
	 */
	public static void printWelcome(){
		System.out.println("Hello. Play Rock Paper Scissors Lizard Spock with Gegner, an automated player.");
		System.out.println("\"Scissors cuts Paper covers Rock crushes");
		System.out.println("Lizard poisons Spock smashes Scissors");
		System.out.println("decapitates Lizard eats Paper disproves");
		System.out.println("Spock vaporizes Rock crushes Scissors.\"");
		System.out.println("\b\b\b\b -- Sam Kass.");
		System.out.println("Enter r for rock,");
		System.out.println("p for paper,");
		System.out.println("s for scissors,");
		System.out.println("l for lizard,");
		System.out.println("k for spock.");
	}

	/**
	 * Obtains from the user (through the console) a valid throw.
	 * @return the throw obtained from the user
	 */
	public static char askThrow() throws SentinelException{
		Scanner myScanner = new Scanner(System.in);
		String input = "";
		while(input.length()!=1){
			try{
				System.out.println("Make a throw.");
				input = myScanner.nextLine();
				if (input.equalsIgnoreCase(GameRunner.SENTINEL)) throw new SentinelException(); //Good
				if (input.length()!=1) throw new InvalidChoiceException(); //Bad
				if (!isThrowValid(input.charAt(0))) throw new InvalidChoiceException(); //Bad
			}
			catch (InvalidChoiceException e){
				System.out.println("Throw is invalid. You must enter either r, p, s, l, or k.");
				input = "";
			}
		}
		char myThrow = input.charAt(0);
		return myThrow;
	}

	/**
	 * Gets the last round from myGameRecord. Interprets the Round. 
	 * Prints on the console the thrower's throw,
	 * and whether the Player won, drew, or lost.
	 * @param myGameRecord the GameRecord from which Talker obtains information.
	 */
	public static void printLastRound(GameRecord myGameRecord){
		Round lastRound = myGameRecord.getLastRound();
		System.out.println();
		System.out.println("Your opponent Gegner threw " + throwCharToString(lastRound.getThrowerChoice()) + ".");
		System.out.println("You threw " + throwCharToString(lastRound.getPlayerChoice()) + ".");		
		if(lastRound.getResult() == PLAYER_WIN){
			System.out.println("You won.");
		}else if(lastRound.getResult() == THROWER_WIN){
			System.out.println("You lost.");
		}else{
			System.out.println("Draw.");
		}
		System.out.println("(Gegner threw this before you did. Gegner did not cheat.)");
	}

	/**
	 * Gets all the rounds played from GameRecord. Interprets the rounds.
	 * Prints on the console the number and percentage of Player wins,
	 * Player losses, and
	 * draws.
	 * Prints the pure percentage of wins and pure percentage of losses, and comments on them.
	 * @param myGameRecord the GameRecord from which Talker obtains information.
	 */
	public static void printGameResult(GameRecord myGameRecord){
		ArrayList<Round> rounds = myGameRecord.getRounds();
		int numRounds = rounds.size();
		int numWins = countResult(rounds, PLAYER_WIN);
		int numDraws = countResult(rounds, DRAW);
		int numLosses = countResult(rounds, THROWER_WIN);
		System.out.println();
		System.out.println("-------");
		System.out.println("Out of the " + numRounds + " rounds,");
		double percWins = (((double)numWins)/numRounds)*100;
		System.out.println("you won " + numWins + " rounds (" + percWins + "%),");
		double percLosses = (((double)numLosses)/numRounds)*100;
		System.out.println("lost " + numLosses + " rounds (" + percLosses + "%),");
		double percDraws = (((double)numDraws)/numRounds)*100;
		System.out.println("and experienced " + numDraws + " draws (" + percDraws + "%).");
		System.out.println();
		double pureWinPerc = (((double)numWins)/(numRounds-numDraws))*100;
		System.out.println("You won " + pureWinPerc + "% of all the non-draw rounds, while ");
		double pureLossPerc = 100-pureWinPerc;
		System.out.println("Gegner won " + pureLossPerc + "% of all the non-draw rounds.");
		if (pureWinPerc < 40) {
			System.out.println("\nIsn't it worth noting that you won substantially less than 50% of the time?");
			System.out.println("Trying to develop strategies for this game...doesn't really help.");
		}
	}

	/**
	 * returns a list of throw choices.
	 * @return a list of throw choices.
	 */
	public static char[] getThrowChoices(){
		return throwChoices;
	}
	
	/**
	 * Asks the user if the Player should be automated
	 * @return true if the Player should be automated
	 */
	public static boolean isPlayerAutomated() {
		System.out.println("(To automate the Player, type a.)");
		Scanner myScanner = new Scanner(System.in);
		String input = myScanner.nextLine();
		if (input.equalsIgnoreCase("a")){
			return true;
		}
		return false;
	}
	
	/**
	 * Asks the user if the ThrowerBrain should be deactivated 
	 * @return true if the ThrowerBrain should be deactivated
	 */
	public static boolean isThrowerBrainDeactivated() {
		System.out.println("(To disable the opponent's AI, type d. If you do so the opponent would only make random throws.)");
		Scanner myScanner = new Scanner(System.in);
		String input = myScanner.nextLine();
		if (input.equalsIgnoreCase("d")){
			return true;
		}
		return false;
	}
	
	public static int askAutomatedPlayerType() {
		System.out.println("(Which type of automization do you want? rr = repeater, ro = rotator, rl = reflector, rz = randomizer, mr = mixer.)");
		Scanner myScanner = new Scanner(System.in);
		while(true) {
			String input = myScanner.nextLine();
			if (input.equalsIgnoreCase("rr")) {
				return REPEATER_PLAYER;
			} else if (input.equalsIgnoreCase("ro")) {
				return ROTATOR_PLAYER;
			} else if (input.equalsIgnoreCase("rl")) {
				return REFLECTOR_PLAYER;
			} else if (input.equalsIgnoreCase("rz")) {
				return RANDOMIZER_PLAYER;
			} else if (input.equalsIgnoreCase("mr")) {
				return MIXER_PLAYER;
			} else {
				System.out.println("Enter a valid choice. rr = repeater, ro = rotator, rl = reflector, rz = randomizer, mr = mixer.)");
			}
		}
	}
	
	/**
	 * Obtains a positive integer from the user, signifying the desired lifespan for the AutomatedPlayer.
	 * @return lifespan obtained from the user
	 */
	public static int askAutomatedPlayerLifespan() {
		System.out.println("(How many rounds should the Automated Player work for?)");
		Scanner myScanner = new Scanner(System.in);
		int lifespan = -1;
		while (lifespan == -1) {
			try {
				String input = myScanner.nextLine();
				lifespan = Integer.parseInt(input);
				if (lifespan < 1) throw new InvalidChoiceException(); //bad
			} catch (Exception e) {
				System.out.println("Enter a valid, positive integer.");
				lifespan = -1;
			}
		}
		return lifespan;
	}
	
	/**
	 * Only used by MixerPlayer class, when it needs to ask the user to specify its phaseLength (how many rounds should pass
	 * before it chooses the next throwing method).
	 * @return phase length obtained from the user.
	 */
	public static int askPhaseLength() {
		System.out.println("(How many rounds should pass before the Mixer Player randomly chooses the next throwing method?)");
		Scanner myScanner = new Scanner(System.in);
		int phaseLength = -1;
		while (phaseLength == -1) {
			try {
				String input = myScanner.nextLine();
				phaseLength = Integer.parseInt(input);
				if (phaseLength < 1) throw new InvalidChoiceException(); //bad
			} catch (Exception e) {
				System.out.println("Enter a valid, positive integer.");
				phaseLength = -1;
			}
		}
		return phaseLength;
	}

	/**
	 * Returns if myThrow is a valid throw.
	 * @param myThrow the throw to be interpreted
	 * @return if myThrow is a valid throw.
	 */
	private static boolean isThrowValid(char myThrow){
		String allChoices = ""+ROCK+PAPER+SCISSORS+LIZARD+SPOCK;
		int throwIndex = allChoices.indexOf(myThrow);
		if (throwIndex >-1){
			return true;
		}
		return false;
	}
	
	/**
	 * Translates a throw-character to a String. 
	 * e.g. translate "r" to "Rock".
	 * @param myThrow a throw-character
	 * @return String corresponding to myThrow
	 */
	private static String throwCharToString(char myThrow){
		String allChoices = ""+ROCK+PAPER+SCISSORS+LIZARD+SPOCK;
		int throwIndex = allChoices.indexOf(myThrow);
		switch (throwIndex){
			case 0: return "Rock";
			case 1: return "Paper";
			case 2: return "Scissors";
			case 3: return "Lizard";
			case 4: return "Spock";
		}
		return "";
	}
	
	/**
	 * Count the frequency of a certain result among an ArrayList of rounds.
	 * @param rounds the ArrayList of rounds.
	 * @param result the result to be investigated
	 * @return frequency of the result
	 */
	private static int countResult(ArrayList<Round> rounds, short result){
		int count = 0;
		for (Round r : rounds){
			if (r.getResult() == result){
				count++;
			}
		}
		return count;
	}

	
	/**
	 * char constant that represents Rock.
	 */
	public static final char ROCK = "r".charAt(0);
	/**
	 * char constant that represents Paper.
	 */
	public static final char PAPER = "p".charAt(0);
	/**
	 * char constant that represents Scissors.
	 */
	public static final char SCISSORS = "s".charAt(0);
	/**
	 * char constant that represents Lizard.
	 */
	public static final char LIZARD = "l".charAt(0);
	/**
	 * char constant that represents Spock.
	 */
	public static final char SPOCK = "k".charAt(0);
	
	
	/**
	 * short constant that represents winning for the Player.
	 */
	public static final short PLAYER_WIN = 5839;
	/**
	 * short constant that represents winning for the Thrower.
	 */
	public static final short DRAW = 6201;
	/**
	 * short constant that represents draw.
	 */
	public static final short THROWER_WIN = 2354;


	/**
	 * int constant that represents a RepeaterPlayer
	 */
	public static final int REPEATER_PLAYER = -1933;
	/**
	 * int constant that represents a ReflectorPlayer
	 */
	public static final int ROTATOR_PLAYER = -2733;
	/**
	 * int constant that represents a RandomizerPlayer
	 */
	public static final int REFLECTOR_PLAYER = -3933;
	/**
	 * int constant that represents a RandomizerPlayer
	 */
	public static final int RANDOMIZER_PLAYER = -9233;
	/**
	 * int constant that represents a MixerPlayer
	 */
	public static final int MIXER_PLAYER = -13633;
	
	/**
	 * String constant that represents the list of preys for the rock.
	 */
	public static final String ROCK_PREY = "" + Talker.LIZARD + Talker.SCISSORS;
	/**
	 * String constant that represents the list of predators for the rock.
	 */
	public static final String ROCK_PREDATOR = "" + Talker.SPOCK + Talker.PAPER;
	/**
	 * String constant that represents the list of preys for the paper.
	 */
	public static final String PAPER_PREY = "" + Talker.SPOCK + Talker.ROCK;
	/**
	 * String constant that represents the list of predators for the paper.
	 */
	public static final String PAPER_PREDATOR = "" + Talker.LIZARD + Talker.SCISSORS;
	/**
	 * String constant that represents the list of preys for the scissors.
	 */
	public static final String SCISSORS_PREY = "" + Talker.LIZARD + Talker.PAPER;
	/**
	 * String constant that represents the list of predators for the scissors.
	 */
	public static final String SCISSORS_PREDATOR = "" + Talker.SPOCK + Talker.ROCK;
	/**
	 * String constant that represents the list of preys for the lizard.
	 */
	public static final String LIZARD_PREY = "" + Talker.SPOCK + Talker.PAPER;
	/**
	 * String constant that represents the list of predators for the lizard.
	 */
	public static final String LIZARD_PREDATOR = "" + Talker.ROCK + Talker.SCISSORS;
	/**
	 * String constant that represents the list of preys for Spock.
	 */
	public static final String SPOCK_PREY = "" + Talker.ROCK + Talker.SCISSORS;
	/**
	 * String constant that represents the list of predators for Spock.
	 */
	public static final String SPOCK_PREDATOR = "" + Talker.LIZARD + Talker.PAPER;
	
	/**
	 * An array that encapsulates all the throw options. The options are represented by constants in the Talker class.
	 */
	private static char[] throwChoices = {ROCK, PAPER, SCISSORS, LIZARD, SPOCK};
}
