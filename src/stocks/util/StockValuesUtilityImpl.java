package stocks.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import stocks.model.Stock;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class for StockValuesUtility which basically fetches the real
 * time data of the stocks from API. Here we have manually downloaded files for 4 different
 * stocks and are using them as the only available stock.
 */
public class StockValuesUtilityImpl implements StockValuesUtility {

  /**
   * A method to get the price of a particular stock.
   *
   * @param stockName is name of stock.
   * @param date      is the date.
   * @return the stock price.
   */
  public double getStockPrice(String stockName, String date) {
    Gson gson = new Gson();
    double result = 0;
    if (Files.notExists(Paths.get("res", stockName + "_TimeSeries.json"))) {
      throw new IllegalArgumentException(stockName + "data is not available. " +
              "Please download the StockDetails and TimeSeries from AlphaVantage.");
    }
    Map stockNameTimeSeries = null;
    try {
      stockNameTimeSeries = gson.fromJson(new FileReader(new File("res//"
              + stockName + "_TimeSeries.json")), Map.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    String dateValue = null;
    try {
      dateValue = (String) ((Map) ((Map) stockNameTimeSeries.get("Time Series (Daily)"))
              .get(date)).get("4. close");
      result = Double.parseDouble(dateValue);
    } catch (Exception e) {
      throw new IllegalArgumentException("No price data found for "
              + stockName + " on " + date);
    }
    return result;
  }

  /**
   * Method to get the real time price of the stock in real time.
   *
   * @param stockName is the name of the stock.
   * @param date      is the date entered.
   * @return the stock price in real time.
   */
  public double getStockPriceRealTime(String stockName, String date) {
    LocalDate enteredDate = LocalDate.parse(date);
    if (enteredDate.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("Entered Date is invalid");
    }
    String stockPrices = getStockPricesRealTime(stockName);
    String[] stockPriceLines = stockPrices.split(System.getProperty("line.separator"));
    String[] stockPriceLinesWithoutHeader = Arrays.copyOfRange(stockPriceLines, 1,
            stockPriceLines.length);
    LinkedHashMap<LocalDate, String> stocksMap = new LinkedHashMap<>();
    for (String stockPriceLine : stockPriceLinesWithoutHeader) {
      String[] stockDetailsByDay = stockPriceLine.split(",");
      stocksMap.put(LocalDate.parse(stockDetailsByDay[0]), stockDetailsByDay[4]);
    }
    LocalDate nextBusinessDay = LocalDate.parse(date);
    while (!stocksMap.containsKey(nextBusinessDay)) {
      if (nextBusinessDay.isBefore(LocalDate.now())) {
        nextBusinessDay = nextBusinessDay.plusDays(1);
      } else {
        return 0;
      }
    }
    return Double.parseDouble(stocksMap.get(nextBusinessDay));
  }


  /**
   * Method to get the real time price of the stock using stock symbol.
   *
   * @param stockSymbol is the stock symbol.
   * @return realtime value.
   */
  public String getStockPricesRealTime(String stockSymbol) {
    String apiKey = "QEHLSEQZWQ0SZGJA";
    URL url = null;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();
    try {

      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol +
              "message is +" + e.getMessage());
    }
    return output.toString();
  }


  /**
   * A method to get the stock details.
   *
   * @param stock is the symbol of stock.
   * @return the stockdetails.
   */

  public Stock getStockDetails(Stock stock) throws IllegalArgumentException {
    String apiKey = "QEHLSEQZWQ0SZGJA";
    URL url = null;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=OVERVIEW"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stock.getTicker() + "&apikey=" + apiKey + "&datatype=JSON");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    InputStream in = null;
    StringBuilder output = new StringBuilder();
    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stock.getTicker());
    }
    Gson gson = new Gson();
    Map<String, String> response = gson.fromJson(output.toString(), Map.class);
    stock.setName(response.get("Name"));
    stock.setCountry(response.get("Country"));
    stock.setDescription(response.get("Description"));
    stock.setCurrency(response.get("Currency"));
    stock.setExchange(response.get("Exchange"));
    stock.setSector(response.get("Sector"));
    return stock;
  }

}