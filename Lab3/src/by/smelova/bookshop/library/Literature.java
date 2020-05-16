package by.smelova.bookshop.library;

import by.smelova.exceptions.InvalidPriceValue;
import com.alibaba.fastjson.annotation.JSONField;

public abstract class Literature {
    @JSONField(name = "price")
    protected int price;
    @JSONField(name = "title")
    protected String title;
    @JSONField(name = "pages")
    protected int pages;
    @JSONField(name = "author")
    protected String author;
    @JSONField(name = "owner")
    protected String owner;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }
    public void setPages(int pg) {this.pages = pg;}

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) throws InvalidPriceValue {
        if(price < 0) throw new InvalidPriceValue("price value must be positive");
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
