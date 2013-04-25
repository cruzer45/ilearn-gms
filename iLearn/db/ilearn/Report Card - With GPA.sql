SELECT `Student`.`stuID`,
		`Student`.`stuFirstName`,
		`Student`.`stuLastName`,
		`Student`.`stuClsCode`, 
		`Subject`.subName,
		`Subject`.`subStaffCode`, 
		`Subject`.`subCredits`,
		`Grade`.*, 
		COALESCE(SUM(`Demerits`.`demerits`),0) AS 'demerits',
		COALESCE(SUM(`RollCall`.`rolAbsent`),0)  AS 'rolAbsent', 
		COALESCE(SUM(`RollCall`.`rolTardy`),0) AS 'rolLate', 
		COALESCE(SUM(Merits.merits),0) AS 'merits',
		`Term`.`trmShortName`, 
		`clsHomeRoom` , 
		`graAvgMid`,
		graAvgFinal,
		`graAvgGPA`
FROM `Grade`
INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.stuID
INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode
INNER JOIN `Term` ON `Grade`.`graTrmCode` = `Term`.`trmID`
LEFT JOIN `Grade_Average` ON `Grade_Average`.`graAvgStuID` = `Grade`.`graStuID`
LEFT JOIN `Demerits` ON `Demerits`.`demStuID` = `Grade`.`graStuID` 
					AND `Demerits`.`demTermID` = `Grade`.`graTrmCode` 
					AND `Demerits`.`demStatus` = 'Active'
LEFT JOIN `RollCall` ON `RollCall`.`rolStuID` = `Grade`.`graStuID` 
					AND `RollCall`.`rolTrmCode` = `Grade`.`graTrmCode` 
					AND `RollCall`.`rolStatus` = 'Active'
LEFT JOIN Merits on Merits.merStuID = Student.stuID
						AND Term.trmID = Merits.merTermID
LEFT JOIN `Class` ON `Class`.`clsCode` = `Student`.`stuClsCode`
WHERE `Grade`.graStatus = 'Active' 
		AND Grade_Average.graAvgStatus = 'Active' 
		AND Term.trmID = 
--		AND `Student`.`stuClsCode` LIKE $P{class}
GROUP BY `Student`.`stuID`,`graSubCode`
ORDER BY `stuClsCode`,`stuID`, `Subject`.subName