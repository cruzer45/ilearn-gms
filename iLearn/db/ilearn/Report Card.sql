SELECT `Student`.*, `Grade`.*
FROM `Grade`
INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.stuID
WHERE `Grade`.graStatus = 'Active'