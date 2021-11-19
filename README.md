# MiniProject-SCS3203-18020102


In the project folder

build the app - Run "gradlew build -Dquarkus.package.type=uber-jar" 

Run the app - "java -jar build/petstore-runner.jar" 


 CRUD Operations

Add a pet

url : http://localhost:8080/petStore/addPet

method : POST

body : { "petName" : "Bruno", "petType" : "Dog", "petAge" : 5 }

Get all pets

url : http://localhost:8080/petStore

method : GET

Get a pet by ID

url : http://localhost:8080/petStore/1

method : GET

Remove a pet

url : http://localhost:8080/petStore/removePet/1

method : DELETE

Update a pet

url : http://localhost:8080/petStore/updatePet

method : PUT

body : { "petId": 1, "petName" : "Lexi", "petType" : "Dog", "petAge" : 7 }

Add a pet Type

url : http://localhost:8080/petStore/addPetType

method : POST

body : { "petType" : "Dog" }

Get all pet types

url : http://localhost:8080/petStore/getAllPetTypes

method : GET

Get a pet type by ID

url : http://localhost:8080/petStore/getPetTypeById/1

method : GET

Update a pet type

url : http://localhost:8080/petStore/updatePetType

method : PUT

body : { "petType" : "Cat", "petTypeId": 1 }

Delete a pet type

url : http://localhost:8080/petStore/removePetType/1

method : DELETE
