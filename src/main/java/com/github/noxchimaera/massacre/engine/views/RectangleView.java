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

package com.github.noxchimaera.massacre.engine.views;

import com.github.noxchimaera.massacre.engine.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Max Balushkin
 */
public class RectangleView extends View {

    private float width;
    private float height;

    private Color colour;

    public RectangleView(float width, float height, Color colour) {
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    @Override public Vector2 getSize() {
        return new Vector2(width, height);
    }

    @Override public Vector2 getOrigin() {
        return new Vector2(width / 2, height / 2);
    }

    public Color getColour() {
        return colour;
    }

    @Override public Image predraw() {
        BufferedImage img = new BufferedImage(
            (int)width, (int)height, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(colour);
        g.fillRect(0, 0, (int)width, (int)height);
        return img;
    }

}
