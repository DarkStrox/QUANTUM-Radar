from typing import List
from violation import Violation

class Fine:
    def __init__(self, plate_number: str, violations: List[Violation]):
        self.plate_number = plate_number
        self.violations = list(violations)

    def get_total_amount(self) -> float:
        return sum(v.fee for v in self.violations)

    def print_fine(self) -> None:
        print(f"Traffic for car {self.plate_number}")
        print(f"Total amount: {int(self.get_total_amount())} EGP")
        print("Violations:")
        for v in self.violations:
            print(f"- {v.description} : {int(v.fee)} EGP")
