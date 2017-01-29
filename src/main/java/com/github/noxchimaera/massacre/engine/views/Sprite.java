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
public class Sprite extends View {

    private BufferedImage image;

    public Sprite(BufferedImage image) {
        this.image = image;
    }

    @Override public Image predraw() {
        return image;
    }

    @Override public Vector2 getSize() {
        return new Vector2(image.getWidth(), image.getHeight());
    }

    @Override public Vector2 getOrigin() {
        return new Vector2(image.getWidth() / 2, image.getHeight() / 2);
    }

}
