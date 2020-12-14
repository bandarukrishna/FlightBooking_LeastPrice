package Assessment.Assesment;
import org.testng.annotations.Test;
public class Assesment1 extends TestBase {
    @Test
    public void TC_1() {
        launchbrowser();
        searchPage sp = new searchPage();
        sp.selectdepcity("Boston");
        sp.selectdescity("Dublin");
        sp.clickonSubmit();
        sp.gettheleastpriceRow();
        sp.fillpaymentData(prop.getProperty("cname"), prop.getProperty("caddress"), prop.getProperty("ccity"), prop.getProperty("cstate"),
            prop.getProperty("czipCode"), prop.getProperty("ccardType"), prop.getProperty("ccreditCardNumber"), prop.getProperty("cmonth"),
            prop.getProperty("cyear"), prop.getProperty("cnameonCard"));
        sp.gettheconfirmMsgAfterPayment();
        sp.getthejsontext();

    }

}