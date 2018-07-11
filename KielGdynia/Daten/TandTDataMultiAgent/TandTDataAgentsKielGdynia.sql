/*Extrahieren von T adn T je Agent, aufgeteilt nach Trips, 20% Test, 80% Training */

/* Kiel-Gdynia:
Agent 1: 12.26, Percentile ~32% (Kiel bis Rostock)
Agent 2: 14.6, Percentile ~64% (Rostock bis Bornholm/Polnische Grenze)
Agent 3: 19.65 Percentile 100 (Bornholm/Polnische Grenze bis Gdynia) */

SET @LeftBorder = 10.1;
SET @RightBorder = 12.26;

SELECT 	dp.AgentLatitude,
			dp.AgentRemainingTravelTimeInMinutes,
			dp.AgentSOG,
			dp.AgentCOG,
			dp.Latitude,
			dp.Longitude,
			dp.WeekdayInt, 
			dp.WeekOfYearInt, 
			dp.HourInt, 
			dp.Length, 
			dp.Breadth, 
			dp.Draught, 
			dp.SOG, 
			dp.COG,
			dp.TripID
FROM kiel_import_complete AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID <= 413683;

SELECT 	dp.AgentLatitude,
			dp.AgentRemainingTravelTimeInMinutes,
			dp.AgentSOG,
			dp.AgentCOG,
			dp.Latitude,
			dp.Longitude,
			dp.WeekdayInt, 
			dp.WeekOfYearInt, 
			dp.HourInt, 
			dp.Length, 
			dp.Breadth, 
			dp.Draught, 
			dp.SOG, 
			dp.COG,
			dp.TripID
FROM kiel_import_complete AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID > 413683;

SET @LeftBorder = 12.26;
SET @RightBorder = 14.60;

SELECT 	dp.AgentLatitude,
			dp.AgentRemainingTravelTimeInMinutes,
			dp.AgentSOG,
			dp.AgentCOG,
			dp.Latitude,
			dp.Longitude,
			dp.WeekdayInt, 
			dp.WeekOfYearInt, 
			dp.HourInt, 
			dp.Length, 
			dp.Breadth, 
			dp.Draught, 
			dp.SOG, 
			dp.COG,
			dp.TripID
FROM kiel_import_complete AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID <= 413683;

SELECT 	dp.AgentLatitude,
			dp.AgentRemainingTravelTimeInMinutes,
			dp.AgentSOG,
			dp.AgentCOG,
			dp.Latitude,
			dp.Longitude,
			dp.WeekdayInt, 
			dp.WeekOfYearInt, 
			dp.HourInt, 
			dp.Length, 
			dp.Breadth, 
			dp.Draught, 
			dp.SOG, 
			dp.COG,
			dp.TripID
FROM kiel_import_complete AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID > 413683;

SET @LeftBorder = 14.60;
SET @RightBorder = 20.0;

SELECT 	dp.AgentLatitude,
			dp.AgentRemainingTravelTimeInMinutes,
			dp.AgentSOG,
			dp.AgentCOG,
			dp.Latitude,
			dp.Longitude,
			dp.WeekdayInt, 
			dp.WeekOfYearInt, 
			dp.HourInt, 
			dp.Length, 
			dp.Breadth, 
			dp.Draught, 
			dp.SOG, 
			dp.COG,
			dp.TripID
FROM kiel_import_complete AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID <= 413683;

SELECT 	dp.AgentLatitude,
			dp.AgentRemainingTravelTimeInMinutes,
			dp.AgentSOG,
			dp.AgentCOG,
			dp.Latitude,
			dp.Longitude,
			dp.WeekdayInt, 
			dp.WeekOfYearInt, 
			dp.HourInt, 
			dp.Length, 
			dp.Breadth, 
			dp.Draught, 
			dp.SOG, 
			dp.COG,
			dp.TripID
FROM kiel_import_complete AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID > 413683;