UPDATE kiel_import_complete
SET EndTime = STR_TO_DATE(EndTime, "'%Y-%c-%e %H:%i'")