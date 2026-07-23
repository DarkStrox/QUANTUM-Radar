from typing import Optional
from rule import Rule
from radar_observation import RadarObservation
from violation import Violation

class SeatbeltRule(Rule):
    RULE_NAME = "Seatbelt Rule"

    def __init__(self, fee: float = 100.0):
        self.fee = fee

    def get_rule_name(self) -> str:
        return self.RULE_NAME

    def evaluate(self, observation: RadarObservation) -> Optional[Violation]:
        if not observation.seatbelt_fastened:
            return Violation(self.RULE_NAME, "Seatbelt not fastned", self.fee)
        return None
