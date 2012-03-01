DELETE FROM `Grade` WHERE `graSubCode` LIKE '%REM. MATH%' AND `graFinal` = 0 AND `graStatus` = 'Active';

UPDATE `Subject` SET `subCredits` = 0 WHERE `subCode` LIKE '%ESPART%' AND `graStatus` = 'Active';

UPDATE `Grade` SET `graGPA` = 4, `graRemark` = 'Excellence', `graLetterGrade` = 'A' WHERE ROUND(`graMid`) >= 95 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 3.75, `graRemark` = 'Excellence', `graLetterGrade` = 'A-' WHERE ROUND(`graMid`) >= 90 AND ROUND(`graMid`) < 95 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 3.5, `graRemark` = 'Exceeds Standards', `graLetterGrade` = 'B+'  WHERE ROUND(`graMid`) >= 85 AND ROUND(`graMid`) < 90 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 3, `graRemark` = 'Exceeds Standards' , `graLetterGrade` = 'B' WHERE ROUND(`graMid`) >= 80 AND ROUND(`graMid`) < 85 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 2.5, `graRemark` = 'Meeting Standards', `graLetterGrade` = 'C+'  WHERE ROUND(`graMid`) >= 75 AND ROUND(`graMid`) < 80 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 2, `graRemark` = 'Meeting Standards' , `graLetterGrade` = 'C' WHERE ROUND(`graMid`) >= 70 AND ROUND(`graMid`) < 75 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 1.5, `graRemark` = 'Passing' , `graLetterGrade` = 'D+' WHERE ROUND(`graMid`) >= 60 AND ROUND(`graMid`) < 70 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 1, `graRemark` = 'Passing' , `graLetterGrade` = 'D' WHERE ROUND(`graMid`) >= 60 AND ROUND(`graMid`) < 65 AND `graStatus` = 'Active';
UPDATE `Grade` SET `graGPA` = 0, `graRemark` = 'No Credit', `graLetterGrade` = 'F'  WHERE ROUND(`graMid`) >= 0 AND ROUND(`graMid`) < 60 AND `graStatus` = 'Active';

UPDATE `Grade` SET `graLetterGrade` = 'P', `graRemark` = 'Pass' WHERE `graSubCode` LIKE '%ESPART%' AND ROUND(`graMid`) >= 60 AND `graStatus` = 'Active';

UPDATE `Grade` SET `graLetterGrade` = 'F', `graRemark` = 'Fail' WHERE `graSubCode` LIKE '%ESPART%' AND ROUND(`graMid`) < 60 AND `graStatus` = 'Active';
