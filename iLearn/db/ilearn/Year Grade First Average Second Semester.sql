UPDATE `Grade_Year_Average` inner JOIN  `Grade`  ON Grade_Year_Average.yrgraStuID = Grade.graStuID
SET yrgraSem2 = graFinal
WHERE graStuID = yrgraStuID AND graSubCode = yrgraSubCode AND graTrmCode = 2