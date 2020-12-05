/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

import javax.swing.tree.DefaultMutableTreeNode;

/* Extending DefaultMutableTreeNode for implementation in leaf and composite
 * Extended by User.java and UserGroup.java */
public abstract class UsersComponent extends DefaultMutableTreeNode{

	public abstract void setID(String ID);

	public abstract String getID();
	
	public abstract String toString();
	
	//Added Bottom Methods for A3
	public abstract void setTimeStamp();
	
	public abstract long getTimeStamp();

}
