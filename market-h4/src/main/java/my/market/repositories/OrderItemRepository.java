package my.market.repositories;

import my.market.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    OrderItem save(OrderItem orderItem);
}
