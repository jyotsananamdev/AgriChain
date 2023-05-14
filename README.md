*Proejct Details*

```
src > test > com > agrichain

BaseTest : Create object of all pages in this class and extend this from every test class

CommonTest : All common methods like senKeys, click, select are here. Add new common methods here if required.

PreprocessorTest : This is where I'm creating dynamic xml through Excel file. Also responsible for creating driver , closing driver and reading properties file. 

Runner : This is the triggering point for the execution.

drivers package :

	-> impl package (Add new browser implementation here)
	
		-> ChromeDriver : Creating chrome driver here if required we can add desired capabilities here.
		-> Firefox : Creating firefox driver here if required we can add desired capabilities here.
		
	-> WebDriverProvide(Interface)

pages package : Add new pages under this package

	 -> LongestSubstringPage : All the web elements and functions related to longest substring page are here.
	
tests package : Add new test classes here.

	 -> LongestSubstringTest : All the test cases related to flight booking create in this class.

utility package : Add new test classes here.

	-> ExecutionCycle : All the implemention for ITestListner is there. We can add implementation here if we wanted to add when test case start, end, finish 
        -> Dataset: Testdata reader for test cases using @dataprovider
        -> ExcelReader: To read excel data
```

```
**src > main >**
resources :
	-> Environment.properties : Keep you common information(data) here.
    	-> execution_config:
       		->> testcases sheet: It's having test cases related data means classname and test case description and pass "yes" for the classes which you wanted to run
       		->> testdata sheet: To achieve data driven testing pass your data here
```
```
Supported WebBrowser :
1. Chrome 
2. Firefox 
```
```
Porject configurations and test data:
1. It is maven based and TestNG integrated project.
2. Go to testng.xml and the class inside this xml will create a new testng xml as per the classes available in execution_config.xlsx testcases sheet
3. Go to Environment.properties file and pass url and browser.
```
```
Project Execution :
 'mvn clean'
 'mvn test'
```

```
Project reporting :

install allure using command `brew install allure`
Then run `allure serve allure-results` in project folder to create allure report

```


 



