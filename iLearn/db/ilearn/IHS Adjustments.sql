UPDATE `Grade` SET `graLetterGrade` = 'P', `graRemark` = 'Pass' WHERE `graSubCode` LIKE '%ESPART%' AND `graFinal` >= 60;

DELETE FROM `Grade` WHERE `graSubCode` LIKE '%REM. MATH%' AND `graFinal` = 0;

UPDATE `Subject` SET `subCredits` = 0 WHERE `subCode` LIKE '%ESPART%';

UPDATE `Grade` SET `graGPA` = 4, `graRemark` = 'Excellence', `graLetterGrade` = 'A' WHERE ROUND(`graFinal`) >= 95;
UPDATE `Grade` SET `graGPA` = 3.75, `graRemark` = 'Excellence', `graLetterGrade` = 'A-' WHERE ROUND(`graFinal`) >= 90 AND ROUND(`graFinal`) < 95;
UPDATE `Grade` SET `graGPA` = 3.5, `graRemark` = 'Exceeds Standards', `graLetterGrade` = 'B+'  WHERE ROUND(`graFinal`) >= 85 AND ROUND(`graFinal`) < 90;
UPDATE `Grade` SET `graGPA` = 3, `graRemark` = 'Exceeds Standards' , `graLetterGrade` = 'B' WHERE ROUND(`graFinal`) >= 80 AND ROUND(`graFinal`) < 85;
UPDATE `Grade` SET `graGPA` = 2.5, `graRemark` = 'Meeting Standards', `graLetterGrade` = 'C+'  WHERE ROUND(`graFinal`) >= 75 AND ROUND(`graFinal`) < 80;
UPDATE `Grade` SET `graGPA` = 2, `graRemark` = 'Meeting Standards' , `graLetterGrade` = 'C' WHERE ROUND(`graFinal`) >= 70 AND ROUND(`graFinal`) < 75;
UPDATE `Grade` SET `graGPA` = 1.5, `graRemark` = 'Passing' , `graLetterGrade` = 'D+' WHERE ROUND(`graFinal`) >= 60 AND ROUND(`graFinal`) < 70;
UPDATE `Grade` SET `graGPA` = 1, `graRemark` = 'Passing' , `graLetterGrade` = 'D' WHERE ROUND(`graFinal`) >= 60 AND ROUND(`graFinal`) < 65;
UPDATE `Grade` SET `graGPA` = 0, `graRemark` = 'No Credit', `graLetterGrade` = 'F'  WHERE ROUND(`graFinal`) >= 0 AND ROUND(`graFinal`) < 60;

UPDATE `Grade` SET `graLetterGrade` = 'P', `graRemark` = 'Pass' WHERE `graSubCode` LIKE '%ESPART%' AND ROUND(`graFinal`) >= 60;

UPDATE `Grade` SET `graLetterGrade` = 'F', `graRemark` = 'Fail' WHERE `graSubCode` LIKE '%ESPART%' AND ROUND(`graFinal`) < 60;