package epam.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "name", "author", "shortContent"})
public class Book {

    @XmlElement(required = true, name = "id")
    @JsonProperty("id")
    private  long id;
    @XmlElement(required = true, name = "name")
    @JsonProperty("name")
    private String name;
    @XmlElement(required = true, name = "author")
    @JsonProperty("author")
    private String author;
    @XmlElement(required = true, name = "shortContent")
    @JsonProperty("shortContent")
    private String shortContent;

    public Book() {
    }

    public Book(long id, String name, String author, String shortContent) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.shortContent = shortContent;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getShortContent() {
        return shortContent;
    }
}
