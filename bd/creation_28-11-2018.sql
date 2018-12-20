--drop database if exists db_wadzem;
create database db_wadzem;
use db_wadzem;


DROP TABLE IF EXISTS db_wadzem.alert;
DROP TABLE IF EXISTS db_wadzem.shoppinglist;
DROP TABLE IF EXISTS db_wadzem.price;
DROP TABLE IF EXISTS db_wadzem.product;
DROP TABLE IF EXISTS db_wadzem.flyer;
DROP TABLE IF EXISTS db_wadzem.region;
DROP TABLE IF EXISTS db_wadzem.province;
DROP TABLE IF EXISTS db_wadzem.account;
DROP TABLE IF EXISTS db_wadzem.store;
DROP TABLE IF EXISTS db_wadzem.brand;
DROP TABLE IF EXISTS db_wadzem.category;


create table brand(
idBrand INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
description VARCHAR(100)
);

create table category(
idCategory INT PRIMARY KEY AUTO_INCREMENT,
name_fr VARCHAR(50) NOT NULL,
name_en VARCHAR(50),
idParent INT,
constraint fk_category_category foreign key (idParent) references category(idCategory)
);

create table province(
idProvince INT PRIMARY KEY AUTO_INCREMENT,
province VARCHAR(30)
);

create table region(
idRegion INT PRIMARY KEY AUTO_INCREMENT,
region VARCHAR(30),
idProvince int,
constraint fk_province_region foreign key (idProvince) references province(idProvince)
);

create table account(
idAccount INT PRIMARY KEY AUTO_INCREMENT,
firstName VARCHAR(30),
lastName VARCHAR(30),
email VARCHAR(50) NOT NULL,
accountPassword VARCHAR(70) NOT NULL,
creationDate DATETIME,
idRegion int,
role VARCHAR(15) NOT NULL,
visible BOOLEAN NOT NULL DEFAULT 1,
active BOOLEAN NOT NULL DEFAULT 0,
confirmationCode INT NOT NULL,
constraint fk_region_account foreign key (idRegion) references region(idRegion)
);

create table product(
idProduct INT PRIMARY KEY AUTO_INCREMENT,
name_fr VARCHAR(50) NOT NULL,
name_en VARCHAR(50),
description_fr VARCHAR(50) NOT NULL,
description_en VARCHAR(50),
product_format VARCHAR(30) NOT NULL,
image longblob,
visible BOOLEAN NOT NULL DEFAULT 1,
idBrand INT,
idCategory INT,
constraint fk_brand_product foreign key (idBrand) references brand(idBrand),
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
name VARCHAR(30) NOT NULL,
image longblob
);

create table flyer(
idFlyer INT PRIMARY KEY AUTO_INCREMENT,
startDate DATETIME,
endDate DATETIME,
visible BOOLEAN NOT NULL DEFAULT 1,
flyerFile longblob, 
idRegion INT,
idStore INT,
constraint fk_region_flayer foreign key (idRegion) references region(idRegion),
constraint fk_store_flayer foreign key (idStore) references store(idStore)
);

insert into flyer values(null,"11/11/2018", "10/10/2018",1, null, 14,8); 

create table price(
idPrice INT PRIMARY KEY AUTO_INCREMENT,
price DECIMAL(10,2),
idProduct INT,
idFlyer INT,
constraint fk_product_price foreign key (idProduct) references product(idProduct),
constraint fk_flyer_price foreign key (idFlyer) references flyer(idFlyer)
);