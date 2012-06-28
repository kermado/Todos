import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Todos application.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class Todos {

	/**
	 * Application frame.
	 */
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Todos window = new Todos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Todos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TodosModel model = new TodosModel();
		TodosView view = new TodosView(model);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Todos");
		
		frame.getContentPane().add(view, BorderLayout.CENTER);
	}

}