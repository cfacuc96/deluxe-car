# Deluxe Car
API in Java with Spring Boot, JPA, Hibernate, JUnit and Swagger documentation.

Inside the documentation folder, located at the root of the deluxe-car, you can find several documents, each with different functions.

* Instrucciones.pdf: this document details the first steps to follow to test the app
* Requerimiento_6.docx: This document is divided into two sections. The first one details the changes that were made in the project as a group. The second details the changes made individually.
* DER_finalProject.png: is an image of the DER of the project.

### First Step
The project has an initialization script in resources folder in src with the name finalProject.sql.

### Second Step

Before starting to execute REQUESTS on the different ENDPOINTS of the project, it is necessary to carry out the initial load of the default users of the system. To do this, from the PostMan we will indicate the following address:

Mapping: POST

URL local: localhost:8080/api/v1/parts/load 

### Third Step


Once the initial upload is done, we will go to the next ENDPOINT to
LOG IN with the corresponding credentials, and obtain a user token:

Mapping: POST

URL local: localhost:8080/api/v1/parts/login

In the REQUEST body, they must put some of the following credentials:

* Argentina: 
  
            {
                "username" : "argSub",
                "password" : "123"
            }

* Uruguay:

            {
                "username" : "uruSub",
                "password" : "123"
            }

* Colombia:

            {
                "username" : "colSub",
                "password" : "123"
            }

After executing this ENDPOINT, the program will return an access token, with which we can identify ourselves when using the other ENDPOINTS. The token, in PostMan, is used in the “Authorization” tab, you must select “Bearer Token” in the drop-down menu, and then copy the value of the token in the box.

## IMPORTANT
No ENDPOINT, except the load and login, will work if we do not put the access token.

## Endpoints
Please check the doc.html in documentation folder, to check the different endpoints and formats.