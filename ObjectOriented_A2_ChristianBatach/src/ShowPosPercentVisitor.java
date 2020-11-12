/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */
import java.util.Arrays;

public class ShowPosPercentVisitor implements Visitor {
	
	// Keep count of positive messages
	int posMessageCounter = 0;
	
	/* Array used to count positive words mentioned in message
	 * Counts words even if there are no spaces around them */
	String[] positiveWords;
	
	/* Keep count of all messages in order to calculate percentage 
	 * of messages that are positive out of all the messages created */
	int totalMessagesCounter = 0;

	public ShowPosPercentVisitor(String positiveWords){
		
		this.positiveWords = positiveWords.toLowerCase().split(" ");
	}
	
	public ShowPosPercentVisitor(String[] positiveWords){
		// Make String (text) lower case 
		for(String positiveWord: positiveWords){
			positiveWord=positiveWord.toLowerCase();
		}
		this.positiveWords = positiveWords;
		
	}
	
	//Loops through messages and keeps track of only those that have positive words
	@Override
	public void visit(UsersComponent node) {
		if(node instanceof User){
			Object[] array=((User) node).getMessages();
			String[] messages = Arrays.copyOf(array,array.length, String[].class);
			totalMessagesCounter += messages.length;
			for(String currentKeyWord : positiveWords)
				for(String currentMessage:messages)
					if(currentMessage.toLowerCase().contains(currentKeyWord))
						posMessageCounter++;
		}
	}

	public double result(){
		// Calculate and return percentage
		return posMessageCounter * 100.0 / totalMessagesCounter;
	}
}