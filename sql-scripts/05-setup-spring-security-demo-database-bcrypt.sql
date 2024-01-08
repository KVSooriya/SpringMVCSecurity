USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- The passwords are encrypted using BCrypt
--
-- Password : test123 for all users

INSERT INTO `users`
VALUES
('annu','{bcrypt}$2a$10$sym93csOump4NvIMJ3Jv2eqy7KddM/3Mcl4oQ6Q98v4WAfkTrKUEq',1),
('abi','{bcrypt}$2a$10$sym93csOump4NvIMJ3Jv2eqy7KddM/3Mcl4oQ6Q98v4WAfkTrKUEq',1),
('sooriya','{bcrypt}$2a$10$wd2AKubkcthwwjD6HiKjjOOVkH7p8Qc/vbG1E4d6mNKck/d0CIl32',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES
('abi','ROLE_EMPLOYEE'),
('annu','ROLE_EMPLOYEE'),
('annu','ROLE_MANAGER'),
('sooriya','ROLE_EMPLOYEE'),
('sooriya','ROLE_MANAGER'),
('sooriya','ROLE_ADMIN');