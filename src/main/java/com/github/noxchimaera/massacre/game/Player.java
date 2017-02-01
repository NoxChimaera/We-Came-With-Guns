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

package com.github.noxchimaera.massacre.game;

import com.github.noxchimaera.massacre.engine.GameTime;
import com.github.noxchimaera.massacre.engine.actors.Actor;
import com.github.noxchimaera.massacre.engine.collision.Collider;
import com.github.noxchimaera.massacre.engine.controls.Keyboard;
import com.github.noxchimaera.massacre.engine.Vector2;
import com.github.noxchimaera.massacre.engine.scene.GameObject;
import com.github.noxchimaera.massacre.engine.views.AutomataView;

import java.awt.event.KeyEvent;

/**
 * @author Max Balushkin
 */
public class Player extends Actor {

    private Collider collider_top;
    private Collider collider_right;
    private Collider collider_bottom;
    private Collider collider_left;

    private final float COLLIDER_OFFSET = 2;
    private final float COLLIDER_SAFE = 2;

    private float speed = 150;

    private GameObject head;
    private AutomataView headAutomata;

    public Player(GameObject body, GameObject head) {
        super(body, false, "player");
        this.head = head;
        getChilds().add(head);
        headAutomata = (AutomataView)head.getView();

        float x = body.getLocation().x();
        float y = body.getLocation().y();

        float w = body.getView().getSize().x();
        float h = body.getView().getSize().y();

        collider_top = new Collider(x + COLLIDER_SAFE, y - COLLIDER_OFFSET,
            w - COLLIDER_OFFSET - COLLIDER_SAFE - 1, 1);
        colliders.add(collider_top);
        collider_right = new Collider(x + w + COLLIDER_OFFSET, y + COLLIDER_OFFSET + COLLIDER_SAFE,
            1, h - COLLIDER_OFFSET - COLLIDER_SAFE - 1);
        colliders.add(collider_right);
        collider_bottom = new Collider(x + COLLIDER_SAFE, y + h + COLLIDER_OFFSET,
            w - COLLIDER_OFFSET - COLLIDER_SAFE - 1, 1);
        colliders.add(collider_bottom);
        collider_left = new Collider(x - COLLIDER_OFFSET, y,
            1, h - COLLIDER_OFFSET - COLLIDER_SAFE - 1);
        colliders.add(collider_left);
    }

    private void updateColliders() {
        float x = gameObject.getLocation().x();
        float y = gameObject.getLocation().y();
        float w = gameObject.getView().getSize().x();
        float h = gameObject.getView().getSize().y();

        collider_top.setLocation(x + COLLIDER_SAFE,y - COLLIDER_OFFSET - 1);
        collider_right.setLocation(x + w + COLLIDER_OFFSET - 1, y + COLLIDER_SAFE);
        collider_bottom.setLocation(x + COLLIDER_SAFE, y + h + COLLIDER_OFFSET - 1);
        collider_left.setLocation(x - COLLIDER_OFFSET - 1, y + COLLIDER_SAFE);
    }

    private boolean wasCollision(Collider collider) {
        return collider.getCollisions().stream()
            .filter(i -> !i.getTag().equals("bullet"))
            .count() != 0;
    }

    @Override public void update(GameTime gameTime) {
        float v = (Keyboard.shared().isPressed(KeyEvent.VK_SHIFT) ? 300 : 150) * (float)gameTime.getDt();
        Vector2 oldLoc = gameObject.getLocation();
        float x = gameObject.getLocation().x();
        float y = gameObject.getLocation().y();

        if (Keyboard.shared().isPressed(KeyEvent.VK_UP, KeyEvent.VK_W) && !wasCollision(collider_top)) {
            y -= v;
        } else if (Keyboard.shared().isPressed(KeyEvent.VK_DOWN, KeyEvent.VK_S) && !wasCollision(collider_bottom)) {
            y += v;
        }
        if (Keyboard.shared().isPressed(KeyEvent.VK_LEFT, KeyEvent.VK_A) && !wasCollision(collider_left)) {
            x -= v;
        } else if (Keyboard.shared().isPressed(KeyEvent.VK_RIGHT, KeyEvent.VK_D) && !wasCollision(collider_right)) {
            x += v;
        }
        gameObject.setLocation(x, y);
        updateColliders();

        float offX = x - oldLoc.x();
        float offY = y - oldLoc.y();

        if (offX != 0 || offY != 0) {
            ((AutomataView)getGameObject().getView()).setState("walk");
            if (offX < 0) {
                if (offY < 0) {
                    headAutomata.setState("nw");
                } else if (offY > 0) {
                    headAutomata.setState("sw");
                } else if (headAutomata.inState("se")) {
                    headAutomata.setState("sw");
                } else if (headAutomata.inState("ne")) {
                    headAutomata.setState("nw");
                }
            } else if (offX > 0) {
                if (offY < 0) {
                    headAutomata.setState("ne");
                } else if (offY > 0) {
                    headAutomata.setState("se");
                } else if (headAutomata.inState("sw")) {
                    headAutomata.setState("se");
                } else if (headAutomata.inState("nw")) {
                    headAutomata.setState("ne");
                }
            }


//            if (offX < 0 && offY < 0) {
//                headAutomata.setState("nw");
//            } else if (offX > 0 && offY < 0) {
//                headAutomata.setState("ne");
//            } else if (offX > 0 && offY > 0) {
//                headAutomata.setState("se");
//            } else if (offX < 0 && offY > 0) {
//                headAutomata.setState("sw");
//            }
        } else {
            ((AutomataView)getGameObject().getView()).setState("idle");
        }

        for (GameObject child : childs) {
            child.moveBy(offX, offY);
        }
    }

}
