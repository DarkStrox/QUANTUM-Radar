import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * System Description:
 * QuRadar is an advanced traffic radar surveillance and infraction management system built in Java. 
 * It continuously processes observation feeds sent from physical radar units installed at traffic monitoring points.
 * Each observation payload conveys critical metrics captured per passing vehicle, including license 
 * plate identification, timestamp, vehicle category (Private car, Truck, Bus), recorded speed, 
 * and seatbelt compliance status.
 *
 * Upon receiving an observation, QuRadar dynamically runs all registered traffic rules against the payload.
 * When violations are detected, QuRadar generates a structured fine notice specifying each infraction and its 
 * corresponding penalty in Egyptian Pounds (EGP), prints the fine report formatted to standards, and retains 
 * aggregated statistics for system-wide auditing.
 *
 * Extensibility Architecture:
 * Designed following the Open-Closed Principle (OCP) using the Strategy Pattern. Rules implement the 
 * {@link Rule} interface and are registered dynamically via {@link #addRule(Rule)}, enabling new traffic policies 
 * to be added without modifying the core QuRadar implementation.
 */
public class QuRadar {
    private final List<Rule> rules = new ArrayList<>();
    private final List<Fine> issuedFines = new ArrayList<>();
    private final Map<String, Integer> ruleViolationCounts = new HashMap<>();

    /**
     
    @param rule the traffic rule to register
    */
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

                // Increment violation count for the rule
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
