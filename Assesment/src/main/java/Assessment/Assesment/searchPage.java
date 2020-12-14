package Assessment.Assesment;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class searchPage extends TestBase {
  Select s = null;
  @FindBy(xpath = "//*[@name='fromPort']")
  WebElement depCity;
  @FindBy(xpath = "//*[@name='toPort']")
  WebElement desCity;
  @FindBy(xpath = "//*[@type='submit']")
  WebElement submit;
  @FindBy(xpath = "//*[@class='table']")
  WebElement table;

  //Payment details
  @FindBy(xpath = "//*[@id='inputName']")
  WebElement name;
  @FindBy(xpath = "//*[@name='address']")
  WebElement address;
  @FindBy(xpath = "//*[@name='city']")
  WebElement city;
  @FindBy(xpath = "//*[@name='state']")
  WebElement state;
  @FindBy(xpath = "//*[@name='zipCode']")
  WebElement zipCode;
  @FindBy(xpath = "//*[@name='cardType']")
  WebElement cardType;
  @FindBy(xpath = "//*[@id='creditCardNumber']")
  WebElement creditCardNumber;
  @FindBy(xpath = "//*[@id='creditCardMonth']")
  WebElement month;
  @FindBy(xpath = "//*[@id='creditCardYear']")
  WebElement year;
  @FindBy(xpath = "//*[@id='nameOnCard']")
  WebElement nameonCard;
  @FindBy(tagName = "h1")
  WebElement thankumsg;
  @FindBy(tagName = "pre")
  WebElement jsonLoc;
  
 ////get the ticket confirmation text after booking 
  @FindBy(xpath = "//*[@value='Purchase Flight']")
  WebElement purchaseflight;
  public searchPage() {

    PageFactory.initElements(driver, this);
  }

  public void selectdepcity(String departurecity) {

    s = new Select(depCity);
    s.selectByValue(departurecity);
  }
  public void selectdescity(String destinationcity) {

    s = new Select(desCity);
    s.selectByValue(destinationcity);

  }

  public void clickonSubmit() {
    submit.click();
  }

  public void gettheleastpriceRow() {
    Map < Integer,
    Double > prices = new HashMap < Integer,
    Double > ();
    List < WebElement > rows = table.findElements(By.tagName("tr"));
    for (WebElement row: rows) {
      List < WebElement > cols = row.findElements(By.tagName("td"));
      for (WebElement col: cols) {
        if (col.getText().contains("$")) {
          String p = col.getText().replace("$", "");
          double price = Double.parseDouble(p);
          prices.put(rows.indexOf(row), price);
          //pricelist.add(price);
          System.out.println(price);
        }
      }
    }
    Double minprice = Collections.min(prices.values());
    for (Map.Entry < Integer, Double > entry: prices.entrySet()) {

      if (entry.getValue() == minprice) {
        System.out.println("The key for value " + minprice + " is " + entry.getKey());
        rows.get(entry.getKey()).findElements(By.tagName("td")).get(0).click();
        System.out.println("least price flight selected");
        break;
      }
    }
  }

  public void fillpaymentData(String cname, String caddress, String ccity, String cstate, String czipCode,String ccardType, String ccreditCardNumber, String cmonth, String cyear, String cnameonCard) {

    name.sendKeys(cname);
    address.sendKeys(caddress);
    city.sendKeys(ccity);
    state.sendKeys(cstate);
    zipCode.sendKeys(czipCode);
   // selecting cardtype from drop down
  
   s = new Select(cardType);
   s.selectByValue(ccardType);
  
    creditCardNumber.sendKeys(ccreditCardNumber);
    month.sendKeys(cmonth);
    year.sendKeys(cyear);
    nameonCard.sendKeys(cnameonCard);
    purchaseflight.click();

  }
  
  
  public void gettheconfirmMsgAfterPayment() {
	  thankumsg.getText().toString();
	  System.out.println("confirm msg: "+thankumsg.getText().toString());
	  
  }
  public void getthejsontext() {
	  
	  System.out.println("json text: "+jsonLoc.getText().toString());
	  
  }
}