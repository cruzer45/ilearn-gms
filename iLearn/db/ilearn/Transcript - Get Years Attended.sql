SELECT DISTINCT syName, yrgraClsCode
FROM SchoolYear
INNER JOIN Grade_Year_Average ON yrgraSchYear = syID
WHERE yrgraStuID = 1
ORDER BY syID