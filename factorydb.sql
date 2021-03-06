set FOREIGN_KEY_CHECKS = 0;

drop table if exists bill;
drop table if exists Ingredient;
drop table if exists Cookie;
drop table if exists Recipe;
drop table if exists Pallet;
drop table if exists BillSpec;
drop table if exists Customer;


set FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES=0;

create table Ingredient (
  ingrName varchar(40) not null,
  saldo integer not null CHECK (saldo>0),
  lastDel date,
  delAmount integer CHECK (delAmount>0),

  primary key (ingrName)
  );

create table Cookie (
  cookieName varchar(40) not null,
  primary key(cookieName)
  );

create table Recipe(
  ingrName varchar(40) not null,
  cookieName varchar(40) not null,
  qty integer not null,

  primary key(ingrName,cookieName),
  foreign key(ingrName) references Ingredient(ingrName),
  foreign key(cookieName) references Cookie(cookieName)
  );

create table Customer(
  custName varchar(40) not null,
  custAddr varchar(40) not null,

  primary key(custName,custAddr)
  );

create table Bill(
  billId integer not null,
  delDate date not null,
  custName varchar(40) not null,
  custAddr varchar(40) not null,

  primary key(billId),
  foreign key(custName,custAddr) references Customer(custName,custAddr)
  );

create table Pallet(
  palletNbr integer not null auto_increment,
  cookieName varchar(40) not null,
  bakeDate datetime not null,
  isBlocked char(0) default null,
  location varchar(40) not null,
  billId integer,

  primary key(palletNbr),
  foreign key(cookieName) references Cookie(cookieName),
  foreign key(billId) references Bill(billId)
  );

  create table BillSpec(
    billId integer not null,
    cookieName varchar(40) not null,
    nbrOfPallets integer not null,

    primary key(billId,cookieName),
    foreign key (billId) references Bill(billId),
    foreign key (cookieName) references Cookie(cookieName)
    );



  start transaction;
  -- custName, Address
  insert into Customer values
  ("Finkakor AB","Helsingborg"),
  ("Småbröd AB","Malmö"),
  ("Kaffebröd AB","Landskrona"),
  ("Bjudkakor AB","Ystad"),
  ("Kalaskakor AB","Trelleborg"),
  ("Partykakor AB","Kristianstad"),
  ("Gästkakor AB","Hässleholm"),
  ("Skånekakor AB", "Perstorp");

  -- cookieName
  insert into Cookie values
  ("Nut ring"),
  ("Nut cookie"),
  ("Amneris"),
  ("Tango"),
  ("Almond delight"),
  ("Berliner");

  -- Name, saldo, lastDel,delAmount
  insert into Ingredient values
  ("Flour",10000,null,null),
  ("Butter",10000,null,null),
  ("Icing sugar",10000,null,null),
  ("Roasted, chopped nuts",10000,null,null),
  ("Fine-ground nuts",10000,null,null),
  ("Ground, roasted nuts",10000,null,null),
  ("Bread crumbs",10000,null,null),
  ("Sugar",10000,null,null),
  ("Egg whites",10000,null,null),
  ("Chocolate",10000,null,null),
  ("Marzipan",10000,null,null),
  ("Eggs",10000,null,null),
  ("Potato starch",10000,null,null),
  ("Wheat flour",10000,null,null),
  ("Chopped almonds",10000,null,null),
  ("Sodium bicarbonate",10000,null,null),
  ("Vanilla",10000,null,null),
  ("Cinnamon",10000,null,null),
  ("Vanilla sugar",10000,null,null);

  -- ingrName,cookieName, qty (dl is written as cl)
  insert into Recipe values
  ("Flour", "Nut ring", 450),
  ("Butter", "Nut ring", 450),
  ("Icing sugar", "Nut ring", 190),
  ("Roasted, chopped nuts", "Nut ring", 225),

  ("Fine-ground nuts", "Nut cookie", 750),
  ("Ground, roasted nuts", "Nut cookie", 625),
  ("Bread crumbs", "Nut cookie", 125),
  ("Sugar", "Nut cookie", 375),
  ("Egg whites", "Nut cookie", 35),
  ("Chocolate", "Nut cookie", 50),

  ("Marzipan", "Amneris", 750),
  ("Butter", "Amneris", 250),
  ("Eggs", "Amneris", 250),
  ("Potato starch", "Amneris", 25),
  ("Wheat flour", "Amneris", 25),

  ("Butter", "Tango", 200),
  ("Sugar", "Tango", 250),
  ("Flour", "Tango", 300),
  ("Sodium bicarbonate", "Tango", 4),
  ("Vanilla", "Tango", 2),

  ("Butter", "Almond delight", 400),
  ("Sugar", "Almond delight", 270),
  ("Chopped almonds", "Almond delight", 279),
  ("Flour", "Almond delight", 400),
  ("Cinnamon", "Almond delight", 10),

  ("Flour","Berliner", 350),
  ("Butter","Berliner", 250),
  ("Icing sugar", "Berliner", 100),
  ("Eggs", "Berliner", 50),
  ("Vanilla sugar", "Berliner", 5),
  ("Chocolate", "Berliner", 50);

-- billId, delDate, isDel, custName, custAddr
  insert into Bill values
    (1, "2016-03-24", "Bjudkakor AB", "Ystad"),
    (2, "2016-02-29", "Finkakor AB", "Helsingborg"),
    (3, "2016-03-04", "Gästkakor AB", "Hässleholm");

-- billId, cookieName, nbrOfPallets
  insert into BillSpec values
    (1, "Berliner", 2),
    (2, "Tango", 1),
    (3, "Amneris", 1);

-- palletNbr, cookieName, billId, bakeDate, isBlocked, location
  insert into Pallet values
    (1, "Berliner", "2016-03-22 14:23:11", null, "Warehouse", 1),
    (2, "Berliner", "2016-03-21 14:23:11", null, "Warehouse", 1),
    (3, "Tango", "2016-03-19 14:23:11", null, "Warehouse", 2),
    (4, "Amneris", "2016-03-18 14:23:11", null, "Warehouse", 3);
    
  commit;
