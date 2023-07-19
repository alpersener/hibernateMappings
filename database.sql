DROP SCHEMA IF EXISTS `advancedMapping`;

CREATE SCHEMA `advancedMapping`;

use `advancedMapping`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `rector` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `university` (
  `id` int NOT NULL AUTO_INCREMENT,
  `university_name` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,

  `rector_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`rector_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`rector_id`) 
  REFERENCES `rector` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `university_department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(128) DEFAULT NULL,
  `university_id` int DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `TITLE_UNIQUE` (`department_name`),
  
  KEY `FK_INSTRUCTOR_idx` (`university_id`),
  
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`university_id`) 
  REFERENCES `university` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `department_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(256) DEFAULT NULL,
  `course_credit` int DEFAULT NULL,
  `university_department_id` int DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_COURSE_ID_idx` (`university_department_id`),

  CONSTRAINT `FK_COURSE` 
  FOREIGN KEY (`university_department_id`) 
  REFERENCES `university_department` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `university_department_student` (
  `university_department_id` int NOT NULL,
  `student_id` int NOT NULL,
  
  PRIMARY KEY (`university_department_id`,`student_id`),
  
  KEY `FK_STUDENT_idx` (`student_id`),
  
  CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`university_department_id`) 
  REFERENCES `university_department` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) 
  REFERENCES `student` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
