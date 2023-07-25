package stocks.model;

import java.util.HashMap;
import java.util.List;

/**
 * An interface portfolioDAO (Data Access Object) which reads stocks, gets all portfolios,
 * saves portfolios, creates a new portfolio and gets the total value of the portfolio.
 */
public interface PortfolioDAO {

  /**
   * A method that helps in checking if the portfolio name exits and return the number of stocks
   * associated with it, it throws an exception if a portfolio name entered doesn't exist.
   *
   * @param portfolioName is the parameter is the portfolio filename that is meant to be read.
   * @return list of stocks with the portfolioName.
   */
  public List<Stock> read(String portfolioName);


  /**
   * Method to get value of all portfolios.
   *
   * @return hashmap of all the portfolios.
   */
  public HashMap<String, PortfolioImpl> getAllPortFolios();

  /**
   * Method to save portfolios.
   *
   * @param portfolioImpl is the input parameter taken to save.
   * @param action        is the action needed to save.
   */
  public void save(PortfolioImpl portfolioImpl, String action);

  /**
   * Method for creating a portfolio which cannot be edited or modified again.
   *
   * @param portfolioDetails is taken as input to perform the creation of portfolio.
   */
  public void createPortfolio(String portfolioDetails);

  /**
   * Method for creating a portfolio which can be edited or modified by buying and selling stocks.
   *
   * @param portfolioDetails is taken as input to perform the creation of portfolio.
   */
  public void createFlexiblePortfolio(String portfolioDetails);

  /**
   * Method to get the total value of a particular portfolio.
   *
   * @param portfolioName is the name of the portfolio.
   * @param date          is the date you want to get the value from.
   * @return the totalvalue of the portfolio.
   */
  public double getTotalValueOfPortfolio(String portfolioName, String date);

  /**
   * Method to get the total value of a particular portfolio on a particular date based
   * on purchase date.
   *
   * @param portfolioName is the name of the portfolio.
   * @param date          is the date you want to get the value from.
   * @return the totalvalue of the portfolio.
   */
  public double calculateTotalValueOfPortfolio(String portfolioName, String date);

  /**
   * Method to purchase stocks.
   *
   * @param portfolioName is the name of the portfolio.
   * @param commission    is the commission fee.
   * @param ticker        is the ticker symbol.
   * @param noOfShares    is the number of shares.
   * @param date          is the date the user enters.
   */
  public void purchaseStocks(String portfolioName, String commission, String ticker,
                             double noOfShares, String date);

  /**
   * Method to sell stocks.
   *
   * @param portfolioName is the name of the portfolio.
   * @param commission    is the commission fee.
   * @param ticker        is the ticker symbol.
   * @param noOfShares    is the number of shares.
   * @param date          is the date the user enters.
   */
  public void sellStocks(String portfolioName, String commission, String ticker,
                         double noOfShares, String date);

  /**
   * Method to determine total cost basis.
   *
   * @param portfolioName is the name of the portfolio.
   * @param date          is the ticker symbol.
   * @return the total cost basis.
   */
  public double totalCostBasis(String portfolioName, String date) throws IllegalArgumentException;

  /**
   * Method to print the performance of the matrix in graphical format.
   *
   * @param portfolioName is the portfolio name.
   * @param fromTime      is the front date.
   * @param toTime        is the rear date.
   * @return the performance scale.
   */
  public int getPerformance(String portfolioName, String fromTime, String toTime);

  /**
   * Method to Invest a fixed amount into an existing portfolio containing
   * multiple stocks, using a specified weight for each stock in the portfolio.
   *
   * @param portfolioName          is the portfolio name.
   * @param commission             is the commission fee.
   * @param totalCost              is the total cost invested by the user.
   * @param date                   is the date entered by user.
   * @param tickerNPercentagesList is the ticker percentage mentioned by the user.
   */
  public void investFixedAmount(String portfolioName, String commission,
                                double totalCost, String date, String tickerNPercentagesList);

  /**
   * Method for creating a new portfolio and performing dollar cost averaging.
   *
   * @param portfolioName          is the portfolio name.
   * @param commission             is the commission fee.
   * @param totalCost              is the total cost invested by the user.
   * @param startDate              is the start date entered by user.
   * @param endDate                is the end date mentioned by user.
   * @param frequencyType          is the frequency type whether in months or days entered by user.
   * @param frequencyValue         is the value of frequency entered by user
   * @param tickerNPercentagesList is the ticker percentage mentioned by the user.
   */
  public void dollarCostAveraging(String portfolioName, String commission, double totalCost,
                                  String startDate, String endDate, String frequencyType,
                                  int frequencyValue, String tickerNPercentagesList);


}