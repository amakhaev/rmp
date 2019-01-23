package com.rmp.mediator.listeners;

import com.rmp.mediator.mediaPlayer.PlayerMediator;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides the global keyboard listener
 */
public final class GlobalKeyboardListener implements NativeKeyListener {

    private static final Map<AvailableKeys, Integer> KEY_CODES;

    static {
        KEY_CODES = new HashMap<>();
        KEY_CODES.put(AvailableKeys.PREV, 65302);
        KEY_CODES.put(AvailableKeys.NEXT, 65303);
        KEY_CODES.put(AvailableKeys.STOP, 65301);
        KEY_CODES.put(AvailableKeys.PLAY, 65300);
    }

    private final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    private final PlayerMediator playerMediator;

    /**
     * Initialize new instance of {@link GlobalKeyboardListener}
     */
    public GlobalKeyboardListener(PlayerMediator playerMediator) {
        this.playerMediator = playerMediator;
        logger.setLevel(Level.OFF);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getRawCode() == KEY_CODES.get(AvailableKeys.PREV)) {
            this.playerMediator.playPrev();
        } else if (nativeKeyEvent.getRawCode() == KEY_CODES.get(AvailableKeys.NEXT)) {
            this.playerMediator.playNext();
        } else if (nativeKeyEvent.getRawCode() == KEY_CODES.get(AvailableKeys.PLAY)) {
            this.playerMediator.play();
        } else if (nativeKeyEvent.getRawCode() == KEY_CODES.get(AvailableKeys.STOP)) {
            this.playerMediator.stopPlaying();
        }

    }

    private enum AvailableKeys {
        PREV,
        NEXT,
        STOP,
        PLAY
    }
}
