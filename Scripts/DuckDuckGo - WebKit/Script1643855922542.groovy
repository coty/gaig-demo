import com.microsoft.playwright.*;

Playwright playwright = Playwright.create();

BrowserType browserType = playwright.webkit();

Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
BrowserContext context = browser.newContext();
Page page = context.newPage();
page.navigate("https://duckduckgo.com/");

page.waitForSelector('#search_form_input_homepage')
page.type('#search_form_input_homepage', 'playwright')
page.click('#search_button_homepage')

page.waitForSelector('#links > #r1-0 > .result__body > .result__title > .result__a')
page.click('#links > #r1-0 > .result__body > .result__title > .result__a')

page.waitForSelector('.main-wrapper > .hero > .container > .buttons_1r9m > .getStarted_1iQB')
page.click('.main-wrapper > .hero > .container > .buttons_1r9m > .getStarted_1iQB')

page.waitForSelector('.theme-doc-sidebar-menu > .theme-doc-sidebar-item-category:nth-child(1) > .menu__list > .theme-doc-sidebar-item-link:nth-child(2) > .menu__link')
page.click('.theme-doc-sidebar-menu > .theme-doc-sidebar-item-category:nth-child(1) > .menu__list > .theme-doc-sidebar-item-link:nth-child(2) > .menu__link')

browser.close()