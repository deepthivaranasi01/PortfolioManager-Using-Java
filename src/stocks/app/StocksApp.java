package stocks.app;

import stocks.controller.UserControllerImpl;
import stocks.model.PortfolioDAO;
import stocks.model.PortfolioDAOImpl;
import stocks.view.PortfolioViewImpl;
import stocks.view.StockGuiViewImpl;

/**
 * This is the main driver file StocksApp which only has a main function.
 * This is the initial trigger to the application. When we trigger the goStock method, it will
 * trigger the menu option, and as a result, we can choose what option can be executed.
 */
public class StocksApp {

  /**
   * Main function which calls the model, view and controller.
   *
   * @param args is the input parameters the main method takes.
   */
  public static void main(String[] args) {
    PortfolioDAO portfolioDAO = new PortfolioDAOImpl();
    PortfolioViewImpl portfolioViewImpl = null;
    StockGuiViewImpl stockGuiViewImpl = null;
    if (args == null || args.length == 0 || !"text".equalsIgnoreCase(args[0])) {
      stockGuiViewImpl = new StockGuiViewImpl("StockAPP");
    } else {
      portfolioViewImpl = new PortfolioViewImpl(System.out);
    }
    UserControllerImpl userControllerImpl = new UserControllerImpl(portfolioViewImpl,
            stockGuiViewImpl, portfolioDAO
            , System.in);
    userControllerImpl.goStock();
  }
}
