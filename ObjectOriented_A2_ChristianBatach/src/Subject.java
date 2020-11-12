/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

// Subject interface | implemented in User.java
public interface Subject {
	
	public void attach(Observer observer);
	
	public void detach(Observer observer);
	
	public void notifyObservers();

	public String getUpdate(Observer o);
	
}
