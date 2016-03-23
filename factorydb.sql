set FOREIGN_KEY_CHECKS = 0;

drop table if exists Name;


set FOREIGN_KEY_CHECKS = 1;
create table Name {
  Name varchar(40) not null,
  Number integer not null CHECK (Number>0)

  primary key (Name),
  foreign key (Number) references Table(attr)
  };


  start transaction;

  insert into TABLE values

  (Attr1,attr2),
  (Attr3,Attr4);

  commit;
