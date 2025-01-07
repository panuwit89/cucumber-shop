package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I have {int} {string} in stock")
    public void i_have_stock(int stock, String name) {
        Product product = catalog.getProduct(name);
        int productStock = product.getStock();
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) throws NotEnoughBalanceException {
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);
    }

    @When("I buy too many {string} with quantity {int}")
    public void i_buy_too_many_quantity(String name, int quantity) throws NotEnoughBalanceException {
        Product prod = catalog.getProduct(name);
        assertThrows(NotEnoughBalanceException.class,
                () -> order.addItem(prod, quantity));
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Then("I should have {int} {string} in stock")
    public void i_should_have_stock(int stockQuantity, String name) {
        Product prod = catalog.getProduct(name);
        assertEquals(stockQuantity, prod.getStock());
    }

    @Then("the stock have {int} {string} Not Enough")
    public void the_stock_has_not_enough_sorry(int stockQuantity, String name) {
        Product prod = catalog.getProduct(name);
        assertEquals(stockQuantity, prod.getStock());
    }
}

