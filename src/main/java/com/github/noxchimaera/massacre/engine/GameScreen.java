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

import com.github.noxchimaera.massacre.engine.actors.Actor;
import com.github.noxchimaera.massacre.engine.interfaces.DrawableComponent;
import com.github.noxchimaera.massacre.engine.interfaces.InitializableComponent;
import com.github.noxchimaera.massacre.engine.interfaces.UpdatableComponent;
import com.github.noxchimaera.massacre.engine.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Balushkin
 */
public abstract class GameScreen implements InitializableComponent, UpdatableComponent, DrawableComponent {

    private Scene scene;
    private boolean running;

    protected List<Actor> actors;

    public GameScreen() {
        scene = new Scene();
        running = true;
        actors = new ArrayList<>();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override public void draw() {
        scene.repaint();
    }

}
