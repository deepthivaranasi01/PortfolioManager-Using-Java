package stocks.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class stock under method that stores various inputs os a stock like stock name, ticker symbol,
 * description,etc.
 */
public class Stock {

  private String ticker;
  private double purchasePrice;
  private String purchaseDate;
  private double noOfShares;
  private String name;
  private String description;
  private String exchange;
  private String currency;
  private String country;
  private String sector;

  private List<StockDetails> purchaseDetailsList = new ArrayList();
  private List<StockDetails> sellDetailsList = new ArrayList();
  private StockDetails lastPurchaseDetails;

  /**
   * Constructor to get purchase price, ticker symbol, purchase date and number of shares.
   *
   * @param purchasePrice  is the purchasePrice
   * @param ticker         is the symbol for stock.
   * @param purchaseDate   is date of purchaseDate.
   * @param noOfShares     is number of shares.
   * @param purchaseOrSell is parameter purchase or sell.
   */
  public Stock(double purchasePrice, String ticker, String purchaseDate,
               double noOfShares, String purchaseOrSell) {
    this.purchasePrice = purchasePrice;
    this.ticker = ticker;
    this.purchaseDate = purchaseDate;
    this.noOfShares = noOfShares * ("PURCHASE".equals(purchaseOrSell) ? 1 : -1);
    if ("PURCHASE".equals(purchaseOrSell)) {
      this.lastPurchaseDetails = new StockDetails(noOfShares, purchasePrice, purchaseDate);
      this.purchaseDetailsList.add(lastPurchaseDetails);
    } else {
      this.sellDetailsList.add(new StockDetails(noOfShares, purchasePrice, purchaseDate));
    }

  }

  /**
   * Constructor to get purchase price, ticker symbol, purchase date and number of shares.
   *
   * @param purchasePrice is the purchasePrice
   * @param ticker        is the symbol for stock.
   * @param purchaseDate  is date of purchaseDate.
   * @param noOfShares    is number of shares.
   */
  public Stock(double purchasePrice, String ticker, String purchaseDate, double noOfShares) {
    this(purchasePrice, ticker, purchaseDate, noOfShares, "PURCHASE");
  }

  /**
   * Method for stock details.
   *
   * @return returns the stock details of last purchase.
   */
  public StockDetails getLastPurchaseDetails() {
    return lastPurchaseDetails;
  }

  /**
   * Method for setting stock details of last purchase.
   *
   * @param lastPurchaseDetails the stock details of last purchase.
   */
  public void setLastPurchaseDetails(StockDetails lastPurchaseDetails) {
    this.lastPurchaseDetails = lastPurchaseDetails;
  }

  /**
   * Method to add stock to portfolio.
   *
   * @param inputStock is the stock to be added.
   */
  public void add(Stock inputStock) {
    this.noOfShares = this.noOfShares + inputStock.noOfShares;
    this.purchasePrice = inputStock.purchasePrice;
    this.purchaseDate = inputStock.purchaseDate;
    this.lastPurchaseDetails = new StockDetails(inputStock.noOfShares,
            inputStock.purchasePrice, inputStock.purchaseDate);
    this.purchaseDetailsList.add(lastPurchaseDetails);
  }

  /**
   * Method to delete stock to portfolio.
   *
   * @param inputStock is the stock to be removed.
   */
  public void delete(Stock inputStock) {
    if (this.noOfShares >= inputStock.noOfShares) {
      this.noOfShares = this.noOfShares - inputStock.noOfShares;
    } else {
      throw new IllegalArgumentException("Not enough stocks in your portfolio to sell");
    }
    this.sellDetailsList.add(new StockDetails(inputStock.noOfShares,
            inputStock.purchasePrice, inputStock.purchaseDate));

  }

  /**
   * Method to get the purchase details list.
   *
   * @return the purchase list.
   */
  public List<StockDetails> getPurchaseDetailsList() {
    return purchaseDetailsList;
  }

  /**
   * Method to get te sell details list.
   *
   * @return the sell list.
   */
  public List<StockDetails> getSellDetailsList() {
    return sellDetailsList;
  }

  /**
   * Returns name of stock.
   *
   * @return the string name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name of stock.
   *
   * @param name the string name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns description of stock.
   *
   * @return the string containing description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets desription of the stock.
   *
   * @param description is the description.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the exchange rate of stock.
   *
   * @return exchange string.
   */
  public String getExchange() {
    return exchange;
  }

  /**
   * Sets exchange rate of stock.
   *
   * @param exchange is the string exchange.
   */
  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  /**
   * Returns currency of stock being purchases.
   *
   * @return currency string.
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets currency of stock being purchases.
   *
   * @param currency is the currency input.
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Returns country of stock being purchased.
   *
   * @return country string.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets country of stock being purchased.
   *
   * @param country is the country string.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Returns sector of stock being purchased.
   *
   * @return is the sector string.
   */
  public String getSector() {
    return sector;
  }

  /**
   * Sets sector of stock being purchased.
   *
   * @param sector is the string sector.
   */
  public void setSector(String sector) {
    this.sector = sector;
  }

  /**
   * Returns ticker symbol of stock being purchased.
   *
   * @return ticker.
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Sets ticker symbol of stock being purchased.
   *
   * @param ticker is the ticker that is taken as input.
   */
  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  /**
   * Returns purchase price of stock being purchased.
   *
   * @return is the purchase price.
   */
  public double getPurchasePrice() {
    return purchasePrice;
  }

  /**
   * Sets Puchase Price of stock being purchased.
   *
   * @param purchasePrice is the purchase price.
   */
  public void setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  /**
   * Returns Puchase date of stock being purchased.
   *
   * @return is the purchase date.
   */
  public String getPurchaseDate() {
    return purchaseDate;
  }

  /**
   * Sets Puchase date of stock being purchased.
   *
   * @param purchaseDate is the purchase date.
   */
  public void setPurchaseDate(String purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  /**
   * Returns No of shares of stock being purchased.
   *
   * @return number of shares.
   */
  public double getNoOfShares() {
    return noOfShares;
  }

  /**
   * Sets No of shares of stock being purchased.
   *
   * @param noOfStocks is the number of stocks.
   */
  public void setNoOfStocks(double noOfStocks) {
    this.noOfShares = noOfStocks;
  }

  /**
   * Displays all the information that has been inputted.
   *
   * @return string of stock.
   */
  public String toString() {
    return "Stock Symbol:" + ticker +
            "\nPurchase Date:" + purchaseDate + "\nNo of shares:" + noOfShares;
  }
}