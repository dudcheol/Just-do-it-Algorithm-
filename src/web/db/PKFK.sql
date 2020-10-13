-- emp, dept, job, loc
-- pk 지정
alter table emp
add constraint emp_pk primary key(empid);

alter table dept
add constraint dept_pk primary key(deptid);

alter table job
add constraint job_pk primary key(jobid);

alter table loc
add constraint loc_pk primary key(locid);

-- fk 지정(null 허용됨)
alter table emp
add constraint emp_fk_dept foreign key(deptid)
references dept(deptid);

alter table emp
add constraint emp_fk_job foreign key(jobid)
references job(jobid);

alter table emp
add constraint emp_fk_mgr foreign key(mgrid)
references emp(empid);

alter table dept
add constraint dept_fk_loc foreign key(locid)
references loc(deptid);

alter table dept
add constraint dept_fk_mgr foreign key(mgrid)
references emp(empid);

-- 부모테이블: 참조되는 테이블(dept), 자식테이블: 참조하는 테이블(emp)
-- data insert: 부모 테이블에 먼저 insert
-- data delete: 자식 테이블 먼저 delete



