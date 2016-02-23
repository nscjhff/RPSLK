/**
 * Customized Exception. Used when something happens that is supposed to trigger the end of the game.
 * This Exception should always be passed back to GameRunner, who handles end-of-game commands.
 * Examples of when this is thrown: when user types the sentinel character into the console,
 * and when the lifespan of an AutomatedPlayer expires.
 * @author Haidun Liu Referred to http://alvinalexander.com/java/java-custom-exception-create-throw-exception.
 * @version 1.01 2015-10-1
 */

public class SentinelException extends Exception {
	public SentinelException(){
		super();
	}
}
