# vehicle-parking
Multilevel vehicle parking system

# DB Information 
   DB used :  MongoDB

   DatabaseName : multilevelparking
   DB Local URL:  spring.data.mongodb.uri=mongodb://localhost:27017/multilevelparking
   Tables/Docments :
          1. Cars
          2. ParkingLot
 
# Server Information :
    Port Used : 8082 (server.port=8082)


# URL endpoints :
localhost:8082/create_parking_lot?number=6

localhost:8082/park?carnumber=KA 01 HH 1234&color=White
localhost:8082/park?carnumber=KA 01 HH 9999&color=White
localhost:8082/park?carnumber=KA 01 BB 0001&color=Black
localhost:8082/park?carnumber=KA 01 HH 7777&color=Red
localhost:8082/park?carnumber=KA 01 HH 2701&color=Blue
localhost:8082/park?carnumber=KA 01 HH 3141&color=Black

localhost:8082/registration_numbers_for_cars_with_colour?color=Black
localhost:8082/slot_number_for_registration_number?regNo=KA 01 HH 3141
localhost:8082/slot_numbers_for_cars_with_colour?color=Black

localhost:8082/leave?slotNo=4
localhost:8082/status
