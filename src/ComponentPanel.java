import Utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class ComponentPanel extends JPanel {
    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    private Component component;
    private JPanel content = new JPanel();
    private final String[] TYPES = {
            "firewall",
            "DBserver",
            "webserver",
    }; //@todo dynamic
    public JLabel close = new JLabel();
    public ComponentPanel(Component component) {

        setComponent(component);

        content.setBorder(new LineBorder(Color.lightGray, 1, true));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Component Type thumbnail
        content.add(new JLabel(ImageUtils.getImageIcon("icons/ComponentTypes/" + component.getComponentTypesId() + ".png", 150, 150))); //@todo dynamic
        content.add(new JLabel(component.getName() + " ("+ TYPES[component.getComponentTypesId() - 1] + ")"));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.add(content);
    }

    public ComponentPanel(Component component, boolean closeable, Object parentPanel) {

        setComponent(component);

        content.setBorder(new LineBorder(Color.lightGray, 1, true));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Component Type thumbnail
        if(closeable){
            close = new JLabel("X", SwingConstants.RIGHT);
            close.setBackground(Color.red);
            close.setHorizontalAlignment(SwingConstants.RIGHT);
            close.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.red));

            close.addMouseListener((MouseListener) parentPanel);


            JPanel closePanel = new JPanel();
            closePanel.setLayout(new BoxLayout(closePanel, BoxLayout.Y_AXIS));
            closePanel.setBackground(Color.red);
            closePanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);

//            redirectPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.red));
            closePanel.add(close, BorderLayout.EAST);

            content.add(closePanel, BorderLayout.EAST);
        }

        content.add(new JLabel(ImageUtils.getImageIcon("icons/ComponentTypes/" + component.getComponentTypesId() + ".png", 150, 150)));
        content.add(new JLabel(component.getName() + " ("+ TYPES[component.getComponentTypesId() - 1] + ")"));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.add(content);
    }

    @Override
    public void setBackground(Color bg) {
        if(content != null){
        content.setBackground(bg);
        }
    }

    @Override
    public void setBorder(Border border) {
        if(content != null){
            content.setBorder(border);
        }
    }


}
