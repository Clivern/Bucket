/*
 * Copyright (C) 2021 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.bucket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Hasher Class */
public class Hasher implements HashFunction {

    /** @var MessageDigest */
    private MessageDigest instance;

    /** Class Constructor */
    public Hasher() {
        try {
            this.instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    /**
     * Get a hash of a string
     *
     * @param key the key to hash
     * @return the hash
     */
    @Override
    public long hash(String key) {
        this.instance.reset();
        this.instance.update(key.getBytes());

        byte[] digest = this.instance.digest();

        long h = 0;

        for (int i = 0; i < 4; i++) {
            h <<= 8;
            h |= ((int) digest[i]) & 0xFF;
        }

        return h;
    }
}
