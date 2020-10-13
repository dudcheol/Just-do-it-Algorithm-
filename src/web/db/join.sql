use scott;
-- JOIN
-- 1) inner join(natural join): 공통 컬럼에 의한 결합을 사용empemp
-- empid, fname, deptname
select e.empid 사번, e.fname 이름, d.deptname 부서
from emp e, dept d
where e.deptid = d.deptid -- 두 테이블을 연결해서 기다란 레코드를 생성
order by 1; -- select 할 때의 index(1부터 시작)를 기준으로 정렬, alias(별칭)도 사용 가능

-- 사번, 이름, 업무
select e.empid 사번, e.fname 이름, j.JOBTITLE 업무
from emp e, job j
where e.jobid = j.jobid
order by 사번;

-- ansi(join~using)
select e.empid 사번, e.fname 이름, d.deptname 부서
from emp e join dept d -- join이라고만 쓰면 자동으로 inner join이 적용됨
using(deptid)
order by 사번;

-- ansi(join~on)
select e.empid 사번, e.fname 이름, d.deptname 부서
from emp e join dept d -- join이라고만 쓰면 자동으로 inner join이 적용됨
on(e.deptid = d.deptid)
order by 사번;

-- empid, fname, deptname, jobtittle
select e.empid, e.fname, d.deptname, j.jobtitle
from emp e, dept d, job j
where e.deptid = d.deptid and e.jobid = j.jobid
order by 1;

-- join on
select e.empid, e.fname, d.deptname, j.jobtitle
from emp e
join dept d
on(e.deptid = d.deptid)
join job j
on(e.jobid = j.jobid)
order by 1;

-- 2) self join(동일한 테이블을 여러번 접근)
-- empid, fname, mgrname
select e.empid 사번, e.fname 사원이름, m.fname "사원의 매니저"
from emp e, emp m
where e.mgrid = m.empid;

-- 자신의 매니저 연봉이 15000 이상인 직원의 사번, 이름, 매니저 이름, 매니저 연봉
select e.empid 사번, e.fname 이름, m.fname "매니저 이름", m.salary "매니저 연봉"
from emp e, emp m
where e.mgrid = m.empid and m.salary >= 15000
order by 4 desc;

-- 사원과 부서 join시 부서가 없는 직원은 제외됨 -> 제외하고 싶지 않다? outer join!
select e.empid 사번, e.fname 이름, d.deptname 부서 -- 106건
from emp e, dept d
where e.deptid = d.deptid
order by 1;

select count(*) -- 107건
from emp;

-- 3) outer join(null값을 가진 레코드가 누락되는 것을 막기 위한 방법)
select e.empid 사번, e.fname 이름, d.deptname 부서 
from emp e left outer join dept d -- data가 많은 쪽(null을 가진 쪽)을 기준으로! 모자란 쪽은 비어있는 레코드가 추가됨.
on (e.deptid = d.deptid) -- e는 deptid가 fk여서 null이 될 수 있기 때문에 d보다 데이터가 더 많다고 할 수 있다
order by 1;

-- empid, fname, deptname, city
select e.empid, e.fname, d.deptname, l.city
from emp e left outer join dept d
on (e.deptid = d.deptid)
left outer join loc l
on (d.locid = l.locid)
order by 1;



