SELECT `stuID`,`stuFirstName`,`stuLastName`,`stuOtherNames`,`stuDOB`,`stuGender`,`stuClsCode`,`stuPhoto`
FROM `Student`
WHERE `stuStatus` = 'Active'
ORDER BY `stuClsCode`, `stuID`