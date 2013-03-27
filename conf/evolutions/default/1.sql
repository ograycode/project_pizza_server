# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table action_model (
  id                        bigint not null,
  name                      varchar(255),
  config                    varchar(255),
  executed                  boolean,
  uploaded                  timestamp,
  execution                 timestamp,
  constraint pk_action_model primary key (id))
;

create table client (
  id                        bigint not null,
  display_name              varchar(255),
  date_added                timestamp,
  port                      integer,
  ip                        varchar(255),
  uuid                      varchar(255),
  status_ok                 boolean,
  group_id                  bigint,
  last_checked_in           timestamp,
  constraint pk_client primary key (id))
;

create table client_group (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_client_group primary key (id))
;


create table action_model_client_group (
  action_model_id                bigint not null,
  client_group_id                bigint not null,
  constraint pk_action_model_client_group primary key (action_model_id, client_group_id))
;

create table client_group_action_model (
  client_group_id                bigint not null,
  action_model_id                bigint not null,
  constraint pk_client_group_action_model primary key (client_group_id, action_model_id))
;
create sequence action_model_seq;

create sequence client_seq;

create sequence client_group_seq;

alter table client add constraint fk_client_group_1 foreign key (group_id) references client_group (id) on delete restrict on update restrict;
create index ix_client_group_1 on client (group_id);



alter table action_model_client_group add constraint fk_action_model_client_group__01 foreign key (action_model_id) references action_model (id) on delete restrict on update restrict;

alter table action_model_client_group add constraint fk_action_model_client_group__02 foreign key (client_group_id) references client_group (id) on delete restrict on update restrict;

alter table client_group_action_model add constraint fk_client_group_action_model__01 foreign key (client_group_id) references client_group (id) on delete restrict on update restrict;

alter table client_group_action_model add constraint fk_client_group_action_model__02 foreign key (action_model_id) references action_model (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists action_model;

drop table if exists action_model_client_group;

drop table if exists client;

drop table if exists client_group;

drop table if exists client_group_action_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists action_model_seq;

drop sequence if exists client_seq;

drop sequence if exists client_group_seq;

