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
import com.github.noxchimaera.massacre.engine.models.Vector;
import com.github.noxchimaera.massacre.engine.scene.GameObject;
import com.github.noxchimaera.massacre.engine.scene.Scene;
import com.github.noxchimaera.massacre.engine.scene.ZIndex;
import com.github.noxchimaera.massacre.engine.views.RectangleView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Max Balushkin
 */
public class StartScreen extends GameScreen {

    private Player player;
    private List<Wall> walls;

    @Override public void draw() {
        super.draw();
    }

    @Override public void update(GameTime gameTime) {
        super.update(gameTime);
    }

    @Override public void initialize() {
        final Scene scene = getScene();
        GameObject player_go = new GameObject(scene);
        player_go.setLocation(10, 10);
        player_go.setView(new RectangleView(50, 50, Color.GREEN));
        player = new Player(player_go);
        actors.add(player);

        walls = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 1000; i += 32) {
            GameObject wall_go = new GameObject(scene);
            wall_go.setLocation(i, 100);
            wall_go.setView(new RectangleView(32, 16,
                new Color(r.nextFloat(), r.nextFloat(), r.nextFloat())));
            Wall wall = new Wall(wall_go);
            walls.add(wall);
        }
        actors.addAll(walls);

        GameObject cursor_go = new GameObject(scene);
        cursor_go.setLocation(0, 0);
        cursor_go.setView(new RectangleView(10, 10, Color.WHITE));
        cursor_go.setZIndex(ZIndex.GUI);
        Cursor cursor = new Cursor(cursor_go);
        actors.add(cursor);

        scene.getCamera().setOrigin(player_go);
    }

}
