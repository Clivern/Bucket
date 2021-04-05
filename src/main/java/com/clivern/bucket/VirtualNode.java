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

/** VirtualNode Class */
public class VirtualNode<T extends Node> implements Node {

    final T physicalNode;

    final int replicaIndex;

    public VirtualNode(T physicalNode, int replicaIndex) {
        this.physicalNode = physicalNode;
        this.replicaIndex = replicaIndex;
    }

    @Override
    public String getKey() {
        return this.physicalNode.getKey() + "-" + this.replicaIndex;
    }

    public boolean isVirtualNodeOf(T pNode) {
        return this.physicalNode.getKey().equals(pNode.getKey());
    }

    public T getPhysicalNode() {
        return this.physicalNode;
    }
}
