/*
Clean-Up, bevor wir die richtige Berechnung durchführen

UPDATE data_prepared
SET AgentLatitude = NULL;
*/


/*
Abfrage der Daten an der Grenze, die man im SubQuery setzt, je Trip

Kleinerer Mangel: Bei mehreren Einträgen auf der Grenze den letzten nehmen. Wie?
*/

SELECT dp.TripID AS ID, dp.Longitude, dp.Latitude AS AgentLatitude, dp.TimeDate AS AgentTime, dp.SOG AS AgentSOG, dp.COG AS AgentCOG
FROM data_prepared AS dp
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM data_prepared
		WHERE Longitude < 9.0
		GROUP BY TripID) AS b ON dp.TripID = b.TripID AND dp.Longitude = b.LongitudeMax
GROUP BY dp.TripID;

/*
Update der neuen Spalten. In der neuen Spalte jeweils nur innerhalb der Longitude-Grenze updaten.
*/

SET @LeftBorder = 8.8;
SET @RightBorder = 9.0;

UPDATE data_prepared, (SELECT dp.TripID AS ID, dp.Longitude, dp.Latitude AS AgentLatitude, dp.TimeDate AS AgentTime, dp.SOG AS AgentSOG, dp.COG AS AgentCOG
FROM data_prepared AS dp
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM data_prepared
		WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder
		GROUP BY TripID) AS b ON dp.TripID = b.TripID AND dp.Longitude = b.LongitudeMax
GROUP BY dp.TripID) AS t
SET data_prepared.AgentLatitude = t.AgentLatitude,
	data_prepared.AgentTime = t.AgentTime,
	data_prepared.AgentSOG = t.AgentSOG,
	data_prepared.AgentCOG = t.AgentCOG
WHERE data_prepared.TripID = t.ID AND data_prepared.Longitude >= @LeftBorder AND data_prepared.Longitude < @RightBorder;