
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class InfrastructureComponentViewPanel extends JPanel{
    private DashboardPanel dashboardPanel;

    public InfrastructureComponentViewPanel(
            Object dashboardPanel,
            ArrayList<InfrastructureComponent> components
    ) {

        // Could not find any monitored components
        if(components.size() == 0){
            return;
        }

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0, 4, 0, 0));

        for (InfrastructureComponent infrastructureComponent: components) {
            InfrastructureComponentPanel componentPanel =  new InfrastructureComponentPanel(infrastructureComponent);

            if(infrastructureComponent.getUptime() == null){
                componentPanel.setBorder(new LineBorder(Color.RED, 2, true));
            }
            else {
                componentPanel.setBorder(new LineBorder(Color.GREEN, 2, true));
                // On mouse click event, update Component Status panel
                componentPanel.addMouseListener((MouseListener) dashboardPanel );
            }

            content.add(componentPanel);
        }

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        add(scrollPane);

        setLayout(new GridLayout(1, 2));
    }



}

