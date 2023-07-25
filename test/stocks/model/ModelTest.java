package stocks.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Test for model.
 */
public class ModelTest {
  PortfolioDAOImpl portfolioDAO = new PortfolioDAOImpl();
  PortfolioImpl portfolio = new PortfolioImpl();

  @Test
  public void toStringTest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append(null);
    Assert.assertEquals("PortFolio Name:null\n" +
            "Stock List: \n", String.valueOf(
            portfolio.toString("2022-11-11")));

  }

  @Test
  public void totalCostBasistest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,2022-11-11");
    Assert.assertEquals("2182.67", String.valueOf(
            portfolioDAO.totalCostBasis("MyPF1", "2022-11-11")));
  }

  @Test
  public void createInflex() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF1,5,GOOG|5");
    String portfolioDetails = "MyPF1,5,GOOG|5";
    Assert.assertEquals("MyPF1,5,GOOG|5", portfolioDetails);
  }

  @Test
  public void createFlex() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    fileWriter.append("MyPF2,5,GOOG|5");
    String portfolioDetails = "MyPF2,5,GOOG|5";
    Assert.assertEquals("MyPF2,5,GOOG|5", portfolioDetails);
  }

  @Test
  public void getAllPortFoliosTest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    Assert.assertEquals("{test=stocks.model.PortfolioImpl@64d2d351, MyPF2=stocks.model." +
            "PortfolioImpl@1b68b9a4, MyPF1=stocks.model.PortfolioImpl@4f9a3314, " +
            "Deepthi=stocks.model.PortfolioImpl@3b2c72c2}", String.valueOf(
            portfolioDAO.getAllPortFolios()));

  }

  @Test
  public void getTotalValueOfPortfolioTest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    Assert.assertEquals("4279.04", String.valueOf(
            portfolioDAO.getTotalValueOfPortfolio("MyPF1", "2022-11-11")));

  }

  @Test
  public void totalCostBasisTest() throws IOException {
    FileWriter fileWriter = new FileWriter("AllPortfolio" + ".json");
    Assert.assertEquals("2182.67", String.valueOf(
            portfolioDAO.totalCostBasis("MyPF1", "2022-11-11")));

  }


}
