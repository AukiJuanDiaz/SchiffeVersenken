/* Hamburg-Bremerhaven:
Agent 1: Grenze 8.69 Percentile 50 (Bremerhaven bis Cuxhaven)
Agent 2: grenze 9.39 Percentile 75(Cuxhaven bis Drochtersen(Elbe))
Agent 3: Grenze 10 Percentile (Drochtersen(Elbe) bis Hamburg)
Ausgewählt nach Peaks, Percentiles und geographischen Sektoren */

SET @LeftBorder = 7.4;
SET @RightBorder = 8.69;

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

SET @LeftBorder = 8.69;
SET @RightBorder = 9.39;

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

SET @LeftBorder = 9.39;
SET @RightBorder = 10.1;

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