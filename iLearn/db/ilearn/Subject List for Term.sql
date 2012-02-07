SELECT DISTINCT `Grade`.`graSubCode`, `subName`
FROM `Grade`
INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.`graSubCode`
WHERE `graClsCode` = '1A' AND `graTrmCode` = 1