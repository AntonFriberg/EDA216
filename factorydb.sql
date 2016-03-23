set FOREIGN_KEY_CHECKS = 0;

drop table if exists Name;


set FOREIGN_KEY_CHECKS = 1;

create table Ingredient {
  ingrName varchar(40) not null,
  saldo integer not null CHECK (saldo>0),
  lastDel date not null,
  delAmount integer not null CHECK (delAmount>0),

  primary key (ingrName)
  };

create table cookie {
  cookieName varchar(40) not null,
  primary key(cookieName)
  };

create table Recipie{
  ingrName varchar(40) not null,
  cookieName varchar(40) not null,
  qty integer not null,

  primary key(ingrName,cookieName),
  foreign key(ingrName) references Ingredient(ingrName),
  foreign key(cookieName) references cookie(cookieName)
  };

create table Customer{
  custName varchar(40) not null,
  custAddr varchar(40) not null,

  primary key(custName,custAddr)
  };

create table Bill{
  billId varchar(40) not null,
  delDate date not null,
  isDel char(0) default null,
  custName varchar(40) not null,
  custAddr varchar(40) not null,

  primary key(billId),
  foreign key(custName,custAddr) references Customer(custName,custAddr)
  };

create table Pallet{
  palletNbr integer not null,
  cookieName varchar(40) not null,
  bakeDate date not null,
  isBlocked char(0) default null,
  location varchar(40) not null,
  billId integer not null,

  primary key(palletNbr),
  foreign key(cookieName) references Cookie(cookieName),
  foreign key(billId) references Bill(billId)
  };

  create table BillSpec{
    billId varchar(40) not null,
    cookieName varchar(40) not null,
    nbrOfPallets integer not null,

    primary key(billId,cookieName),
    foreign key (billId) references Bill(billId),
    foreign key (cookieName) references Cookie(cookieName)
    };



  start transaction;

  insert into TABLE values

  (Attr1,attr2),
  (Attr3,Attr4);

  commit;
