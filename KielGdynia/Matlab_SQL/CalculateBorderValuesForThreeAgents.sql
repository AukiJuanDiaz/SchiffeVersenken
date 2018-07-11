/* Kiel-Gdynia:
Agent 1: 12.26, Percentile ~32% (Kiel bis Rostock)
Agent 2: 14.6, Percentile ~64% (Rostock bis Bornholm/Polnische Grenze)
Agent 3: 19.65 Percentile 100 (Bornholm/Polnische Grenze bis Gdynia) */

SET @LeftBorder = 10.1;
SET @RightBorder = 12.26;

UPDATE kiel_import_complete, (SELECT dp.TripID AS ID, dp.Longitude, dp.Latitude AS AgentLatitude, dp.TimeDate AS AgentTime, dp.SOG AS AgentSOG, dp.COG AS AgentCOG
FROM kiel_import_complete AS dp
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM kiel_import_complete
		WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder
		GROUP BY TripID) AS b ON dp.TripID = b.TripID AND dp.Longitude = b.LongitudeMax
GROUP BY dp.TripID) AS t
SET kiel_import_complete.AgentLatitude = t.AgentLatitude,
	kiel_import_complete.AgentTime = t.AgentTime,
	kiel_import_complete.AgentSOG = t.AgentSOG,
	kiel_import_complete.AgentCOG = t.AgentCOG
WHERE kiel_import_complete.TripID = t.ID AND kiel_import_complete.Longitude >= @LeftBorder AND kiel_import_complete.Longitude < @RightBorder;

SET @LeftBorder = 12.26;
SET @RightBorder = 14.60;

UPDATE kiel_import_complete, (SELECT dp.TripID AS ID, dp.Longitude, dp.Latitude AS AgentLatitude, dp.TimeDate AS AgentTime, dp.SOG AS AgentSOG, dp.COG AS AgentCOG
FROM kiel_import_complete AS dp
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM kiel_import_complete
		WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder
		GROUP BY TripID) AS b ON dp.TripID = b.TripID AND dp.Longitude = b.LongitudeMax
GROUP BY dp.TripID) AS t
SET kiel_import_complete.AgentLatitude = t.AgentLatitude,
	kiel_import_complete.AgentTime = t.AgentTime,
	kiel_import_complete.AgentSOG = t.AgentSOG,
	kiel_import_complete.AgentCOG = t.AgentCOG
WHERE kiel_import_complete.TripID = t.ID AND kiel_import_complete.Longitude >= @LeftBorder AND kiel_import_complete.Longitude < @RightBorder;



/* For the last sector, not the most eastern but the latest datapoint will be taken*/
SET @LeftBorder = 14.60;
SET @RightBorder = 20.0;

UPDATE kiel_import_complete, (SELECT dp.TripID AS ID, dp.Longitude, dp.Latitude AS AgentLatitude, dp.TimeDate AS AgentTime, dp.SOG AS AgentSOG, dp.COG AS AgentCOG
FROM kiel_import_complete AS dp
INNER JOIN (
		SELECT TripID, MAX(TimeDate) AS TimeLatest
		FROM kiel_import_complete
		WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder
		GROUP BY TripID) AS b ON dp.TripID = b.TripID AND dp.TimeDate = b.TimeLatest
GROUP BY dp.TripID) AS t
SET kiel_import_complete.AgentLatitude = t.AgentLatitude,
	kiel_import_complete.AgentTime = t.AgentTime,
	kiel_import_complete.AgentSOG = t.AgentSOG,
	kiel_import_complete.AgentCOG = t.AgentCOG
WHERE kiel_import_complete.TripID = t.ID AND kiel_import_complete.Longitude >= @LeftBorder AND kiel_import_complete.Longitude < @RightBorder;

UPDATE kiel_import_complete
SET AgentRemainingTravelTimeInMinutes = HOUR(TIMEDIFF(AgentTime, TimeDate)) * 60 + MINUTE( TIMEDIFF(AgentTime, TimeDate))