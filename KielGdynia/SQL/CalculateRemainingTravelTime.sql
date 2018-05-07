UPDATE kiel_import_complete
SET RemainingTravelTime = TIMEDIFF(EndTime, TimeDate);