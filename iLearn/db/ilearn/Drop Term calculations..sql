DELETE
FROM `Grade`
WHERE `graTrmCode` = 2;
DELETE
FROM `Grade_Average`
WHERE `graAvgTerm` = 2 ;
DELETE
FROM `Log_Actions`
WHERE `logTrmCode` = '2';