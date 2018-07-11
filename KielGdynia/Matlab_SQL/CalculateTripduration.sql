UPDATE kiel_import_complete
SET TripDuration = TIMEDIFF(EndTime, StartTime)
