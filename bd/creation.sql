--drop database if exists db_wadzem;
create database db_wadzem;
use db_wadzem;


DROP TABLE IF EXISTS db_wadzem.alert;
DROP TABLE IF EXISTS db_wadzem.shoppinglist;
DROP TABLE IF EXISTS db_wadzem.price;
DROP TABLE IF EXISTS db_wadzem.product;
DROP TABLE IF EXISTS db_wadzem.flyer;
DROP TABLE IF EXISTS db_wadzem.account;
DROP TABLE IF EXISTS db_wadzem.store;
DROP TABLE IF EXISTS db_wadzem.region;
DROP TABLE IF EXISTS db_wadzem.brand;
DROP TABLE IF EXISTS db_wadzem.category;


create table brand(
idMarque INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL
);

create table category(
idCategory INT PRIMARY KEY AUTO_INCREMENT,
name_fr VARCHAR(50) NOT NULL,
name_en VARCHAR(50),
idParent INT,
constraint fk_category_category foreign key (idParent) references category(idCategory)
);

insert into category values(null,"Légume","Vegitable", null);

insert into province values(null,"Québec"),(null, "Ontario");


insert into region values(null,"Montréal", 1),(null,"Montréal", 1),(null,"Québec", 1),(null,"Laval", 1),(null,"Gatineau", 1),(null,"Longueuil", 1),(null,"Sherbrooke", 1),(null,"Montérégie", 1)
,(null,"Saguenay", 1),(null,"Lévis", 1),(null,"Trois-Rivières", 1),(null,"Terrebonne", 1); 

insert into region values(null,"Ottawa", 2),(null,"Toronto", 2),(null,"Hamilton", 2),(null,"Kingston", 2),(null,"London", 2),(null,"Windsor", 2); 
  	   		 	 	

create table account(
idAccount INT PRIMARY KEY AUTO_INCREMENT,
firstName VARCHAR(30),
lastName VARCHAR(30),
email VARCHAR(50) NOT NULL,
accountPassword VARCHAR(70) NOT NULL,
creationDate DATETIME,
province VARCHAR (30),
city VARCHAR(30),
role VARCHAR(10) NOT NULL,
visible BOOLEAN NOT NULL DEFAULT 1,
active BOOLEAN NOT NULL DEFAULT 0,
confirmationCode INT NOT NULL
);

create table product(
idProduct INT PRIMARY KEY AUTO_INCREMENT,
name_fr VARCHAR(50) NOT NULL,
name_en VARCHAR(50),
description_fr VARCHAR(50) NOT NULL,
description_en VARCHAR(50),
product_format VARCHAR(30) NOT NULL,
visible BOOLEAN NOT NULL DEFAULT 1,
idMarque INT,
idCategory INT,
constraint fk_marque_product foreign key (idMarque) references brand(idMarque),
constraint fk_category_product foreign key (idCategory) references category(idCategory)
);

create table alert(
idAlert INT PRIMARY KEY AUTO_INCREMENT,
creationDate DATETIME,
visible BOOLEAN NOT NULL DEFAULT 1,
idProduct INT,
idAccount INT,
constraint fk_product_alert foreign key (idProduct) references product(idProduct),
constraint fk_accountt_alert foreign key (idAccount) references account(idAccount)
);

create table shoppinglist(
idShoppingList INT PRIMARY KEY AUTO_INCREMENT,
creationDate DATETIME,
visible BOOLEAN NOT NULL DEFAULT 1,
idProduct INT,
idAccount INT,
constraint fk_product_shoppinglist foreign key (idProduct) references product(idProduct),
constraint fk_account_shoppinglist foreign key (idAccount) references account(idAccount)
);

create table store(
idStore INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL
);

create table region(
idRegion INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
province VARCHAR(30)
);

create table flyer(
idFlyer INT PRIMARY KEY AUTO_INCREMENT,
startDate DATETIME,
endDate DATETIME,
visible BOOLEAN NOT NULL DEFAULT 1,
flyerPath VARCHAR(50), 
idRegion INT,
idStore INT,
constraint fk_region_flayer foreign key (idRegion) references region(idRegion),
constraint fk_store_flayer foreign key (idStore) references store(idStore)
);

create table price(
idPrice INT PRIMARY KEY AUTO_INCREMENT,
price DECIMAL(10,2),
idProduct INT,
idFlyer INT,
constraint fk_product_price foreign key (idProduct) references product(idProduct),
constraint fk_flyer_price foreign key (idFlyer) references flyer(idFlyer)
);