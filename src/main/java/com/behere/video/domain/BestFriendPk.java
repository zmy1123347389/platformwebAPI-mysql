package com.behere.video.domain;

import java.util.List;

/**
 * @author: Behere
 */
public class BestFriendPk {

    private List<BestFriend> bestFriends;

    private BestFriend my;

    public List<BestFriend> getBestFriends() {
        return bestFriends;
    }

    public void setBestFriends(List<BestFriend> bestFriends) {
        this.bestFriends = bestFriends;
    }

    public BestFriend getMy() {
        return my;
    }

    public void setMy(BestFriend my) {
        this.my = my;
    }
}