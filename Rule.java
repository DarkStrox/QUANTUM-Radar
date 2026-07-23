import java.util.Optional;

public interface Rule {
    String getRuleName();
    Optional<Violation> evaluate(RadarObservation observation);
}
