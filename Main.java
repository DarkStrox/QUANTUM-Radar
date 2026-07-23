import java.time.LocalDateTime;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     QuRadar Traffic Monitoring System Demo       ");
        System.out.println("==================================================\n");

        QuRadar radar = new QuRadar();

        radar.addRule(new SeatbeltRule(100.0));
        radar.addRule(new SpeedLimitRule(CarType.PRIVATE, 80.0, 300.0));
        radar.addRule(new SpeedLimitRule(CarType.TRUCK, 60.0, 300.0));

        System.out.println("--- Processing Observations ---\n");

        RadarObservation obs1 = new RadarObservation(
            "ABC1234",
            LocalDateTime.now(),
            CarType.PRIVATE,
            94.0,
            false
        );
        radar.observe(obs1);
        System.out.println();

        RadarObservation obs2 = new RadarObservation(
            "TRK5678",
            LocalDateTime.now(),
            CarType.TRUCK,
            75.0,
            false
        );
        radar.observe(obs2);
        System.out.println();

        RadarObservation obs3 = new RadarObservation(
            "SAFE100",
            LocalDateTime.now(),
            CarType.PRIVATE,
            70.0,
            true
        );
        radar.observe(obs3);

        System.out.println("\n==================================================");
        System.out.println("1. getAllPossibleFines (Plate Number -> Total Amount)");
        System.out.println("==================================================");
        Map<String, Double> finesMap = radar.getAllPossibleFines();
        for (Map.Entry<String, Double> entry : finesMap.entrySet()) {
            System.out.println("Plate: " + entry.getKey() + " | Total amount: " + entry.getValue().intValue() + " EGP");
        }

        System.out.println("\n==================================================");
        System.out.println("2. getAllViolatedRulesWithCount");
        System.out.println("==================================================");
        Map<String, Integer> ruleCounts = radar.getAllViolatedRulesWithCount();
        for (Map.Entry<String, Integer> entry : ruleCounts.entrySet()) {
            System.out.println("Rule: " + entry.getKey() + " -> Violations Count: " + entry.getValue());
        }
    }
}
