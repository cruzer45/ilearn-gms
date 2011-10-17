SELECT CONCAT_WS(', ',`staLastName`,`staFirstName`) AS 'Teacher', `clsCode`,`subName`, COUNT(`assmtID`)
FROM `Assments`
INNER JOIN `Subject` ON `Subject`.`subCode` = `Assments`.`assmtSubject`
INNER JOIN `Staff` ON `staff`.`staCode` = `Subject`.`subStaffCode`
INNER JOIN `Class` ON `class`.`clsID` = `Assments`.`assmtClassID`
GROUP BY CONCAT_WS(', ',`staLastName`,`staFirstName`), `assmtSubject`
ORDER BY CONCAT_WS(', ',`staLastName`,`staFirstName`), `clsCode`,`assmtSubject`, COUNT(`assmtID`)
