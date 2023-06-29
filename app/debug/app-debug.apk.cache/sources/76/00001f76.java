package orderspage;

/* loaded from: classes3.dex */
public class BeanclassOrderPage {
    private String orderdate;
    private String orderno;
    private String price;
    private int status;

    public BeanclassOrderPage(int status, String price, String orderno, String orderdate) {
        this.status = status;
        this.price = price;
        this.orderno = orderno;
        this.orderdate = orderdate;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderno() {
        return this.orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderdate() {
        return this.orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }
}