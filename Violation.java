public class Violation {
    private final String ruleName;
    private final String description;
    private final double fee;

    public Violation(String ruleName, String description, double fee) {
        this.ruleName = ruleName;
        this.description = description;
        this.fee = fee;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }
}
