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
username - seller@user.com
password - Sell@123

Buyer User1:
username - buyer@user.com
password - Buy@123

-----------------------------------------------------------------

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


Run following queries in Mysql to insert data in tables:

-- Dumping database structure for products
CREATE DATABASE IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `products`;

-- Dumping structure for table products.delivery
CREATE TABLE IF NOT EXISTS `delivery` (
  `d_id` int(11) NOT NULL,
  `days` int(11) NOT NULL,
  `pincode` int(11) NOT NULL,
  `product_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`d_id`),
  KEY `FKco4ppaq0bvd8c3hjxsfcu7tih` (`product_code`),
  CONSTRAINT `FKco4ppaq0bvd8c3hjxsfcu7tih` FOREIGN KEY (`product_code`) REFERENCES `products` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table products.delivery: ~4 rows (approximately)
INSERT IGNORE INTO `delivery` (`d_id`, `days`, `pincode`, `product_code`) VALUES
	(1, 1, 201001, 1),
	(3, 2, 110010, 3),
	(6, 4, 400001, 4),
	(17, 4, 400001, 7);

-- Dumping structure for table products.products
CREATE TABLE IF NOT EXISTS `products` (
  `code` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `details` varchar(600) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table products.products: ~7 rows (approximately)
INSERT IGNORE INTO `products` (`code`, `brand`, `details`, `name`, `price`) VALUES
	(1, 'Puma', 'Shoe', 'Sports Shoes', '1999'),
	(3, 'Levis', 'Shirts', 'D-Shirt', '2199'),
	(4, 'Levis', 'Shirts', 'D-Shirt', '2199'),
	(5, 'Levis', 'Shirts', 'D-Shirt', '2199'),
	(7, 'Maruti', 'Sports Car', 'Breeza', '1099000'),
	(8, 'Ferrari', 'Sports Car', 'F7', '5099000'),
	(9, 'Honda', 'Sedan', 'Honda City', '950000');

-- Dumping structure for table products.users
CREATE TABLE IF NOT EXISTS `users` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table products.users: ~5 rows (approximately)
INSERT IGNORE INTO `users` (`email`, `firstname`, `lastname`, `password`, `roles`) VALUES
	('admin@user.com', 'Admin', 'Test', '$2a$10$DE9pZHg2fTFNbS3QHjir/.u8MD391wkoqysELvn/MV4734AIskGGe', 'ROLE_ADMIN'),
	('buyer@user.com', 'Buyer', 'Party', '$2a$10$YYIbLPr463QWwroIblZVceAgo9w5KOotBqVG3OkrNqV3fwB2swKRa', 'ROLE_BUYER'),
	('seller@user.com', 'Seller', 'Party', '$2a$10$VwJrcxVf88foqnCN5Z2bTu3QTJA0D3x/PqHsQRIhKxZcSP3AVknmm', 'ROLE_SELLER'),
	('test@user.com', 'test', 'one', '$2a$10$9z.VM2BDqUhu5X5QR3nG6OLDog55hs.jqMvJmARsgWaVl3oAFCUUi', NULL),
	('ujjwaltyagi@gmail.com', 'Ujjwal', 'Tyagi', '$2a$10$7KjWPZ6zAJ9hi5KqTE9EKuic0XeviF2AwPArjIL7XJGyAQWWsAFKW', 'ROLE_ADMIN');

----------------------------------------------------------------------
