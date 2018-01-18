package com.example.stepdefs;

import com.example.cart.CartApplication;
import com.example.cart.model.ProductDetail;
import com.example.cart.offer.Buy2Get1Offer;
import com.example.cart.offer.Buy2Get50OnNextOffer;
import com.example.cart.offer.Offer;
import com.example.cart.repo.ProductRepository;
import com.example.cart.service.ShoppingCartService;
import com.example.cart.tax.SimpleTaxCalculator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ContextConfiguration
@SpringBootTest(classes = CartApplication.class)
public class ShoppingCartStepdefs {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductRepository productRepository;

    @Given("^shopping cart is empty$")
    public void shoppingCartIsEmpty() throws Throwable {
        shoppingCartService.clearShoppingCart();
        Assert.assertTrue(shoppingCartService.getProductDetail().isEmpty());
    }

    @And("^below are the available product$")
    public void belowAreTheAvailableProduct(List<ProductData> lst) throws Throwable {
        lst.forEach(productData -> productRepository.add(productData.getName(), productData.getPrice(), productData.getOfferForProduct()));
    }

    @When("^user added (\\d+) \"([^\"]*)\" to Cart$")
    public void userAddedToCart(int quantity, String productName) throws Throwable {
        shoppingCartService.addProduct(productName, quantity);
    }


    @Then("^shopping cart should contain (\\d+) \"([^\"]*)\" with price of \"([^\"]*)\"$")
    public void shoppingCartShouldContainWithPriceOf(int quantity, String productName, String price) throws Throwable {
        List<ProductDetail> lstData = shoppingCartService.getProductDetail();
        Assert.assertFalse(lstData.isEmpty());
        Optional<ProductDetail> detail = lstData.stream().filter(productDetail1 -> productDetail1.getProduct().getProductName().equals(productName)).findFirst();
        Assert.assertTrue(detail.isPresent());
        ProductDetail productDetail = detail.get();
        Assert.assertEquals(productName, productDetail.getProduct().getProductName());
        Assert.assertEquals(quantity, productDetail.getQuantity());
        Assert.assertEquals(new BigDecimal(price), productDetail.getProduct().getPrice());
    }

    @And("^shopping cart total price is \"([^\"]*)\"$")
    public void shoppingCartTotalPriceIs(String totalPrice) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(new BigDecimal(totalPrice), shoppingCartService.getTotalAmount());
    }

    @And("^Tax rate is \"([^\"]*)\"$")
    public void taxRateIs(String taxRate) throws Throwable {
        shoppingCartService.setTaxCalculator(new SimpleTaxCalculator(new BigDecimal(taxRate)));
    }

    @And("^shopping cart tax amount is \"([^\"]*)\"$")
    public void shoppingCartTaxAmountIs(String taxamount) throws Throwable {
        Assert.assertEquals(new BigDecimal(taxamount), shoppingCartService.getTaxAmount());
    }

    @And("^shopping cart total discount is \"([^\"]*)\"$")
    public void shoppingCartTotalDiscountIs(String discount) throws Throwable {
        Assert.assertEquals(new BigDecimal(discount), shoppingCartService.getDiscountAmount());
    }
}

class ProductData {

    private String name;
    private BigDecimal price;

    private String offer;

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Offer getOfferForProduct() {
        if (offer == null)
            return new Offer();
        else if (offer.equals("BUY2_GET1"))
            return new Buy2Get1Offer();
        else if (offer.equals("BUY2_GET50OnNext"))
            return new Buy2Get50OnNextOffer();
        else
            return new Offer();
    }

}
