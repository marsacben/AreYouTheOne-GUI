import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JSpinner;

public class test extends JPanel {

	/**
	 * Create the panel.
	 */
	public test() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JSpinner spinner = new JSpinner();
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		add(spinner_1);

	}

}
