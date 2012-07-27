SELECT ROUND(yrgraYearAverage) as 'grade'
FROM Grade_Year_Average
INNER JOIN Subject ON subCode = yrgraSubCode
INNER JOIN SchoolYear ON syID = yrgraSchYear
WHERE yrgraStuID = 1 AND yrgraClsCode = '1A' AND syName = '2011-2012' AND subName = 'Agriculture Science'