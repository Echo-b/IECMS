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
INSERT INTO `alert_info` VALUES (1,31.23,'sensor','2023-08-14 00:19:36',2,0,0),(2,33.09,'sensor','2023-08-14 00:20:06',2,0,0),(3,35.51,'air-conditioning','2023-08-14 00:21:06',1,0,0),(4,35.44,'sensor','2023-08-14 00:21:06',2,0,0),(5,33.82,'air-conditioning','2023-08-14 00:21:16',1,0,0),(6,37.94,'fan','2023-08-14 00:21:36',3,0,0),(7,32.96,'sensor','2023-08-14 00:21:47',2,0,0),(8,38.95,'fan','2023-08-15 08:15:02',3,0,0),(9,33.03,'air-conditioning','2023-08-15 08:15:22',1,0,0),(10,38.11,'air-conditioning','2023-08-15 08:15:32',1,0,0),(11,33.98,'sensor','2023-08-15 08:16:12',2,0,0),(12,30.39,'fan','2023-08-15 08:16:22',3,0,0),(13,32.86,'air-conditioning','2023-08-15 08:16:32',1,0,0),(14,31.3,'sensor','2023-09-30 14:58:11',2,0,0),(15,30.14,'fan','2023-09-30 15:01:48',3,0,0),(16,30.97,'air-conditioning','2023-09-30 15:02:18',1,0,0),(17,35.38,'sensor','2023-09-30 15:02:18',2,0,0),(18,38.98,'air-conditioning','2023-09-30 15:02:48',1,0,0),(19,32.72,'fan','2023-09-30 15:02:48',3,0,0),(20,35.34,'fan','2023-09-30 15:02:58',3,0,0),(21,34.88,'fan','2023-09-30 15:03:28',3,0,0),(22,33.67,'air-conditioning','2023-09-30 15:03:38',1,0,0),(23,36.59,'air-conditioning','2023-09-30 15:07:39',1,0,0),(24,36.09,'fan','2023-09-30 15:07:39',3,0,0),(25,33.94,'air-conditioning','2023-09-30 15:07:49',1,0,0),(26,33.32,'fan','2023-09-30 15:07:49',3,0,0);
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
INSERT INTO `average_data` VALUES (1,1,14.2664,'2023-10-01 15:41:58',40,120),(2,2,15.137,'2023-10-02 15:42:08',65,150),(3,1,15.2664,'2023-10-03 15:42:18',50,200),(4,3,15.2664,'2023-10-04 15:42:28',56,400),(5,2,16.137,'2023-10-05 15:42:38',45,450),(6,3,14.2664,'2023-10-06 15:43:14',55,600),(7,1,13.2664,'2023-10-07 15:43:24',45,250),(8,3,16.38,'2023-10-08 15:43:34',57,300),(9,1,13.5664,'2023-10-09 15:43:44',66,100),(10,2,14.137,'2023-10-10 15:43:54',48,340),(11,1,15.4664,'2023-10-11 15:45:25',70,220),(12,3,15.38,'2023-10-12 15:45:35',76,160),(13,1,12.2664,'2023-10-13 15:45:45',66,450);
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
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `command_record`
--

LOCK TABLES `command_record` WRITE;
/*!40000 ALTER TABLE `command_record` DISABLE KEYS */;
INSERT INTO `command_record` VALUES (1,'LEDON','air-condition','/topics/led/sub','2023-10-11 11:01:06','admin',1,NULL),(1,'Add_device','air-condition',NULL,'2023-11-29 09:18:19','admin',3,NULL),(2,'Add_device','sensor',NULL,'2023-11-29 09:18:19','admin',4,NULL),(3,'Add_device','fringe',NULL,'2023-11-29 09:18:19','admin',5,NULL),(4,'Add_device','测试设备4',NULL,'2023-11-29 09:18:19','echo',6,NULL),(5,'Add_device','摄像头',NULL,'2023-11-29 09:18:19','echo',7,NULL),(6,'Add_device','测试设备6',NULL,'2023-11-29 09:18:19','echo',8,NULL),(7,'Add_device','测试设备7',NULL,'2023-11-29 09:18:19','echo',9,NULL),(8,'Add_device','测试8',NULL,'2023-11-29 09:18:19','admin',10,NULL),(9,'Add_device','测试11',NULL,'2023-11-29 09:18:19','admin',11,NULL),(10,'Add_device','测试设备10',NULL,'2023-11-29 09:18:19','echo',12,NULL),(11,'Add_device','测试设备11',NULL,'2023-11-29 09:18:19','admin',13,NULL),(14,'Add_device','sdf',NULL,'2023-12-03 18:02:41','admin',14,NULL),(12,'Delete_device','sdf',NULL,'2023-12-04 12:09:03','admin',15,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:13:57','admin',16,NULL),(12,'Delete_device','demo',NULL,'2023-12-04 12:20:03','admin',17,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:20:29','admin',18,NULL),(12,'Delete_device','demo',NULL,'2023-12-04 12:28:11','admin',19,NULL),(12,'Add_device','sd',NULL,'2023-12-04 12:30:45','admin',20,NULL),(13,'Add_device','sdfdsfd',NULL,'2023-12-04 12:32:26','admin',21,NULL),(13,'Delete_device','sdfdsfd',NULL,'2023-12-04 12:33:14','admin',22,NULL),(12,'Delete_device','sd',NULL,'2023-12-04 12:33:16','admin',23,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:45:49','admin',24,NULL),(12,'Delete_device','demo',NULL,'2023-12-04 12:46:08','admin',25,NULL),(12,'Add_device','demo',NULL,'2023-12-04 12:50:46','admin',26,NULL),(13,'Add_device','test',NULL,'2023-12-04 12:53:44','echo',27,NULL),(14,'Add_device','tesss',NULL,'2023-12-04 12:54:38','echo',28,NULL),(14,'Delete_device','tesss',NULL,'2023-12-04 13:06:34','echo',29,NULL),(13,'Delete_device','test',NULL,'2023-12-04 13:15:09','echo',30,NULL),(13,'Add_device','sdfsdf',NULL,'2023-12-04 13:16:12','echo',31,NULL),(14,'Add_device','sdfsd',NULL,'2023-12-04 13:16:36','echo',32,NULL),(15,'Add_device','sdfsddfd',NULL,'2023-12-04 13:16:48','echo',33,NULL),(15,'Delete_device','sdfsddfd',NULL,'2023-12-04 13:17:01','echo',34,NULL),(13,'Delete_device','sdfsdf',NULL,'2023-12-04 13:18:11','echo',35,NULL),(1,'Add_device','air-condition',NULL,'2023-12-04 07:58:24','admin',36,NULL),(2,'Add_device','sensor',NULL,'2023-12-04 07:58:24','admin',37,NULL),(3,'Add_device','fringe',NULL,'2023-12-04 07:58:24','admin',38,NULL),(4,'Add_device','测试设备4',NULL,'2023-12-04 07:58:24','echo',39,NULL),(5,'Add_device','摄像头',NULL,'2023-12-04 07:58:24','echo',40,NULL),(6,'Add_device','测试设备6',NULL,'2023-12-04 07:58:24','echo',41,NULL),(7,'Add_device','测试设备7',NULL,'2023-12-04 07:58:24','echo',42,NULL),(8,'Add_device','测试8',NULL,'2023-12-04 07:58:24','admin',43,NULL),(9,'Add_device','测试11',NULL,'2023-12-04 07:58:24','admin',44,NULL),(10,'Add_device','测试设备10',NULL,'2023-12-04 07:58:24','echo',45,NULL),(11,'Add_device','测试设备11',NULL,'2023-12-04 07:58:24','admin',46,NULL),(12,'Add_device','demo',NULL,'2023-12-04 07:58:24','admin',47,NULL),(14,'Add_device','sdfsd',NULL,'2023-12-04 07:58:24','echo',48,NULL),(15,'Add_device','sensor15',NULL,'2023-12-04 16:04:18','echo',49,NULL),(1,'Add_device','air-condition',NULL,'2023-12-04 13:16:28','admin',50,NULL),(2,'Add_device','sensor',NULL,'2023-12-04 13:16:28','admin',51,NULL),(3,'Add_device','fringe',NULL,'2023-12-04 13:16:28','admin',52,NULL),(4,'Add_device','测试设备4',NULL,'2023-12-04 13:16:28','echo',53,NULL),(5,'Add_device','摄像头',NULL,'2023-12-04 13:16:28','echo',54,NULL),(6,'Add_device','测试设备6',NULL,'2023-12-04 13:16:28','echo',55,NULL),(7,'Add_device','测试设备7',NULL,'2023-12-04 13:16:28','echo',56,NULL),(8,'Add_device','测试8',NULL,'2023-12-04 13:16:28','admin',57,NULL),(9,'Add_device','测试11',NULL,'2023-12-04 13:16:28','admin',58,NULL),(10,'Add_device','测试设备10',NULL,'2023-12-04 13:16:28','echo',59,NULL),(11,'Add_device','测试设备11',NULL,'2023-12-04 13:16:28','admin',60,NULL),(12,'Add_device','demo',NULL,'2023-12-04 13:16:28','admin',61,NULL),(14,'Add_device','sdfsd',NULL,'2023-12-04 13:16:28','echo',62,NULL),(15,'Add_device','sensor15',NULL,'2023-12-04 13:16:28','echo',63,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 13:01:19',NULL,64,NULL),(1,'LEDON','led','/topics/sub/led','2023-12-10 13:05:45','admin',65,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 13:05:50','admin',66,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 13:10:13',NULL,67,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 13:15:59',NULL,68,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 13:19:36',NULL,69,NULL),(4,'BEEPON','beep','/topics/sub/beep','2023-12-10 13:35:15',NULL,70,NULL),(1,'LEDON','led','/topics/sub/led','2023-12-10 13:40:53','admin',71,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 13:40:56','admin',72,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 13:41:17','admin',73,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 13:41:19','admin',74,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 13:42:52','admin',75,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 13:42:54','admin',76,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 14:00:11','admin',77,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 14:00:59','admin',78,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 14:02:52','admin',79,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 14:03:37','admin',80,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 14:03:39','admin',81,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 14:04:36','admin',82,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:05:30',NULL,83,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:11:28',NULL,84,NULL),(1,'LEDON','led','/topics/sub/led','2023-12-10 14:13:50','admin',85,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 14:13:53','admin',86,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 14:24:48','admin',87,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 14:24:50','admin',88,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 14:28:00','admin',89,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 14:28:02','admin',90,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 14:28:05','admin',91,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 14:28:06','admin',92,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 14:28:14','admin',93,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 14:28:16','admin',94,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 14:29:41','admin',95,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 14:29:42','admin',96,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 14:29:43','admin',97,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 14:29:43','admin',98,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 14:29:43','admin',99,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 14:29:43','admin',100,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 14:29:43','admin',101,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:34:25',NULL,102,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:35:16',NULL,103,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:41:42',NULL,104,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:47:04',NULL,105,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:50:13',NULL,106,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:51:55',NULL,107,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 14:59:59',NULL,108,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:01:43',NULL,109,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:02:59',NULL,110,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:04:05',NULL,111,NULL),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:32:26','admin',112,'30'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:32:56','admin',113,'30'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:34:18','admin',114,'20'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:34:38','admin',115,'20'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:35:49','admin',116,'20'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:36:09','admin',117,'20'),(2,'DELAYOPEN','beep','/topics/sub/beep','2023-12-10 15:38:03','admin',118,'5'),(2,'DELAYOPEN','beep','/topics/sub/beep','2023-12-10 15:38:08','admin',119,'5'),(1,'LEDON','led','/topics/sub/led','2023-12-10 15:38:56','admin',120,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 15:38:57','admin',121,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 15:39:01','admin',122,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 15:39:02','admin',123,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 15:40:21','admin',124,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 15:40:23','admin',125,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 15:42:05','admin',126,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 15:42:06','admin',127,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 15:42:08','admin',128,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 15:42:09','admin',129,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:42:09',NULL,130,NULL),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 15:42:13','admin',131,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 15:42:15','admin',132,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 15:42:16','admin',133,''),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:42:28','admin',134,'5'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:42:33','admin',135,'5'),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:43:07',NULL,136,NULL),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:49:09','admin',137,'5'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:49:14','admin',138,'5'),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:49:15',NULL,139,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:49:20',NULL,140,NULL),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 15:49:21','admin',141,'20'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 15:49:41','admin',142,'20'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:55:50','admin',143,'5'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:55:55','admin',144,'5'),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 15:55:56',NULL,145,NULL),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 15:56:49','admin',146,'8'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 15:56:57','admin',147,'8'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:57:32','admin',148,'15'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 15:57:39','admin',149,'15'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 15:57:47','admin',150,'15'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 16:19:16','admin',151,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 16:19:28','admin',152,'5'),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 16:19:51','admin',153,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 16:19:59','admin',154,'5'),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:23:24',NULL,155,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:23:29',NULL,156,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:23:35',NULL,157,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:23:41',NULL,158,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:23:47',NULL,159,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:23:52',NULL,160,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:23:58',NULL,161,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:24:04',NULL,162,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 16:24:09',NULL,163,NULL),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 16:28:46','admin',164,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 16:28:56','admin',165,'5'),(1,'LEDON','led','/topics/sub/led','2023-12-10 16:35:25','admin',166,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 16:35:32','admin',167,''),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 16:36:16','admin',168,'5'),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 16:39:24','admin',169,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 16:39:25','admin',170,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 16:39:26','admin',171,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 16:43:11','admin',172,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 16:43:13','admin',173,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 17:02:13','admin',174,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 17:02:15','admin',175,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 17:02:39','admin',176,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 17:30:46','admin',177,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 17:49:32','admin',178,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 17:49:43','admin',179,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 17:49:46','admin',180,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 17:49:48','admin',181,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 17:49:51','admin',182,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 17:49:52','admin',183,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 17:50:03','admin',184,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 17:50:06','admin',185,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 17:50:07','admin',186,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 17:50:08','admin',187,''),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 17:51:54','admin',188,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 17:52:25','admin',189,'5'),(1,'LEDON','led','/topics/sub/led','2023-12-10 17:52:56','admin',190,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 17:53:02',NULL,191,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 17:53:08',NULL,192,NULL),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 17:53:13','admin',193,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 19:10:31','admin',194,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 19:10:36','admin',195,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 19:11:05','admin',196,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 19:11:06','admin',197,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 19:19:32','admin',198,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 19:19:36','admin',199,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 19:19:51','admin',200,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 19:19:56','admin',201,''),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 19:22:10','admin',202,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 19:22:42','admin',203,'5'),(1,'LEDON','led','/topics/sub/led','2023-12-10 19:23:02','admin',204,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 19:23:09',NULL,205,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 19:23:15',NULL,206,NULL),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 19:23:24','admin',207,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 19:43:58','admin',208,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 19:44:05','admin',209,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 19:44:29','admin',210,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 19:44:34','admin',211,''),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 19:46:21','admin',212,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 19:46:43','admin',213,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 19:46:58','admin',214,'5'),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 19:47:16','admin',215,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 19:47:17','admin',216,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 19:50:12','admin',217,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 19:50:13','admin',218,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 19:56:04','admin',219,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 19:56:09','admin',220,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 19:56:22','admin',221,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 19:56:25','admin',222,''),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 19:57:54','admin',223,'5'),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 19:57:57',NULL,224,NULL),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 19:58:30','admin',225,'5'),(1,'LEDON','led','/topics/sub/led','2023-12-10 19:59:01','admin',226,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 19:59:10',NULL,227,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 19:59:16',NULL,228,NULL),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 19:59:19','admin',229,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 20:00:36','admin',230,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 20:09:36','admin',231,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 20:12:25','admin',232,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 20:12:26','admin',233,''),(1,'LEDON','led','/topics/sub/led','2023-12-10 20:15:19','admin',234,''),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 20:15:23','admin',235,''),(2,'BEEPON','beep','/topics/sub/beep','2023-12-10 20:15:30','admin',236,''),(2,'BEEPOFF','beep','/topics/sub/beep','2023-12-10 20:15:37','admin',237,''),(1,'DELAYOPEN','led','/topics/sub/led','2023-12-10 20:16:37','admin',238,'5'),(1,'DELAYCLOSE','led','/topics/sub/led','2023-12-10 20:17:00','admin',239,'5'),(1,'LEDON','led','/topics/sub/led','2023-12-10 20:17:27','admin',240,''),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 20:17:38',NULL,241,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 20:17:43',NULL,242,NULL),(4,'BEEPALERT','beep','/topics/sub/beep','2023-12-10 20:17:49',NULL,243,NULL),(1,'LEDOFF','led','/topics/sub/led','2023-12-10 20:19:36','admin',244,'');
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
Create Trigger trigger_Add after INSERT on device for each row
begin
    INSERT into command_record(did, command, deviceName, date, operator)
    VALUES(new.did, 'Add_device', new.deviceName, now(), new.creator);
end;

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
INSERT INTO `device` VALUES (1,'led','off',116.37,39.86,'admin',0,'sensor',1,'2023-12-03 19:07:31'),(2,'beep','off',121.47,31.23,'admin',0,'sensor',1,'2023-12-01 19:07:35'),(3,'htSensor','off',110.21,22.66,'admin',0,'sensor',1,'2023-12-03 19:07:42'),(4,'测试设备4','off',123,22,'echo',0,'other',1,'2023-11-03 19:07:45'),(5,'摄像头','off',122.44,22.33,'echo',0,'sensor',1,'2023-11-23 19:07:51'),(6,'测试设备6','off',122,23,'echo',0,'light',1,'2023-11-10 19:08:02'),(7,'测试设备7','off',123,23,'echo',0,'sensor',1,'2023-12-04 19:07:31'),(8,'测试8','off',123,21,'admin',0,'other',1,'2023-12-05 19:07:35'),(9,'测试11','off',123,21,'admin',1,'light',1,'2023-12-06 19:07:42'),(10,'测试设备10','off',112,23,'echo',0,'sensor',1,'2023-11-06 19:07:45'),(11,'测试设备11','off',123,24,'admin',0,'other',1,'2023-11-24 19:07:51'),(12,'demo','off',0,0,'admin',0,'sensor',1,'2023-12-04 12:50:46'),(14,'sdfsd','off',0,0,'echo',0,'light',1,'2023-12-04 13:16:36'),(15,'sensor15','off',0,0,'echo',0,'sensor',1,'2023-12-04 16:04:18');
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_INIT` AFTER INSERT ON `device` FOR EACH ROW begin
    if NEW.type = 'sensor' then
        INSERT into threshold(did, temp_max, humi_max, light_max)
        VALUES(NEW.did, 35, 80, 0);
    else if NEW.type = 'light' then
        INSERT into threshold(did, temp_max, humi_max, light_max)
        VALUES(NEW.did, 0, 0, 150);
    end if;
    end if;
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
INSERT INTO `normal_data` VALUES (1,2,19.59,'2023-08-15 08:17:02',0,280),(2,1,28.44,'2023-08-14 00:19:46',0,333),(3,2,15.95,'2023-08-14 00:19:46',0,300),(4,1,25.73,'2023-08-14 00:19:56',0,540),(5,2,27.34,'2023-08-14 00:19:56',0,480),(6,1,17.75,'2023-08-14 00:20:06',0,250),(7,3,16.14,'2023-08-14 00:20:16',0,100),(8,1,25.16,'2023-08-14 00:20:16',0,0),(9,3,12.63,'2023-08-14 00:20:26',0,0),(10,2,17.92,'2023-08-14 00:20:26',0,0),(11,2,24.17,'2023-08-14 00:20:36',0,0),(12,3,14.87,'2023-08-14 00:20:36',0,0),(13,1,13.21,'2023-08-14 00:20:46',0,0),(14,3,10.47,'2023-08-14 00:20:46',0,0),(15,1,14.62,'2023-08-14 00:20:56',0,0),(16,1,16.67,'2023-08-14 00:20:56',0,0),(17,2,13.49,'2023-08-14 00:21:16',0,0),(18,1,15.72,'2023-08-14 00:21:26',0,0),(19,2,17.81,'2023-08-14 00:21:26',0,0),(20,1,12.52,'2023-08-14 00:21:36',0,0),(21,3,17.41,'2023-08-14 00:21:47',0,0),(22,1,23.68,'2023-08-14 00:21:57',0,0),(23,3,10.89,'2023-08-15 08:14:32',0,0),(24,1,19.91,'2023-08-15 08:14:32',0,0),(25,1,16.08,'2023-08-15 08:14:42',0,0),(26,3,23.43,'2023-08-15 08:14:42',0,0),(27,1,20.94,'2023-08-15 08:14:52',0,0),(28,3,16.33,'2023-08-15 08:14:52',0,0),(29,1,27.83,'2023-08-15 08:15:02',0,0),(30,2,12.06,'2023-08-15 08:15:12',0,0),(31,1,28.27,'2023-08-15 08:15:12',0,0),(32,2,11.44,'2023-08-15 08:15:22',0,0),(33,2,24.48,'2023-08-15 08:15:32',0,0),(34,1,15.94,'2023-08-15 08:15:42',0,0),(35,1,16.49,'2023-08-15 08:15:42',0,0),(36,3,16.03,'2023-08-15 08:15:52',0,0),(37,1,19.19,'2023-08-15 08:15:52',0,0),(38,3,25.81,'2023-08-15 08:16:02',0,0),(39,2,10.66,'2023-08-15 08:16:02',0,0),(40,1,24.16,'2023-08-15 08:16:12',0,0),(41,2,19.15,'2023-08-15 08:16:22',0,0),(42,1,10.01,'2023-08-15 08:16:32',0,0),(43,2,12.26,'2023-08-15 08:16:42',0,0),(44,3,11.99,'2023-08-15 08:16:42',0,0),(45,3,22.24,'2023-08-15 08:16:52',0,0),(46,2,12.43,'2023-08-15 08:16:52',0,0),(47,1,20.17,'2023-09-30 14:58:01',0,0),(48,1,13.79,'2023-09-29 14:58:01',0,0),(49,1,11.88,'2023-09-30 14:58:11',0,0),(50,2,10.92,'2023-09-30 14:58:21',0,0),(51,2,19.48,'2023-09-30 14:58:21',0,0),(52,3,21.35,'2023-09-30 15:00:22',0,0),(53,3,11.93,'2023-09-30 15:00:32',0,0),(54,2,13.75,'2023-09-30 15:01:48',0,0),(55,2,23.99,'2023-09-30 15:01:58',0,0),(56,3,26.18,'2023-09-30 15:01:58',0,0),(57,1,26.08,'2023-09-30 15:02:08',0,0),(58,2,17.35,'2023-09-30 15:02:08',0,0),(59,1,25.17,'2023-09-30 15:02:28',0,0),(60,2,19.76,'2023-09-30 15:02:28',0,0),(61,3,23.85,'2023-09-30 15:02:38',0,0),(62,2,12.51,'2023-09-30 15:02:38',0,0),(63,1,14.53,'2023-09-30 15:02:58',0,0),(64,1,20.41,'2023-09-30 15:03:08',0,0),(65,3,11.69,'2023-09-30 15:03:08',0,0),(66,1,15.44,'2023-09-30 15:03:18',0,0),(67,2,23.64,'2023-09-30 15:03:18',0,0),(68,1,18.82,'2023-09-30 15:03:28',0,0);
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
INSERT INTO `threshold` VALUES (3,35,80,0),(4,0,0,150);
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
INSERT INTO `user` VALUES ('echo','def823239cd714d6f18cda2a2b9e1369046125716c43d27a1256753ac72164b0','f778738c-e4f8-4870-b634-56703b4acafe.gif','editor',0,NULL,'8154532213648629760'),('admin','def823239cd714d6f18cda2a2b9e1369046125716c43d27a1256753ac72164b0','ff1dfcdb-aa8e-4e16-a240-0fb4b315c4d4.jpg','admin',0,'demo@test','8154532213648629761'),('ztl','def823239cd714d6f18cda2a2b9e1369046125716c43d27a1256753ac72164b0','f778738c-e4f8-4870-b634-56703b4acafe.gif','editor',1,NULL,'8154532213648629762');
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
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='DB WorkerID Assigner for UID Generator';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker_node`
--

LOCK TABLES `worker_node` WRITE;
/*!40000 ALTER TABLE `worker_node` DISABLE KEYS */;
INSERT INTO `worker_node` VALUES (13,'192.168.7.1','1701001722632-33439',2,'2023-11-26','2023-11-26 12:28:42','2023-11-26 12:28:42'),(14,'192.168.7.1','1701065661883-10464',2,'2023-11-27','2023-11-27 06:14:21','2023-11-27 06:14:21'),(15,'192.168.7.1','1701066687883-99341',2,'2023-11-27','2023-11-27 06:31:27','2023-11-27 06:31:27'),(16,'192.168.7.1','1701066861760-79489',2,'2023-11-27','2023-11-27 06:34:21','2023-11-27 06:34:21'),(17,'192.168.7.1','1701066988392-12395',2,'2023-11-27','2023-11-27 06:36:28','2023-11-27 06:36:28'),(18,'192.168.7.1','1701067327067-49437',2,'2023-11-27','2023-11-27 06:42:07','2023-11-27 06:42:07'),(19,'192.168.7.1','1701067466121-61403',2,'2023-11-27','2023-11-27 06:44:26','2023-11-27 06:44:26'),(20,'192.168.7.1','1701067523747-26520',2,'2023-11-27','2023-11-27 06:45:23','2023-11-27 06:45:23'),(21,'192.168.7.1','1701067627778-41658',2,'2023-11-27','2023-11-27 06:47:07','2023-11-27 06:47:07'),(22,'192.168.7.1','1701067690572-33864',2,'2023-11-27','2023-11-27 06:48:10','2023-11-27 06:48:10'),(23,'192.168.7.1','1701068203440-15605',2,'2023-11-27','2023-11-27 06:56:43','2023-11-27 06:56:43'),(24,'192.168.7.1','1701068563273-83135',2,'2023-11-27','2023-11-27 07:02:43','2023-11-27 07:02:43'),(25,'192.168.7.1','1701068865942-88363',2,'2023-11-27','2023-11-27 07:07:45','2023-11-27 07:07:45'),(26,'192.168.7.1','1701068887356-48503',2,'2023-11-27','2023-11-27 07:08:07','2023-11-27 07:08:07'),(147,'192.168.173.222','1701676850364-62146',2,'2023-12-04','2023-12-04 08:00:50','2023-12-04 08:00:50'),(148,'192.168.173.222','1701677630811-23670',2,'2023-12-04','2023-12-04 08:13:50','2023-12-04 08:13:50'),(149,'192.168.7.1','1701696570615-54083',2,'2023-12-04','2023-12-04 13:29:30','2023-12-04 13:29:30'),(150,'192.168.7.1','1701696628283-86439',2,'2023-12-04','2023-12-04 13:30:28','2023-12-04 13:30:28'),(151,'192.168.7.1','1701696799009-86946',2,'2023-12-04','2023-12-04 13:33:19','2023-12-04 13:33:19'),(152,'192.168.7.1','1701697143140-54956',2,'2023-12-04','2023-12-04 13:39:03','2023-12-04 13:39:03'),(153,'192.168.7.1','1701697355539-72174',2,'2023-12-04','2023-12-04 13:42:35','2023-12-04 13:42:35'),(154,'192.168.7.1','1701697604384-44999',2,'2023-12-04','2023-12-04 13:46:44','2023-12-04 13:46:44'),(155,'192.168.7.1','1701697721457-57890',2,'2023-12-04','2023-12-04 13:48:41','2023-12-04 13:48:41'),(156,'192.168.7.1','1701697882263-21111',2,'2023-12-04','2023-12-04 13:51:22','2023-12-04 13:51:22'),(157,'192.168.7.1','1701697970499-98572',2,'2023-12-04','2023-12-04 13:52:50','2023-12-04 13:52:50'),(158,'192.168.7.1','1701698108822-99354',2,'2023-12-04','2023-12-04 13:55:08','2023-12-04 13:55:08'),(159,'192.168.7.1','1701698218859-45855',2,'2023-12-04','2023-12-04 13:56:58','2023-12-04 13:56:58'),(160,'192.168.7.1','1701699022695-68841',2,'2023-12-04','2023-12-04 14:10:22','2023-12-04 14:10:22'),(161,'192.168.7.1','1702136911724-53469',2,'2023-12-09','2023-12-09 15:48:31','2023-12-09 15:48:31'),(162,'192.168.7.1','1702137682565-82613',2,'2023-12-09','2023-12-09 16:01:22','2023-12-09 16:01:22'),(163,'192.168.7.1','1702139358066-47486',2,'2023-12-09','2023-12-09 16:29:18','2023-12-09 16:29:18'),(164,'192.168.7.1','1702140858953-24469',2,'2023-12-09','2023-12-09 16:54:18','2023-12-09 16:54:18'),(165,'192.168.7.1','1702181377937-75182',2,'2023-12-10','2023-12-10 04:09:37','2023-12-10 04:09:37'),(166,'192.168.7.1','1702181506596-4137',2,'2023-12-10','2023-12-10 04:11:46','2023-12-10 04:11:46'),(167,'192.168.7.1','1702181541786-1071',2,'2023-12-10','2023-12-10 04:12:21','2023-12-10 04:12:21'),(168,'192.168.7.1','1702181612147-22324',2,'2023-12-10','2023-12-10 04:13:32','2023-12-10 04:13:32'),(169,'192.168.7.1','1702181659248-84835',2,'2023-12-10','2023-12-10 04:14:19','2023-12-10 04:14:19'),(170,'192.168.7.1','1702181684724-73209',2,'2023-12-10','2023-12-10 04:14:44','2023-12-10 04:14:44'),(171,'192.168.7.1','1702182377575-22355',2,'2023-12-10','2023-12-10 04:26:17','2023-12-10 04:26:17'),(172,'192.168.7.1','1702182561603-20341',2,'2023-12-10','2023-12-10 04:29:21','2023-12-10 04:29:21'),(173,'192.168.7.1','1702182889829-82965',2,'2023-12-10','2023-12-10 04:34:49','2023-12-10 04:34:49'),(174,'192.168.7.1','1702182949732-36774',2,'2023-12-10','2023-12-10 04:35:49','2023-12-10 04:35:49'),(175,'192.168.7.1','1702182993209-84418',2,'2023-12-10','2023-12-10 04:36:33','2023-12-10 04:36:33'),(176,'192.168.7.1','1702183041148-61101',2,'2023-12-10','2023-12-10 04:37:21','2023-12-10 04:37:21'),(177,'192.168.7.1','1702183061847-75165',2,'2023-12-10','2023-12-10 04:37:41','2023-12-10 04:37:41'),(178,'192.168.7.1','1702183151175-67688',2,'2023-12-10','2023-12-10 04:39:11','2023-12-10 04:39:11'),(179,'192.168.7.1','1702183196812-92531',2,'2023-12-10','2023-12-10 04:39:56','2023-12-10 04:39:56'),(180,'192.168.7.1','1702183263496-57095',2,'2023-12-10','2023-12-10 04:41:03','2023-12-10 04:41:03'),(181,'192.168.7.1','1702183536649-31937',2,'2023-12-10','2023-12-10 04:45:36','2023-12-10 04:45:36'),(182,'192.168.7.1','1702183617821-67674',2,'2023-12-10','2023-12-10 04:46:57','2023-12-10 04:46:57'),(183,'192.168.7.1','1702184462583-56679',2,'2023-12-10','2023-12-10 05:01:02','2023-12-10 05:01:02'),(184,'192.168.7.1','1702184706311-64929',2,'2023-12-10','2023-12-10 05:05:06','2023-12-10 05:05:06'),(185,'192.168.7.1','1702186495628-76732',2,'2023-12-10','2023-12-10 05:34:55','2023-12-10 05:34:55'),(186,'192.168.7.1','1702186832896-55635',2,'2023-12-10','2023-12-10 05:40:32','2023-12-10 05:40:32'),(187,'192.168.7.1','1702187985028-43309',2,'2023-12-10','2023-12-10 05:59:45','2023-12-10 05:59:45'),(188,'192.168.7.1','1702188815839-11781',2,'2023-12-10','2023-12-10 06:13:35','2023-12-10 06:13:35'),(189,'192.168.7.1','1702190087966-64449',2,'2023-12-10','2023-12-10 06:34:47','2023-12-10 06:34:47'),(190,'192.168.7.1','1702191071657-11188',2,'2023-12-10','2023-12-10 06:51:11','2023-12-10 06:51:11'),(191,'192.168.7.1','1702191769762-31577',2,'2023-12-10','2023-12-10 07:02:49','2023-12-10 07:02:49'),(192,'192.168.7.1','1702193375915-83554',2,'2023-12-10','2023-12-10 07:29:35','2023-12-10 07:29:35'),(193,'192.168.7.1','1702193465771-73641',2,'2023-12-10','2023-12-10 07:31:05','2023-12-10 07:31:05'),(194,'192.168.7.1','1702193513141-49812',2,'2023-12-10','2023-12-10 07:31:53','2023-12-10 07:31:53'),(195,'192.168.7.1','1702193647566-13197',2,'2023-12-10','2023-12-10 07:34:07','2023-12-10 07:34:07'),(196,'192.168.7.1','1702194820251-71627',2,'2023-12-10','2023-12-10 07:53:40','2023-12-10 07:53:40'),(197,'192.168.7.1','1702195555754-25257',2,'2023-12-10','2023-12-10 08:05:55','2023-12-10 08:05:55'),(198,'192.168.7.1','1702196574135-5636',2,'2023-12-10','2023-12-10 08:22:54','2023-12-10 08:22:54'),(199,'192.168.7.1','1702196657551-66599',2,'2023-12-10','2023-12-10 08:24:17','2023-12-10 08:24:17'),(200,'192.168.7.1','1702197830852-19335',2,'2023-12-10','2023-12-10 08:43:50','2023-12-10 08:43:50'),(201,'192.168.7.1','1702197848182-59471',2,'2023-12-10','2023-12-10 08:44:08','2023-12-10 08:44:08'),(202,'192.168.7.1','1702197952577-39385',2,'2023-12-10','2023-12-10 08:45:52','2023-12-10 08:45:52'),(203,'192.168.7.1','1702198795801-82047',2,'2023-12-10','2023-12-10 08:59:55','2023-12-10 08:59:55'),(204,'192.168.7.1','1702199211224-29883',2,'2023-12-10','2023-12-10 09:06:51','2023-12-10 09:06:51'),(205,'192.168.7.1','1702199270641-91449',2,'2023-12-10','2023-12-10 09:07:50','2023-12-10 09:07:50'),(206,'192.168.7.1','1702199690459-49752',2,'2023-12-10','2023-12-10 09:14:50','2023-12-10 09:14:50'),(207,'192.168.7.1','1702200611536-7025',2,'2023-12-10','2023-12-10 09:30:11','2023-12-10 09:30:11'),(208,'192.168.7.1','1702201570646-5773',2,'2023-12-10','2023-12-10 09:46:10','2023-12-10 09:46:10'),(209,'192.168.7.1','1702206440292-23515',2,'2023-12-10','2023-12-10 11:07:20','2023-12-10 11:07:20'),(210,'192.168.7.1','1702210079727-2068',2,'2023-12-10','2023-12-10 12:07:59','2023-12-10 12:07:59'),(211,'192.168.7.1','1702210108355-61378',2,'2023-12-10','2023-12-10 12:08:28','2023-12-10 12:08:28'),(212,'192.168.7.1','1702210353146-57236',2,'2023-12-10','2023-12-10 12:12:33','2023-12-10 12:12:33');
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

-- Dump completed on 2023-12-10 22:41:12
