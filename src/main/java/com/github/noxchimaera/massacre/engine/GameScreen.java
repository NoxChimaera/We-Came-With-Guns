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
import com.github.noxchimaera.massacre.engine.collision.Collider;
import com.github.noxchimaera.massacre.engine.scene.Scene;
import com.github.noxchimaera.massacre.game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Max Balushkin
 */
public abstract class GameScreen {

    private Scene scene;
    private boolean running;

    protected List<Actor> actors;

    public GameScreen() {
        scene = new Scene(this);
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

    public List<Actor> getActors() {
        return actors;
    }

    public Actor getActorByTag(String tag) {
        for (Actor actor : actors) {
            if (actor.getTag().equals(tag)) {
                return actor;
            }
        }
        return null;
    }

    public List<Actor> getActorsByTag(String tag) {
        return actors.stream()
            .filter(i -> i.getTag().equals(tag))
            .collect(Collectors.toList());
    }

    public void update(GameTime gameTime) {
        scene.getCamera().moveToTarget();
        actors.removeIf(i -> i.isReadyToDestroy());
        actors.forEach(i -> i.getColliders().forEach(j -> j.reset()));

        for (Actor a1 : actors) {
            if (!a1.isEnabled()) {
                continue;
            }

            if (a1.isFixed()) {
                continue;
            }
            for (Actor a2 : actors) {
                if (a1 == a2 || !a2.isEnabled()) {
                    continue;
                }
                a1.checkCollision(a2);
            }
        }

        actors.stream()
            .filter(i -> i.isEnabled())
            .forEach(i -> i.update(gameTime));
    }

    public void draw() {
        scene.repaint();
    }

    public abstract void initialize();

}
