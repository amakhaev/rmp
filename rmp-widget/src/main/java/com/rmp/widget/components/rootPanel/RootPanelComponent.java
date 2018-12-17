package com.rmp.widget.components.rootPanel;

import com.rmp.widget.components.buttonsPanel.ButtonPanelComponent;
import com.rmp.widget.components.buttonsPanel.ButtonPanelBuilder;
import com.rmp.widget.controls.gradientPanel.GradientJPanel;
import com.rmp.widget.skins.RMPSkin;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the root panel of widget
 */
public class RootPanelComponent {

    @Getter
    private JPanel rootPanel;

    private RMPSkin skin;

    /**
     * Initialize new instance of {@link RootPanelComponent}
     *
     * @param skin - the skin of root panel
     */
    RootPanelComponent(RMPSkin skin) {
        this.skin = skin;
        this.rootPanel = new GradientJPanel(this.skin.getBackgroundStartColor(), this.skin.getBackgroundEndColor());
        this.initialize();
    }

    private void initialize() {
        this.rootPanel.setLayout(new BorderLayout());

        ButtonPanelComponent buttonPanelComponent = new ButtonPanelBuilder()
                .setSkin(this.skin.getButtonPanelSkin())
                .build();

        this.rootPanel.add(buttonPanelComponent.getButtonPanel(), BorderLayout.PAGE_END);
    }

}
