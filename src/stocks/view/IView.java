package stocks.view;

import java.awt.event.ActionListener;

import java.util.HashMap;

/**
 * PortfolioView is an interface that has methods set Total value,displayPortfolio,
 * displayAllPortfolio,ShowPortfolioOptions,getPortfolioDetails,getSpecificPortfolioName
 * methods that help us in getting the required view details.
 * Interface for view GUI.
 */
public interface IView {

  /**
   * Method for setting listener.
   *
   * @param listener is the listener object.
   */
  void setListener(ActionListener listener);

  /**
   * Display this view.
   */
  void display();

  /**
   * Method to get user input string info in hashmap.
   *
   * @return the user input string info in hashmap.
   */
  HashMap<String, String> getUserInputStringInfoMap();

  /**
   * Method to display popup dialog.
   *
   * @param popupString contains the string.
   * @param popupTitle  contains the popup title.
   * @param popupType   contains the popup type.
   */
  public void displayPopup(String popupString, String popupTitle, String popupType);

  /**
   * Method to get user input string info in hashmap.
   *
   * @return the user input string information.
   */
  String getUserInputStringInfoMap1();

  /**
   * Method to clear the form.
   */
  public void clearForm();


}
