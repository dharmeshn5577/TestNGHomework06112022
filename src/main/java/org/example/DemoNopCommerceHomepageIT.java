package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class DemoNopCommerceHomepageIT {
    protected static WebDriver driver;

    @BeforeMethod       // used BeforeMethod TestNG function to run this method before running every method
    public void openBrowser(){
        // set driver path for Chrome browser
        System.setProperty("webdriver.chrome.driver","src/test/java/Drivers/chromedriver.exe");

        // assign value to driver varible as a chrome driver to perform task in Chrome browser
        driver = new ChromeDriver();

        // use inbuilt manage method to manage something, here we have to manage windows, so I have used inbuilt window method
        // and I want to do maximize the window, so I also used inbuilt maximize method
        driver.manage().window().maximize();
        // used implicitWait timeout feature to instruct webDriver to wait till 2 seconds before sending test case fail exception
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // used get method with driver to navigate to the website
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterMethod    // used AfterMethod TestNG function to run this method after running every method
    public void closeBrowser(){
        driver.quit();}     // used inbuilt quit method to close the tab(s) of browser opened by ChromeDriver

    // created non-static String return type method and used import SimpleDateFormat package to find timestamp in required format
    public String getTimeStamp(){
        // import SimpleDateFormat package to find timestamp in required format
        return  new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
    }

    // created non-static no return type with parameter clickElement method to perform click action
    public void clickElement(By by){driver.findElement(by).click();}

    // created non-static no return type typeText method with 2 parameter to fill data action in text field
    public void typeText(By by, String textToFill){driver.findElement(by).sendKeys(textToFill);}

    // created non-static String return type getTextElement method to capture text action
    public String getTextElement(By by){return driver.findElement(by).getText();}


    @Test       // used test annotation feature of TestNG to run multiple test cases in one class
    public void verifyUserCanRegisterSuccessfully(){
    // used predefined clickElement method with by classname to find that particular location and to click on it
    clickElement(By.className("ico-register"));
    // used predefined clickElement method with by id to find that particular location and click on it
    clickElement(By.id("gender-male"));

    // used predefined typeText method with by id to find that particular location and to fill that data in it
    typeText(By.id("FirstName"), "Ram");
    typeText(By.id("LastName"), "Patel");

    // to generate new email address each time, created one by using timestamp variable, which I have created above
    String email = "xyz" + getTimeStamp() + "@gmail.com";
    System.out.println("Email ID: "+email);  // print generated email address
    typeText(By.id("Email"), email);

    typeText(By.id("Company"),"Patel Trading Co. Ltd.");
    clickElement(By.id("Newsletter"));
    typeText(By.id("Password"),"Abc@123");
    typeText(By.id("ConfirmPassword"),"Abc@123");

    // imported select method and created object select and used Birth Day locator to select birthday from dropdown list
    Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
    selectDay.selectByIndex(5);    // To select day 5 use selectByIndex function

    // imported select method and created object select and used Birth month locator to select birth month from dropdown list
    Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
    selectMonth.selectByVisibleText("May");      // To select month May  use selectByVisibleText function

    // imported select method and created object select and used Birth year locator to select birth year from dropdown list
    Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
    selectYear.selectByValue("1985");       // To select year 1985 use selectByValue function

    driver.findElement(By.id("register-button")).click();

    // created string variable and assign value by using predefined getTextElement method with  by classname to find that particular location
    // and to capture that particular message from instructed location
    String regMessage = getTextElement(By.className("result"));
    System.out.println(regMessage);     // print out the captured message
    }

    @Test
    public void captureListOfProductsRegisteredInDesktopsSubCategory(){

    // used predefined clickElement method by using xpath to find that particular location and click on it
    clickElement(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li[1]/a[1]"));
    clickElement(By.xpath("//div[@class=\"item-grid\"]//h2/a[@href=\"/desktops\"]"));
    System.out.println("Listed Desktop Products:");

    // created String variable product1 to capture subcategory name
    // used predefined getTextElement method by using xpath to find that particular location and also used getText method to store the product title from it
    String product1 = getTextElement(By.xpath("//div[@class=\"item-grid\"]/div[1]//h2[1]/a[1]"));
    // print out the captured subcategory title
    System.out.println("1. "+product1);

    String product2 = getTextElement(By.xpath("//div[@class=\"item-grid\"]/div[2]//h2[1]/a[1]"));
    System.out.println("2. "+product2);
    String product3 = getTextElement(By.xpath("//div[@class=\"item-grid\"]/div[3]//h2[1]/a[1]"));
    System.out.println("3. "+product3);
    }

    @Test
    public void verifyUserAbleToWriteCommentsInNewReleaseSection(){
        // used predefined clickElement method by using xpath to find that particular location and to click on it
        driver.findElement(By.xpath("//a[contains(@href, \"release\") and contains(@class, \"read\")]")).click();

        // used predefined typeText method with by id to find that particular location and to fill that data in it
        typeText(By.id("AddNewComment_CommentTitle"), "Product variety collection");
        typeText(By.id("AddNewComment_CommentText"), "We have listed so many new products and now you have very large collection of products.");
        clickElement(By.name("add-comment"));

        // created string variable and assign value by using predefined typeText method with by classname to find that particular location
        // and to capture that particular message from instructed location
        String regMessage = getTextElement(By.className("result"));
        System.out.println(regMessage); // print out the captured message
    }

    @Test
    public void verifyAndCaptureProductCategoriesOnHomepage(){
        System.out.println("List of Categories on Homepage: ");
        // used predefined getTextElement method by using xpath to find that particular location
        // and created String variable to capture that category title from instructed location
        String computers = getTextElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]"));
        System.out.println("1. "+computers);      // print out the captured category title
        String electronics = getTextElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/electronics\"]"));
        System.out.println("2. "+electronics);
        String apparel = getTextElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/apparel\"]"));
        System.out.println("3. "+apparel);
        String digitalDownloads = getTextElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/digital-downloads\"]"));
        System.out.println("4. "+digitalDownloads);
        String books = getTextElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/books\"]"));
        System.out.println("5. "+books);
        String jewelry = getTextElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/jewelry\"]"));
        System.out.println("6. "+jewelry);
        String giftCards = getTextElement(By.xpath("//ul[@class=\"top-menu notmobile\"]//a[@href=\"/gift-cards\"]"));
        System.out.println("7. "+giftCards);
    }

    @Test
    public void verifyUnregisterUserCannotReferAProductToFriend(){

        // used predefined clickElement method by using xpath/name to find that particular location and to click on it
        clickElement(By.xpath("//div[@data-productid=\"4\"]/div[2]/h2/a[1]"));
        clickElement(By.xpath(" //div[@class=\"email-a-friend\"]/button"));

        // used predefined typeText method with by id to find that particular location and to fill that data in it
        typeText(By.id("FriendEmail"), "friend123@gmail.com");
        typeText(By.id("YourEmailAddress"),"ram999@gmail.com");
        typeText(By.id("PersonalMessage"),"This is a very nice deal with great price.");
        clickElement(By.name("send-email"));

        // used predefined getTextElement method by using xpath to find that particular location
        // and created String variable to capture that category title from instructed location
        String errorMessage = getTextElement(By.xpath("//form[@method=\"post\"]/div[1]/ul/li"));
        System.out.println(errorMessage);   // print out the captured error message
    }
}
