package es.faouzi.ua.procweb.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
@Root(name = "channel",strict = false)
public class Channel {
    @Element(name = "description")
    public String description;

    @Element(name = "title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ElementList(name = "item", inline = true)
    public List<Item> itemList;

    public Channel(List<Item> itemList) {

        this.itemList = itemList;
    }

    public Channel() {
    }

    public Channel(String description, List<Item> itemList) {
        this.description = description;
        this.itemList = itemList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
