/*
Test Set extrahieren.
20% der Trips
*/

SELECT 	dp.RemainingTravelTimeInMinutes,
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
WHERE TripID <= 365242;

/*
Training Set extrahieren.
80% der Trips
*/

SELECT 	dp.RemainingTravelTimeInMinutes,
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
WHERE TripID > 365242;
