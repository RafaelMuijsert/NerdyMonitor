import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class AvailabilityDesignPanel extends JPanel implements ActionListener {
    private InfrastructureDesign infrastructureDesign;
    private JFrame beschikbaarheid;
    private int lijnHoogte = 50;
    private JButton laatOntwerpZien;
    private JLabel beschikbaarheidsPercentage;

    public void meldingGeenGetal() {
        JOptionPane.showMessageDialog(this, "Voer een getal tussen 0 en 100 in!!");
    }
    public AvailabilityDesignPanel() {
        setLayout(new BorderLayout());

        // subpanel voor de balk onderaan
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Use FlowLayout

        // label voor het tekstje
        beschikbaarheidsPercentage = new JLabel("Beschikbaarheid:");

        // Creëer een aangepast SpinnerNumberModel met een Double-type
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0.0, null, null, 0.01);

        // Pas de formatter toe op de editor van de JSpinner
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) new JSpinner(spinnerModel).getEditor();

        // Aangepaste DecimalFormat met 5 decimalen
        DecimalFormat decimalFormat = new DecimalFormat("0.#####");
        editor.getTextField().setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(decimalFormat)));

        JSpinner spinner = new JSpinner(spinnerModel);

        // Pas de grootte van de spinner aan
        Dimension spinnerSize = new Dimension(150, 30); // Aangepaste breedte en hoogte
        spinner.setPreferredSize(spinnerSize);

        // Maak het onderste vak
        Dimension preferredSize = subPanel.getPreferredSize();
        preferredSize.height = lijnHoogte;
        subPanel.setPreferredSize(preferredSize);
        Border border = BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK);
        subPanel.setBorder(border);

        // Knop voor ontwerp opslaan en laten zien
        laatOntwerpZien = new JButton("Laat ontwerp zien");
        laatOntwerpZien.setBounds(100, 100, 50, 50);
        laatOntwerpZien.addActionListener(this);

        subPanel.add(beschikbaarheidsPercentage);
        subPanel.add(spinner);
        subPanel.add(laatOntwerpZien);
        add(subPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == laatOntwerpZien) {
            try {
                int spinnerIndex = 1; // Index van het JSpinner-object in de container (aanpassen indien nodig)
                SpinnerNumberModel spinnerModel = (SpinnerNumberModel) ((JSpinner) ((JButton) e.getSource()).getParent().getComponent(spinnerIndex)).getModel();
                double spinnerValue = spinnerModel.getNumber().doubleValue();
                if (spinnerValue >= 0 && spinnerValue < 100) {
                    System.out.println("Ingevoerde waarde: " + spinnerValue);
                    // Voer actie uit met de waarde...
                } else if (spinnerValue < 0.0) {
                    System.out.println("Ongeldige waarde! Waarde onder nul!!");
                     meldingGeenGetal();
                    spinnerModel.setValue(0);
                } else if (spinnerValue >= 100) {
                    System.out.println("Ongeldige waarde! Waarde boven 100!!");
                     meldingGeenGetal();
                    spinnerModel.setValue(99.99);
                } else {
                    System.out.println("Ongeldige waarde! Voer een getal tussen de 0 en 100 in!!");
                    meldingGeenGetal();
                    spinnerModel.setValue(0.0);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Werkt niet");
            }
        }
    }
}