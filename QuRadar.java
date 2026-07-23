import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuRadar {
    private final List<Rule> rules = new ArrayList<>();
    private final List<Fine> issuedFines = new ArrayList<>();
    private final Map<String, Integer> ruleViolationCounts = new HashMap<>();

    public void addRule(Rule rule) {
        if (rule != null) {
            rules.add(rule);
            ruleViolationCounts.putIfAbsent(rule.getRuleName(), 0);
        }
    }

    public Optional<Fine> observe(RadarObservation observation) {
        List<Violation> violations = new ArrayList<>();

        for (Rule rule : rules) {
            Optional<Violation> violationOpt = rule.evaluate(observation);
            if (violationOpt.isPresent()) {
                Violation violation = violationOpt.get();
                violations.add(violation);

                ruleViolationCounts.put(rule.getRuleName(), ruleViolationCounts.getOrDefault(rule.getRuleName(), 0) + 1);
            }
        }

        if (!violations.isEmpty()) {
            Fine fine = new Fine(observation.getPlateNumber(), violations);
            issuedFines.add(fine);
            fine.printFine();
            return Optional.of(fine);
        }

        return Optional.empty();
    }

    public Map<String, Double> getAllPossibleFines() {
        Map<String, Double> summary = new HashMap<>();
        for (Fine fine : issuedFines) {
            summary.put(
                fine.getPlateNumber(),
                summary.getOrDefault(fine.getPlateNumber(), 0.0) + fine.getTotalAmount()
            );
        }
        return summary;
    }

    public Map<String, Integer> getAllViolatedRulesWithCount() {
        return new HashMap<>(ruleViolationCounts);
    }

    public List<Fine> getIssuedFines() {
        return Collections.unmodifiableList(issuedFines);
    }

    public List<Rule> getRules() {
        return Collections.unmodifiableList(rules);
    }
}
