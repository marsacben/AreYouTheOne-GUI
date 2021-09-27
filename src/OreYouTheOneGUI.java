import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Label;

public class OreYouTheOneGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OreYouTheOneGUI window = new OreYouTheOneGUI();
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
	public OreYouTheOneGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 864, 653);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JSpinner spinner = new JSpinner();
		panel.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		panel.add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		panel.add(spinner_2);
		
		JSpinner spinner_3 = new JSpinner();
		panel.add(spinner_3);
		
		JSpinner spinner_4 = new JSpinner();
		panel.add(spinner_4);
		
		JButton btnSubmitCeremony = new JButton("Submit Ceremony");
		panel.add(btnSubmitCeremony);
		
		Label label = new Label("Num Correct: ");
		panel.add(label);
		
		JSpinner spinner_5 = new JSpinner();
		panel.add(spinner_5);
	}

}
