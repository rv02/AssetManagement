--Populating the database with dummy values
--Now user can assume Employee database with these values
MERGE INTO employee KEY (id) VALUES
    (1, 'Marry Poppins', 'Manager'),
    (2, 'Gloria Borger', ' Chief Reporter');