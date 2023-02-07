# MedicalAdmission

CRUD application for Medical Admission system in ProMedicus.

### Tech Stack:
* Apache Springboot 3.0.2 - Web, JPA, Test
* H2 - In memory database to hold the admission records
* Maven - build tool
* Postman
* JUnit 5

### Build:
`mvn clean install`

### Steps to follow:
1. Bring up the Application by running the `main` method in `Application.java` - you should see the message 
![image](https://user-images.githubusercontent.com/124317572/217131421-8b3e7f43-e02d-4c80-96a0-e8e3131c6759.png)

2. This will bring up the application and we can browse it using a browser/ postman. To test it, hit the endpoint: `http://localhost:8080/greeting` and we should see a response `Hello, world!`
![image](https://user-images.githubusercontent.com/124317572/217131971-b21d5d8b-1c27-4618-a69d-10cd10d2732b.png)

3. To add an admission, call the endpoint: `http://localhost:8080/addAdmission` with `POST` method and Admission object in the request body.

![image](https://user-images.githubusercontent.com/124317572/217132169-8d06dcbf-1d9e-4504-a358-ff261e1e2fc2.png)

4. To update an admission, call the endpoint `http://localhost:8080/editAdmission` with `POST` method and Admission object in the request body.

![image](https://user-images.githubusercontent.com/124317572/217132402-cbb6a30e-46b2-4e5c-91d6-c65cf586b024.png)

5. To fetch all the admission records, hit the endoint: `http://localhost:8080/fetchAdmissions` with `GET` method and it should return list of admissions.

![image](https://user-images.githubusercontent.com/124317572/217132565-003abc51-405d-471c-ae95-3e60fb75c034.png)
