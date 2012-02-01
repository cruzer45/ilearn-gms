SELECT `Student`.`stuID`,`Student`.`stuFirstName`,`Student`.`stuLastName`,`Student`.`stuClsCode`, `Subject`.subName,`Subject`.`subStaffCode`, `Subject`.`subCredits`,`Grade`.*, SUM(`Demerits`.`demerits`) AS 'demerits', COUNT(`RollCall`.`rolAbsent`) AS 'rolAbsent', `Term`.`trmShortName`, `clsHomeRoom` , `graAvgFinal`,`graAvgGPA`
FROM `Grade`
INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.stuID
INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode
INNER JOIN `Term` ON `Grade`.`graTrmCode` = `Term`.`trmID`
LEFT JOIN `Grade_Average` ON `Grade_Average`.`graAvgStuID` = `Grade`.`graStuID`
LEFT JOIN `Demerits` ON `Demerits`.`demStuID` = `Grade`.`graStuID` AND `Demerits`.`demTermID` = `Grade`.`graTrmCode` AND `Demerits`.`demStatus` = 'Active'
LEFT JOIN `RollCall` ON `RollCall`.`rolStuID` = `Grade`.`graStuID` AND `RollCall`.`rolTrmCode` = `Grade`.`graTrmCode` AND `RollCall`.`rolStatus` = 'Active'
LEFT JOIN `Class` ON `Class`.`clsCode` = `Student`.`stuClsCode`
WHERE `Grade`.graStatus = 'Active'
GROUP BY `Student`.`stuID`,`graSubCode`
ORDER BY `stuClsCode`,`stuID`, `Subject`.subName