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

import javax.swing.*;
import java.awt.*;

/**
 * @author Max Balushkin
 */
public class Game implements Initializable {

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

        container.setVisible(true);
    }

    private long time() {
        return System.currentTimeMillis();
    }

    public void run() {
        final int tps = 60;
        final int skip = 1000 / tps;
        final float skip_f = 1000f / tps;
        final int maxSkip = 5;

        long nextTick = time();
        while (currentScreen.isRunning()) {
            int i = 0;
            long delta;
            float delta_f;
            // Updates logic `tps` times per second
            for (long t = time(); t > nextTick && i < maxSkip; t = time()) {
                delta = t - nextTick;
                delta_f = delta / skip_f;
                currentScreen.update(new GameTime(delta, delta_f));

                ++i;
                nextTick += skip;
            }

            delta = time() + skip - nextTick;
            delta_f = delta / skip_f;
            // Draws scene once at game loop
            currentScreen.draw(new GameTime(delta, delta_f));
        }
    }

}
