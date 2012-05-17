DROP TABLE IF EXISTS temp_gpa;
CREATE TABLE temp_gpa
SELECT `yrgraStuID`, `yrgraClsCode`, SUM(`subCredits`) AS 'credits', SUM(`yrgraYearAverage`*`subCredits`) AS 'points_earned', 
(SUM(`yrgraYearAverage`*`subCredits`) / SUM(`subCredits`)) AS 'grade', 
(SUM(`yrgraGPA`*`subCredits`) / SUM(`subCredits`)) AS 'GPA'
FROM `Grade_Year_Average`
INNER JOIN `Subject` ON `Subject`.`subCode` = Grade_Year_Average.yrgraSubCode
GROUP BY `yrgraStuID`, `yrgraClsCode`;

