--drop table review;
--drop table game;
--drop table platform;
--drop table gametype;

create table game (
	id int not null primary key,
	"name_game" text not null unique
);

create table review (
	"id" int not null primary key,
	"id_game" int not null,
	"r_url" text unique,
	foreign key (id_game) references game(id)
);

create table platform (
	id int not null primary key,
	platform_name varchar(100) not null
);

create table gametype (
	id int not null primary key,
	gt_name varchar(100) not null
);

alter table review add column producer varchar(100);
alter table review add column year int;
alter table review add column platform_id int references platform(id);
alter table review add column grade_graphic int;
alter table review add column grade_jogability int;
alter table review add column grade_fun int;
alter table review add column grade_sound int;
alter table review add column gametype_id int references gametype(id);

