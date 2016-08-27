package com.patterns.creational.builder;

/**
 * Created on 8/5/16.
 */
public class FriendGraphClient {
    public static void main(String[] args) {
        //FriendGraph.FriendBuilder fb = new FriendGraph.FriendBuilder();
        FriendGraph fg = new FriendGraph.FriendBuilder()
                .addFriend(new Friend("a"), new Friend("b"))
                .addFriend(new Friend("c"), new Friend("d"))
                .build();

        // now the friend graph is built, you cannot add new friend to the same graph.
        // if you add new friend using same builder, you will have to call .build again
        // but it returns a new FriendGraph object.
        // only querying is possible.
        fg.isFriend(new Friend("a"), new Friend("b"));
    }

    public void getFriend(Builder<? extends FriendGraph.FriendBuilder> friendBuilder) {
        friendBuilder.build();
    }
}
