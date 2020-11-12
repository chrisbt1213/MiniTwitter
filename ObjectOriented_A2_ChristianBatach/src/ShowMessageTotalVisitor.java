/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

public class ShowMessageTotalVisitor implements Visitor {

	//Keep count of messages
	int counter = 0;
	
	@Override
	public void visit(UsersComponent node) {
		if(node instanceof User){
			counter+=((User) node).getMessages().length;
		}
	}

	public int result(){
		return counter;
	}
}
