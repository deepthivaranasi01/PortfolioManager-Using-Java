package stocks.model;

/**
 * The interface portfolio has a method toString that takes in date as input to give
 * the string value of total value.
 */

public interface Portfolio {

  /**
   * This method takes in input as String date and returns the result of individual stock price.
   *
   * @param date is the date that is taken as input to generate individual stock value result.
   * @return result is the total value of individual stock price.
   */
  public String toString(String date);


}
