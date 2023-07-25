package stocks.controller;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import stocks.app.StocksApp;

import static org.junit.Assert.assertEquals;

/**
 * Test for controller.
 */
public class UserControllerImplTest {

  private static final InputStream systemIn = System.in;
  private static final PrintStream systemOut = System.out;

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
          "13. Exit.\n";
  String initialPortfolio = "PortFolio Name:PF2FLEX\n" +
          "Stock List: \n" +
          "Stock Name: Amazon.com Inc\n" +
          "Stock Symbol:AMZN\n" +
          "Purchase Date:2022-11-28\n" +
          "No of shares:5.0\n" +
          "Individual Stock Price as on 2022-11-29:0.00\n" +
          "--\n" +
          "----PortFolio Name:PF1INFLEX\n" +
          "Stock List: \n" +
          "Stock Name: Alphabet Inc Class C\n" +
          "Stock Symbol:GOOG\n" +
          "Purchase Date:2022-11-28\n" +
          "No of shares:5.0\n" +
          "Individual Stock Price as on 2022-11-29:0.00\n" +
          "--\n" +
          "----\n";

  public static void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void exitTestPasses() {
    String userInput = "13\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String expectedOutput = menu;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);

    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test
  public void showValueatCertainDatePasses() {
    String userInput = "6\nPF2FLEX,2022-11-16\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio name and date (Eg. MyPF1,2022-04-29)";
    String valueOfMyPF2 = "PortFolio Name:PF2FLEX\n" +
            "Stock List: \n" +
            "Stock Name: Amazon.com Inc\n" +
            "Stock Symbol:AMZN\n" +
            "Purchase Date:2022-11-28\n" +
            "No of shares:5.0\n" +
            "Individual Stock Price as on 2022-11-16:485.60\n" +
            "--\n" +
            "Total value of portfolio PF2FLEX is $0.0";
    String expectedOutput = menu + message + valueOfMyPF2;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test
  public void showValueatCertainDatePasses2() {
    String userInput = "6\nPF1INFLEX,2022-11-14\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio name and date (Eg. MyPF1,2022-04-29)";
    String valueOfMyPF2 = "PortFolio Name:PF1INFLEX\n" +
            "Stock List: \n" +
            "Stock Name: Alphabet Inc Class C\n" +
            "Stock Symbol:GOOG\n" +
            "Purchase Date:2022-11-28\n" +
            "No of shares:5.0\n" +
            "Individual Stock Price as on 2022-11-14:480.15\n" +
            "--\n" +
            "Total value of portfolio PF1INFLEX is $0.0";
    String expectedOutput = menu + message + valueOfMyPF2;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void showValueatCertainDateFails() {
    String userInput = "6\nInvalidStocks,2022-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void showValueatCertainDateFails2() {
    String userInput = "6\nPF2FLEX,2022-05\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void showValueatCertainDateFails3() {
    String userInput = "6\nPF2FLEX,1956-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void showValueatCertainDateFails4() {
    String userInput = "6\nInvalidStocks,2022-03-25\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }


  @Test(expected = IllegalArgumentException.class)
  public void showValueatCertainDateFails8() {
    String userInput = "6\nPF2FLEX,2023-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }


  @Test(expected = IllegalArgumentException.class)
  public void invalidInputTest() {
    String userInput = "20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test
  public void getAllValuesTestPasses() {
    String userInput = "1\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String expectedOutput = menu + initialPortfolio;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);

    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test
  public void getAllValuesTestWhenNoPortFolio() {
    String userInput = "1\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String expectedOutput = menu;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    String withoutPortfolio = menu;
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(withoutPortfolio, expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getAllValuesTestFails() {
    String userInput = "";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();

  }

  @Test
  public void viewSpecificPortfolioTestPasses() {
    String userInput = "2\nPF2FLEX\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String question = "Enter portfolio name";
    String myPF1 = "PortFolio Name:PF3Flexible\n" +
            "Stock List: \n" +
            "Stock Name: Alphabet Inc Class C\n" +
            "Stock Symbol:GOOG\n" +
            "Purchase Date:2022-11-16\n" +
            "No of shares:5.0\n" +
            "Individual Stock Price as on 2022-11-17:0.00\n" +
            "--";
    String expectedOutput = menu + question + myPF1;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void viewSpecificPortfolioFails() {
    String userInput = "2\nInvalidName\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();


  }

  @Test(expected = NoSuchElementException.class)
  public void viewSpecificPortfolioFails3() {
    String userInput = "2\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }


  @Test
  public void createNewPortfolioPasses() {
    String userInput = "3\nSampleTest,5,GOOG|5,AMZN|5,NFLX|5";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio info (Eg. MyPF1,NFLX|7,GOOG|12)";
    String expectedOutput = menu + message;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFails() {
    String userInput = "3\nSampleTest,5,GOOG|5";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio info (Eg. MyPF1,5,NFLX|7,GOOG|12)";
    String illegalMessage = "Portfolio already exists.";
    String expectedOutput = menu + message + illegalMessage;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFails2() {
    String userInput = "3\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();

  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFails3() {
    String userInput = "3\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFails4() {
    String userInput = "3\nSampleTest3,-5,GOOG|5\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();

  }

  @Test
  public void createNewPortfolioFlexiPasses() {
    String userInput = "4\nSampleTest2,5,GOOG|5,AMZN|5,NFLX|5)";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio info (Eg. MyPF1,5,NFLX|7,GOOG|12)";
    String expectedOutput = menu + message;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFlexiFails() {
    String userInput = "4\nMyPF4Flexible,5,GOOG|5";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio info (Eg. MyPF1,5,NFLX|7,GOOG|12)";
    String illegalMessage = "Portfolio already exists.";
    String expectedOutput = menu + message + illegalMessage;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFlexiFails2() {
    String userInput = "4\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();

  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFlexiFails3() {
    String userInput = "4\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewPortfolioFlexiFails4() {
    String userInput = "4\nSampleTest5,-5,GOOG|5\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();

  }


  @Test
  public void getValueatCertainDatePasses() {
    String userInput = "5\nPF2FLEX,2022-11-11\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio name and date (Eg. MyPF1,2022-04-29)";
    String valueOfMyPF2 = "PortFolio Name:PF2FLEX\n" +
            "Stock List: \n" +
            "Stock Name: Amazon.com Inc\n" +
            "Stock Symbol:AMZN\n" +
            "Purchase Date:2022-11-28\n" +
            "No of shares:5.0\n" +
            "Individual Stock Price as on 2022-11-11:503.95\n" +
            "--\n" +
            "Total value of portfolio PF2FLEX is $503.95000000000005";
    String expectedOutput = menu + message + valueOfMyPF2;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test
  public void getValueatCertainDatePasses2() {
    String userInput = "5\nPF1INFLEX,2022-11-16\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio name and date (Eg. MyPF1,2022-04-29)";
    String valueOfMyPF2 = "PortFolio Name:PF1INFLEX\n" +
            "Stock List: \n" +
            "Stock Name: Alphabet Inc Class C\n" +
            "Stock Symbol:GOOG\n" +
            "Purchase Date:2022-11-28\n" +
            "No of shares:5.0\n" +
            "Individual Stock Price as on 2022-11-16:494.95\n" +
            "--\n" +
            "Total value of portfolio PF1INFLEX is $494.95";
    String expectedOutput = menu + message + valueOfMyPF2;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getValueatCertainDateFails() {
    String userInput = "5\nInvalidStocks,2022-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getValueatCertainDateFails2() {
    String userInput = "5\nPF1INFLEX,2022-05\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getValueatCertainDateFails3() {
    String userInput = "5\nPF1INFLEX,1956-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }


  @Test(expected = IllegalArgumentException.class)
  public void getValueatCertainDateFails7() {
    String userInput = "5\nPF1INFLEX,\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getValueatCertainDateFails8() {
    String userInput = "5\nPF1INFLEX,2023-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test
  public void buyStockTest() {
    String userInput = "7\nPF2FLEX,5,GOOG,12,2022-11-10\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter information to purchase stocks (Eg. MyPF1,GOOG,12,2022-11-10)";
    String expectedOutput = menu + message;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);

    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest2() {
    String userInput = "7\nPF2FLEX,5,AMZM,10,2022-11-11,GOOG,10,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest3() {
    String userInput = "7\nPF4Flexible,AMZM,0,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest4() {
    String userInput = "7\nPF4Flexible,AMZM,10.5,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest5() {
    String userInput = "7\nPF4Flexible,AMZN,-10,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest6() {
    String userInput = "7\nPF4Flexible,AMZM,10,1955-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest7() {
    String userInput = "7\nPF4Flexible,AMZM,10,2023-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest8() {
    String userInput = "7\nPF4Flexible,AMZM,10,2022-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void buyStockTest9() {
    String userInput = "7\nPF2FLEX,-5,AMZM,10,2022-11-11,GOOG,10,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test
  public void sellStockTest() {
    String userInput = "8\nPF2FLEX,5,GOOG,1,2022-11-16\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter information to sell stocks (Eg. MyPF1,GOOG,12,2022-11-10):";
    String expectedOutput = menu + message;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);

    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);


  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest2() {
    String userInput = "8\nPF4Flexibl,5,GOOG,1,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest3() {
    String userInput = "8\nPF2FLEX,5,GOOG,0,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest4() {
    String userInput = "8\nPF2FLEX,5,GOOG,1.5,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest5() {
    String userInput = "8\nPF2FLEX,5,GOOG,-1,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest6() {
    String userInput = "8\nPF2FLEX,5,GOOG,1,1955-11-11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest7() {
    String userInput = "8\nPF2FLEX,GOOG,5,1,2023-11-11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest8() {
    String userInput = "8\nPF2FLEX,5,GOOG,1,2022-11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest9() {
    String userInput = "8\nPF2FLEX,5,GOOG,50,2022-11-11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest10() {
    String userInput = "8\nPF2FLEX,-5,GOOG,1,2022-11-11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sellStockTest11() {
    String userInput = "8\nPF2FLEX,5,GOOG,1,2022-11-11,AMZN,1,2022-11-11\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test
  public void costBasisCertainDatePasses() {
    String userInput = "9\nPF2FLEX,2022-11-28\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio name and date " +
            "(Eg. MyPF1,2022-04-29)";
    String valueOfMyPF2 = "Cost Basis of portfolio PF2FLEX is $1589.79";
    String expectedOutput = menu + message + valueOfMyPF2;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test
  public void costBasisCertainDatePasses2() {
    String userInput = "9\nPF3Flexible,2022-10-16\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter comma separated portfolio name and date " +
            "(Eg. MyPF1,2022-04-29)";
    String valueOfMyPF2 = "Cost Basis of portfolio PF2FLEX is $0.0";
    String expectedOutput = menu + message + valueOfMyPF2;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void costBasisCertainDateFails() {
    String userInput = "9\nInvalidStocks,2022-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void costBasisCertainDateFails2() {
    String userInput = "9\nPF2FLEX,2022-05\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void costBasisCertainDateFails3() {
    String userInput = "9\nPF2FLEX,1956-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void costBasisatCertainDateFails4() {
    String userInput = "9\nPF2FLEX,2022-03-25\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }


  @Test(expected = IllegalArgumentException.class)
  public void costBasisCertainDateFails8() {
    String userInput = "9\nPF2FLEX,2023-05-06\n11";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test
  public void displayTest() {
    String userInput = "10\nPF4Flexible,2022-11-11,2022-11-16\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter portfolio along with period and type " +
            "(Eg. MyPF1,2022-07-10,2022-11-10):";
    String output = "Performance of Portfolio PF4Flexible from 2022-11-11 to 2022-11-16\n" +
            "2022-11-11 : **********\n" +
            "2022-11-12 : **********\n" +
            "2022-11-13 : **********\n" +
            "2022-11-14 : **********\n" +
            "2022-11-15 : **********\n" +
            "2022-11-16 : ***************\n" +
            "Scale: * = $200";
    String expectedOutput = menu + message + output;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);

    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test
  public void fixedAmountTest() {
    String userInput = "11\nPF2FLEX,5,5000,2022-10-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter portfolio along with commission,total cost, date, stock and its " +
            "percentage (Eg. MyPF1,7,2000,2022-07-10,GOOG|40,AMZN|40,AAPL|20)";
    String expectedOutput = menu + message;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);

    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails() {
    String userInput = "11\nInvalidPort,5,5000,2022-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails2() {
    String userInput = "11\nPF2FLEX,-5,5000,2022-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails3() {
    String userInput = "11\nPF2FLEX,5,-5000,2022-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails11() {
    String userInput = "11\nPF2FLEX,0,5000,2022-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails4() {
    String userInput = "11\nPF2FLEX,5,0,2022-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails5() {
    String userInput = "11\nPF2FLEX,5,5000,2023-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails6() {
    String userInput = "11\nPF2FLEX,5,5000,1956-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails7() {
    String userInput = "11\nPF2FLEX,5,5000,202-11-01,GOOG|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails8() {
    String userInput = "11\nPF2FLEX,5,5000,2022-11-01,GOO|50,AMZN|30,AAPL|20\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails9() {
    String userInput = "11\nPF2FLEX,5,5000,2022-11-01,GOOG|90,AMZN|5,AAPL|6\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails10() {
    String userInput = "11\nPF2FLEX,5,5000,2022-11-01,GOOG|50,AMZN|30,AAPL|19\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void fixedAmountTestFails12() {
    String userInput = "11\nPF2FLEX,5,5000,2022-11-01,GOOG|-50,AMZN|60,AAPL|90\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test
  public void DCATest() {
    String userInput = "12\nDCA1,5,5000,2022-08-01,2022-11-28,DAYS,15,GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    String message = "Enter portfolio along with commission, total cost, start date," +
            " end date,frequency details, stock and its percentage (Eg. MyPF1,7,2000," +
            "2022-07-01,2022-11-02,DAYS,10,GOOG|40,AMZN|40,AAPL|20)";
    String expectedOutput = menu + message;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);

    restoreSystemInputOutput();
    assertEquals(baos.toString(), expectedOutput);
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails() {
    String userInput = "12\nPF2FLEX,5,5000,2022-08-01,2022-11-28,DAYS,15," +
            "GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails2() {
    String userInput = "12\nPF2FLEX,-5,5000,2022-08-01,2022-11-28,DAYS,15," +
            "GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails12() {
    String userInput = "12\nPF2FLEX,0,5000,2022-08-01,2022-11-28,DAYS,15," +
            "GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails3() {
    String userInput = "12\nPF2FLEX,5,-5000,2022-08-01,2022-11-28,DAYS,15" +
            ",GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails4() {
    String userInput = "12\nPF2FLEX,5,0,2022-08-01,2022-11-28,DAYS,15,GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails5() {
    String userInput = "12\nPF2FLEX,5,5000,022-08-01,2022-11-28,DAYS,15," +
            "GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails6() {
    String userInput = "12\nPF2FLEX,5,5000,1930-08-01,2022-11-28,DAYS,15" +
            ",GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails7() {
    String userInput = "12\nPF2FLEX,5,5000,2022-11-28,2022-08-01,DAYS,15," +
            "GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails8() {
    String userInput = "12\nPF2FLEX,5,5000,2022-08-01,2022-11-28,YEARS,-5," +
            "GOOG|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFail9() {
    String userInput = "12\nPF2FLEX,5,5000,2022-08-01,2022-11-28,DAYS,15," +
            "GOO|50,AMZN|45,AAPL|5\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails10() {
    String userInput = "12\nPF2FLEX,5,5000,2022-08-01,2022-11-28,DAYS,15," +
            "GOOG|50,AMZN|45,AAPL|6\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails11() {
    String userInput = "12\nPF2FLEX,5,5000,2022-08-01,2022-11-28,DAYS,15," +
            "GOOG|50,AMZN|45,AAPL|4\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }

  @Test(expected = IllegalArgumentException.class)
  public void DCATestFails13() {
    String userInput = "12\nPF2FLEX,5,5000,2022-08-01,2022-11-28,DAYS,15," +
            "GOOG|-30,AMZN|70,AAPL|60\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    StocksApp.main(null);
    restoreSystemInputOutput();
  }


}