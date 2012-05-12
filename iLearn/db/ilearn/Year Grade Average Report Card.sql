SELECT `Student`.`stuID`,`Student`.`stuFirstName`,`Student`.`stuLastName`,`Student`.`stuClsCode`, `Subject`.subName,`Subject`.`subStaffCode`, `Subject`.`subCredits`,Grade_Year_Average.*, SUM(`Demerits`.`demerits`) AS 'demerits', COUNT(`RollCall`.`rolAbsent`) AS 'rolAbsent',  `clsHomeRoom` , `graAvgFinal`,`graAvgGPA`
FROM Grade_Year_Average
INNER JOIN `Student` ON Grade_Year_Average.yrgraStuID = `Student`.stuID
INNER JOIN `Subject` ON `Subject`.`subCode` = Grade_Year_Average.yrgraSubCode
--INNER JOIN `Term` ON `Grade`.`graTrmCode` = `Term`.`trmID`
LEFT JOIN `Grade_Average` ON `Grade_Average`.`graAvgStuID` = `Grade`.`graStuID`
LEFT JOIN `Demerits` ON `Demerits`.`demStuID` = `Grade`.`graStuID` AND `Demerits`.`demTermID` = `Grade`.`graTrmCode` AND `Demerits`.`demStatus` = 'Active'
LEFT JOIN `RollCall` ON `RollCall`.`rolStuID` = `Grade`.`graStuID` AND `RollCall`.`rolTrmCode` = `Grade`.`graTrmCode` AND `RollCall`.`rolStatus` = 'Active'
LEFT JOIN `Class` ON `Class`.`clsCode` = `Student`.`stuClsCode`
WHERE `Grade`.graStatus = 'Active' AND `Student`.`stuClsCode` LIKE $P{class}
GROUP BY `Student`.`stuID`,`graSubCode`
ORDER BY `stuClsCode`,`stuID`, `Subject`.subName