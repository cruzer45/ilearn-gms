SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`),`stuDOB`, `stuGender`, `stuNonBelizean`,`stuRepeating`, `stuClsCode`
FROM `Student`
WHERE `stuStatus` = 'Active'
ORDER BY `stuClsCode`, CONCAT_WS(' ',`stuFirstName`,`stuLastName`)