// Write REST controller(s) to process the following HTTP requests
//  Get a list of all customers
// GET /api/customers
// Accept: application/json
// This HTTP endpoint supports the following parameters
//  offset - return the first result from n records from the first; n is the 
// number given by offset parameter
//  limit - return the number of records specified by limit
// The default value for offset is 0 and limit is 5
//  Get the details of a customer with the customer’s id
// GET /api/customer/<customer_id>
// Accept: application/json
// Return a 404 and an appropriate error object if the customer does not exist
//  Get all orders for a customer
// GET /api/customer/<customer_id>/orders
// Accept: application/json
// This endpoint returns an array of orders in JSON. If the customer does not 
// have any orders, the endpoint should return an empty array
// Return a 404 and an appropriate error object if the customer does not exist.


package sg.nus.iss.workshop21.controllers;

public class NwRestController {
    
}
