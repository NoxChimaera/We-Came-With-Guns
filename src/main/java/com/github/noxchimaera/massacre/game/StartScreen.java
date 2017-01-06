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

import com.github.noxchimaera.massacre.engine.GameScreen;
import com.github.noxchimaera.massacre.engine.GameTime;
import com.github.noxchimaera.massacre.engine.controls.Keyboard;
import com.github.noxchimaera.massacre.engine.scene.GameObject;
import com.github.noxchimaera.massacre.engine.scene.Scene;
import com.github.noxchimaera.massacre.engine.views.RectangleView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

/**
 * @author Max Balushkin
 */
public class StartScreen extends GameScreen {

    private List<GameObject> gos = new ArrayList<>();

    @Override public void draw() {
        super.draw();
    }

    @Override public void update(GameTime gameTime) {
        GameObject go = getScene().getObjectByTag("player");

        double v = 150 * gameTime.getDt();
        float x = go.getLocation().x();
        float y = go.getLocation().y();

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
        go.setLocation(x, y);
    }

    @Override public void initialize() {
        GameObject go = new GameObject(getScene(), "player");
        go.setLocation(10, 10);
        go.setView(new RectangleView(50, 50, Color.BLUE));
        gos.add(go);

        Random r = new Random();

        for (int i = 0; i < 1000; i += 32) {
            GameObject g = new GameObject(getScene(), "wall");
            g.setLocation(i, 100);
            g.setView(new RectangleView(32, 16, new Color(r.nextFloat(), r.nextFloat(), r.nextFloat())));
            gos.add(g);
        }

        for (GameObject gameObject : gos) {
            getScene().addObject(gameObject);
        }

        getScene().getCamera().setOrigin(go);

        getScene().addString(new Scene.SceneString("Some text", 100, 100, Color.RED));
    }
}
