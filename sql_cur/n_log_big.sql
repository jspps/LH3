-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2017-04-13 09:32:52
-- 服务器版本： 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `learnhall3_cfg`
--
DROP DATABASE IF EXISTS `learnhall3_cfg`;
CREATE DATABASE IF NOT EXISTS `learnhall3_cfg` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `learnhall3_cfg`;
--
-- Database: `learnhall3_design`
--
DROP DATABASE IF EXISTS `learnhall3_design`;
CREATE DATABASE IF NOT EXISTS `learnhall3_design` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `learnhall3_design`;

DELIMITER $$
--
-- 存储过程
--
DROP PROCEDURE IF EXISTS `pro_rnk4profit`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_rnk4profit` (IN `parsType` INT)  BEGIN
	DECLARE rnk varchar(64) character set utf8;
	DECLARE chn varchar(64) character set utf8 default 'agent';
	IF parsType = 1 then
		SET chn = "lhub";
	ELSE
		SET chn = "agent";
	END IF;
	
    SET rnk = concat("learnhall3_log.rnk4profit",DATE_FORMAT(NOW(),'%Y%m%d'),"_",chn);
    
    SET @tmpYYYY = DATE_FORMAT(NOW(),'%Y');
    
    SET @drop = concat("DROP TABLE IF EXISTS ",rnk);
    PREPARE drop1 FROM @drop;
    EXECUTE drop1;
    
    SET @crt = concat(
    	"CREATE TABLE IF NOT EXISTS ",
    	rnk,
    	" (",
		"`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '排行榜唯一标识',",
		"`indexs` int(11) NOT NULL COMMENT '排序名次',",
		"`type` int(11) NOT NULL COMMENT '0代理商,1学习中心',",
		"`ownerid` int(11) NOT NULL COMMENT '拥有者标识[agentid/lhubid]',",
		"`money` double NOT NULL COMMENT '成交金额',",
		"`bonus` double NOT NULL COMMENT '代理奖金/学中押金',",
		"`royalty` double NOT NULL COMMENT '提成',",
		"PRIMARY KEY (`id`),",
		"UNIQUE KEY `type_ownerid` (`type`,`ownerid`)"
		") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;"
	);
	PREPARE crt1 FROM @crt;
    EXECUTE crt1;
	
	SET @in2 = concat(
	    	"INSERT INTO ",
	    	rnk,
	    	"(`indexs`,`type`,`ownerid`,`money`,`bonus`,`royalty`)",
			"SELECT @rownum:=@rownum+1 AS `index`,tmptb.* FROM (SELECT @rownum:=0) r,"
			);
			
	IF parsType = 1 then
		SET @in2 = concat(
	    	@in2,
			" (SELECT 1 as `type`,`lhubid` as `ownerid`,SUM(`realprice`) as `money`,SUM(`lhubDeposit`) as `bonus`,SUM(`lhubRoyalty`) as `royalty` FROM `orders4profit` `tmp` WHERE `tmp`.`isProfit4Lhub` = true AND `tmp`.`orderNo` IN(SELECT `orderNo` FROM `orders` WHERE `orders`.`type` = 0 AND `orders`.`statusProcess` > 0 AND `orders`.`lasttime` BETWEEN '",@tmpYYYY,"-01-01' AND '",@tmpYYYY,"-12-31 23:59:59') GROUP BY `tmp`.`lhubid` ORDER BY `money` DESC,`tmp`.`lhubid` ASC) tmptb;"
		);
	ELSE
		SET @in2 = concat(
	    	@in2,
			" (SELECT 0 as `type`,`agentid` as `ownerid`,SUM(`realprice`) as `money`,SUM(`agentBonus`) as `bonus`,SUM(`agentRoyalty`) as `royalty` FROM `orders4profit` `tmp` WHERE `tmp`.`isProfit4Agent` = true AND `tmp`.`orderNo` IN(SELECT `orderNo` FROM `orders` WHERE `orders`.`type` = 0 AND `orders`.`statusProcess` > 0 AND `orders`.`lasttime` BETWEEN '",@tmpYYYY,"-01-01' AND '",@tmpYYYY,"-12-31 23:59:59') GROUP BY `tmp`.`agentid` ORDER BY `money` DESC,`tmp`.`agentid` ASC) tmptb;"
		);
	END IF;
	
	PREPARE in2 FROM @in2;
    EXECUTE in2;
END$$

DROP PROCEDURE IF EXISTS `pro_rnkProfit4Del`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_rnkProfit4Del` ()  BEGIN
	DECLARE b int default 0;
	DECLARE deltb varchar(100) character set utf8 default '';
    DECLARE cur CURSOR FOR SELECT CONCAT('DROP TABLE IF EXISTS ', table_name, ';' ) AS dtbname FROM information_schema.tables WHERE (table_name LIKE 'rnk4profit%') AND (table_name NOT LIKE CONCAT("%",DATE_FORMAT(NOW(),'%Y%m%d'),"%") AND table_name NOT LIKE CONCAT("%",DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 1 DAY),'%Y%m%d'),"%") AND table_name NOT LIKE CONCAT("%",DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 2 DAY),'%Y%m%d'),"%") AND table_name NOT LIKE CONCAT("%",DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 3 DAY),'%Y%m%d'),"%"));
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET b = 1;
    OPEN cur;
	FETCH cur INTO deltb;
	WHILE b <> 1 DO
		SET @sql1 = concat("",deltb);
		PREPARE sql1 FROM @sql1;
		EXECUTE sql1;
		DEALLOCATE PREPARE sql1;
		SET b = 0;
		FETCH cur INTO deltb;
    END WHILE;
    CLOSE cur;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `lgid` varchar(32) NOT NULL COMMENT '登陆账号',
  `phone` varchar(32) NOT NULL COMMENT '手机号码11位',
  `email` varchar(128) NOT NULL,
  `lgpwd` varchar(32) NOT NULL COMMENT '登录密码',
  `type` int(11) NOT NULL COMMENT '类型[1管理员,2学习中心,3代理商,4学生,5程序,6美工]',
  `status` int(11) NOT NULL COMMENT '状态[0正常,1已被删除]',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `lasttime` datetime NOT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `lgid` (`lgid`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `adcourses`
--

DROP TABLE IF EXISTS `adcourses`;
CREATE TABLE IF NOT EXISTS `adcourses` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程管理表',
  `departid` int(11) NOT NULL COMMENT '题库分类ID',
  `nmMajor` varchar(256) NOT NULL COMMENT '专业',
  `nmLevel` varchar(256) NOT NULL COMMENT '层次',
  `nmSub` varchar(256) NOT NULL COMMENT '科目',
  `nmArea` varchar(256) NOT NULL COMMENT '范围',
  `profitAgent` int(11) NOT NULL COMMENT '代理商利润百分百',
  `profitOwner` int(11) NOT NULL COMMENT '题库拥有者利润百分比',
  `deposit` int(11) NOT NULL COMMENT '质量押金百分比',
  `bonus` int(11) NOT NULL COMMENT '代理商奖金百分比',
  `wrong` int(11) NOT NULL COMMENT '错误',
  `program` int(11) NOT NULL COMMENT '程序提成(RMB:元)',
  `art` int(11) NOT NULL COMMENT '美工提成(RMB:元)',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`cid`),
  KEY `departid` (`departid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `adprivilege`
--

DROP TABLE IF EXISTS `adprivilege`;
CREATE TABLE IF NOT EXISTS `adprivilege` (
  `prid` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(30) NOT NULL COMMENT '权限名',
  `pdesc` varchar(200) NOT NULL DEFAULT '' COMMENT '描述',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT '请求地址',
  `parentid` int(11) NOT NULL DEFAULT '0' COMMENT '父级权限',
  PRIMARY KEY (`prid`),
  KEY `fk_privilege` (`parentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `adqdepartment`
--

DROP TABLE IF EXISTS `adqdepartment`;
CREATE TABLE IF NOT EXISTS `adqdepartment` (
  `did` int(11) NOT NULL AUTO_INCREMENT COMMENT '大分类标识',
  `name` varchar(16) NOT NULL COMMENT '名字',
  PRIMARY KEY (`did`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `aduser`
--

DROP TABLE IF EXISTS `aduser`;
CREATE TABLE IF NOT EXISTS `aduser` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `accountid` int(11) NOT NULL COMMENT '帐号标识',
  `uname` varchar(50) NOT NULL COMMENT '用户名',
  `powerids` varchar(128) NOT NULL COMMENT '权限IDS(0表示所有权限)',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname` (`uname`),
  UNIQUE KEY `accountid` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `agent`
--

DROP TABLE IF EXISTS `agent`;
CREATE TABLE IF NOT EXISTS `agent` (
  `agid` int(11) NOT NULL AUTO_INCREMENT,
  `accountid` int(11) NOT NULL COMMENT '帐号标识',
  `uname` varchar(50) NOT NULL COMMENT '代理商名称',
  `code` varchar(64) NOT NULL COMMENT '代理编码(联系号码)',
  `province` varchar(32) NOT NULL COMMENT '省份',
  `city` varchar(32) NOT NULL COMMENT '市',
  `seat` varchar(256) NOT NULL COMMENT '所在地',
  `qq` varchar(128) NOT NULL COMMENT 'QQ邮箱',
  `need` varchar(128) NOT NULL COMMENT '没有要代理的课程,提出的需求',
  `goodness` varchar(256) NOT NULL COMMENT '优势',
  `volume` int(11) NOT NULL COMMENT '成交量',
  `curmoney` double NOT NULL COMMENT '当前佣金',
  `allmoney` double NOT NULL COMMENT '总佣金',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `endtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '代理结束时间点',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '状态:1正常,2待审核,3未通过',
  `examineStatus` int(11) NOT NULL COMMENT '审核状态 0 初始化 1审核中 2审核不通过 3审核通过',
  `examineDes` text NOT NULL COMMENT '审核内容',
  `alipay` varchar(256) NOT NULL COMMENT '支付宝帐号',
  `isVerifyAlipay` bit(1) NOT NULL COMMENT '是否验证支付宝',
  PRIMARY KEY (`agid`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `accountid` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `answer`
--

DROP TABLE IF EXISTS `answer`;
CREATE TABLE IF NOT EXISTS `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `askid` int(11) NOT NULL COMMENT '提问id',
  `customerid` int(11) NOT NULL COMMENT '消费者id（学生id）',
  `content` text NOT NULL COMMENT '回复内容',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `askid_custid` (`askid`,`customerid`),
  KEY `customerid` (`customerid`),
  KEY `askid` (`askid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提问回复';

-- --------------------------------------------------------

--
-- 表的结构 `appraise`
--

DROP TABLE IF EXISTS `appraise`;
CREATE TABLE IF NOT EXISTS `appraise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `appraisetext` text NOT NULL COMMENT '评论语（评论了什么）',
  `kindid` int(11) NOT NULL COMMENT '大套餐id',
  `customerid` int(11) NOT NULL COMMENT '消费者id（学生id）',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `custname` varchar(256) NOT NULL COMMENT '消费者名字',
  `kindname` varchar(256) NOT NULL COMMENT '评论的课程套餐名',
  `reback` text NOT NULL COMMENT '回复',
  `lhubid` int(11) NOT NULL COMMENT '所属学习中心id',
  `score` int(11) NOT NULL COMMENT '评分',
  PRIMARY KEY (`id`),
  KEY `customerid` (`customerid`),
  KEY `kindid` (`kindid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

-- --------------------------------------------------------

--
-- 表的结构 `ask`
--

DROP TABLE IF EXISTS `ask`;
CREATE TABLE IF NOT EXISTS `ask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL COMMENT '  消费者id（学生id）',
  `title` text NOT NULL COMMENT '提问标题',
  `rewardamount` double NOT NULL COMMENT '悬赏金额',
  `expirationtime` datetime NOT NULL COMMENT '过期时间',
  `status` int(11) NOT NULL COMMENT '0新/旧，1已回复，2已采纳',
  `tag` varchar(256) NOT NULL COMMENT '标签',
  `answerid` int(11) NOT NULL COMMENT '采纳的回复id',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `status_opt` int(11) NOT NULL COMMENT '0创建,1删除,2可用',
  PRIMARY KEY (`id`),
  KEY `customerid` (`customerid`),
  KEY `askid` (`answerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `boughtkinds`
--

DROP TABLE IF EXISTS `boughtkinds`;
CREATE TABLE IF NOT EXISTS `boughtkinds` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(256) NOT NULL COMMENT '已购套餐名',
  `customerid` int(11) NOT NULL COMMENT '购买者ID',
  `kindid` int(11) NOT NULL COMMENT '套餐ID',
  `price` double NOT NULL COMMENT '购买价格RMB',
  `kbi` int(11) NOT NULL COMMENT '购买价格考币',
  `num` int(11) NOT NULL COMMENT '可模考的总次数',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `validtime` date NOT NULL COMMENT '截至时间(有效期)',
  `lhubid` int(11) NOT NULL COMMENT '所属学习中心id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customerid_kind` (`customerid`,`kindid`),
  KEY `customerid` (`customerid`),
  KEY `kindid` (`kindid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `cfgs`
--

DROP TABLE IF EXISTS `cfgs`;
CREATE TABLE IF NOT EXISTS `cfgs` (
  `cfgid` int(11) NOT NULL AUTO_INCREMENT COMMENT '常量表标识',
  `name` varchar(32) NOT NULL COMMENT '名',
  `valStr` text NOT NULL COMMENT '字符串值',
  `valInt` int(11) NOT NULL COMMENT 'int值',
  PRIMARY KEY (`cfgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `accountid` int(11) NOT NULL,
  `name` varchar(256) NOT NULL COMMENT '名字',
  `kbiAll` int(11) NOT NULL COMMENT '总考币(历史记录)',
  `kbiUse` int(11) NOT NULL COMMENT '考币(可使用的)',
  `email` varchar(128) NOT NULL COMMENT '邮箱',
  `province` varchar(32) NOT NULL,
  `city` varchar(32) NOT NULL,
  `seat` varchar(128) NOT NULL,
  `headIcon` varchar(128) NOT NULL,
  `descr` text NOT NULL,
  `moneyAll` double NOT NULL COMMENT '总金额(用于统计)',
  `moneyCur` double NOT NULL COMMENT '当前金额(用于计算)',
  `recommendCode` varchar(64) NOT NULL COMMENT '推荐码',
  `alipay` varchar(512) NOT NULL COMMENT '支付宝帐号',
  `alipayRealName` varchar(256) NOT NULL COMMENT '支付宝帐号-实名制认证名',
  `isVerifyAlipay` bit(1) NOT NULL COMMENT '是否验证了支付宝帐号',
  `backAlipay` varchar(512) NOT NULL COMMENT '备份通过验证支付宝帐号',
  `backAlipayName` varchar(256) NOT NULL COMMENT '备份通过真实姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountid` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `errorfeedback`
--

DROP TABLE IF EXISTS `errorfeedback`;
CREATE TABLE IF NOT EXISTS `errorfeedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `examid` int(11) NOT NULL COMMENT '试卷（课程）id ',
  `nmExam` varchar(256) NOT NULL COMMENT '试卷名',
  `optid` int(11) NOT NULL COMMENT '考题id ',
  `opttype` int(11) NOT NULL COMMENT '该题类型1单2多..',
  `optgid` int(11) NOT NULL COMMENT 'opttype为7时,1单2多..',
  `customerid` int(11) NOT NULL COMMENT '消费者id(学生id)',
  `nmCust` varchar(256) NOT NULL COMMENT '学员名称',
  `description` text NOT NULL COMMENT '主要问题',
  `lhubid` int(11) NOT NULL COMMENT '所属学习中心',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `examid` (`examid`),
  KEY `customerid` (`customerid`),
  KEY `lhubid` (`lhubid`),
  KEY `optquestionid` (`optid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='错误反馈';

-- --------------------------------------------------------

--
-- 表的结构 `exam`
--

DROP TABLE IF EXISTS `exam`;
CREATE TABLE IF NOT EXISTS `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `examtypeid` int(11) NOT NULL COMMENT '分类ID',
  `name` varchar(128) NOT NULL COMMENT '试卷名称',
  `score` int(11) NOT NULL COMMENT '总分',
  `lhubid` int(11) NOT NULL COMMENT '学习中心ID',
  `examtime` int(11) NOT NULL COMMENT '考试时间(限定做完此试卷的时间,分钟)',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `productid` int(11) NOT NULL COMMENT '产品标识',
  `pro0etpid` int(11) NOT NULL COMMENT '产品试卷类型中间表标识',
  `descstr` text NOT NULL COMMENT '试卷说明，简介',
  PRIMARY KEY (`id`),
  KEY `examtypeid` (`examtypeid`),
  KEY `lhubid` (`lhubid`),
  KEY `pro0etpid` (`pro0etpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `examcatalog`
--

DROP TABLE IF EXISTS `examcatalog`;
CREATE TABLE IF NOT EXISTS `examcatalog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `examid` int(11) NOT NULL COMMENT '试卷标识',
  `serial` varchar(32) NOT NULL COMMENT '大题序号[一,二,三]',
  `catalogType` int(11) NOT NULL COMMENT '类型[1单,2多,3判断,4填空,5简答,6论述,7案例]',
  `gid` int(11) NOT NULL COMMENT 'catalogType为7时，小分类[1单2多3判4填空5简答6论述]',
  `bigtypes` varchar(256) NOT NULL COMMENT '大题类型[单选，多选..]',
  `isSubjective` bit(1) NOT NULL COMMENT '是否主观题',
  `num` int(11) NOT NULL COMMENT '题数',
  `everyScore` int(11) NOT NULL COMMENT '每题分数',
  `title` text NOT NULL COMMENT '题干',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `parentid` int(11) NOT NULL COMMENT 'catalogType为7时,父级id',
  PRIMARY KEY (`id`),
  KEY `examid` (`examid`),
  KEY `parentid` (`parentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷目录:单选，多选...';

-- --------------------------------------------------------

--
-- 表的结构 `examtype`
--

DROP TABLE IF EXISTS `examtype`;
CREATE TABLE IF NOT EXISTS `examtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '章节练习,历年真题,全真模拟,考前押题,知识要点,ITM辅助',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `exchangermb`
--

DROP TABLE IF EXISTS `exchangermb`;
CREATE TABLE IF NOT EXISTS `exchangermb` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `type` int(11) NOT NULL COMMENT '0学员,1lhub,2agent',
  `makerid` int(11) NOT NULL COMMENT '操作者ID',
  `nmMaker` varchar(512) NOT NULL COMMENT '操作者姓名',
  `alipay` varchar(256) NOT NULL COMMENT '支付宝帐号',
  `alipayName` varchar(512) NOT NULL COMMENT '支付宝帐号姓名',
  `reason` text NOT NULL COMMENT '申请缘由',
  `moneyCur` double NOT NULL COMMENT '当前可提现金',
  `monyeApply` double NOT NULL COMMENT '申请金额',
  `monyeReal` double NOT NULL COMMENT '真实获得金额',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0正常,1删除',
  `batchNo` varchar(64) NOT NULL DEFAULT '' COMMENT '批量付款批次号',
  `statusOpt` int(11) NOT NULL DEFAULT '0' COMMENT '0审核中,1取消中,2已取消,3拒绝，4同意,5成功',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `content` text NOT NULL COMMENT '回调内容',
  `lasttime` datetime NOT NULL COMMENT '最后一次操作时间',
  PRIMARY KEY (`id`),
  KEY `type_makerid` (`type`,`makerid`),
  KEY `batchNo` (`batchNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='申请兑换人民币';

-- --------------------------------------------------------

--
-- 表的结构 `itms4auto`
--

DROP TABLE IF EXISTS `itms4auto`;
CREATE TABLE IF NOT EXISTS `itms4auto` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '智能组题模版标识',
  `kindid` int(11) NOT NULL COMMENT '套餐标识',
  `num4radio` int(11) NOT NULL COMMENT '单选',
  `num4chbox` int(11) NOT NULL COMMENT '多选',
  `num4judge` int(11) NOT NULL COMMENT '判断',
  `num4fill` int(11) NOT NULL COMMENT '填空',
  `num4jd` int(11) NOT NULL COMMENT '简答',
  `num4luns` int(11) NOT NULL COMMENT '论述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kindid` (`kindid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='智能组题的模版';

-- --------------------------------------------------------

--
-- 表的结构 `itms4day`
--

DROP TABLE IF EXISTS `itms4day`;
CREATE TABLE IF NOT EXISTS `itms4day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) NOT NULL COMMENT '考试人',
  `kindid` int(11) NOT NULL COMMENT '考试套餐',
  `rightrate` int(11) NOT NULL COMMENT '正确率',
  `createtime` date NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `custid_kindid` (`custid`,`kindid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `kind`
--

DROP TABLE IF EXISTS `kind`;
CREATE TABLE IF NOT EXISTS `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `kclassid` int(11) NOT NULL COMMENT '套餐分类标识',
  `nmKClass` varchar(256) NOT NULL COMMENT '套餐名',
  `coursid` int(11) NOT NULL COMMENT '课程标识',
  `productid` int(11) NOT NULL COMMENT '产品id',
  `nmProduct` varchar(256) NOT NULL COMMENT '产品名',
  `lhubid` int(11) NOT NULL COMMENT '学习中心id',
  `nmLhub` varchar(256) NOT NULL COMMENT '学习中心名',
  `price` double NOT NULL COMMENT '标价',
  `discount` double NOT NULL COMMENT '优惠后价格',
  `kbi` int(11) NOT NULL COMMENT '考币价格',
  `validity` int(11) NOT NULL COMMENT '有效期,天',
  `imgurl` varchar(128) NOT NULL COMMENT '图标',
  `buycount` int(11) NOT NULL COMMENT '销量，购买量',
  `visit` int(11) NOT NULL COMMENT '人气,访问量',
  `praise` int(11) NOT NULL COMMENT '口碑，点赞',
  `examtypes` varchar(255) NOT NULL COMMENT '有那些试卷类型表',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `numExercises` int(11) NOT NULL COMMENT '章节练习数量',
  `numZhenti` int(11) NOT NULL COMMENT '历年真题数量',
  `numSimulation` int(11) NOT NULL COMMENT '全真模拟数量',
  `numVast` int(11) NOT NULL COMMENT '绝胜押题数量',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `isHasITMS` bit(1) NOT NULL COMMENT '是否保护ITMS辅助',
  `examids` text NOT NULL COMMENT '拥有试卷ids:1,2,3,',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kc_pr_lhub` (`kclassid`,`productid`,`lhubid`),
  KEY `kclassid` (`kclassid`),
  KEY `productid` (`productid`),
  KEY `lhubid` (`lhubid`),
  KEY `productid_lhubid` (`productid`,`lhubid`),
  KEY `coursid` (`coursid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `kindclass`
--

DROP TABLE IF EXISTS `kindclass`;
CREATE TABLE IF NOT EXISTS `kindclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(128) NOT NULL COMMENT '套餐分类名称',
  `imgurl` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `learnhub`
--

DROP TABLE IF EXISTS `learnhub`;
CREATE TABLE IF NOT EXISTS `learnhub` (
  `lhid` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库唯一标识',
  `accountid` int(11) NOT NULL COMMENT '帐号标识',
  `name` varchar(64) NOT NULL COMMENT '学习中心名称',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型(1个人,2机构)',
  `codeid` varchar(20) NOT NULL COMMENT '身份证号码',
  `province` varchar(32) NOT NULL COMMENT '省份',
  `city` varchar(32) NOT NULL COMMENT '市',
  `seat` varchar(256) NOT NULL COMMENT '地址',
  `qq` varchar(128) NOT NULL COMMENT 'QQ邮箱',
  `uname` varchar(50) NOT NULL COMMENT '联系人名称',
  `salesmode` int(11) NOT NULL COMMENT '销售模式(1代理,2自行)',
  `img4jg` varchar(256) NOT NULL COMMENT '机构图片正面',
  `volume` int(11) NOT NULL COMMENT '成交量',
  `moneyAll` double NOT NULL COMMENT '总成交金额',
  `moneyCur` double NOT NULL COMMENT '当前成交额',
  `isselfadmin` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否自主管理权限',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '状态:1正常,2待审核,3未通过',
  `tiku` int(11) NOT NULL COMMENT '题库提成%',
  `quality` int(11) NOT NULL COMMENT '质量提成%',
  `wrong` int(11) NOT NULL COMMENT '错误率%',
  `examineStatus` int(11) NOT NULL COMMENT '审核状态 0 初始化 1审核中 2审核不通过 3审核通过',
  `examineDes` text NOT NULL COMMENT '审核内容',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `imgr4Cover` varchar(512) NOT NULL COMMENT '宣传图片地址',
  `descr` text NOT NULL COMMENT '描述',
  `alipay` varchar(256) NOT NULL COMMENT '支付宝帐号',
  `isVerifyAlipay` bit(1) NOT NULL COMMENT '是否验证支付宝',
  `img4idface` varchar(512) NOT NULL COMMENT '身份证正面',
  `img4idback` varchar(256) NOT NULL COMMENT '身份证背面',
  `img4logo` varchar(256) NOT NULL DEFAULT '' COMMENT 'logo标识',
  PRIMARY KEY (`lhid`),
  UNIQUE KEY `codeid` (`codeid`),
  UNIQUE KEY `accountid` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `msg`
--

DROP TABLE IF EXISTS `msg`;
CREATE TABLE IF NOT EXISTS `msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `target` varchar(64) NOT NULL COMMENT '目标[1学生、2代理商、3学习中心]',
  `description` text NOT NULL COMMENT '消息内容',
  `num` int(11) NOT NULL COMMENT '浏览次数',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `accountid` (`target`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息';

-- --------------------------------------------------------

--
-- 表的结构 `openkind4customer`
--

DROP TABLE IF EXISTS `openkind4customer`;
CREATE TABLE IF NOT EXISTS `openkind4customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `customerid` int(11) NOT NULL COMMENT '开通体验用户ID',
  `custname` varchar(256) NOT NULL COMMENT '学员名',
  `kindid` int(11) NOT NULL COMMENT '体验套餐ID',
  `lhubid` int(11) NOT NULL DEFAULT '0' COMMENT '学习中心id',
  `agentid` int(11) NOT NULL DEFAULT '0' COMMENT '代理商id',
  `kbi` int(11) NOT NULL COMMENT '体验考币值',
  `num` int(11) NOT NULL COMMENT '模考的总次数',
  `validity` int(11) NOT NULL DEFAULT '0' COMMENT '有效天数',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `validtime` date NOT NULL COMMENT '截至时间(有效期)',
  `remarks` varchar(256) NOT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态0初始,1,删除,2已使用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `custid_kindid` (`customerid`,`kindid`),
  KEY `customerid` (`customerid`),
  KEY `kindid` (`kindid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开通套餐给用户去体验';

-- --------------------------------------------------------

--
-- 表的结构 `openkind4third`
--

DROP TABLE IF EXISTS `openkind4third`;
CREATE TABLE IF NOT EXISTS `openkind4third` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `kindid` int(11) NOT NULL COMMENT '体验套餐id',
  `lhubid` int(11) NOT NULL DEFAULT '0' COMMENT '学习中心id',
  `agentid` int(11) NOT NULL DEFAULT '0' COMMENT '代理商id',
  `num` int(11) NOT NULL COMMENT '帐号数量',
  `money` double NOT NULL COMMENT '进账金额(第三方给的钱)',
  `nmThird` varchar(256) NOT NULL COMMENT '所属集团客户',
  `nmContact` varchar(256) NOT NULL COMMENT '联系人名',
  `phone` varchar(64) NOT NULL COMMENT '联系电话',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kindid_lhubid_agentid` (`kindid`,`lhubid`,`agentid`),
  KEY `kindid` (`kindid`),
  KEY `phone` (`phone`),
  KEY `lhubid` (`lhubid`),
  KEY `agentid` (`agentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开通套餐4第三方';

-- --------------------------------------------------------

--
-- 表的结构 `optquestion`
--

DROP TABLE IF EXISTS `optquestion`;
CREATE TABLE IF NOT EXISTS `optquestion` (
  `optid` int(11) NOT NULL AUTO_INCREMENT COMMENT '选择题标识',
  `examid` int(11) NOT NULL COMMENT '试卷ID',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型[1单,2多,3判断,4填空,5简答,6论述,7案例]',
  `content` text NOT NULL COMMENT '题的内容',
  `right_2` text NOT NULL COMMENT '正确答案',
  `analyse` text NOT NULL COMMENT '分析',
  `voiceurl` varchar(128) NOT NULL COMMENT '音频地址',
  `videourl` varchar(128) NOT NULL COMMENT '视频地址',
  `position` varchar(256) NOT NULL COMMENT '教材位置',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `examcatalogid` int(11) NOT NULL COMMENT '试卷目录标识',
  `answernum` int(11) NOT NULL COMMENT '答案题数超过4以上才用',
  `gid` int(11) NOT NULL DEFAULT '0' COMMENT 'type下小分类[1单2多3判4填空5简答6论述]',
  `imgPic` varchar(256) NOT NULL COMMENT '图片补充',
  PRIMARY KEY (`optid`),
  KEY `type` (`type`),
  KEY `examid` (`examid`),
  KEY `examid_catalogid` (`examid`,`examcatalogid`),
  KEY `examcatalogid` (`examcatalogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `type` int(11) NOT NULL COMMENT '0套餐,1lhub_test,2agent_test,3考币,4问答',
  `name` varchar(256) NOT NULL COMMENT '购买产生的订单名字',
  `extra_param` varchar(512) NOT NULL COMMENT '额外参数[套餐ids...]',
  `makerid` int(11) NOT NULL COMMENT '操作者ID',
  `price` double NOT NULL COMMENT '价格',
  `kbi` int(11) NOT NULL DEFAULT '0' COMMENT '获得考币',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '订单状态0正常,1删除',
  `statusProcess` int(11) NOT NULL COMMENT '流程状态',
  `nmMaker` varchar(512) NOT NULL COMMENT '操作者姓名',
  `discount` double NOT NULL COMMENT '优惠价格',
  `recommendCode` varchar(64) NOT NULL COMMENT '推荐号',
  `lasttime` datetime NOT NULL COMMENT '最后一次操作时间',
  `orderNo` varchar(64) NOT NULL COMMENT '订单编号唯一',
  `realprice` double NOT NULL COMMENT '实际支付价格',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `kindid` (`extra_param`(255)),
  KEY `type_makeid` (`type`,`makerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- --------------------------------------------------------

--
-- 表的结构 `orders4profit`
--

DROP TABLE IF EXISTS `orders4profit`;
CREATE TABLE IF NOT EXISTS `orders4profit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `orderNo` varchar(64) NOT NULL COMMENT '订单编号唯一',
  `kindid` int(11) NOT NULL COMMENT '套餐id',
  `custid` int(11) NOT NULL COMMENT '消费者id(学生id)',
  `lhubid` int(11) NOT NULL COMMENT '学习中心id',
  `agentid` int(11) NOT NULL COMMENT '学习中心id',
  `agentBonus` double NOT NULL COMMENT '代理商奖金',
  `agentRoyalty` double NOT NULL COMMENT '代理商提成',
  `lhubRoyalty` double NOT NULL COMMENT '学习中心题库提成',
  `lhubDeposit` double NOT NULL COMMENT '学习中心质量押金',
  `developRoyalty` double NOT NULL COMMENT '开发提成',
  `artRoyalty` double NOT NULL COMMENT '美工提成',
  `price` double NOT NULL COMMENT '价格',
  `discount` double NOT NULL COMMENT '优惠价格',
  `realprice` double NOT NULL COMMENT '实际支付价格',
  `isProfit4Agent` bit(1) NOT NULL COMMENT '代理商是否提成',
  `isProfit4Lhub` bit(1) NOT NULL COMMENT '学习中心是否提成',
  `status` int(11) NOT NULL COMMENT '0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `orderNo` (`orderNo`),
  KEY `custid` (`custid`),
  KEY `lhubid` (`lhubid`),
  KEY `agentid` (`agentid`),
  KEY `kindid` (`kindid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单_分润记录表';

-- --------------------------------------------------------

--
-- 表的结构 `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `coursesid` int(11) NOT NULL COMMENT '课程ID',
  `name` varchar(128) NOT NULL COMMENT '产品名字',
  `imgurl` varchar(128) NOT NULL COMMENT '图标地址',
  `descr` text NOT NULL COMMENT '描述，详情',
  `lhubid` int(11) NOT NULL COMMENT '学习中心id',
  `status` int(11) NOT NULL COMMENT '产品状态',
  `examineStatus` int(11) NOT NULL COMMENT '审核状态  0 初始化   1审核中  2审核不通过  3审核通过',
  `examineDes` text NOT NULL COMMENT '审核内容',
  `complete` int(11) NOT NULL COMMENT '是否完成  0 未完成  1完成',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `cruces` text NOT NULL COMMENT '知识要点',
  `isRecommend` bit(1) NOT NULL COMMENT '是否推荐',
  `lastTime4Recommend` datetime NOT NULL COMMENT '最后推荐时间',
  `protection` text NOT NULL COMMENT '消费者保障',
  `lasttime` datetime NOT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`),
  KEY `coursesid` (`coursesid`),
  KEY `lhubid` (`lhubid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `product0examtype`
--

DROP TABLE IF EXISTS `product0examtype`;
CREATE TABLE IF NOT EXISTS `product0examtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `lhubid` int(11) NOT NULL COMMENT '学习中心标识',
  `productid` int(11) NOT NULL COMMENT '产品标识',
  `examtypeid` int(11) NOT NULL COMMENT '类型ID',
  `buymoney` double NOT NULL COMMENT '购买价格',
  `friend` int(11) NOT NULL COMMENT '朋友数量',
  `kbi` int(11) NOT NULL COMMENT '购买后拥有考币数量',
  `status` int(11) NOT NULL COMMENT '0正常，1删除',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `lhubid_prid_etypeid` (`lhubid`,`productid`,`examtypeid`),
  KEY `lhubid` (`lhubid`),
  KEY `productid` (`productid`),
  KEY `examtypeid` (`examtypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `record4orders`
--

DROP TABLE IF EXISTS `record4orders`;
CREATE TABLE IF NOT EXISTS `record4orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `orderNo` varchar(64) NOT NULL COMMENT '订单号',
  `tradeNo` varchar(64) NOT NULL COMMENT '第三方订单号',
  `content` text NOT NULL COMMENT '回调内容',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `record4seeanswer`
--

DROP TABLE IF EXISTS `record4seeanswer`;
CREATE TABLE IF NOT EXISTS `record4seeanswer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `askid` int(11) NOT NULL COMMENT '问题标识',
  `custid` int(11) NOT NULL COMMENT '查看人标识',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `askid` (`askid`,`custid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录查看满意答案';

-- --------------------------------------------------------

--
-- 表的结构 `recordanswer`
--

DROP TABLE IF EXISTS `recordanswer`;
CREATE TABLE IF NOT EXISTS `recordanswer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `examid` int(11) NOT NULL COMMENT '考卷id',
  `nmExam` varchar(256) NOT NULL COMMENT '试卷名字',
  `customerid` int(11) NOT NULL COMMENT '考试学员标识',
  `custname` varchar(128) NOT NULL COMMENT '考生名',
  `score` int(11) NOT NULL COMMENT '得分',
  `avecorrectrate` int(11) NOT NULL COMMENT '平均正确率',
  `lasttime` datetime NOT NULL COMMENT '上次时间',
  `num` int(11) NOT NULL COMMENT '模考次数',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `anwers` blob NOT NULL COMMENT '所有答案的记录',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `costTimes` int(11) NOT NULL COMMENT '完成花费时间',
  `catalog` blob NOT NULL COMMENT '考试目录结构',
  `examType` int(11) NOT NULL COMMENT '[1章节,2历年,3.全真..]',
  `score4ques` mediumblob NOT NULL COMMENT '记录试卷每题的得分',
  `kindid` int(11) NOT NULL COMMENT '所属套餐ID',
  `lens4exam` int(11) NOT NULL COMMENT '试题总数量',
  `lens4right` int(11) NOT NULL COMMENT '正确总数量',
  `courseid` int(11) NOT NULL COMMENT '课程标识',
  `lhubid` int(11) NOT NULL DEFAULT '0' COMMENT '所属学习中心',
  PRIMARY KEY (`id`),
  UNIQUE KEY `examid_cust_id` (`examid`,`customerid`),
  KEY `examid` (`examid`),
  KEY `customerid` (`customerid`),
  KEY `courseid` (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录在线答题';

-- --------------------------------------------------------

--
-- 表的结构 `recordfee4customer`
--

DROP TABLE IF EXISTS `recordfee4customer`;
CREATE TABLE IF NOT EXISTS `recordfee4customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `type` int(11) NOT NULL DEFAULT '2' COMMENT '1收入,2支出',
  `custid` int(11) NOT NULL COMMENT '学员ID',
  `custname` varchar(256) NOT NULL COMMENT '学员名',
  `val` double NOT NULL COMMENT '数值',
  `cont` varchar(512) NOT NULL COMMENT '购买描述内容(64个汉字)',
  `kindid` int(11) NOT NULL COMMENT '购买套餐ID',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `type_custid` (`type`,`custid`),
  KEY `type_custid_kindid` (`type`,`custid`,`kindid`),
  KEY `custid` (`custid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户金额支出,收入记录';

-- --------------------------------------------------------

--
-- 表的结构 `recordkbi4customer`
--

DROP TABLE IF EXISTS `recordkbi4customer`;
CREATE TABLE IF NOT EXISTS `recordkbi4customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `type` int(11) NOT NULL DEFAULT '2' COMMENT '1收入,2支出',
  `custid` int(11) NOT NULL COMMENT '学员ID',
  `custname` varchar(256) NOT NULL COMMENT '学员名',
  `val` double NOT NULL COMMENT '数值',
  `cont` varchar(512) NOT NULL COMMENT '购买描述内容(64个汉字)',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `type_custid` (`type`,`custid`),
  KEY `custid` (`custid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户考币支出,收入记录()';

-- --------------------------------------------------------

--
-- 表的结构 `recordques4exam`
--

DROP TABLE IF EXISTS `recordques4exam`;
CREATE TABLE IF NOT EXISTS `recordques4exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customid` int(11) NOT NULL COMMENT '考试者',
  `questionid` int(11) NOT NULL COMMENT '问题id',
  `catalog4Exam` int(11) NOT NULL COMMENT '所属试卷类型[单,多,判断]]',
  `numError` int(11) NOT NULL DEFAULT '1' COMMENT '出错次数',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customid_quesid` (`customid`,`questionid`),
  KEY `customid` (`customid`),
  KEY `questionid` (`questionid`),
  KEY `customid_catalog` (`customid`,`catalog4Exam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录考试数出错题';

-- --------------------------------------------------------

--
-- 表的结构 `rnk4profit`
--

DROP TABLE IF EXISTS `rnk4profit`;
CREATE TABLE IF NOT EXISTS `rnk4profit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `indexs` int(11) NOT NULL COMMENT '排序名次',
  `type` int(11) NOT NULL COMMENT '0代理商,1学习中心',
  `ownerid` int(11) NOT NULL COMMENT '拥有者标识[agentid/lhubid]',
  `money` double NOT NULL COMMENT '成交金额',
  `bonus` double NOT NULL COMMENT '代理奖金/学中押金',
  `royalty` double NOT NULL COMMENT '提成',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_ownerid` (`type`,`ownerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润排行榜表';

-- --------------------------------------------------------

--
-- 表的结构 `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE IF NOT EXISTS `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `customerid` int(11) NOT NULL COMMENT '消费者id（学生id）',
  `kindid` int(11) NOT NULL COMMENT '大套餐id',
  `agentCode` varchar(256) NOT NULL COMMENT '代理商推荐号',
  PRIMARY KEY (`id`),
  KEY `custid_kindid` (`customerid`,`kindid`),
  KEY `customerid` (`customerid`),
  KEY `kindid` (`kindid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

--
-- 限制导出的表
--

--
-- 限制表 `adcourses`
--
ALTER TABLE `adcourses`
  ADD CONSTRAINT `fk_2depart_id` FOREIGN KEY (`departid`) REFERENCES `adqdepartment` (`did`);

--
-- 限制表 `adprivilege`
--
ALTER TABLE `adprivilege`
  ADD CONSTRAINT `fk_privilege` FOREIGN KEY (`parentid`) REFERENCES `adprivilege` (`prid`);

--
-- 限制表 `aduser`
--
ALTER TABLE `aduser`
  ADD CONSTRAINT `fk_u2a_id` FOREIGN KEY (`accountid`) REFERENCES `account` (`id`);

--
-- 限制表 `agent`
--
ALTER TABLE `agent`
  ADD CONSTRAINT `fk_ag2ac_id` FOREIGN KEY (`accountid`) REFERENCES `account` (`id`);

--
-- 限制表 `appraise`
--
ALTER TABLE `appraise`
  ADD CONSTRAINT `fk_ap2cus_id` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `fk_ap2kind_id` FOREIGN KEY (`kindid`) REFERENCES `kind` (`id`);

--
-- 限制表 `boughtkinds`
--
ALTER TABLE `boughtkinds`
  ADD CONSTRAINT `fk_bk2cus_id` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `fk_bk2kind_id` FOREIGN KEY (`kindid`) REFERENCES `kind` (`id`);

--
-- 限制表 `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `fk_cus2acc_id` FOREIGN KEY (`accountid`) REFERENCES `account` (`id`);

--
-- 限制表 `errorfeedback`
--
ALTER TABLE `errorfeedback`
  ADD CONSTRAINT `fk_err2opt_id` FOREIGN KEY (`optid`) REFERENCES `optquestion` (`optid`),
  ADD CONSTRAINT `fk_fb2cus_id` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `fk_fb2exam_id` FOREIGN KEY (`examid`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `fk_fb2lhub_id` FOREIGN KEY (`lhubid`) REFERENCES `learnhub` (`lhid`);

--
-- 限制表 `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `fk_exam2type_id` FOREIGN KEY (`examtypeid`) REFERENCES `examtype` (`id`);

--
-- 限制表 `examcatalog`
--
ALTER TABLE `examcatalog`
  ADD CONSTRAINT `catalog2exam_id` FOREIGN KEY (`examid`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `fk_ec_parentid` FOREIGN KEY (`parentid`) REFERENCES `examcatalog` (`id`);

--
-- 限制表 `kind`
--
ALTER TABLE `kind`
  ADD CONSTRAINT `fk_kind2course_id` FOREIGN KEY (`coursid`) REFERENCES `adcourses` (`cid`),
  ADD CONSTRAINT `fk_kind2kc_id` FOREIGN KEY (`kclassid`) REFERENCES `kindclass` (`id`),
  ADD CONSTRAINT `fk_kind2lhub_id` FOREIGN KEY (`lhubid`) REFERENCES `learnhub` (`lhid`),
  ADD CONSTRAINT `fk_kind2pro_id` FOREIGN KEY (`productid`) REFERENCES `product` (`id`);

--
-- 限制表 `learnhub`
--
ALTER TABLE `learnhub`
  ADD CONSTRAINT `fk_lhub2acc_id` FOREIGN KEY (`accountid`) REFERENCES `account` (`id`);

--
-- 限制表 `openkind4customer`
--
ALTER TABLE `openkind4customer`
  ADD CONSTRAINT `open_cust_id` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `open_kind_id` FOREIGN KEY (`kindid`) REFERENCES `kind` (`id`);

--
-- 限制表 `openkind4third`
--
ALTER TABLE `openkind4third`
  ADD CONSTRAINT `fk_third_agid` FOREIGN KEY (`agentid`) REFERENCES `agent` (`agid`),
  ADD CONSTRAINT `fk_third_kindid` FOREIGN KEY (`kindid`) REFERENCES `kind` (`id`),
  ADD CONSTRAINT `fk_third_lhubid` FOREIGN KEY (`lhubid`) REFERENCES `learnhub` (`lhid`);

--
-- 限制表 `optquestion`
--
ALTER TABLE `optquestion`
  ADD CONSTRAINT `ques_ecata_id` FOREIGN KEY (`examcatalogid`) REFERENCES `examcatalog` (`id`);

--
-- 限制表 `orders4profit`
--
ALTER TABLE `orders4profit`
  ADD CONSTRAINT `fk_ord_agentid` FOREIGN KEY (`agentid`) REFERENCES `agent` (`agid`),
  ADD CONSTRAINT `fk_ord_custid` FOREIGN KEY (`custid`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `fk_ord_kid` FOREIGN KEY (`kindid`) REFERENCES `kind` (`id`),
  ADD CONSTRAINT `fk_ord_lhubid` FOREIGN KEY (`lhubid`) REFERENCES `learnhub` (`lhid`);

--
-- 限制表 `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_pro2co_id` FOREIGN KEY (`coursesid`) REFERENCES `adcourses` (`cid`),
  ADD CONSTRAINT `fk_pro2lhub_id` FOREIGN KEY (`lhubid`) REFERENCES `learnhub` (`lhid`);

--
-- 限制表 `product0examtype`
--
ALTER TABLE `product0examtype`
  ADD CONSTRAINT `pe2examtypeid` FOREIGN KEY (`examtypeid`) REFERENCES `examtype` (`id`),
  ADD CONSTRAINT `pe2lhubid` FOREIGN KEY (`lhubid`) REFERENCES `learnhub` (`lhid`),
  ADD CONSTRAINT `pe2productid` FOREIGN KEY (`productid`) REFERENCES `product` (`id`);

--
-- 限制表 `recordanswer`
--
ALTER TABLE `recordanswer`
  ADD CONSTRAINT `fk_r2e_id` FOREIGN KEY (`examid`) REFERENCES `exam` (`id`);

--
-- 限制表 `recordques4exam`
--
ALTER TABLE `recordques4exam`
  ADD CONSTRAINT `queserr_cust_id` FOREIGN KEY (`customid`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `queserr_opt_id` FOREIGN KEY (`questionid`) REFERENCES `optquestion` (`optid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
