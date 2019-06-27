GIVEN a car and the parking lot with available space
WHEN the car go into parking lot
THEN let it go in and give ticket to the driver

GIVEN a car and the parking lot without available space
WHEN the car go into parking lot
THEN show a message saying "no enough space"

GIVEN a driver have a ticket and the parking lot with related car
WHEN the driver use this ticket to pick up his/her car
THEN give him the car

GIVEN a ticket and the parking lot without related car
WHEN the driver use this ticket to pick up his car
THEN show a message saying "invalid ticket"