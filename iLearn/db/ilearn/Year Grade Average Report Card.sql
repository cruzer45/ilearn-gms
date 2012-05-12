SELECT `Student`.`stuID`,`Student`.`stuFirstName`,`Student`.`stuLastName`,`Student`.`stuClsCode`, `Subject`.subName,`Subject`.`subStaffCode`, `Subject`.`subCredits`,Grade_Year_Average.*,COALESCE(SUM(`Demerits`.`demerits`),0)  AS 'demerits', COUNT(`RollCall`.`rolAbsent`) AS 'rolAbsent',  `clsHomeRoom` 
FROM Grade_Year_Average
INNER JOIN `Student` ON Grade_Year_Average.yrgraStuID = `Student`.stuID
INNER JOIN `Subject` ON `Subject`.`subCode` = Grade_Year_Average.yrgraSubCode

LEFT JOIN `Demerits` ON `Demerits`.`demStuID` = Grade_Year_Average.yrgraStuID AND `Demerits`.`demStatus` = 'Active'
LEFT JOIN `RollCall` ON `RollCall`.`rolStuID` = Grade_Year_Average.yrgraStuID AND  `RollCall`.`rolStatus` = 'Active'
LEFT JOIN `Class` ON `Class`.`clsCode` = `Student`.`stuClsCode`

GROUP BY `Student`.`stuID`,yrgraSubCode
ORDER BY `stuClsCode`,`stuID`, `Subject`.subName