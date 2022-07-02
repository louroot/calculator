## Weekly payment calculator ##

This is a spring boot application that makes an attempt at calculating
simple interest for a specified amount, terms (in months) and annual interest rate,
it will generate as a response a list of weekly payments along with the amount to pay
and the dates when due.

### Some notes ###
The app accepts amounts >= 1, terms >= 1 (assumes its in months), and the rate > 0

The application can be started using your editor of choice (intellij for me),
just run the CalculatorApplication class, should by default be accessible via localhost:8081.

Another option is to build the docker image and start the container with docker,
make sure to map an actual port on your machine to the port 8081 that the app
listens to before starting the container.

If using the image call 127.0.0.1:yourport
with postman, if running from an editor just make your calls to localhost:8081

To create the docker image make sure to follow these steps
    
* run `mvn clean install` to generate the jar
* run `docker build -t calculator .`
* with the image built, you can start it up from docker ui, make sure assign a port
* or just run the docker command `docker run -p 127.0.0.1:8081:8081/tcp calculator` 

Example curl calls

Update the calls to 127.0.0.1 if using docker

/health

`curl --location --request GET 'localhost:8081/health'`

/interest

`curl --location --request POST 'localhost:8081/interest' \
--header 'Content-Type: application/json' \
--data-raw '{
"amount": 1000,
"terms": 12,
"rate": 5
}'`