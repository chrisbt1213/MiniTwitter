/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Dec 04, 2020
 */

public class ShowLastUpdatedVisitor implements Visitor {
	
	private User lastUpdated;

	//Compares Users Last Updated Time to assign the User that updated last to lastUpdated
	@Override
	public void visit(UsersComponent node) {
		if(node instanceof User) {
			if (lastUpdated == null || ((User) node).getLastUpdateTime() > lastUpdated.getLastUpdateTime()) {
				lastUpdated = (User) node;
			}
		}
		
	}

	public User getLastUpdated() {
		return lastUpdated;
	}

}
