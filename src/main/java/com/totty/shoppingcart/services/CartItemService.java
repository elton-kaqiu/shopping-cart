package com.totty.shoppingcart.services;

import com.totty.shoppingcart.entities.CartItem;

public interface CartItemService {
    void addCartItem(Long cartId, Long productId, Integer quantity);

    void removeCartItem(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, Integer quantity);

}
