package com.totty.shoppingcart.services.impls;

import com.totty.shoppingcart.entities.Cart;
import com.totty.shoppingcart.entities.CartItem;
import com.totty.shoppingcart.exceptions.CartNotFoundException;
import com.totty.shoppingcart.repositories.CartItemRepository;
import com.totty.shoppingcart.repositories.CartRepository;
import com.totty.shoppingcart.services.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("Cart not found!"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = this.getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = this.getCart(id);
        return cart.getItems()
                .stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


    }

    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return cartRepository.save(newCart).getId();
    }
}
