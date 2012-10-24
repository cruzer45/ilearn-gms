UPDATE Grade SET graStatus = 'Closed'
WHERE graStatus = 'Active' AND graTrmCode != 3;

UPDATE Grade_Average SET graAvgStatus = 'Closed'
WHERE graAvgStatus = 'Active' AND graAvgTerm != 3;

