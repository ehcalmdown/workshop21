package sg.nus.iss.workshop21.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer { //model follows the table that was presented or built beforehand
    private Integer id;
    private String company;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String address;
    private String city;
    private String stateProvince;

    public Integer getCustomerId() { // labelled as customer id to diffrentiate between other id
        return id;
    }
    public void setCustomerId(Integer customerId) {
        this.id = customerId;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getBusinessPhone() {
        return businessPhone;
    }
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStateProvince() {
        return stateProvince;
    }
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

       
    public static Customer create(SqlRowSet rs) { //build up the model template for customer form
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("id"));
        c.setCompany("company");
        c.setLastName(rs.getString("last_name"));
        c.setFirstName(rs.getString("first_name"));
        return c;
    }

    public JsonObject toJson(){ //convert the form to json
        return Json.createObjectBuilder()
                   .add("id", getCustomerId())
                   .add("company", getCompany())
                   .add("last_name", getLastName())
                   .add("first_name", getFirstName())
                   .build();

    }
    
    
}
