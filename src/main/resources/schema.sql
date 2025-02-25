CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    total_annual_leave INT DEFAULT 15,
    used_annual_leave INT DEFAULT 0
);

CREATE TABLE annual_leave (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    leave_date DATE NOT NULL,
    reason VARCHAR(255),
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);
