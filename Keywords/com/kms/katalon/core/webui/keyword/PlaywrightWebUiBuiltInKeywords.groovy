package com.kms.katalon.core.webui.keyword
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration;
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.microsoft.playwright.*
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as OriginalWebUI

class PlaywrightWebUiBuiltInKeywords {
	static Page page
	static Browser browser
	static BrowserContext context
	static Playwright playwright

	/**
	 * Refresh browser
	 */
	static def openBrowser(String url) {
		String driverTypeString = RunConfiguration.getDriverSystemProperty(DriverFactory.WEB_UI_DRIVER_PROPERTY,
				DriverFactory.EXECUTED_BROWSER_PROPERTY);
		KeywordUtil.logInfo("Configured for ${driverTypeString}")
		WebUIDriverType webDriverType = null
		if (driverTypeString != null) {
			webDriverType = WebUIDriverType.valueOf(driverTypeString);
		}

		KeywordUtil.logInfo("Called openBrowser()")
		playwright = Playwright.create()
		boolean headless = (webDriverType == WebUIDriverType.FIREFOX_HEADLESS_DRIVER || webDriverType == WebUIDriverType.HEADLESS_DRIVER)
		switch(webDriverType) {
			case WebUIDriverType.FIREFOX_DRIVER:
				browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless))
				break
			case WebUIDriverType.SAFARI_DRIVER:
				browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless))
				break
			default:
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless))
				break
		}
		context = browser.newContext();
		context.tracing().start(new Tracing.StartOptions()
				.setScreenshots(true)
				.setSnapshots(true));
		page = context.newPage();
	}

	static def closeBrowser() {
		KeywordUtil.logInfo("Called closeBrowser()")
		context.tracing().stop(new Tracing.StopOptions()
				.setPath(Paths.get("trace.zip")));
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	static def navigateToUrl(String url) {
		page.navigate(url);
	}

	static def click(TestObject to) {
		String xpath = to.getSelectorCollection()[SelectorMethod.XPATH]
		String xpathLocator = "xpath=${xpath}"

		KeywordUtil.logInfo("Attempting to click xpath: ${xpath}")

		page.click(xpathLocator)
	}

	static def verifyElementText(TestObject to, String text) {
		String xpath = to.getSelectorCollection()[SelectorMethod.XPATH]
		String xpathLocator = "xpath=${xpath}"

		assertThat(page.locator(xpathLocator)).hasText(text);
	}

	// Catch any static method calls that are not defined in this class
	static def methodMissing(String name, args) {
		// Forward the method call to the original WebUI class using metaClass
		OriginalWebUI.metaClass."$name"(*args)
	}
}