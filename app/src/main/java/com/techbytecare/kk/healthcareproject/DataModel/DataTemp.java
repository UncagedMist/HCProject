package com.techbytecare.kk.healthcareproject.DataModel;

/**
 * Created by kundan on 3/1/2018.
 */

public class DataTemp {

    private Feeds[] feeds;

    private Channel channel;

    public Feeds[] getFeeds ()
    {
        return feeds;
    }

    public void setFeeds (Feeds[] feeds)
    {
        this.feeds = feeds;
    }

    public Channel getChannel ()
    {
        return channel;
    }

    public void setChannel (Channel channel)
    {
        this.channel = channel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [feeds = "+feeds+", channel = "+channel+"]";
    }
}
