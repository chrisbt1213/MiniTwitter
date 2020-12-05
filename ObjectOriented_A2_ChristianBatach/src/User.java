/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

import javax.swing.DefaultListModel;

public class User extends UsersComponent implements Observer, Subject {
	

	private String userID;
	
	// Applies by observers to update follower News Feeds
	private DefaultListModel<String> newsFeedList = new DefaultListModel<String>();

	// To store followers
	private DefaultListModel<Observer> followers = new DefaultListModel<Observer>();
	
	// To store users being followed 
	private DefaultListModel<Subject> following = new DefaultListModel<Subject>();
		
	//To store posted messages
	private String message;
	
	// Used to check that a message has been posted and notify observers 
	private boolean messagePostedAlert = false;
	
	// creationTime adds the creation time attribute and lastUpdateTime updates when a new tweet is posted
	private long creationTime, lastUpdateTime;
	
	// Constructor
	public User(String id){
		setID(id);
		this.allowsChildren=false;
		this.newsFeedList=new DefaultListModel<String>();
		setTimeStamp();
	}
	
	// Implements UserComponent
	@Override
	public void setID(String id){
		this.userID=id;
	}
	
	// Implements UserComponent
	@Override
	public String getID(){
		return this.userID;
	}
	
	public DefaultListModel<String> getNewsFeedListModel(){
		return this.newsFeedList;
	}

	public DefaultListModel<Subject> getFollowingsListModel(){
		return this.following;
	}
	
	// Implements UserComponent
	@Override
	public String toString(){
		return this.getID();
	}
	
	// Attaches a user to the subject list of an object
	public void follow(User user){
		setSubject(user);
		user.attach(this);
	}
	
	// Saves message to be posted and notifies observers
	public void tweetByThisUser(String message){
		this.message = message;
		newsFeedList.addElement("Posted by me: "+message);
		//Assigns the last updated time of this owner's feed
		lastUpdateTime = System.currentTimeMillis();
		this.messagePostedAlert = true;
		notifyObservers();
	}
	
	// Returns NewsFeedList as an array 
	public Object[] getMessages(){
			return  this.newsFeedList.toArray();
	}
	
	// Returns Followers as an array
	public Object[] getFollowers(){
		return this.followers.toArray();
	}
	

	// Returns users being followed as an array 
	public Object[] getFollowings(){
		return this.following.toArray();
	}
	
	// Implements Subject ***************************************
	@Override
	public void attach(Observer observer) {
		followers.addElement(observer);
	}
	
	@Override
	public void detach(Observer observer) {
		followers.removeElement(observer);
	}
	
	// After observers are notified, messagePostedAlert is turned off until the next post
	@Override
	public void notifyObservers() {
		if(messagePostedAlert){
			messagePostedAlert=false;
			for(Object user:followers.toArray())
				((Observer) user).update(this);
			}
		else 
			return;
	}
	@Override
	public String getUpdate(Observer o) {
		return this.message;
	} // ********************************************************
	
	// Implements Observer **************************************
	@Override
	public void update(Subject subject) {
		String update=subject.getUpdate(this);
		
		//Updates news feeds and shows who posted the message
		this.newsFeedList.addElement("Posted by "+subject.toString()+": "+update);
		
		lastUpdateTime = System.currentTimeMillis();
		//Allows other users to know when this user last updated their feed
		this.newsFeedList.addElement(subject.toString()+" last updated on " + String.valueOf(getLastUpdateTime()));
		
	}
	@Override
	public void setSubject(Subject subject) {
		following.addElement(subject);
		
	} // ********************************************************

	@Override
	public void setTimeStamp() {
		creationTime = System.currentTimeMillis();
	}

	@Override
	public long getTimeStamp() {
		return creationTime;
	}
	
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}


}