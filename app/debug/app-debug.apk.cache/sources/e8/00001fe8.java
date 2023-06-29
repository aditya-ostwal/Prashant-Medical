package yorumlarsayfasi;

/* loaded from: classes8.dex */
public class BeanclassReview {
    private String date;
    private String description;
    private String like;
    private String rateno;
    private String title;
    private String username;

    public BeanclassReview(String title, String rateno, String date, String username, String description, String like) {
        this.title = title;
        this.rateno = rateno;
        this.date = date;
        this.username = username;
        this.description = description;
        this.like = like;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRateno() {
        return this.rateno;
    }

    public void setRateno(String rateno) {
        this.rateno = rateno;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLike() {
        return this.like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}