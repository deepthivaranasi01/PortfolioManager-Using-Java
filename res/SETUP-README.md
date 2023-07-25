# CS 5010 Assignment 6 SETUP-README

*Name:* Deepthi Varanasi and Anirudh Krishnan Venkatanathan.

*Email:* varanasi.d@northeastern.edu and venkatanathan.a@northeastern.edu



### How to Run and use the Program

The program can be run in Stocks.jar file under res folder using the command `java -jar Stocks.jar`.
The program by default is set for graphical user interface. So for the user to run the text file
interface,program can be run in Stocks.jar file under res folder using the command 
`java -jar Stocks.jar text`.  
After running the program, you'll be able to find the menu option.
In the text based interface, after running, this would be the menu option

1. Composition of all portfolio.
2. Composition of specific portfolio.
3. Create an inflexible portfolio.
4. Create a Flexible portfolio.
5. Get value of a portfolio as on a given date.
6. Show portfolio value as on a given date depending on purchase dates.
7. Buy Stocks.
8. Sell Stocks.
9. Get the cost basis by a specific date (i.e. the total amount of money invested in a portfolio)
10. Display performance of a portfolio.
11. Invest a fixed amount into an existing portfolio containing multiple stocks, using a specified weight for each stock in the portfolio.
12. Dollar cost averaging for a new flexible portfolio.
13. Exit.

To get the composition of all portfolios, type 1 and click enter and the composition of all 
existing portfolios are displayed.
To get the composition of specific portfolio, type 2 and enter.
It then asks the user to enter the name of the particular portfolio which is to be displayed. 
It then displays the value of that particular portfolio after entering. It throws illegal 
argument exception if wrong name is entered. 
To create an inflexible portfolio, type 3 and 
click enter. It then asks the user to enter the portfolio name, commission, stock ticker symbol 
and the number of stocks. This is inputted as a single string with comma based separators for all 
inputs and | based separators for ticker symbol and number of stocks. 
Eg (ExampleName,5,GOOG|5,AMZN|10). After entering the details the portfolio is created successfully.
It throws illegal argument exception if existing portfolio name is entered while creating, or when 
any invalid inputs like wrong stock symbol is entered, negative, 0 or fractional shares are
entered. 
To create a flexible portfolio, type 4 and click enter. Then the same process for creating
inflexible portfolio is repeated.
For getting value of particular on portfolio a date, enter 5, it'll ask the user for the
portfolio name and date, and will fetch the value from that date. Entering invalid inputs like 
portfolio name, wrong dates or format will throw Illegal Argument Exceptions. 
To show portfolio value as on a given date depending on purchase dates, it's the same
step as getting values on a particular date, the difference is while displaying, it will show the 
whole purchase and sell dates. The menu button to enter is 6. 
To buy stocks, type 7 and enter. Then it asks the user to enter the portfolio name, 
commission fee, stock symbol, number of shares and date. Here, the program allows the
user to purchase only 1 different stock at a time. It throws Illegal argument exception for all
invalid inputs like portfolio name, commission fee, symbol of stock, number of share and date.
For selling stocks, the user enters, 8 in menu and clicks enter. The rest all is same as buying
stocks. 
For cost basis, press 9 and the program asks user to enter portfolio name and date, it then 
queries the cost basis and returns the value. Illegal argument exceptions are thrown if
invalid portfolio name or date is entered. 
For displaying performance of the portfolio, type 10 and enter. It then asks the name of the 
portfolio, the start and end dates for which the performance analysis should be conducted.
It displays in * format where * represents the value of the portfolio and scale changes 
dynamically and is also mentioned for the user's reference. It throws exceptions at invalid inputs.
To Invest a fixed amount into an existing portfolio containing multiple stocks using a specified
weight for stocks, type 11 and enter. It then asks user for portfolio name, commission fee,
total cost to be invested, date, stock and its percentage. The program will throw exception if
inflexible portfolios, or portfolios with wrong name are entered, if the commission is negative,
if the amount to be invested is 0 or less than 0, invalid stock name is entered or invalid 
share percentage is entered or invalid date is entered.
For dollar cost averaging, type 12 and enter, it will ask the user portfolio name, commission fee,
total cost to be invested, start date, end date, frequency type as in days or months, frequency
duration as in number, stock and its percentage. The program will throw exception if the commission
is negative, if the amount to be invested is 0 or less than 0, invalid stock name is entered 
or invalid share percentage is entered, or for invalid date, frequency type and duration. In 
dollar cost averaging, for portfolios, the user can enter existing portfolio and the operation will
be functioned and updated to it. If the user enters a new name, it will create a portfolio and 
perform the operation.

In GUI, the user sees menu options as radio buttons, where the use can choose an option and 
clicks go. In all features, the inputs are given in the format of form based type inputs.
There is a submit button which will perform the task, cancel button which will go back to the menu, 
clear button which will clear the user inputs. In creation of portfolio, invest using weights, and
dollar cost averaging, the user is given by default 1 stock symbol form box and 1 number of shares
or percentage of share box. If the user wishes to add additional stocks, it opens another form with 
2 inputs, stock symbol and number of shares/percentage. It has add or cancel option, after adding,
comes back to the previous form where the data user entered is still there, if the user wishes to 
add another stock, they can click on add additional stock button again and repeat as many number of
times the user wishes. The GUI throws pop up when a successful function is performed or when an
illegal argument exception is happened and then goes back to previous step. It usually takes a
couple of seconds for the result to be generated. From the user's perspective, it is a 
user-friendly, simple to use GUI and easy to use. 

### Third party library

Our program Java spring. A class is included in the util package named SpringUtilities. It provides
utility methods for creating form- or grid-style layouts with SpringLayout.
These utilities are used by several programs, such as  SpringBox and SpringCompactGrid.
Our program also uses Gson jar files for reading and writing values int to the json file.
The zipped version of the file is in the res for reference.

https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringGridProject/src/layout/SpringUtilities.java
https://jar-download.com/artifacts/com.google.code.gson/gson/2.8.2/source-code

### List of Stocks Supported

The program supports the list of all the stocks supported by the API. It fetches the details from API.

