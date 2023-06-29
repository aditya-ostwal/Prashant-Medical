package adresspage;

/* loaded from: classes4.dex */
public class AdressBeanlist {
    private String adressType;
    private String adressname;
    private String city;
    private String companyname;
    private String companytaxname;
    private String companytaxnumber;
    private String country;
    private String district;
    private String id;
    private String name;
    private String openadress;
    private String surname;
    private String telephone;

    public AdressBeanlist(String id, String name, String surname, String telephone, String country, String city, String district, String openadress, String adressname, String companyname, String companytaxname, String companytaxnumber, String adressType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.country = country;
        this.city = city;
        this.district = district;
        this.openadress = openadress;
        this.adressname = adressname;
        this.companyname = companyname;
        this.companytaxname = companytaxname;
        this.companytaxnumber = companytaxnumber;
        this.adressType = adressType;
    }

    public String getAdressType() {
        return this.adressType;
    }

    public void setAdressType(String adressType) {
        this.adressType = adressType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOpenadress() {
        return this.openadress;
    }

    public void setOpenadress(String openadress) {
        this.openadress = openadress;
    }

    public String getAdressname() {
        return this.adressname;
    }

    public void setAdressname(String adressname) {
        this.adressname = adressname;
    }

    public String getCompanyname() {
        return this.companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanytaxname() {
        return this.companytaxname;
    }

    public void setCompanytaxname(String companytaxname) {
        this.companytaxname = companytaxname;
    }

    public String getCompanytaxnumber() {
        return this.companytaxnumber;
    }

    public void setCompanytaxnumber(String companytaxnumber) {
        this.companytaxnumber = companytaxnumber;
    }
}