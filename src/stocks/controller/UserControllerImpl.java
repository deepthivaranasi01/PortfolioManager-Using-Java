package stocks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.io.InputStream;
import java.util.Scanner;


import stocks.model.Portfolio;
import stocks.model.PortfolioImpl;
import stocks.model.PortfolioDAO;
import stocks.view.PortfolioViewImpl;
import stocks.view.StockGuiViewImpl;


/**
 * This is an implementation of the controller part of the mvc design. the controller
 * basically responsible for controlling the way that a user interacts with an MVC application.
 * It controls the flow of the model and thereby performing the operation and sending it back to
 * the user.
 */
public class UserControllerImpl implements UserController, ActionListener {

  private InputStream in;
  private PortfolioViewImpl portfolioViewImpl;
  private PortfolioDAO portfolioDAO;
  private Scanner scanner;
  private StockGuiViewImpl stockGuiViewImpl;


  /**
   * A constructor is created with no parameters that gets
   * the value of portfolioView and portfolioDAO.
   *
   * @param portfolioViewImpl for portfolio view.
   * @param stockGuiViewImpl  for portfolio GUI view.
   * @param portfolioDAO      for portfolio dao.
   * @param in                for InputStream.
   */
  public UserControllerImpl(PortfolioViewImpl portfolioViewImpl, StockGuiViewImpl stockGuiViewImpl,
                            PortfolioDAO portfolioDAO,
                            InputStream in) {
    this.portfolioViewImpl = portfolioViewImpl;
    this.stockGuiViewImpl = stockGuiViewImpl;
    this.portfolioDAO = portfolioDAO;
    this.in = in;
    scanner = new Scanner(this.in);
    if (stockGuiViewImpl != null) {
      stockGuiViewImpl.setListener(this);
    }
  }

  /**
   * A private funtion to check date format of the portfolio.
   *
   * @param date for portfolio date
   */
  private void checkDate(String date) {
    try {
      Object o = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(date);
    } catch (Exception e) {
      throw new IllegalArgumentException("The date is not in the right format.");
    }
  }

  private void checkIn() {
    try {
      String read = null;
      in.read();
    } catch (Exception e) {
      throw new IllegalArgumentException("Data Invalid");
    }
  }

  /**
   * A public void function which basically controls the whole input options from the user and
   * performing the operation and displaying the result to the user.
   */

  @Override
  public void goStock() {
    if (stockGuiViewImpl != null) {
      // GUI

      stockGuiViewImpl.display();
      return;
    }
    String portfolioDetails = null;
    int option = 1;
    while (option > 0 && option < 13) {
      try {
        portfolioViewImpl.showPortfolioOptionsAndGetInput();
        option = scanner.nextInt();
        if (option == 1) {
          portfolioViewImpl.displayAllPortfolio(
                  portfolioDAO.getAllPortFolios());
        } else if (option == 2) {
          portfolioViewImpl.getSpecificPortfolioName();
          //portfolioViewGuiImpl.getSpecificPortfolioName();
          String portfolioName = scanner.next();
          HashMap<String, PortfolioImpl> allPortFolios =
                  portfolioDAO.getAllPortFolios();
          if (allPortFolios.containsKey(portfolioName)) {
            portfolioViewImpl.displayPortfolio(allPortFolios.get(portfolioName), null);
          } else {
            throw new IllegalArgumentException("Portfolio " + portfolioName + " does not exists");
          }
        } else if (option == 3) {
          portfolioViewImpl.getPortfolioDetails();
          portfolioDetails = scanner.next();
          portfolioDAO.createPortfolio(portfolioDetails);
        } else if (option == 4) {
          portfolioViewImpl.getPortfolioDetails();
          portfolioDetails = scanner.next();
          portfolioDAO.createFlexiblePortfolio(portfolioDetails);
        } else if (option == 5) {
          portfolioViewImpl.getPortfolioNameAndDate();
          String portfolioNameAndDate = scanner.next();
          String[] portfolioInfo = portfolioNameAndDate.split(",");
          checkDate(portfolioInfo[1]);
          double totalValue = portfolioDAO.
                  getTotalValueOfPortfolio(portfolioInfo[0], portfolioInfo[1]);
          HashMap<String, PortfolioImpl> allPortFolios =
                  portfolioDAO.getAllPortFolios();
          portfolioViewImpl.displayPortfolio(allPortFolios
                  .get(portfolioInfo[0]), portfolioInfo[1]);
          portfolioViewImpl.displayPortfolioValue(portfolioInfo[0], totalValue);
        } else if (option == 6) {
          portfolioViewImpl.getPortfolioNameAndDate();
          String portfolioNameAndDate = scanner.next();
          String[] portfolioInfo = portfolioNameAndDate.split(",");
          checkDate(portfolioInfo[1]);
          double totalValue = portfolioDAO.
                  calculateTotalValueOfPortfolio(portfolioInfo[0], portfolioInfo[1]);
          HashMap<String, PortfolioImpl> allPortFolios =
                  portfolioDAO.getAllPortFolios();
          portfolioViewImpl.displayPortfolio(allPortFolios
                  .get(portfolioInfo[0]), portfolioInfo[1]);
          portfolioViewImpl.displayPortfolioValue(portfolioInfo[0], totalValue);
        } else if (option == 7) {
          portfolioViewImpl.getPurchaseOrSellStock("purchase");
          String purchaseStock = scanner.next();
          String[] purchaseIndividualInfo = purchaseStock.split(",");
          portfolioDAO.purchaseStocks(purchaseIndividualInfo[0], purchaseIndividualInfo[1],
                  purchaseIndividualInfo[2],
                  Double.parseDouble(purchaseIndividualInfo[3]), purchaseIndividualInfo[4]);
        } else if (option == 8) {
          portfolioViewImpl.getPurchaseOrSellStock("sell");
          String sellStock = scanner.next();
          String[] sellIndividualInfo = sellStock.split(",");
          portfolioDAO.sellStocks(sellIndividualInfo[0], sellIndividualInfo[1],
                  sellIndividualInfo[2],
                  Double.parseDouble(sellIndividualInfo[3]), sellIndividualInfo[4]);
        } else if (option == 9) {
          portfolioViewImpl.getPortfolioNameAndDate();
          String portfolioNameAndDate = scanner.next();
          String[] portfolioInfo = portfolioNameAndDate.split(",");
          checkDate(portfolioInfo[1]);
          double totalValue = portfolioDAO.totalCostBasis(portfolioInfo[0], portfolioInfo[1]);
          portfolioViewImpl.displayCostBasis(portfolioInfo[0], totalValue);
        } else if (option == 10) {
          portfolioViewImpl.getPortfolioInputForPerformance();
          String portfolioPerfomanceInput = scanner.next();
          String[] portfolioInfo = portfolioPerfomanceInput.split(",");
          portfolioViewImpl.printGraph(portfolioInfo[0], portfolioInfo[1], portfolioInfo[2]);
          portfolioViewImpl.printScale(portfolioDAO.getPerformance(portfolioInfo[0],
                  portfolioInfo[1], portfolioInfo[2]));
        } else if (option == 11) {
          portfolioViewImpl.getInputForInvestFixedAmount();
          String portfolioPerfomanceInput = scanner.next();
          String[] portfolioInfo = portfolioPerfomanceInput.split(",");
          String tickerNPercentageList = "";
          for (int i = 4; i < portfolioInfo.length; i++) {
            tickerNPercentageList = tickerNPercentageList + "," + portfolioInfo[i];
          }
          portfolioDAO.investFixedAmount(portfolioInfo[0], portfolioInfo[1],
                  Double.parseDouble(portfolioInfo[2]), portfolioInfo[3],
                  tickerNPercentageList.substring(1));
        } else if (option == 12) {
          portfolioViewImpl.getInputForDollarCostAveraging();
          String portfolioPerfomanceInput = scanner.next();
          String[] portfolioInfo = portfolioPerfomanceInput.split(",");
          String tickerNPercentageList = "";
          for (int i = 7; i < portfolioInfo.length; i++) {
            tickerNPercentageList = tickerNPercentageList + "," + portfolioInfo[i];
          }
          portfolioDAO.dollarCostAveraging(portfolioInfo[0], portfolioInfo[1],
                  Double.parseDouble(portfolioInfo[2]), portfolioInfo[3], portfolioInfo[4],
                  portfolioInfo[5], Integer.parseInt(portfolioInfo[6]),
                  tickerNPercentageList.substring(1));
        } else if (option == 13) {
          System.exit(0);
        }
      } catch (IllegalArgumentException iae) {
        //iae.printStackTrace();
        throw new IllegalArgumentException("Illegal Argument at :" + iae.getMessage());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  void facilitateSubmit(String action) throws IllegalArgumentException {
    if (action.equals("1")) {
      String portfolioDetails = stockGuiViewImpl.getUserInputStringInfoMap1().toString();
      portfolioDAO.createFlexiblePortfolio(portfolioDetails);
      stockGuiViewImpl.displayPopup("Successfully purchased", "Task done",
              "Information");
    } else if (action.equals("2")) {
      String[] strings = stockGuiViewImpl.getUserInputStringInfoMap1().
              toString().split("\\,");
      portfolioDAO.purchaseStocks(strings[0], strings[1], strings[2],
              Double.parseDouble(strings[3]), strings[4]);
      stockGuiViewImpl.displayPopup("Successfully purchased", "Task done",
              "Information");
    } else if (action.equals("3")) {
      String[] strings = stockGuiViewImpl.getUserInputStringInfoMap1().
              toString().split("\\,");
      portfolioDAO.sellStocks(strings[0], strings[1], strings[2],
              Double.parseDouble(strings[3]), strings[4]);
      stockGuiViewImpl.displayPopup("Successfully sold", "Task done",
              "Information");
    } else if (action.equals("4")) {
      String[] strings = stockGuiViewImpl.getUserInputStringInfoMap1().
              toString().split("\\,");
      double costbasis = portfolioDAO.totalCostBasis(strings[0], strings[1]);
      stockGuiViewImpl.displayPopup("Total Costbasis is" + " " + costbasis,
              "Total Costbasis", String.valueOf(costbasis));
    } else if (action.equals("5")) {
      String string = stockGuiViewImpl.getUserInputStringInfoMap1().toString();
      HashMap<String, PortfolioImpl> allPortFolios = portfolioDAO.getAllPortFolios();
      String portfolioName = string;
      Portfolio portfolio = allPortFolios.get(portfolioName);
      if (allPortFolios.containsKey(portfolioName)) {
        stockGuiViewImpl.displayPopup(portfolio.toString(null), string + " " +
                "Details", "Information");
      } else {
        stockGuiViewImpl.displayPopup("Please enter valid portfolio name",
                "Error", "Information");
      }
    } else if (action.equals("6")) {
      String[] details = stockGuiViewImpl.getUserInputStringInfoMap1().toString().
              split("\\,");
      //String details1 = stockGuiViewImpl.getUserInputStringInfoMap1().toString();
      String s = "";
      for (int i = 4; i < details.length; i++) {
        s = s + "," + details[i];
      }
      portfolioDAO.investFixedAmount(details[0], details[1], Double.parseDouble(details[2]),
              details[3], s.substring(1));

      stockGuiViewImpl.displayPopup("Successfully completed",
              "Success", "Information");
    } else if (action.equals("7")) {
      String[] details = stockGuiViewImpl.getUserInputStringInfoMap1().toString().
              split("\\,");
      // String details1 = stockGuiViewImpl.getUserInputStringInfoMap1().toString();
      String s = "";
      for (int i = 7; i < details.length; i++) {
        s = s + "," + details[i];
      }
      portfolioDAO.dollarCostAveraging(details[0], details[1], Double.parseDouble(details[2]),
              details[3], details[4], details[5], Integer.parseInt(details[6]),
              s.substring(1));
      stockGuiViewImpl.displayPopup("Successfully completed",
              "Success", "Information");
    }
  }


  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if ("MenuGo".equals(actionEvent.getActionCommand())) {
      String action = stockGuiViewImpl.getMenuSelection();
      if (action == null) {
        stockGuiViewImpl.displayError("Choose an option before clicking Go");
      } else {
        if ("1".equals(action)) {
          stockGuiViewImpl.addUpdateUserInput(action, new String[]{"Portfolio Name",
              "Commission", "Stock Symbol", "Number of shares", "Additional Stocks"},
                  new String[]{"Submit",
                    "Cancel", "Clear", "Add Additional Stock with numbers"});
          stockGuiViewImpl.setListener(this);
        }
        else if ("2".equals(action)) {
          stockGuiViewImpl.addUpdateUserInput(action, new String[]{"Portfolio Name",
              "Commission", "Stock", "Number of Shares", "Date"});
          stockGuiViewImpl.setListener(this);
        }
        else if ("3".equals(action)) {
          stockGuiViewImpl.addUpdateUserInput(action, new String[]{"Portfolio Name",
              "Commission", "Stock", "Number of Shares", "Date"});
          stockGuiViewImpl.setListener(this);
        }
        else if ("4".equals(action)) {
          stockGuiViewImpl.addUpdateUserInput(action, new String[]{"Portfolio Name", "Date"});
          stockGuiViewImpl.setListener(this);
        }
        else if ("5".equals(action)) {
          stockGuiViewImpl.addUpdateUserInput(action, new String[]{"Portfolio Name"});
          stockGuiViewImpl.setListener(this);
        }
        else if ("6".equals(action)) {
          stockGuiViewImpl.addUpdateUserInput(action, new String[]{"Portfolio Name",
              "Commission", "Total Amount", "Date", "Stock Symbol",
              "Percentage of shares", "Additional Stocks"},
                  new String[]{"Submit", "Cancel", "Clear",
                    "Add Additional Stock with percentages"});
          stockGuiViewImpl.setListener(this);
        }
        else if ("7".equals(action)) {
          stockGuiViewImpl.addUpdateUserInput(action, new String[]{"Portfolio Name",
              "Commission", "Total Amount", "Start Date", "End Date",
              "Frequency Type (Days,Months)", "Investment Duration", "Stock Symbol",
              "Percentage of shares", "Additional Stocks"}, new String[]{"Submit",
                "Cancel", "Clear",
                "Add Additional Stock with percentages"});
          stockGuiViewImpl.setListener(this);
        }
        else if ("8".equals(action)) {
          System.exit(0);
          stockGuiViewImpl.setListener(this);
        }
      }
    } else if ("UserInputSubmit".equals(actionEvent.getActionCommand())) {
      String action = stockGuiViewImpl.getUserInputStringInfoMap().get("action");
      String status = stockGuiViewImpl.addFormInfoTouserInputStringInfoMap();
      if ("SUCCESS".equals(status)) {
        try {
          facilitateSubmit(action);
          stockGuiViewImpl.backToMainMenu();
        } catch (IllegalArgumentException e) {
          //e.printStackTrace();
          stockGuiViewImpl.displayError(e.getMessage());
        }
        catch (DateTimeParseException ex) {
          stockGuiViewImpl.displayError(ex.getMessage());
        }
      }
    } else if ("UserInputClear".equals(actionEvent.getActionCommand())) {
      stockGuiViewImpl.clearForm();
    } else if ("UserInputCancel".equals(actionEvent.getActionCommand())) {
      stockGuiViewImpl.backToMainMenu();
    } else if ("UserInputAdd Additional Stock with numbers".
            equals(actionEvent.getActionCommand())) {
      stockGuiViewImpl.showAddStockUserInput(new String[]{"Stock Symbol", "Number of shares"});
      stockGuiViewImpl.setListener(this);
    } else if ("UserInputAdd Additional Stock with percentages".
            equals(actionEvent.getActionCommand())) {
      stockGuiViewImpl.showAddStockUserInput(new String[]{"Stock Symbol", "Percentage of shares"});
      stockGuiViewImpl.setListener(this);
    } else if ("AddlUserInputAdd".equals(actionEvent.getActionCommand())) {
      stockGuiViewImpl.addStockUserInput();
    } else if ("AddlUserInputCancel".equals(actionEvent.getActionCommand())) {
      stockGuiViewImpl.cancelAddStockUserInput();
    }
  }


}
