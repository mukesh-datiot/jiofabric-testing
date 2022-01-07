
package com.jiofabric.automation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Collection_TestCases {

    public static WebDriver driver;
    public static WebDriverWait wait;


    @BeforeClass
    public static void beforeClassSetup() {
        System.out.println("beforeClassSetup");
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }


    @AfterClass
    public static void afterClassSetup() {
        System.out.println("afterClassSetup");

    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @After
    public void tearDown() {
        driver.close();
    }


    @Test
    public void a_addCollection() throws Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Collection");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            addCollection(dataMap);
        }
    }


    public void addCollection(Map<String, String> dataMap) throws Exception{
        String selectedField = "";
        String type = "";
        String name = "";
        String items = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equalsIgnoreCase("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("type")) {
                type = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("name")) {
                name = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("items")) {
                items = dataMap.get(s);
            }
        }

         wait = new WebDriverWait(driver, 50);
        WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        selectCollection.click();
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        Select selectType = new Select(driver.findElement(By.cssSelector("[data-test='test-select']")));
        selectType.selectByValue(type);

        WebElement addName = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']"));
        addName.sendKeys(name);
        WebElement addItems = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
        addItems.sendKeys(items);
        addItems.sendKeys(Keys.ENTER);

        WebElement saveDetailButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        saveDetailButton.click();
        WebElement createMessageOnAddUnit = driver.findElement(By.id("swal2-title"));
        System.out.println(createMessageOnAddUnit.getText());
        Assert.assertEquals("Collection saved successfully", createMessageOnAddUnit.getText());
        Thread.sleep(5000);
        WebElement editCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
        editCollection.click();
        String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
        Assert.assertEquals(vertical, selectedField);
        String typeVerify = driver.findElement(By.cssSelector("[data-test='test-select']")).getAttribute("value");
        Assert.assertEquals(typeVerify, type);
        String name1Verify = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']")).getAttribute("value");
        Assert.assertEquals(name1Verify, name);
        WebElement item = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
        String aliases1Verify = item.getText().replaceAll("\\n",",");

        List<String> itemExcel = Arrays.asList(items.split(","));
        System.out.println("Item1 " +itemExcel);
        List<String> itemUi = Arrays.asList(aliases1Verify.split(","));
        System.out.println("items2 " +itemUi);
        System.out.println("is equal" + listEqualsIgnoreOrder(itemExcel, itemUi));
        Assert.assertTrue(listEqualsIgnoreOrder(itemExcel, itemUi));

        String isSelected = driver.findElement(By.xpath("//input[@name='item.ordered']")).getAttribute("value");
        System.out.println("IsOrderedSelected " + isSelected);
        Assert.assertEquals(isSelected, "false");

        String isSelectedModulo = driver.findElement(By.xpath("//input[@name='item.modulo']")).getAttribute("value");
        System.out.println("IsModuloSelected " + isSelectedModulo);
        Assert.assertEquals(isSelectedModulo, "false");

    }


    @Test
    public void a_addOrderedCollection() throws Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Collection");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            addOrderedCollection(dataMap);
        }
    }


    public void addOrderedCollection(Map<String, String> dataMap) throws Exception{
        String selectedField = "";
        String type = "";
        String name = "";
        String items = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equalsIgnoreCase("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("type")) {
                type = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("name")) {
                name = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("items")) {
                items = dataMap.get(s);
            }
        }

        wait = new WebDriverWait(driver, 50);
        WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        selectCollection.click();
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        Select selectType = new Select(driver.findElement(By.cssSelector("[data-test='test-select']")));
        selectType.selectByValue(type);

        WebElement addName = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']"));
        addName.sendKeys(name);
        WebElement addItems = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
        addItems.sendKeys(items);
        addItems.sendKeys(Keys.ENTER);

        WebElement markOrdered = driver.findElement(By.xpath("//input[@name='item.ordered']"));
        markOrdered.click();

        WebElement saveDetailButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        saveDetailButton.click();
        WebElement createMessageOnAddUnit = driver.findElement(By.id("swal2-title"));
        System.out.println(createMessageOnAddUnit.getText());
        Assert.assertEquals("Collection saved successfully", createMessageOnAddUnit.getText());
        Thread.sleep(5000);
        WebElement editCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
        editCollection.click();
        String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
        Assert.assertEquals(vertical, selectedField);
        String typeVerify = driver.findElement(By.cssSelector("[data-test='test-select']")).getAttribute("value");
        Assert.assertEquals(typeVerify, type);
        String name1Verify = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']")).getAttribute("value");
        Assert.assertEquals(name1Verify, name);
        WebElement item = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
        String aliases1Verify = item.getText().replaceAll("\\n",",");

        List<String> itemExcel = Arrays.asList(items.split(","));
        System.out.println("Item1 " +itemExcel);
        List<String> itemUi = Arrays.asList(aliases1Verify.split(","));
        System.out.println("items2 " +itemUi);
        System.out.println("is equal" + listEqualsIgnoreOrder(itemExcel, itemUi));
        Assert.assertTrue(listEqualsIgnoreOrder(itemExcel, itemUi));

        String isSelected = driver.findElement(By.xpath("//input[@name='item.ordered']")).getAttribute("value");
        System.out.println("IsSelected " + isSelected);
        Assert.assertEquals(isSelected, "true");
    }


    @Test
    public void a_addModuloCollection() throws Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Collection");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            addModuloCollection(dataMap);
        }
    }


    public void addModuloCollection(Map<String, String> dataMap) throws Exception{
        String selectedField = "";
        String type = "";
        String name = "";
        String items = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equalsIgnoreCase("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("type")) {
                type = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("name")) {
                name = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("items")) {
                items = dataMap.get(s);
            }
        }

        wait = new WebDriverWait(driver, 50);
        WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        selectCollection.click();
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        Select selectType = new Select(driver.findElement(By.cssSelector("[data-test='test-select']")));
        selectType.selectByValue(type);

        WebElement addName = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']"));
        addName.sendKeys(name);
        WebElement addItems = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
        addItems.sendKeys(items);
        addItems.sendKeys(Keys.ENTER);

        WebElement markModulo = driver.findElement(By.xpath("//input[@name='item.modulo']"));
        markModulo.click();

        WebElement saveDetailButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        saveDetailButton.click();
        WebElement createMessageOnAddUnit = driver.findElement(By.id("swal2-title"));
        System.out.println(createMessageOnAddUnit.getText());
        Assert.assertEquals("Collection saved successfully", createMessageOnAddUnit.getText());
        Thread.sleep(5000);
        WebElement editCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
        editCollection.click();
        String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
        Assert.assertEquals(vertical, selectedField);
        String typeVerify = driver.findElement(By.cssSelector("[data-test='test-select']")).getAttribute("value");
        Assert.assertEquals(typeVerify, type);
        String name1Verify = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']")).getAttribute("value");
        Assert.assertEquals(name1Verify, name);
        WebElement item = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
        String aliases1Verify = item.getText().replaceAll("\\n",",");

        List<String> itemExcel = Arrays.asList(items.split(","));
        System.out.println("Item1 " +itemExcel);
        List<String> itemUi = Arrays.asList(aliases1Verify.split(","));
        System.out.println("items2 " +itemUi);
        System.out.println("is equal" + listEqualsIgnoreOrder(itemExcel, itemUi));
        Assert.assertTrue(listEqualsIgnoreOrder(itemExcel, itemUi));

        String isSelected = driver.findElement(By.xpath("//input[@name='item.modulo']")).getAttribute("value");
        System.out.println("IsSelected " + isSelected);
        Assert.assertEquals(isSelected, "true");
    }


    @Test
    public void a_addCollectionWithOrderedAndModuloCheckbox() throws Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Collection");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            addCollectionWithOrderedAndModuloCheckbox(dataMap);
        }
    }


    public void addCollectionWithOrderedAndModuloCheckbox(Map<String, String> dataMap) throws Exception{
        String selectedField = "";
        String type = "";
        String name = "";
        String items = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equalsIgnoreCase("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("type")) {
                type = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("name")) {
                name = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("items")) {
                items = dataMap.get(s);
            }
        }

        wait = new WebDriverWait(driver, 50);
        WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        selectCollection.click();
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        Select selectType = new Select(driver.findElement(By.cssSelector("[data-test='test-select']")));
        selectType.selectByValue(type);

        WebElement addName = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']"));
        addName.sendKeys(name);
        WebElement addItems = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
        addItems.sendKeys(items);
        addItems.sendKeys(Keys.ENTER);

        WebElement markOrdered = driver.findElement(By.xpath("//input[@name='item.ordered']"));
        markOrdered.click();

        WebElement markModulo = driver.findElement(By.xpath("//input[@name='item.modulo']"));
        markModulo.click();

        WebElement saveDetailButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        saveDetailButton.click();
        WebElement createMessageOnAddUnit = driver.findElement(By.id("swal2-title"));
        System.out.println(createMessageOnAddUnit.getText());
        Assert.assertEquals("Collection saved successfully", createMessageOnAddUnit.getText());
        Thread.sleep(5000);
        WebElement editCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
        editCollection.click();
        String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
        Assert.assertEquals(vertical, selectedField);
        String typeVerify = driver.findElement(By.cssSelector("[data-test='test-select']")).getAttribute("value");
        Assert.assertEquals(typeVerify, type);
        String name1Verify = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']")).getAttribute("value");
        Assert.assertEquals(name1Verify, name);
        WebElement item = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
        String aliases1Verify = item.getText().replaceAll("\\n",",");

        List<String> itemExcel = Arrays.asList(items.split(","));
        System.out.println("Item1 " +itemExcel);
        List<String> itemUi = Arrays.asList(aliases1Verify.split(","));
        System.out.println("items2 " +itemUi);
        System.out.println("is equal" + listEqualsIgnoreOrder(itemExcel, itemUi));
        Assert.assertTrue(listEqualsIgnoreOrder(itemExcel, itemUi));

        String isSelectedOrdered = driver.findElement(By.xpath("//input[@name='item.ordered']")).getAttribute("value");
        System.out.println("IsOrderedSelected " + isSelectedOrdered);
        Assert.assertEquals(isSelectedOrdered, "true");

        String isSelectedModulo = driver.findElement(By.xpath("//input[@name='item.modulo']")).getAttribute("value");
        System.out.println("IsModuloSelected " + isSelectedModulo);
        Assert.assertEquals(isSelectedModulo, "true");

    }

    public static  boolean listEqualsIgnoreOrder(List<String> list1, List<String> list2) {
        return new HashSet<String>(list1).equals(new HashSet<String>(list2));
    }


    @Test
    public void clickingOnBackButton() throws Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Collection");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            checkingBackButton(dataMap);
        }
    }

    public  void checkingBackButton(Map<String, String> dataMap) throws InterruptedException, Exception {
        String selectedField = "";
        String type = "";
        String name = "";
        String items = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equalsIgnoreCase("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("type")) {
                type = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("name")) {
                name = dataMap.get(s);
            }
            if (s.equalsIgnoreCase("items")) {
                items = dataMap.get(s);
            }
        }

        wait = new WebDriverWait(driver, 50);
        WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        selectCollection.click();
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        Select selectType = new Select(driver.findElement(By.cssSelector("[data-test='test-select']")));
        selectType.selectByValue(type);

        WebElement addName = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']"));
        addName.sendKeys(name);
        WebElement addItems = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
        addItems.sendKeys(items);
        addItems.sendKeys(Keys.ENTER);

        WebElement clickOnBackButton = driver.findElement(By.cssSelector("[class='btn btn-info ml-1']"));
        clickOnBackButton.click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[class='btn btn-primary']")).isDisplayed());
        wait = new WebDriverWait(driver, 50);
        boolean editCollectionButton  = (isElementPresent(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']")));
        Assert.assertEquals(editCollectionButton, false);
//        boolean editButton2 = (isElementPresent(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='delete-unit']")));
//        Assert.assertEquals(editButton2, false);

    }

    public static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void b_editUnit() throws Exception {
        String selectedField = "";
        String name = "";
        String updatedName = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Collection");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                //  System.out.println("s = " + s);
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
                if (s.equals("updatedName")) {
                    updatedName = dataMap.get(s);
                }
            }
            ip.close();

            WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
            selectCollection.click();
//            WebElement searchCollectionByName = driver.findElement(By.xpath("//*[@class='form-control']"));
//            searchCollectionByName.sendKeys(name);
            Thread.sleep(200);
            WebElement editCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
            editCollection.click();
//            WebElement editButton = driver.findElement(By.id("edit-collection"));
//            editButton.click();
          //  Thread.sleep(200);
            WebElement editName = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']"));
            editName.click();
            editName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            editName.clear();
            editName.sendKeys(updatedName);
            Thread.sleep(200);
            WebElement editUnits = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
            editUnits.click();

            Thread.sleep(200);
            WebElement editPopUpOnEditUnit = driver.findElement(By.id("swal2-title"));
            System.out.println(editPopUpOnEditUnit.getText());
            Assert.assertEquals("Collection saved successfully", editPopUpOnEditUnit.getText());
            Thread.sleep(200);
            WebElement clickOnBackButton = driver.findElement(By.cssSelector("[class='btn btn-info ml-1']"));
            clickOnBackButton.click();
            WebElement edtCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ updatedName+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
            edtCollection.click();

            String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
            Assert.assertEquals(vertical, selectedField);
            String name1Verify = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']")).getAttribute("value");
            Assert.assertEquals(name1Verify, updatedName);

        }
    }


    @Test
    public void b_editAddCollection() throws Exception {

        String selectedField = "";
        String type = "";
        String name = "";
        String items = "";
        String updatedName = "";

        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Collection");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            addCollection(dataMap);
        }


   //     dataList = read(wb, "Collection");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                //  System.out.println("s = " + s);
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
                if (s.equals("updatedName")) {
                    updatedName = dataMap.get(s);
                }
                if (s.equals("type")) {
                    type = dataMap.get(s);
                }
                if (s.equals("items")) {
                    items = dataMap.get(s);
                }
            }
            ip.close();

            WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
            selectCollection.click();
            WebElement editCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
            editCollection.click();
            WebElement editName = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']"));
            editName.click();
            editName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            editName.clear();
            editName.sendKeys(updatedName);
            Thread.sleep(200);
            WebElement editUnits = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
            editUnits.click();

            Thread.sleep(200);
            WebElement editPopUpOnEditUnit = driver.findElement(By.id("swal2-title"));
            System.out.println(editPopUpOnEditUnit.getText());
            Assert.assertEquals("Collection saved successfully", editPopUpOnEditUnit.getText());
            Thread.sleep(200);
            WebElement clickOnBackButton = driver.findElement(By.cssSelector("[class='btn btn-info ml-1']"));
            clickOnBackButton.click();
            WebElement edtCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ updatedName+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
            edtCollection.click();

            String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
            Assert.assertEquals(vertical, selectedField);
            String name1Verify = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']")).getAttribute("value");
            Assert.assertEquals(name1Verify, updatedName);

            String typeVerify = driver.findElement(By.cssSelector("[data-test='test-select']")).getAttribute("value");
            Assert.assertEquals(typeVerify, type);
            WebElement item = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
            String aliases1Verify = item.getText().replaceAll("\\n",",");

            List<String> itemExcel = Arrays.asList(items.split(","));
            System.out.println("Item1 " +itemExcel);
            List<String> itemUi = Arrays.asList(aliases1Verify.split(","));
            System.out.println("items2 " +itemUi);
            System.out.println("is equal" + listEqualsIgnoreOrder(itemExcel, itemUi));
            Assert.assertTrue(listEqualsIgnoreOrder(itemExcel, itemUi));

        }
    }

    @Test
    public void mandatoryFieldsAreEmptyWhileUploadingCollectionFile() throws Exception {
        WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        selectCollection.click();
        WebElement clickUploadButton = driver.findElement(By.cssSelector("[class='mx-1 btn btn-secondary']"));
        clickUploadButton.click();
        WebElement clickSubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        clickSubmitButton.click();
        Thread.sleep(500);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement errorMessage = driver.findElement(By.cssSelector("[class='alert-text font-weight-bold']"));
        System.out.println(errorMessage.getText());
        Assert.assertEquals("All fields are required", errorMessage.getText());

    }

    @Test
    public void checkingNavigationButton() throws Exception{
        WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        selectCollection.click();
        List<WebElement> rows = driver.findElements(By.xpath("*//table/tbody/tr"));
        int count = rows.size();
        System.out.println(count);
        if(count<10){
            WebElement navigationBackButton = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][1]"));
            Assert.assertTrue(navigationBackButton.isEnabled());

            WebElement navigationNextButton = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][2]"));
            Assert.assertFalse(navigationNextButton.isEnabled());
        }else {
            WebElement navigationBackButton = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][1]"));
            Assert.assertTrue(navigationBackButton.isEnabled());

            WebElement navigationNextButton = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][2]"));
            Assert.assertTrue(navigationNextButton.isEnabled());
            navigationNextButton.click();
            Assert.assertTrue(rows.size()>0);

            WebElement navigationButton1 = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][1]"));
            navigationButton1.click();
            Assert.assertTrue(navigationButton1.isEnabled());

            int count1 = rows.size();
            System.out.println(count1);
            Assert.assertEquals(count1,10);
        }

    }

    @Test
    public void d_deleteCollection() throws Exception {
        String selectedField = "";
        String name = "";
        String updatedName = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Collection");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                //  System.out.println("s = " + s);
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }
            }
            ip.close();

            WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
            selectCollection.click();
            Thread.sleep(200);
            WebElement deleteCollectionButton = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='delete-collection']"));
            deleteCollectionButton.click();
            Thread.sleep(200);
            WebElement deleteCollection = driver.findElement(By.cssSelector("[class='swal2-confirm swal2-styled']"));
            deleteCollection.click();
            Thread.sleep(200);
            WebElement popUpOnEditCollection = driver.findElement(By.id("swal2-title"));
            System.out.println(popUpOnEditCollection.getText());
            Assert.assertEquals("collection deleted successfully", popUpOnEditCollection.getText());
            Thread.sleep(3000);
            boolean deleteButton2 = (isElementPresent(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='delete-collection']")));
            Assert.assertEquals(deleteButton2, false);
            Thread.sleep(5000);
            for (Map<String, String> dataMap1 : dataList) {
                addCollection(dataMap1);
            }
        }
    }

    @Test
    public void d_createDeletedCollection() throws Exception {
        String selectedField = "";
        String name = "";
        String updatedName = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Collection");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                //  System.out.println("s = " + s);
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }
            }
            ip.close();

            WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
            selectCollection.click();
            Thread.sleep(200);
            WebElement deleteCollectionButton = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='delete-collection']"));
            deleteCollectionButton.click();
            Thread.sleep(200);
            WebElement deleteCollection = driver.findElement(By.cssSelector("[class='swal2-confirm swal2-styled']"));
            deleteCollection.click();
            Thread.sleep(200);
            WebElement popUpOnEditCollection = driver.findElement(By.id("swal2-title"));
            System.out.println(popUpOnEditCollection.getText());
            Assert.assertEquals("collection deleted successfully", popUpOnEditCollection.getText());
            Thread.sleep(3000);
            boolean deleteButton2 = (isElementPresent(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='delete-collection']")));
            Assert.assertEquals(deleteButton2, false);
        }


    }

    @Test
    public void searchCollectionByName()throws Exception {
        String name = "";
        String selectedField = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Collection");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }

            }
            ip.close();


            WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
            selectCollection.click();
            WebElement searchUnitName = driver.findElement(By.xpath("//*[@class='form-control']"));
            searchUnitName.click();
            searchUnitName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            searchUnitName.clear();
            searchUnitName.sendKeys(name);
        //    boolean deleteButton2 = (isElementPresent(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='delete-collection']")));

            Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='delete-collection']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='delete-collection']")).isDisplayed());
        }
    }


    @Test
    public void clickingOnOneOfTheCorrespondingItems() throws Exception {

        String selectedField = "";
        String name = "";
        String type = "";
        String items = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\jioFabricAutomation\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Collection");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                if (s.equals("type")) {
                    type = dataMap.get(s);
                }
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }

            }
            ip.close();

            WebElement selectCollection = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
            selectCollection.click();

            WebElement editCollection = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name+"']/following-sibling::td[2]/div/button[@id='edit-collection']"));
            editCollection.click();


            String name1Verify = driver.findElement(By.cssSelector("[data-test='test-QuantitySchemaName']")).getAttribute("value");
            Assert.assertEquals(name1Verify, name);
            WebElement item = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
            String aliases1Verify = item.getText().replaceAll("\\n",",").replaceAll(" ", "");

            System.out.println("Name1 " + name);
            System.out.println("Aliases1 " + aliases1Verify);

            WebElement clickOnBackButton = driver.findElement(By.cssSelector("[class='btn btn-info ml-1']"));
            clickOnBackButton.click();

            WebElement clickItems = driver.findElement(By.xpath("//tbody/tr/td[text()='/"+ selectedField.toLowerCase() +"/collection/"+ name +"']/following-sibling::td/button"));
            clickItems.click();


            Thread.sleep(500);
            Assert.assertTrue(driver.findElement(By.xpath("//*[@class='modal-content']")).isDisplayed());

            WebElement item1 = driver.findElement(By.xpath("(//*[@class='modal-body']/div/span)[1]"));
            String aliases2Verify = item1.getText().replaceAll("\\n",",").replaceAll(" ", "");

            System.out.println("Aliases2 " + aliases2Verify);

            List<String> alias1 = Arrays.asList(aliases1Verify.trim().split(","));
            List<String> alias2 = Arrays.asList(aliases2Verify.trim().split(","));

            Assert.assertTrue(listEqualsIgnoreOrder(alias1, alias2));

            WebElement clickingOnOkButton = driver.findElement(By.xpath("//button[@class='btn btn-secondary']"));
            clickingOnOkButton.click();
            Thread.sleep(500);
            boolean popUp = (isElementPresent(By.xpath("//*[@class='modal-content']")));
            Assert.assertEquals(popUp, false);

            Thread.sleep(500);
            clickItems.click();
            Thread.sleep(500);
            Assert.assertTrue(driver.findElement(By.xpath("//*[@class='modal-content']")).isDisplayed());
            WebElement item2 = driver.findElement(By.xpath("(//*[@class='modal-body']/div/span)[1]"));
            String aliases3Verify = item2.getText().replaceAll("\\n",",").replaceAll(" ", "");

            System.out.println("Aliases3 " + aliases3Verify);

            List<String> alias3 = Arrays.asList(aliases3Verify.trim().split(","));

            Assert.assertTrue(listEqualsIgnoreOrder(alias1, alias3));

            WebElement clickingOnCrossButton = driver.findElement(By.xpath("//button[@class='close']"));
            clickingOnCrossButton.click();
            Thread.sleep(500);
            boolean popUp1 = (isElementPresent(By.xpath("//*[@class='modal-content']")));
            Assert.assertEquals(popUp1, false);
        }

    }


    @Test
    public void addCollectionWithoutVerticalAndTypeField() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = driver.findElement(By.xpath("(//*[@data-test='test-navItems'])[2]"));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
        Thread.sleep(1000);
        WebElement clickOnSaveButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        clickOnSaveButton.click();
        WebElement errorOnVerticalField = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/div"));
        System.out.println(errorOnVerticalField.getText());
        Assert.assertEquals("Vertical is required", errorOnVerticalField.getText());
        WebElement errorOnTypeField = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div"));
        System.out.println(errorOnTypeField.getText());
        Assert.assertEquals("Choose collection type", errorOnTypeField.getText());
    }

    @Test
    public void addCollectionWithoutSelectType() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = driver.findElement(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]"));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
        Thread.sleep(1000);
        WebElement selectValueOfVertical = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/select/option[2]"));
        selectValueOfVertical.click();
        WebElement clickOnSaveButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        clickOnSaveButton.click();
        WebElement errorOnTypeField = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div"));
        System.out.println(errorOnTypeField.getText());
        Assert.assertEquals("Choose collection type", errorOnTypeField.getText());
    }

    @Test
    public void addCollectionWithoutSelectVertical() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
      //  Thread.sleep(1000);
        WebElement collectionButton = driver.findElement(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]"));
        collectionButton.click();
      //  Thread.sleep(1000);
        WebElement addCollectionButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        addCollectionButton.click();
      //  Thread.sleep(1000);
        Assert.assertFalse(driver.findElement(By.cssSelector("[data-test='test-select']")).isEnabled());
    }

//    @Test
//    public void clickingOnBackButton() throws Exception {
//
//        WebDriverWait wait = new WebDriverWait(driver, 50);
//        Thread.sleep(2000);
//        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
//        collectionButton.click();
//        Thread.sleep(2000);
//        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
//        addCollectionButton.click();
//        WebElement clickOnBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-info ml-1']")));
//        clickOnBackButton.click();
//        Thread.sleep(1000);
//        Assert.assertTrue(driver.findElement(By.cssSelector("[class='btn btn-primary']")).isDisplayed());
//
//    }

    private static List<Map<String, String>> read(Workbook wb, String main){
        Sheet sheet = wb.getSheet(main);
        List dataList = new ArrayList<Map<String, String>>();
        int i, j;
        Map<String, Integer> cellHeader = new HashMap<String, Integer>();
        for (i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (i == 0) {
                for (j = 0; j < row.getLastCellNum(); j++) {
                    cellHeader.put(row.getCell(j).getStringCellValue(), j);
                }
                continue;
            }
            //  System.out.println("&&&&&&&&& "+ row.getCell(cellHeader.get("Execute")).toString().equals("Yes"));
            row.getCell(cellHeader.get("runmode")).toString().equals("Y");
            if (row.getCell(cellHeader.get("runmode")).toString().equals("Y")) {
                Map<String, String> data = new HashMap<String, String>();
                Set<String> mapKey = cellHeader.keySet();
                for (String s : mapKey){
                    if (row.getCell(cellHeader.get(s)) != null) {
                        data.put(s, row.getCell(cellHeader.get(s)).toString());
                    }
                }
                dataList.add(data);
            }
        }

        System.out.println("dataList " + dataList.toString());
        return dataList;
    }
}
