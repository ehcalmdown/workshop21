package sg.nus.iss.workshop21.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer>{

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("id"));
        c.setCompany("company");
        c.setLastName(rs.getString("last_name"));
        c.setFirstName(rs.getString("first_name"));
        c.setEmailAddress(rs.getString("email_address"));
        c.setJobTitle(rs.getString("job_title"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setHomePhone(rs.getString("home_phone"));
        c.setMobilePhone(rs.getString("mobile_phone"));
        c.setAddress(rs.getString("address"));
        c.setStateProvince(rs.getString("state_province"));
        return c;
    } //An interface used by JdbcTemplate for mapping rows of a ResultSet on a per-row basis. 
    
    
}
