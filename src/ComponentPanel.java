import Utils.ImageUtils;

import javax.swing.*;


public class ComponentPanel extends JPanel {
    public ComponentPanel(Component component) {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        // Component Type thumbnail
        content.add(new JLabel(ImageUtils.getImageIcon("icons/ComponentTypes/" + component.getComponentTypesId() + ".png", 150, 150))); //@todo dynamic
        content.add(new JLabel(component.getName()));
        super.add(content);
    }
}
