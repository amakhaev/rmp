package com.rmp.mediator.listeners;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

class GlobalKeyboardListener implements NativeKeyListener {

    private Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    GlobalKeyboardListener() {
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
    }
}
