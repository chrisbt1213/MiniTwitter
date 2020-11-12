/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

public class ShowGroupTotalVisitor implements Visitor {

	// Keep count of all the group nodes created | root is inlcuded
	int counter = 0;
	
	@Override
	public void visit(UsersComponent node) {
		if(node instanceof UserGroup)
			counter++;
		else 
			return;
	}

	public int result(){
		return counter;
	}
	
}
