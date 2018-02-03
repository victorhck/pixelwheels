/*
 * Copyright 2017 Aurélien Gâteau <mail@agateau.com>
 *
 * This file is part of Pixel Wheels.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.agateau.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.HashMap;

/**
 * Provide mapping between real and virtual keys
 */
public class KeyMapper {
    private final HashMap<VirtualKey, Integer[]> mKeyForVirtualKey = new HashMap<VirtualKey, Integer[]>();

    private static final KeyMapper sDefaultInstance = new KeyMapper();

    public static KeyMapper getDefaultInstance() {
        return sDefaultInstance;
    }

    public KeyMapper() {
        setKey(VirtualKey.LEFT, Input.Keys.LEFT);
        setKey(VirtualKey.RIGHT, Input.Keys.RIGHT);
        setKey(VirtualKey.UP, Input.Keys.UP);
        setKey(VirtualKey.DOWN, Input.Keys.DOWN);
        setKeys(VirtualKey.TRIGGER, new Integer[]{Input.Keys.SPACE, Input.Keys.ENTER});
        setKeys(VirtualKey.BACK, new Integer[]{Input.Keys.ESCAPE, Input.Keys.BACK});
    }

    public void setKey(VirtualKey vkey, int key) {
        setKeys(vkey, new Integer[]{key});
    }

    public void setKeys(VirtualKey vkey, Integer[] keys) {
        mKeyForVirtualKey.put(vkey, keys);
    }

    public void addKey(VirtualKey vkey, int key) {
        Integer[] keys = mKeyForVirtualKey.get(vkey);
        if (keys == null) {
            setKey(vkey, key);
            return;
        }
        Integer[] newKeys = new Integer[keys.length + 1];
        System.arraycopy(keys, 0, newKeys, 0, keys.length);
        newKeys[keys.length] = key;
        setKeys(vkey, newKeys);
    }

    public boolean isKeyPressed(VirtualKey vkey) {
        Integer[] keys = mKeyForVirtualKey.get(vkey);
        for (Integer key : keys) {
            if (Gdx.input.isKeyPressed(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isKeyJustPressed(VirtualKey vkey) {
        Integer[] keys = mKeyForVirtualKey.get(vkey);
        for (Integer key : keys) {
            if (Gdx.input.isKeyJustPressed(key)) {
                return true;
            }
        }
        return false;
    }
}
