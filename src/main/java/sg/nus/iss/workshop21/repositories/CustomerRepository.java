package sg.nus.iss.workshop21.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import jakarta.websocket.server.PathParam;
import sg.nus.iss.workshop21.models.Customer;
import sg.nus.iss.workshop21.models.CustomerRowMapper;
import sg.nus.iss.workshop21.models.Order;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate template;

//get all customer records, param offset, param limit, return

public List<Customer>getAllCustomers(Integer offset, Integer limit){
    //prevent inheritance 
    final List<Customer> c = new LinkedList<>();
    //query
    final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS, offset, limit);

    while (rs.next()) {
        c.add(Customer.create(rs));
    }
    return c;
}

/**
 * Get single customer record by Id
 * 
 * @param id
 * @return
 */
public Customer findCustomerById(Integer id) {
    List<Customer> custs = template.query(SQL_SELECT_CUSTOMERS_BY_ID, new CustomerRowMapper(),
            new Object[] { id });
    return (Customer) custs.get(0);
}

public List<Order> getCustomersOrder(Integer id) {
    // prevent inheritance
    final List<Order> orders = new LinkedList<>();

    // perform the query

    final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS_ORDER, id);

    while (rs.next()) {
        orders.add(Order.create(rs));
    }
    return orders;
}
    
}
