package stocks.model;

/**
 * A class stock details stores various inputs os a stock like stock name, ticker symbol,
 * description,etc.
 */

public class StockDetails {

  private double noOfShares;

  private double price;

  private String stockDate;

  /**
   * A constructor for Stock details.
   *
   * @param noOfShares is the number of shares.
   * @param price      is the price of the stock.
   * @param stockDate  is the stock date.
   */
  public StockDetails(double noOfShares, double price, String stockDate) {
    this.noOfShares = noOfShares;
    this.price = price;
    this.stockDate = stockDate;
  }

  /**
   * Gets number of shares.
   *
   * @return the value of the shares.
   */
  public double getNoOfShares() {
    return noOfShares;
  }

  /**
   * Sets the number of shares.
   *
   * @param noOfShares is the value of number of shares.
   */
  public void setNoOfShares(double noOfShares) {
    this.noOfShares = noOfShares;
  }

  /**
   * Method to get price.
   *
   * @return the price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Method to set the price.
   *
   * @param price is the price of stock.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Method to get the stock date.
   *
   * @return stockdate -the date of the stocks.
   */
  public String getStockDate() {
    return stockDate;
  }

  /**
   * Method to set the date.
   *
   * @param stockDate is the date of the stocks,
   */
  public void setStockDate(String stockDate) {
    this.stockDate = stockDate;
  }

  /**
   * A to String method to return the stock date ie stock details which is number of shares,
   * stock date and the price.
   *
   * @return the stock date, number of shares and its price.
   */
  public String toString() {
    return stockDate + "," + noOfShares + "," + price;
  }
}

