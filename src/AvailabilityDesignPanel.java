import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class AvailabilityDesignPanel extends JPanel implements ActionListener {
    private JButton laatOntwerpZien;
    private int lijnHoogte = 50;
    private JLabel beschikbaarheidsPercentage;
    private JTextField beschikbaarheidsPercentageNummer;

    public AvailabilityDesignPanel() {
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
        add(subPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == laatOntwerpZien) {
            try {
                // ophalen en een double getal van maken
                double nummer = Double.parseDouble(beschikbaarheidsPercentageNummer.getText());
                BigDecimal decimal = BigDecimal.valueOf(nummer);
                int scale = decimal.scale();
                if (nummer >= 0 && nummer <= 100 && scale <= 2) {
                    // maar 2 decimalen want anders rond het af
                    String ingevoerdPercentage = String.format("%.2f", nummer);
                    System.out.println("Ingevoerd getal: " + ingevoerdPercentage);
                    // verder code naar volgend scherm

                } else { //verkeerd getal
                    JOptionPane.showMessageDialog(this, "Voer een getal in tussen 0 en 100. \n " +
                            "Het getal mag niet meer dan decimalen achter de komma hebben.");
                }
            } catch (NumberFormatException ex) { //letters ingevoerd
                JOptionPane.showMessageDialog(this, "Ongeldige invoer. Voer een geldig getal in.");
            }
        }
    }
}