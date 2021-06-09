--Before each startup delete all rows from Employee
--And reset IDENTITY
TRUNCATE TABLE employee;

--Populating the database with dummy values
--Now user can assume Employee database with these values
INSERT INTO employee VALUES
    (1, 'Marry Poppins', 'Manager'),
    (2, 'Gloria Borger', 'Chief Reporter');