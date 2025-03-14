import com.microsoft.playwright.*;

Playwright playwright = Playwright.create()

Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
BrowserContext context = browser.newContext();
Page page = context.newPage();
page.route("**", { route -> 
  System.out.println(route.request().url());
  route.resume();
})

page.navigate("http://todomvc.com");