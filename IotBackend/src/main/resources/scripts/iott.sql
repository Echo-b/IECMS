-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: iott
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alert_info`
--

DROP TABLE IF EXISTS `alert_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert_info` (
  `dno` int NOT NULL AUTO_INCREMENT,
  `temperature` float NOT NULL,
  `sender` varchar(30) NOT NULL,
  `date` datetime NOT NULL,
  `did` int NOT NULL,
  `humidity` float NOT NULL,
  `light` float NOT NULL,
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_info`
--

LOCK TABLES `alert_info` WRITE;
/*!40000 ALTER TABLE `alert_info` DISABLE KEYS */;
INSERT INTO `alert_info` VALUES (1,31.2343,'sensor','2022-08-14 00:19:36',2,0,0),(2,33.0289,'sensor','2022-08-14 00:20:06',2,0,0),(3,35.5381,'air-conditioning','2022-08-14 00:21:06',1,0,0),(4,35.4494,'sensor','2022-08-14 00:21:06',2,0,0),(5,33.0982,'air-conditioning','2022-08-14 00:21:16',1,0,0),(6,37.9454,'fan','2022-08-14 00:21:36',3,0,0),(7,32.9906,'sensor','2022-08-14 00:21:47',2,0,0),(8,38.9557,'fan','2022-08-15 08:15:02',3,0,0),(9,33.0363,'air-conditioning','2022-08-15 08:15:22',1,0,0),(10,38.1381,'air-conditioning','2022-08-15 08:15:32',1,0,0),(11,33.981,'sensor','2022-08-15 08:16:12',2,0,0),(12,30.4539,'fan','2022-08-15 08:16:22',3,0,0),(13,32.8786,'air-conditioning','2022-08-15 08:16:32',1,0,0),(14,31.3029,'sensor','2022-09-30 14:58:11',2,0,0),(15,30.1594,'fan','2022-09-30 15:01:48',3,0,0),(16,30.2197,'air-conditioning','2022-09-30 15:02:18',1,0,0),(17,35.3748,'sensor','2022-09-30 15:02:18',2,0,0),(18,38.9878,'air-conditioning','2022-09-30 15:02:48',1,0,0),(19,32.712,'fan','2022-09-30 15:02:48',3,0,0),(20,35.3054,'fan','2022-09-30 15:02:58',3,0,0),(21,34.8835,'fan','2022-09-30 15:03:28',3,0,0),(22,33.767,'air-conditioning','2022-09-30 15:03:38',1,0,0),(23,36.5719,'air-conditioning','2022-09-30 15:07:39',1,0,0),(24,36.0935,'fan','2022-09-30 15:07:39',3,0,0),(25,33.9455,'air-conditioning','2022-09-30 15:07:49',1,0,0),(26,33.3652,'fan','2022-09-30 15:07:49',3,0,0);
/*!40000 ALTER TABLE `alert_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `average_data`
--

DROP TABLE IF EXISTS `average_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `average_data` (
  `dno` int NOT NULL AUTO_INCREMENT,
  `did` int NOT NULL,
  `temperature` float NOT NULL,
  `date` varchar(50) NOT NULL,
  `humidity` float NOT NULL,
  `light` float NOT NULL,
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `average_data`
--

LOCK TABLES `average_data` WRITE;
/*!40000 ALTER TABLE `average_data` DISABLE KEYS */;
INSERT INTO `average_data` VALUES (1,1,14.2664,'2022-10-01 15:41:58',0,0),(2,2,15.137,'2022-10-01 15:42:08',0,0),(3,1,15.2664,'2022-10-01 15:42:18',0,0),(4,3,15.2664,'2022-10-01 15:42:28',0,0),(5,2,16.137,'2022-10-01 15:42:38',0,0),(6,3,14.2664,'2022-10-01 15:43:14',0,0),(7,1,13.2664,'2022-10-01 15:43:24',0,0),(8,3,16.38,'2022-10-01 15:43:34',0,0),(9,1,13.5664,'2022-10-01 15:43:44',0,0),(10,2,14.137,'2022-10-01 15:43:54',0,0),(11,1,15.4664,'2022-10-01 15:45:25',0,0),(12,3,15.38,'2022-10-01 15:45:35',0,0),(13,1,12.2664,'2022-10-01 15:45:45',0,0);
/*!40000 ALTER TABLE `average_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `command_record`
--

DROP TABLE IF EXISTS `command_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `command_record` (
  `did` int NOT NULL,
  `command` varchar(255) NOT NULL,
  `deviceName` varchar(64) NOT NULL,
  `topic` varchar(64) DEFAULT NULL,
  `date` datetime NOT NULL,
  `operator` varchar(40) DEFAULT NULL,
  `cid` int NOT NULL AUTO_INCREMENT,
  `param` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `command_record`
--

LOCK TABLES `command_record` WRITE;
/*!40000 ALTER TABLE `command_record` DISABLE KEYS */;
INSERT INTO `command_record` VALUES (1,'LEDON','air-condition','/topics/led/sub','2023-10-11 11:01:06','admin',1,NULL),(1,'Add_device','air-condition',NULL,'2023-11-29 09:18:19','admin',3,NULL),(2,'Add_device','sensor',NULL,'2023-11-29 09:18:19','admin',4,NULL),(3,'Add_device','fringe',NULL,'2023-11-29 09:18:19','admin',5,NULL),(4,'Add_device','测试设备4',NULL,'2023-11-29 09:18:19','echo',6,NULL),(5,'Add_device','摄像头',NULL,'2023-11-29 09:18:19','echo',7,NULL),(6,'Add_device','测试设备6',NULL,'2023-11-29 09:18:19','echo',8,NULL),(7,'Add_device','测试设备7',NULL,'2023-11-29 09:18:19','echo',9,NULL),(8,'Add_device','测试8',NULL,'2023-11-29 09:18:19','admin',10,NULL),(9,'Add_device','测试11',NULL,'2023-11-29 09:18:19','admin',11,NULL),(10,'Add_device','测试设备10',NULL,'2023-11-29 09:18:19','echo',12,NULL),(11,'Add_device','测试设备11',NULL,'2023-11-29 09:18:19','admin',13,NULL),(14,'Add_device','sdf',NULL,'2023-12-03 18:02:41','admin',14,NULL),(12,'Delete_device','sdf',NULL,'2023-12-04 12:09:03','admin',15,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:13:57','admin',16,NULL),(12,'Delete_device','demo',NULL,'2023-12-04 12:20:03','admin',17,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:20:29','admin',18,NULL),(12,'Delete_device','demo',NULL,'2023-12-04 12:28:11','admin',19,NULL),(12,'Add_device','sd',NULL,'2023-12-04 12:30:45','admin',20,NULL),(13,'Add_device','sdfdsfd',NULL,'2023-12-04 12:32:26','admin',21,NULL),(13,'Delete_device','sdfdsfd',NULL,'2023-12-04 12:33:14','admin',22,NULL),(12,'Delete_device','sd',NULL,'2023-12-04 12:33:16','admin',23,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:45:49','admin',24,NULL),(12,'Delete_device','demo',NULL,'2023-12-04 12:46:08','admin',25,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:50:46','admin',26,NULL),(13,'Add_device','test',NULL,'2023-12-04 12:53:44','echo',27,NULL),(14,'Add_device','tesss',NULL,'2023-12-04 12:54:38','echo',28,NULL),(14,'Delete_device','tesss',NULL,'2023-12-04 13:06:34','echo',29,NULL),(13,'Delete_device','test',NULL,'2023-12-04 13:15:09','echo',30,NULL),(13,'Add_device','sdfsdf',NULL,'2023-12-04 13:16:12','echo',31,NULL),(14,'Add_device','sdfsd',NULL,'2023-12-04 13:16:36','echo',32,NULL),(15,'Add_device','sdfsddfd',NULL,'2023-12-04 13:16:48','echo',33,NULL),(15,'Delete_device','sdfsddfd',NULL,'2023-12-04 13:17:01','echo',34,NULL),(13,'Delete_device','sdfsdf',NULL,'2023-12-04 13:18:11','echo',35,NULL),(1,'Add_device','air-condition',NULL,'2023-12-04 07:58:24','admin',36,NULL),(2,'Add_device','sensor',NULL,'2023-12-04 07:58:24','admin',37,NULL),(3,'Add_device','fringe',NULL,'2023-12-04 07:58:24','admin',38,NULL),(4,'Add_device','测试设备4',NULL,'2023-12-04 07:58:24','echo',39,NULL),(5,'Add_device','摄像头',NULL,'2023-12-04 07:58:24','echo',40,NULL),(6,'Add_device','测试设备6',NULL,'2023-12-04 07:58:24','echo',41,NULL),(7,'Add_device','测试设备7',NULL,'2023-12-04 07:58:24','echo',42,NULL),(8,'Add_device','测试8',NULL,'2023-12-04 07:58:24','admin',43,NULL),(9,'Add_device','测试11',NULL,'2023-12-04 07:58:24','admin',44,NULL),(10,'Add_device','测试设备10',NULL,'2023-12-04 07:58:24','echo',45,NULL),(11,'Add_device','测试设备11',NULL,'2023-12-04 07:58:24','admin',46,NULL),(12,'Add_device','demo',NULL,'2023-12-04 07:58:24','admin',47,NULL),(14,'Add_device','sdfsd',NULL,'2023-12-04 07:58:24','echo',48,NULL),(15,'Add_device','sensor15',NULL,'2023-12-04 16:04:18','echo',49,NULL),(1,'Add_device','air-condition',NULL,'2023-12-04 13:16:28','admin',50,NULL),(2,'Add_device','sensor',NULL,'2023-12-04 13:16:28','admin',51,NULL),(3,'Add_device','fringe',NULL,'2023-12-04 13:16:28','admin',52,NULL),(4,'Add_device','测试设备4',NULL,'2023-12-04 13:16:28','echo',53,NULL),(5,'Add_device','摄像头',NULL,'2023-12-04 13:16:28','echo',54,NULL),(6,'Add_device','测试设备6',NULL,'2023-12-04 13:16:28','echo',55,NULL),(7,'Add_device','测试设备7',NULL,'2023-12-04 13:16:28','echo',56,NULL),(8,'Add_device','测试8',NULL,'2023-12-04 13:16:28','admin',57,NULL),(9,'Add_device','测试11',NULL,'2023-12-04 13:16:28','admin',58,NULL),(10,'Add_device','测试设备10',NULL,'2023-12-04 13:16:28','echo',59,NULL),(11,'Add_device','测试设备11',NULL,'2023-12-04 13:16:28','admin',60,NULL),(12,'Add_device','demo',NULL,'2023-12-04 13:16:28','admin',61,NULL),(14,'Add_device','sdfsd',NULL,'2023-12-04 13:16:28','echo',62,NULL),(15,'Add_device','sensor15',NULL,'2023-12-04 13:16:28','echo',63,NULL);
/*!40000 ALTER TABLE `command_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `did` int NOT NULL AUTO_INCREMENT,
  `deviceName` varchar(20) NOT NULL,
  `status` varchar(8) NOT NULL,
  `longitude` float NOT NULL,
  `latitude` float NOT NULL,
  `creator` varchar(60) NOT NULL,
  `group_id` int NOT NULL,
  `type` varchar(40) NOT NULL,
  `insert_flag` int NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
drop trigger if exists trigger_Add;
Create Trigger trigger_Add after INSERT on device for each row
begin
    INSERT into command_record(did, command, deviceName, date, operator)
    VALUES(new.did, 'Add_device', new.deviceName, now(), new.creator);
end;
drop trigger if exists trigger_INIT;
Create Trigger trigger_INIT after insert on device for each row
begin
    if new.type = 'sensor' or new.type = 'light' then
        INSERT into threshold(did, temp_max, humi_max, light_max)
        VALUES(new.did, 35, 65, 150);
    end if;
end;
drop trigger if exists trigger_Delete;
Create Trigger trigger_Delete after delete on device for each row
begin
    INSERT into command_record(did, command, deviceName, date, operator)
    VALUES(old.did, 'Delete_device', old.deviceName, now(), old.creator);
end;


--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,'air-condition','on',116.37,39.86,'admin',0,'sensor',1,'2023-12-03 19:07:31'),(2,'sensor','off',121.47,31.23,'admin',0,'sensor',1,'2023-12-01 19:07:35'),(3,'fringe','off',110.21,22.66,'admin',0,'light',1,'2023-12-03 19:07:42'),(4,'测试设备4','off',123,22,'echo',0,'other',1,'2023-11-03 19:07:45'),(5,'摄像头','off',122.44,22.33,'echo',0,'sensor',1,'2023-11-23 19:07:51'),(6,'测试设备6','off',122,23,'echo',0,'light',1,'2023-11-10 19:08:02'),(7,'测试设备7','off',123,23,'echo',0,'sensor',1,'2023-12-04 19:07:31'),(8,'测试8','off',123,21,'admin',0,'other',1,'2023-12-05 19:07:35'),(9,'测试11','off',123,21,'admin',1,'light',1,'2023-12-06 19:07:42'),(10,'测试设备10','off',112,23,'echo',0,'sensor',1,'2023-11-06 19:07:45'),(11,'测试设备11','off',123,24,'admin',0,'other',1,'2023-11-24 19:07:51'),(12,'demo','off',0,0,'admin',0,'sensor',1,'2023-12-04 12:50:46'),(14,'sdfsd','off',0,0,'echo',0,'light',1,'2023-12-04 13:16:36'),(15,'sensor15','off',0,0,'echo',0,'sensor',1,'2023-12-04 16:04:18');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_Add` AFTER INSERT ON `device` FOR EACH ROW begin
    INSERT into command_record(did, command, deviceName, date, operator)
    VALUES(new.did, 'Add_device', new.deviceName, now(), new.creator);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_Delete` AFTER DELETE ON `device` FOR EACH ROW begin
    INSERT into command_record(did, command, deviceName, date, operator)
    VALUES(old.did, 'Delete_device', old.deviceName, now(), old.creator);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `init`
--

DROP TABLE IF EXISTS `init`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `init` (
  `groups` int NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `init`
--

LOCK TABLES `init` WRITE;
/*!40000 ALTER TABLE `init` DISABLE KEYS */;
INSERT INTO `init` VALUES (2,1);
/*!40000 ALTER TABLE `init` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `normal_data`
--

DROP TABLE IF EXISTS `normal_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `normal_data` (
  `dno` int NOT NULL AUTO_INCREMENT,
  `did` int NOT NULL,
  `temperature` float NOT NULL,
  `date` datetime NOT NULL,
  `humidity` float NOT NULL,
  `light` float NOT NULL,
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `normal_data`
--

LOCK TABLES `normal_data` WRITE;
/*!40000 ALTER TABLE `normal_data` DISABLE KEYS */;
INSERT INTO `normal_data` VALUES (1,2,19.5917,'2022-08-15 08:17:02',0,0),(2,1,28.446,'2022-08-14 00:19:46',0,0),(3,2,15.9584,'2022-08-14 00:19:46',0,0),(4,1,25.735,'2022-08-14 00:19:56',0,0),(5,2,27.3471,'2022-08-14 00:19:56',0,0),(6,1,7.75604,'2022-08-14 00:20:06',0,0),(7,3,6.14843,'2022-08-14 00:20:16',0,0),(8,1,25.1659,'2022-08-14 00:20:16',0,0),(9,3,2.63473,'2022-08-14 00:20:26',0,0),(10,2,7.92553,'2022-08-14 00:20:26',0,0),(11,2,24.171,'2022-08-14 00:20:36',0,0),(12,3,14.8567,'2022-08-14 00:20:36',0,0),(13,1,3.21413,'2022-08-14 00:20:46',0,0),(14,3,10.4727,'2022-08-14 00:20:46',0,0),(15,1,14.6942,'2022-08-14 00:20:56',0,0),(16,1,16.4767,'2022-08-14 00:20:56',0,0),(17,2,3.44898,'2022-08-14 00:21:16',0,0),(18,1,15.5972,'2022-08-14 00:21:26',0,0),(19,2,7.85811,'2022-08-14 00:21:26',0,0),(20,1,12.3452,'2022-08-14 00:21:36',0,0),(21,3,17.6541,'2022-08-14 00:21:47',0,0),(22,1,23.688,'2022-08-14 00:21:57',0,0),(23,3,10.3889,'2022-08-15 08:14:32',0,0),(24,1,19.9691,'2022-08-15 08:14:32',0,0),(25,1,16.0708,'2022-08-15 08:14:42',0,0),(26,3,23.4453,'2022-08-15 08:14:42',0,0),(27,1,20.9384,'2022-08-15 08:14:52',0,0),(28,3,16.3433,'2022-08-15 08:14:52',0,0),(29,1,27.8723,'2022-08-15 08:15:02',0,0),(30,2,12.0226,'2022-08-15 08:15:12',0,0),(31,1,28.2427,'2022-08-15 08:15:12',0,0),(32,2,1.42735,'2022-08-15 08:15:22',0,0),(33,2,24.4388,'2022-08-15 08:15:32',0,0),(34,1,5.93442,'2022-08-15 08:15:42',0,0),(35,1,6.49322,'2022-08-15 08:15:42',0,0),(36,3,6.04633,'2022-08-15 08:15:52',0,0),(37,1,19.7319,'2022-08-15 08:15:52',0,0),(38,3,25.8381,'2022-08-15 08:16:02',0,0),(39,2,10.6046,'2022-08-15 08:16:02',0,0),(40,1,24.1836,'2022-08-15 08:16:12',0,0),(41,2,19.1285,'2022-08-15 08:16:22',0,0),(42,1,0.0115113,'2022-08-15 08:16:32',0,0),(43,2,2.25996,'2022-08-15 08:16:42',0,0),(44,3,1.94188,'2022-08-15 08:16:42',0,0),(45,3,22.024,'2022-08-15 08:16:52',0,0),(46,2,12.4273,'2022-08-15 08:16:52',0,0),(47,1,20.1017,'2022-09-30 14:58:01',0,0),(48,1,3.7279,'2022-09-29 14:58:01',0,0),(49,1,0.324549,'2022-09-30 14:58:11',0,0),(50,2,0.979237,'2022-09-30 14:58:21',0,0),(51,2,19.7548,'2022-09-30 14:58:21',0,0),(52,3,21.3295,'2022-09-30 15:00:22',0,0),(53,3,1.92634,'2022-09-30 15:00:32',0,0),(54,2,0.790537,'2022-09-30 15:01:48',0,0),(55,2,23.9299,'2022-09-30 15:01:58',0,0),(56,3,26.188,'2022-09-30 15:01:58',0,0),(57,1,26.0678,'2022-09-30 15:02:08',0,0),(58,2,17.3505,'2022-09-30 15:02:08',0,0),(59,1,25.1627,'2022-09-30 15:02:28',0,0),(60,2,19.7836,'2022-09-30 15:02:28',0,0),(61,3,23.8425,'2022-09-30 15:02:38',0,0),(62,2,12.5631,'2022-09-30 15:02:38',0,0),(63,1,14.53,'2022-09-30 15:02:58',0,0),(64,1,20.4661,'2022-09-30 15:03:08',0,0),(65,3,11.4669,'2022-09-30 15:03:08',0,0),(66,1,5.45041,'2022-09-30 15:03:18',0,0),(67,2,23.6494,'2022-09-30 15:03:18',0,0),(68,1,8.85319,'2022-09-30 15:03:28',0,0);
/*!40000 ALTER TABLE `normal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `threshold`
--

DROP TABLE IF EXISTS `threshold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `threshold` (
  `did` int NOT NULL,
  `temp_max` float NOT NULL,
  `humi_max` float NOT NULL,
  `light_max` float NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `threshold`
--

LOCK TABLES `threshold` WRITE;
/*!40000 ALTER TABLE `threshold` DISABLE KEYS */;
/*!40000 ALTER TABLE `threshold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todolist`
--

DROP TABLE IF EXISTS `todolist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todolist` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `apply` varchar(40) DEFAULT NULL,
  `deviceName` varchar(60) NOT NULL,
  `did` int NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todolist`
--

LOCK TABLES `todolist` WRITE;
/*!40000 ALTER TABLE `todolist` DISABLE KEYS */;
INSERT INTO `todolist` VALUES (14,'2023-12-04 12:50:46','admin','demo',12,3),(17,'2023-12-04 13:16:12','echo','sdfsdf',13,5),(18,'2023-12-04 13:16:36','echo','sdfsd',14,3),(19,'2023-12-04 13:16:48','echo','sdfsddfd',15,5),(20,'2023-12-04 16:04:18','echo','sensor15',15,3);
/*!40000 ALTER TABLE `todolist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `password_hash` varchar(512) NOT NULL,
  `avatar` varchar(100) NOT NULL,
  `roles` varchar(40) NOT NULL,
  `group_id` int DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `uid` varchar(256) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('echo','def823239cd714d6f18cda2a2b9e1369046125716c43d27a1256753ac72164b0','f778738c-e4f8-4870-b634-56703b4acafe.gif','editor',0,NULL,'8154532213648629760'),('admin','def823239cd714d6f18cda2a2b9e1369046125716c43d27a1256753ac72164b0','56e1efca-cd04-4904-820b-da3a679ea9b3.jpg','admin',0,'demo@test','8154532213648629761'),('ztl','def823239cd714d6f18cda2a2b9e1369046125716c43d27a1256753ac72164b0','f778738c-e4f8-4870-b634-56703b4acafe.gif','editor',1,NULL,'8154532213648629762');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker_node`
--

DROP TABLE IF EXISTS `worker_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker_node` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
  `HOST_NAME` varchar(64) NOT NULL COMMENT 'host name',
  `PORT` varchar(64) NOT NULL COMMENT 'port',
  `TYPE` int NOT NULL COMMENT 'node type: CONTAINER(1), ACTUAL(2), FAKE(3)',
  `LAUNCH_DATE` date NOT NULL COMMENT 'launch date',
  `MODIFIED` timestamp NOT NULL COMMENT 'modified time',
  `CREATED` timestamp NOT NULL COMMENT 'created time',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='DB WorkerID Assigner for UID Generator';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker_node`
--

LOCK TABLES `worker_node` WRITE;
/*!40000 ALTER TABLE `worker_node` DISABLE KEYS */;
INSERT INTO `worker_node` VALUES (13,'192.168.7.1','1701001722632-33439',2,'2023-11-26','2023-11-26 12:28:42','2023-11-26 12:28:42'),(14,'192.168.7.1','1701065661883-10464',2,'2023-11-27','2023-11-27 06:14:21','2023-11-27 06:14:21'),(15,'192.168.7.1','1701066687883-99341',2,'2023-11-27','2023-11-27 06:31:27','2023-11-27 06:31:27'),(16,'192.168.7.1','1701066861760-79489',2,'2023-11-27','2023-11-27 06:34:21','2023-11-27 06:34:21'),(17,'192.168.7.1','1701066988392-12395',2,'2023-11-27','2023-11-27 06:36:28','2023-11-27 06:36:28'),(18,'192.168.7.1','1701067327067-49437',2,'2023-11-27','2023-11-27 06:42:07','2023-11-27 06:42:07'),(19,'192.168.7.1','1701067466121-61403',2,'2023-11-27','2023-11-27 06:44:26','2023-11-27 06:44:26'),(20,'192.168.7.1','1701067523747-26520',2,'2023-11-27','2023-11-27 06:45:23','2023-11-27 06:45:23'),(21,'192.168.7.1','1701067627778-41658',2,'2023-11-27','2023-11-27 06:47:07','2023-11-27 06:47:07'),(22,'192.168.7.1','1701067690572-33864',2,'2023-11-27','2023-11-27 06:48:10','2023-11-27 06:48:10'),(23,'192.168.7.1','1701068203440-15605',2,'2023-11-27','2023-11-27 06:56:43','2023-11-27 06:56:43'),(24,'192.168.7.1','1701068563273-83135',2,'2023-11-27','2023-11-27 07:02:43','2023-11-27 07:02:43'),(25,'192.168.7.1','1701068865942-88363',2,'2023-11-27','2023-11-27 07:07:45','2023-11-27 07:07:45'),(26,'192.168.7.1','1701068887356-48503',2,'2023-11-27','2023-11-27 07:08:07','2023-11-27 07:08:07'),(147,'192.168.173.222','1701676850364-62146',2,'2023-12-04','2023-12-04 08:00:50','2023-12-04 08:00:50'),(148,'192.168.173.222','1701677630811-23670',2,'2023-12-04','2023-12-04 08:13:50','2023-12-04 08:13:50'),(149,'192.168.7.1','1701696570615-54083',2,'2023-12-04','2023-12-04 13:29:30','2023-12-04 13:29:30'),(150,'192.168.7.1','1701696628283-86439',2,'2023-12-04','2023-12-04 13:30:28','2023-12-04 13:30:28'),(151,'192.168.7.1','1701696799009-86946',2,'2023-12-04','2023-12-04 13:33:19','2023-12-04 13:33:19'),(152,'192.168.7.1','1701697143140-54956',2,'2023-12-04','2023-12-04 13:39:03','2023-12-04 13:39:03'),(153,'192.168.7.1','1701697355539-72174',2,'2023-12-04','2023-12-04 13:42:35','2023-12-04 13:42:35'),(154,'192.168.7.1','1701697604384-44999',2,'2023-12-04','2023-12-04 13:46:44','2023-12-04 13:46:44'),(155,'192.168.7.1','1701697721457-57890',2,'2023-12-04','2023-12-04 13:48:41','2023-12-04 13:48:41'),(156,'192.168.7.1','1701697882263-21111',2,'2023-12-04','2023-12-04 13:51:22','2023-12-04 13:51:22'),(157,'192.168.7.1','1701697970499-98572',2,'2023-12-04','2023-12-04 13:52:50','2023-12-04 13:52:50'),(158,'192.168.7.1','1701698108822-99354',2,'2023-12-04','2023-12-04 13:55:08','2023-12-04 13:55:08'),(159,'192.168.7.1','1701698218859-45855',2,'2023-12-04','2023-12-04 13:56:58','2023-12-04 13:56:58'),(160,'192.168.7.1','1701699022695-68841',2,'2023-12-04','2023-12-04 14:10:22','2023-12-04 14:10:22');
/*!40000 ALTER TABLE `worker_node` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-04 22:53:27
