/**
 * A custom exception to handle cases where users enter invalid throw choices.
 * It is thrown when e.g. the user types a number when asked for a character that represents one of the throw choices,
 * or when the user types a negative double when asked for a positive int representing the desired lifespan for the AutomatedPlayer. 
 * @author Haidun Liu. Referred to http://alvinalexander.com/java/java-custom-exception-create-throw-exception.
 * @version 1.01 2015-10-1
 */

public class InvalidChoiceException extends Exception{
	public InvalidChoiceException(){
		super();
	}
}
