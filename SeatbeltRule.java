import java.util.Optional;

public class SeatbeltRule implements Rule {
    private static final String RULE_NAME = "Seatbelt Rule";
    private final double fee;

    public SeatbeltRule(double fee) {
        this.fee = fee;
    }

    public SeatbeltRule() {
        this(100.0);
    }

    @Override
    public String getRuleName() {
        return RULE_NAME;
    }

    @Override
    public Optional<Violation> evaluate(RadarObservation observation) {
        if (!observation.isSeatbeltFastened()) {
            return Optional.of(new Violation(
                RULE_NAME,
                "Seatbelt not fastned",
                fee
            ));
        }
        return Optional.empty();
    }
}
