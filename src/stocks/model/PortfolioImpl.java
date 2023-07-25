package stocks.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import stocks.util.StockValuesUtilityImpl;

/**
 * A class Portfolio under model which basically has a list of functions like
 * getTotalValueOfPortfolio, getCurrentBusinessDay where it gives value of last
 * business days when a day of closed market is entered.
 */
public class PortfolioImpl implements Portfolio {
  private String name;
  private boolean flexiblePortfolio = false;
  private List<Stock> stocks = new ArrayList<>();
  private List<InvestmentDetails> investmentDetailsList = new ArrayList<>();

  /**
   * Method to get invesment list details.
   *
   * @return the invesment details of the portfolio.
   */
  public List<InvestmentDetails> getInvestmentDetailsList() {
    return investmentDetailsList;
  }

  /**
   * Method to set invesment list details.
   *
   * @param investmentDetailsList is the invesment details of the portfolio.
   */
  public void setInvestmentDetailsList(List<InvestmentDetails> investmentDetailsList) {
    this.investmentDetailsList = investmentDetailsList;
  }

  /**
   * Method for differentiating flexible or infexible portfolio.
   *
   * @return if flexible portfolio is true or false.
   */
  public boolean isFlexiblePortfolio() {
    return flexiblePortfolio;
  }

  /**
   * Method for differentiating flexible or infexible portfolio.
   *
   * @param flexiblePortfolio for flexible portfolio is true or false.
   */
  public void setFlexiblePortfolio(boolean flexiblePortfolio) {
    this.flexiblePortfolio = flexiblePortfolio;
  }


  /**
   * Method to get string name.
   *
   * @return name is the name that is returned.
   */
  public String getName() {
    return name;
  }

  /**
   * Method to set string name.
   *
   * @param name that takes in the string name and sets it.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method to get stocks.
   *
   * @return returns the list of stocks.
   */
  public List<Stock> getStocks() {
    return stocks;
  }

  /**
   * Method to set stocks.
   *
   * @param stocks takes in the stocks as input and sets the stocks.
   */
  public void setStocks(List<Stock> stocks) {
    this.stocks = stocks;
  }



  /**
   * This method takes in input as String date and returns the result of individual stock price.
   *
   * @param date is the date that is taken as input to genarate individual stock value result.
   * @return result is the total value of individual stock price.
   */
  public String toString(String date) {
    if (date == null) {
      date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    String result = "PortFolio Name:" + getName() + "\n";
    result = result + "Stock List: \n";
    StockValuesUtilityImpl stockValuesUtilityImpl = new StockValuesUtilityImpl();
    for (Stock s : getStocks()) {
      double individualStockPrice = 0;
      try {
        individualStockPrice = stockValuesUtilityImpl.
                getStockPriceRealTime(s.getTicker(), date) * s.getNoOfShares();
      } catch (Exception e) {
        individualStockPrice = 0;
      }

      result = result + s.toString() + "\n";
      //result = result + String.format(
      // "Individual Stock Price as on %s:%.2f\n", date, individualStockPrice);
      result = result + "--\n";
    }
    return result;
  }
}