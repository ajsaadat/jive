In order to run this application run the following command:
	mvn clean install. this command needs to be executed in homework root.
	mvn clean package. this command needs to be executed in restapi root. 
	java -jar (name of the jar file in target folder). Example: java -jar restapi-0.0.1-SNAPSHOT.jar
	above command will run a tomcat on port 8080. 
Following commands can be executed from http://localhost:8080/deck
	1) POST to above mentioned URL will create a new shuffled deck.
	2) GET to above mentioned URL with returned id will pop a card from the deck. ex http://localhost:8080/deck?deckID=1234
	3) PUT to above mentioned URL with returned id will reshuffle the deck. ex http://localhost:8080/deck?deckID=1234
