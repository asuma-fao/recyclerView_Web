package es.faouzi.ua.procweb.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
@Root(name = "item", strict = false)
public class Item {
    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", authoritem='" + authoritem + '\'' +
                ", subtittleitem='" + subtittleitem + '\'' +
                ", guid='" + guid + '\'' +
                ", image=" + image +
                ", enclosure=" + enclosureUrl +
                '}';
    }

    @Path("title")
    @Text(required=false)
    public String title;


    @Path("itunes:author")
    @Text(required=false)
    public String authoritem;

    @Path("itunes:subtitle")
    @Text(required=false)
    public String subtittleitem;


    @Path("guid")
    @Text(required=false)
    public String guid;

    @Path("itunes:image")
    @Attribute(name = "href", required = false)
    public String image = "";


    @Path("enclosure")
    @Attribute(name = "url", required = false)
    public String enclosureUrl = "";

  /*  public Item(String title, String authoritem, String subtittleitem, String guid, String image, String enclosureUrl) {
        this.title = title;
        this.authoritem = authoritem;
        this.subtittleitem = subtittleitem;
        this.guid = guid;
        this.image = image;
        this.enclosureUrl = enclosureUrl;
    }
*/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthoritem() {
        return authoritem;
    }

    public void setAuthoritem(String authoritem) {
        this.authoritem = authoritem;
    }

    public String getSubtittleitem() {
        return subtittleitem;
    }

    public void setSubtittleitem(String subtittleitem) {
        this.subtittleitem = subtittleitem;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
    }

    @Namespace(prefix="itunes")
    static public class Imagen {

        @Attribute(name = "href")
        public String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public Imagen(String href) {
            this.href = href;
        }
    }



}
