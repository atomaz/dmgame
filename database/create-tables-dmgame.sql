drop table platform;
create table platform (
	id int not null primary key,
	name text not null
);

drop table game;
create table game (
	id int not null primary key,
	name text not null
);

create table review (
	id int not null primary key,
	id_game int not null,
	id_platform int,
	description text not null,
	url text not null,
	grade  real,
	foreign key (id_game) references game(id),
	foreign key (id_platform) references platform(id) 
);