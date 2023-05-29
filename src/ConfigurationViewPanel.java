import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ConfigurationViewPanel extends JPanel implements ActionListener, MouseListener{
    private final ArrayList<Component> components;
    private final EmptyDesignPanel parent;
    private JPanel content;

    public ConfigurationViewPanel (
            Object parentPanel,
            ArrayList<Component> components
    )
    {
        this.parent = (EmptyDesignPanel)parentPanel;
        this.components = components;
        content = new JPanel();
        content.setLayout(new GridLayout(0, 1, 0, 0));

        if(components == null || components.size() <= 0){
            content.add(new JLabel("Voeg een component toe om te beginnen!"));
        }
        else{
            content.setLayout(new GridLayout(0, 4, 0, 0));

            for (Component component: components) {
                ComponentPanel componentPanel =  new ComponentPanel(component, true, this);

                componentPanel.addMouseListener(this);
                content.add(componentPanel);
            }

        }

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);

        add(scrollPane);

        setLayout(new GridLayout(1, 2));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() instanceof JLabel) {
            // Jlabel traverse out of panels to reach ComponentPanel.
            Component tobeDeleted = ((ComponentPanel)((JLabel) e.getSource()).getParent().getParent().getParent()).getComponent();
            components.remove(tobeDeleted);
            parent.refresh();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
