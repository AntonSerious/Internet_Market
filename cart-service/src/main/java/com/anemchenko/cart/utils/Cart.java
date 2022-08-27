package com.anemchenko.cart.utils;

import com.anemchenko.api.dtos.OrderItemDto;
import com.anemchenko.api.dtos.ProductDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private double totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto product) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(product.getId())) {
                o.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new OrderItemDto(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }
    public void decrement(Long productId) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto i = iter.next();
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }
    public void remove(Long productId) {
        items.removeIf(i->i.getProductId().equals(productId));
        recalculate();
    }
    public void clear(){
        items.clear();
        totalPrice = 0;
    }

    private void recalculate(){
        totalPrice = 0;
        for(OrderItemDto oid : items){
            totalPrice += oid.getPrice();
        }
    }
    public void merge(Cart anotherCart){
        for(OrderItemDto anotherItem : anotherCart.items){
            boolean merged = false;
            for(OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
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
    public void delete(Long id) {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getProductId().equals(id)){
                items.remove(i);
                recalculate();
                return;
            }
        }
    }




}
