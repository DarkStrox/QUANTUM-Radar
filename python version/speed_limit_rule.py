from typing import Optional
from rule import Rule
from car_type import CarType
from radar_observation import RadarObservation
from violation import Violation

class SpeedLimitRule(Rule):
    def __init__(self, car_type: CarType, max_speed: float, fee: float):
        self.car_type = car_type
        self.max_speed = max_speed
        self.fee = fee
        formatted_type = car_type.value.capitalize()
        self.rule_name = f"{formatted_type} Speed Limit Rule"

    def get_rule_name(self) -> str:
        return self.rule_name

    def evaluate(self, observation: RadarObservation) -> Optional[Violation]:
        if observation.car_type == self.car_type and observation.speed > self.max_speed:
            actual_speed = int(observation.speed)
            limit = int(self.max_speed)
            desc = f"speed of {actual_speed} exceeded max allowed {limit}"
            return Violation(self.rule_name, desc, self.fee)
        return None
