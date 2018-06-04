package context;

import config.ApplicationProperties;
import config.webdriver.DriverBase;
import io.qameta.allure.junit5.AllureJunit5AnnotationProcessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import static support.web.WebElementHelper.navigateToPage;

import ui.components.locators.Locators;
import ui.components.models.MainModel;

import static config.ApplicationProperties.ApplicationProperty.APP_URL;

@ExtendWith(AllureJunit5AnnotationProcessor.class)
public class TestContext {

    protected WebDriver driver;

    @BeforeAll
    public static void instantiateDriverObject() {
        DriverBase.instantiateDriverObject();
    }

    @BeforeEach
    public void setUp(){
        driver = DriverBase.getDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void closeDriverObjects() {
        DriverBase.closeDriverObjects();
    }

    @AfterEach
    public void clearCookies() throws Exception {
        DriverBase.clearCookies();
    }

    public MainModel open(String language){
        navigateToPage(ApplicationProperties.getString(APP_URL));
        MainModel mainModel=new MainModel(language);
        mainModel.changeLanguage();
        return mainModel;
    }
}
