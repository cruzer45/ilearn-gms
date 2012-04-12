SELECT `merDate`,`merits`, `merRemarks`, CONCAT_WS(' ',`staFirstName`, `staLastName`)
FROM `Merits`
INNER JOIN `Staff` ON `Staff`.`staID` = `Merits`.`merStaID`
WHERE `merStatus` = 'Active' AND `merTermID` = 2 AND `merStuID` = ''
