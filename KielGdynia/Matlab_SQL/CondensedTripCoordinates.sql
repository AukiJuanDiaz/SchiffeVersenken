SELECT TripID, Latitude, Longitude
FROM kiel_import_complete
GROUP BY TripID, Latitude, Longitude;