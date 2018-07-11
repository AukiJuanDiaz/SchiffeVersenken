SELECT COUNT( DISTINCT TripID), StartLatitude, StartLongitude
FROM kiel_import_complete
GROUP BY StartLatitude, StartLongitude