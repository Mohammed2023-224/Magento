package test;

import engine.dataDriven.ReadExcel;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Checkout;
import pages.HomePage;
import pages.Inv;
import pages.Product;

import java.util.Map;

public class endtoendTests extends BaseTests {
    HomePage home;
    Inv inv;
    Product product;
    Checkout checkout;

    @Epic("End to End")
    @Test(dataProvider = "endToend")
    public void endToEnd(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Integer.parseInt(data.get("linkoccur")));
        inv.clickCertainProduct(data.get("productname"));
        product.typeQuantity(data.get("quantity"))
                .chooseSize(data.get("size"))
                .chooseColor(data.get("color"))
                .clickAddToCart()
                .clickOnCart()
                .clickOnCheckOut();
        checkout
                .typeNameField(data.get("name"))
                .typeLastNameField(data.get("lastname"))
                .typeStreetField(data.get("street"))
                .typeEmailField(data.get("email"))
                .typeCityField(data.get("city"))
                .typePostalCodeField(data.get("postal"))
                .chooseCountry(data.get("country"), 2)
                .typePhoneField(data.get("phone"))
                .chooseDelivery()
                .clickNextButton()
                .clickPlaceOrderButton()
                .checkOrder();
        String orderNumber = checkout.getOrderNumber();
        Assert.assertNotEquals(orderNumber, "");
    }

    @Epic("First")
    @DataProvider(name = "endToend")
    private Object[] data() {
        ReadExcel r = new ReadExcel();
        return r.readDataHashMapByRowCondition(testData, "EndToEnd", "LinkScript", "lnk0001");
    }

    @BeforeClass
    private void initObjetcs() {
        home = new HomePage(driver);
        inv = new Inv(driver);
        product = new Product(driver);
        checkout = new Checkout(driver);
    }
}
