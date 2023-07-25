package stocks.util;

import stocks.model.Stock;

/**
 * An interface for StockValuesUtility which basically fetches the real
 * time data of the stocks from API. Here we have manually downloaded files for 4 different
 * stocks and are using them as the only available stock.
 */
public interface StockValuesUtility {

  /**
   * A method to get the price of a particular stock.
   *
   * @param stockName is name of stock.
   * @param date      is the date.
   * @return the stock price.
   */
  public double getStockPrice(String stockName, String date);

  /**
   * Method to get the real time price of the stock.
   *
   * @param stockName is the name of the stock.
   * @param date      is the date entered.
   * @return the real time value.
   */
  public double getStockPriceRealTime(String stockName, String date);

  /**
   * Method to get the real time price of the stock using stock symbol.
   *
   * @param stockSymbol is the stock symbol.
   * @return the real time value.
   */
  public String getStockPricesRealTime(String stockSymbol);

  /**
   * A method to get the stock details.
   *
   * @param stock is the symbol of stock.
   * @return the stockdetails.
   */
  public Stock getStockDetails(Stock stock) throws IllegalArgumentException;

}