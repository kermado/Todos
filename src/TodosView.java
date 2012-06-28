import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Todos application view.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class TodosView extends JPanel implements Observer {
	
	/**
	 * Reference to the model.
	 */
	private TodosModel model;
	
	/**
	 * New todo text field.
	 */
	private JTextField newTodo;
	
	/**
	 * Todos scroll pane.
	 */
	private JScrollPane todosScrollPane;
	
	/**
	 * Todos panel.
	 */
	private JPanel todosPanel;
	
	/**
	 * Lower panel that holds the "Clear completed tasks" button remaining tasks label.
	 */
	private JPanel actionsPanel;
	
	/**
	 * Remaining tasks label.
	 */
	private JLabel todosCountLabel;
	
	/**
	 * Clear completed tasks button.
	 */
	private JButton clearBtn;
	
	/**
	 * "Tasks remaining" text.
	 */
	public static final String txtRemaining = "tasks remaining";

	/**
	 * TodosView constructor.
	 * 
	 * @param model model to observe
	 */
	public TodosView(TodosModel model) {
		this.model = model;
		this.model.addObserver(this);
		setLayout(new BorderLayout(0, 0));
		
		newTodo = new JTextField();
		newTodo.setForeground(Color.DARK_GRAY);
		newTodo.setToolTipText("What needs to be done?");
		newTodo.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		newTodo.setColumns(10);
		add(newTodo, BorderLayout.NORTH);
		
		todosScrollPane = new JScrollPane();
		todosScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		todosScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(todosScrollPane, BorderLayout.CENTER);
		
		todosPanel = new JPanel();
		todosScrollPane.setViewportView(todosPanel);
		todosPanel.setLayout(new BoxLayout(todosPanel, BoxLayout.Y_AXIS));
		
		actionsPanel = new JPanel();
		actionsPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		actionsPanel.setLayout(new BorderLayout(0, 0));
		add(actionsPanel, BorderLayout.SOUTH);
		
		todosCountLabel = new JLabel(model.countActive() + " " + txtRemaining);
		todosCountLabel.setForeground(Color.DARK_GRAY);
		actionsPanel.add(todosCountLabel, BorderLayout.CENTER);
		
		clearBtn = new JButton("Clear completed tasks");
		clearBtn.setEnabled(false);
		actionsPanel.add(clearBtn, BorderLayout.EAST);
		
		newTodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getModel().addTodoItem(((JTextField) e.getSource()).getText());
				newTodo.setText(null);
			}
		});
		
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getModel().clearCompleted();
			}
		});
	}
	
	/**
	 * Returns the model being observed.
	 * 
	 * @return current model being observed
	 */
	public TodosModel getModel() {
		return model;
	}
	
	/**
	 * Update all the todo checkboxes.
	 */
	public void updateTodoCheckboxes() {
		ArrayList<TodoItem> todos = model.getTodoItems();
		todosPanel.removeAll();
		
		for (final TodoItem item : todos) {
			JCheckBox checkbox = new JCheckBox(item.getDescription(), item.getComplete());
			todosPanel.add(checkbox);
			checkbox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getModel().updateTodoItem(item, ((JCheckBox) e.getSource()).isSelected());
				}
			});
		}
		
		todosPanel.revalidate();
		todosPanel.repaint();
	}
	
	/**
	 * Update the "Tasks remaining" label.
	 */
	public void updateCountLabel() {
		todosCountLabel.setText(model.countActive() + " " + txtRemaining);
		todosCountLabel.repaint();
	}
	
	/**
	 * Update the clear completed tasks button.
	 */
	public void updateClearBtn() {
		clearBtn.setEnabled(model.countInactive() > 0);
	}

	/**
	 * Called when model notifies the view of changes.
	 * 
	 * @param o observable object
	 * @param arg arguments passed from model
	 */
	@Override
	public void update(Observable o, Object arg) {
		updateTodoCheckboxes();
		updateCountLabel();
		updateClearBtn();
	}
	
}