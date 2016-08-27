package com.patterns.creational.builder;

/**
 * Created on 8/5/16.
 */
public class FriendGraph {
    //final SomeGraph g;
    public boolean isFriend(Friend a, Friend b) {
        // some algorithm to find out if b is a friend of a;
        return true;
    }
    public static class FriendBuilder implements Builder{
        //SomeGraph g;
        public FriendBuilder() {
            // creates a new graph
            // g = new SomeGraph();
        }
        public FriendBuilder addFriend(Friend a, Friend b) {
            // some algorithm to create a graph
            return this;
        }
        // only build should have capability to create new object
        public FriendGraph build() {
            return new FriendGraph(this);
        }
    }
    private FriendGraph(FriendBuilder fb) {
        //some algorithm to create graph.
        // this.g = fb.g;
    }
}


