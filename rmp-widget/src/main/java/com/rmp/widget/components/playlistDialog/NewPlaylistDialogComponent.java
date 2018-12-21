package com.rmp.widget.components.playlistDialog;

import com.rmp.widget.controls.button.TransparentButton;
import com.rmp.widget.controls.gradientPanel.GradientJPanel;
import com.rmp.widget.dataWatcher.ReplayDataObserver;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.utilities.LocalizationUtils;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Provides the dialog that worked with responsible on creating new playlist
 */
public class NewPlaylistDialogComponent extends JDialog {

    @Setter
    private static Color backgroundStartColor =  Colors.CHARCOAL;

    @Setter
    private static Color backgroundEndColor =  Colors.IRON;

    private static final Dimension SIZE = new Dimension(300, 120);

    private JButton saveButton;
    private JTextField jTextField;

    @Getter
    private ReplayDataObserver<String> saveSubject;

    /**
     * Initialize new instance of {@link NewPlaylistDialogComponent}
     *
     * @param parent - the parent frame of dialog
     */
    public NewPlaylistDialogComponent(JFrame parent) {
        super(parent, LocalizationUtils.getString("new_playlist"), true);
        this.saveSubject = new ReplayDataObserver<>();

        this.setPreferredSize(SIZE);

        if (parent != null) {
            Dimension parentSize = parent.getSize();
            this.setLocation(
                    (int)(parent.getX() + (parentSize.getWidth() / 2 - SIZE.getWidth() / 2)),
                    (int)(parent.getY() + (parentSize.getHeight() / 2 - SIZE.getHeight() / 2))
            );
        }

        this.getContentPane().add(this.createContentPanel());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(this.getDialogWidowListener());
    }

    public void start() {
        this.pack();
        this.setVisible(true);
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new GradientJPanel(backgroundStartColor, backgroundEndColor);
        contentPanel.setPreferredSize(SIZE);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.jTextField = this.createTextField();
        JPanel buttonPanel = this.createButtonsPanel();
        this.saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (jTextField.getText().isEmpty()) {
                    return;
                }
                saveSubject.emit(jTextField.getText().trim());
                dispose();
            }
        });

        contentPanel.add(this.jTextField, BorderLayout.PAGE_START);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        return contentPanel;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension((int)SIZE.getWidth() - 10, 30));
        textField.setBackground(Colors.CHARCOAL);
        textField.setForeground(Color.gray.brighter());
        textField.setCaretColor(Color.gray.brighter());
        textField.setBorder(BorderFactory.createLineBorder(Colors.IRON, 1));

        return textField;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension((int)SIZE.getWidth(), (int)SIZE.getHeight() - 50));
        buttonsPanel.setBackground(Colors.TRANSPARENT);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonsPanel.setLayout(new FlowLayout());

        this.saveButton = new TransparentButton(
                LocalizationUtils.getString("save"),
                Colors.LIGHT_GRAY,
                Colors.PRIMARY
        );

        JButton cancel = new TransparentButton(
                LocalizationUtils.getString("cancel"),
                Colors.LIGHT_GRAY, Colors.PRIMARY_CANCEL
        );
        cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        buttonsPanel.add(this.saveButton);
        buttonsPanel.add(cancel);

        return buttonsPanel;
    }

    private WindowListener getDialogWidowListener() {
        return new WindowListener() {
            public void windowActivated(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
                jTextField.setText("");
            }

            public void windowClosing(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowOpened(WindowEvent e) {
            }
        };
    }
}

