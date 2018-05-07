SELECT * 
FROM kiel_import_complete
WHERE TripID IN (SELECT TripID
					 FROM kiel_import_complete
					 WHERE Longitude >= 20.0
					 GROUP BY TripID)