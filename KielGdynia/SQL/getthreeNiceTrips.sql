
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
			dp.TripID,
			dp.TimeDate
FROM kiel_import_complete AS dp
WHERE TripID IN (183783, 1992358, 393533)

