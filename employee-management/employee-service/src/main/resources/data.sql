-- Ensure the 'employee' table exists
CREATE TABLE IF NOT EXISTS employee
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    address         VARCHAR(255)        NOT NULL,
    date_of_birth   DATE                NOT NULL,
    registered_date DATE                NOT NULL
    );

-- Insert well-known UUIDs for specific employees
INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174000',
       'John Doe',
       'john.doe@example.com',
       '123 Main St, Springfield',
       '1985-06-15',
       '2024-01-10'
    WHERE NOT EXISTS (SELECT 1
                  FROM employee
                  WHERE id = '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174001',
       'Jane Smith',
       'jane.smith@example.com',
       '456 Elm St, Shelbyville',
       '1990-09-23',
       '2023-12-01'
    WHERE NOT EXISTS (SELECT 1
                  FROM employee
                  WHERE id = '123e4567-e89b-12d3-a456-426614174001');

INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174002',
       'Alice Johnson',
       'alice.johnson@example.com',
       '789 Oak St, Capital City',
       '1978-03-12',
       '2022-06-20'
    WHERE NOT EXISTS (SELECT 1
                  FROM employee
                  WHERE id = '123e4567-e89b-12d3-a456-426614174002');

INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174003',
       'Bob Brown',
       'bob.brown@example.com',
       '321 Pine St, Springfield',
       '1982-11-30',
       '2023-05-14'
    WHERE NOT EXISTS (SELECT 1
                  FROM employee
                  WHERE id = '123e4567-e89b-12d3-a456-426614174003');

INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '123e4567-e89b-12d3-a456-426614174004',
       'Emily Davis',
       'emily.davis@example.com',
       '654 Maple St, Shelbyville',
       '1995-02-05',
       '2024-03-01'
    WHERE NOT EXISTS (SELECT 1
                  FROM employee
                  WHERE id = '123e4567-e89b-12d3-a456-426614174004');

-- Insert additional employees
INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174005',
       'Michael Green',
       'michael.green@example.com',
       '987 Cedar St, Springfield',
       '1988-07-25',
       '2024-02-15'
    WHERE NOT EXISTS (SELECT 1 FROM employee WHERE id = '223e4567-e89b-12d3-a456-426614174005');

INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174006',
       'Sarah Taylor',
       'sarah.taylor@example.com',
       '123 Birch St, Shelbyville',
       '1992-04-18',
       '2023-08-25'
    WHERE NOT EXISTS (SELECT 1 FROM employee WHERE id = '223e4567-e89b-12d3-a456-426614174006');

INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174007',
       'David Wilson',
       'david.wilson@example.com',
       '456 Ash St, Capital City',
       '1975-01-11',
       '2022-10-10'
    WHERE NOT EXISTS (SELECT 1 FROM employee WHERE id = '223e4567-e89b-12d3-a456-426614174007');

INSERT INTO employee (id, name, email, address, date_of_birth, registered_date)
SELECT '223e4567-e89b-12d3-a456-426614174008',
       'Laura White',
       'laura.white@example.com',
       '789 Palm St, Springfield',
       '1989-09-02',
       '2024-04-20'
    WHERE NOT EXISTS (SELECT 1 FROM employee WHERE id = '223e4567-e89b-12d3-a456-426614174008');
