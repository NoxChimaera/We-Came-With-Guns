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
import com.github.noxchimaera.massacre.engine.views.RectangleView;
import com.github.noxchimaera.massacre.engine.views.Sprite;
import com.github.noxchimaera.massacre.engine.views.View;
import com.github.noxchimaera.massacre.game.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * @author Max Balushkin
 */
public class PlayerBuilder {

    public Player build(Scene scene, Vector2 location) {
        GameObject player_go = new GameObject(scene);
        player_go.setLocation(location);

        View player_view = new RectangleView(50, 50, Color.GREEN);
        player_go.setView(player_view);
        Player p = new Player(player_go);
        Vector2 playerSize = player_view.getSize();

        Sprite hand_view = null;
        try {
            hand_view = new Sprite(ImageIO.read(Main.class.getResource("/sprites/hand_white.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Vector2 handSize = hand_view.getSize();

        float y = location.y() + playerSize.y() / 2 - handSize.y() / 2;
        float xl = location.x() - handSize.x() - 1;
        float xr = location.x() + playerSize.x() + 1;


        GameObject leftHand_go = new GameObject(scene);
        leftHand_go.setLocation(xl, y);
        leftHand_go.setView(hand_view);

        GameObject rightHand_go = new GameObject(scene);
        rightHand_go.setLocation(xr, y);
        rightHand_go.setView(hand_view);

        p.getChilds().add(leftHand_go);
        p.getChilds().add(rightHand_go);

        return p;
    }

}
