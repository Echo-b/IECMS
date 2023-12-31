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
                              PRIMARY KEY (`dno`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_info`
--

LOCK TABLES `alert_info` WRITE;
/*!40000 ALTER TABLE `alert_info` DISABLE KEYS */;
INSERT INTO `alert_info` VALUES (1,31.2343,'sensor','2022-08-14 00:19:36',2),(2,33.0289,'sensor','2022-08-14 00:20:06',2),(3,35.5381,'air-conditioning','2022-08-14 00:21:06',1),(4,35.4494,'sensor','2022-08-14 00:21:06',2),(5,33.0982,'air-conditioning','2022-08-14 00:21:16',1),(6,37.9454,'fan','2022-08-14 00:21:36',3),(7,32.9906,'sensor','2022-08-14 00:21:47',2),(8,38.9557,'fan','2022-08-15 08:15:02',3),(9,33.0363,'air-conditioning','2022-08-15 08:15:22',1),(10,38.1381,'air-conditioning','2022-08-15 08:15:32',1),(11,33.981,'sensor','2022-08-15 08:16:12',2),(12,30.4539,'fan','2022-08-15 08:16:22',3),(13,32.8786,'air-conditioning','2022-08-15 08:16:32',1),(14,31.3029,'sensor','2022-09-30 14:58:11',2),(15,30.1594,'fan','2022-09-30 15:01:48',3),(16,30.2197,'air-conditioning','2022-09-30 15:02:18',1),(17,35.3748,'sensor','2022-09-30 15:02:18',2),(18,38.9878,'air-conditioning','2022-09-30 15:02:48',1),(19,32.712,'fan','2022-09-30 15:02:48',3),(20,35.3054,'fan','2022-09-30 15:02:58',3),(21,34.8835,'fan','2022-09-30 15:03:28',3),(22,33.767,'air-conditioning','2022-09-30 15:03:38',1),(23,36.5719,'air-conditioning','2022-09-30 15:07:39',1),(24,36.0935,'fan','2022-09-30 15:07:39',3),(25,33.9455,'air-conditioning','2022-09-30 15:07:49',1),(26,33.3652,'fan','2022-09-30 15:07:49',3);
/*!40000 ALTER TABLE `alert_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `average_temperature`
--

DROP TABLE IF EXISTS `average_temperature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `average_temperature` (
                                       `dno` int NOT NULL AUTO_INCREMENT,
                                       `did` int NOT NULL,
                                       `temperature` float NOT NULL,
                                       `date` varchar(50) NOT NULL,
                                       PRIMARY KEY (`dno`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `average_temperature`
--

LOCK TABLES `average_temperature` WRITE;
/*!40000 ALTER TABLE `average_temperature` DISABLE KEYS */;
INSERT INTO `average_temperature` VALUES (1,1,14.2664,'2022-10-01 15:41:58'),(2,2,15.137,'2022-10-01 15:42:08'),(3,1,15.2664,'2022-10-01 15:42:18'),(4,3,15.2664,'2022-10-01 15:42:28'),(5,2,16.137,'2022-10-01 15:42:38'),(6,3,14.2664,'2022-10-01 15:43:14'),(7,1,13.2664,'2022-10-01 15:43:24'),(8,3,16.38,'2022-10-01 15:43:34'),(9,1,13.5664,'2022-10-01 15:43:44'),(10,2,14.137,'2022-10-01 15:43:54'),(11,1,15.4664,'2022-10-01 15:45:25'),(12,3,15.38,'2022-10-01 15:45:35'),(13,1,12.2664,'2022-10-01 15:45:45');
/*!40000 ALTER TABLE `average_temperature` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `command_record`
--

LOCK TABLES `command_record` WRITE;
/*!40000 ALTER TABLE `command_record` DISABLE KEYS */;
INSERT INTO `command_record` VALUES (1,'LEDON','air-condition','/topics/led/sub','2023-10-11 11:01:06','admin',1,NULL);
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
                          PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
INSERT INTO `device` VALUES (1,'air-condition','on',116.37,39.86,'admin',0,'public',1),(2,'sensor','off',121.47,31.23,'admin',0,'public',1),(3,'fringe','off',110.21,22.66,'admin',0,'public',1),(4,'测试设备4','off',123,22,'echo',0,'private',1),(5,'摄像头','off',122.44,22.33,'echo',0,'private',1),(6,'测试设备6','off',122,23,'echo',0,'private',1),(7,'测试设备7','off',123,23,'echo',0,'private',1),(8,'测试8','off',123,21,'admin',0,'public',1),(9,'测试11','off',123,21,'admin',0,'public',1),(10,'测试设备10','off',112,23,'echo',0,'private',1),(11,'测试设备11','off',123,24,'admin',0,'public',1);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
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
                               PRIMARY KEY (`dno`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `normal_data`
--

LOCK TABLES `normal_data` WRITE;
/*!40000 ALTER TABLE `normal_data` DISABLE KEYS */;
INSERT INTO `normal_data` VALUES (1,2,19.5917,'2022-08-15 08:17:02'),(2,1,28.446,'2022-08-14 00:19:46'),(3,2,15.9584,'2022-08-14 00:19:46'),(4,1,25.735,'2022-08-14 00:19:56'),(5,2,27.3471,'2022-08-14 00:19:56'),(6,1,7.75604,'2022-08-14 00:20:06'),(7,3,6.14843,'2022-08-14 00:20:16'),(8,1,25.1659,'2022-08-14 00:20:16'),(9,3,2.63473,'2022-08-14 00:20:26'),(10,2,7.92553,'2022-08-14 00:20:26'),(11,2,24.171,'2022-08-14 00:20:36'),(12,3,14.8567,'2022-08-14 00:20:36'),(13,1,3.21413,'2022-08-14 00:20:46'),(14,3,10.4727,'2022-08-14 00:20:46'),(15,1,14.6942,'2022-08-14 00:20:56'),(16,1,16.4767,'2022-08-14 00:20:56'),(17,2,3.44898,'2022-08-14 00:21:16'),(18,1,15.5972,'2022-08-14 00:21:26'),(19,2,7.85811,'2022-08-14 00:21:26'),(20,1,12.3452,'2022-08-14 00:21:36'),(21,3,17.6541,'2022-08-14 00:21:47'),(22,1,23.688,'2022-08-14 00:21:57'),(23,3,10.3889,'2022-08-15 08:14:32'),(24,1,19.9691,'2022-08-15 08:14:32'),(25,1,16.0708,'2022-08-15 08:14:42'),(26,3,23.4453,'2022-08-15 08:14:42'),(27,1,20.9384,'2022-08-15 08:14:52'),(28,3,16.3433,'2022-08-15 08:14:52'),(29,1,27.8723,'2022-08-15 08:15:02'),(30,2,12.0226,'2022-08-15 08:15:12'),(31,1,28.2427,'2022-08-15 08:15:12'),(32,2,1.42735,'2022-08-15 08:15:22'),(33,2,24.4388,'2022-08-15 08:15:32'),(34,1,5.93442,'2022-08-15 08:15:42'),(35,1,6.49322,'2022-08-15 08:15:42'),(36,3,6.04633,'2022-08-15 08:15:52'),(37,1,19.7319,'2022-08-15 08:15:52'),(38,3,25.8381,'2022-08-15 08:16:02'),(39,2,10.6046,'2022-08-15 08:16:02'),(40,1,24.1836,'2022-08-15 08:16:12'),(41,2,19.1285,'2022-08-15 08:16:22'),(42,1,0.0115113,'2022-08-15 08:16:32'),(43,2,2.25996,'2022-08-15 08:16:42'),(44,3,1.94188,'2022-08-15 08:16:42'),(45,3,22.024,'2022-08-15 08:16:52'),(46,2,12.4273,'2022-08-15 08:16:52'),(47,1,20.1017,'2022-09-30 14:58:01'),(48,1,3.7279,'2022-09-30 14:58:01'),(49,1,0.324549,'2022-09-30 14:58:11'),(50,2,0.979237,'2022-09-30 14:58:21'),(51,2,19.7548,'2022-09-30 14:58:21'),(52,3,21.3295,'2022-09-30 15:00:22'),(53,3,1.92634,'2022-09-30 15:00:32'),(54,2,0.790537,'2022-09-30 15:01:48'),(55,2,23.9299,'2022-09-30 15:01:58'),(56,3,26.188,'2022-09-30 15:01:58'),(57,1,26.0678,'2022-09-30 15:02:08'),(58,2,17.3505,'2022-09-30 15:02:08'),(59,1,25.1627,'2022-09-30 15:02:28'),(60,2,19.7836,'2022-09-30 15:02:28'),(61,3,23.8425,'2022-09-30 15:02:38'),(62,2,12.5631,'2022-09-30 15:02:38'),(63,1,14.53,'2022-09-30 15:02:58'),(64,1,20.4661,'2022-09-30 15:03:08'),(65,3,11.4669,'2022-09-30 15:03:08'),(66,1,5.45041,'2022-09-30 15:03:18'),(67,2,23.6494,'2022-09-30 15:03:18'),(68,1,8.85319,'2022-09-30 15:03:28');
/*!40000 ALTER TABLE `normal_data` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todolist`
--

LOCK TABLES `todolist` WRITE;
/*!40000 ALTER TABLE `todolist` DISABLE KEYS */;
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
INSERT INTO `user` VALUES ('echo','310647c17748fd6731a1fcf4b4106bf86399975af6388b1ff91ea93aa4a92c2e','f778738c-e4f8-4870-b634-56703b4acafe.gif','editor',1,NULL,'8154532213648629760'),('admin','def823239cd714d6f18cda2a2b9e1369046125716c43d27a1256753ac72164b0','56e1efca-cd04-4904-820b-da3a679ea9b3.jpg','admin',1,'demo@test','8154532213648629761');
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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='DB WorkerID Assigner for UID Generator';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker_node`
--

LOCK TABLES `worker_node` WRITE;
/*!40000 ALTER TABLE `worker_node` DISABLE KEYS */;
INSERT INTO `worker_node` VALUES (13,'192.168.7.1','1701001722632-33439',2,'2023-11-26','2023-11-26 12:28:42','2023-11-26 12:28:42'),(14,'192.168.7.1','1701065661883-10464',2,'2023-11-27','2023-11-27 06:14:21','2023-11-27 06:14:21'),(15,'192.168.7.1','1701066687883-99341',2,'2023-11-27','2023-11-27 06:31:27','2023-11-27 06:31:27'),(16,'192.168.7.1','1701066861760-79489',2,'2023-11-27','2023-11-27 06:34:21','2023-11-27 06:34:21'),(17,'192.168.7.1','1701066988392-12395',2,'2023-11-27','2023-11-27 06:36:28','2023-11-27 06:36:28'),(18,'192.168.7.1','1701067327067-49437',2,'2023-11-27','2023-11-27 06:42:07','2023-11-27 06:42:07'),(19,'192.168.7.1','1701067466121-61403',2,'2023-11-27','2023-11-27 06:44:26','2023-11-27 06:44:26'),(20,'192.168.7.1','1701067523747-26520',2,'2023-11-27','2023-11-27 06:45:23','2023-11-27 06:45:23'),(21,'192.168.7.1','1701067627778-41658',2,'2023-11-27','2023-11-27 06:47:07','2023-11-27 06:47:07'),(22,'192.168.7.1','1701067690572-33864',2,'2023-11-27','2023-11-27 06:48:10','2023-11-27 06:48:10'),(23,'192.168.7.1','1701068203440-15605',2,'2023-11-27','2023-11-27 06:56:43','2023-11-27 06:56:43'),(24,'192.168.7.1','1701068563273-83135',2,'2023-11-27','2023-11-27 07:02:43','2023-11-27 07:02:43'),(25,'192.168.7.1','1701068865942-88363',2,'2023-11-27','2023-11-27 07:07:45','2023-11-27 07:07:45'),(26,'192.168.7.1','1701068887356-48503',2,'2023-11-27','2023-11-27 07:08:07','2023-11-27 07:08:07'),(27,'192.168.7.1','1701068906717-48031',2,'2023-11-27','2023-11-27 07:08:26','2023-11-27 07:08:26'),(28,'192.168.7.1','1701069319681-30147',2,'2023-11-27','2023-11-27 07:15:19','2023-11-27 07:15:19'),(29,'192.168.7.1','1701069574487-49703',2,'2023-11-27','2023-11-27 07:19:34','2023-11-27 07:19:34'),(30,'192.168.7.1','1701088146258-11232',2,'2023-11-27','2023-11-27 12:29:06','2023-11-27 12:29:06'),(31,'192.168.7.1','1701091019946-12390',2,'2023-11-27','2023-11-27 13:16:59','2023-11-27 13:16:59'),(32,'192.168.31.114','1701149502657-86858',2,'2023-11-28','2023-11-28 05:31:42','2023-11-28 05:31:42'),(33,'192.168.114.222','1701162370540-86108',2,'2023-11-28','2023-11-28 09:06:10','2023-11-28 09:06:10'),(34,'192.168.114.222','1701164648019-94084',2,'2023-11-28','2023-11-28 09:44:08','2023-11-28 09:44:08'),(35,'192.168.114.222','1701164666777-21235',2,'2023-11-28','2023-11-28 09:44:26','2023-11-28 09:44:26'),(36,'192.168.114.222','1701164916871-58882',2,'2023-11-28','2023-11-28 09:48:36','2023-11-28 09:48:36'),(37,'192.168.114.222','1701171005712-57824',2,'2023-11-28','2023-11-28 11:30:05','2023-11-28 11:30:05'),(38,'192.168.114.222','1701171061197-25422',2,'2023-11-28','2023-11-28 11:31:01','2023-11-28 11:31:01'),(39,'192.168.114.222','1701171261295-4607',2,'2023-11-28','2023-11-28 11:34:21','2023-11-28 11:34:21'),(40,'192.168.114.222','1701171544092-66608',2,'2023-11-28','2023-11-28 11:39:04','2023-11-28 11:39:04'),(41,'192.168.114.222','1701171766168-47363',2,'2023-11-28','2023-11-28 11:42:46','2023-11-28 11:42:46'),(42,'192.168.114.222','1701172757523-68981',2,'2023-11-28','2023-11-28 11:59:17','2023-11-28 11:59:17'),(43,'192.168.114.222','1701173038111-18197',2,'2023-11-28','2023-11-28 12:03:58','2023-11-28 12:03:58'),(44,'192.168.114.222','1701176704045-59746',2,'2023-11-28','2023-11-28 13:05:04','2023-11-28 13:05:04'),(45,'192.168.114.222','1701176841634-31102',2,'2023-11-28','2023-11-28 13:07:21','2023-11-28 13:07:21'),(46,'192.168.114.222','1701177169784-33294',2,'2023-11-28','2023-11-28 13:12:49','2023-11-28 13:12:49'),(47,'192.168.114.222','1701177248111-6774',2,'2023-11-28','2023-11-28 13:14:08','2023-11-28 13:14:08'),(48,'192.168.114.222','1701177351536-59365',2,'2023-11-28','2023-11-28 13:15:51','2023-11-28 13:15:51'),(49,'192.168.114.222','1701177822431-46149',2,'2023-11-28','2023-11-28 13:23:42','2023-11-28 13:23:42'),(50,'192.168.114.222','1701178889412-1718',2,'2023-11-28','2023-11-28 13:41:29','2023-11-28 13:41:29'),(51,'192.168.114.222','1701178980849-75454',2,'2023-11-28','2023-11-28 13:43:00','2023-11-28 13:43:00'),(52,'192.168.114.222','1701179040128-38355',2,'2023-11-28','2023-11-28 13:44:00','2023-11-28 13:44:00'),(53,'192.168.114.222','1701179171620-78534',2,'2023-11-28','2023-11-28 13:46:11','2023-11-28 13:46:11'),(54,'192.168.114.222','1701179455523-98016',2,'2023-11-28','2023-11-28 13:50:55','2023-11-28 13:50:55'),(55,'192.168.114.222','1701179523940-5202',2,'2023-11-28','2023-11-28 13:52:03','2023-11-28 13:52:03'),(56,'192.168.114.222','1701179667595-54406',2,'2023-11-28','2023-11-28 13:54:27','2023-11-28 13:54:27'),(57,'192.168.114.222','1701179780214-56154',2,'2023-11-28','2023-11-28 13:56:20','2023-11-28 13:56:20'),(58,'192.168.114.222','1701179801346-39033',2,'2023-11-28','2023-11-28 13:56:41','2023-11-28 13:56:41'),(59,'192.168.114.222','1701179866998-70571',2,'2023-11-28','2023-11-28 13:57:47','2023-11-28 13:57:47'),(60,'192.168.114.222','1701179927528-65562',2,'2023-11-28','2023-11-28 13:58:47','2023-11-28 13:58:47'),(61,'192.168.114.222','1701180155734-1320',2,'2023-11-28','2023-11-28 14:02:35','2023-11-28 14:02:35'),(62,'192.168.114.222','1701180199956-13799',2,'2023-11-28','2023-11-28 14:03:20','2023-11-28 14:03:20'),(63,'192.168.114.222','1701180459463-39640',2,'2023-11-28','2023-11-28 14:07:39','2023-11-28 14:07:39'),(64,'192.168.114.222','1701180510999-82568',2,'2023-11-28','2023-11-28 14:08:31','2023-11-28 14:08:31'),(65,'192.168.114.222','1701180972190-65830',2,'2023-11-28','2023-11-28 14:16:12','2023-11-28 14:16:12'),(66,'192.168.114.222','1701180993158-27410',2,'2023-11-28','2023-11-28 14:16:33','2023-11-28 14:16:33'),(67,'192.168.114.222','1701181110889-23380',2,'2023-11-28','2023-11-28 14:18:30','2023-11-28 14:18:30'),(68,'192.168.114.222','1701181197906-109',2,'2023-11-28','2023-11-28 14:19:57','2023-11-28 14:19:57'),(69,'192.168.114.222','1701181339428-9631',2,'2023-11-28','2023-11-28 14:22:19','2023-11-28 14:22:19'),(70,'192.168.114.222','1701181474367-5286',2,'2023-11-28','2023-11-28 14:24:34','2023-11-28 14:24:34'),(71,'192.168.114.222','1701181649851-96043',2,'2023-11-28','2023-11-28 14:27:29','2023-11-28 14:27:29'),(72,'192.168.114.222','1701181905373-33458',2,'2023-11-28','2023-11-28 14:31:45','2023-11-28 14:31:45'),(73,'192.168.114.222','1701181946656-86553',2,'2023-11-28','2023-11-28 14:32:26','2023-11-28 14:32:26'),(74,'192.168.34.1','1701223915187-77134',2,'2023-11-29','2023-11-29 02:11:55','2023-11-29 02:11:55'),(75,'192.168.34.1','1701224311361-41511',2,'2023-11-29','2023-11-29 02:18:31','2023-11-29 02:18:31'),(76,'192.168.34.1','1701224368587-11899',2,'2023-11-29','2023-11-29 02:19:28','2023-11-29 02:19:28'),(77,'192.168.34.1','1701224441946-42191',2,'2023-11-29','2023-11-29 02:20:41','2023-11-29 02:20:41');
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

-- Dump completed on 2023-11-29 17:00:36
