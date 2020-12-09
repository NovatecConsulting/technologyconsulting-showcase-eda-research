package de.novatec;

import java.util.logging.Logger;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.Cardinality;
import com.microsoft.azure.functions.annotation.EventHubTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Function {
    @FunctionName("OrderPlacedConsumer")
    public void run(
            @EventHubTrigger(
                name = "order",
                consumerGroup = "$Default",
                eventHubName = "",
                connection = "CONNECTION_STRING",
                cardinality = Cardinality.ONE) Order order,
            final ExecutionContext context) {
        Logger log = context.getLogger();
        log.info("OrderPlacedConsumer received event from event hub " + order.toString());
        
        log.info("Fetching customer with id " + order.getCustomerId() + " from container");
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Customer> customerResponse = restTemplate.getForEntity("http://tc-eda-iac-prod-customer-consumer-container.westeurope.azurecontainer.io/customers/" + order.getCustomerId(), Customer.class);

        if(customerResponse.getStatusCode() != HttpStatus.OK) {
            log.warning("Couldn't fetch customer with id " + order.getCustomerId() + "; ErrorCode=" + customerResponse.getStatusCode().value());
        } else {
            Customer customer = customerResponse.getBody();
            log.info("Customer " + customer.toString() + " ordered " + order.toString());
        }
    }
}
