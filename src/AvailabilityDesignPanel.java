import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AvailabilityDesignPanel extends JPanel implements ActionListener {
    private JButton laatOntwerpZien;
    private int lijnHoogte = 50;
    private JLabel beschikbaarheidsPercentage;
    private JTextField beschikbaarheidsPercentageNummer;
    private InfrastructureDesign infrastructureDesign;
    private MainFrame mainFrame;


    public AvailabilityDesignPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // subpanel voor de balk onderaan
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // label voor het tekstje
        beschikbaarheidsPercentage = new JLabel("Beschikbaarheid:");

        // Maak het onderste vak
        Dimension preferredSize = subPanel.getPreferredSize();
        preferredSize.height = lijnHoogte;
        subPanel.setPreferredSize(preferredSize);
        Border border = BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK);
        subPanel.setBorder(border);

        // Textfield voor het invoeren van het getal
        beschikbaarheidsPercentageNummer = new JTextField(10);

        // Knop voor ontwerp opslaan en laten zien
        laatOntwerpZien = new JButton("Laat ontwerp zien");
        laatOntwerpZien.addActionListener(this);


        subPanel.add(beschikbaarheidsPercentage);
        subPanel.add(beschikbaarheidsPercentageNummer);
        subPanel.add(laatOntwerpZien);


        ArrayList<Component> components = ComponentRepository.findAll();
        ComponentViewPanel componentViewPanel = new ComponentViewPanel(null, components);

        add(componentViewPanel);
        add(subPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        exampleDesign.add(new Databaseserver("DB-01"));
//        exampleDesign.add(new Firewall("FW-01"));
//        exampleDesign.add(new Firewall("FW-02"));
//        exampleDesign.add(new Webserver("WS-01"));
//        exampleDesign.add(new Webserver("WS-01"));
//        exampleDesign.add(new Webserver("WS-02"));

        if (e.getSource() == laatOntwerpZien) {
            try {
                // ophalen en een double getal van maken
                double nummer = Double.parseDouble(beschikbaarheidsPercentageNummer.getText());
                BigDecimal decimal = BigDecimal.valueOf(nummer);
                int scale = decimal.scale();

                if (nummer >= 0 && nummer <= 100 && scale <= 4) {
                    // maar 2 decimalen want anders rond het af

                    BacktrackAlgo algo = new BacktrackAlgo();
                    BacktrackAlgo.availability = nummer / 100; //Beschikbaarheid invullen.
                    ArrayList<String> result = BacktrackAlgo.getServerConfiguration(); //"result" bevat nu de namen van de servers.

                    ArrayList<Component> bestConfiguration = new ArrayList<>();
                    for(String componentName : result){
                        // Get component using component name, disgusting
                        Component component = new Component(componentName);
                        bestConfiguration.add(component);
                    }

                    // Convert Components into infrastructureDesign object
                    InfrastructureDesign infrastructureDesign = new InfrastructureDesign(false);
                    infrastructureDesign.add(bestConfiguration);

                    this.mainFrame.setActiveBody(new OverviewPanel(infrastructureDesign, this, this.mainFrame));

                } else { //verkeerd getal
                    JOptionPane.showMessageDialog(this, "Voer een getal in tussen 0 en 100. \n " +
                            "Het getal mag niet meer dan vier decimalen achter de komma hebben.");
                    beschikbaarheidsPercentageNummer.setText("");
                }

            } catch (NumberFormatException ex) { //letters ingevoerd
                JOptionPane.showMessageDialog(this, "Ongeldige invoer. Voer een geldig getal in.");
                beschikbaarheidsPercentageNummer.setText("");
            }
        }
    }
}