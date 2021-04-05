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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/** Bucket Test Cases */
public class BucketTest {

    @Test
    public void testAllocation() {
        ServiceNode node1 = new ServiceNode("DC1", "127.0.0.1", 8080);
        Integer node1Count = 0;

        ServiceNode node2 = new ServiceNode("DC2", "127.0.0.2", 8081);
        Integer node2Count = 0;

        ServiceNode node3 = new ServiceNode("DC3", "127.0.0.3", 8082);
        Integer node3Count = 0;

        ServiceNode node4 = new ServiceNode("DC4", "127.0.0.4", 8084);
        Integer node4Count = 0;

        ServiceNode node5 = new ServiceNode("DC5", "127.0.0.5", 8085);
        Integer node5Count = 0;

        ServiceNode node6 = new ServiceNode("DC6", "127.0.0.6", 8086);
        Integer node6Count = 0;

        ServiceNode node7 = new ServiceNode("DC7", "127.0.0.7", 8087);
        Integer node7Count = 0;

        Bucket<ServiceNode> bucket =
                new Bucket<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7), 10);
        ArrayList<String> items1 = new ArrayList<String>();
        ArrayList<String> items2 = new ArrayList<String>();

        for (Integer i = 0; i < 1000000; i++) {
            items1.add(bucket.routeNode(i.toString()).toString());

            if (items1.get(i).equals(node1.toString())) {
                node1Count++;
            }

            if (items1.get(i).equals(node2.toString())) {
                node2Count++;
            }

            if (items1.get(i).equals(node3.toString())) {
                node3Count++;
            }

            if (items1.get(i).equals(node4.toString())) {
                node4Count++;
            }

            if (items1.get(i).equals(node5.toString())) {
                node5Count++;
            }

            if (items1.get(i).equals(node6.toString())) {
                node6Count++;
            }

            if (items1.get(i).equals(node7.toString())) {
                node7Count++;
            }
        }

        for (Integer i = 0; i < 1000000; i++) {
            items2.add(bucket.routeNode(i.toString()).toString());
        }

        for (Integer i = 0; i < 1000000; i++) {
            if (!items1.get(i).equals(items2.get(i))) {
                System.out.println(i);
            }

            assertEquals(items1.get(i), items2.get(i));
        }

        System.out.println("Node1 count: " + node1Count);
        System.out.println("Node2 count: " + node2Count);
        System.out.println("Node3 count: " + node3Count);
        System.out.println("Node4 count: " + node4Count);
        System.out.println("Node5 count: " + node5Count);
        System.out.println("Node6 count: " + node6Count);
        System.out.println("Node7 count: " + node7Count);
        System.out.println(
                "- Total count: "
                        + (node1Count
                                + node2Count
                                + node3Count
                                + node4Count
                                + node5Count
                                + node6Count
                                + node7Count));
    }
}
