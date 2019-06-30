### Parking lot
**GIVEN** a car and the parking lot with available space  
**WHEN** the car go into parking lot  
**THEN** return a ticket

**GIVEN** a car and the parking lot without available space  
**WHEN** the car go into parking lot  
**THEN** show error  

**GIVEN** a ticket and a parking lot parked corresponding car
**WHEN** use this ticket to pick the car  
**THEN** give the car

**GIVEN** a ticket and a parking lot without corresponding car
**WHEN** use this ticket to pick car  
**THEN** show error

**GIVEN** a valid ticket and a parking lot parked corresponding car
**WHEN** use this ticket to pick the car twice
**THEN** show error