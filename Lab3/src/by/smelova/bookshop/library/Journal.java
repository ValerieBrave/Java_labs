package by.smelova.bookshop.library;

public class Journal extends Book {
    private String publishers;
    public Journal() {
        super();
        this.publishers = "";
    }

    public Journal(int pr, String tit, int pag, String au, String ow, String publishers) {
        super(pr, tit, pag, au, ow);
        this.publishers = publishers;
    }

    public String getPublishers() {
        return publishers;
    }
    @Override
    public String toString() {
        return this.author + ", " + this.title + ", " + this.price + "$";
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        else {
            Journal bk = (Journal) obj;
            if(this.price == bk.price
                    && this.pages == bk.pages
                    && this.owner.equals(bk.owner)
                    && this.title.equals(bk.title)
                    && this.author.equals(bk.author)
                    && this.publishers.equals(bk.publishers)) return true;
            else return  false;
        }
    }
    @Override
    public  int hashCode() {
        return  this.hashCode();
    }
}
