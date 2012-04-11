SELECT `clsID`,`Class`.`clsCode`, `subID`, `Subject`.`subCode`, `subStaffCode`
FROM `Class`
INNER JOIN `ClassSubjects` ON `Class`.`clsCode` = `ClassSubjects`.`clsCode`
INNER JOIN `Subject` ON `Subject`.subID = `ClassSubjects`.`subCode`
WHERE `Subject`.`subCode` = '3G1AGRIC'