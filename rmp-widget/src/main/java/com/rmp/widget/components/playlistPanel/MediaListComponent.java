package com.rmp.widget.components.playlistPanel;

import com.rmp.widget.controls.scroll.ModernScrollPanel;
import com.rmp.widget.readModels.UIMediaFileModel;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.utilities.ImageUtility;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Provides the component that worked with media files
 */
class MediaListComponent {

    private final ImageIcon playIcon;

    private JList<UIMediaFileModel> mediaList;
    private DefaultListModel<UIMediaFileModel> defaultListModel;
    private Integer selectedItemId;

    @Setter
    private Function<List<Integer>, Void> deleteMediaItemHandler;

    /**
     * Initialize new instance of {@link MediaListComponent}
     */
    MediaListComponent(URL playIcon) {
        this.selectedItemId = -1;
        this.playIcon = new ImageIcon(ImageUtility.getScaledImage(new ImageIcon(playIcon).getImage(), 15, 15));
    }

    /**
     * Creates the list of media files
     *
     * @return the {@link JScrollPane} instance
     */
    JScrollPane create() {
        this.defaultListModel = new DefaultListModel<>();
        this.mediaList = new JList<>(this.defaultListModel);
        this.mediaList.setLayoutOrientation(JList.VERTICAL);
        this.mediaList.setBackground(Color.BLACK);
        this.mediaList.setBorder(BorderFactory.createEmptyBorder());
        this.mediaList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list,
                                                          Object value,
                                                          int index,
                                                          boolean isSelected,
                                                          boolean cellHasFocus) {
                JLabel lbl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setForeground(Colors.PRIMARY_GRAY);
                lbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                UIMediaFileModel mediaFile = (UIMediaFileModel) value;
                lbl.setText((index + 1) + ". " + mediaFile.getDisplayName());
                lbl.setToolTipText(mediaFile.getDisplayName());

                if (selectedItemId != null && mediaFile.getId() == selectedItemId) {
                    lbl.setIcon(playIcon);
                }

                if (isSelected) {
                    this.setBackground(Colors.IRON);
                } else if (selectedItemId != null && mediaFile.getId() == selectedItemId) {
                    this.setBackground(new Color(6,45,0));
                }
                else {
                    this.setBackground(Color.BLACK);
                }

                return lbl;
            }
        });

        this.mediaList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE &&
                        deleteMediaItemHandler != null &&
                        mediaList.getSelectedIndices() != null &&
                        mediaList.getSelectedIndices().length > 0) {
                    deleteMediaItemHandler.apply(
                            Arrays.stream(mediaList.getSelectedIndices())
                                    .map(index -> defaultListModel.get(index).getId())
                                    .boxed()
                                    .collect(Collectors.toList())
                    );
                }
            }
        });

        JScrollPane scrollPane = new ModernScrollPanel(this.mediaList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }

    /**
     * Adds the media file to list
     *
     * @param mediaFile - the media file to add
     */
    void addFile(UIMediaFileModel mediaFile) {
        if (this.defaultListModel != null) {
            this.defaultListModel.addElement(mediaFile);
        }
    }

    /**
     * Adds the media files to list
     *
     * @param mediaFiles - the media file to add
     */
    void addFiles(List<UIMediaFileModel> mediaFiles) {
        if (this.defaultListModel != null) {
            this.defaultListModel.clear();
            mediaFiles.forEach(mf -> this.defaultListModel.addElement(mf));
        }
    }

    /**
     * Clears the list model.
     */
    void clear() {
        if (this.defaultListModel != null) {
            this.defaultListModel.clear();
        }
    }

    /**
     * Sets the selected item id in media list component
     *
     * @param selectedItemId - the new value of selected item id
     */
    void setSelectedItemId(Integer selectedItemId) {
        this.selectedItemId = selectedItemId;
        this.mediaList.repaint();
    }

}

