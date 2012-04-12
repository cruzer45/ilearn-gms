SELECT `demDate`, `demerits`, `demRemarks`, `demActionTaken`, CONCAT_WS(' ',`staFirstName`, `staLastName`) AS 'staff'
FROM `Demerits`
INNER JOIN `Staff` ON `Staff`.`staCode` = `Demerits`.`demStaCode`
WHERE `demStatus` = 'Active' AND `demStuID` = 1 AND `demTermID` = 2 