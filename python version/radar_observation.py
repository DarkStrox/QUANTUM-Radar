from datetime import datetime
from car_type import CarType

class RadarObservation:
    def __init__(self, plate_number: str, date: datetime, car_type: CarType, speed: float, seatbelt_fastened: bool):
        self.plate_number = plate_number
        self.date = date
        self.car_type = car_type
        self.speed = speed
        self.seatbelt_fastened = seatbelt_fastened
