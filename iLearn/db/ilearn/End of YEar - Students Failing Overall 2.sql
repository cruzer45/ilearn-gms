select  distinct CONCAT_WS(' ',Student.stuFirstName,Student.stuLastName), yrgraClsCode, yrgraYrAvg
from Grade_Year_Average
inner join Student on Student.stuID = Grade_Year_Average.yrgraStuID
where yrgraYrAvg < 60
and yrgraClsCode like '4%'