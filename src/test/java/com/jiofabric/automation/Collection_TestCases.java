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
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Collection");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            addCollection(dataMap);
        }
    }


    public void addCollection(Map<String, String> dataMap) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 50);
        String selectedField = "";
        String type = "";
        String name = "";
        String items = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equals("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equals("type")) {
                type = dataMap.get(s);
            }
            if (s.equals("name")) {
                name = dataMap.get(s);
            }
            if (s.equals("items")) {
                items = dataMap.get(s);
            }
        }

        Thread.sleep(1000);
        WebElement selectCollection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@data-test='test-navItems'])[2]")));
        selectCollection.click();
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        Thread.sleep(1000);
        Select selectType = new Select(driver.findElement(By.cssSelector("[data-test='test-select']")));
        selectType.selectByVisibleText(type);

        Thread.sleep(100);
        WebElement addName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='test-QuantitySchemaName']")));
        addName.sendKeys(name);
        Thread.sleep(100);
        WebElement addItems = driver.findElement(By.xpath("//input[starts-with(@id,'react-select')]"));
        addItems.sendKeys(items);
        addItems.sendKeys(Keys.ENTER);

        Thread.sleep(100);
        WebElement saveDetailButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        saveDetailButton.click();
        // Thread.sleep(500);
        WebElement createMessageOnAddUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
        System.out.println(createMessageOnAddUnit.getText());
        Assert.assertEquals("Collection saved successfully", createMessageOnAddUnit.getText());
        Thread.sleep(5000);
    }

    @Test
    public void b_editUnit() throws Exception {
        //  driver.getCurrentUrl();
        String name = "";
        String updatedName = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

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

                if (s.equals("updatedName")) {
                    updatedName = dataMap.get(s);
                }
            }
            ip.close();

            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement selectCollection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@data-test='test-navItems'])[2]")));
            selectCollection.click();
            WebElement searchCollectionByName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='form-control']")));
            searchCollectionByName.sendKeys(name);
            Thread.sleep(200);
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-collection")));
            editButton.click();
            Thread.sleep(200);
            WebElement editName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='test-QuantitySchemaName']")));
            editName.click();
            editName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            editName.clear();
            editName.sendKeys(updatedName);
            Thread.sleep(200);
            WebElement editUnits = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[starts-with(@id,'react-select')]")));
            editUnits.click();

            Thread.sleep(200);
            WebElement editPopUpOnEditUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
            System.out.println(editPopUpOnEditUnit.getText());
            Assert.assertEquals("Collection saved successfully", editPopUpOnEditUnit.getText());
            Thread.sleep(200);
            WebElement clickOnBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-info ml-1']")));
            clickOnBackButton.click();

        }
    }

    @Test
    public void d_deleteUnit() throws Exception {
        //   driver.getCurrentUrl();
        String name = "";
        String updatedName = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

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
            }
            ip.close();

            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement selectCollection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@data-test='test-navItems'])[2]")));
            selectCollection.click();
            WebElement searchCollectionByName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='form-control']")));
            searchCollectionByName.sendKeys(name);
            Thread.sleep(1000);
            WebElement deleteCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete-collection")));
            deleteCollectionButton.click();
            Thread.sleep(200);
            WebElement deleteCollection = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='swal2-confirm swal2-styled']")));
            deleteCollection.click();
            Thread.sleep(200);
            WebElement popUpOnEditCollection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
            System.out.println(popUpOnEditCollection.getText());
            Assert.assertEquals("collection deleted successfully", popUpOnEditCollection.getText());
        }
    }


    @Test
    public void addCollectionWithoutVerticalAndTypeField() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@data-test='test-navItems'])[2]")));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        Thread.sleep(1000);
        WebElement clickOnSaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        clickOnSaveButton.click();
        WebElement errorOnVerticalField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/div")));
        System.out.println(errorOnVerticalField.getText());
        Assert.assertEquals("Vertical is required", errorOnVerticalField.getText());
        WebElement errorOnTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div")));
        System.out.println(errorOnTypeField.getText());
        Assert.assertEquals("Choose collection type", errorOnTypeField.getText());
    }

    @Test
    public void addCollectionWithoutSelectType() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        Thread.sleep(1000);
        WebElement selectValueOfVertical = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[1]/div/div/select/option[2]")));
        selectValueOfVertical.click();
        WebElement clickOnSaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        clickOnSaveButton.click();
        WebElement errorOnTypeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div")));
        System.out.println(errorOnTypeField.getText());
        Assert.assertEquals("Choose collection type", errorOnTypeField.getText());
    }

    @Test
    public void addCollectionWithoutSelectVertical() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(1000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
        collectionButton.click();
        Thread.sleep(1000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        Thread.sleep(1000);
        Assert.assertFalse(driver.findElement(By.cssSelector("[data-test='test-select']")).isEnabled());
    }

    @Test
    public void clickingOnBackButton() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        Thread.sleep(2000);
        WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='layout-sidenav']/div[3]/ul/li[3]")));
        collectionButton.click();
        Thread.sleep(2000);
        WebElement addCollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-primary']")));
        addCollectionButton.click();
        WebElement clickOnBackButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-info ml-1']")));
        clickOnBackButton.click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.cssSelector("[class='btn btn-primary']")).isDisplayed());

    }

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
