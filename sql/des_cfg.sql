-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-07-21 08:16:03
-- 服务器版本： 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `learnhall3_cfg`
--
CREATE DATABASE IF NOT EXISTS `learnhall3_cfg` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `learnhall3_cfg`;
--
-- Database: `learnhall3_design`
--
CREATE DATABASE IF NOT EXISTS `learnhall3_design` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `learnhall3_design`;

-- --------------------------------------------------------

--
-- 表的结构 `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
`id` int(11) NOT NULL COMMENT '标识',
  `lgid` varchar(32) NOT NULL COMMENT '登陆账号',
  `phone` varchar(32) NOT NULL COMMENT '手机号码11位',
  `lgpwd` varchar(32) NOT NULL COMMENT '登录密码',
  `type` int(11) NOT NULL COMMENT '类型[1管理员,2学习中心,3代理商,4学生,5程序,6美工]',
  `status` int(11) NOT NULL COMMENT '状态[0正常,1已被删除]',
  `alipay` varchar(128) NOT NULL COMMENT '支付宝帐号',
  `moneyall` double NOT NULL COMMENT '总金额(RMB)',
  `moneyuse` double NOT NULL COMMENT '可用金额(RMB)',
  `moneyfrozen` double NOT NULL COMMENT '冻结金额(RMB)',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `adcourses`
--

DROP TABLE IF EXISTS `adcourses`;
CREATE TABLE IF NOT EXISTS `adcourses` (
`cid` int(11) NOT NULL COMMENT '课程管理表',
  `departid` int(11) NOT NULL COMMENT '题库分类ID',
  `majorid` int(11) NOT NULL COMMENT '专业ID',
  `levelid` int(11) NOT NULL COMMENT '层次ID',
  `subid` int(11) NOT NULL COMMENT '科目ID',
  `areaid` int(11) NOT NULL COMMENT '范围ID',
  `profitAgent` int(11) NOT NULL COMMENT '代理商利润百分百',
  `profitOwner` int(11) NOT NULL COMMENT '题库拥有者利润百分比',
  `deposit` int(11) NOT NULL COMMENT '质量押金百分比',
  `bonus` int(11) NOT NULL COMMENT '代理商奖金百分比',
  `wrong` int(11) NOT NULL COMMENT '错误',
  `program` int(11) NOT NULL COMMENT '程序提成(RMB:元)',
  `art` int(11) NOT NULL COMMENT '美工提成(RMB:元)',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `adprivilege`
--

DROP TABLE IF EXISTS `adprivilege`;
CREATE TABLE IF NOT EXISTS `adprivilege` (
`prid` int(11) NOT NULL COMMENT '标识',
  `name` varchar(30) NOT NULL COMMENT '权限名',
  `pdesc` varchar(200) NOT NULL DEFAULT '' COMMENT '描述',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT '请求地址',
  `parentid` int(11) NOT NULL DEFAULT '0' COMMENT '父级权限'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `adqarea`
--

DROP TABLE IF EXISTS `adqarea`;
CREATE TABLE IF NOT EXISTS `adqarea` (
`aid` int(11) NOT NULL COMMENT '考试范围',
  `name` varchar(128) NOT NULL COMMENT '值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `adqdepartment`
--

DROP TABLE IF EXISTS `adqdepartment`;
CREATE TABLE IF NOT EXISTS `adqdepartment` (
`did` int(11) NOT NULL COMMENT '大分类标识',
  `name` varchar(16) NOT NULL COMMENT '名字'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `adqlevel`
--

DROP TABLE IF EXISTS `adqlevel`;
CREATE TABLE IF NOT EXISTS `adqlevel` (
`lid` int(11) NOT NULL COMMENT '层次',
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `adqmajor`
--

DROP TABLE IF EXISTS `adqmajor`;
CREATE TABLE IF NOT EXISTS `adqmajor` (
`mid` int(11) NOT NULL COMMENT '专业',
  `name` varchar(32) NOT NULL COMMENT '值',
  `departid` int(11) NOT NULL COMMENT '分类ID',
  `imgurl` varchar(128) NOT NULL COMMENT '图片地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `adqsubject`
--

DROP TABLE IF EXISTS `adqsubject`;
CREATE TABLE IF NOT EXISTS `adqsubject` (
`sid` int(11) NOT NULL COMMENT '科目',
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `aduser`
--

DROP TABLE IF EXISTS `aduser`;
CREATE TABLE IF NOT EXISTS `aduser` (
`uid` int(11) NOT NULL COMMENT '标识',
  `accountid` int(11) NOT NULL COMMENT '帐号标识',
  `uname` varchar(50) NOT NULL COMMENT '用户名',
  `powerids` varchar(128) NOT NULL COMMENT '权限IDS(0表示所有权限)',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `agent`
--

DROP TABLE IF EXISTS `agent`;
CREATE TABLE IF NOT EXISTS `agent` (
`agid` int(11) NOT NULL,
  `accountid` int(11) NOT NULL COMMENT '帐号标识',
  `uname` varchar(50) NOT NULL COMMENT '代理商名称',
  `code` varchar(64) NOT NULL COMMENT '代理编码(联系号码)',
  `province` varchar(32) NOT NULL COMMENT '省份',
  `city` varchar(32) NOT NULL COMMENT '市',
  `seat` varchar(256) NOT NULL COMMENT '所在地',
  `qq` varchar(32) NOT NULL COMMENT 'QQ号码',
  `need` varchar(128) NOT NULL COMMENT '没有要代理的课程,提出的需求',
  `goodness` varchar(256) NOT NULL COMMENT '优势',
  `volume` int(11) NOT NULL COMMENT '成交量',
  `curmoney` double NOT NULL COMMENT '当前佣金',
  `allmoney` double NOT NULL COMMENT '总佣金',
  `agtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '时间点（创建,提交）',
  `endtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '代理结束时间点',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '状态:1正常,2待审核,3未通过',
  `examineStatus` int(11) NOT NULL COMMENT '审核状态 0 初始化 2审核中 3 审核不通过 4审核通过',
  `examineDes` text NOT NULL COMMENT '审核内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `appraise`
--

DROP TABLE IF EXISTS `appraise`;
CREATE TABLE IF NOT EXISTS `appraise` (
`id` int(11) NOT NULL COMMENT '标识',
  `appraisetext` text NOT NULL COMMENT '评论语（评论了什么）',
  `kindid` int(11) NOT NULL COMMENT '大套餐id',
  `customerid` int(11) NOT NULL COMMENT '消费者id（学生id）',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `custname` varchar(256) NOT NULL COMMENT '消费者名字',
  `kindname` varchar(256) NOT NULL COMMENT '评论的课程套餐名',
  `reback` text NOT NULL COMMENT '回复',
  `lhubid` int(11) NOT NULL COMMENT '所属学习中心id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `ask`
--

DROP TABLE IF EXISTS `ask`;
CREATE TABLE IF NOT EXISTS `ask` (
`id` int(11) NOT NULL COMMENT '标识',
  `putquestionid` int(11) NOT NULL COMMENT '提问id',
  `customerid` int(11) NOT NULL COMMENT '消费者id（学生id）',
  `asktext` text NOT NULL COMMENT '回复内容',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提问回复' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `boughtkinds`
--

DROP TABLE IF EXISTS `boughtkinds`;
CREATE TABLE IF NOT EXISTS `boughtkinds` (
`id` int(11) NOT NULL COMMENT '标识',
  `name` varchar(256) NOT NULL COMMENT '已购套餐名',
  `customerid` int(11) NOT NULL COMMENT '购买者ID',
  `kindid` int(11) NOT NULL COMMENT '套餐ID',
  `price` double NOT NULL COMMENT '购买价格RMB',
  `kbi` int(11) NOT NULL COMMENT '购买价格考币',
  `num` int(11) NOT NULL COMMENT '可模考的总次数',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `validtime` date NOT NULL COMMENT '截至时间(有效期)',
  `lhubid` int(11) NOT NULL COMMENT '所属学习中心id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `cfgs`
--

DROP TABLE IF EXISTS `cfgs`;
CREATE TABLE IF NOT EXISTS `cfgs` (
`cfgid` int(11) NOT NULL COMMENT '常量表标识',
  `name` varchar(32) NOT NULL COMMENT '名',
  `valStr` text NOT NULL COMMENT '字符串值',
  `valInt` int(11) NOT NULL COMMENT 'int值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
`id` int(11) NOT NULL COMMENT '标识',
  `accountid` int(11) NOT NULL,
  `name` varchar(256) NOT NULL COMMENT '名字',
  `kbiAll` int(11) NOT NULL COMMENT '总考币(历史记录)',
  `kbiUse` int(11) NOT NULL COMMENT '考币(可使用的)',
  `email` varchar(128) NOT NULL COMMENT '邮箱',
  `province` varchar(32) NOT NULL,
  `city` varchar(32) NOT NULL,
  `seat` varchar(128) NOT NULL,
  `headIcon` varchar(128) NOT NULL,
  `descr` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `errorfeedback`
--

DROP TABLE IF EXISTS `errorfeedback`;
CREATE TABLE IF NOT EXISTS `errorfeedback` (
`id` int(11) NOT NULL COMMENT '标识',
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
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='错误反馈' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `exam`
--

DROP TABLE IF EXISTS `exam`;
CREATE TABLE IF NOT EXISTS `exam` (
`id` int(11) NOT NULL COMMENT '标识',
  `examtypeid` int(11) NOT NULL COMMENT '分类ID',
  `name` varchar(128) NOT NULL COMMENT '试卷名称',
  `score` int(11) NOT NULL COMMENT '总分',
  `lhubid` int(11) NOT NULL COMMENT '学习中心ID',
  `examtime` int(11) NOT NULL COMMENT '考试时间(限定做完此试卷的时间,分钟)',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `productid` int(11) NOT NULL COMMENT '产品标识',
  `pro0etpid` int(11) NOT NULL COMMENT '产品试卷类型中间表标识',
  `descstr` text NOT NULL COMMENT '试卷说明，简介'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `examcatalog`
--

DROP TABLE IF EXISTS `examcatalog`;
CREATE TABLE IF NOT EXISTS `examcatalog` (
`id` int(11) NOT NULL COMMENT '标识',
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
  `parentid` int(11) NOT NULL COMMENT 'catalogType为7时,父级id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷目录:单选，多选...' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `examtype`
--

DROP TABLE IF EXISTS `examtype`;
CREATE TABLE IF NOT EXISTS `examtype` (
`id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL COMMENT '章节练习,历年真题,全真模拟,考前押题,知识要点,ITM辅助'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `itms4auto`
--

DROP TABLE IF EXISTS `itms4auto`;
CREATE TABLE IF NOT EXISTS `itms4auto` (
`id` int(11) NOT NULL COMMENT '智能组题模版标识',
  `kindid` int(11) NOT NULL COMMENT '套餐标识',
  `num4radio` int(11) NOT NULL COMMENT '单选',
  `num4chbox` int(11) NOT NULL COMMENT '多选',
  `num4judge` int(11) NOT NULL COMMENT '判断',
  `num4fill` int(11) NOT NULL COMMENT '填空',
  `num4jd` int(11) NOT NULL COMMENT '简答',
  `num4luns` int(11) NOT NULL COMMENT '论述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='智能组题的模版' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `itms4day`
--

DROP TABLE IF EXISTS `itms4day`;
CREATE TABLE IF NOT EXISTS `itms4day` (
`id` int(11) NOT NULL,
  `custid` int(11) NOT NULL COMMENT '考试人',
  `kindid` int(11) NOT NULL COMMENT '考试套餐',
  `rightrate` int(11) NOT NULL COMMENT '正确率',
  `createtime` date NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `kind`
--

DROP TABLE IF EXISTS `kind`;
CREATE TABLE IF NOT EXISTS `kind` (
`id` int(11) NOT NULL COMMENT '标识',
  `kclassid` int(11) NOT NULL COMMENT '套餐分类标识',
  `nmKClass` varchar(256) NOT NULL COMMENT '套餐名',
  `departid` int(11) NOT NULL COMMENT '课程类别标识',
  `nmDepart` varchar(256) NOT NULL COMMENT '课程类别',
  `majorId` int(11) NOT NULL COMMENT '专业ID',
  `nmMajor` varchar(256) NOT NULL COMMENT '专业名',
  `subId` int(11) NOT NULL COMMENT '科目ID',
  `nmSub` varchar(256) NOT NULL COMMENT '科目名',
  `levId` int(11) NOT NULL COMMENT '层次ID',
  `nmLev` varchar(256) NOT NULL COMMENT '层次名',
  `arearId` int(11) NOT NULL COMMENT '考试范围ID',
  `nmArear` varchar(256) NOT NULL COMMENT '考试范围名',
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
  `examids` text NOT NULL COMMENT '拥有试卷ids:1,2,3,'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `kindclass`
--

DROP TABLE IF EXISTS `kindclass`;
CREATE TABLE IF NOT EXISTS `kindclass` (
`id` int(11) NOT NULL COMMENT '标识',
  `name` varchar(128) NOT NULL COMMENT '套餐分类名称',
  `imgurl` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `learnhub`
--

DROP TABLE IF EXISTS `learnhub`;
CREATE TABLE IF NOT EXISTS `learnhub` (
`lhid` int(11) NOT NULL COMMENT '数据库唯一标识',
  `accountid` int(11) NOT NULL COMMENT '帐号标识',
  `name` varchar(64) NOT NULL COMMENT '学习中心名称',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型(1个人,2机构)',
  `codeid` varchar(20) NOT NULL COMMENT '身份证号码',
  `province` varchar(32) NOT NULL COMMENT '省份',
  `city` varchar(32) NOT NULL COMMENT '市',
  `seat` varchar(256) NOT NULL COMMENT '地址',
  `qq` varchar(32) NOT NULL COMMENT 'QQ号码',
  `uname` varchar(50) NOT NULL COMMENT '联系人名称',
  `salesmode` int(11) NOT NULL COMMENT '销售模式(1代理,2自行)',
  `codeheads` varchar(128) NOT NULL COMMENT '身份证正面',
  `codetails` varchar(128) NOT NULL COMMENT '身份证背面',
  `volume` int(11) NOT NULL COMMENT '成交量',
  `allmoney` double NOT NULL COMMENT '成交金额',
  `isselfadmin` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否自主管理权限',
  `validitytime` datetime NOT NULL COMMENT '有效期时间',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '状态:1正常,2待审核,3未通过',
  `tiku` int(11) NOT NULL COMMENT '题库提成%',
  `quality` int(11) NOT NULL COMMENT '质量提成%',
  `wrong` int(11) NOT NULL COMMENT '错误率%',
  `examineStatus` int(11) NOT NULL COMMENT '审核状态 0 初始化 2审核中 3 审核不通过 4审核通过',
  `examineDes` text NOT NULL COMMENT '审核内容',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `imgr4Cover` varchar(512) NOT NULL COMMENT '宣传图片地址',
  `descr` text NOT NULL COMMENT '描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `msg`
--

DROP TABLE IF EXISTS `msg`;
CREATE TABLE IF NOT EXISTS `msg` (
`id` int(11) NOT NULL COMMENT '标识',
  `target` varchar(64) NOT NULL COMMENT '目标[1学生、2代理商、3学习中心]',
  `description` text NOT NULL COMMENT '消息内容',
  `num` int(11) NOT NULL COMMENT '浏览次数',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `openkind4customer`
--

DROP TABLE IF EXISTS `openkind4customer`;
CREATE TABLE IF NOT EXISTS `openkind4customer` (
`id` int(11) NOT NULL COMMENT '标识',
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
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态0初始,1,删除,2已使用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开通套餐给用户去体验' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `openkind4third`
--

DROP TABLE IF EXISTS `openkind4third`;
CREATE TABLE IF NOT EXISTS `openkind4third` (
`id` int(11) NOT NULL COMMENT '标识',
  `kindid` int(11) NOT NULL COMMENT '体验套餐id',
  `lhubid` int(11) NOT NULL DEFAULT '0' COMMENT '学习中心id',
  `agentid` int(11) NOT NULL DEFAULT '0' COMMENT '代理商id',
  `num` int(11) NOT NULL COMMENT '帐号数量',
  `money` double NOT NULL COMMENT '进账金额(第三方给的钱)',
  `nmThird` varchar(256) NOT NULL COMMENT '所属集团客户',
  `nmContact` varchar(256) NOT NULL COMMENT '联系人名',
  `phone` varchar(64) NOT NULL COMMENT '联系电话',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开通套餐4第三方' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `optquestion`
--

DROP TABLE IF EXISTS `optquestion`;
CREATE TABLE IF NOT EXISTS `optquestion` (
`optid` int(11) NOT NULL COMMENT '选择题标识',
  `examid` int(11) NOT NULL COMMENT '试卷ID',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型[1单,2多,3判断,4填空,5简答,6论述,7案例]',
  `title` text NOT NULL COMMENT '标题',
  `A` text NOT NULL COMMENT 'A选项',
  `right_2` text NOT NULL COMMENT '正确答案',
  `analyse` text NOT NULL COMMENT '分析',
  `voiceurl` varchar(128) NOT NULL COMMENT '音频地址',
  `videourl` varchar(128) NOT NULL COMMENT '视频地址',
  `position` varchar(256) NOT NULL COMMENT '教材位置',
  `status` int(11) NOT NULL COMMENT '状态0正常,1删除',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `examcatalogid` int(11) NOT NULL COMMENT '试卷目录标识',
  `answernum` int(11) NOT NULL COMMENT '答案题数超过4以上才用',
  `gid` int(11) NOT NULL DEFAULT '0' COMMENT 'type下小分类[1单2多3判4填空5简答6论述]'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
`id` int(11) NOT NULL COMMENT '标识',
  `name` varchar(256) NOT NULL COMMENT '购买产生的订单名字',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '订单类型[1.RMB购买,2.考币购买]',
  `kindid` int(11) NOT NULL COMMENT '大套餐id',
  `lhubid` int(11) NOT NULL COMMENT '学习中心id',
  `agentid` int(11) NOT NULL COMMENT '代理商id',
  `customerid` int(11) NOT NULL COMMENT '消费者id(学生id)',
  `price` double NOT NULL COMMENT '价格',
  `kbi` int(11) NOT NULL DEFAULT '0' COMMENT '考币值',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '订单状态（1初始化，2支付成功，3完成，）',
  `mnAgent` double NOT NULL COMMENT '代理商佣金',
  `bonusAgent` double NOT NULL COMMENT '代理商奖金',
  `mnQBank` double NOT NULL COMMENT '题库提成',
  `mnDeposit` double NOT NULL COMMENT '质量押金',
  `mnDevelop` double NOT NULL COMMENT '开发提成',
  `mnArt` double NOT NULL COMMENT '美工提成',
  `statusProcess` int(11) NOT NULL COMMENT '流程状态',
  `custname` varchar(256) NOT NULL COMMENT '消费者名字',
  `discount` double NOT NULL COMMENT '优惠价格',
  `agentCode` varchar(256) NOT NULL COMMENT '代理商推荐号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
`id` int(11) NOT NULL COMMENT '标识',
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
  `lastTime4Recommend` datetime NOT NULL COMMENT '最后推荐时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `product0examtype`
--

DROP TABLE IF EXISTS `product0examtype`;
CREATE TABLE IF NOT EXISTS `product0examtype` (
`id` int(11) NOT NULL COMMENT '唯一标识',
  `lhubid` int(11) NOT NULL COMMENT '学习中心标识',
  `productid` int(11) NOT NULL COMMENT '产品标识',
  `examtypeid` int(11) NOT NULL COMMENT '类型ID',
  `buymoney` double NOT NULL COMMENT '购买价格',
  `friend` int(11) NOT NULL COMMENT '朋友数量',
  `kbi` int(11) NOT NULL COMMENT '购买后拥有考币数量',
  `status` int(11) NOT NULL COMMENT '0正常，1删除',
  `createtime` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `putquestion`
--

DROP TABLE IF EXISTS `putquestion`;
CREATE TABLE IF NOT EXISTS `putquestion` (
`id` int(11) NOT NULL,
  `customerid` int(11) NOT NULL COMMENT '  消费者id（学生id）',
  `title` text NOT NULL COMMENT '提问标题',
  `rewardamount` double NOT NULL COMMENT '悬赏金额',
  `expirationtime` datetime NOT NULL COMMENT '过期时间',
  `status` int(11) NOT NULL COMMENT '状态（0初始化，1已回复，2已采纳）',
  `tag` varchar(256) NOT NULL COMMENT '标签',
  `askid` int(11) NOT NULL COMMENT '采纳的回复id',
  `createtime` datetime NOT NULL DEFAULT '2015-04-01 00:00:00' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `recordanswer`
--

DROP TABLE IF EXISTS `recordanswer`;
CREATE TABLE IF NOT EXISTS `recordanswer` (
`id` int(11) NOT NULL COMMENT '标识',
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
  `departid` int(11) NOT NULL COMMENT '大分类ID',
  `majoryid` int(11) NOT NULL COMMENT '专业分类ID',
  `levid` int(11) NOT NULL COMMENT '级别ID',
  `subid` int(11) NOT NULL COMMENT '科目ID',
  `subname` varchar(256) NOT NULL COMMENT '科目名称',
  `score4ques` mediumblob NOT NULL COMMENT '记录试卷每题的得分',
  `kindid` int(11) NOT NULL COMMENT '所属套餐ID',
  `lens4exam` int(11) NOT NULL COMMENT '试题总数量',
  `lens4right` int(11) NOT NULL COMMENT '正确总数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录在线答题' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `recordfee4customer`
--

DROP TABLE IF EXISTS `recordfee4customer`;
CREATE TABLE IF NOT EXISTS `recordfee4customer` (
`id` int(11) NOT NULL COMMENT '标识',
  `type` int(11) NOT NULL DEFAULT '2' COMMENT '1收入,2支出',
  `custid` int(11) NOT NULL COMMENT '学员ID',
  `custname` varchar(256) NOT NULL COMMENT '学员名',
  `val` double NOT NULL COMMENT '数值',
  `cont` varchar(512) NOT NULL COMMENT '购买描述内容(64个汉字)',
  `kindid` int(11) NOT NULL COMMENT '购买套餐ID',
  `createtime` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户金额支出,收入记录' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `recordkbi4customer`
--

DROP TABLE IF EXISTS `recordkbi4customer`;
CREATE TABLE IF NOT EXISTS `recordkbi4customer` (
`id` int(11) NOT NULL COMMENT '标识',
  `type` int(11) NOT NULL DEFAULT '2' COMMENT '1收入,2支出',
  `custid` int(11) NOT NULL COMMENT '学员ID',
  `custname` varchar(256) NOT NULL COMMENT '学员名',
  `val` double NOT NULL COMMENT '数值',
  `cont` varchar(512) NOT NULL COMMENT '购买描述内容(64个汉字)',
  `createtime` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户考币支出,收入记录()' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `recordques4exam`
--

DROP TABLE IF EXISTS `recordques4exam`;
CREATE TABLE IF NOT EXISTS `recordques4exam` (
`id` int(11) NOT NULL,
  `customid` int(11) NOT NULL COMMENT '考试者',
  `questionid` int(11) NOT NULL COMMENT '问题id',
  `catalog4Exam` int(11) NOT NULL COMMENT '所属试卷类型[单,多,判断]]',
  `numError` int(11) NOT NULL DEFAULT '1' COMMENT '出错次数',
  `createtime` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录考试数出错题' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE IF NOT EXISTS `shoppingcart` (
`id` int(11) NOT NULL COMMENT '标识',
  `customerid` int(11) NOT NULL COMMENT '消费者id（学生id）',
  `kindid` int(11) NOT NULL COMMENT '大套餐id',
  `agentCode` varchar(256) NOT NULL COMMENT '代理商推荐号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车' AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `lgid` (`lgid`), ADD UNIQUE KEY `alipay` (`alipay`), ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `adcourses`
--
ALTER TABLE `adcourses`
 ADD PRIMARY KEY (`cid`), ADD KEY `departid` (`departid`), ADD KEY `majorid` (`majorid`), ADD KEY `levelid` (`levelid`), ADD KEY `subid` (`subid`), ADD KEY `areaid` (`areaid`);

--
-- Indexes for table `adprivilege`
--
ALTER TABLE `adprivilege`
 ADD PRIMARY KEY (`prid`), ADD KEY `fk_privilege` (`parentid`);

--
-- Indexes for table `adqarea`
--
ALTER TABLE `adqarea`
 ADD PRIMARY KEY (`aid`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `adqdepartment`
--
ALTER TABLE `adqdepartment`
 ADD PRIMARY KEY (`did`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `adqlevel`
--
ALTER TABLE `adqlevel`
 ADD PRIMARY KEY (`lid`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `adqmajor`
--
ALTER TABLE `adqmajor`
 ADD PRIMARY KEY (`mid`), ADD UNIQUE KEY `name_departid` (`name`,`departid`), ADD KEY `departid` (`departid`);

--
-- Indexes for table `adqsubject`
--
ALTER TABLE `adqsubject`
 ADD PRIMARY KEY (`sid`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `aduser`
--
ALTER TABLE `aduser`
 ADD PRIMARY KEY (`uid`), ADD UNIQUE KEY `uname` (`uname`), ADD UNIQUE KEY `accountid` (`accountid`);

--
-- Indexes for table `agent`
--
ALTER TABLE `agent`
 ADD PRIMARY KEY (`agid`), ADD UNIQUE KEY `code` (`code`), ADD UNIQUE KEY `accountid` (`accountid`);

--
-- Indexes for table `appraise`
--
ALTER TABLE `appraise`
 ADD PRIMARY KEY (`id`), ADD KEY `customerid` (`customerid`), ADD KEY `kindid` (`kindid`);

--
-- Indexes for table `ask`
--
ALTER TABLE `ask`
 ADD PRIMARY KEY (`id`), ADD KEY `putquestionid` (`putquestionid`), ADD KEY `customerid` (`customerid`);

--
-- Indexes for table `boughtkinds`
--
ALTER TABLE `boughtkinds`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `customerid_kind` (`customerid`,`kindid`), ADD KEY `customerid` (`customerid`), ADD KEY `kindid` (`kindid`);

--
-- Indexes for table `cfgs`
--
ALTER TABLE `cfgs`
 ADD PRIMARY KEY (`cfgid`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `accountid` (`accountid`), ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `errorfeedback`
--
ALTER TABLE `errorfeedback`
 ADD PRIMARY KEY (`id`), ADD KEY `examid` (`examid`), ADD KEY `customerid` (`customerid`), ADD KEY `lhubid` (`lhubid`), ADD KEY `optquestionid` (`optid`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
 ADD PRIMARY KEY (`id`), ADD KEY `examtypeid` (`examtypeid`), ADD KEY `lhubid` (`lhubid`), ADD KEY `pro0etpid` (`pro0etpid`);

--
-- Indexes for table `examcatalog`
--
ALTER TABLE `examcatalog`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `examid_cype_gid` (`examid`,`catalogType`,`gid`), ADD KEY `examid` (`examid`), ADD KEY `parentid` (`parentid`);

--
-- Indexes for table `examtype`
--
ALTER TABLE `examtype`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `itms4auto`
--
ALTER TABLE `itms4auto`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `kindid` (`kindid`);

--
-- Indexes for table `itms4day`
--
ALTER TABLE `itms4day`
 ADD PRIMARY KEY (`id`), ADD KEY `custid_kindid` (`custid`,`kindid`);

--
-- Indexes for table `kind`
--
ALTER TABLE `kind`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `kc_pr_lhub` (`kclassid`,`productid`,`lhubid`), ADD KEY `kclassid` (`kclassid`), ADD KEY `productid` (`productid`), ADD KEY `lhubid` (`lhubid`), ADD KEY `productid_lhubid` (`productid`,`lhubid`), ADD KEY `majorId` (`majorId`);

--
-- Indexes for table `kindclass`
--
ALTER TABLE `kindclass`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `learnhub`
--
ALTER TABLE `learnhub`
 ADD PRIMARY KEY (`lhid`), ADD UNIQUE KEY `codeid` (`codeid`), ADD UNIQUE KEY `accountid` (`accountid`);

--
-- Indexes for table `msg`
--
ALTER TABLE `msg`
 ADD PRIMARY KEY (`id`), ADD KEY `accountid` (`target`);

--
-- Indexes for table `openkind4customer`
--
ALTER TABLE `openkind4customer`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `custid_kindid` (`customerid`,`kindid`), ADD KEY `customerid` (`customerid`), ADD KEY `kindid` (`kindid`);

--
-- Indexes for table `openkind4third`
--
ALTER TABLE `openkind4third`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `kindid_lhubid_agentid` (`kindid`,`lhubid`,`agentid`), ADD KEY `kindid` (`kindid`), ADD KEY `phone` (`phone`), ADD KEY `lhubid` (`lhubid`), ADD KEY `agentid` (`agentid`);

--
-- Indexes for table `optquestion`
--
ALTER TABLE `optquestion`
 ADD PRIMARY KEY (`optid`), ADD KEY `type` (`type`), ADD KEY `examid` (`examid`), ADD KEY `examid_catalogid` (`examid`,`examcatalogid`), ADD KEY `examcatalogid` (`examcatalogid`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
 ADD PRIMARY KEY (`id`), ADD KEY `kindid` (`kindid`), ADD KEY `lhubid` (`lhubid`), ADD KEY `agentid` (`agentid`), ADD KEY `customerid` (`customerid`), ADD KEY `type` (`type`), ADD KEY `type_cust` (`type`,`customerid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
 ADD PRIMARY KEY (`id`), ADD KEY `coursesid` (`coursesid`), ADD KEY `lhubid` (`lhubid`);

--
-- Indexes for table `product0examtype`
--
ALTER TABLE `product0examtype`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `lhubid_prid_etypeid` (`lhubid`,`productid`,`examtypeid`), ADD KEY `lhubid` (`lhubid`), ADD KEY `productid` (`productid`), ADD KEY `examtypeid` (`examtypeid`);

--
-- Indexes for table `putquestion`
--
ALTER TABLE `putquestion`
 ADD PRIMARY KEY (`id`), ADD KEY `customerid` (`customerid`), ADD KEY `askid` (`askid`);

--
-- Indexes for table `recordanswer`
--
ALTER TABLE `recordanswer`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `examid_cust_id` (`examid`,`customerid`), ADD KEY `examid` (`examid`), ADD KEY `customerid` (`customerid`), ADD KEY `d_m_l_s` (`departid`,`majoryid`,`levid`,`subid`);

--
-- Indexes for table `recordfee4customer`
--
ALTER TABLE `recordfee4customer`
 ADD PRIMARY KEY (`id`), ADD KEY `type_custid` (`type`,`custid`), ADD KEY `type_custid_kindid` (`type`,`custid`,`kindid`), ADD KEY `custid` (`custid`);

--
-- Indexes for table `recordkbi4customer`
--
ALTER TABLE `recordkbi4customer`
 ADD PRIMARY KEY (`id`), ADD KEY `type_custid` (`type`,`custid`), ADD KEY `custid` (`custid`);

--
-- Indexes for table `recordques4exam`
--
ALTER TABLE `recordques4exam`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `customid_quesid` (`customid`,`questionid`), ADD KEY `customid` (`customid`), ADD KEY `questionid` (`questionid`), ADD KEY `customid_catalog` (`customid`,`catalog4Exam`);

--
-- Indexes for table `shoppingcart`
--
ALTER TABLE `shoppingcart`
 ADD PRIMARY KEY (`id`), ADD KEY `custid_kindid` (`customerid`,`kindid`), ADD KEY `customerid` (`customerid`), ADD KEY `kindid` (`kindid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `adcourses`
--
ALTER TABLE `adcourses`
MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程管理表';
--
-- AUTO_INCREMENT for table `adprivilege`
--
ALTER TABLE `adprivilege`
MODIFY `prid` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `adqarea`
--
ALTER TABLE `adqarea`
MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试范围';
--
-- AUTO_INCREMENT for table `adqdepartment`
--
ALTER TABLE `adqdepartment`
MODIFY `did` int(11) NOT NULL AUTO_INCREMENT COMMENT '大分类标识';
--
-- AUTO_INCREMENT for table `adqlevel`
--
ALTER TABLE `adqlevel`
MODIFY `lid` int(11) NOT NULL AUTO_INCREMENT COMMENT '层次';
--
-- AUTO_INCREMENT for table `adqmajor`
--
ALTER TABLE `adqmajor`
MODIFY `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业';
--
-- AUTO_INCREMENT for table `adqsubject`
--
ALTER TABLE `adqsubject`
MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '科目';
--
-- AUTO_INCREMENT for table `aduser`
--
ALTER TABLE `aduser`
MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `agent`
--
ALTER TABLE `agent`
MODIFY `agid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `appraise`
--
ALTER TABLE `appraise`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `ask`
--
ALTER TABLE `ask`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `boughtkinds`
--
ALTER TABLE `boughtkinds`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `cfgs`
--
ALTER TABLE `cfgs`
MODIFY `cfgid` int(11) NOT NULL AUTO_INCREMENT COMMENT '常量表标识';
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `errorfeedback`
--
ALTER TABLE `errorfeedback`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `examcatalog`
--
ALTER TABLE `examcatalog`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `examtype`
--
ALTER TABLE `examtype`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `itms4auto`
--
ALTER TABLE `itms4auto`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '智能组题模版标识';
--
-- AUTO_INCREMENT for table `itms4day`
--
ALTER TABLE `itms4day`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `kind`
--
ALTER TABLE `kind`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `kindclass`
--
ALTER TABLE `kindclass`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `learnhub`
--
ALTER TABLE `learnhub`
MODIFY `lhid` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据库唯一标识';
--
-- AUTO_INCREMENT for table `msg`
--
ALTER TABLE `msg`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `openkind4customer`
--
ALTER TABLE `openkind4customer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `openkind4third`
--
ALTER TABLE `openkind4third`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `optquestion`
--
ALTER TABLE `optquestion`
MODIFY `optid` int(11) NOT NULL AUTO_INCREMENT COMMENT '选择题标识';
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `product0examtype`
--
ALTER TABLE `product0examtype`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识';
--
-- AUTO_INCREMENT for table `putquestion`
--
ALTER TABLE `putquestion`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `recordanswer`
--
ALTER TABLE `recordanswer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `recordfee4customer`
--
ALTER TABLE `recordfee4customer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `recordkbi4customer`
--
ALTER TABLE `recordkbi4customer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- AUTO_INCREMENT for table `recordques4exam`
--
ALTER TABLE `recordques4exam`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `shoppingcart`
--
ALTER TABLE `shoppingcart`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识';
--
-- 限制导出的表
--

--
-- 限制表 `adcourses`
--
ALTER TABLE `adcourses`
ADD CONSTRAINT `fk_2area_id` FOREIGN KEY (`areaid`) REFERENCES `adqarea` (`aid`),
ADD CONSTRAINT `fk_2depart_id` FOREIGN KEY (`departid`) REFERENCES `adqdepartment` (`did`),
ADD CONSTRAINT `fk_2level_id` FOREIGN KEY (`levelid`) REFERENCES `adqlevel` (`lid`),
ADD CONSTRAINT `fk_2major_id` FOREIGN KEY (`majorid`) REFERENCES `adqmajor` (`mid`),
ADD CONSTRAINT `fk_2subject_id` FOREIGN KEY (`subid`) REFERENCES `adqsubject` (`sid`);

--
-- 限制表 `adprivilege`
--
ALTER TABLE `adprivilege`
ADD CONSTRAINT `fk_privilege` FOREIGN KEY (`parentid`) REFERENCES `adprivilege` (`prid`);

--
-- 限制表 `adqmajor`
--
ALTER TABLE `adqmajor`
ADD CONSTRAINT `fk_m2depart_id` FOREIGN KEY (`departid`) REFERENCES `adqdepartment` (`did`);

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
ADD CONSTRAINT `fk_kind2kc_id` FOREIGN KEY (`kclassid`) REFERENCES `kindclass` (`id`),
ADD CONSTRAINT `fk_kind2major_id` FOREIGN KEY (`majorId`) REFERENCES `adqmajor` (`mid`),
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
-- 限制表 `orders`
--
ALTER TABLE `orders`
ADD CONSTRAINT `fk_od2agent_id` FOREIGN KEY (`agentid`) REFERENCES `agent` (`agid`),
ADD CONSTRAINT `fk_od2cus_id` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
ADD CONSTRAINT `fk_od2k_id` FOREIGN KEY (`kindid`) REFERENCES `kind` (`id`),
ADD CONSTRAINT `fk_od2lhub_id` FOREIGN KEY (`lhubid`) REFERENCES `learnhub` (`lhid`);

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
