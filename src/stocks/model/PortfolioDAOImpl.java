package stocks.model;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import stocks.util.StockValuesUtilityImpl;

/**
 * An implementation of the interface portfolioDAO (Data Access Object) which reads stocks
 * , gets all portfolios,
 * saves portfolios, creates a new portfolio and gets the total value of the portfolio.
 */
public class PortfolioDAOImpl implements PortfolioDAO {

  private double commissionFee = 0;

  /**
   * A method to check if portfolio name exists or not, it throws exception if the portfolio name
   * doesn't exist.
   *
   * @param portfolioName is the parameter is the portfolio filename that is meant to be read.
   * @return the portfolio stocks.
   * @throws IllegalArgumentException if portfolio does not exist.
   */
  public List<Stock> read(String portfolioName) throws IllegalArgumentException {
    HashMap<String, PortfolioImpl> portfolioList = getAllPortFolios();
    if (portfolioList.containsKey(portfolioName)) {
      return portfolioList.get(portfolioName).getStocks();
    } else {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " does not exists");
    }

  }

  /**
   * Method for creating a portfolio.
   *
   * @param portfolioDetails is taken as input to perform the creation of portfolio.
   */
  @Override
  public void createPortfolio(String portfolioDetails) {

    List<Stock> stocks = new ArrayList<>();
    StockValuesUtilityImpl stockValuesUtilityImpl = new StockValuesUtilityImpl();
    String portfolioFileName = null;
    boolean flexiblePortfolio = false;
    portfolioCreationHelper(portfolioDetails, stocks, stockValuesUtilityImpl, flexiblePortfolio);
  }

  private void portfolioCreationHelper(String portfolioDetails, List<Stock> stocks,
                                       StockValuesUtilityImpl stockValuesUtilityImpl,
                                       boolean flexiblePortfolio, boolean allowEmptyPortfolio) {
    String portfolioFileName;
    double totalInvestedMoney = 0;
    try {
      String[] portfolioDetailsArr = portfolioDetails.split(",");
      portfolioFileName = portfolioDetailsArr[0];
      commissionFee = Double.parseDouble(portfolioDetailsArr[1]);
      if (commissionFee < 0) {
        throw new IllegalArgumentException("Commission fee can't be negative");
      }
      if (portfolioDetailsArr.length < 3 && !allowEmptyPortfolio) {
        throw new IllegalArgumentException("Cannot create empty portfolio, " +
                "enter the data correctly");
      }
      String line = "";
      for (int i = 2; i < portfolioDetailsArr.length; i++) {
        line = portfolioDetailsArr[i];
        String[] stockDetails = line.split("\\|");
        if (stockDetails.length < 2) {
          throw new IllegalArgumentException("Please enter the stock name" +
                  " and number of shares correctly");
        }
        int noOfShares = 0;
        try {
          noOfShares = Integer.parseInt(stockDetails[1]);
          if (noOfShares <= 0) {
            throw new IllegalArgumentException("Shares cannot be negative or 0");
          }
        } catch (NumberFormatException ex) {
          throw new IllegalArgumentException("Fractional amount of shares can not be purchased "
                  + stockDetails[1]);
        }
        String stockSymbol = stockDetails[0];
        double stockPurchasePrice = stockValuesUtilityImpl.getStockPriceRealTime(
                stockSymbol, LocalDate.now().
                        minusDays(1).
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Stock stock = new Stock(stockPurchasePrice, stockSymbol,
                LocalDate.now().
                        minusDays(1).format(DateTimeFormatter.
                                ofPattern("yyyy-MM-dd")), noOfShares);
        Stock stockWithAdditionalData =
                stockValuesUtilityImpl.getStockDetails(stock);
        stocks.add(stockWithAdditionalData);
        totalInvestedMoney = totalInvestedMoney + stockPurchasePrice * noOfShares;
      }
    } catch (Exception ex) {
      //ex.printStackTrace();
      throw new IllegalArgumentException("Data in input is not valid. " + ex.getMessage());
    }
    PortfolioImpl portfolioImpl = new PortfolioImpl();
    if (stocks != null && !stocks.isEmpty()) {
      InvestmentDetails investmentDetails = new InvestmentDetails();
      investmentDetails.setInvestmentDate(LocalDate.now().
              minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      investmentDetails.setTotalMoneyInvested(totalInvestedMoney + commissionFee);
      portfolioImpl.getInvestmentDetailsList().add(investmentDetails);
    }
    portfolioImpl.setFlexiblePortfolio(flexiblePortfolio);
    portfolioImpl.setName(portfolioFileName);
    portfolioImpl.setStocks(stocks);
    PortfolioDAO portfolioDAO = new PortfolioDAOImpl();
    portfolioDAO.save(portfolioImpl, "CREATE");
  }

  private void portfolioCreationHelper(String portfolioDetails,
                                       List<Stock> stocks, StockValuesUtilityImpl
                                               stockValuesUtilityImpl, boolean flexiblePortfolio) {

    portfolioCreationHelper(portfolioDetails, stocks, stockValuesUtilityImpl,
            flexiblePortfolio, false);
  }


  /**
   * Method for creating a portfolio which is flexible.
   *
   * @param portfolioDetails is taken as input to perform the creation of portfolio.
   */
  @Override
  public void createFlexiblePortfolio(String portfolioDetails) {

    List<Stock> stocks = new ArrayList<>();
    StockValuesUtilityImpl stockValuesUtilityImpl = new StockValuesUtilityImpl();
    String portfolioFileName = null;
    boolean flexiblePortfolio = true;
    portfolioCreationHelper(portfolioDetails, stocks, stockValuesUtilityImpl, flexiblePortfolio);
  }

  /**
   * Method to get  all portfolios.
   *
   * @return get all portfolios.
   */
  public HashMap<String, PortfolioImpl> getAllPortFolios() {
    Gson gson = new Gson();
    HashMap<String, PortfolioImpl> portfolioList = null;
    try {
      portfolioList = gson.fromJson(new FileReader(new File("res//AllPortFolio.json"))
              , new TypeToken<HashMap<String, PortfolioImpl>>() {
              }.getType());
      if (portfolioList == null) {
        portfolioList = new HashMap<String, PortfolioImpl>();
      }
    } catch (FileNotFoundException e) {
      portfolioList = new HashMap<String, PortfolioImpl>();
    }
    return portfolioList;
  }


  /**
   * Method to save portfolios.
   *
   * @param portfolioImpl is the input parameter taken to save.
   * @param action        so that created portfolio cannot be created again.
   */
  public void save(PortfolioImpl portfolioImpl, String action) {
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      HashMap<String, PortfolioImpl> portfolioList = getAllPortFolios();

      if (portfolioList.containsKey(portfolioImpl.getName()) && "CREATE".equals(action)) {
        throw new IllegalArgumentException("Portfolio already exists.");
      }
      if (portfolioList.containsKey(portfolioImpl.getName()) && "UPDATE".
              equals(action) && !portfolioImpl.isFlexiblePortfolio()) {
        throw new IllegalArgumentException("You cannot modify this" +
                " portfolio as it is a inflexible");
      }
      portfolioList.put(portfolioImpl.getName(), portfolioImpl);
      FileWriter writer = new FileWriter(new File("res//AllPortFolio.json"));
      gson.toJson(portfolioList, writer);
      writer.flush();
      writer.close();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Method to get the total value of a particular portfolio.
   *
   * @param portfolioName is the name of the portfolio.
   * @param date          is the date you want to get the value from.
   * @return the total value of the portfolio.
   */
  public double getTotalValueOfPortfolio(String portfolioName, String date) {
    StockValuesUtilityImpl stockValuesUtilityImpl = new StockValuesUtilityImpl();
    List<Stock> stocks = read(portfolioName);
    double total = 0;
    for (Stock stock : stocks) {
      double individualStockPrice = stockValuesUtilityImpl.
              getStockPriceRealTime(stock.getTicker(), date)
              * stock.getNoOfShares();
      total = total + individualStockPrice;
    }
    return total;
  }

  /**
   * Method to calculate the total value of a particular portfolio.
   *
   * @param portfolioName is the name of the portfolio.
   * @param date          is the date you want to get the value from.
   * @return the total value of the portfolio.
   */
  public double calculateTotalValueOfPortfolio(String portfolioName, String date) {
    StockValuesUtilityImpl stockValuesUtilityImpl = new StockValuesUtilityImpl();
    List<Stock> stocks = read(portfolioName);
    double total = 0;
    HashMap<String, String> stockSummary = new HashMap<String, String>();
    for (Stock stock : stocks) {
      String ticker = stock.getTicker();
      List<StockDetails> purchaseDetailsList = stock.getPurchaseDetailsList();
      List<StockDetails> purchasedSharesBeforeDate = new ArrayList<>();
      for (StockDetails stockDetails : purchaseDetailsList) {
        if (!(LocalDate.parse(stockDetails.getStockDate()).isAfter(LocalDate.parse(date)))) {
          purchasedSharesBeforeDate.add(stockDetails);
        }
      }
      int totalNoOfSharesPurchasedBeforeDate = purchasedSharesBeforeDate
              .stream().mapToInt(x -> (int) x.getNoOfShares()).sum();
      List<StockDetails> sellDetailsList = stock.getSellDetailsList();
      List<StockDetails> soldSharesBeforeDate = new ArrayList<>();
      for (StockDetails stockDetails : sellDetailsList) {
        if (!(LocalDate.parse(stockDetails.getStockDate()).isAfter(LocalDate.parse(date)))) {
          soldSharesBeforeDate.add(stockDetails);
        }
      }
      int totalNoOfSharesSoldBeforeDate = soldSharesBeforeDate
              .stream().mapToInt(x -> (int) x.getNoOfShares()).sum();
      int totalNoOfShares = totalNoOfSharesPurchasedBeforeDate - totalNoOfSharesSoldBeforeDate;
      if (totalNoOfShares != 0) {
        double individualStockPrice = stockValuesUtilityImpl.getStockPriceRealTime(
                stock.getTicker(),
                LocalDate.parse(date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                * totalNoOfShares;
        total = total + individualStockPrice;
      }
    }
    return total;
  }

  /**
   * Method to purchase or sell stocks.
   *
   * @param portfolioName         name of portfolio.
   * @param allowFractionalShares for allowing fractional shares.
   * @param commission            commission of portfolio.
   * @param ticker                ticker symbol.
   * @param noOfShares            number of shares of the portfolio.
   * @param date                  is the date entered.
   * @param purchaseOrSell        whether to perform the purchase stocks part or sell stocks part.
   */
  public void purchaseOrSellStocks(String portfolioName, String commission, String ticker,
                                   double noOfShares, String date, String purchaseOrSell,
                                   boolean allowFractionalShares) throws DateTimeParseException {
    LocalDate dateConvert;
    commissionFee = Double.parseDouble(commission);
    if (commissionFee < 0) {
      throw new IllegalArgumentException("Commission fee can't be negative");
    }
    double totalInvestedPrice = 0;
    StockValuesUtilityImpl stockValuesUtility = new StockValuesUtilityImpl();
    HashMap<String, PortfolioImpl> list = getAllPortFolios();
    PortfolioImpl portfolioImpl = new PortfolioImpl();
    try {
      dateConvert = LocalDate.parse(date);
    } catch (DateTimeParseException e) {
      throw new DateTimeParseException("Enter valid date", date, 4);
    }
    if (dateConvert.compareTo(LocalDate.now()) >= 0) {
      throw new IllegalArgumentException("Date cannot be today or future's");
    }
    if (list.containsKey(portfolioName)) {
      portfolioImpl = list.get(portfolioName);
      if (!portfolioImpl.isFlexiblePortfolio()) {
        throw new IllegalArgumentException("You cannot buy or sell shares" +
                " in inflexible portfolio");
      }
    } else {
      throw new IllegalArgumentException("Entered portfolio doesn't exist");
    }
    try {
      if (noOfShares <= 0) {
        throw new IllegalArgumentException("Shares cannot be negative or 0");
      }
      if (noOfShares % 1 != 0 && !allowFractionalShares) {
        throw new IllegalArgumentException("Fractional amount of shares can " +
                "not be purchased or sold");
      }
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("Invalid data entered.");
    }
    List<Stock> portfolioStocksList = portfolioImpl.getStocks();
    double stockPriceRealTime = stockValuesUtility.getStockPriceRealTime(ticker,
            LocalDate.parse(date).
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    int totalStockShares = 0;
    if ("SELL".equals(purchaseOrSell)) {
      if (dateConvert.compareTo(LocalDate.now()) >= 0) {
        throw new IllegalArgumentException("Date cannot be today or future's");
      }
      for (int i = 0; i < portfolioStocksList.size(); i++) {
        Stock stock = portfolioStocksList.get(i);
        if (stock.getTicker().equals(ticker) && stock.getPurchaseDate().compareTo(date) <= 0) {
          totalStockShares += stock.getNoOfShares();
        }
      }
    }
    boolean addedStock = false;
    for (int i = 0; i < portfolioStocksList.size(); i++) {
      Stock stock = portfolioStocksList.get(i);
      if (stock.getTicker().equals(ticker) && stock.getPurchaseDate().equals(date)) {
        addedStock = true;
        StockDetails stockDetails = new StockDetails(noOfShares, stockPriceRealTime, date);
        if ("PURCHASE".equals(purchaseOrSell)) {
          stock.setNoOfStocks(stock.getNoOfShares() + noOfShares);
          stock.getPurchaseDetailsList().add(stockDetails);
        } else {
          if (totalStockShares < noOfShares) {
            throw new IllegalArgumentException("You do not have sufficient stocks to sell");
          }
          stock.setNoOfStocks(stock.getNoOfShares() - noOfShares);
          stock.getSellDetailsList().add(stockDetails);
        }
      }
    }
    if ("SELL".equals(purchaseOrSell) && totalStockShares < noOfShares) {
      throw new IllegalArgumentException("You do not have sufficient stocks to sell");
    }
    if (!addedStock) {
      Stock newPurchase = new Stock(stockPriceRealTime, ticker, date, noOfShares, purchaseOrSell);
      portfolioStocksList.add(newPurchase);
      portfolioImpl.setStocks(portfolioStocksList);
    }
    if ("PURCHASE".equals(purchaseOrSell)) {
      totalInvestedPrice = stockPriceRealTime * noOfShares;
    }
    InvestmentDetails newInvestmentDetails = new InvestmentDetails();
    newInvestmentDetails.setTotalMoneyInvested(totalInvestedPrice + commissionFee);
    newInvestmentDetails.setInvestmentDate(date);
    portfolioImpl.getInvestmentDetailsList().add(newInvestmentDetails);
    save(portfolioImpl, "UPDATE");

  }

  /**
   * Method to purchase stocks.
   *
   * @param portfolioName is the name of the portfolio.
   * @param ticker        is the ticker symbol.
   * @param noOfShares    is the number of shares.
   * @param date          is the date the user enters.
   */
  public void purchaseStocks(String portfolioName, String commission, String ticker,
                             double noOfShares, String date) {
    purchaseOrSellStocks(portfolioName, commission, ticker, noOfShares, date,
            "PURCHASE", false);
  }

  /**
   * Method to sell stocks.
   *
   * @param portfolioName is the name of the portfolio.
   * @param ticker        is the ticker symbol.
   * @param noOfShares    is the number of shares.
   * @param date          is the date the user enters.
   */
  public void sellStocks(String portfolioName, String commission, String ticker,
                         double noOfShares, String date) {
    purchaseOrSellStocks(portfolioName, commission, ticker, noOfShares, date,
            "SELL", false);
  }


  /**
   * Method to determine cost basis.
   *
   * @param portfolioName is the name of the portfolio.
   * @param date          is the ticker symbol.
   * @return returns the total cost basis.
   */
  public double totalCostBasis(String portfolioName, String date) {
    HashMap<String, PortfolioImpl> allPortFolios = getAllPortFolios();
    double totalInvested = 0;
    LocalDate dateConvert;
    try {
      dateConvert = LocalDate.parse(date);
    }
    catch (DateTimeParseException e) {
      throw new DateTimeParseException("Enter valid date",date,1);
    }
    if (allPortFolios.containsKey(portfolioName)) {
      PortfolioImpl portfolio = getAllPortFolios().get(portfolioName);
      List<InvestmentDetails> investmentDetailsList = portfolio.getInvestmentDetailsList();
      for (InvestmentDetails investmentDetails : investmentDetailsList) {
        String investmentDate = investmentDetails.getInvestmentDate();
        if (!LocalDate.parse(investmentDate).isAfter(LocalDate.parse(date))) {
          totalInvested = totalInvested + investmentDetails.getTotalMoneyInvested();
        }
      }
    } else {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " does not exists");
    }
    return totalInvested;
  }

  /**
   * Method to determine the performance basis of portfolio.
   *
   * @param portfolioName is the portfolio name.
   * @param fromTime      is the from date for graph.
   * @param toTime        is the to date for the graph.
   * @param timeSpan      is the time span of the graph.
   * @return returns the performance of the portfolio in graphical format.
   */
  public Map<String, String> performanceOfPortfolio(String portfolioName, String fromTime,
                                                    String toTime, String timeSpan) {

    Map<String, String> linesMap = new LinkedHashMap<>();

    if ("EVERY_DAY".equals(timeSpan)) {

      LocalDate toDate = LocalDate.parse(toTime);
      LocalDate fromDate = LocalDate.parse(fromTime);

      for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
        try {
          double totalValueOfPortfolio = calculateTotalValueOfPortfolio(
                  portfolioName, date.toString());
          String value = String.valueOf((double) Math.round(totalValueOfPortfolio * 100) / 100);
          linesMap.put(date.toString(), value);
        } catch (Exception e) {
          throw new RuntimeException("Data not found" + e.getMessage());
        }
      }
    } else if ("EVERY_5_DAYS".equals(timeSpan)) {

      LocalDate toDate = LocalDate.parse(toTime);
      LocalDate fromDate = LocalDate.parse(fromTime);

      for (LocalDate date = fromDate; !date.isAfter(toDate.plusDays(4));
           date = date.plusDays(5)) {
        try {
          LocalDate tmpDate = (date.isBefore(toDate)) ? date : toDate;
          double totalValueOfPortfolio = calculateTotalValueOfPortfolio(
                  portfolioName, tmpDate.toString());
          String value = String.valueOf((double) Math.round(totalValueOfPortfolio * 100) / 100);
          linesMap.put(tmpDate.toString(), value);
        } catch (Exception e) {
          throw new RuntimeException("Data not found" + e.getMessage());
        }
      }
    } else if ("EVERY_MONTH".equals(timeSpan)) {

      LocalDate fromDate = LocalDate.parse(fromTime);
      LocalDate toDate = LocalDate.parse(toTime);

      for (LocalDate date = fromDate; !date.isAfter(toDate);
           date = date.plusMonths(1)) {
        LocalDate lastDayOfFromMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        if (lastDayOfFromMonth.isAfter(toDate)) {
          lastDayOfFromMonth = toDate;
        }
        double totalValueOfPortfolio = calculateTotalValueOfPortfolio(portfolioName,
                lastDayOfFromMonth.toString());
        String value = String.valueOf((double) Math.round(totalValueOfPortfolio * 100) / 100);
        linesMap.put(date.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " " +
                date.getYear(), value);
      }

    } else if ("EVERY_3_MONTHS".equals(timeSpan)) {

      LocalDate fromDate = LocalDate.parse(fromTime);
      LocalDate toDate = LocalDate.parse(toTime);

      for (LocalDate date = fromDate; !date.isAfter(toDate);
           date = date.plusMonths(3)) {
        LocalDate lastDayOfFromMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        if (lastDayOfFromMonth.isAfter(toDate)) {
          lastDayOfFromMonth = toDate;
        }
        double totalValueOfPortfolio = calculateTotalValueOfPortfolio(portfolioName,
                lastDayOfFromMonth.toString());
        String value = String.valueOf((double) Math.round(totalValueOfPortfolio * 100) / 100);
        linesMap.put(date.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " " +
                date.getYear(), value);
      }

    } else if ("EVERY_YEAR".equals(timeSpan)) {

      LocalDate fromDate = LocalDate.parse(fromTime);
      LocalDate toDate = LocalDate.parse(toTime);

      for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusYears(1)) {
        LocalDate lastDayOfFromYear = date.with(TemporalAdjusters.lastDayOfYear());

        if (lastDayOfFromYear.isAfter(toDate)) {
          lastDayOfFromYear = toDate;
        }
        double totalValueOfPortfolio = calculateTotalValueOfPortfolio(portfolioName,
                lastDayOfFromYear.toString());
        String value = String.valueOf((double) Math.round(totalValueOfPortfolio * 100) / 100);
        linesMap.put(date.getYear() + "", value);
      }
    }

    return linesMap;
  }


  /**
   * Method for printing graph.
   *
   * @param n is the number of * to print graph.
   * @return returns the performance of the portfolio in graphical format.
   */
  private String printGraph(int n) {
    String graph = "";
    for (int i = 0; i < n; i++) {
      graph = graph + "*";
    }
    return graph;

  }

  /**
   * Method to determine performance analysis.
   *
   * @param portfolioName is the portfolio name.
   * @param fromTime      is the front date.
   * @param toTime        is the rear date.
   */
  @Override
  public int getPerformance(String portfolioName, String fromTime, String toTime) {
    LocalDate toDate = LocalDate.parse(toTime);
    LocalDate fromDate = LocalDate.parse(fromTime);
    long days = fromDate.until(toDate, ChronoUnit.DAYS);
    String timeSpan = "EVERY_DAY";
    if (fromDate.plusYears(25).isBefore(toDate)) {
      throw new IllegalArgumentException("Cannot show performance for more than 25 years");
    } else if (days > 365 * 5) {
      timeSpan = "EVERY_YEAR";
    } else if (days > 365 * 2) {
      timeSpan = "EVERY_3_MONTHS";
    } else if (days > 120) {
      timeSpan = "EVERY_MONTH";
    } else if (days > 25) {
      timeSpan = "EVERY_5_DAYS";
    }
    Map<String, String> linesMap = performanceOfPortfolio(portfolioName,
            fromTime, toTime, timeSpan);
    double largestVal = 0.0;
    int scale = 0;
    for (String linesKey : linesMap.keySet()) {
      double value = Double.parseDouble(linesMap.get(linesKey));
      if (value >= largestVal) {
        largestVal = value;
      }
    }
    double scaleValue = largestVal / 50;
    scale = (int) (Math.ceil((scaleValue + 100) / 100.0)) * 100;
    for (String linesKey : linesMap.keySet()) {
      int value = (int) Double.parseDouble(linesMap.get(linesKey)) / scale;
      System.out.println(linesKey + " : " + printGraph(value));
    }
    return scale;
  }


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
  @Override
  public void investFixedAmount(String portfolioName, String commission,
                                double totalCost, String date, String tickerNPercentagesList) {

    StockValuesUtilityImpl stockValuesUtility = new StockValuesUtilityImpl();
    HashMap<String, PortfolioImpl> list = getAllPortFolios();
    PortfolioImpl portfolioImpl = new PortfolioImpl();
    LocalDate dateConvert;
    try {
      double value = totalCost;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Enter correct value for cost");
    }
    try {
      dateConvert = LocalDate.parse(date);
    } catch (DateTimeParseException e) {
      throw new DateTimeParseException("Enter valid date", date, 3);
    }
    if (totalCost <= 0) {
      throw new IllegalArgumentException("Value can't be negative");
    }
    if (dateConvert.compareTo(LocalDate.now()) >= 0) {
      throw new IllegalArgumentException("Date cannot be today or future's");
    }
    if (list.containsKey(portfolioName)) {
      portfolioImpl = list.get(portfolioName);
      if (!portfolioImpl.isFlexiblePortfolio()) {
        throw new IllegalArgumentException("You cannot buy or sell shares" +
                " in inflexible portfolio");
      }
    } else {
      throw new IllegalArgumentException("Entered portfolio doesn't exist");
    }
    String[] tickerPercentageArr = tickerNPercentagesList.split(",");

    double totalPercentage = 0;
    for (int i = 0; i < tickerPercentageArr.length; i++) {
      String[] tickerInfo = (tickerPercentageArr[i]).split("\\|");
      double percentage = Double.parseDouble(tickerInfo[1]);
      totalPercentage = totalPercentage + percentage;

    }
    if (totalPercentage != 100) {
      throw new IllegalArgumentException("Total Percentage should be 100");
    }
    for (int i = 0; i < tickerPercentageArr.length; i++) {
      String[] tickerInfo = tickerPercentageArr[i].split("\\|");
      String ticker = tickerInfo[0];
      double percentage = Double.parseDouble(tickerInfo[1]);
      double stockPriceRealTime = stockValuesUtility.getStockPriceRealTime(ticker,
              LocalDate.parse(date).
                      format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      double noOfStocks = percentage / 100 * totalCost / stockPriceRealTime;
      purchaseOrSellStocks(portfolioName, commission, ticker, noOfStocks, date,
              "PURCHASE", true);

    }
  }

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
  @Override
  public void dollarCostAveraging(String portfolioName, String commission, double totalCost,
                                  String startDate, String endDate, String frequencyType,
                                  int frequencyValue, String tickerNPercentagesList) throws
          DateTimeParseException {
    if (!getAllPortFolios().containsKey(portfolioName)) {
      portfolioCreationHelper(portfolioName + "," + commission, null,
              null, true, true);
    }
    try {
      double value = totalCost;
    } catch (NumberFormatException e2) {
      throw new NumberFormatException("Enter a valid value for cost");
    }
    LocalDate localDate;
    LocalDate localEndDate;
    try {
      localDate = LocalDate.parse(startDate);
      localEndDate = LocalDate.parse(endDate);
    } catch (DateTimeParseException e) {
      throw new DateTimeParseException("Enter valid date", startDate, 3);
    }
    Duration range = Duration.between(localDate.atStartOfDay(), localEndDate.atStartOfDay());
    long difference = range.toDays();
    if (difference <= frequencyValue) {
      throw new IllegalArgumentException("Invalid range");
    }
    if (frequencyValue <= 0) {
      throw new IllegalArgumentException("Frequency type is invalid.");
    }
    if (frequencyValue % 1 != 0) {
      throw new IllegalArgumentException("Frequency value is invalid.");
    }
    if (!localDate.isBefore(localEndDate)) {
      throw new IllegalArgumentException("End date is before start date");
    }
    while (localDate.isBefore(localEndDate)) {
      investFixedAmount(portfolioName, commission, totalCost,
              localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), tickerNPercentagesList);
      if (!frequencyType.toLowerCase().equals("days") ||
              frequencyType.toLowerCase().equals("months")
              || frequencyType.toLowerCase().equals("years")) {
        throw new IllegalArgumentException("Please enter valid frequency type");
      }
      if ("DAYS".equals(frequencyType.toUpperCase())) {
        localDate = localDate.plusDays(frequencyValue);
      } else if ("MONTHS".equals(frequencyType.toUpperCase())) {
        localDate = localDate.plusMonths(frequencyValue);
      } else if ("YEARS".equals(frequencyType.toUpperCase())) {
        localDate = localDate.plusYears(frequencyValue);
      } else {
        throw new IllegalArgumentException("Frequency invalid");
      }

    }

  }


}
