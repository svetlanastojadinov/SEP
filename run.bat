cd payment_concentrator
start cmd /k call run.bat
cd..

cd bank
start cmd /k call run.bat
cd..

cd pcc
start cmd /k call run.bat
cd..

cd bank-front
start cmd /k npm start
cd..

cd client/sep-client
start cmd /k npm start
cd..
cd..


