package es.faouzi.ua.procweb.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
@Root(name = "rss", strict = false)
public class Rss {

    @Element
    public Channel channel;


    public Rss(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }



    public Rss() {
    }

    @Override
    public String toString() {
        return "RssFeed [channel=" + channel + "]";
    }

}


