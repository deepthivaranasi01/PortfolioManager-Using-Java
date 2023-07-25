package stocks.view;

import java.io.IOException;
import java.util.HashMap;

import stocks.model.PortfolioImpl;

/**
 * PortfolioView is an interface that has methods set Total value,displayPortfolio,
 * displayAllPortfolio,ShowPortfolioOptions,getPortfolioDetails,getSpecificPortfolioName
 * methods that help us in getting the required view details.
 */
public interface PortfolioView {

  /**
   * Method to display the details of portfolio on particular date.
   *
   * @param portfolioImpl is the portfolio details.
   * @param date          is the date.
   * @throws IOException if data is invalid.
   */
  public void displayPortfolio(PortfolioImpl portfolioImpl, String date) throws IOException;

  /**
   * Method to display the details of all portfolios and stocks all together.
   *
   * @param portfolioList is the portfoliolist.
   * @throws IOException if data is invalid.
   */
  public void displayAllPortfolio(HashMap<String, PortfolioImpl> portfolioList) throws IOException;

  /**
   * This is the initial input screen guiding user what command does what.
   *
   * @throws IOException if data is invalid.
   */

  public void showPortfolioOptionsAndGetInput() throws IOException;

  /**
   * This is displayed after the user decides to enter details of a new portfolio.
   *
   * @throws IOException if data is invalid.
   */
  public void getPortfolioDetails() throws IOException;

  /**
   * This is displayed after the user decides to get value of a portfolio on a particular date.
   *
   * @throws IOException if data is invalid.
   */

  public void getPortfolioNameAndDate() throws IOException;

  /**
   * This is displayed after the user decides to get value of a portfolio.
   *
   * @param totalValue    is the total value.
   * @param portfolioName is the name of the portfolio.
   * @throws IOException if data is invalid.
   */
  public void displayPortfolioValue(String portfolioName, double totalValue) throws IOException;

  /**
   * Method to display the cost basis.
   *
   * @param portfolioName name of the portfolio.
   * @param totalValue    total value of the portfolio.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void displayCostBasis(String portfolioName, double totalValue) throws IOException;

  /**
   * This is displayed after the user decides to get value of a particular portfolio by its name.
   *
   * @throws IOException if data entered is invalid.
   */
  public void getSpecificPortfolioName() throws IOException;

  /**
   * Method for getting purchase or sell stock.
   *
   * @param purchaseOrSell for purchasing or selling stock.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getPurchaseOrSellStock(String purchaseOrSell) throws IOException;

  /**
   * Method to get the performance of the portfolio.
   *
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getPortfolioInputForPerformance() throws IOException;

  /**
   * Method to print the graph.
   *
   * @param portfolioName is the portfolio name.
   * @param fromTime      is the start date that the user enters.
   * @param toTime        is the end date that the user enters.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void printGraph(String portfolioName, String fromTime, String toTime) throws IOException;

  /**
   * Method to print the scale of the graph.
   *
   * @param scale is the scale in $.
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void printScale(int scale) throws IOException;

  /**
   * Method to Invest a fixed amount into an existing portfolio containing
   * multiple stocks, using a specified weight for each stock in the portfolio.
   *
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getInputForInvestFixedAmount() throws IOException;


  /**
   * Method for creating a new portfolio and performing dollar cost averaging.
   *
   * @throws IOException throws exception when invalid inputs are passed.
   */
  public void getInputForDollarCostAveraging() throws IOException;


}



