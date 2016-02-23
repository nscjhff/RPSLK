import java.util.ArrayList;
import java.util.Random;

/**
 * The "brain" that helps the Thrower to come up with strategized throws to increase the chance of winning against the Player.
 * 
 * Owned by Thrower.
 * 
 * Analyzes past rounds by extracting data from GameRecord and observing patterns by which the user makes decisions.
 * There are three possible patterns ThrowerBrain can observe: Reflector, Repeater, and Rotator.
 * This is how the ThrowerBrain observes patterns: after the player made a throw, each of the PatternAgents makes a prediction about what
 * 	that throw would be. If the prediction matches the actual throw, the patternDetector's streak +1. If prediction is false, streak = 0.
 * 	(The agents are kept in an array).
 * 
 * ThrowerBrain then makes a throw that strategizes against the pattern with the highest streak.
 * 
 * If two patterns have the same streak, reflector is favored over repeater over rotator. 
 * If all patterns have streak of 0, submits a random throw.
 * 
 * Effectiveness of ThrowerBrain, compared to DeactivatedThrowerBrain, which simply produces random throws:
 * 
 * DeactivatedThrowerBrain's rate of wining against AutomatedPlayers of various kinds.
 * Percentage taken out of all rounds that are not draw.
 * In each of the experiments the game ran for 10,000 rounds.

<ul>
   <li>Against repeater : 59.56% </li>
   <li>Against rotator : 59.24% </li>
   <li>Against reflector : 50.74% </li>
   <li>Against randomizer: 49.68% </li>
   <li>Against mixer that changes method every 25 rounds: 59.52% </li>
   <li>Against mixer that changes method every 3 rounds: 49.73% </li>
   <li>Against mixer that changes method every 2 rounds: 50.76% </li>
 </ul>
 * 
 * ThrowerBrain's rate of wining against AutomatedPlayers of various kinds.
 * Percentage taken out of all rounds that are not draw.
 * In each of the experiments the game ran for 10,000 rounds.
 * 
 <ul>
   <li>Against repeater : 99.98% </li>
   <li>Against rotator : 100% </li>
   <li>Against reflector : 99.98% </li>
   <li>Against randomizer: 50.32% </li>
   <li>Against mixer that changes method every 25 rounds: 87.54% </li>
   <li>Against mixer that changes method every 3 rounds: 74.54% </li>
   <li>Against mixer that changes method every 2 rounds: 67.03% </li>
 </ul>
 * 
 * The ThrowerBrain, which can detect patterns of throwing "repeater", "rotator", and "reflector", perform significantly better
 * than DeactivatedThrowerBrain when playing against these three kinds of AutomatedPlayers, plus Mixer, which randomly picks from 
 * the above 3 methods. 
 * 
 * However, ThrowerBrain has drawbacks:
 <ul>
   <li>no ability to ensure its winning against randomizer.</li>
   <li>the first two rounds are random throws, so it is hard to ensure 100% win against repeater, rotator, and reflector.</li>
   <li>takes a few rounds to adjust to a new throwing method, so winning percentage exponentially decreases when the rounds
    for which a mixer continues in a single throwing method decreases.</li>
  </ul>
 * 
 * 
 * @author Haidun Liu
 * @version 1.01 2015-10-1
 */
public class ThrowerBrain {

	/**
	 * Constructor. Gives ThrowerBrain reference to the GameRecord in the game.
	 * @param myGameRecord the GameRecord in the game.
	 */
	public ThrowerBrain(GameRecord myGameRecord) {
		this.myGameRecord = myGameRecord;
		agents = new PatternAgent[3];
		agents[REPEATER_PATTERN_AGENT_INDEX] = new RepeaterPatternAgent();
		agents[ROTATOR_PATTERN_AGENT_INDEX] = new RotatorPatternAgent();
		agents[REFLECTOR_PATTERN_AGENT_INDEX] = new ReflectorPatternAgent(myGameRecord);
		myRandom = new Random();
	}

	/**
	 * Analyzes the Player's throwing pattern by analyzing data from GameRecord.
	 * After the player made a throw, each of the PatternAgents makes a prediction about what
	 * 	that throw would be, based on the player's second to last throw. If the prediction matches the actual throw, 
	 * 	the patternDetector's streak +1. If prediction is false, streak = 0.
	 */
	public void analyze() {
		for (int i = 0; i < agents.length; i++) {
			ArrayList<Round> allRounds = myGameRecord.getRounds();
			char playersSecondToLastThrow = allRounds.get(allRounds.size()-2).getPlayerChoice();
			char playersLastThrow = allRounds.get(allRounds.size()-1).getPlayerChoice();
			if (agents[i].guessPlayersLastThrow(playersSecondToLastThrow) == playersLastThrow) {
				agents[i].addStreak();
			} else {
				agents[i].resetStreak();
			}
		}
	}

	/**
	 * Suggests the next throw based on analysis of the Player's throw pattern.
	 * Consults the PatternAgent with the highest streak of correct guesses.
																			 * If all PatternAge``````nts have streak = 0, returns a random throw. 
	 * if two patterns have the same streak, reflector is favored. (rotator and repeater would not simultaneously have >1 streak)
	 */
	public char suggest() {
		int indexBest[] = findBestAgent();

		//if all PatternAgents have streak = 0, returns a random throw. 
		if (indexBest.length == 0) {
			return randomThrow();
		}

		//if only one pattern has the highest streak, this pattern is consulted for the next throw.
		ArrayList<Round> allRounds = myGameRecord.getRounds();
		char playersLastThrow = allRounds.get(allRounds.size()-1).getPlayerChoice();
		if (indexBest.length == 1) {
			return agents[indexBest[0]].suggest(playersLastThrow);
		}

		//if two patterns have the same streak, reflector is favored. (rotator and repeater cannot simultaneously have >1 streak)
		//according to the indexes of agents defined in array, Reflector, if found, would always be the second in the indexBest array
		return  agents[indexBest[1]].suggest(playersLastThrow);
	}

	/**
	 * Returns a random throw among the 5 throw options.
	 * @return a random throw among the 5 throw options.
	 */
	public char randomThrow() {
		int myThrowIndex = myRandom.nextInt(5);
		char[] options = Talker.getThrowChoices();
		char myThrow = options[myThrowIndex];
		return myThrow;
	}
	
	/**
	 * Return an array containing the index(s) of the agent(s) with the highest streak of correct guesses.
	 * If all patterns have streak of 0, returns an empty array. 
	 * Precondition: there are three agents in the agents[] array.
	 * @return index of agent with highest streak (indexes as defined in constants)
	 */
	private int[] findBestAgent() {
		//first, checks if all agents has streak == 0. If so, returns an empty array.
		int totalStreak = 0;
		for (int i = 0; i < agents.length; i++) {
			totalStreak = totalStreak + agents[i].getStreak();
		}
		if (totalStreak == 0) {
			return new int[] {};
		}

		//if not, finds the highest streak and the index of that agent to which this streak belongs.
		int indexMax = -1;
		int maxStreak = -1;
		for (int i = 0; i < agents.length; i++) {
			if (agents[i].getStreak() > maxStreak) {
				indexMax = i;
				maxStreak = agents[i].getStreak();
			}
		}

		//now, checks if there are agents with the same streak as the max detected.
		//based on the algorithm above, indexMax is the index of the first agent with its streak value.
		int indexSecondMax = -1;
		for (int i = indexMax; i < agents.length; i++) {
			if (agents[i].getStreak() == maxStreak) {
				indexSecondMax = i;
			}
		}
		if (indexMax == indexSecondMax) {
			return new int[] {indexMax};
		} else {
			return new int[] {indexMax, indexSecondMax};
		}
	}

	/**
	 * The GameRecord in the game.
	 */
	private GameRecord myGameRecord;
	/**
	 * The array of all PatternAgents.
	 * The agents are arranged by the constants below.
	 */
	private PatternAgent [] agents;

	/**
	 * Index of the RepeaterPatternAgent in the array agents.
	 */
	private final int REPEATER_PATTERN_AGENT_INDEX = 0;
	/**
	 * Index of the RotatorPatternAgent in the array agents.
	 */
	private final int ROTATOR_PATTERN_AGENT_INDEX = 1;
	/**
	 * Index of the ReflectorPatternAgent in the array agents.
	 */
	private final int REFLECTOR_PATTERN_AGENT_INDEX = 2;
	/**
	 * A Random used to choose anything random, from a random throw to a random method (mixerThrow())
	 */
	protected Random myRandom;
}
