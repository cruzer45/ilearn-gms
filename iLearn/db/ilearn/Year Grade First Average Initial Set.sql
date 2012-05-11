INSERT INTO `Grade_Year_Average` (yrgraStuID, yrgraClsCode, yrgraSubCode, yrgraSem1)
SELECT DISTINCT graStuID, graClsCode,graSubCode,graFinal
FROM `Grade`
WHERE graTrmCode = 1