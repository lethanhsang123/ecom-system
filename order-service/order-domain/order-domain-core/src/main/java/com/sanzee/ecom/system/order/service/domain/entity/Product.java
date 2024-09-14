package com.sanzee.ecom.system.order.service.domain.entity;

import com.sanzee.ecom.system.domain.entity.BaseEntity;
import com.sanzee.ecom.system.domain.valueobject.Money;
import com.sanzee.ecom.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

    private String name;
    private Money price;

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    private Product(Builder builder) {
        this.setId(builder.productId);
        this.name = builder.name;
        this.price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProductId productId;
        private String name;
        private Money price;

        private Builder() {}

        public Builder id(ProductId id) {
            this.productId = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }

    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
