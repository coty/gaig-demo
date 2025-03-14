import com.microsoft.playwright.*;

Playwright playwright = Playwright.create()
Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
  .setHeadless(false));
BrowserContext context = browser.newContext();
// Open new page
Page page = context.newPage();
// Go to https://katalon-studio-samples.github.io/calculator/
page.navigate("https://katalon-studio-samples.github.io/calculator/");
// Click text=9
page.click("text=9");
// Click text=x
page.click("text=x");
// Click button:has-text("9")
page.click("button:has-text(\"9\")");
// Click text==
page.click("text==");
// Click text=x
page.click("text=x");
// Click text=9
page.click("text=9");
// Click text==
page.click("text==");
// Click text=729
page.click("text=729");
// Click text=AC
page.click("text=AC");
