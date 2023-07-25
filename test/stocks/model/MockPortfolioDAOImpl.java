package stocks.model;

import java.util.HashMap;
import java.util.List;

/**
 * Mock model test class for testing.
 */
public class MockPortfolioDAOImpl implements PortfolioDAO, Portfolio {

  private final String uniqueCode;
  private final List uniqueCode2;
  private final HashMap uniqueCode3;
  private final double uniqueCode4;
  private final int uniqueCode5;
  private StringBuilder s;


  /**
   * A constructor for mock model.
   *
   * @param s           is the string builder.
   * @param uniqueCode  is a unique key.
   * @param uniqueCode2 is a unique key.
   * @param uniqueCode3 is a unique key.
   * @param uniqueCode4 is a unique key.
   * @param uniqueCode5 is a unique key.
   */
  public MockPortfolioDAOImpl(StringBuilder s, String uniqueCode, List uniqueCode2,
                              HashMap uniqueCode3, double uniqueCode4, int uniqueCode5) {
    this.s = s;
    this.uniqueCode = uniqueCode;
    this.uniqueCode2 = uniqueCode2;
    this.uniqueCode3 = uniqueCode3;
    this.uniqueCode4 = uniqueCode4;
    this.uniqueCode5 = uniqueCode5;

  }


  @Override
  public String toString(String date) {
    if (date == null) {
      throw new IllegalArgumentException("Invalid date");
    }
    s.append(String.format("toString method is in view with date %s and unique key %s ",
            date, uniqueCode));
    return uniqueCode;
  }

  @Override
  public List<Stock> read(String portfolioName) {
    if (portfolioName == null) {
      throw new IllegalArgumentException("Invalid portfolio name");
    }
    s.append(String.format("read method is in view with name %s and unique key %s ",
            portfolioName, uniqueCode));
    return uniqueCode2;
  }

  @Override
  public HashMap<String, PortfolioImpl> getAllPortFolios() {
    if (getAllPortFolios() == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    s.append(getAllPortFolios());
    return uniqueCode3;
  }

  @Override
  public void save(PortfolioImpl portfolioImpl, String action) {
    if (portfolioImpl == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    s.append(portfolioImpl);
    s.append(String.format("read method is in view with unique key %s ", uniqueCode));

  }

  @Override
  public void createPortfolio(String portfolioDetails) {
    if (portfolioDetails == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    s.append(String.format("read method is in view with details %s " +
            "and unique key %s ", portfolioDetails, uniqueCode));


  }

  @Override
  public void createFlexiblePortfolio(String portfolioDetails) {
    if (portfolioDetails == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    s.append(String.format("read method is in view with details %s " +
            "and unique key %s ", portfolioDetails, uniqueCode));


  }

  @Override
  public double getTotalValueOfPortfolio(String portfolioName, String date) {
    if (portfolioName == null || date == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    s.append(String.format("get total value method is in view with name %s and unique key %s ",
            portfolioName, uniqueCode));
    return uniqueCode4;
  }

  @Override
  public double calculateTotalValueOfPortfolio(String portfolioName, String date) {
    if (portfolioName == null || date == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    s.append(String.format("calculate total value method is in view with name %s and " +
            "unique key %s ", portfolioName, uniqueCode));
    return 0;
  }

  /**
   * Method to purchase stocks.
   *
   * @param portfolioName is the name of the portfolio.
   * @param commission    is the commission fee.
   * @param ticker        is the ticker symbol.
   * @param noOfShares    is the number of shares.
   * @param date          is the date the user enters.
   */
  @Override
  public void purchaseStocks(String portfolioName, String commission, String ticker,
                             double noOfShares, String date) {
    if (portfolioName == null || ticker == null || noOfShares == 0 || date == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }

    s.append(String.format("purchase stocks method is in view with name %s, ticker %s, " +
            "number of shares %d, and date %s", portfolioName, ticker, noOfShares, date));
    s.append(uniqueCode);
  }

  /**
   * Method to sell stocks.
   *
   * @param portfolioName is the name of the portfolio.
   * @param commission    is the commission fee.
   * @param ticker        is the ticker symbol.
   * @param noOfShares    is the number of shares.
   * @param date          is the date the user enters.
   */
  @Override
  public void sellStocks(String portfolioName, String commission, String ticker,
                         double noOfShares, String date) {
    if (portfolioName == null || ticker == null || noOfShares == 0 || date == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    s.append(String.format("sell stocks method is in view with name %s, ticker %s, " +
            "number of shares %d, and date %s", portfolioName, ticker, noOfShares, date));
    s.append(uniqueCode);


  }


  @Override
  public double totalCostBasis(String portfolioName, String date) {
    if (portfolioName == null || date == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    s.append(String.format("total cost basis method is in view with name %s and unique key %s "
            , portfolioName, uniqueCode));
    return uniqueCode4;
  }

  @Override
  public int getPerformance(String portfolioName, String fromTime, String toTime) {
    if (portfolioName == null || fromTime == null || toTime == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    return uniqueCode5;
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
  public void investFixedAmount(String portfolioName, String commission, double totalCost,
                                String date, String tickerNPercentagesList) {
    if (portfolioName == null || commission == null || totalCost == 0 || date == null ||
            tickerNPercentagesList == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    s.append(String.format("invest stocks method is in view with name %s, commission %s, " +
                    "total cost invested %d, date %s, ticker symbol %s and its percentage %s",
            portfolioName, commission, totalCost, date, tickerNPercentagesList));
    s.append(uniqueCode);

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
                                  int frequencyValue, String tickerNPercentagesList) {

    if (portfolioName == null || commission == null || totalCost == 0 || startDate == null ||
            endDate == null || frequencyType == null || frequencyValue == 0 ||
            tickerNPercentagesList == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    s.append(String.format("Dollar cost average method is in view with name %s, commission %s, " +
                    "total cost invested %d, start date %s, end date %s, frequency type %s," +
                    " frequency value %s, ticker symbol %s and its percentage %s", portfolioName,
            commission, totalCost, startDate, endDate, frequencyType, frequencyValue,
            tickerNPercentagesList));
    s.append(uniqueCode);

  }

}

