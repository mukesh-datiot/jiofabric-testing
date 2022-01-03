package com.jiofabric.automation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Unit_TestCases {

    public static WebDriver driver;
    public static  WebDriverWait wait;

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
    public void a_addUnit() throws Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Unit");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            addUnit(dataMap);
        }
    }


    public static void addUnit(Map<String, String> dataMap) throws InterruptedException, Exception {

     //   WebDriverWait wait = new WebDriverWait(driver, 50);
        String name = "";

        String name1 = "";
        String name2 = "";
        String name3 = "";
        String name4 = "";
        String name5 = "";

        String aliases1 = "";
        String aliases2 = "";
        String aliases3 = "";
        String aliases4 = "";
        String aliases5 = "";

        String selectedField = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equals("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equals("name")) {
                name = dataMap.get(s);
            }
            if (s.equals("name1")) {
                name1 = dataMap.get(s);
            }
            if (s.equals("name2")) {
                name2 = dataMap.get(s);
            }
            if (s.equals("name3")) {
                name3 = dataMap.get(s);
            }
            if (s.equals("name4")) {
                name4 = dataMap.get(s);
            }
            if (s.equals("name5")) {
                name5 = dataMap.get(s);
            }
            if (s.equals("aliases1")) {
                aliases1 = dataMap.get(s);
            }
            if (s.equals("aliases2")) {
                aliases2 = dataMap.get(s);
            }
            if (s.equals("aliases3")) {
                aliases3 = dataMap.get(s);
            }
            if (s.equals("aliases4")) {
                aliases4 = dataMap.get(s);
            }
            if (s.equals("aliases5")) {
                aliases5 = dataMap.get(s);
            }
        }

        wait = new WebDriverWait(driver, 50);
//        WebElement addUnitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
//        addUnitButton.click();
        WebElement addUnitButton = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
        addUnitButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        WebElement addName = driver.findElement(By.cssSelector("[data-test='test--unit-name']"));
        addName.sendKeys(name);
        WebElement addUnits = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[1]"));
        addUnits.sendKeys(name1);
        WebElement addAliases1 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[1]"));
        addAliases1.sendKeys(aliases1);
        addAliases1.sendKeys(Keys.ENTER);

        if (!name2.trim().isEmpty()) {
            WebElement clickOnPlusButton = driver.findElement(By.xpath("(//button[@class='mx-1 btn btn-secondary btn-sm'])"));
            clickOnPlusButton.click();
            WebElement addUnits2 = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[2]"));
            addUnits2.sendKeys(name2);
            if (!aliases2.trim().isEmpty()) {
                WebElement addAliases2 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[2]"));
                addAliases2.sendKeys(aliases2);
                addAliases2.sendKeys(Keys.ENTER);
            }
        }

        if (!name3.trim().isEmpty()) {
            WebElement clickOnPlusButton = driver.findElement(By.xpath("(//button[@class='mx-1 btn btn-secondary btn-sm'])"));
            clickOnPlusButton.click();
            WebElement addUnits3 = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[3]"));
            addUnits3.sendKeys(name3);
            if (!aliases3.trim().isEmpty()) {
                WebElement addAliases3 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[3]"));
                addAliases3.sendKeys(aliases3);
                addAliases3.sendKeys(Keys.ENTER);
            }
        }

        if (!name4.trim().isEmpty()) {
            WebElement clickOnPlusButton = driver.findElement(By.xpath("(//button[@class='mx-1 btn btn-secondary btn-sm'])"));
            clickOnPlusButton.click();
            WebElement addUnits4 = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[4]"));
            addUnits4.sendKeys(name4);
            if (!aliases4.trim().isEmpty()) {
                WebElement addAliases4 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[4]"));
                addAliases4.sendKeys(aliases4);
                addAliases4.sendKeys(Keys.ENTER);
            }
        }

        if (!name5.trim().isEmpty()) {
            WebElement clickOnPlusButton = driver.findElement(By.xpath("(//button[@class='mx-1 btn btn-secondary btn-sm'])"));
            clickOnPlusButton.click();
            WebElement addUnits5 = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[5]"));
            addUnits5.sendKeys(name5);
            if (!aliases5.trim().isEmpty()) {
                WebElement addAliases5 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[5]"));
                addAliases5.sendKeys(aliases5);
                addAliases5.sendKeys(Keys.ENTER);
            }
        }


        WebElement saveDetailButton = driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
        saveDetailButton.click();
//        WebElement createMessageOnAddUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
//        System.out.println(createMessageOnAddUnit.getText());
        WebElement createMessageOnAddUnit = driver.findElement(By.id("swal2-title"));
        System.out.println(createMessageOnAddUnit.getText());
        Assert.assertEquals("Meta data saved successfully", createMessageOnAddUnit.getText());
        Thread.sleep(2000);
        WebElement editButton = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='edit-unit']"));
        editButton.click();
        String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
        Assert.assertEquals(vertical, selectedField);
        String nameVerify = driver.findElement(By.cssSelector("[data-test='test--unit-name']")).getAttribute("value");
        Assert.assertEquals(nameVerify, name);
        String name1Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[1]")).getAttribute("value");
        Assert.assertEquals(name1Verify, name1);
        WebElement unit1 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
        String aliases1Verify = unit1.getText().replaceAll("\\n",",");

        List<String> aliasesExcel = Arrays.asList(aliases1.split(","));
        System.out.println("Item1 " +aliasesExcel);
        List<String> aliasesUi = Arrays.asList(aliases1Verify.split(","));
        System.out.println("items2 " +aliasesUi);
        System.out.println("is equal" + listEqualsIgnoreOrder(aliasesExcel, aliasesUi));
        Assert.assertTrue(listEqualsIgnoreOrder(aliasesExcel, aliasesUi));


      //  Assert.assertEquals(aliases1Verify, aliases1);


        if(isElementPresent(By.xpath("(//input[@data-test='test-unitName'])[2]"))){
            String name2Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[2]")).getAttribute("value");
            Assert.assertEquals(name2Verify, name2);
        }


//        Thread.sleep(2000);
//        WebElement editButton = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='edit-unit']"));
//        editButton.click();
//        System.out.println("Aliases1 = " + aliases1);
//        WebElement unit1 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
//        String aliasesValue = unit1.getText().replaceAll("\\n",",");
//        //   System.out.println("Aliases2 = /n   +a+  /n xxxxx /n xxxxxxx/n x   ");

//        System.out.print("Aliases2 = " +aliasesValue);
//        Assert.assertEquals(aliasesValue, aliases1);

        if(isElementPresent(By.xpath("(//*[@class=' css-1hwfws3'])[2]"))){
            WebElement unit2 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[2]"));
            String aliases2Verify = unit2.getText().replaceAll("\\n",",");
            List<String> unitAliasesExcle = Arrays.asList(aliases2.split(","));
            System.out.println("Item1 " +unitAliasesExcle);
            List<String> unitAliasesUi = Arrays.asList(aliases2Verify.split(","));
            System.out.println("items2 " +unitAliasesUi);
            System.out.println("is equal" + listEqualsIgnoreOrder(unitAliasesExcle, unitAliasesUi));
            Assert.assertTrue(listEqualsIgnoreOrder(unitAliasesExcle, unitAliasesUi));
          //  Assert.assertEquals(aliases2Verify, aliases2);
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        if(isElementPresent(By.xpath("(//input[@data-test='test-unitName'])[3]"))){
            String name3Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[3]")).getAttribute("value");
            Assert.assertEquals(name3Verify, name3);
        }
        if(isElementPresent(By.xpath("(//*[@class=' css-1hwfws3'])[3]"))){
            WebElement unit3 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[3]"));
            String aliases3Verify = unit3.getText().replaceAll("\\n",",");
            List<String> aliases3Excel = Arrays.asList(aliases3.split(","));
            System.out.println("Item1 " +aliases3Excel);
            List<String> aliases3Ui = Arrays.asList(aliases3Verify.split(","));
            System.out.println("items2 " +aliases3Ui);
            System.out.println("is equal" + listEqualsIgnoreOrder(aliases3Excel, aliases3Ui));
            Assert.assertTrue(listEqualsIgnoreOrder(aliases3Excel, aliases3Ui));
            //  Assert.assertEquals(aliases2Verify, aliases2);
        }
        if(isElementPresent(By.xpath("(//input[@data-test='test-unitName'])[4]"))){
            String name4Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[4]")).getAttribute("value");
            Assert.assertEquals(name4Verify, name4);
        }

        if(isElementPresent(By.xpath("(//*[@class=' css-1hwfws3'])[4]"))){
            WebElement unit4 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[4]"));
            String aliases4Verify = unit4.getText().replaceAll("\\n",",");
            List<String> aliases4Excel = Arrays.asList(aliases4.split(","));
            System.out.println("Item1 " +aliases4Excel);
            List<String> aliases4Ui = Arrays.asList(aliases4Verify.split(","));
            System.out.println("items2 " +aliases4Ui);
            System.out.println("is equal" + listEqualsIgnoreOrder(aliases4Excel, aliases4Ui));
            Assert.assertTrue(listEqualsIgnoreOrder(aliases4Excel, aliases4Ui));
            //  Assert.assertEquals(aliases2Verify, aliases2);
        }

//        if(isElementPresent(By.xpath("(//*[@class=' css-1hwfws3'])[4]"))){
//            WebElement unit4 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[4]"));
//            String aliases2Verify = unit4.getText().replaceAll("\\n",",");
//            Assert.assertEquals(aliases2Verify, aliases4);
//        }
        js.executeScript("window.scrollBy(0,200)", "");
        if(isElementPresent(By.xpath("(//input[@data-test='test-unitName'])[5]"))){
            String name5Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[5]")).getAttribute("value");
            Assert.assertEquals(name5Verify, name5);
        }

        if(isElementPresent(By.xpath("(//*[@class=' css-1hwfws3'])[5]"))){
            WebElement unit5 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[5]"));
            String aliases5Verify = unit5.getText().replaceAll("\\n",",");
            List<String> aliases5Excel = Arrays.asList(aliases5.split(","));
            System.out.println("Item1 " +aliases5Excel);
            List<String> aliases5Ui = Arrays.asList(aliases5Verify.split(","));
            System.out.println("items2 " +aliases5Ui);
            System.out.println("is equal" + listEqualsIgnoreOrder(aliases5Excel, aliases5Ui));
            Assert.assertTrue(listEqualsIgnoreOrder(aliases5Excel, aliases5Ui));
            //  Assert.assertEquals(aliases2Verify, aliases2);
        }
//        if(isElementPresent(By.xpath("(//*[@class=' css-1hwfws3'])[5]"))){
//            WebElement unit5 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[5]"));
//            String aliases5Verify = unit5.getText().replaceAll("\\n",",");
//            Assert.assertEquals(aliases5Verify, aliases5);
//        }

    }

    public static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    public static void verifyUnit() throws Exception {

        String updatedUnitName = "";
        String selectedField = "";
        String name = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Unit");
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
                if (s.equals("updatedUnitName")) {
                    updatedUnitName = dataMap.get(s);
                }
            }
            ip.close();

            List<WebElement> rowSize =
                    driver.findElements(By.xpath("*//table/tbody/tr"));
            WebElement row = null;
            for (int i = 0; i < rowSize.size(); i++) {
                String col1 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(1)"))).getText();
                String col2 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(2)"))).getText();
//            System.out.println(rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(2)"))).getText());
//            System.out.println(col1 + "  " + col2);
                if (col1.equals(selectedField) && col2.equals(name)) {
                    System.out.println(col1 + "  " + col2);
                    row = rowSize.get(i);
                    break;
                }
            }
            System.out.println("CHAL JAA BHAI");
            System.out.println(row.getText());
            row.findElement((By.cssSelector("td:nth-of-type(4)"))).findElement(By.id("edit-unit")).click();
            Thread.sleep(1000);
        }
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

    @Test
    public void createUnitWithBlankData() throws Exception {
       // WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement addUnitButton = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
        addUnitButton.click();
        WebElement saveDetailButton = driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
        saveDetailButton.click();
        WebElement errorOnNameField = driver.findElement(By.xpath("(//*[@class='error invalid-feedback'])[1]"));
        System.out.println(errorOnNameField.getText());
        Assert.assertEquals("Name is required", errorOnNameField.getText());
        WebElement errorOnUnitFiels = driver.findElement(By.xpath("(//*[@class='error invalid-feedback'])[2]"));
        System.out.println(errorOnUnitFiels.getText());
        Assert.assertEquals("unit name is required", errorOnUnitFiels.getText());

    }

    @Test
    public void d_deleteOneUnitButton() throws InterruptedException, Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Unit");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            deleteOneUnitFromMultipleUnits(dataMap);
        }
    }

    public void deleteOneUnitFromMultipleUnits(Map<String, String> dataMap) throws InterruptedException, Exception {

     //   WebDriverWait wait = new WebDriverWait(driver, 50);
        String name = "";

        String name1 = "";
        String name2 = "";
        String name3 = "";

        String aliases1 = "";
        String aliases2 = "";
        String aliases3 = "";


        String selectedField = "";
        String deletedUnit = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equals("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equals("deletedUnit")) {
                deletedUnit = dataMap.get(s);
            }
            if (s.equals("name")) {
                name = dataMap.get(s);
            }

            if (s.equals("name1")) {
                name1 = dataMap.get(s);
            }
            if (s.equals("name2")) {
                name2 = dataMap.get(s);
            }
            if (s.equals("name3")) {
                name3 = dataMap.get(s);
            }
            if (s.equals("aliases1")) {
                aliases1 = dataMap.get(s);
            }
            if (s.equals("aliases2")) {
                aliases2 = dataMap.get(s);
            }
            if (s.equals("aliases3")) {
                aliases3 = dataMap.get(s);
            }
        }

        WebElement addUnitButton = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
        addUnitButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        WebElement addName = driver.findElement(By.cssSelector("[data-test='test--unit-name']"));
        addName.sendKeys(name);
        WebElement addUnits = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[1]"));
        addUnits.sendKeys(name1);
        WebElement addAliases1 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[1]"));
        addAliases1.sendKeys(aliases1);
        addAliases1.sendKeys(Keys.ENTER);

        if (!name2.trim().isEmpty()) {
            WebElement clickOnPlusButton = driver.findElement(By.xpath("(//button[@class='mx-1 btn btn-secondary btn-sm'])"));
            clickOnPlusButton.click();
            WebElement addUnits2 = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[2]"));
            addUnits2.sendKeys(name2);
            if (!aliases2.trim().isEmpty()) {
                WebElement addAliases2 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[2]"));
                addAliases2.sendKeys(aliases2);
                addAliases2.sendKeys(Keys.ENTER);
            }
        }

        if (!name3.trim().isEmpty()) {
            WebElement clickOnPlusButton = driver.findElement(By.xpath("(//button[@class='mx-1 btn btn-secondary btn-sm'])"));
            clickOnPlusButton.click();
            WebElement addUnits3 = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[3]"));
            addUnits3.sendKeys(name3);
            if (!aliases3.trim().isEmpty()) {
                WebElement addAliases3 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[3]"));
                addAliases3.sendKeys(aliases3);
                addAliases3.sendKeys(Keys.ENTER);
            }
        }


        // Thread.sleep(1000);
        boolean isPresent = driver.findElement(By.xpath("//*[@value='" + deletedUnit + "']")).isDisplayed();
        System.out.println(isPresent);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@value='" + deletedUnit + "']")).isDisplayed());
        WebElement element2 = driver.findElement(By.xpath("//input[@value='" + deletedUnit + "']/../../../../preceding-sibling::div[1]/button"));
        element2.click();
        // Thread.sleep(2000);
        boolean eleDisplayed = isElementPresent(By.xpath("//*[@value='" + deletedUnit + "']"));
        Assert.assertEquals(eleDisplayed, false);

        WebElement saveDetailButton = driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
        saveDetailButton.click();
        WebElement createMessageOnAddUnit = driver.findElement(By.id("swal2-title"));
        System.out.println(createMessageOnAddUnit.getText());
        Assert.assertEquals("Meta data saved successfully", createMessageOnAddUnit.getText());
        Thread.sleep(2000);
        WebElement editButton = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='edit-unit']"));
        editButton.click();
//        verifyUnit();
        String vertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']")).getAttribute("value");
        Assert.assertEquals(vertical, selectedField);
        String nameVerify = driver.findElement(By.cssSelector("[data-test='test--unit-name']")).getAttribute("value");
        Assert.assertEquals(nameVerify, name);
        String name1Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[1]")).getAttribute("value");
        Assert.assertEquals(name1Verify, name1);

        if(isElementPresent(By.xpath("(//input[@data-test='test-unitName'])[2]"))){
            String name2Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[2]")).getAttribute("value");
            Assert.assertEquals(name2Verify, name2);
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        if(isElementPresent(By.xpath("(//input[@data-test='test-unitName'])[3]"))){
            String name3Verify = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[3]")).getAttribute("value");
            Assert.assertEquals(name3Verify, name3);
        }

    }

    @Test
    public void d_deleteUnit() throws Exception {

      //  WebDriverWait wait = new WebDriverWait(driver, 50);

        String selectedField = "";
        String name = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Unit");
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

//            List<WebElement> rowSize =
//                    driver.findElements(By.xpath("*//table/tbody/tr"));
//            WebElement row = null;
//            for (int i = 0; i < rowSize.size(); i++) {
//                String col1 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(1)"))).getText();
//                String col2 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(2)"))).getText();
//                if (col1.equals(selectedField) && col2.equals(name)) {
//                    System.out.println(col1 + "  " + col2);
//                    row = rowSize.get(i);
//                    break;
//                }
//            }
//            System.out.println("CHAL JAA BHAI");
//            System.out.println(row.getText());
//            row.findElement((By.cssSelector("td:nth-of-type(4)"))).findElement(By.id("delete-unit")).click();
            wait = new WebDriverWait(driver, 50);
            WebElement editButton1 = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='delete-unit']"));
            editButton1.click();
            WebElement deleteUnit = driver.findElement(By.cssSelector("[class='swal2-confirm swal2-styled']"));
            deleteUnit.click();
            Thread.sleep(200);
            WebElement popUpOnDeleteUnit = driver.findElement(By.id("swal2-title"));
            System.out.println(popUpOnDeleteUnit.getText());
            Assert.assertEquals("Unit Deleted successfully", popUpOnDeleteUnit.getText());
            Thread.sleep(5000);
//            WebElement searchUnitName = driver.findElement(By.xpath("//*[@class='form-control']"));
//            searchUnitName.click();
//            searchUnitName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//            searchUnitName.clear();
//            searchUnitName.sendKeys(name);
//            WebElement msgOnPage = driver.findElement(By.cssSelector("[class='react-bs-table-no-data']"));
//            System.out.println(msgOnPage.getText());
//            Assert.assertEquals("No Data Found", msgOnPage.getText());
            wait = new WebDriverWait(driver, 50);
            boolean editButton2 = (isElementPresent(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='delete-unit']")));
            Assert.assertEquals(editButton2, false);
        }
    }

    @Test
    public void b_editVerticalAndName() throws Exception {
     //   WebDriverWait wait = new WebDriverWait(driver, 50);

        String updatedUnitName = "";
        String selectedField = "";
        String name = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Unit");
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
                if (s.equals("updatedUnitName")) {
                    updatedUnitName = dataMap.get(s);
                }
            }
            ip.close();

//            List<WebElement> rowSize =
//                    driver.findElements(By.xpath("*//table/tbody/tr"));
//            WebElement row = null;
//            for (int i = 0; i < rowSize.size(); i++) {
//                String col1 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(1)"))).getText();
//                String col2 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(2)"))).getText();
//                if (col1.equals(selectedField) && col2.equals(name)) {
//                    System.out.println(col1 + "  " + col2);
//                    row = rowSize.get(i);
//                    break;
//                }
//            }
//            System.out.println("CHAL JAA BHAI");
//            System.out.println(row.getText());
//            row.findElement((By.cssSelector("td:nth-of-type(4)"))).findElement(By.id("edit-unit")).click();
         //   Thread.sleep(2000);
            wait = new WebDriverWait(driver, 50);
            WebElement editButton = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='edit-unit']"));
            editButton.click();
            WebElement editVertical = driver.findElement(By.cssSelector("[data-test='test-vertical-input']"));
            Assert.assertFalse(editVertical.isEnabled());

            WebElement editName = driver.findElement(By.cssSelector("[data-test='test--unit-name']"));
            editName.click();
            editName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            editName.clear();
            editName.sendKeys(updatedUnitName);
            WebElement editUnits = driver.findElement(By.cssSelector("[data-test='test-unitName']"));
            editUnits.click();
            String editName1 = editName.getAttribute("value");

            WebElement editPopUpOnEditUnit = driver.findElement(By.id("swal2-title"));
            System.out.println(editPopUpOnEditUnit.getAttribute("value"));
            Assert.assertEquals("unit name updated successfully", editPopUpOnEditUnit.getText());

            Assert.assertEquals(editName1, updatedUnitName);

            WebElement clickOnBackButton = driver.findElement(By.cssSelector("[class='btn btn-info ml-1']"));
            clickOnBackButton.click();

            wait = new WebDriverWait(driver, 50);
            WebElement editButton1 = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ updatedUnitName +"']/following-sibling::td/div/button[@id='edit-unit']"));
            editButton1.click();
            Assert.assertEquals(editName1, updatedUnitName);
        }
    }

    @Test
    public void a_addDeletedUnit() throws Exception {
       // WebDriverWait wait = new WebDriverWait(driver, 50);

        String selectedField = "";
        String name = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Unit");
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
            }
            ip.close();

//            List<WebElement> rowSize =
//                    driver.findElements(By.xpath("*//table/tbody/tr"));
//            WebElement row = null;
//            for (int i = 0; i < rowSize.size(); i++) {
//                String col1 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(1)"))).getText();
//                String col2 = rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(2)"))).getText();
////            System.out.println(rowSize.get(i).findElement((By.cssSelector("td:nth-of-type(2)"))).getText());
////            System.out.println(col1 + "  " + col2);
//                if (col1.equals(selectedField) && col2.equals(name)) {
//                    System.out.println(col1 + "  " + col2);
//                    row = rowSize.get(i);
//                    break;
//                }
//            }
//            System.out.println("CHAL JAA BHAI");
//            System.out.println(row.getText());
//            row.findElement((By.cssSelector("td:nth-of-type(4)"))).findElement(By.id("delete-unit")).click();
            wait = new WebDriverWait(driver, 50);
            WebElement editButton1 = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='delete-unit']"));
            editButton1.click();
            WebElement deleteUnit =driver.findElement(By.cssSelector("[class='swal2-confirm swal2-styled']"));
            deleteUnit.click();
            Thread.sleep(200);
            WebElement deletePopUpOnDeleteUnit = driver.findElement(By.id("swal2-title"));
            System.out.println(deletePopUpOnDeleteUnit.getText());
            Assert.assertEquals("Unit Deleted successfully", deletePopUpOnDeleteUnit.getText());
            Thread.sleep(5000);
            for (Map<String, String> dataMap1 : dataList) {
                addUnit(dataMap1);
            }


        }
    }

    @Test
    public void clickingOnBackButton() throws Exception {
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");
        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        dataList = read(wb, "Unit");
        ip.close();
        for (Map<String, String> dataMap : dataList) {
            checkingBackButton(dataMap);
        }
    }

    public  void checkingBackButton(Map<String, String> dataMap) throws InterruptedException, Exception {

    //    WebDriverWait wait = new WebDriverWait(driver, 50);
        String name = "";

        String unit1 = "";


        String aliases1 = "";

        String selectedField = "";
        Set<String> mapKeys = dataMap.keySet();
        for (String s : mapKeys) {
            System.out.println("s = " + s);
            if (s.equals("selectedField")) {
                selectedField = dataMap.get(s);
            }
            if (s.equals("name")) {
                name = dataMap.get(s);
            }


            if (s.equals("unit1")) {
                unit1 = dataMap.get(s);
            }

            if (s.equals("aliases1")) {
                aliases1 = dataMap.get(s);
            }


        }

        //   Reporter.log("Test case start for " + selectedField);
        WebElement addUnitButton = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
        addUnitButton.click();
        Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
        selectVertical.selectByVisibleText(selectedField);
        WebElement addName = driver.findElement(By.cssSelector("[data-test='test--unit-name']"));
        addName.sendKeys(name);
        WebElement addUnits = driver.findElement(By.xpath("(//input[@data-test='test-unitName'])[1]"));
        addUnits.sendKeys(unit1);
        WebElement addAliases1 = driver.findElement(By.xpath("(//input[starts-with(@id,'react-select')])[1]"));
        addAliases1.sendKeys(aliases1);
        addAliases1.sendKeys(Keys.ENTER);

        WebElement clickOnBackButton = driver.findElement(By.cssSelector("[class='btn btn-info ml-1']"));
        clickOnBackButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='btn btn-primary']")).isDisplayed());
        wait = new WebDriverWait(driver, 50);
        boolean editButton2 = (isElementPresent(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='delete-unit']")));
        Assert.assertEquals(editButton2, false);
//        WebElement searchUnitName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='form-control']")));
//        searchUnitName.click();
//        searchUnitName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        searchUnitName.clear();
//        searchUnitName.sendKeys(name);
//        WebElement msgOnPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='react-bs-table-no-data']")));
//        System.out.println(msgOnPage.getText());
//        Assert.assertEquals("No Data Found", msgOnPage.getText());

    }

    @Test
    public void checkNavigationButton() throws Exception{
        List<WebElement> rows = driver.findElements(By.xpath("*//table/tbody/tr"));
        int count = rows.size();
        System.out.println(count);
        if(count<10){
            WebElement navigationButton1 = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][1]"));
            Assert.assertTrue(navigationButton1.isEnabled());

            WebElement navigationButton2 = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][2]"));
            Assert.assertFalse(navigationButton2.isEnabled());
        }else {
            WebElement navigationButton1 = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][1]"));
            Assert.assertTrue(navigationButton1.isEnabled());

            WebElement navigationButton2 = driver.findElement(By.xpath("//button[@class='btn btn-icon btn-sm btn-primary mr-2 my-1'][2]"));
            Assert.assertTrue(navigationButton2.isEnabled());
            navigationButton2.click();
            Assert.assertTrue(rows.size()>0);

        }
    }


    @Test
    public void createUnitWithBlankUnit() throws Exception {
        String name = "";
        String selectedField = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Unit");
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
            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement addUnitButton = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
            addUnitButton.click();
            Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
            selectVertical.selectByVisibleText(selectedField);
            WebElement addName = driver.findElement(By.cssSelector("[data-test='test--unit-name']"));
            addName.sendKeys(name);
            WebElement saveDetailButton = driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
            saveDetailButton.click();
            WebElement errorOnUnitFied = driver.findElement(By.xpath("//*[@class='error invalid-feedback']"));
            System.out.println(errorOnUnitFied.getText());
            Assert.assertEquals("unit name is required", errorOnUnitFied.getText());

        }

    }


    @Test
    public void createUnitWithBlankName() throws Exception {
        String unit1 = "";
        String selectedField = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Unit");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                if (s.equals("unit1")) {
                    unit1 = dataMap.get(s);
                }
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
            }
            ip.close();
            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement addUnitButton = driver.findElement(By.xpath("//*[@class='btn btn-primary']"));
            addUnitButton.click();
            Select selectVertical = new Select(driver.findElement(By.cssSelector("[data-test='test-vertical-input']")));
            selectVertical.selectByVisibleText(selectedField);
            WebElement saveDetailButton = driver.findElement(By.cssSelector("[data-test='test-submit-button']"));
            saveDetailButton.click();
            WebElement addUnits = driver.findElement(By.cssSelector("[data-test='test-unitName']"));
            addUnits.sendKeys(unit1);
            WebElement errorOnNameField = driver.findElement(By.xpath("//*[@class='error invalid-feedback']"));
            System.out.println(errorOnNameField.getText());
            Assert.assertEquals("Name is required", errorOnNameField.getText());

        }

    }

    @Test
    public void demoTest() throws Exception {

        String name = "";
        String selectedField = "";
        String aliases1 = "";
        String aliases2 = "";
        FileInputStream ip = new FileInputStream("D:\\code\\Selenium-testng\\demo\\src\\test\\resources\\excel\\unit.xlsx");

        Workbook wb = WorkbookFactory.create(ip);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        dataList = read(wb, "Unit");
        for (Map<String, String> dataMap : dataList) {
            Set<String> mapKeys = dataMap.keySet();
            for (String s : mapKeys) {
                if (s.equals("name")) {
                    name = dataMap.get(s);
                }
                if (s.equals("selectedField")) {
                    selectedField = dataMap.get(s);
                }
                if (s.equals("aliases2")) {
                    aliases2 = dataMap.get(s);
                }
            }
            ip.close();
            Thread.sleep(2000);
            WebElement editButton = driver.findElement(By.xpath("//tbody/tr/td[text()='"+ selectedField +"']/../../tr/td[text()='"+ name +"']/following-sibling::td/div/button[@id='edit-unit']"));
            editButton.click();
            System.out.println("Aliases1 = " + aliases2);
            WebElement unit1 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[2]"));
            String aliasesValue = unit1.getText().replaceAll("\\n",",");
         //   System.out.println("Aliases2 = /n   +a+  /n xxxxx /n xxxxxxx/n x   ");

            System.out.print("Aliases2 = " +aliasesValue);
            List<String> items1 = Arrays.asList(aliases2.split(","));
            System.out.println("Item1 " +items1);
            List<String> items2 = Arrays.asList(aliasesValue.split(","));
            System.out.println("items2 " +items2);
            System.out.println("is equal" + listEqualsIgnoreOrder(items1, items2));
            Assert.assertTrue(listEqualsIgnoreOrder(items1, items2));
//            String aliasesValue = "";
//            By mySelector = By.xpath("(//*[@class=' css-1hwfws3'])[1]");
//            List<WebElement> myElements = driver.findElements(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
//            for(WebElement e : myElements) {
//                System.out.print("Aliases2 = " +e.getText());
//            }
//            System.out.println("Aliases2 = " + aliasesValue);
//            Assert.assertEquals(aliasesValue, aliases1);
//            WebElement addAliases1 = driver.findElement(By.xpath("(//*[@class=' css-1hwfws3'])[1]"));
//            String aliasesValue = addAliases1.getText();
//            System.out.println("Aliases2 = " + aliasesValue);
//            Assert.assertEquals(aliasesValue, aliases1);
        }

    }

    public static  boolean listEqualsIgnoreOrder(List<String> list1, List<String> list2) {
        return new HashSet<String>(list1).equals(new HashSet<String>(list2));
    }
}

