from abc import ABC, abstractmethod
from typing import Optional
from radar_observation import RadarObservation
from violation import Violation

class Rule(ABC):
    @abstractmethod
    def get_rule_name(self) -> str:
        pass

    @abstractmethod
    def evaluate(self, observation: RadarObservation) -> Optional[Violation]:
        pass
