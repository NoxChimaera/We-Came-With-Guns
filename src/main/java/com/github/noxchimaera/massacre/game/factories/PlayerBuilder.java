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
import com.github.noxchimaera.massacre.engine.utils.Spritesheet;
import com.github.noxchimaera.massacre.engine.views.Animation;
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
        player_go.setZIndex(1);

        Spritesheet player_ss = Spritesheet.load(Main.class.getResource("/sprites/player.gif"));
        if (player_ss == null) {
            throw new RuntimeException("Can't load player.gif");
        }

        Sprite head_view = new Sprite(player_ss.clip(160, 0, 235, 50));

        Animation player_view = new Animation(500);
        Sprite player_down_idle = new Sprite(player_ss.clip(0, 55, 60, 105));
        Sprite player_down_stepr = new Sprite(player_ss.clip(65, 55, 125,105));
        Sprite player_down_stepl = new Sprite(player_ss.clip(130, 55, 190, 105));
        player_view.addFrame(player_down_idle, player_down_stepl, player_down_idle, player_down_stepr);

        player_go.setView(player_view);
        Player p = new Player(player_go);
        Vector2 playerSize = player_view.getSize();

        GameObject head_go = new GameObject(scene);
        head_go.setView(head_view);
        head_go.setLocation(
            player_go.getLocation().x() - 5,
            player_go.getLocation().y() - head_view.getSize().y() + 5);
        head_go.setZIndex(2);
        p.getChilds().add(head_go);

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