import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class EmptyDesignPanel extends JPanel implements ActionListener, MouseListener {

    private JPanel configurationPanel;
    private ComponentViewPanel componentViewPanel;
    public EmptyDesignPanel() {
        setLayout(new GridLayout(1, 2));

        ArrayList<Component> components = ComponentRepository.findAll();
        componentViewPanel = new ComponentViewPanel(this, components);
        configurationPanel = new JPanel();
        configurationPanel.setBackground(Color.red);
        add(componentViewPanel);
        add(configurationPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() instanceof ComponentPanel){
            if(configurationPanel.contains(e.getPoint())){
                Component component = ((ComponentPanel) e.getSource()).getComponent();
                boolean isInsideTargetPanel = SwingUtilities.isDescendingFrom((ComponentPanel) e.getSource(), configurationPanel);
                if(isInsideTargetPanel){
                    System.out.println("boeie");
                }


                this.configurationPanel.add(new ComponentPanel(component));
                configurationPanel.revalidate();
                configurationPanel.repaint();



            }
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}