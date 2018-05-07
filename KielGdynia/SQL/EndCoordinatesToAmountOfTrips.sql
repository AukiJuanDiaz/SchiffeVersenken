SELECT COUNT( DISTINCT TripID), EndLatitude, EndLongitude
FROM kiel_import_complete
GROUP BY  EndLatitude, EndLongitude