
INSERT INTO hr.organizations
(id, name)
VALUES(1, 'DT추진팀');

INSERT INTO hr.employees
(employee_number, email, name, password, `role`, total_annual_leave, used_annual_leave, org_id)
VALUES('A024015', 'park_jy@lotte.net', '박준영', '1234','ADMIN', 0, 0, 1);