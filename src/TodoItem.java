/**
 * Represents a single todo item.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class TodoItem {
	
	/**
	 * Description for the todo item.
	 */
	private String description;
	
	/**
	 * True if the todo item has been completed, false otherwise.
	 */
	private boolean complete = false;

	/**
	 * TodoItem constructor.
	 * 
	 * @param description description for the todo item
	 */
	public TodoItem(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the todo item's description.
	 * 
	 * @return todo item description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns true if the todo item has been completed, false otherwise.
	 * 
	 * @return true if complete, false otherwise
	 */
	public boolean getComplete() {
		return complete;
	}
	
	/**
	 * Change the completed status of the todo item.
	 * 
	 * @param complete true if todo item should be marked as complete, false otherwise
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
