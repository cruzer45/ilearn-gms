UPDATE `Grade_Year_Average` LEFT JOIN  `Grade`  ON grade_year_average.yrgraStuID = grade.graStuID
SET yrgraSem2 = graFinal
WHERE graStuID = yrgraStuID