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

### Parking boy
**GIVEN** 一个停车仔和其管理的两个有空位的停车场
**WHEN**  停车
**THEN** 停在第一个停车场

**GIVEN** 一个停车仔和其管理的两个停车场，只有一个有空位
**WHEN**  停车
**THEN** 停在有空位的停车场

**GIVEN** 一个停车仔和其管理的两个满的停车场
**WHEN**  停车
**THEN** 错误

**GIVEN** 一个停车仔和其管理的两个停车场，一张有对应车停着的停车票
**WHEN**  取车
**THEN** 取出车


**GIVEN** 一个停车仔和其管理的两个停车场，一张没有对应车停着的停车票
**WHEN**  取车
**THEN** 错误