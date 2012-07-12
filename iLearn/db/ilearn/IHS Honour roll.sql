select distinct CONCAT_WS(', ', stuLastName, stuFirstName) as 'student' ,yrgraClsCode, yrgraYrAvg
from Grade_Year_Average 
inner join Student on Student.stuID = Grade_Year_Average.yrgraStuID
where yrgraYrAvg > 80 
and yrgraClsCode not like '4%' 
order by yrgraYrAvg desc