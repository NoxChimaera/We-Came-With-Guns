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

package com.github.noxchimaera.massacre.engine.scene;

import com.github.noxchimaera.massacre.engine.GameScreen;
import com.github.noxchimaera.massacre.engine.Vector2;
import com.github.noxchimaera.massacre.engine.actors.Actor;
import com.github.noxchimaera.massacre.engine.collision.Collider;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Max Balushkin
 */
public class Scene extends JPanel {

    private GameScreen screen;

    private Camera camera;

    private BufferedImage buffer;

    private boolean showColliders = true;

    public void toggleDebugMode() {
        showColliders = !showColliders;
    }

    public Scene(GameScreen screen) {
        this.screen = screen;
        camera = new Camera(this);
    }

    public Camera getCamera() {
        return camera;
    }

    public List<Actor> getActorsOnScene() {
        Vector2 cam = camera.getLocation();

        List<Actor> res = new ArrayList<>();
        for (Actor child : screen.getActors()) {
            if (child.getGameObject().getView() == null) {
                continue;
            }
            Vector2 loc = child.getGameObject().getLocation();
            Vector2 size = child.getGameObject().getView().getSize();
            boolean inBounds = loc.x() + size.x() > cam.x() && loc.y() + size.y() > cam.y();
            inBounds &= loc.x() < getWidth() && loc.y() < getHeight();
            if (!inBounds) {
                continue;
            }
            res.add(child);
        }
        return res;
    }

    private void drawCollider(Graphics2D g, Collider c, boolean isActorEnabled) {
        if (!isActorEnabled) {
            g.setColor(Color.BLACK);
        } else if (c.wasCollision()) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.PINK);
        }

        int x = (int)(c.getX() - camera.getLocation().x());
        int y = (int)(c.getY() - camera.getLocation().y());
        int w = (int)c.getWidth();
        int h = (int)c.getHeight();

        g.drawRect(x, y, w, h);
        g.drawLine(x, y, x + w, y + h);
        g.drawLine(x, y + h, x + w, y);
    }

    @Override protected void paintComponent(Graphics g) {
        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2d = (Graphics2D)buffer.getGraphics();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        List<Actor> onScene = getActorsOnScene();
        List<GameObject> gos = new ArrayList<>();
        for (Actor actor : onScene) {
            gos.add(actor.getGameObject());
            gos.addAll(actor.getChilds());
        }
        gos.sort(Comparator.comparingInt(GameObject::getZIndex));
        for (GameObject go : gos) {
            go.draw(g2d);
        }

        if (showColliders) {
            for (Actor actor : onScene) {
                for (Collider collider : actor.getColliders()) {
                    drawCollider(g2d, collider, actor.isEnabled());
                }
            }
        }

        g.drawImage(buffer, 0, 0, this);
    }

}
