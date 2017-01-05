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

package com.github.noxchimaera.massacre.game;

import com.github.noxchimaera.massacre.engine.GameObject;
import com.github.noxchimaera.massacre.engine.GameScreen;
import com.github.noxchimaera.massacre.engine.GameTime;
import com.github.noxchimaera.massacre.engine.scene.Scene;
import com.github.noxchimaera.massacre.engine.controls.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Balushkin
 */
public class StartScreen extends GameScreen {

    private List<GameObject> gos = new ArrayList<>();

    @Override public void draw(GameTime gameTime) {
        super.draw(gameTime);
    }

    @Override public void update(GameTime gameTime) {
        for (GameObject go : gos) {
        float v = go.getSpeed() * gameTime.getDt();
            float x = go.getX();
            float y = go.getY();

            if (Keyboard.shared().isPressed(KeyEvent.VK_UP)) {
                y -= v;
            } else if (Keyboard.shared().isPressed(KeyEvent.VK_DOWN)) {
                y += v;
            }
            if (Keyboard.shared().isPressed(KeyEvent.VK_LEFT)) {
                x -= v;
            } else if (Keyboard.shared().isPressed(KeyEvent.VK_RIGHT)) {
                x += v;
            }
            go.setX(x);
            go.setY(y);
        }
    }

    @Override public void initialize() {
        gos.add(new GameObject(100, 100, 50, 50, Color.GREEN, 50));
        gos.add(new GameObject(100, 150, 50, 50, Color.RED, 0));

        gos.add(new GameObject(0, 10, 32, 50, Color.GREEN, 20));
        gos.add(new GameObject(50, 170, 50, 30, Color.GREEN, 100));
        gos.add(new GameObject(100, 300, 50, 50, Color.GREEN, 70));

        for (GameObject gameObject : gos) {
            getScene().addObject(gameObject);
        }

        getScene().addString(new Scene.SceneString("Some text", 100, 100, Color.RED));
    }
}
