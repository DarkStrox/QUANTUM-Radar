from datetime import datetime
from car_type import CarType
from radar_observation import RadarObservation
from seatbelt_rule import SeatbeltRule
from speed_limit_rule import SpeedLimitRule
from qu_radar import QuRadar

def main():
    print("==================================================")
    print("     QuRadar Traffic Monitoring System Demo       ")
    print("==================================================\n")

    radar = QuRadar()

    radar.add_rule(SeatbeltRule(100.0))
    radar.add_rule(SpeedLimitRule(CarType.PRIVATE, 80.0, 300.0))
    radar.add_rule(SpeedLimitRule(CarType.TRUCK, 60.0, 300.0))

    print("--- Processing Observations ---\n")

    obs1 = RadarObservation(
        "ABC1234",
        datetime.now(),
        CarType.PRIVATE,
        94.0,
        False
    )
    radar.observe(obs1)
    print()

    obs2 = RadarObservation(
        "TRK5678",
        datetime.now(),
        CarType.TRUCK,
        75.0,
        False
    )
    radar.observe(obs2)
    print()

    obs3 = RadarObservation(
        "SAFE100",
        datetime.now(),
        CarType.PRIVATE,
        70.0,
        True
    )
    radar.observe(obs3)

    print("\n==================================================")
    print("1. get_all_possible_fines (Plate Number -> Total Amount)")
    print("==================================================")
    fines_map = radar.get_all_possible_fines()
    for plate, total in fines_map.items():
        print(f"Plate: {plate} | Total amount: {int(total)} EGP")

    print("\n==================================================")
    print("2. get_all_violated_rules_with_count")
    print("==================================================")
    rule_counts = radar.get_all_violated_rules_with_count()
    for rule_name, count in rule_counts.items():
        print(f"Rule: {rule_name} -> Violations Count: {count}")

if __name__ == "__main__":
    main()
