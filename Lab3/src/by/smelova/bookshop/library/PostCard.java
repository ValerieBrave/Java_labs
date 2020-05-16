package by.smelova.bookshop.library;

public class PostCard extends Book {
    private String design;

    public String getDesign() {
        return design;
    }

    public PostCard() {
        super();
        this.design = "";
    }

    public PostCard(int pr, String tit, int pag, String au, String ow, String design) {
        super(pr, tit, pag, au, ow);
        this.design = design;
    }
    @Override
    public String toString() {
        return this.author + ", " + this.title + ", "+this.design+", " + this.price + "$";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        else {
            PostCard bk = (PostCard)obj;
            if(this.price == bk.price
                    && this.pages == bk.pages
                    && this.owner.equals(bk.owner)
                    && this.title.equals(bk.title)
                    && this.author.equals(bk.author)
                    && this.design.equals(bk.design)) return true;
            else return  false;
        }
    }
    @Override
    public  int hashCode() {
        return  this.hashCode();
    }
}
