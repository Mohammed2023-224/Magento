import engine.dataDriven.ReadExcel;
import engine.logger.CustomLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Checkout;
import pages.HomePage;
import pages.Inv;
import pages.Product;

import java.util.ArrayList;
import java.util.Map;

public class test extends BaseTests {
    HomePage home;
    Inv inv;
    Product product;
    Checkout checkout;

    @Test(dataProvider = "sorter")
    public void assertSorter(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Integer.parseInt(data.get("linkoccur")));
        ArrayList<String> proName = inv.getProductsNames();
        CustomLogger.logger.info(proName);
        inv.chooseSorter(Integer.parseInt(data.get("sorter")));
        ArrayList<String> prodName = inv.getProductsNames();
        CustomLogger.logger.info(prodName);
        Assert.assertNotEquals(prodName, proName);
    }

    @Test(dataProvider = "limiter")
    public void assertLimiter(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Integer.parseInt(data.get("linkoccur")));
        int countB = inv.countProducts();
        CustomLogger.logger.info(countB);
        inv.chooseLimiter(Integer.parseInt(data.get("limiter")));
        int countA = inv.countProducts();
        CustomLogger.logger.info(countA);
        Assert.assertNotEquals(countA, countB);
    }

    @Test(dataProvider = "filter")
    public void assertFilters(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Integer.parseInt(data.get("linkoccur")));
        ArrayList<String> proName = inv.getProductsNames();
        CustomLogger.logger.info(proName);
        inv.clickOnFilter("XS");
        ArrayList<String> prodName = inv.getProductsNames();
        CustomLogger.logger.info(prodName);
        Assert.assertTrue(inv.checkFiltersApplied());
    }


    @Test(dataProvider = "compare", enabled = false)
    public void checkComparePage(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Integer.parseInt(data.get("linkoccur")));
        inv.clickCertainProductCompare(Integer.parseInt(data.get("firstcompareitem")))
                .clickCertainProductCompare(Integer.parseInt(data.get("secondcompareitem")))
                .checkComparedItems();
        inv.clickOnCompare();
        Assert.assertTrue(inv.checkComparePage());
    }

    @Test(dataProvider = "endToend", enabled = false)
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


    @DataProvider(name = "endToend")
    private Object[] data() {
        ReadExcel r = new ReadExcel();
        return r.readDataHashMapByRowCondition(testData, "EndToEnd", "LinkScript", "lnk0001");
    }

    @DataProvider(name = "compare")
    private Object[] compareData() {
        ReadExcel r = new ReadExcel();
        return r.readDataHashMapByRowCondition(testData, "EndToEnd", "LinkScript", "lnk0002");
    }

    @DataProvider(name = "limiter")
    private Object[] limiterData() {
        ReadExcel r = new ReadExcel();
        return r.readDataHashMapByRowCondition(testData, "EndToEnd", "LinkScript", "lnk0003");
    }

    @DataProvider(name = "filter")
    private Object[] filterData() {
        ReadExcel r = new ReadExcel();
        return r.readDataHashMapByRowCondition(testData, "EndToEnd", "LinkScript", "lnk0004");
    }

    @DataProvider(name = "sorter")
    private Object[] sorterData() {
        ReadExcel r = new ReadExcel();
        return r.readDataHashMapByRowCondition(testData, "EndToEnd", "LinkScript", "lnk0005");
    }

    @BeforeClass
    private void initObjetcs() {
        home = new HomePage(driver);
        inv = new Inv(driver);
        product = new Product(driver);
        checkout = new Checkout(driver);
    }
}
