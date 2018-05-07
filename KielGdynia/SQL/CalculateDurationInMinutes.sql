UPDATE kiel_import_complete
SET TripDurationInMinutes = HOUR(TripDuration) * 60 + MINUTE(TripDuration);
