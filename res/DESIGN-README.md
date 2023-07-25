
# CS 5010 Assignment 4 DESIGN-README

*Name:* Deepthi Varanasi and Anirudh Krishnan Venkatanathan.

*Email:* varanasi.d@northeastern.edu and venkatanathan.a@northeastern.edu

### DESIGN

In the previous assignments, the project was designed as a text based interface using MVC principle
that lets a user create and manipulate stock market portfolios. It has the following features

1. Create flexible and inflexible portfolios.
2. Examine composition of all or a particular portfolio.
3. Determine the total value of a portfolioImpl on a certain date.
4. Persist a portfolioImpl so that it can be saved and loaded.
5. Purchase or Sell Stocks in flexible portfolios.
6. Query the total cost basis of a portfolio on a certain date.
7. GetPerformance (Analysis of Portfolio) in order to achieve the requirements given.

In Assignment 4, we have inputted data from four different stocks in this assignment. The user can
only be able to use these 4 stocks while operating the features. However, from Assignment 5, the
usage of Alpha Vantage API to get the details of the stock values is integrated which is still
being continued, however the original idea of downloading stock details and inputting can still 
be supported. In the future, if any other API can be integrated into the code by adding a method
in the stock value utility class where all the API actions are held. All the portfolios are stored
in res folder under the AllPortFolio.json file.


This source code is built in package stocks and there are 4 subpackages app which has driver file,
controller which contains controller implementation and interface, model which has model interface,
implementation, util package which has implementation of API calls and view package which has
view implementation. 


A class diagram summarizing the design is also made along with a runnable JAR file with name Stock.jar

### DESIGN CHANGES

1. Two additional features are added which are Invest a fixed amount into an existing portfolio
   containing multiple stocks, using a specified weight for each stock in portfolio and creating 
   "start-to-finish" dollar-cost averaging as a single operation

2. On top of the text based interface that has been in use, now the support of Graphical user
   interface is also implemented with the following features, however the text bases interface 
   still works with the updated features. By default, the GUI is shown once the code is tun. To
   run the text file, while running the jar file, the user can simply add "text" to  the 
   java -jar Stocks.jar command. (`java -jar Stocks.jar` for GUI,`java -jar Stocks.jar text` 
   for text based interface)

   -Invest a fixed amount into an existing portfolio containing multiple stocks, 
    using a specified weight for each stock in the portfolio. 
   -Dollar cost averaging for a new flexible portfolio.

3. Controller has an interface and implementation as before. And additional changes were made to the
   goStock method of the controller in order to include extra menu options. Also, a new method for
   action listener is added in the controller for GUI

4. We have created an additional class and interface in the view package for GUI implementation,
   this helps us in keeping the tract of totalMoneyInvested and InvestmentDate that helps
   us in calculating the costBasis by including the commission fee.

5. We have added additional methods to the interface PortfolioDAO and implemented 
   them in the PortfolioDAOImpl such as :
     1.investFixedAmount   
     2.dollarCostAveraging

6. We used Alpha Vantage API in order to fetch the real time values for this assignment.
