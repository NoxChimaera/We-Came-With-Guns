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

package com.github.noxchimaera.massacre.engine.utils;

import com.github.noxchimaera.massacre.engine.views.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author Max Balushkin
 */
public class Spritesheet {

    public static Spritesheet load(URL source) {
        try {
            return new Spritesheet(ImageIO.read(source));
        } catch (IOException e) {
            System.out.println("Can not load spritesheet `" + source + "`");
            return null;
        }
    }

    private BufferedImage spritesheet;

    public Spritesheet(BufferedImage spritesheet) {
        this.spritesheet = spritesheet;
    }

    public BufferedImage clip(int x, int y, int x2, int y2) {
        return spritesheet.getSubimage(x, y, x2 - x, y2 - y);
    }

}
