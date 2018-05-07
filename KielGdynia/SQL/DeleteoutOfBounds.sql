DELETE FROM kiel_import_complete
WHERE TripID IN (SELECT p.TripID
					  FROM (SELECT *
					  		  FROM kiel_import_complete
					  		  WHERE (Longitude <= 9.86) || (Longitude >= 20.0) || (Latitude >= 56.26)
					  		  GROUP BY TripID) as p)
					  
