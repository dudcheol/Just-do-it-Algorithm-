-- view : 기존 테이블로부터 만들어지는 가상의 테이블
-- 보안상의 이유나 복잡한 쿼리 등을 대신해서 사용됨
-- view에 대해 dml을 실행하면 원래 테이블에 반영이 됨

create or replace view v_emp
as select * from emp
where deptid >= 50;

desc v_emp;
select * from v_emp;


-- empdept : empid, fname, deptid, deptname
create view empdept
as
select e.empid, e.fname, e.deptid, d.deptname
from emp e, dept d
where e.deptid is not null
and e.deptid = d.deptid
order by e.empid;

select * from empdept;

-- empdept: empid, fname, deptid, deptname, jobtitle
create or replace view empdept
as
select e.empid, e.fname, e.deptid, d.deptname, j.jobtitle
from emp e, dept d, job j
where e.deptid is not null
and e.deptid = d.deptid
and e.jobid = j.jobid
order by e.empid;

select * from empdept;

-- empsal: 20,30,50번 부서 직원들의 사번, 이름, 부서이름, 급여를 보여주는 뷰
create view empsal
as
select empid, fname, deptname, salary
from emp join dept using(deptid)
where deptid in (20,30,50);

select * from empsal;

-- inline view(subquery의 from절에서 사용되는 view)
select e.empid, e.fname, k.deptname
from emp e, (select deptid, deptname from dept) k
where e.deptid = k.deptid;


create view emptest
as
select empid, fname, email
from emp;

select * from emptest;

select max(empid) from emp;
insert into emptest values(207, 'tommy', 'tommy@me.com');
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          

