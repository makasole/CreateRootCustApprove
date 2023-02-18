package org.example;// checklist
// We load up the Google Chrome driver System.setProperty("webdriver.chrome.driver","C:\\Users\\makasole\\OneDrive - TietoEVRY\\Desktop\\auto\\chromedriver_win32\\chromedriver.exe");
// Create a driver object in Google Chrome. ChromeDriver driver = new ChromeDriver();
// invoking the get method by the chrome object driver.get("http://10.254.188.90:20240/vam-ui/#/");
// create a web element object by its xpath WebElement loginElement = driver.findElement(By.xpath("//*[@id=\"userid\"]"));
// In order to click, call the method .click() web item object loginElement.click();
// In order to enter data, we call the element keysToSend method loginElement.sendKeys("There should be text here");

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.UUID;

public class CreateRootCustApprove {
    static String customerId = null;

    public static void main(String[] args) {
        int numberOfRuns = 2;
        String driverName = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\makasole\\OneDrive - TietoEVRY\\Desktop\\auto\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(driverName, driverPath);
        ChromeDriver driver = new ChromeDriver();
        driver.get(getUrl());

        for (int i = 1; i <= numberOfRuns; i++) {
            System.out.println("Creating Customer: " + i);
            creatApproveVerifyRootCustomer(driver);
            System.out.println("Done with Customer: " + i);
        }
    }


    public static void fillForm(ChromeDriver driver) {
        login(getCreatorLogin(), getCreatorPassword(), driver);

        WebElement rootCustomerElement = getElement("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a/span", driver);
        rootCustomerElement.click();
        // wait 3 seconds for the page to load
        pageSleep(3000);

        WebElement addRootCustomerButtonElement = getElement("/html/body/app-root/app-layout/div/div/main/app-customer-list/div/app-page-header/div/div[2]/button/span", driver);
        addRootCustomerButtonElement.click();

        WebElement nameElement = getElement("//*[@id=\"firstName\"]", driver);
        nameElement.click();
        nameElement.sendKeys(generateRandomString());

        WebElement preferredNameElement = getElement("//*[@id=\"companyName\"]", driver);
        preferredNameElement.click();
        preferredNameElement.sendKeys(generateRandomString());

        WebElement customerIdElement = getElement("//*[@id=\"customerIdentity\"]", driver);
        customerIdElement.click();

        customerId = generateRandomString();
        System.out.println(customerId);
        customerIdElement.sendKeys(customerId);

        WebElement companyRegNumberElement = getElement("//*[@id=\"referenceNumber_0\"]", driver);
        companyRegNumberElement.click();
        companyRegNumberElement.sendKeys(generateRandomString());
        pageSleep(2000);
    }

    public static void sendRootCustomerForApproval(ChromeDriver driver) {
        // sent for activation Root Customer
        WebElement sendForActivationButtonElement = getElement("/html/body/app-root/app-layout/div/div/main/app-customers/div/div[2]/div/app-customer-send-for-activation/div/div/div[2]/button", driver);
        sendForActivationButtonElement.click();

        pageSleep(2000);

        // choose what we have to send to the approval
        WebElement allElement = getElement("/html/body/ngb-modal-window/div/div/app-send-for-approval/div/div[2]/div[1]/label/div[1]/span", driver);
        allElement.click();

        pageSleep(2000);

        // wait 2 seconds for the page to load
        pageSleep(2000);

        // Sent to approval
        WebElement sendElement = getElement("/html/body/ngb-modal-window/div/div/app-send-for-approval/div/div[3]/div[2]/button", driver);
        sendElement.click();

        // wait 2 seconds for the page to load
        pageSleep(2000);
    }

    public static void submitForm(ChromeDriver driver) {
        WebElement submitButtonElement = getElement("/html/body/ngb-modal-window/div/div/app-customer-form/div/form/div[3]/div/div[3]/button", driver);
        submitButtonElement.click();

        pageSleep(2000);
    }

    public static void executeApprovalFlow(ChromeDriver driver) {
        login(getApproverLogin(), getApproverPassword(), driver);

        WebElement approvalButtonElement = getElement("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a/span", driver);
        approvalButtonElement.click();

        pageSleep(2000);

        WebElement approvalButton2Element = getElement("/html/body/app-root/app-layout/div/div/main/app-approvals/div/div/div/app-table/div/div/virtual-scroller/div[2]/table/tbody/tr[1]/td[11]/div/span[2]/a", driver);
        approvalButton2Element.click();

        pageSleep(2000);
    }

    public static void checkRootCustomer(ChromeDriver driver) {
        login(getCreatorLogin(), getCreatorPassword(), driver);

        WebElement rootCustomerElement1 = getElement("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a/span", driver);
        rootCustomerElement1.click();

        pageSleep(2000);
    }

    public static void searchRootCustomer(ChromeDriver driver) {
        WebElement searchRootCustomerElement = getElement("//*[@id=\"navbarSupportedContent\"]/div/button", driver);
        searchRootCustomerElement.click();

        pageSleep(2000);

        WebElement searchElement = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/div/div/button", driver);
        searchElement.click();

        pageSleep(2000);

        WebElement searchElement2 = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/div/div/div/div[3]/a", driver);
        searchElement2.click();

        pageSleep(2000);

        WebElement searchElement3 = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/app-search/span/div/div[1]/div/input", driver);
        searchElement3.click();

        searchElement3.sendKeys(customerId);

        pageSleep(2000);

        WebElement searchElement4 = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/app-search/span/div/div[1]/div/span/button", driver);
        searchElement4.click();

        pageSleep(3000);
        WebElement searchElement5 = getElement("/html/body/app-root/app-layout/div/div/main/app-search-results/div/div[2]/virtual-scroller/div[2]/div/div/div/div[1]", driver);
        searchElement5.click();

        pageSleep(3000);
    }

    private static void creatApproveVerifyRootCustomer(ChromeDriver driver) {
        pageSleep(3000);

        fillForm(driver);

        submitForm(driver);

        sendRootCustomerForApproval(driver);

        refreshPage(driver);

        executeApprovalFlow(driver);

        refreshPage(driver);

        checkRootCustomer(driver);

        searchRootCustomer(driver);

        refreshPage(driver);
    }

    public static WebElement getElement(String xpathExpression, ChromeDriver driver) {
        try {
            WebElement element = driver.findElement(By.xpath(xpathExpression));
            return element;
        } catch (Exception e) {
            try {
                Thread.sleep(5000);
                WebElement element = driver.findElement(By.xpath(xpathExpression));
                return element;
            } catch (InterruptedException e1) {
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

    // sumbol randomator
    //    private static String generateRandomString() {
    //        // byte[] array = new byte[7]; // length is bounded by 7
    //        // Random random = new Random();
    //        // random.nextBytes(array);
    //        // return new String(array, StandardCharsets.UTF_8);
    //        UUID randomUUID = UUID.randomUUID();
    //
    //        return randomUUID.toString()
    //                .replaceAll("_", "")
    //                .replaceAll("-", "");
    //    }


    private static String generateRandomString() {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 90; // letter 'Z'
        int targetStringLength = 5; //word length
        Random random = new Random();
        return "Autotest" + random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    private static void pageSleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private static void login(String login, String password, ChromeDriver driver) {
        WebElement loginElement = getElement("//*[@id=\"userid\"]", driver);
        loginElement.click();
        loginElement.sendKeys(login);
        WebElement passwordElement = getElement("//*[@id=\"password\"]", driver);
        passwordElement.click();
        passwordElement.sendKeys(password);
        WebElement loginButtonElement = getElement("/html/body/app-root/app-login/main/div[1]/div/form/button", driver);
        loginButtonElement.click();
        // login

        // wait 4 seconds for the page to load
        pageSleep(4000);
    }

    private static String getCreatorLogin() {
        return "BANKSUP";
    }

    private static String getCreatorPassword() {
        return "Password@123";
    }

    private static String getApproverLogin() {
        return "BANKALL";
    }

    private static String getApproverPassword() {
        return "Password@123";
    }

    private static String getUrl() {
        return "http://10.254.188.90:20240/vam-ui/#/";
    }

    private static void refreshPage(ChromeDriver driver) {
        // refresh page to sing out
        driver.navigate().refresh();
        // wait 4 seconds for the page to load
        pageSleep(4000);
    }
}