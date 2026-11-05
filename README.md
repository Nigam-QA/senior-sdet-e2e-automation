# Senior SDET E2E Automation

A portfolio-ready starter repository containing two independent automation frameworks:

1. **UI E2E:** Java 17 + Selenium 4 + TestNG + Maven + Page Object Model.
2. **API automation:** JavaScript + Playwright Test + APIRequestContext.

> Demo target: [SauceDemo](https://www.saucedemo.com/) for UI tests and [Restful Booker](https://restful-booker.herokuapp.com/) for API tests. Public demo services can change or become unavailable; use only test accounts and non-sensitive data.

## Repository layout

```text
selenium-java-testng/       Java/Selenium UI framework
playwright-javascript-api/  Playwright API test framework
.github/workflows/          GitHub Actions pipelines
jenkins/                    Jenkins pipeline example
docs/                       Architecture and interview notes
```

## Prerequisites

- JDK 17+
- Maven 3.9+
- Node.js 20+
- npm

## Run UI tests

```bash
cd selenium-java-testng
mvn clean test
```

Default browser: Chrome. Override with:

```bash
mvn clean test -Dbrowser=firefox -Dheadless=true
```

The framework uses Selenium Manager (built into modern Selenium) to resolve browser drivers.

## Run API tests

```bash
cd playwright-javascript-api
npm ci
npx playwright test
```

To view the HTML report:

```bash
npx playwright show-report
```

## CI

GitHub Actions workflows run the UI and API suites independently on pushes and pull requests. A Jenkins pipeline is included under `jenkins/Jenkinsfile`.

## Security

- Never commit real credentials, tokens, cookies, or production data.
- Keep secrets in GitHub Actions Secrets or Jenkins Credentials.
- `.env` and local override files are ignored by Git.

## Suggested next enhancements

- Add a database test-data adapter and isolated test data.
- Add contract testing (e.g. Pact) where service contracts are available.
- Add accessibility checks and visual regression tests.
- Add Dockerized browser execution and a test-grid strategy.
- Publish reports as CI artifacts.
