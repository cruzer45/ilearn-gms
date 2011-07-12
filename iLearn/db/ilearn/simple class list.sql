SELECT `stuID`,`stuSSN`, CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name',`stuClsCode`
FROM `Student`
WHERE `stuStatus` = 'Active'
ORDER BY `stuClsCode` ASC, `Name` ASC