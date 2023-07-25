package stocks.view;

import java.io.IOException;
import java.util.HashMap;

import stocks.model.PortfolioImpl;


/**
 * This is the view class of the MVC design for stocks. View is basically the code which the
 * client gets and works with. This only has scanner functions to get the
 * input data from the clients. View represents the visualization of the data that model contains.
 * This is the text based view of Stocks.
 */
public class PortfolioViewImpl implements PortfolioView {
  Appendable out;

  /**
   * A constructor is created to append the output.
   *
   * @param output is the output parameter
   */
  public PortfolioViewImpl(Appendable output) {
    this.out = output;
  }

  /**
   * Method to display the details of portfolio on particular date.
   *
   * @param portfolioImpl is the portfolio details.
   * @param date          is the date.
   * @throws IOException when invalid inputs are entered.
   */
  public void displayPortfolio(PortfolioImpl portfolioImpl, String date) throws IOException {
    this.out.append(portfolioImpl.toString(date));
  }

  /**
   * Method to display the details of all portfolios and stocks all together.
   *
   * @param portfolioList is the portfoliolist.
   * @throws IOException when invalid inputs are entered.
   */
  public void displayAllPortfolio(HashMap<String, PortfolioImpl> portfolioList)
          throws IOException {
    for (String s : portfolioList.keySet()) {
      this.out.append(portfolioList.get(s).toString(null));
      this.out.append("----");
    }
  }

  /**
   * This is the initial input screen guiding user what command does what.
   *
   * @throws IOException when invalid inputs are entered.
   */
  public void showPortfolioOptionsAndGetInput() throws IOException {
    this.out.append("1. Composition of all portfolio." + "\n");
    this.out.append("2. Composition of specific portfolio." + "\n");
    this.out.append("3. Create an inflexible portfolio." + "\n");
    this.out.append("4. Create a Flexible portfolio." + "\n");
    this.out.append("5. Get value of a portfolio as on a given date." + "\n");
    this.out.append("6. Show portfolio value as on a given date depending on " +
            "purchase dates." + "\n");
    this.out.append("7. Buy Stocks." + "\n");
    this.out.append("8. Sell Stocks." + "\n");
    this.out.append("9. Get the cost basis by a specific date (i.e. the total " +
            "amount of money invested in a portfolio)" + "\n");
    this.out.append("10. Display performance of a portfolio." + "\n");
    this.out.append("11. Invest a fixed amount into an existing portfolio containing " +
            "multiple stocks, using a specified weight for each stock in the portfolio." + "\n");
    this.out.append("12. Dollar cost averaging for a new flexible portfolio." + "\n");
    this.out.append("13. Exit." + "\n");
  }

  /**
   * This is displayed after the user decides to enter details of a new portfolio.
   *
   * @throws IOException when invalid inputs are entered.
   */
  public void getPortfolioDetails() throws IOException {
    this.out.append("Enter comma separated portfolio info (Eg. MyPF1,5,NFLX|7,GOOG|12)");
  }

  /**
   * This is displayed after the user decides to get value of a portfolio on a particular date.
   *
   * @throws IOException when invalid inputs are entered.
   */
  public void getPortfolioNameAndDate() throws IOException {
    this.out.append("Enter comma separated portfolio name and date (Eg. MyPF1,2022-04-29)");
  }

  /**
   * This is displayed after the user decides to get value of a portfolio.
   *
   * @param totalValue    is the total value.
   * @param portfolioName is the name of the portfolio.
   * @throws IOException when invalid inputs are entered.
   */
  public void displayPortfolioValue(String portfolioName, double totalValue) throws IOException {
    this.out.append("Total value of portfolio" + " " + portfolioName +
            " " + "is" + " $" + totalValue);
  }

  /**
   * Method to display the cost basis.
   *
   * @param portfolioName name of the portfolio.
   * @param totalValue    total value of the portfolio.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void displayCostBasis(String portfolioName, double totalValue) throws IOException {
    this.out.append("Cost Basis of portfolio" + " " + portfolioName + " " +
            "is" + " $" + totalValue);
  }

  /**
   * This is displayed after the user decides to get value of a particular portfolio by its name.
   *
   * @throws IOException when invalid inputs are entered.
   */

  public void getSpecificPortfolioName() throws IOException {
    this.out.append("Enter portfolio name");
  }

  /**
   * Method for getting purchase or sell stock.
   *
   * @param purchaseOrSell for purchasing or selling stock.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getPurchaseOrSellStock(String purchaseOrSell) throws IOException {
    this.out.append("Enter information to " + purchaseOrSell + " stocks " +
            "(Eg. MyPF1,5,GOOG,12,2022-11-10)");
  }

  /**
   * Method to get the performance of the portfolio.
   *
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getPortfolioInputForPerformance() throws IOException {
    this.out.append("Enter portfolio along with period and type " +
            "(Eg. MyPF1,2022-07-10,2022-11-10)");

  }

  /**
   * Method to Invest a fixed amount into an existing portfolio containing
   * multiple stocks, using a specified weight for each stock in the portfolio.
   *
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getInputForInvestFixedAmount() throws IOException {
    this.out.append("Enter portfolio along with commission,total cost, date, stock and " +
            "its percentage (Eg. MyPF1,7,2000,2022-07-10,GOOG|40,AMZN|40,AAPL|20)");

  }

  /**
   * Method for creating a new portfolio and performing dollar cost averaging.
   *
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getInputForDollarCostAveraging() throws IOException {
    this.out.append("Enter portfolio along with commission, total cost, start date, end date," +
            " frequency details, stock and its percentage (Eg. MyPF1,7,2000,2022-07-01," +
            "2022-11-02,DAYS,10,GOOG|40,AMZN|40,AAPL|20)");

  }

  /**
   * Method to print the graph.
   *
   * @param portfolioName is the portfolio name.
   * @param fromTime      is the start date that the user enters.
   * @param toTime        is the end date that the user enters.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void printGraph(String portfolioName, String fromTime, String toTime) throws IOException {
    this.out.append("Performance of Portfolio" + " " + portfolioName + " " +
            "from" + " " + fromTime + " " + "to" + " " + toTime + "\n");
  }

  /**
   * Method to print the scale of the graph.
   *
   * @param scale is the scale in $.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void printScale(int scale) throws IOException {
    this.out.append("Scale: * = $" + scale);
  }
}
