/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class AdminControlPanel {

	private static AdminControlPanel instance;
	private JFrame frame;
	private JPanel panelUserId;
	private JPanel panelGroupId;
	private JPanel panelTree;
	private JTextArea txtUserID;
	private JTextArea txtGroupID;
	private JButton btnAddUser;
	private JButton btnAddGroup;
	private JButton btnOpenUserView;
	private JButton btnShowUserTotal;
	private JButton btnShowGroupTotal;
	private JButton btnShowMessagesTotal;
	private JButton btnShowPosPercent;
	private JButton btnIdVerification;
	private JButton btnLastUpdatedUser;
	private JTree tree;
	private TreeActionsBackEnd actionsTree;
	
	// PopUp window for Error Handling 
	private void popUp(String errorMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, errorMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Singleton - in order to only get one instance of Admin Control *************************
	public static AdminControlPanel getInstance() {
		if(instance == null) {
			synchronized (AdminControlPanel.class) {
				if (instance == null) {
					instance = new AdminControlPanel();
				}
			}
		}
		return instance;
	}
	
	private AdminControlPanel() {
		initialize();
	}
	//*****************************************************************************************
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(0, 204, 255));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Keeps tree's actions in a separate class
		actionsTree = new TreeActionsBackEnd(new HashMap<String,UsersComponent>());
		
		//Actions of buttons of this class included below
		ActionsAdmin listener = new ActionsAdmin();
		
		btnAddUser = new JButton("Add User");
		btnAddUser.setBackground(Color.WHITE);
		btnAddUser.setForeground(new Color(0, 153, 204));
		btnAddUser.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnAddUser.setBounds(605, 21, 189, 58);
		btnAddUser.addActionListener(listener);
		frame.getContentPane().add(btnAddUser);
		
		panelUserId = new JPanel();
		panelUserId.setBackground(new Color(0, 204, 255));
		panelUserId.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Enter New User Id", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelUserId.setBounds(304, 12, 289, 67);
		frame.getContentPane().add(panelUserId);
		panelUserId.setLayout(null);
		
		txtUserID = new JTextArea();
		txtUserID.setBounds(6, 18, 277, 43);
		panelUserId.add(txtUserID);
		
		btnAddGroup = new JButton("Add Group");
		btnAddGroup.setForeground(new Color(0, 153, 204));
		btnAddGroup.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnAddGroup.setBackground(Color.WHITE);
		btnAddGroup.setBounds(605, 101, 189, 58);
		btnAddGroup.addActionListener(listener);
		frame.getContentPane().add(btnAddGroup);
		
		panelGroupId = new JPanel();
		panelGroupId.setLayout(null);
		panelGroupId.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Enter New Group Id", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGroupId.setBackground(new Color(0, 204, 255));
		panelGroupId.setBounds(304, 91, 289, 67);
		frame.getContentPane().add(panelGroupId);
		
		txtGroupID = new JTextArea();
		txtGroupID.setBounds(6, 18, 277, 43);
		panelGroupId.add(txtGroupID);
		
		btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.setBackground(Color.WHITE);
		btnOpenUserView.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnOpenUserView.setForeground(new Color(0, 153, 204));
		btnOpenUserView.setBounds(304, 176, 490, 58);
		btnOpenUserView.addActionListener(listener);
		frame.getContentPane().add(btnOpenUserView);
		
		btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.setForeground(new Color(0, 153, 204));
		btnShowUserTotal.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnShowUserTotal.setBounds(304, 308, 239, 67);
		btnShowUserTotal.addActionListener(listener);
		frame.getContentPane().add(btnShowUserTotal);
		
		btnShowGroupTotal = new JButton("Show Group Total");
		btnShowGroupTotal.setForeground(new Color(0, 153, 204));
		btnShowGroupTotal.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnShowGroupTotal.setBounds(555, 308, 239, 67);
		btnShowGroupTotal.addActionListener(listener);
		frame.getContentPane().add(btnShowGroupTotal);
		
		btnShowMessagesTotal = new JButton("Show Messages Total");
		btnShowMessagesTotal.setForeground(new Color(0, 153, 204));
		btnShowMessagesTotal.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnShowMessagesTotal.setBounds(555, 387, 239, 67);
		btnShowMessagesTotal.addActionListener(listener);
		frame.getContentPane().add(btnShowMessagesTotal);
		
		btnShowPosPercent = new JButton("Show Positive Percentage");
		btnShowPosPercent.setForeground(new Color(0, 153, 204));
		btnShowPosPercent.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnShowPosPercent.setBounds(304, 387, 239, 67);
		btnShowPosPercent.addActionListener(listener);
		frame.getContentPane().add(btnShowPosPercent);
		
		btnIdVerification = new JButton("ID Verification");
		btnIdVerification.setForeground(new Color(0, 153, 204));
		btnIdVerification.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnIdVerification.setBounds(304, 235, 239, 67);
		btnIdVerification.addActionListener(listener);
		frame.getContentPane().add(btnIdVerification);
		
		btnLastUpdatedUser = new JButton("Last Updated User");
		btnLastUpdatedUser.setForeground(new Color(0, 153, 204));
		btnLastUpdatedUser.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnLastUpdatedUser.setBounds(555, 235, 239, 67);
		btnLastUpdatedUser.addActionListener(listener);
		frame.getContentPane().add(btnLastUpdatedUser);
		
		panelTree = new JPanel();
		panelTree.setBackground(new Color(0, 204, 255));
		panelTree.setBorder(new TitledBorder(null, "Tree View", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTree.setBounds(0, 3, 298, 457);
		frame.getContentPane().add(panelTree);
		panelTree.setLayout(null);
		
		tree = new JTree();
		tree.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		tree.setEditable(true);
		tree.setBackground(Color.WHITE);
		tree=new JTree(actionsTree.getModel());
		panelTree.add(tree);
		tree.setForeground(Color.WHITE);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setBounds(6, 18, 286, 433);
	}
	
	// Error handling: in case "Open User View" button is pressed on a group instead of a user (also works in case click is outside of panel)
	public void openUserView(UsersComponent user) {
		UsersComponent node=getSelectedNode(this.tree);
		if(!(node instanceof User)){
			popUp("This button doesn't work on groups","ERROR");
			return;
		}
		else {
			new UserPanel((User)node,actionsTree);
		}
	}
	
	//To select users or groups on Click
	public UsersComponent getSelectedNode(JTree tree){
		TreePath parentPath = tree.getSelectionPath(); 
		UsersComponent selectedNode=null;
		if (parentPath == null) {
			selectedNode = (UsersComponent) actionsTree.getModel().getRoot();
		} else {
			selectedNode = (UsersComponent)(parentPath.getLastPathComponent());
		}
		return selectedNode;	
	}
	
	private class ActionsAdmin implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//grab the currently selected node in the j tree.
			UsersComponent selectedNode=getSelectedNode(tree);

			//Adds User node on Click
			if(e.getSource()==btnAddUser){
				String userId=txtUserID.getText().trim();
				UsersComponent newUser=new User(userId);
				selectedNode=getSelectedNode(tree);
				if(userId.isEmpty())
					return;
				if(actionsTree.addNode(selectedNode,newUser)){
					tree.scrollPathToVisible(new TreePath(newUser.getPath()));
				}
				else {
					return;
				}
				
			}
			//Adds Group node on Click
			if(e.getSource()==btnAddGroup){
				String groupId=txtGroupID.getText().trim();
				UsersComponent newUserGroup=new UserGroup(groupId);
				if(groupId.isEmpty())
					return;
				if(actionsTree.addNode(selectedNode,newUserGroup)){
					tree.scrollPathToVisible(new TreePath(newUserGroup.getPath()));
					//prints creationTime attribute on console
					System.out.println(newUserGroup.getTimeStamp());
				}
				else {
					return;
				}
			}
			//Opens User View On Click
			if(e.getSource()==btnOpenUserView){
				openUserView(selectedNode);
			}
			else{
				// Validates names of groups/users
				if(e.getSource()==btnIdVerification){
					ShowValidationVisitor validation=new ShowValidationVisitor();
					actionsTree.accept(validation);
					if(validation.result() > 0){
						popUp("A total of "+validation.result()+" user or group names are invalid because they "
								+ "contain spaces!","Invalid Names");
					}
					else {
						popUp("All user and group names are valid!","Valid Names");
					}
				}
				//Displays User that Last Updated
				if(e.getSource()==btnLastUpdatedUser){
					ShowLastUpdatedVisitor lastUpdated=new ShowLastUpdatedVisitor();
					actionsTree.accept(lastUpdated);
					popUp("The user that last updated is "+lastUpdated.getLastUpdated(),"Last Updated User");
				}
				//Displays Total Number of User nodes on Click
				if(e.getSource()==btnShowUserTotal){
					ShowUserTotalVisitor userTotal=new ShowUserTotalVisitor();
					actionsTree.accept(userTotal);
					popUp("Total number of users created so far: "+userTotal.result(),"Total Number of Users");
				}
				//Displays Total Number of Group nodes on Click
				if(e.getSource()==btnShowGroupTotal){
					ShowGroupTotalVisitor groupTotal=new ShowGroupTotalVisitor();
					actionsTree.accept(groupTotal);
					popUp("Total number of groups created so far: "+groupTotal.result(),"Total Number of Groups");

				}
				//Displays Positive Message Percentage on Click
				if(e.getSource()==btnShowPosPercent){
					//Pass list of positive words
					ShowPosPercentVisitor positivePercentage=new ShowPosPercentVisitor("love enjoy insipired great awesome rad cool");
					actionsTree.accept(positivePercentage);
					popUp(String.format("%.2g",positivePercentage.result())+"% of Messages Posted are Positive","Positive Messages Total");
				}
				//Displays Total Number of Messages on Click
				if(e.getSource()==btnShowMessagesTotal){
					ShowMessageTotalVisitor messagesTotal= new ShowMessageTotalVisitor();
					actionsTree.accept(messagesTotal);
					popUp("Total number of messages posted: "+messagesTotal.result(),"Total Number of Messages");
				}
			}

		}
		
	}
	
}