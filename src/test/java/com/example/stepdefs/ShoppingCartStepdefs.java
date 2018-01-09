package com.example.stepdefs;

import com.example.cart.CartApplication;
import com.example.cart.model.ProductDetail;
import com.example.cart.repo.ProductRepository;
import com.example.cart.service.ShoppingCartService;
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
        lst.forEach(productData -> productRepository.add(productData.getName(),productData.getPrice()));
    }

    @When("^user added (\\d+) \"([^\"]*)\" to Cart$")
    public void userAddedToCart(int quantity, String productName) throws Throwable {
        shoppingCartService.addProduct(productName,quantity);
    }


    @Then("^shopping cart should contain (\\d+) \"([^\"]*)\" with price of \"([^\"]*)\"$")
    public void shoppingCartShouldContainWithPriceOf(int quantity, String productName, String price) throws Throwable {
        List<ProductDetail> lstData = shoppingCartService.getProductDetail();
        Assert.assertFalse(lstData.isEmpty());
        Optional<ProductDetail> detail = lstData.stream().filter(productDetail1 -> productDetail1.getProduct().getProductName().equals(productName)).findFirst();
        Assert.assertTrue(detail.isPresent());
        ProductDetail productDetail = detail.get();
        Assert.assertEquals(productName,productDetail.getProduct().getProductName());
        Assert.assertEquals(quantity,productDetail.getQuantity());
        Assert.assertEquals(new BigDecimal(price),productDetail.getProduct().getPrice());
    }

    @And("^shopping cart total price is \"([^\"]*)\"$")
    public void shoppingCartTotalPriceIs(String totalPrice) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(new BigDecimal(totalPrice),shoppingCartService.getTotalAmount());
    }
}

class ProductData {

    private String name;
    private BigDecimal price;

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
}
