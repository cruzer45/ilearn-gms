SELECT CONCAT_WS(' ',`staFirstName`,`staLastName`) AS "Name"
FROM `Staff`
INNER JOIN `User_Staff` ON `User_Staff`.`staID` = `Staff` .`staID`
INNER JOIN `User` ON `User`.`usrID` = `User_Staff`.`userID`
WHERE`usrName` LIKE ''