package paymentManagement;

public class UPIPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using UPI.");
        return true;
    }
}
