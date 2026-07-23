import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fine {
    private final String plateNumber;
    private final List<Violation> violations;

    public Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.violations = new ArrayList<>(violations);
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public List<Violation> getViolations() {
        return Collections.unmodifiableList(violations);
    }

    public double getTotalAmount() {
        double total = 0;
        for (Violation v : violations) {
            total += v.getFee();
        }
        return total;
    }

    public void printFine() {
        System.out.println("Traffic for car " + plateNumber);
        System.out.println("Total amount: " + (int) getTotalAmount() + " EGP");
        System.out.println("Violations:");
        for (Violation v : violations) {
            System.out.println("- " + v.getDescription() + " : " + (int) v.getFee() + " EGP");
        }
    }
}
