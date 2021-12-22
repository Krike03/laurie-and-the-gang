alter table division
    add fk_parent_division int;
alter table division
    add constraint fk_division foreign key (fk_parent_division) references division(id);