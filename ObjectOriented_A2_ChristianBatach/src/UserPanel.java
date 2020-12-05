/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

// User View Window
public class UserPanel {
	
		private JFrame userFrame;
		private JPanel toFollowPanel;
		private JTextArea toFollowTxt;
		private JButton followUserBtn;
		private JPanel followPanel;
		private JList<Subject> followingList;
		private JPanel postTweetPanel;
		private JTextArea postTweetTxt;
		private JButton postTweetBtn;
		private JPanel newsFeedPanel;
		private JList<String> newsFeedList;
		private JLabel lblUpdate;
		private User thisUser;
		private TreeActionsBackEnd treeDataHandler;
		private DefaultListModel<Subject> modelFollowing;
		
		// PopUp window for Error Handling 
		private void popUp(String errorMessage, String titleBar)
		{
			JOptionPane.showMessageDialog(null, errorMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
		}
	
	public UserPanel(User user,TreeActionsBackEnd actionsTree) {
		
		this.thisUser = user;
		this.treeDataHandler = actionsTree;
		thisUser.getNewsFeedListModel();
		this.modelFollowing=thisUser.getFollowingsListModel();
		Actions listener = new Actions();
		
		userFrame = new JFrame();
		userFrame.setVisible(true);
		// Displays creationTime attribute of user (for A3)
		userFrame.setTitle("User View" + " of @" + thisUser.getID() + " Created at " + String.valueOf(thisUser.getTimeStamp()));
		userFrame.getContentPane().setBackground(new Color(0, 153, 204));
		userFrame.setBounds(100, 100, 470, 640);
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		userFrame.getContentPane().setLayout(null);
		
		toFollowPanel = new JPanel();
		toFollowPanel.setBackground(new Color(0, 153, 204));
		toFollowPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "User Id You Want Follow", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		toFollowPanel.setBounds(16, 6, 249, 65);
		userFrame.getContentPane().add(toFollowPanel);
		toFollowPanel.setLayout(null);
		
		toFollowTxt = new JTextArea();
		toFollowTxt.setBounds(6, 18, 237, 41);
		toFollowPanel.add(toFollowTxt);
		
		followUserBtn = new JButton("Follow User");
		followUserBtn.setForeground(new Color(0, 153, 204));
		followUserBtn.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		followUserBtn.setBounds(272, 18, 182, 52);
		followUserBtn.addActionListener(listener);
		userFrame.getContentPane().add(followUserBtn);
		
		followPanel = new JPanel();
		followPanel.setBackground(new Color(0, 153, 204));
		followPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "List View (Currently Following)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		followPanel.setBounds(16, 75, 438, 135);
		userFrame.getContentPane().add(followPanel);
		followPanel.setLayout(null);
		
		followingList = new JList<Subject>(modelFollowing);
		followingList.setBounds(6, 18, 426, 112);
		followPanel.add(followingList);
		
		postTweetPanel = new JPanel();
		postTweetPanel.setBackground(new Color(0, 153, 204));
		postTweetPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Tweet Message", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		postTweetPanel.setBounds(16, 213, 268, 113);
		userFrame.getContentPane().add(postTweetPanel);
		postTweetPanel.setLayout(null);
		
		postTweetTxt = new JTextArea();
		postTweetTxt.setBounds(6, 18, 256, 90);
		postTweetPanel.add(postTweetTxt);
		postTweetTxt.setLineWrap(true);
		
		postTweetBtn = new JButton("Post Tweet");
		postTweetBtn.setForeground(new Color(0, 153, 204));
		postTweetBtn.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
		postTweetBtn.setBounds(289, 225, 165, 101);
		postTweetBtn.addActionListener(listener);
		userFrame.getContentPane().add(postTweetBtn);
		
		newsFeedPanel = new JPanel();
		newsFeedPanel.setLayout(null);
		newsFeedPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "List View (News Feed)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		newsFeedPanel.setBackground(new Color(0, 153, 204));
		newsFeedPanel.setBounds(16, 330, 438, 242);
		userFrame.getContentPane().add(newsFeedPanel);
		
		newsFeedList = new JList<String>(thisUser.getNewsFeedListModel());
		newsFeedList.setBounds(6, 18, 426, 218);
		newsFeedPanel.add(newsFeedList);
		
		lblUpdate = new JLabel("Last Updated: " + String.valueOf(thisUser.getLastUpdateTime()));
		lblUpdate.setBounds(29, 576, 425, 27);
		userFrame.getContentPane().add(lblUpdate);
		
	}
	
	public void tweet(String message){
		this.thisUser.tweetByThisUser(message);
	}

	public void follow(User userToFollow){
		this.thisUser.follow((User) userToFollow);
		popUp("You are now following "+userToFollow.getID()+"!","New User Followed");
	}
	
	private class Actions implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//source of action is follow button
			if(e.getSource() == followUserBtn){
				String userId = toFollowTxt.getText().trim();
				UsersComponent node = null;
				
				if(treeDataHandler.getUser(userId)!= null){
					node = treeDataHandler.getUser(userId);
				}
				else {
					popUp("User not found.","ERROR");
					return;
				}
				if(followErrorHandling(node)){
					return;
				}
				else if(!(node instanceof User)){
					popUp("Groups cannot be followed. Please enter a username to follow.","ERROR");
				}
				else{
					follow((User)node);
				}
			}
			//source of action is Tweet button
			if(e.getSource() == postTweetBtn){
				String message = postTweetTxt.getText().trim();
				tweet(message);
				//The moment a tweet is posted the time updates on the label
				lblUpdate.setText("Last Updated: " + String.valueOf(thisUser.getLastUpdateTime()));
			}
		}

	}

	// Error handling: in case users try to follow same user again or follow their own selves
	private boolean followErrorHandling(UsersComponent node){
		if(ifUserAlreadyFollowed(node)){
			popUp("You are already following this user.", "ERROR");
			return true;
		}
		if(followingOwn(node)){
			popUp("You cannot follow yourself.", "ERROR");
			return true;
		}
		return false;
	}
	
	// Used in method above (followErrorHandling)
	public boolean ifUserAlreadyFollowed(UsersComponent node){
		Object[] array= thisUser.getFollowings();
		for(Object user:array){
			if(user.equals(node))
				return true;
		}
		 return false;
	}
	
	// Used in method above (followErrorHandling)
	public boolean followingOwn(UsersComponent node){
		return node.getID().equals(thisUser.getID());
	}
	


}
