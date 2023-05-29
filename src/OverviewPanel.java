import Utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OverviewPanel extends JPanel implements ActionListener {
	private InfrastructureDesign infrastructureDesign;
	private final Object goBackPanel;
	private final MainFrame parentPanel;
	private static final Font TITLE_FONT = new Font("Montserrat", Font.PLAIN, 32);
	private JButton jbTerug;
	private JButton jbOpslaan ;

	public OverviewPanel(InfrastructureDesign infrastructureDesign, JPanel goBackPanel, MainFrame parentPanel) {
		this.infrastructureDesign = infrastructureDesign;
		this.goBackPanel = goBackPanel;
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());

    public OverviewPanel(InfrastructureDesign infrastructureDesign) {
        this.infrastructureDesign = infrastructureDesign;
        setLayout(new BorderLayout());

        JPanel jpCost = new JPanel();
        JPanel jpOverview = new JPanel();

        jpCost.setLayout(new BorderLayout());
        JLabel jlCost = new JLabel("Kosten ontwerp", SwingConstants.CENTER);
        jlCost.setFont(OverviewPanel.TITLE_FONT);
        jpCost.add(jlCost, BorderLayout.NORTH);


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

        for (Webserver webserver : this.infrastructureDesign.getWebservers()) {
            jpCostOverview.add(new ComponentOverview(webserver, 1));
        }

        JScrollPane scrollPane = new JScrollPane(jpCostOverview);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        jpCost.add(scrollPane, BorderLayout.CENTER);


        BorderLayout jpOverviewLayout = new BorderLayout();
        jpOverviewLayout.setHgap(10);
        jpOverviewLayout.setVgap(10);
        jpOverview.setLayout(jpOverviewLayout);

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

        terug = new JButton("Terug");
        terug.addActionListener(this);
        jpOverviewControls.add(terug);

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

        jpOverviewInfo.setLayout(new GridLayout(2, 0));
        JLabel jlOverview = new JLabel("Overzicht", SwingConstants.CENTER);
        jlOverview.setFont(OverviewPanel.TITLE_FONT);
        jpOverviewInfo.add(jlOverview);

		add(jpCost, BorderLayout.CENTER);
		add(jpOverview, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbTerug){
			this.parentPanel.setActiveBody((JPanel) this.goBackPanel);
		}
		else if(e.getSource() == jbOpslaan) {

			UIManager.put("FileChooser.openButtonText", "Opslaan");
			UIManager.put("FileChooser.cancelButtonText", "Annuleren");
			UIManager.put("FileChooser.readOnly", Boolean.TRUE);

			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setDialogTitle("Opslaan IT-infrastructuur ontwerp");

			jFileChooser.showSaveDialog(this);
			//
			if(jFileChooser.getSelectedFile() == null ){
				return;
			}

			try {
				this.infrastructureDesign.saveDesign(StringUtils.removeExtention(jFileChooser.getSelectedFile().getPath()));
				JOptionPane.showMessageDialog(this, "Bestand is opgeslagen");

				// Redirect to new design
				this.parentPanel.setActiveBody(new NewDesignPanel(this.parentPanel));
			}

			catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
