package com.anemchenko.utils;

import com.anemchenko.dto.OrderItemDto;
import com.anemchenko.dto.ProductDto;
import com.anemchenko.model.OrderItem;
import com.anemchenko.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.lang.model.element.Element;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal price;

    public Cart(){
        this.items = new ArrayList<>();
        this.price = BigDecimal.ZERO;
    }

    public boolean add(Long productId){
        for(OrderItemDto o : items){
            if(o.getProductId().equals(productId)){
                o.incrementQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }
    public void clear(){
        items.clear();
        price = BigDecimal.ZERO;
    }


    public void add(Product product){

        items.add(new OrderItemDto(product));
        recalculate();
    }
    private void recalculate(){
        price = BigDecimal.ZERO;
        for(OrderItemDto oid : items){
            price = price.add(oid.getPrice());
        }
    }

    public void delete(Long id) {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getProductId().equals(id)){
                items.remove(i);
                recalculate();
                return;
            }
        }
    }

    public void remove(Long id) {
        for(OrderItemDto o : items){
            if(o.getProductId().equals(id)){
                o.incrementQuantity(-1);
                recalculate();
            }
        }
    }
    public void decrement(Long productId) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto i = iter.next();
            if (i.getProductId().equals(productId)) {
                i.incrementQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }
    public void merge(Cart anotherCart){
        for(OrderItemDto anotherItem : anotherCart.items){
            boolean merged = false;
            for(OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.incrementQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if(!merged){
                items.add(anotherItem);
            }
        }
        recalculate();
        anotherCart.clear();
    }
}
