SELECT `graStuID`, `graClsCode`,`graTrmCode`, SUM(`subCredits`) as 'credits', SUM(`graMid`*`subCredits`) AS 'points_earned' , (SUM(`graMid`*`subCredits`) / SUM(`subCredits`)) as 'grade'
FROM `Grade`
INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode
WHERE  `graSubCode` NOT LIKE '%ESPART%'
GROUP BY `graStuID`, `graClsCode`,`graTrmCode`