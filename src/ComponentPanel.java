import Utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;


public class ComponentPanel extends JPanel {
    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    private Component component;
    private JPanel content = new JPanel();

    public ComponentPanel(Component component) {

        setComponent(component);

        content.setBorder(new LineBorder(Color.lightGray, 1, true));
        content.setBackground(Color.lightGray);
        JPanel padding = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // Component Type thumbnail
        content.add(new JLabel(ImageUtils.getImageIcon("icons/ComponentTypes/" + component.getComponentTypesId() + ".png", 150, 150))); //@todo dynamic
        content.add(new JLabel(component.getName()));
        content.setSize(150, 150);
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
