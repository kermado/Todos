import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Todos application model.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class TodosModel extends Observable {

	/**
	 * List of todo items.
	 */
	private ArrayList<TodoItem> todoItems;
	
	/**
	 * TodosModel constructor.
	 */
	public TodosModel() {
		todoItems = new ArrayList<TodoItem>();
	}
	
	/**
	 * Returns the full list of todo items.
	 * 
	 * @return TodoItem ArrayList
	 */
	public ArrayList<TodoItem> getTodoItems() {
		return todoItems;
	}
	
	/**
	 * Adds a todo item to the list.
	 * 
	 * @param description todo item description
	 */
	public void addTodoItem(String description) {
		if (description.trim().length() > 0) {		
			todoItems.add(new TodoItem(description));
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Updates a todo item already entered into the list.
	 * 
	 * @param item todo item to update
	 * @param complete true to mark the item as complete, false otherwise
	 */
	public void updateTodoItem(TodoItem item, boolean complete) {
		item.setComplete(complete);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Clear completed todo items from the list.
	 */
	public void clearCompleted() {
		Iterator<TodoItem> items = todoItems.iterator();
		
		while (items.hasNext()) {
			TodoItem item = items.next();
			if (item.getComplete()) {
				items.remove();
			}
		}
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Counts the number of incomplete todo items in the list.
	 * 
	 * @return number of incomplete todo items
	 */
	public int countActive() {
		int count = 0;
		
		for (TodoItem item : todoItems) {
			if (!item.getComplete()) {
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * Counts the number of complete todo items in the list.
	 * 
	 * @return number of complete todo items
	 */
	public int countInactive() {
		return todoItems.size() - countActive();
	}
	
}