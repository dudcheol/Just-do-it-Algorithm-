use ssafydb;

-- 1) 30번 부서에 속한 사원의 이름과 부서번호 그리고 부서이름을 출력하라.
select e.first_name, e.department_id, d.department_name
from employees e, departments d
where e.department_id = d.department_id and d.department_id = 30;

-- 2) 30번 부서에 속한 사원들의 모든 직업과 부서위치를 출력하라. (단, 직업 목록이 중복되지 않게 하라.)
select distinct job_title, l.city
from employees e
join departments d
using(department_id)
join locations l
using(location_id)
join jobs j
using(job_id)
where department_id = 30;

-- 3) 커미션이 책정되어 있는 모든 사원의 이름, 부서이름 및 위치를 출력하라.
select e.first_name, d.department_name, l.city
from employees e
join departments d
using(department_id)
join locations l
using(location_id)
where e.commission_pct is not null;

-- 4) 이름에 A가 들어가는 모든 사원의 이름과 부서 이름을 출력하라.
select e.first_name, d.department_name
from employees e
join departments d
using(department_id)
where first_name like binary '%A%';

-- 5) Dallas에서 근무하는 모든 사원의 이름, 직업, 부서번호 및 부서이름을 출력하라.
select e.first_name, j.job_title, d.department_id, d.department_name
from employees e
join departments d
using(department_id)
join locations l
using(location_id)
join jobs j
using(job_id)
where city = 'Dallas';

-- 6) 사원이름 및 사원번호, 해당 관리자이름 및 관리자 번호를 출력하되, 각 컬럼명을 employee,emp#,manager,mgr#으로 표시하여 출력하라.
select e.first_name employee, e.employee_id "emp#", m.first_name manager, m.employee_id "mgr#"
from employees e
join employees m
on(e.manager_id = m.employee_id);