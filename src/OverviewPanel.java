import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OverviewPanel extends JPanel implements ActionListener {
	private InfrastructureDesign infrastructureDesign;
	private final Object goBackPanel;
	private final MainFrame parentPanel;
	private ArrayList<InfrastructureComponent> components;
	private static final Font TITLE_FONT = new Font("Montserrat", Font.PLAIN, 32);
	private JButton jbTerug;
	private JButton jbOpslaan ;

	public OverviewPanel(InfrastructureDesign infrastructureDesign, JPanel goBackPanel, MainFrame parentPanel) {
		this.infrastructureDesign = infrastructureDesign;
		this.goBackPanel = goBackPanel;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());

		JPanel jpCost = new JPanel();
		JPanel jpOverview = new JPanel();

		jpCost.setLayout(new BorderLayout());
		JLabel jlCost = new JLabel("Kosten ontwerp", SwingConstants.CENTER);
		jlCost.setFont(OverviewPanel.TITLE_FONT);
		jpCost.add(jlCost, BorderLayout.NORTH);

		JPanel jpCostOverview = new JPanel();
		GridLayout glCostOverview = new GridLayout(0, 1);
		glCostOverview.setVgap(4);
		jpCostOverview.setLayout(glCostOverview);


		ArrayList<Integer> componentIds = new ArrayList<>();
		ArrayList<Component> components = this.infrastructureDesign.getComponents();

		for(int i = 0; i < components.size(); i++){
			// Only add to known components id's if not added yet or the list is still empty
			if(componentIds.size() == 0 || !componentIds.contains(components.get(i).getId())){
				componentIds.add(components.get(i).getId());
			} else{
				continue;
			}

			int quantity = 0;
			// Loop through the remaining components in the list and increment quantity if it is the same component
			for(int x = i; x < components.size(); x++) {
				Component current = components.get(i);
				Component loop = components.get(x);

				if(current.getId() == loop.getId()){
					quantity++;
				}
			}

			jpCostOverview.add(new ComponentOverview(components.get(i), quantity));
		}


//		for(Firewall firewall: this.infrastructureDesign.getFirewalls()) {
//			jpCostOverview.add(new ComponentOverview(firewall, 1));
//		}
//
//		for(Databaseserver database: this.infrastructureDesign.getDatabases()) {
//			jpCostOverview.add(new ComponentOverview(database, 1));
//		}
//
//		for(Webserver webserver: this.infrastructureDesign.getWebservers()) {
//			jpCostOverview.add(new ComponentOverview(webserver, 1));
//		}

		JScrollPane scrollPane = new JScrollPane(jpCostOverview);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		jpCost.add(scrollPane, BorderLayout.CENTER);


		BorderLayout jpOverviewLayout = new BorderLayout();
		jpOverviewLayout.setHgap(10);
		jpOverviewLayout.setVgap(10);
		jpOverview.setLayout(jpOverviewLayout);

		JPanel jpOverviewControls = new JPanel();
		JPanel jpOverviewInfo = new JPanel();

		jpOverviewControls.setLayout(new FlowLayout());

		jbTerug = new JButton("Terug");
		jbOpslaan = new JButton("Opslaan");

		jbTerug.addActionListener(this);
		jbOpslaan.addActionListener(this);
		jpOverviewControls.add(jbTerug);
		jpOverviewControls.add(jbOpslaan);

		jpOverviewInfo.setLayout(new GridLayout(2, 0));
		JLabel jlOverview = new JLabel("Overzicht", SwingConstants.CENTER);
		jlOverview.setFont(OverviewPanel.TITLE_FONT);
		jpOverviewInfo.add(jlOverview);

		jpOverview.add(jpOverviewInfo, BorderLayout.NORTH);
		//

		JPanel jpOverviewSummary = new JPanel();
		GridLayout grOverviewSummary = new GridLayout(0, 2);
		grOverviewSummary.setVgap(4);
		jpOverviewSummary.setLayout(grOverviewSummary);

		// @todo dynamishc
		jpOverviewSummary.add(new JLabel("Totaalprijs"));
		jpOverviewSummary.add(new JLabel("€ " +String.valueOf(infrastructureDesign.getTotalCost())));
		jpOverviewSummary.add(new JLabel("Totale beschikbaarheid"));
		jpOverviewSummary.add(new JLabel(String.valueOf(infrastructureDesign.getTotalAvailability()) + "%"));
		jpOverviewSummary.add(new JLabel("Totaal componenten"));
		jpOverviewSummary.add(new JLabel(String.valueOf(infrastructureDesign.getComponents().size())));
		//
		jpOverview.add(jpOverviewSummary, BorderLayout.CENTER);
		jpOverview.add(jpOverviewControls, BorderLayout.SOUTH);

		jpOverview.setPreferredSize(new Dimension(500, 0));

		add(jpCost, BorderLayout.CENTER);
		add(jpOverview, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbTerug){
			this.parentPanel.setActiveBody((JPanel) this.goBackPanel);
		}
		else if(e.getSource() == jbOpslaan){

			UIManager.put("FileChooser.openButtonText","Opslaan");
			UIManager.put("FileChooser.cancelButtonText","Annuleren");

			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setDialogTitle("Opslaan IT-infrastructuur ontwerp");

			jFileChooser.showSaveDialog(this);

			if(jFileChooser.getSelectedFile() == null ){
				return;
			}

			Gson gson = new Gson();
			try {
				Map<String, Object> map = new HashMap<>();
				map.put("title", "Thinking in Java");
				map.put("date_created", java.time.LocalDateTime.now().toString());
				map.put("year", 1998);
				map.put("authors", new String[]{"Bruce Eckel"});
				gson.toJson(map);

				Gson boeie = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(map);
				System.out.println(json);
				boeie.toJson(map,  new FileWriter("/home/thijmen/Desktop/test.json") );
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}

			System.out.println(jFileChooser.getSelectedFile());
//			JOptionPane.showMessageDialog(this, jFileChooser.getSelectedFile());

		}
	}
}
