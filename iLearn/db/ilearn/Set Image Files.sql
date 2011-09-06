UPDATE `Student` SET `stuPhoto` = LOAD_FILE('/tmp/no-image-selected.png') 
WHERE stuStatus = 'Active' AND `stuPhoto` IS NULL;