import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JSpinner;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.rmi.CORBA.Util;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Match;
import model.MiniMax;
import model.Person;
import model.Picks;
import model.Results;
import model.Season;
import model.TreeTrim;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import java.awt.Component;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;

public class TrimAlgoGUI extends JFrame implements ChangeListener{

	private JPanel contentPane;
	private JTable tableCeremony;
	private JTable tableTB;
	private static int numPairs = 5;
	
	
	
	
	//private static String[][] contestants = {{"M1", "M2", "M3", "M4", "M5"}, {"F1", "F2", "F3", "F4", "F5"}};
	//private static SpinnerListModel inputModel = new SpinnerListModel(contestants);
	//private static SpinnerListModel inputModel2 = new SpinnerListModel(contestants);
	private static SpinnerListModel[] inputModels = new SpinnerListModel[numPairs*2];
	private static JTextField[][] inputs; 
	private static String[][] inputNames;
	static String tbNames[]= {"null", "null"};
	static JButton btnTruthBooth = new JButton("Truth Booth");
	static JButton btnSubmitCeremony = new JButton("Submit Ceremony");
	static JButton btnSubmitNumPairs = new JButton("Submit Num Pairs");
	static JButton btnSubmit = new JButton("Submit");
	static JRadioButton rdbtnQueer = new JRadioButton("Make Queer");
	static JTextField PairsInput = new JTextField();
	static DefaultTableModel modelTB = new DefaultTableModel();
	static DefaultTableModel modelCeremony = new DefaultTableModel();
	static LinkedList<Person> people = new LinkedList();
	static Boolean isTruthBooth = true;
	static Season s = null;
	static MiniMax algorithm ;
	static Results r;
	static int iTB =0;
	static int iCeremony =0;
	private JTextField a1;
	private static JTextField numPairsInput;
	static JPanel InputContestantsPanel = new JPanel();
	static boolean isQueer = false;
	/*private JSpinner m1 = new JSpinner(inputModel);
	private JSpinner m2 = new JSpinner(inputModel);
	private JSpinner m3 = new JSpinner(inputModel);
	private JSpinner m4 = new JSpinner(inputModel);
	private JSpinner m5 = new JSpinner(inputModel);
	private JSpinner f1 = new JSpinner(inputModel);
	private JSpinner f2 = new JSpinner(inputModel);
	private JSpinner f3 = new JSpinner(inputModel);
	private JSpinner f4 = new JSpinner(inputModel);
	private JSpinner f5 = new JSpinner(inputModel);*/
	//private JSpinner[][] inputs = {{m1,m2,m3,m4,m5}, {f1,f2,f3,f4,f5}};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrimAlgoGUI frame = new TrimAlgoGUI();
					btnSubmitCeremony.setEnabled(false);
					btnSubmit.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							getSelection();
							btnSubmit.setEnabled(false);
							btnSubmit.setVisible(false);
						}
					});
					btnSubmitNumPairs.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							numPairs = Integer.parseInt(numPairsInput.getText());
							inputs= new JTextField[numPairs][2];
							isQueer = rdbtnQueer.isSelected();
							for(int i=0; i<2; i++) {
								for(int j=0; j<numPairs; j++) {
									inputs[j][i] = new JTextField(10);
									String s = "cell " + j + " " + i;
									System.out.println(s);
									InputContestantsPanel.add(inputs[j][i], s);
								}
							}
							btnSubmitNumPairs.setEnabled(false);
							btnSubmitNumPairs.setVisible(false);
							Vector row = new Vector();
		    	            row.add("Person 1");
		    	            row.add("Person 2");
		    	            row.add("isMatch");
		    	            modelTB.addRow(row);
		    	            
		    	            Vector row2 = new Vector();
		    	            for(int i=0; i<numPairs; i++) {
		    	            	String s = "Pair";
		    	            	row2.add(s);
		    	            }
		    	            row2.add("numMatch");
		    	            modelCeremony.addRow(row2);
							//createInputFields(); 
							//getSelection(isQueer);
						}
					});  

			        btnTruthBooth.addActionListener(new ActionListener(){  
			        	public void actionPerformed(ActionEvent e){  
			        		Match m = r.getMatch(iTB);
					        System.out.println(m.toString());
					        boolean isMatch = r.getTruthBooth(iTB);
		    	            Vector row = new Vector();
		    	            row.add(m.getP1().getName());
		    	            row.add(m.getP2().getName());
		    	            row.add(isMatch);
		    	            modelTB.addRow(row);
		    	            System.out.println(iTB);
		    	            btnTruthBooth.setEnabled(false);
		    	            btnSubmitCeremony.setEnabled(true);
		    	            iTB++;
			        	        }  
			        	    });  
			        btnSubmitCeremony.addActionListener(new ActionListener(){  
			        	public void actionPerformed(ActionEvent e){  
			        	            Picks p = r.getPick(iCeremony);
			        	            int numMatch = r.getCeremony(iCeremony);
			        	            Vector row = new Vector();
			        	            for(int i=0; i<numPairs; i++) {
			        	            	String s = p.getPair(i).getP1().getName() + " & " + p.getPair(i).getP2().getName();
			        	            	row.add(s);
			        	            }
			        	            row.add(numMatch);
			        	            
			        	            modelCeremony.addRow(row);
			        	            btnTruthBooth.setEnabled(true);
			        	            if(numMatch == numPairs) {
			        	            	btnTruthBooth.setEnabled(false);
			        	            }
			        	            
				    	            btnSubmitCeremony.setEnabled(false);
			        	            iCeremony++;
			        	        }  
			        	    });  
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Results runSeason(String[][] names, boolean isQueer){
		Season s;
		TreeTrim util;
		if(isQueer) {
			s = new Season(names);
			LinkedList<Person> contestants = s.getContestants();
			util = new TreeTrim(contestants);
		}
		else {
			boolean[][] gender= new boolean[numPairs][2];
			for(int i=0; i<numPairs; i++) {
				gender[i][0] = true;
				gender[i][1] = false;
				
			}
			//String[][] ans = {{"M10","M9","M8","M7", "M6","M5","M4","M3","M2","M1"}};
			s = new Season(names, gender);
			LinkedList<Person>[] contestants = s.getContestantsSplit();
			//System.out.println("group1:" + contestants[0]);
			//System.out.println("group2:" + contestants[1]);
			util = new TreeTrim(contestants[0], contestants[1]);
		}
        LinkedList<Picks> picks = new LinkedList<>();
        LinkedList<Match> matches = new LinkedList<>();
        LinkedList<Boolean> tb = new LinkedList<>();
        LinkedList<Integer> ceremony = new LinkedList<>();
        LinkedList<Hashtable<Integer, LinkedList<Person>>> ruledOut = new LinkedList<>();
        int beams =0;
        int i=1;
        while(beams != numPairs){
            System.out.println("Round " + (i));
            Match m = util.getTruthBooth();
            matches.add(m);
            boolean resp = s.truthBoth(m);
            tb.add(resp);
            util.recordTruthBooth(resp);
            System.out.println("TruthBooth: " + m.toString() + " ans: " + resp);
            Picks p = util.getCeremony();
            picks.add(p);
            System.out.println("Pick: " + p.toString());
            beams = s.ceremony(p);
            ceremony.add(beams);
            System.out.println("beams: " + beams);
            util.recordCeremony(beams);
            //ruledOut.add(util.ruledOut);
            System.out.println("------");
            i++;
        }
        //LinkedList<Picks> last = new LinkedList<>();
        //last.add(picks.getLast());
        //System.out.println(last.add(picks.getLast()).toSt);
        //testCorrectPicks(10,ans,last);
        return new Results(picks, matches, tb, ceremony, ruledOut);
    }
	
	
	/**
	 * Create the frame.
	 */
	public TrimAlgoGUI() {
		//createInputFields();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1157, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JPanel TtitlePanel = new JPanel();
		GridBagConstraints gbc_TtitlePanel = new GridBagConstraints();
		gbc_TtitlePanel.insets = new Insets(0, 0, 0, 5);
		gbc_TtitlePanel.gridx = 0;
		gbc_TtitlePanel.gridy = 0;
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(TtitlePanel);
		TtitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel Title = new JPanel();
		TtitlePanel.add(Title);
		
		JLabel lblAreYouThe = new JLabel("Are You The One");
		Title.add(lblAreYouThe);
		
		JPanel input = new JPanel();
		TtitlePanel.add(input);
		
		JLabel NumPairsLabel = new JLabel("Enter Number Of Pairs");
		input.add(NumPairsLabel);
		
		
		
		
		
		input.add(btnSubmitNumPairs);
		
		numPairsInput = new JTextField();
		input.add(numPairsInput);
		numPairsInput.setColumns(10);
		
		
		input.add(rdbtnQueer);
		contentPane.add(InputContestantsPanel);
		InputContestantsPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][][]", "[][]"));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		
		
		contentPane.add(btnSubmit);
		
		JPanel results = new JPanel();
		contentPane.add(results);
		
		JPanel panel = new JPanel();
		results.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		tableTB = new JTable(modelTB);
		panel.add(tableTB);
		JTableHeader header = tableTB.getTableHeader();
		btnTruthBooth.setAlignmentY(Component.TOP_ALIGNMENT);
		btnTruthBooth.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		panel.add(btnTruthBooth);
		
		JPanel CeremonyPanel = new JPanel();
		results.add(CeremonyPanel);
		CeremonyPanel.setLayout(new BoxLayout(CeremonyPanel, BoxLayout.Y_AXIS));
		
		
		tableCeremony = new JTable(modelCeremony);
		CeremonyPanel.add(tableCeremony);
		CeremonyPanel.add(btnSubmitCeremony);
		btnSubmitCeremony.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_CeremonyPanel = new GridBagConstraints();
		gbc_CeremonyPanel.insets = new Insets(0, 0, 0, 5);
		gbc_CeremonyPanel.gridx = 3;
		gbc_CeremonyPanel.gridy = 0;
		GridBagConstraints gbc_Info = new GridBagConstraints();
		gbc_Info.gridx = 4;
		gbc_Info.gridy = 0;
		
		for(int i=0; i<numPairs; i++) {
			modelCeremony.addColumn("Pair");
		}
		modelCeremony.addColumn("Num Correct");
		
        modelTB.addColumn("Person 1");
        modelTB.addColumn("Person 1");
        modelTB.addColumn("Are Match");
		header.setBackground(Color.yellow);
	
	}
	
	public static void createInputFields() {
		for(int i=0; i<2; i++) {
			for(int j=0; j<numPairs; j++) {
				inputs[i][j] = new JTextField(10);
				String s = "cell " + i + " " + j;
				System.out.println(s);
				InputContestantsPanel.add(inputs[i][j], s);
			}
		}
	}
	
	//get input for ceremony
	public static void getSelection() {
		String[][] names = new String[numPairs][2];
		for(int i =0; i<numPairs; i++) {
		    names[i][0]= (String) inputs[i][0].getText();
		    names[i][1]=(String) inputs[i][1].getText();
		}
		for(int i =0; i<numPairs; i++) {
			System.out.println("p1" + names[i][0] + " p2" + names[i][1]);
		}
		System.out.println(names.toString());
		r = runSeason(names, isQueer);
		
	}
	
	public static Match getTB() {
		return algorithm.getTruthBooth();
	}
	
	public static Person getPerson(String name) {
		Person p = null;
		for(int i=0; i<people.size(); i++) {
			if(people.get(i).getName().equals(name)) {
				p = people.get(i);
			}
		}
		return p;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
