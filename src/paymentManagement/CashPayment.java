package paymentManagement;


public class CashPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " in cash.");
        return true;
    }
}

