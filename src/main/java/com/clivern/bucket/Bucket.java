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

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/** Bucket Class */
public class Bucket<T extends Node> {

    private final SortedMap<Long, VirtualNode<T>> ring = new TreeMap<>();

    private final HashFunction hashFunction;

    /**
     * Class Constructor
     *
     * @param pNodes collections of physical nodes
     * @param vNodeCount amounts of virtual nodes
     */
    public Bucket(Collection<T> pNodes, int vNodeCount) {
        this(pNodes, vNodeCount, new Hasher());
    }

    /**
     * Class Constructor
     *
     * @param pNodes collections of physical nodes
     * @param vNodeCount amounts of virtual nodes
     * @param hashFunction hash Function to hash Node instances
     */
    public Bucket(Collection<T> pNodes, int vNodeCount, HashFunction hashFunction) {
        if (hashFunction == null) {
            throw new NullPointerException("Hash Function is null");
        }

        this.hashFunction = hashFunction;

        if (pNodes != null) {
            for (T pNode : pNodes) {
                this.addNode(pNode, vNodeCount);
            }
        }
    }

    /**
     * Add physical node to the hash ring with some virtual nodes
     *
     * @param pNode physical node needs added to hash ring
     * @param vNodeCount the number of virtual node of the physical node. Value should be greater
     *     than or equals to 0
     */
    public void addNode(T pNode, int vNodeCount) {
        if (vNodeCount < 0) {
            throw new IllegalArgumentException("Illegal virtual node counts :" + vNodeCount);
        }

        int existingReplicas = this.getExistingReplicas(pNode);

        for (int i = 0; i < vNodeCount; i++) {
            VirtualNode<T> vNode = new VirtualNode<>(pNode, i + existingReplicas);
            ring.put(hashFunction.hash(vNode.getKey()), vNode);
        }
    }

    /**
     * Remove the physical node from the hash ring
     *
     * @param pNode physical node
     */
    public void removeNode(T pNode) {
        Iterator<Long> it = ring.keySet().iterator();

        while (it.hasNext()) {
            Long key = it.next();
            VirtualNode<T> virtualNode = ring.get(key);
            if (virtualNode.isVirtualNodeOf(pNode)) {
                it.remove();
            }
        }
    }

    /**
     * With a specified key, route the nearest Node instance in the current hash ring
     *
     * @param key the object key to find a nearest Node
     * @return T
     */
    public T routeNode(String key) {
        if (ring.isEmpty()) {
            return null;
        }

        Long hashVal = hashFunction.hash(key);
        SortedMap<Long, VirtualNode<T>> tailMap = ring.tailMap(hashVal);
        Long nodeHashVal = !tailMap.isEmpty() ? tailMap.firstKey() : ring.firstKey();

        return ring.get(nodeHashVal).getPhysicalNode();
    }

    public int getExistingReplicas(T pNode) {
        int replicas = 0;

        for (VirtualNode<T> vNode : ring.values()) {
            if (vNode.isVirtualNodeOf(pNode)) {
                replicas++;
            }
        }

        return replicas;
    }
}
