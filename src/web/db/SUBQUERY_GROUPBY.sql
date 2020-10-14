create table emp2 as (select * from emp); -- table copy

-- transaction : 개념. commit, rollback (서술형 나올 가능성 높음!!)
-- dml : insert, delete, update

-- Sub Query
-- * Sub Query의 위치에 따라
-- 1. 일반 SubQuery : select 절
-- 2. Inline View : from 절
-- 3. Nested Query : where 절

-- 최고 연봉을 받는 직원의 정보
select max(salary)
from emp2;
            
-- 답 : 51 / 1 / 2 / 20 / 4 / 34
-- 1. 'Seattle'에 있는 부서 정보
select deptid, deptname
from dept
where locid = (select locid
		from loc
		where city = 'Seattle');
        
-- 2. 전체 평균 연봉보다 많이 받는 직원 정보
select *
from emp2
where salary > (select avg(salary)
				from emp2);
                
-- 3. 최고 연봉을 받는 직원의 정보(empid, fname, jobtitle)
select e.empid, e.fname, j.jobtitle
from emp e
join job j
using(jobid)
where salary = (select max(salary)
				from emp
				);

-- 4. jobid가 'IT_PROG'인 직원의 최소 연봉과 같은 금액을 받는 직원 정보
select *
from emp e
where salary = (
		select min(salary)
        from emp
        where jobid = 'IT_PROG'
		);

-- 5. 20번 부서 직원들의 평균 연봉보다 많이 받는 직원 정보
select *
from emp 
where salary > (
		select avg(salary)
        from emp
        where deptid = 20
        );
        
-- 6. 'Bruce'와 동일한 부서에 속한 직원정보(empid, fname, hdate) : 'Bruce' 자신은 제외
select empid, fname, hdate
from emp
where deptid = (
		select deptid
        from emp
        where fname = 'Bruce'
        ) and fname != 'Bruce';
        
-- 7. 전체 평균 연봉보다 많이 받고 이름에 'T'가 들어가는 직원과 동일한 부서에 근무하는 모든 직원 정보
select *
from emp
where deptid = (
		select deptid
        from emp
        where fname like '%T%'
        and
        salary > (
			select avg(salary)
			from emp
		)
    );

select *
from emp
where salary > (select avg(salary) from emp)
and deptid in (select distinct deptid from emp where fname like "%T%"); -- 이름에 T가 들어가는 사람이 여러명일 수 있으므로 'in'을 써야 함

-- 8. 미국 내 근무하는 사원들의 평균 월급보다 많이 받는 사원 정보
select empid, fname, jobtitle
from emp e, job j
where salary > (
				select avg(salary)
                from emp
                where deptid in (
								select deptid
								from dept
								where locid in (
												select locid
												from loc
												where countryid = 'US'
												)
								)
				)
and e.jobid = j.jobid;
-- 미국에 위치한 부서구할 때, join은 테이블을 키우니까 더 느릴 수 
-- where절 순서를 적을 때 고려할 것 : 가장 많이 걸러낼 수 있는 조건을 먼저

-- 부서별 인원수
select deptid, count(*)
from emp -- 1
where deptid is not null
group by deptid -- 2
order by 1;

-- 부서별 연봉 평균 (연봉 높은 순), deptid가 널인 레코드 제외
select deptid 부서, round(avg(salary)) 연봉평균
from emp
where deptid is not null
group by deptid
order by 연봉평균 desc;

-- 부서별 업무 (deptid, jobid) 10 IT_PROG
-- select 절에 나오는 컬럼명은 집계함수를 제외하고 모두 group by절에 같이 나와야 함
-- sum, count, min ...
-- 집계 함수는 group by 절에 사용 불가
select deptid, jobid, avg(salary)
from emp
where deptid is not null
group by deptid, jobid
order by deptid;

-- deptid가 50이상인 부서 중 jobid별로 평균 임금이 8000 이상인 레코드의 jobid, 평균임금
-- group by 사용시 : group에 조건을 걸때는 having을 사용해야 함
select jobid, avg(salary) average
from emp
where deptid >= 50
group by jobid
-- having avg(salary) >= 8000
having average >= 8000 -- 나눈 그룹에 대해서 조건을 붙임
order by average desc;

-- 부서별 인원수가 5명 이상
select deptid, count(*) cnt
from emp
group by deptid
having cnt >= 5
order by cnt desc, deptid; -- cnt로 desc 정렬, 같다면 deptid로 asc 정렬





