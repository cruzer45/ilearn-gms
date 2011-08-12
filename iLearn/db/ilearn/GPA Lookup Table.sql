# --------------------------------------------------------
# Host:                         localhost
# Server version:               5.1.53-community
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2011-08-12 09:14:39
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping structure for table ilearn.gpa_lookup
CREATE TABLE IF NOT EXISTS `gpa_lookup` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `gradeMin` int(11) DEFAULT NULL,
  `gradeNext` int(11) DEFAULT NULL,
  `gradeLetter` varchar(45) DEFAULT NULL,
  `gpa` double DEFAULT NULL,
  `remark` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table ilearn.gpa_lookup: ~0 rows (approximately)
DELETE FROM `gpa_lookup`;
/*!40000 ALTER TABLE `gpa_lookup` DISABLE KEYS */;
INSERT INTO `gpa_lookup` (`id`, `gradeMin`, `gradeNext`, `gradeLetter`, `gpa`, `remark`) VALUES
	(1, 95, 200, 'A+', 4, 'Excellence'),
	(2, 90, 95, 'A', 3.75, 'Excellence'),
	(3, 85, 90, 'B+', 3.5, 'Exceeds Standards'),
	(4, 80, 85, 'B', 3, 'Exceeds Standards'),
	(5, 75, 80, 'C+', 2.5, 'Meeting Standards'),
	(6, 70, 75, 'C', 2, 'Meeting Standards'),
	(7, 65, 70, 'D+', 1.5, 'Passing'),
	(8, 60, 65, 'D', 1, 'Passing'),
	(9, 0, 60, 'F', 0, 'Failure- No Credit');
/*!40000 ALTER TABLE `gpa_lookup` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
