package test;

import engine.dataDriven.ReadExcel;
import engine.logger.CustomLogger;
import io.qameta.allure.Epic;
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

public class Tests extends BaseTests {
    HomePage home;
    Inv inv;
    Product product;
    Checkout checkout;

    //tests
    @Epic("Single tests")
    @Test(dataProvider = "sorter")
    public void assertSorter(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Double.parseDouble(data.get("linkoccur")));
        ArrayList<String> proName = inv.getProductsNames();
        CustomLogger.logger.info(proName);
        inv.chooseSorter(Double.parseDouble(data.get("sorter")));
        ArrayList<String> prodName = inv.getProductsNames();
        CustomLogger.logger.info(prodName);
        Assert.assertNotEquals(prodName, proName);
    }

    @Epic("Single tests")
    @Test(dataProvider = "limiter")
    public void assertLimiter(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Double.parseDouble(data.get("linkoccur")));
        int countB = inv.countProducts();
        CustomLogger.logger.info(countB);
        inv.chooseLimiter(Double.parseDouble(data.get("limiter")));
        int countA = inv.countProducts();
        CustomLogger.logger.info(countA);
        Assert.assertNotEquals(countA, countB);
    }

    @Epic("Single tests")
    @Test(dataProvider = "filter")
    public void assertFilters(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Double.parseDouble(data.get("linkoccur")));
        ArrayList<String> proName = inv.getProductsNames();
        CustomLogger.logger.info(proName);
        inv.clickOnFilter("XS");
        ArrayList<String> prodName = inv.getProductsNames();
        CustomLogger.logger.info(prodName);
        Assert.assertTrue(inv.checkFiltersApplied());
    }

    @Epic("Single tests")
    @Test(dataProvider = "compare")
    public void checkComparePage(Map<String, String> data) {
        home.
                navigationToWebSite()
                .navigateBetweenLinks(data.get("link"), Double.parseDouble(data.get("linkoccur")));
        inv.clickCertainProductCompare(Double.parseDouble(data.get("firstcompareitem")))
                .clickCertainProductCompare(Double.parseDouble(data.get("secondcompareitem")))
                .countComparedItems();
        inv.clickOnCompare();
        Assert.assertTrue(inv.checkComparePage());
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
