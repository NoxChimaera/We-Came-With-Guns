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
import com.github.noxchimaera.massacre.engine.views.*;
import com.github.noxchimaera.massacre.game.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * @author Max Balushkin
 */
public class PlayerBuilder {

    public Player build(Scene scene, Vector2 location) {
        GameObject player_go = new GameObject(scene, null);
        player_go.setLocation(location);
        player_go.setZIndex(1);

        Spritesheet player_ss = Spritesheet.load(Main.class.getResource("/sprites/player.gif"));
        if (player_ss == null) {
            throw new RuntimeException("Can't load player.gif");
        }

        AutomataView head_view = new AutomataView();
        Sprite head_ne = new Sprite(player_ss.clip(0, 0, 75, 50));
        head_view.addState("ne", head_ne);
        Sprite head_nw = new Sprite(player_ss.clip(80, 0, 80 + 75, 50));
        head_view.addState("nw", head_nw);
        Sprite head_sw = new Sprite(player_ss.clip(160, 0, 160 + 75, 50));
        head_view.addState("sw", head_sw);
        Sprite head_se = new Sprite(player_ss.clip(240, 0, 240 + 75, 50));
        head_view.addState("se", head_se);
        head_view.setState("sw");

        Animation walk_anim = new Animation(250);
        Sprite player_down_idle = new Sprite(player_ss.clip(0, 55, 60, 105));
        Sprite player_down_stepr = new Sprite(player_ss.clip(65, 55, 125,105));
        Sprite player_down_stepl = new Sprite(player_ss.clip(130, 55, 190, 105));
        walk_anim.addFrame(player_down_idle, player_down_stepl, player_down_idle, player_down_stepr);

        AutomataView player_view = new AutomataView();
        player_view.addState("idle", player_down_idle);
        player_view.addState("walk", walk_anim);
        player_view.setState("idle");

        GameObject head_go = new GameObject(scene, player_go);
        head_go.setView(head_view);
        head_go.setLocation(
            -5,
            -head_view.getSize().y() + 5);
        head_go.setZIndex(2);

        player_go.setView(player_view);
        Player p = new Player(player_go, head_go);
        Vector2 playerSize = player_view.getSize();

        Sprite hand_view = null;
        try {
            hand_view = new Sprite(ImageIO.read(Main.class.getResource("/sprites/hand_white.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Vector2 handSize = hand_view.getSize();

        float y = playerSize.y() / 2 - handSize.y() / 2;
        float xl = -handSize.x() - 1;
        float xr = playerSize.x() + 1;

        GameObject leftHand_go = new GameObject(scene, player_go);
        leftHand_go.setLocation(xl, y);
        leftHand_go.setView(hand_view);

        GameObject rightHand_go = new GameObject(scene, player_go);
        rightHand_go.setLocation(xr, y);
        rightHand_go.setView(hand_view);

        p.getChilds().add(leftHand_go);
        p.getChilds().add(rightHand_go);

        return p;
    }

}
