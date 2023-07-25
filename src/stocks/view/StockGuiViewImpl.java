package stocks.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import stocks.util.SpringUtilities;


/**
 * This is the view class of the MVC design for stocks. View is basically the code which the
 * client gets and works with. This only has scanner functions to get the
 * input data from the clients. View represents the visualization of the data that model contains.
 * This is the GUI View of the Stocks.
 */

public class StockGuiViewImpl extends JFrame implements IView {
  @SuppressWarnings("compatibility:1101509145390026622")
  private static final long serialVersionUID = 1L;
  private static final String[] menuLabels = {"Create Flexible Portfolio", "Buy Stocks",
    "Sell Stocks", "Cost Basis", "Retrieve", "Invest Fixed Amount",
    "Dollar Cost Average", "Exit"};
  ButtonGroup menuLabelGroup = null;
  JPanel menuList = null;
  JPanel userInput = null;
  HashMap<String, JTextField> textFieldsMap = null;
  JPanel addlStockUserInput = null;
  JTextField addlStockName = null;
  JTextField addStockShare = null;
  HashMap<String, String> userInputStringInfoMap = null;

  String userInputStringInfoMap1;//deepthi
  ArrayList<JButton> listenerButtons = null;

  /**
   * A constructor of the GUI view.
   *
   * @param caption input the caption.
   */
  public StockGuiViewImpl(String caption) {
    super(caption);
    setSize(800, 600);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
    userInputStringInfoMap = new LinkedHashMap<String, String>();
    listenerButtons = new ArrayList<JButton>();
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    buildMenu();
  }


  /**
   * Method to display error.
   *
   * @param errorString is the error parameter.
   */
  public void displayError(String errorString) {
    displayPopup(errorString, "Attention !!", "ERROR");
  }

  /**
   * Method to get user input string info in hashmap.
   *
   * @param popupString contains the string.
   * @param popupTitle  contains the popup title.
   * @param popupType   contains the popup type.
   */
  @Override
  public void displayPopup(String popupString, String popupTitle, String popupType) {
    JOptionPane.showMessageDialog(null, popupString, popupTitle,
            "ERROR".equals(popupType) ? JOptionPane.ERROR_MESSAGE :
                    JOptionPane.INFORMATION_MESSAGE);
  }

  JLabel createTitleLabel(String label) {
    JLabel lbl = new JLabel(label);
    lbl.setPreferredSize(new Dimension(100, 100));
    lbl.setFont(new Font(lbl.getFont().getFamily(), Font.BOLD, 16));
    return lbl;
  }

  /**
   * Method to build menu.
   */
  public void buildMenu() {
    menuList = new JPanel();
    menuList.setLayout(new BoxLayout(menuList, BoxLayout.Y_AXIS));
    menuList.setAlignmentX(Component.CENTER_ALIGNMENT);
    menuList.add(Box.createRigidArea(new Dimension(30, 30)));
    menuList.add(createTitleLabel("          Menu"));
    menuList.add(createTitleLabel("         ======"));
    menuLabelGroup = new ButtonGroup();
    menuList.add(Box.createRigidArea(new Dimension(30, 30)));
    for (int i = 0; i < menuLabels.length; i++) {
      JRadioButton rb1 = new JRadioButton(menuLabels[i]);
      rb1.setActionCommand(String.valueOf(i + 1));
      menuLabelGroup.add(rb1);
      menuList.add(rb1);
    }
    menuList.add(Box.createRigidArea(new Dimension(30, 30)));
    JButton goBtn = new JButton("Go");
    goBtn.setActionCommand("MenuGo");
    //goBtn.addActionListener(this);
    listenerButtons.add(goBtn);
    menuList.add(goBtn, BorderLayout.CENTER);

    getContentPane().add(menuList);
  }

  /**
   * Method to get the menu selections.
   *
   * @return action which is performed.
   */
  public String getMenuSelection() {
    if (menuLabelGroup.getSelection() == null) {
      return null;
    }
    else {
      String action = menuLabelGroup.getSelection().getActionCommand();
      userInputStringInfoMap = new LinkedHashMap<String, String>();
      userInputStringInfoMap.put("action", action);
      menuLabelGroup.clearSelection();
      return action;
    }
  }

  /**
   * Method where user enters input in text field.
   *
   * @param textFields for user to enter input in the form of text.
   */
  public void showAddStockUserInput(String[] textFields) {
    if ("ERROR".equals(checkRequiredFields())) {
      return;
    }
    userInput.setVisible(false);
    if (addlStockUserInput == null) {
      addlStockUserInput = new JPanel();
      BoxLayout boxLayout = new BoxLayout(addlStockUserInput, BoxLayout.Y_AXIS);
      addlStockUserInput.setLayout(boxLayout);

      JPanel formPanel = new JPanel(new SpringLayout());
      formPanel.setPreferredSize(new Dimension(600, 2 * 40));
      formPanel.setMaximumSize(new Dimension(600, 2 * 40));
      int rows = 2;
      int cols = 2;

      for (int i = 0; i < textFields.length; i++) {
        JLabel jl = new JLabel(textFields[i] + ":", JLabel.TRAILING);
        formPanel.add(jl);

        if (i == 0) {
          addlStockName = new JTextField();
          formPanel.add(addlStockName);
        } else {
          addStockShare = new JTextField();
          formPanel.add(addStockShare);
        }

      }
      SpringUtilities.makeCompactGrid(formPanel, //parent
              rows, cols,
              100, 20,  //initX, initY
              10, 10);
      addlStockUserInput.add(formPanel);

      JPanel btnLayout = new JPanel();
      String[] buttons = {"Add", "Cancel"};
      for (int i = 0; i < buttons.length; i++) {
        JButton addBtn = new JButton(buttons[i]);
        addBtn.setActionCommand("AddlUserInput" + buttons[i]);
        //addBtn.addActionListener(this);
        listenerButtons.add(addBtn);
        btnLayout.add(addBtn);
      }
      addlStockUserInput.add(btnLayout);
      this.add(addlStockUserInput);
    }
    addlStockUserInput.setVisible(true);

  }

  /**
   * Method to add stock user input.
   */
  public void addStockUserInput() {
    String stockName = addlStockName.getText();
    String stockShare = addStockShare.getText();
    if (stockName == null || "".equals(stockName) || stockShare == null ||
            "".equals(stockShare)) {
      displayError("All fields are required");
      return;
    }
    if (userInputStringInfoMap.containsKey("AdditionalStock")) {
      userInputStringInfoMap.put("AdditionalStock",
              userInputStringInfoMap.get("AdditionalStock") + "," + stockName + "|" + stockShare);
    } else {
      userInputStringInfoMap.put("AdditionalStock", stockName + "|" + stockShare);
    }
    textFieldsMap.get("Additional Stocks").setText(userInputStringInfoMap.get("AdditionalStock"));
    cancelAddStockUserInput();
  }

  /**
   * Method to add the cancel add stock user input in gui.
   */
  public void cancelAddStockUserInput() {
    addlStockName.setText("");
    addStockShare.setText("");
    addlStockUserInput.setVisible(false);
    userInput.setVisible(true);
  }

  /**
   * Method to add update from user input.
   *
   * @param option     contains the different options.
   * @param textFields contains the text field for user to enter input.
   */
  public void addUpdateUserInput(String option, String[] textFields) {
    addUpdateUserInput(option, textFields, new String[]{"Submit", "Cancel", "Clear"});
  }

  /**
   * Method to add update from user input.
   *
   * @param option     contains the different options.
   * @param textFields contains the text field for user to enter input.
   * @param buttons    contains buttons in GUI.
   */
  public void addUpdateUserInput(String option, String[] textFields, String[] buttons) {
    menuList.setVisible(false);
    if (userInput != null) {
      userInput.removeAll();
      textFieldsMap.clear();
      textFieldsMap = null;
    }
    if (userInput == null) {
      userInput = new JPanel();
      userInput.setAlignmentX(Component.CENTER_ALIGNMENT);
      this.add(userInput);


      BoxLayout boxLayout = new BoxLayout(userInput, BoxLayout.Y_AXIS);
      userInput.setLayout(boxLayout);
    }
    textFieldsMap = new LinkedHashMap<String, JTextField>();

    userInput.add(createTitleLabel("Selected - " + menuLabels[Integer.parseInt(option) - 1]));
    //userInput.add(Box.createRigidArea(new Dimension(30, 30)));
    //
    String action = userInputStringInfoMap.get("action");
    JPanel formPanel = new JPanel(new SpringLayout());
    formPanel.setPreferredSize(new Dimension(600, textFields.length * 40));
    formPanel.setMaximumSize(new Dimension(600, textFields.length * 40));
    int rows = textFields.length;
    int cols = 2;
    for (int i = 0; i < textFields.length; i++) {
      JLabel jl = new JLabel(textFields[i] + ":", JLabel.TRAILING);
      formPanel.add(jl);
      JTextField jt = new JTextField();
      if (("1".equals(action) || "6".equals(action) || "7".equals(action)) &&
              (i + 1) == textFields.length) {
        jt.setEditable(false);
      }
      formPanel.add(jt);
      textFieldsMap.put(textFields[i], jt);
    }
    SpringUtilities.makeCompactGrid(formPanel, //parent
            rows, cols,
            100, 20,  //initX, initY
            10, 10);
    userInput.add(formPanel);
    //
    JPanel btnLayout = new JPanel();
    for (int i = 0; i < buttons.length; i++) {
      JButton addBtn = new JButton(buttons[i]);
      addBtn.setActionCommand("UserInput" + buttons[i]);
      //addBtn.addActionListener(this);
      listenerButtons.add(addBtn);
      btnLayout.add(addBtn);
    }
    userInput.add(btnLayout);

    userInput.setVisible(true);

  }

  /**
   * Method to go back to the main menu.
   */
  public void backToMainMenu() {
    userInput.setVisible(false);
    menuList.setVisible(true);
  }

  /**
   * Method to add form information to the user Input string info in map.
   *
   * @return the message whether error or successful.
   */
  public String addFormInfoTouserInputStringInfoMap() {
    if ("ERROR".equals(checkRequiredFields())) {
      return "ERROR";
    }
    for (String field : textFieldsMap.keySet()) {
      // all fields are mandatory
      String value = textFieldsMap.get(field).getText();
      if (value == null) {
        value = "";
      }
      userInputStringInfoMap.put(field, value);
    }
    return "SUCCESS";
  }

  /**
   * Method for setting listener.
   *
   * @param listener is the listener object.
   */
  @Override
  public void setListener(ActionListener listener) {
    for (JButton btn : listenerButtons) {
      if (btn.getActionListeners() == null || btn.getActionListeners().length == 0) {
        btn.addActionListener(listener);
      }
    }
  }

  /**
   * Method to display the GUI.
   */
  @Override
  public void display() {
    //pack();
    setVisible(true);
  }

  /**
   * Method to get user input string information map.
   *
   * @return user Input String Info Map.
   */
  @Override
  public HashMap<String, String> getUserInputStringInfoMap() {
    return userInputStringInfoMap;
  }

  String checkRequiredFields() {

    for (String field : textFieldsMap.keySet()) {
      // all fields are mandatory
      String value = textFieldsMap.get(field).getText();
      if (value == null) {
        value = "";
      }
      if ((!field.equals("Additional Stocks") && !field.equals("End Date")) && "".equals(value)) {
        displayError(field + " is required");
        return "ERROR";
      }
    }
    return "SUCCESS";

  }

  /**
   * Method to get user input string information map.
   *
   * @return user Input String Info Map.
   */
  @Override
  public String getUserInputStringInfoMap1() {
    String s = "";
    String action = userInputStringInfoMap.get("action");
    for (String field : textFieldsMap.keySet()) {
      // all fields are mandatory
      String value = textFieldsMap.get(field).getText();
      if (value == null) {
        value = "";
      }
      userInputStringInfoMap.put(field, value);
      if (value.equals("") && field.equals("End Date")) {
        value = LocalDate.now().
                minusDays(1).
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      }
      if ("".equals(value)) {
        continue;
      }
      else if (("1".equals(action) || "6".equals(action) || "7".equals(action)) &&
              ("Stock Symbol".equals(field))) {
        s = s + value + "|";
      } else {
        s = s + value + ",";
      }
    }
    return s.substring(0, s.length() - 1);
  }

  /**
   * Method to clear the form.
   */
  @Override
  public void clearForm() {
    for (String field : textFieldsMap.keySet()) {
      textFieldsMap.get(field).setText("");
    }
  }
}
