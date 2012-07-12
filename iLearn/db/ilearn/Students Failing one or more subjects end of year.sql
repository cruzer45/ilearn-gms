select distinct CONCAT_WS(', ', stuLastName, stuFirstName) as 'student' ,yrgraClsCode, yrgraSubCode,yrgraYearAverage
from Grade_Year_Average 
inner join Student on Student.stuID = Grade_Year_Average.yrgraStuID
where yrgraYearAverage < 60 
and yrgraClsCode not like '4%'  
order by yrgraYearAverage desc