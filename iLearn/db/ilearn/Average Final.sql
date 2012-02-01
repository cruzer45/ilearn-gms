SELECT `graStuID`, `graClsCode`,`graTrmCode`, SUM(`subCredits`) as 'credits', SUM(`graFinal`*`subCredits`) AS 'points_earned' , (SUM(`graFinal`*`subCredits`) / SUM(`subCredits`)) as 'grade'
FROM `Grade`
INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode
WHERE  `graSubCode` NOT LIKE '%ESPART%'
GROUP BY `graStuID`, `graClsCode`,`graTrmCode`