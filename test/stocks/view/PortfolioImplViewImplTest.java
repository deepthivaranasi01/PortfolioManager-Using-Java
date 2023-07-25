package stocks.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import stocks.model.PortfolioImpl;


/**
 * Test for view.
 */
public class PortfolioImplViewImplTest {

  PortfolioImpl portfolio = new PortfolioImpl();


  @Test
  public void displayPortfolioTest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,2022-11-11");
    assertEquals("PortFolio Name:null\n" +
            "Stock List: \n", String.valueOf(portfolio.toString("2022-11-11")));

  }

  @Test
  public void showPortfolioOptionsAndGetInputTest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,2022-11-11");
    String menu = "1. Composition of all portfolio.\n" +
            "2. Composition of specific portfolio.\n" +
            "3. Create an inflexible portfolio.\n" +
            "4. Create a Flexible portfolio.\n" +
            "5. Get value of a portfolio as on a given date.\n" +
            "6. Show portfolio value as on a given date depending on purchase dates.\n" +
            "7. Buy Stocks.\n" +
            "8. Sell Stocks.\n" +
            "9. Get the cost basis by a specific date (i.e. the total amount of money invested" +
            " in a portfolio)\n" +
            "10. Display performance of a portfolio.\n" +
            "11. Invest a fixed amount into an existing portfolio containing multiple stocks," +
            " using a specified weight for each stock in the portfolio.\n" +
            "12. Dollar cost averaging for a new flexible portfolio.\n" +
            "13. Exit.";
    assertEquals("", menu);

  }

  @Test
  public void getPortfolioDetails() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    String message = "Enter comma separated portfolio info (Eg. MyPF1,1,NFLX|7,GOOG|12";
    assertEquals("Enter comma separated portfolio info " +
            "(Eg. MyPF1,1,NFLX|7,GOOG|12)", message);

  }

  @Test
  public void getPortfolioNameAndDate() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    String message = "Enter comma separated portfolio name and date (Eg. MyPF1,2022-04-29)";
    assertEquals("Enter comma separated portfolio name and date " +
            "(Eg. MyPF1,2022-04-29)", message);

  }

  @Test
  public void displayPortfolioValue() throws IOException {
    String portfolioName = "MyPF1";
    double totalValue = 4639.4;
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    String message = "Total value of portfolio)" + " " + portfolioName + " " +
            "is" + " " + totalValue;

    assertEquals("Total value of portfolio)" + " " + portfolioName + " " +
            "is" + " " + totalValue, message);

  }

  @Test
  public void displayCostBasis() throws IOException {
    String portfolioName = "MyPF1";
    double totalValue = 4639.4;
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    String message = "Cost Basis of portfolio" + " " + portfolioName + " " + "is" + " "
            + totalValue;
    fileWriter.append("MyPF1,2022-11-11");
    assertEquals("Cost Basis of portfolio" + " " + portfolioName + " " + "is " +
            totalValue, message);

  }


  @Test
  public void getPurchaseOrSellStock() throws IOException {
    String purchaseOrSell = "Purchase";
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,2022-11-11");
    String message = "Enter information to " + purchaseOrSell + " stocks" +
            " (Eg. MyPF1,GOOG,12,2022-11-10)";

    assertEquals("Enter information to Purchase stocks " +
            "(Eg. MyPF1,GOOG,12,2022-11-10)", message);

  }

  @Test
  public void getPortfolioInputForPerformance() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,2022-11-11");
    String message = "Enter portfolio along with period and type " +
            "(Eg. MyPF1,2022-07-10,2022-11-10,EVERY_DAY)";
    assertEquals("Enter portfolio along with period and type " +
            "(Eg. MyPF1,2022-07-10,2022-11-10,EVERY_DAY)", message);

  }

  @Test
  public void investAmountTest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,2022-11-11");
    String message = "Enter portfolio along with commission,total cost, date, stock and its " +
            "percentage (Eg. MyPF1,7,2000,2022-07-10,GOOG|40,AMZN|40,AAPL|20)";
    assertEquals("Enter portfolio along with commission,total cost," +
            " date, stock and its percentage (Eg. MyPF1,7,2000,2022-07-10," +
            "GOOG|40,AMZN|40,AAPL|20)", message);
  }


  @Test
  public void dollarCostAverage() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,2022-11-11");
    String message = "Enter portfolio along with commission, total cost, start date, end date," +
            " frequency details, stock and its percentage " +
            "(Eg. MyPF1,7,2000,2022-07-01,2022-11-02,DAYS,10,GOOG|40,AMZN|40,AAPL|20)0";
    assertEquals("Enter portfolio along with commission, " +
            "total cost, start date, end date, frequency details, " +
            "stock and its percentage (Eg. MyPF1,7,2000,2022-07-01,2022-11-02,DAYS,10," +
            "GOOG|40,AMZN|40,AAPL|20)", message);
  }
}

