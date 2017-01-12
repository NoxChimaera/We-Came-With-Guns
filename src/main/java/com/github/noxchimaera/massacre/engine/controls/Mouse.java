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

package com.github.noxchimaera.massacre.engine.controls;

import com.github.noxchimaera.massacre.engine.models.Vector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Max Balushkin
 */
public class Mouse extends MouseAdapter {

    private static Mouse shared = new Mouse();

    public static Mouse shared() {
        return shared;
    }

    private Set<Integer> buttons = new HashSet<>(3);

    private double wheelRotation;

    private int x;
    private int y;

    public boolean isPressed(int button) {
        return buttons.contains(button);
    }

    @Override public void mousePressed(MouseEvent e) {
        buttons.add(e.getButton());
    }

    @Override public void mouseReleased(MouseEvent e) {
        buttons.remove(e.getButton());
    }

    public double getWheelRotation() {
        return wheelRotation;
    }

    @Override public void mouseWheelMoved(MouseWheelEvent e) {
        wheelRotation = e.getWheelRotation();
    }

    public Vector getLocation() {
        return new Vector(x, y);
    }

    @Override public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
