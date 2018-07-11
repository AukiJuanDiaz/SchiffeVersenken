SELECT TripID, Latitude, Longitude
FROM kiel_import_complete
WHERE TripID IN (SELECT TripID
					  FROM kiel_import_complete
					  WHERE Latitude >= 55.450
					  GROUP BY TripID)
GROUP BY TripID, Latitude, Longitude
					  
		