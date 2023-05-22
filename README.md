# trillo-Automation
Trillo API and UI test automation.
# Prerequisites
- JDK 17.
- MAVEN 3.8.4 +.
- Chrome/Edge/IE/Firefox/Safari browsers.
- valid user on Trillo.
- Valid key and token.
# How To Use
1. Clone/Download the repository.
2. Go to the cloned project folder location.
3. Open cmd from the cloned project where the pom.xml and testng.xml exist.
4. Run (mvn -f pom.xml test -Pregression). 
5. Alternatively you can import existing maven project to Eclipse IDE and run testng.xml as a testNG Suite.
6. The tests will run, API test cases will run first then UI test cases.
7. After running is complete, Allure report will load the test resultes with screenshots in windows default browser.
![maven run](https://github.com/mghazalde/trillo-Automation/assets/102529622/eeac3e45-92b4-48ae-a6ad-7f7c90320683)
![maven results](https://github.com/mghazalde/trillo-Automation/assets/102529622/cb9d40af-7e93-47a0-a945-e5a1133eaf8c)
![report overview](https://github.com/mghazalde/trillo-Automation/assets/102529622/8425120d-de34-484e-86e5-ea6c4bdfd745)
![detailed report1](https://github.com/mghazalde/trillo-Automation/assets/102529622/c4f82cf3-0dad-415b-ac48-6f83c666ca4d)
![detailed report2](https://github.com/mghazalde/trillo-Automation/assets/102529622/19fedd83-3c60-469a-89dc-241c8c980bf2)

# Test Automation programming language, Framework and Reporting 
- Language: JAVA.
- API: Restassured. 
- UI : Shaft Engine open source. (https://github.com/ShaftHQ/SHAFT_ENGINE) Selenium Ecosystem member. 
- Reports: Allure reports.

# Test Automation Design
- Page object model design (pages classes contain locators, variables and actions, Test classes contain assertions).
- Login data and API data stored in \src\main\resources\properties\environment.properties.
- Test data used for validations stored in src\test\resources\testDataFiles\testData.json.
 
# trello Test Scope (2 test classes)
- API 9 test classes to validate:
1. Board creation.
2. List creation.   
3. Card creation.   
- UI 16 test classes to validate:
1. login with a valid user.
2. created board.
3. created list.
4. created card.
5. creation of a second card.
6. Archiving/Deleting a second card.
7. Moving first card to another list.
8. Moving back the first card by drag and drop.

# Notes
- Total testcases : 25.
- Before API test : all the created boards will be deleted.
- Allure screenshots will be stored in \allure-results folder (will be created after first run).
- If Allure report doesn't start, please run \generate_allure_report.bat.
- Screenshots of the report and maven run is stored in \screenshots.
- Video recording of the execution is stored in \video  (video recording is disabled in \src\main\resources\properties\VisualValidations.properties) require vlc codec to run.
- Test running (browser/headless execution/remote execution..etc) can be configued using the properties files in \src\main\resources\properties\.
