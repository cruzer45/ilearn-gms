# --------------------------------------------------------
# Host:                         localhost
# Server version:               5.1.53-community
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2011-03-08 09:52:38
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping structure for table ilearn.listusergroups
DROP TABLE IF EXISTS `listusergroups`;
CREATE TABLE IF NOT EXISTS `listusergroups` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `groupName` varchar(45) DEFAULT NULL,
  `levels` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table ilearn.listusergroups: ~0 rows (approximately)
DELETE FROM `listusergroups`;
/*!40000 ALTER TABLE `listusergroups` DISABLE KEYS */;
INSERT INTO `listusergroups` (`id`, `groupName`, `levels`) VALUES
	(1, 'Administration', 'Read Write'),
	(2, 'Administration', 'Read Only'),
	(3, 'Counsellor ', 'Read Only'),
	(4, 'Teachers', 'Read Write'),
	(5, 'Parents', 'Read Only');
/*!40000 ALTER TABLE `listusergroups` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
