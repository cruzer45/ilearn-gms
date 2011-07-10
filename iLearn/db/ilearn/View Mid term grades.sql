SELECT `Student`.`stuID`, CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS `Name`,`Student`.`stuClsCode`, `Subject`.subName,`Grade`.*
FROM `Grade`
INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.stuID
INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode
WHERE `Grade`.graStatus = 'Active'
ORDER BY `stuClsCode`,`stuID`, `Subject`.subName;