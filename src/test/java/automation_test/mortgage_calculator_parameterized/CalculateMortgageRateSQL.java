package automation_test.mortgage_calculator_parameterized;

import automation_test.BaseUIClass;
import org.testng.annotations.Test;
import page_object.Home;
import utillities.DateUtils;

public class CalculateMortgageRateSQL extends BaseUIClass {
    @Test
    public void calculateMonthlyPayment() {
        String[] date = DateUtils.returnNextMonth();
        new Home(driver)
                .typeHomePrice("300000")
                .downPaymentInDollar()
                .typeDownPayment("60000")
                .typeLoanAmount("240000")
                .typeInterestRate("3")
                .typeLoanTermYears("30")
                .selectMonth(date[0])
                .typeYear(date[1])
                .typePropertyTax("5000")
                .typePMI("0.5")
                .typeHomeOwnerInsurance("1000")
                .typeMonthlyHOA("100")
                .selectLoanType("FHA")
                .selectBuyOrRefinance("Buy")
                .clickOnCalculateButton()
                .validateTotalMonthlyPayment("1,611.85");
    }
}

