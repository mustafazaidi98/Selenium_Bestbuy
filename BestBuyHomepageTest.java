/*
 * (C) Copyright 2021 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.webdriver.jupiter.ch03.locators;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;

class BestBuyHomepageTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();

        // Loading BestBuy Homepage takes roughly 3 seconds to complete loading the page/elements
        // We will get 'ElementNotVisibleException' if there is a delay in loading particular element 
        // which Webdriver wants to interact. 
        // So, the best solution is do implicit wait for few seconds.

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        driver.quit();
        Thread.sleep(Duration.ofSeconds(5).toMillis());

    }


    @Test
    void testBestBuyHomepageHeaderElements() {
        String sutUrl = "https://www.bestbuy.com/";
        driver.get(sutUrl);


        testMenuItems();
	    try {
  		    Thread.sleep(5000);
		    System.out.println("\n\n\n  Test ... Completed  - Remove this when you are done unit-testing your code \n\n\n");
	    } catch (InterruptedException e) {
  		    Thread.currentThread().interrupt();
	    }

    }

    @Test
    void testBestBuyEmailSignUp() {
        driver.get(
                "https://www.bestbuy.com/");

        WebElement inputText = driver.findElement(By.name("footer-email-signup"));
        String textValue = "mySeleliumTest@gmail.com";
        inputText.sendKeys(textValue);
        assertThat(inputText.getAttribute("value")).isEqualTo(textValue);


        //inputText.clear();

        //assertThat(inputText.getAttribute("value")).isEmpty();
    }
    public void testMenuItems() {
        WebElement hamburgerMenuButton = driver.findElement(By.className("hamburger-menu-button"));
        hamburgerMenuButton.click();
        testMenuItem("Deals",false);
        testMenuItem("Support & Services",false);
        testMenuItem("Brands",false);
        testMenuItem("Appliances",true);
        testMenuItem("TV & Home Theater",true);
        testMenuItem("Computers & Tablets",true);
        testMenuItem("Cell Phones",true);
        testMenuItem("Audio",true);
        testMenuItem("Video Games",true);
        testMenuItem("Cameras, Camcorders & Drones",true);
        testMenuItem("Home, Furniture & Office",true);
        testMenuItem("Smart Home, Security & Wi-Fi",true);
        testMenuItem("Car Electronics & GPS",true);
        testMenuItem("Movies & Music",true);
        testMenuItem("Wearable Technology",true);
        testMenuItem("Health, Wellness & Fitness",true);
        testMenuItem("Outdoor Living",true);
        testMenuItem("Toys, Games & Collectibles",true);
        testMenuItem("Electric Transportation",true);
        testMenuItem("New & Featured",true);
        WebElement closeButton = driver.findElement(By.className("menuCloseBtn"));
        assertThat(closeButton.isDisplayed()).isTrue();
        closeButton.click();
    }

    private void testMenuItem(String itemName , boolean useBackButton) {
        WebElement menuItem = driver.findElement(By.xpath("//button[contains(text(), '" + itemName + "')]"));
        assertThat(menuItem.isDisplayed()).isTrue();
        assertThat(menuItem).as(itemName + " menu item").isNotNull();
        try {
            menuItem.click();
            System.out.println(itemName + " menu item click test passed");
            if (useBackButton) {
                WebElement backButton = driver.findElement(By.xpath("//span[contains(@class, 'hamburger-back-button-text')]"));
                backButton.click();
            }
        } catch (Exception e) {
            throw new AssertionError(itemName + " menu item is not clickable", e);
        }
    }
}
