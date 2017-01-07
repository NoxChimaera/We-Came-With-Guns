/*
 * Copyright 2016 Max Balushkin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.noxchimaera.massacre.engine;

import com.github.noxchimaera.massacre.engine.controls.Keyboard;
import com.github.noxchimaera.massacre.engine.controls.Mouse;
import com.github.noxchimaera.massacre.engine.interfaces.InitializableComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Max Balushkin
 */
public class Game implements InitializableComponent {

    private String gameTitle = "The Game";

    private Dimension screenSize;
    private boolean fullScreen;

    private GameScreen currentScreen;

    public Game(GameScreen currentScreen) {
        this(currentScreen, new Dimension(1366, 768), false);
    }

    public Game(GameScreen currentScreen, Dimension screenSize, boolean fullScreen) {
        this.screenSize = screenSize;
        this.fullScreen = fullScreen;

        this.currentScreen = currentScreen;
    }

    @Override public void initialize() {
        JFrame container = new JFrame();
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setSize(screenSize);
        container.setLocationRelativeTo(null);
        container.setResizable(false);
        container.setTitle(gameTitle);

        container.add(currentScreen.getScene());
        currentScreen.initialize();

        container.addKeyListener(Keyboard.shared());

        currentScreen.getScene().addMouseListener(Mouse.shared());
        currentScreen.getScene().addMouseMotionListener(Mouse.shared());
        currentScreen.getScene().addMouseWheelListener(Mouse.shared());

        container.setVisible(true);
    }

    private long nano() {
        return System.nanoTime();
    }

    public void run() {
        // Game loop by Eric from Entropy Interactive
        final double second = 1000000000.0;
        final double delta = 1 / 60.0;
        double nextTime = (double)nano() / second;
        double maxTimeDiff = 0.5;
        int skippedFrames = 1;
        int maxSkippedFrames = 2;
        while (currentScreen.isRunning()) {
            double curTime = (double)nano() / second;
            if ((curTime - nextTime) > maxTimeDiff) {
                nextTime = curTime;
            }
            if (curTime >= nextTime) {
                nextTime += delta;
                currentScreen.update(new GameTime(delta));
                if (curTime < nextTime || skippedFrames > maxSkippedFrames) {
                    // Time to draw the scene
                    currentScreen.draw();
                    skippedFrames = 1;
                } else {
                    ++skippedFrames;
                }
            } else {
                // Skip rest
                int sleepTime = (int)(1000 * (nextTime - curTime));
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

}
