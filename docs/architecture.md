# Architecture overview

## Selenium UI framework

TestNG test -> Page Object -> BasePage wait/action helpers -> Selenium WebDriver
-> browser. DriverFactory uses ThreadLocal WebDriver to isolate parallel test
threads. Config reads system properties and safe demo defaults. BaseTest owns
driver lifecycle and captures screenshots on failure.

## Playwright API framework

Playwright Test runner -> APIRequestContext (`request` fixture) -> REST service.
Tests assert HTTP status, JSON structure and CRUD workflow. The HTML reporter
and trace-on-failure settings support debugging.

## Design considerations

- Keep test intent in test classes; selectors and page behaviour belong in page objects.
- Prefer explicit waits over fixed sleeps.
- Keep tests independent and use unique test data in shared environments.
- Avoid depending on execution order.
- Use secret stores for non-demo credentials.
