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

import com.github.noxchimaera.massacre.engine.actors.Actor;
import com.github.noxchimaera.massacre.engine.collision.Collider;
import com.github.noxchimaera.massacre.engine.models.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Balushkin
 */
public class Scene extends JPanel {

    private boolean showColliders = true;

    private Camera camera;

    private ArrayList<SceneString> strList;

    private List<Actor> childs;

    private BufferedImage buffer;

    public Scene() {
        strList = new ArrayList<>();
        childs = new ArrayList<>();

        camera = new Camera(this);
    }

    public Camera getCamera() {
        return camera;
    }

    public void addObject(Actor obj) {
        childs.add(obj);
    }

    public void addString(SceneString str) {
        strList.add(str);
    }

    public List<Actor> getActorsOnScene() {
        Vector cam = camera.getLocation();

        List<Actor> res = new ArrayList<>();
        for (Actor child : childs) {
            if (child.getGameObject().getView() == null) {
                continue;
            }
            Vector loc = child.getGameObject().getLocation();
            Vector size = child.getGameObject().getView().getSize();
            boolean inBounds = loc.x() + size.x() > cam.x() && loc.y() + size.y() > cam.y();
            inBounds &= loc.x() < getWidth() && loc.y() < getHeight();
            if (!inBounds) {
                continue;
            }
            res.add(child);
        }
        return childs;
    }
    public List<Actor> getActors() {
        return childs;
    }

    private void drawCollider(Graphics2D g, Collider c) {
        if (c.wasCollision()) {
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


//        getLocation().sub(getScene().getCamera().getLocation())
    }

    @Override protected void paintComponent(Graphics g) {
        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2d = (Graphics2D)buffer.getGraphics();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        for (Actor child : getActorsOnScene()) {
            if (!child.getGameObject().getView().isVisible()) {
                continue;
            }
            child.getGameObject().draw(g2d);
            if (showColliders) {
                for (Collider collider : child.getColliders()) {
                    drawCollider(g2d, collider);
                }
            }
        }
        for (SceneString str : strList) {
            g2d.setColor(str.getColour());
            g2d.drawString(str.getContent(), str.getX(), str.getY());
        }

        g.drawImage(buffer, 0, 0, this);
    }

    public static class SceneString {
        private final String content;
        private final int x;
        private final int y;
        private final Color colour;

        public SceneString(String content, int x, int y) {
            this(content, x, y, Color.BLACK);
        }

        public SceneString(String content, int x, int y, Color colour) {
            this.content = content;
            this.x = x;
            this.y = y;
            this.colour = colour;
        }

        public String getContent() {
            return content;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Color getColour() {
            return colour;
        }
    }

}
