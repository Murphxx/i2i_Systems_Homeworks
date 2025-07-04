package com.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {
    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<Integer, Person> map = hz.getMap("people");

        for (int i = 0; i < 10000; i++) {
            map.put(i, new Person("Person-" + i));
        }

        System.out.println("10,000 Person objects added to Hazelcast map.");

        for (int i = 0; i < 10; i++) {
            System.out.println("Key " + i + ": " + map.get(i));
        }

        hz.shutdown();
    }
}
