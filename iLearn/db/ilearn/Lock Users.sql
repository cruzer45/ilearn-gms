UPDATE `User` SET `usrStatus` = 'Locked'
WHERE `usrStatus` = 'Active' AND `usrGroup` != 'Administration'