package com.ttulka.ecommerce.sales.product;

import com.ttulka.ecommerce.sales.category.CategoryId;

import lombok.ToString;

/**
 * Null object implementation for Product entity.
 */
@ToString
public final class UnknownProduct implements Product {

    @Override
    public ProductId id() {
        return new ProductId(0);
    }

    @Override
    public Code code() {
        return new Code("000");
    }

    @Override
    public Title title() {
        return new Title("unknown product");
    }

    @Override
    public Description description() {
        return new Description("This product is not to be found.");
    }

    @Override
    public Price price() {
        return new Price(0.0F);
    }

    @Override
    public void changeTitle(Title title) {
        // do nothing
    }

    @Override
    public void changeDescription(Description description) {
        // do nothing
    }

    @Override
    public void changePrice(Price price) {
        // do nothing
    }

    @Override
    public void putForSale() {
        // do nothing
    }

    @Override
    public void categorize(CategoryId categoryId) {
        // do nothing
    }
}
