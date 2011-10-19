DELETE FROM `grade` WHERE `graSubCode` LIKE '%COUN%';

DELETE FROM `grade` WHERE `graMid` = 0 AND (`graClsCode` LIKE '3%' OR `graClsCode` LIKE '%4');