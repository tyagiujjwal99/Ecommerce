# Ecommerce
E-Commerce Application with role specified access using Spring Security.
logging.file.name=C:/Apps/ECommerce/logs/ecommerce.log

-------------------------------------------------------
Login Details :
Admin User1:
username = ujjwaltyagi@gmail.com
password = Abc@123

Admin User2:
username - admin@user.com
password - Admin@123

Seller User1:
Sell@123
seller@user.com

Buyer User1:
buyer@user.com
Buy@123
-----------------------------------------------------------------
Please use this curl in Postman :
https://api.postman.com/collections/30195903-914f91fb-c58b-4a98-a1a3-7d9dda1a68c5?access_key=PMAT-01J10DAWPA387VM58G2KN7N4CE


### API's for Postman:

Two admins are added, details are mentioned above.
Admin will do the registration of users (Seller/Buyer):
http://localhost:9001/users/add

User will authenticate using generate token API:
http://localhost:9001/token
If user is not registered in the system, then he/she will not be able to generate the token.

Application will be accessed by all the users as per their privileges by providing the generated token in the Authorization under Bearer token.
To delete the user:
http://localhost:9001/users/delete

To update the user details/roles:
http://localhost:9001/users/update



Seller can add, update and delete products:

Add a new product:
http://localhost:9001/products/add

Update an existing product on the basis of code:
http://localhost:9001/products/update/{code}

Remove an existing product on the basis of code:
http://localhost:9001/products/delete/{code}

Buyer can view products by code and all products:

View product by code:
http://localhost:9001/products/{code}

View all products:
http://localhost:9001/products/getallproduct

Search product by string (match case):
http://localhost:9001/products/search/{string}


-----------------------------------------------------------------------


DDL/DML:
Before running the project, run this query in Mysql:
Create database products;

After this hibernate will create all the required tables in the database as per the models in the project.

----------------------------------------------------------------------
