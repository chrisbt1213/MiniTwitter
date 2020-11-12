/* Christian Batach
 * CS 3560 - 01
 * Dr. Sun
 * Nov. 12, 2020
 */

// Observer interface | implemented in User.java
public interface Observer {
	
	public void update(Subject subject);

	public void setSubject(Subject subject);
	
}
