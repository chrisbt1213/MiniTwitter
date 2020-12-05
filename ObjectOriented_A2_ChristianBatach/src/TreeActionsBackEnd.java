/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;

//Handles actions performed in tree and implements Visitable 
public class TreeActionsBackEnd implements Visitable {

	private Map<String,UsersComponent> map;
	
	private DefaultTreeModel tree;
	
	// PopUp window for Error Handling 
	private void popUp(String errorMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, errorMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Applies default Root group
	public  TreeActionsBackEnd(Map<String,UsersComponent> map){
		UsersComponent root=new UserGroup("Root");
		this.map=map;
		this.tree=new DefaultTreeModel(root);
		map.put(root.getID(), root);
	}
	
	/* Add Nodes and Handles Errors in case User/Group name already exists in tree
	 * Or in case there's an attempt to create users under other users */
	public boolean addNode(UsersComponent parentNode,UsersComponent childNode) {
		if(treeContains(childNode.getID())){
			popUp("This name already exists, please choose another one","ERROR!");
			return false;
		}
		if(!parentNode.getAllowsChildren()){
			popUp("Users cannot be created under other users","ERROR");
			return false;
		}
		map.put(childNode.getID(), childNode);
		tree.insertNodeInto(childNode,parentNode,parentNode.getChildCount());
		return true;
	}
	
	//To check if already in tree
	public boolean treeContains(String userId) {
		return map.containsKey(userId);
	}

	public UsersComponent getUser(String userId){
		if (treeContains(userId)){
			return map.get(userId);
		}
		else{
			return null;
		}
	}

	public DefaultTreeModel getModel() {
		return this.tree;
	}

	// Implementation for Visitable
	@Override
	public void accept(Visitor visitor) {
		for (Map.Entry<String, UsersComponent> entry : map.entrySet()){
			  visitor.visit(entry.getValue());
		}

	}

}
