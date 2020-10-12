use scott;
-- 함수
-- 1. 집계함수(count, min, max, avg, sum)
select count(empid) 직원수, count(deptid) 부서수
from emp;

select count(*)
from emp;

select max(salary) 최고연봉, min(salary) 최저연봉, round(avg(salary),2) 평균연봉, sum(salary) 연봉합계
from emp;

-- 2. 숫자관련 함수
select ceil(8.9) from dual; -- 올림(천장)
select floor(2.8); -- 내림(바닥)
select round(3.356, 2); -- 반올림
select greatest(10,20,50);

-- 3. 문자관련 함수
select empid, fname, lname, concat(fname, ' ', lname) 이름 -- 중간에 쓰는 글은 사이에 들어가는 글자가 됨
from emp;

-- Steven King은 AD_PRES 입니다.
select concat(fname, ' ', lname, '은 ', jobid, ' 입니다.') 설명
from emp;

select fname, left(fname, 2), right(fname, 2)
from emp;

select fname, substring(fname, 1, 2)
from emp;


-- 4. 날짜 관련 함수
select now(), sysdate(), curdate(), curtime();
select now(), date_add(now(), interval 1 second);
select year(now()), month(now()), monthname(now()), dayofweek(now());

-- 5. 논리함수(if, null check, case-when-then)
-- if
select if(100>200, 'true', 'false');
select empid, fname, if(comm is null, 'nope', comm)
from emp;

-- 사번, 이름, 부서가 null이면 'yet', 아니면 자기부서 출력
select empid, fname, if(deptid is null, 'yet', deptid) 부서
from emp;

-- 사번, 이름, 입사일, 입사일이 '2005-01-01' 이전이면 'senior', 아니면 'junior'
select empid, fname, hdate, if(hdate < '2005-01-01', 'senior', 'junior') "경력 등급"
from emp;

-- 사번, 이름, 연봉, 연봉이 9000 이상이면 'high', 아니면 'low'
select empid, fname, salary, if(salary >= 9000, 'high', 'low')
from emp;

-- ifnull(val1, val2) : val1이 null이면 val2, 아니면 val1 // val1이 null이냐? 그럼 val2~
select empid, fname, ifnull(comm, 'no comm')
from emp;

-- nullif(val1, val2) : val1과 val2가 같으면 null, 아니면 val1
select nullif(100, 100), nullif(100, 200);

select fname, comm, nullif(comm, 0.1)
from emp -- 1
where comm is not null; -- 2

-- case~when
select empid, fname,
	case jobid -- jobid를 기준으로
		when 'IT_PROG' then 'class-1' -- 중간에 ',' 없음
		when 'AD_PRES' then 'class-2'
		when 'AD_VP'   then 'class-3'
		else '모름'
	end 등급
from emp;

-- dml(select, update, insert, delete) - CRUD
desc emp;
insert into emp(empid, fname, jobid, hdate, deptid)
values(208, 'tommy', 'IT_PROG', sysdate(), 30);

select *
from emp
where empid = 208;

select count(*)
from emp;

update emp
set salary = 12000
where empid = 208;

delete from emp
where empid = 208;

