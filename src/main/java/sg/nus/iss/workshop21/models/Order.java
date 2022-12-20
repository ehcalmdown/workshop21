package sg.nus.iss.workshop21.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {
    private Integer id;
    private Customer c;
    private DateTime orderDate;
    private DateTime shippedDate;
    private String shipName;
    private Double shippingFee;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Customer getC() {
        return c;
    }
    public void setC(Customer c) {
        this.c = c;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public DateTime getShippedDate() {
        return shippedDate;
    }
    public void setShippedDate(DateTime shippedDate) {
        this.shippedDate = shippedDate;
    }
    public String getShipName() {
        return shipName;
    }
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    public Double getShippingFee() {
        return shippingFee;
    }
    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public static Order create (SqlRowSet rs){ //customer model 
        Customer c = new Customer();
        Order o = new Order();
        c.setCustomerId(rs.getInt("customer_id"));
        c.setCompany(rs.getString("company"));
        c.setLastName(rs.getString("last_name"));
        c.setFirstName(rs.getString("first_name"));
        c.setEmailAddress(rs.getString("email_address"));
        c.setJobTitle(rs.getString("job_title"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setHomePhone(rs.getString("home_phone"));
        c.setMobilePhone(rs.getString("mobile_phone"));
        c.setAddress(rs.getString("address"));
        c.setStateProvince(rs.getString("state_province"));
        o.setC(c);
        o.setId(rs.getInt("order_id"));
        o.setOrderDate(new DateTime(//date time formatting
            DateTimeFormat.forPattern("dd/MM/yyyy")
                                            .parseDateTime(rs.getString("order_date"))));
        if(rs.getString("shipped_date") != null)
        o.setShippedDate(DateTimeFormat.forPattern("dd/MM/yyyy")
                    .parseDateTime(rs.getString("shipped_date")));
                    o.setShipName(rs.getString("ship_name"));
                    o.setShippingFee(rs.getDouble("shipping_fee"));
            
            return o;
    }


    // private Integer id;
    // private Customer c;
    // private DateTime orderDate;
    // private DateTime shippedDate;
    // private String shipName;
    // private Double shippingFee;
    public JsonObject toJson(){
        return Json.createObjectBuilder()
                   .add("order_id", getId())
                   .add("order_date", getOrderDate() != null ? getOrderDate().toString() : "")// simplified if else statement
                   .add("ship_name", getShipName())
                   .add("shipped_date", getShippedDate() != null ? getShippedDate().toString(): "")
                   .add("shipping_fee", getShippingFee())
                   .add("customer_id", getC().getCustomerId().toString()) 
                   .build();

    }


    
    
}
