drop table review;
drop table game;
create table game (
	id int not null primary key,
	"name_game" text not null unique
);

create table review (
	"id" int not null primary key,
	"id_game" int not null,
	"r_description" text,
	"r_url" text,
	"r_grade"  real,
	foreign key (id_game) references game(id)
);