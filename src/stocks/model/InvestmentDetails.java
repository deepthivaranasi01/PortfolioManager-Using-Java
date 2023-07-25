package stocks.model;

/**
 * An implementation of the interface portfolioDAO (Data Access Object) which reads stocks
 * , gets all portfolios,
 * saves portfolios, creates a new portfolio and gets the total value of the portfolio.
 */
public class InvestmentDetails {

  private double totalMoneyInvested;
  private String investmentDate;

  /**
   * A method to estimate the total money Invested.
   *
   * @return total money invested is returned.
   */
  public double getTotalMoneyInvested() {
    return totalMoneyInvested;
  }

  /**
   * A Method to set money that has been invested.
   *
   * @param totalMoneyInvested is money that has been invested.
   */
  public void setTotalMoneyInvested(double totalMoneyInvested) {
    this.totalMoneyInvested = totalMoneyInvested;
  }

  /**
   * Method to get the invesment date.
   *
   * @return the invesment date.
   */
  public String getInvestmentDate() {
    return investmentDate;
  }

  /**
   * Method to set the invesment date.
   *
   * @param investmentDate is the invesment date.
   */
  public void setInvestmentDate(String investmentDate) {
    this.investmentDate = investmentDate;
  }
}
