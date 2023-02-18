// checklist
// загружаємо драйвер гугл хрома System.setProperty("webdriver.chrome.driver","C:\\Users\\makasole\\OneDrive - TietoEVRY\\Desktop\\auto\\chromedriver_win32\\chromedriver.exe");
// створити об'єкт драйвера в гугл хром. ChromeDriver driver = new ChromeDriver();
// викликаєм метод гет об'єктом хрома driver.get("http://10.254.188.90:20240/vam-ui/#/");
// створюємо об'єкт веб елемента по його х-пассу WebElement loginElement = driver.findElement(By.xpath("//*[@id=\"userid\"]"));
// для того що б клікнути визиваємо метод .click() об'єкта веб елемента loginElement.click();
// для того щоб ввести дані викликаємо метод сендкейс елемента loginElement.sendKeys("тут має бути текст");

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

public class Main {
    static String customerId = null;

    public static void fillForm(ChromeDriver driver) {



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
    }


    public static WebElement getElement(String xpathExpression, ChromeDriver driver) {
        try {
            WebElement element = driver.findElement(By.xpath(xpathExpression));
            return element;
        }  catch (Exception e) {
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
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\makasole\\OneDrive - TietoEVRY\\Desktop\\auto\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://10.254.188.90:20240/vam-ui/#/");

        // чекаєм 3 сек поки прогрузиться сторінка
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // логін юзера
        WebElement loginElement = getElement("//*[@id=\"userid\"]", driver);
        loginElement.click();
        loginElement.sendKeys("BANKSUP");
        WebElement passwordElement = getElement("//*[@id=\"password\"]", driver);
        passwordElement.click();
        passwordElement.sendKeys("Password@123");
        WebElement loginButtonElement = getElement("/html/body/app-root/app-login/main/div[1]/div/form/button", driver);
        loginButtonElement.click();
        // залогінились

        // чекаєм 4 сек поки прогрузиться сторінка
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // створити нового рут кастомера
        WebElement rootCustomerElement = getElement("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a/span", driver);
        rootCustomerElement.click();

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement addRootCustomerButtonElement = getElement("/html/body/app-root/app-layout/div/div/main/app-customer-list/div/app-page-header/div/div[2]/button/span", driver);
        addRootCustomerButtonElement.click();

        // заповнюємо форму рут кастомера
        fillForm(driver);

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // сабмітим форму
        WebElement submitButtonElement = getElement("/html/body/ngb-modal-window/div/div/app-customer-form/div/form/div[3]/div/div[3]/button", driver);
        submitButtonElement.click();

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        // відправити на активацію рут кастомера
        WebElement sendForActivationButtonElement = getElement("/html/body/app-root/app-layout/div/div/main/app-customers/div/div[2]/div/app-customer-send-for-activation/div/div/div[2]/button", driver);
        sendForActivationButtonElement.click();

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // вибираєм те що ми маємо відправити до апрувала
        WebElement allElement = getElement("/html/body/ngb-modal-window/div/div/app-send-for-approval/div/div[2]/div[1]/label/div[1]/span", driver);
        allElement.click();

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        // відправляємо до апрувала
        WebElement sendElement = getElement("/html/body/ngb-modal-window/div/div/app-send-for-approval/div/div[3]/div[2]/button", driver);
        sendElement.click();

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        // рефреш сторінки для того щоб вилогінитись
        driver.navigate().refresh();

        // чекаєм 4 сек поки прогрузиться сторінка
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        // логін юзера
        WebElement loginElement1 = getElement("//*[@id=\"userid\"]", driver);
        loginElement1.click();
        loginElement1.sendKeys("BANKALL");
        WebElement passwordElement1 = getElement("//*[@id=\"password\"]", driver);
        passwordElement1.click();
        passwordElement1.sendKeys("Password@123");
        WebElement loginButtonElement1 = getElement("/html/body/app-root/app-login/main/div[1]/div/form/button", driver);
        loginButtonElement1.click();
        // залогінились
        // чекаєм 4 сек поки прогрузиться сторінка
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // ідемо в апрувал
        WebElement approvalElement = getElement("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a", driver);
        approvalElement.click();

        // чекаєм 4 сек поки прогрузиться сторінка
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // апрувимо
        WebElement approvalButtonElement = getElement("/html/body/app-root/app-layout/div/div/main/app-approvals/div/div/div/app-table/div/div/virtual-scroller/div[2]/table/tbody/tr[1]/td[11]/div/span[2]/a", driver);
        approvalButtonElement.click();

        WebElement approvalButton2Element = getElement("/html/body/ngb-modal-window/div/div/app-approvals-confirmation/div/form/div[3]/div[2]/button", driver);
        approvalButton2Element.click();

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // рефреш сторінки для того щоб вилогінитись
        driver.navigate().refresh();

        // чекаєм 4 сек поки прогрузиться сторінка
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        // логін юзера
        WebElement loginElement2 = getElement("//*[@id=\"userid\"]", driver);
        loginElement2.click();
        loginElement2.sendKeys("BANKSUP");
        WebElement passwordElement2 = getElement("//*[@id=\"password\"]", driver);
        passwordElement2.click();
        passwordElement2.sendKeys("Password@123");
        WebElement loginButtonElement2 = getElement("/html/body/app-root/app-login/main/div[1]/div/form/button", driver);
        loginButtonElement2.click();
        // залогінились
        // чекаєм 4 сек поки прогрузиться сторінка
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // перевіряємо рут кастомера
        WebElement rootCustomerElement1 = getElement("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a/span", driver);
        rootCustomerElement1.click();

        // чекаєм 2 сек поки прогрузиться сторінка
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // пошук рут кастомера
        WebElement searchRootCustomerElement = getElement("//*[@id=\"navbarSupportedContent\"]/div/button", driver);
        searchRootCustomerElement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement searchElement = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/div/div/button", driver);
        searchElement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        WebElement searchElement2 = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/div/div/div/div[3]/a", driver);
        searchElement2.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement searchElement3 = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/app-search/span/div/div[1]/div/input", driver);
        searchElement3.click();
        searchElement3.sendKeys(customerId);

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement searchElement4 = getElement("//*[@id=\"navbarSupportedContent\"]/div/div/div/app-nav-global-search/app-search/span/div/div[1]/div/span/button", driver);
        searchElement4.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement searchElement5 = getElement("/html/body/app-root/app-layout/div/div/main/app-search-results/div/div[2]/virtual-scroller/div[2]/div/div/div/div[1]", driver);
        searchElement5.click();

        // створюємо ієрархію акаунтів - шадов - інтернал - 2 ва

        WebElement accountElement = getElement("//*[@id=\"navbarSupportedContent\"]/ul[2]/li[1]", driver);
        accountElement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement addRootAccountElement = getElement("/html/body/app-root/app-layout/div/div/main/app-account-container/app-accounts/div/aside/div/div/app-account-error-block/button", driver);
        addRootAccountElement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement accountTypeElement = getElement("/html/body/ngb-modal-window/div/div/app-account-root-form/div/form/div[2]/div/div[1]/div[2]/div[1]/div/app-account-type-dropdown/ng-select/div/div/div[2]", driver);
        accountTypeElement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement externalShadowlement = getElement("//*[@id=\"a1e90c758967-1\"]", driver);
        externalShadowlement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement currencylement = getElement("//*[@id=\"currency\"]/div/div/div[2]", driver);
        currencylement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement usdElement = getElement("//*[@id=\"a73b3ae4e52a-3\"]", driver);
        usdElement.click();

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement accountNameElement = getElement("//*[@id=\"accountName\"]", driver);
        accountNameElement.click();
        accountNameElement.sendKeys("ShadowAccount");

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement accountNumberElement = getElement("//*[@id=\"accountNumber\"]", driver);
        accountNumberElement.click();
        accountNumberElement.sendKeys("ShadowAccount5494");

        // чекаєм 1 сек поки прогрузиться сторінка
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement addNewRootAccountButtonElement = getElement("/html/body/ngb-modal-window/div/div/app-account-root-form/div/form/div[3]/div[2]/button", driver);
        addNewRootAccountButtonElement.click();
    }


    // рандоматор для символів
    private static String generateRandomString() {
//        byte[] array = new byte[7]; // length is bounded by 7
//        Random random = new Random();
//        random.nextBytes(array);
//        return new String(array, StandardCharsets.UTF_8);
        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString()
                .replaceAll("_", "")
                .replaceAll("-", "");
    }



}