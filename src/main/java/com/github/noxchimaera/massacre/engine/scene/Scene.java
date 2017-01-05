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

import com.github.noxchimaera.massacre.engine.GameObject;
import com.github.noxchimaera.massacre.engine.models.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Max Balushkin
 */
public class Scene extends JPanel {

    private Camera camera;

    private ArrayList<SceneString> strList;
    private ArrayList<GameObject> objects;

    private BufferedImage buffer;

    public Scene() {
        strList = new ArrayList<>();
        objects = new ArrayList<>();

        camera = new Camera(this);
    }

    public Camera getCamera() {
        return camera;
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void addString(SceneString str) {
        strList.add(str);
    }

    @Override protected void paintComponent(Graphics g) {
        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        camera.moveToTarget();
        Vector cam = camera.getLocation();
//        Vector cam = new Vector(0,0);

        Graphics2D g2d = (Graphics2D)buffer.getGraphics();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        for (GameObject go : objects) {
            Vector loc = go.getLocation();
            Vector size = go.getSize();
            if (loc.x() + size.x() < cam.x() && loc.y() + size.y() < cam.y()) {
                continue;
            } else if (loc.x() > getWidth() && loc.y() > getHeight()) {
                continue;
            }

            g2d.setColor(go.getColour());
            g2d.fillRect((int)(go.getX() - cam.x()), (int)(go.getY() - cam.y()), go.getW(), go.getH());
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
