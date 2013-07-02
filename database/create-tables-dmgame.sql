create table game (
	id int not null primary key,
	name text not null
);

create table review (
	id int not null primary key,
	id_game int not null,
	description text not null,
	url text not null,
	grade  real,
	foreign key (id_game) references game(id)
);