package favoritespage;

/* loaded from: classes9.dex */
public class FavBeanlist {
    private String image;
    private String price;
    private String stockcode;
    private String title;

    public FavBeanlist(String image, String title, String stockcode, String price) {
        this.image = image;
        this.title = title;
        this.stockcode = stockcode;
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStockcode() {
        return this.stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}