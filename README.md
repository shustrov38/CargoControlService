# CargoControlService

## kafka topics

- `EmulationData` pipeline between `SensorRequestManager` and `Postgre`
- `EmulationData-ext` saving duplicate messages 

## postgresql table requirements

```postgresql
-- create sequence hibernate_sequence start 1;


drop table if exists sensor_values;
drop table if exists container_state;
drop table if exists container;
drop table if exists emulation_data;


create table emulation_data (
    id bigint not null primary key
);


create table container (
    emulation_data_id bigint       not null references emulation_data (id),
    id                bigint       not null primary key,
    name              varchar(255) not null
);


create table container_state (
    container_id bigint    not null references container (id),
    id           bigint    not null primary key,
    time         timestamp not null
);


create table sensor_values (
    container_state_id bigint       not null references container_state (id),
    name               varchar(255) not null,
    value              decimal      not null
);
```

## requests

- POST `localhost:8080/emulate` - emulate data and store in db
- GET `localhost:8080/emulate/view-data` - get all stored data from db