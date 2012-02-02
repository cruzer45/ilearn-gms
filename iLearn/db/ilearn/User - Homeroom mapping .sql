SELECT `User`.`usrName`,  `Class`.`clsCode`
FROM `User`
INNER JOIN `User_Staff` ON `User_Staff`.`userID` = `User`.`usrID`
INNER JOIN `Staff` ON `Staff`.`staID` = `User_Staff`.`staID`
INNER JOIN `Class` ON `Class`.`clsHomeRoom` = CONCAT_WS(' ',`staFirstName`,`staLastName`)
order by `Class`.`clsCode`