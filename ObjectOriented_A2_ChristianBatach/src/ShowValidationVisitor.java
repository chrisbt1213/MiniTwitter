/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Dec 04, 2020
 */

public class ShowValidationVisitor implements Visitor {
	
	/* NOTE: I already took care of duplicate users in A2 by displaying a dialogue box when 
	 * someone wants to create user with a name that already exists.
	 * You can find that in line 36 of TreeActionsBackEnd.java */
	
	// Keep count of all user who have spaces 
	int counter=0;
	
	@Override
	public void visit(UsersComponent node) {
		if(node.getID().contains(" ")) {
			counter++;
			System.out.println(counter);
		}
		else return;
	}

	public int result(){
		return counter;
	}

}