SELECT TripID
FROM kiel_import_complete
WHERE Latitude >= 56.26
GROUP BY TripID