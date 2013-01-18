SELECT CONCAT_WS(', ',`staLastName`,`staFirstName`) AS 'Teacher', `clsCode`,`subName`, COUNT(`assmtID`) AS 'Count'
FROM `Assments`
INNER JOIN `Subject` ON `Subject`.`subCode` = `Assments`.`assmtSubject`
INNER JOIN `Staff` ON `Staff`.`staCode` = `Subject`.`subStaffCode`
INNER JOIN `Class` ON `Class`.`clsID` = `Assments`.`assmtClassID`
WHERE `Assments`.assmtTerm = 1
GROUP BY CONCAT_WS(', ',`staLastName`,`staFirstName`), `assmtSubject`
ORDER BY CONCAT_WS(', ',`staLastName`,`staFirstName`), `clsCode`,`assmtSubject`, COUNT(`assmtID`)
