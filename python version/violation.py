class Violation:
    def __init__(self, rule_name: str, description: str, fee: float):
        self.rule_name = rule_name
        self.description = description
        self.fee = fee
