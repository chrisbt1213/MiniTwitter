/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

public class ShowUserTotalVisitor implements Visitor {
	
	// Keep count of all the user nodes created
	int counter=0;
	
	@Override
	public void visit(UsersComponent node) {
		if(node instanceof User)
			counter++;
		else return;
	}

	public int result(){
		return counter;
	}

}