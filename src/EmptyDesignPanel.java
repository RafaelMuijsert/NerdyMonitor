import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EmptyDesignPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private ConfigurationViewPanel configurationViewPanel;
    private final ComponentViewPanel componentViewPanel;
    private ArrayList<Component> configuration; // Gebruik dit voor de overazicht
    private JPanel dragAndDropPanel;
    private JPanel redirectPanel;
    private JButton redirectButton;
    public EmptyDesignPanel() {

        setLayout(new GridLayout(1, 2));

        ArrayList<Component> components = ComponentRepository.findAll();
        configuration = new ArrayList<>(); // @todo geef eventueel arraylist mee, zodat als je van overview terug gaat je design niet weg is
        dragAndDropPanel = new JPanel();
        componentViewPanel = new ComponentViewPanel(this, components);
        configurationViewPanel = new ConfigurationViewPanel(this, configuration);


        // Container for Button for alignment to the right
        redirectPanel = new JPanel();
        redirectPanel.setLayout(new BoxLayout(redirectPanel, BoxLayout.Y_AXIS));
        redirectPanel.setBackground(Color.red);
        redirectPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);

        // Redirect button
        redirectButton = new JButton("Bereken kosten");
        redirectButton.setHorizontalAlignment(JButton.RIGHT);
        redirectButton.addActionListener(this);

        redirectPanel.add(redirectButton, BorderLayout.EAST);

        dragAndDropPanel.setLayout(new BoxLayout(dragAndDropPanel, BoxLayout.Y_AXIS));
        dragAndDropPanel.add(configurationViewPanel);
        dragAndDropPanel.add(redirectPanel);



        add(componentViewPanel);
        add(dragAndDropPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == redirectButton){
            System.out.println(" redirect");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() instanceof ComponentPanel) {
            System.out.println(((ComponentPanel) e.getSource()).getParent());
            // "Drag and drop" effect
            Component component = ((ComponentPanel) e.getSource()).getComponent();
            setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    ImageUtils.getImageIcon("icons/ComponentTypes/" + component.getComponentTypesId() + ".png", 150, 150).getImage(),
                    new Point(0,0),"custom cursor")
            );
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        if(e.getSource() instanceof ComponentPanel) {
            // Mouse position is outta bounds
            if(dragAndDropPanel.getMousePosition() == null ) {
                return;
            }

            // Component has been dragged outside of its parent container.
            if(e.getPoint().x > dragAndDropPanel.getMousePosition().x){
                Component component = ((ComponentPanel) e.getSource()).getComponent();

                configuration.add(component);
                this.refresh();
            }
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {
//        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if(e.getSource() instanceof ComponentPanel) {
                ((ComponentPanel) e.getSource()).setLocation(e.getPoint());
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("TEST");
    }

    public void refresh(){
        this.configurationViewPanel = new ConfigurationViewPanel(this, configuration);


        // Refresh
        dragAndDropPanel.removeAll();
        dragAndDropPanel.add(configurationViewPanel);
        dragAndDropPanel.add(redirectPanel);

        dragAndDropPanel.revalidate();
        dragAndDropPanel.repaint();
    }
}