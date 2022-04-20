DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `username` varchar(50) NOT NULL,
    `password` char(68)    NOT NULL,
    `enabled`  tinyint(1)  NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users`
VALUES
       ('ashish', '{bcrypt}$2a$10$/XgvkglsCvaY9qEfvVlMteU6TIyIpx/bga2SLuLDFZvSX5FblxiHG', 1),
       ('swati', '{bcrypt}$2a$10$/XgvkglsCvaY9qEfvVlMteU6TIyIpx/bga2SLuLDFZvSX5FblxiHG', 1),
       ('akash', '{bcrypt}$2a$10$/XgvkglsCvaY9qEfvVlMteU6TIyIpx/bga2SLuLDFZvSX5FblxiHG', 1),
       ('piyush', '{bcrypt}$2a$10$/XgvkglsCvaY9qEfvVlMteU6TIyIpx/bga2SLuLDFZvSX5FblxiHG', 1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities`
(
    `username`  varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

--
-- Inserting data for table `authorities`
--
-- Default Password - cse123

INSERT INTO `authorities`
VALUES
       ('ashish','ROLE_MANAGER'),
       ('swati','ROLE_ADMIN'),
       ('piyush','ROLE_OWNER'),
       ('akash', 'ROLE_EMPLOYEE');