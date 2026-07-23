from typing import List, Dict, Optional
from rule import Rule
from radar_observation import RadarObservation
from fine import Fine
from violation import Violation

class QuRadar:
    def __init__(self):
        self.rules: List[Rule] = []
        self.issued_fines: List[Fine] = []
        self.rule_violation_counts: Dict[str, int] = {}

    def add_rule(self, rule: Rule) -> None:
        if rule is not None:
            self.rules.append(rule)
            if rule.get_rule_name() not in self.rule_violation_counts:
                self.rule_violation_counts[rule.get_rule_name()] = 0

    def observe(self, observation: RadarObservation) -> Optional[Fine]:
        violations: List[Violation] = []

        for rule in self.rules:
            violation = rule.evaluate(observation)
            if violation is not None:
                violations.append(violation)
                rule_name = rule.get_rule_name()
                self.rule_violation_counts[rule_name] = self.rule_violation_counts.get(rule_name, 0) + 1

        if violations:
            fine = Fine(observation.plate_number, violations)
            self.issued_fines.append(fine)
            fine.print_fine()
            return fine

        return None

    def get_all_possible_fines(self) -> Dict[str, float]:
        summary: Dict[str, float] = {}
        for fine in self.issued_fines:
            summary[fine.plate_number] = summary.get(fine.plate_number, 0.0) + fine.get_total_amount()
        return summary

    def get_all_violated_rules_with_count(self) -> Dict[str, int]:
        return dict(self.rule_violation_counts)
