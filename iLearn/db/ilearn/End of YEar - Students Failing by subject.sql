select CONCAT_WS(' ',Student.stuFirstName,Student.stuLastName), yrgraClsCode, yrgraSubCode, yrgraYearAverage
from Grade_Year_Average
inner join Student on Student.stuID = Grade_Year_Average.yrgraStuID
where yrgraYearAverage < 60