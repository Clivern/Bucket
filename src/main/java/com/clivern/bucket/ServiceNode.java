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

/** Service Node Class */
public class ServiceNode implements Node {

    private final String dc;
    private final String ip;
    private final int port;

    /**
     * Class Constructor
     *
     * @param dc the service datacenter
     * @param ip the service IP
     * @param port the service port
     */
    public ServiceNode(String dc, String ip, int port) {
        this.dc = dc;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String getKey() {
        return this.dc + " - " + this.ip + " : " + this.port;
    }

    @Override
    public String toString() {
        return this.getKey();
    }
}
