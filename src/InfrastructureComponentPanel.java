import javax.swing.*;

public class InfrastructureComponentPanel extends ComponentPanel{
    public InfrastructureComponentPanel(InfrastructureComponent component) {
        super(component);
        add(new JLabel("Status: " + (component.isAvailable() ? "Actief" : "Inactief")));
    }
}
