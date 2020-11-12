/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

// Composite extending UsersComponent 
public class UserGroup extends UsersComponent {

	private String groupID;
	
	// Implements UserComponent
	@Override
	public void setID(String id) {
		this.groupID = id;
	}

	// Implements UserComponent
	@Override
	public String getID() {
		return this.groupID;
	}

	// Implements UserComponent
	@Override
	public String toString() {
		return this.getID();
	}
	
	public UserGroup(String id){
		setID(id);
	}
	
	public void insert(UsersComponent user,int childNodeIndex){
		super.insert(user, childNodeIndex);
	}

	public void removeUser(UsersComponent user){
		super.remove(user);
	}
		
}