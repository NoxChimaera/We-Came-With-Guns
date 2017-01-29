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

package com.github.noxchimaera.massacre.game.factories;

import com.github.noxchimaera.massacre.Main;
import com.github.noxchimaera.massacre.engine.Vector2;
import com.github.noxchimaera.massacre.engine.scene.GameObject;
import com.github.noxchimaera.massacre.engine.scene.Scene;
import com.github.noxchimaera.massacre.engine.views.Sprite;
import com.github.noxchimaera.massacre.engine.views.View;
import com.github.noxchimaera.massacre.game.Bullet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Max Balushkin
 */
public class BulletFactory {

    private static BufferedImage bulletImg;

    static {
        try {
            bulletImg = ImageIO.read(Main.class.getResource("/sprites/hand_white.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bullet create(Scene scene, Vector2 direction) {
        GameObject bullet_go = new GameObject(scene);
        View bullet_view = new Sprite(bulletImg);
//        View bullet_view = new RectangleView(16, 16, Color.RED);
        bullet_go.setView(bullet_view);
        return new Bullet(bullet_go, direction);
    }

}
