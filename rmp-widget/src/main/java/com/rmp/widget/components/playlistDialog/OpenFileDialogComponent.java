package com.rmp.widget.components.playlistDialog;

import com.rmp.widget.utilities.LocalizationUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Provides the open file dialog.
 */
public class OpenFileDialogComponent {

    private JFileChooser jFileChooser;
    private JFrame parent;

    /**
     * Initialize new instance of {@link OpenFileDialogComponent}
     */
    public OpenFileDialogComponent(JFrame parent) {
        this.parent = parent;
        this.jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        this.jFileChooser.setDialogTitle(LocalizationUtils.getString("open_media"));
        this.jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.jFileChooser.setMultiSelectionEnabled(true);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3 & wav Images", "wav", "mp3");
        this.jFileChooser.addChoosableFileFilter(filter);
    }

    /**
     * Shows the selected files.
     */
    public List<File> startForResult() {
        int returnValue = this.jFileChooser.showOpenDialog(this.parent);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return Arrays.asList(this.jFileChooser.getSelectedFiles());
        }

        return Collections.emptyList();
    }

}
