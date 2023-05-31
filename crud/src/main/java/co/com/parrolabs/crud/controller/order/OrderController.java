package co.com.parrolabs.crud.controller.order;

import co.com.parrolabs.crud.controller.dto.OrderResponse;
import co.com.parrolabs.crud.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderFacade orderFacade;

    @Autowired
    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderResponse orderResponse) {
        return orderFacade.createOrder(orderResponse);
    }

    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderNumber) {
         return orderFacade.deleteOrder(orderNumber) ;
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("orderNumber") String id) {
       return orderFacade.getOrderById(id);
    }
}
