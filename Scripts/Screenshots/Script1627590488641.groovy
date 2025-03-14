import com.microsoft.playwright.*;

import java.nio.file.Paths;

Playwright playwright = Playwright.create()

List<BrowserType> browserTypes = Arrays.asList(
	// playwright.firefox(),
	// playwright.webkit(),
	playwright.chromium()
);

for (BrowserType browserType : browserTypes) {
	Browser browser = browserType.launch()
	BrowserContext context = browser.newContext();
	Page page = context.newPage();
	page.navigate("http://whatsmyuseragent.org/");
	page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot-" + browserType.name() + ".png")));
}