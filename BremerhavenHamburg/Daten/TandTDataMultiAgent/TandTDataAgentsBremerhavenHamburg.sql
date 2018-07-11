/*Extrahieren von T adn T je Agent, aufgeteilt nach Trips, 20% Test, 80% Training */

/* Hamburg-Bremerhaven:
Agent 1: Grenze 8.69 Percentile 50 (Bremerhaven bis Cuxhaven)
Agent 2: grenze 9.39 Percentile 75(Cuxhaven bis Drochtersen(Elbe))
Agent 3: Grenze 10 Percentile (Drochtersen(Elbe) bis Hamburg)
Ausgewählt nach Peaks, Percentiles und geographischen Sektoren */

SET @LeftBorder = 7.4;
SET @RightBorder = 8.69;

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
FROM data_prepared AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID <= 365242;

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
FROM data_prepared AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID > 365242;

SET @LeftBorder = 8.69;
SET @RightBorder = 9.39;

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
FROM data_prepared AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID <= 365242;

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
FROM data_prepared AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID > 365242;

SET @LeftBorder = 9.39;
SET @RightBorder = 10.1;

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
FROM data_prepared AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID <= 365242;

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
FROM data_prepared AS dp
WHERE Longitude >= @LeftBorder AND Longitude < @RightBorder AND TripID > 365242;