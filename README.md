Unzip the File and Import as maven project.
Once build, Run the RiseApplication.java as Java Application.
Server Port : 5000
Swagger URL:
http://localhost:5000/swagger-ui.html

Tast#1.  Refer Source Code for design and Coding attached. I have tried my best to corelate with Real World environment only where real testing and coding skills are been tested.For this, I have H2 DB In  memory. Also I used few Named Queries So that In real world where data may be in millions, named queries will give high perforamnce results. This project is totally DB independent and can be connect to any DB as in real time situation.

Task#2. Due to time constraint , I have just created the UI for User Upload File also. This can be found by following URL.
http://localhost:5000/

Please use Only file provided which is Albion and Gloucester. The reason is because, I had to fix few data issues in the actual file given.
Albion File has two records which is intentially made in correct to show that Invalid/Non-Corealted data will not be loaded.
Glucester File is untouched and good to load.

Tast#3. Only Corelated Data will be successfully loaded.
You can go below URL to check the data loaded. 

http://localhost:5000/h2/


Task#4 & 5. As informed, that I couldnt get the time to create a fancy User Interface.But below contract link will be helpful to get the Aggregated Data.
Make sure you upload the file, before you execute. Else you wont get any result.

http://localhost:5000/swagger-ui.html

(A) Aggregated Statical Information , 
	http://localhost:5000/rise/agg
(B) Average Speed of All Horses along with their name and Id.
	http://localhost:5000/rise/speed/all
(C)	Average Speed of Specific Horse you are intrested in. You need to enter HorseId
	http://localhost:5000/rise/speed/1717028
(D) Fastest last 800 metre run  Horse Details
	http://localhost:5000/rise/speed/fast800
(E) Fastest last 400 metre run  Horse Details
	http://localhost:5000/rise/speed/fast400

## Unit Test

To run Unit Test, go to src/test/java and run as JUnit Test. 
This Tests majorly  Services and Repository.

## TODOs
1) Change the File upload read mechanism to Input Stream instead of reading fully. I have used here as CSV files were vey small.
2) Fancy UI display for all the APIS's .
3. Write Integration Tests
4. Write Validation to stop reloading the same file.
