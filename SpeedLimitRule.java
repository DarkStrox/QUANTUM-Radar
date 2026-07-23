import java.util.Optional;

public class SpeedLimitRule implements Rule {
    private final CarType carType;
    private final double maxSpeed;
    private final double fee;
    private final String ruleName;

    public SpeedLimitRule(CarType carType, double maxSpeed, double fee) {
        this.carType = carType;
        this.maxSpeed = maxSpeed;
        this.fee = fee;
        this.ruleName = carType.name().substring(0, 1).toUpperCase() + carType.name().substring(1).toLowerCase() + " Speed Limit Rule";
    }

    @Override
    public String getRuleName() {
        return ruleName;
    }

    @Override
    public Optional<Violation> evaluate(RadarObservation observation) {
        if (observation.getCarType() == carType && observation.getSpeed() > maxSpeed) {
            int actualSpeed = (int) observation.getSpeed();
            int limit = (int) maxSpeed;
            String desc = "speed of " + actualSpeed + " exceeded max allowed " + limit;
            return Optional.of(new Violation(ruleName, desc, fee));
        }
        return Optional.empty();
    }
}
