
/* 
Wählt die richtige Länge aus
*/

/*
SELECT a.id, a.rev, a.contents
FROM YourTable a
INNER JOIN (
    SELECT id, MAX(rev) rev
    FROM YourTable
    GROUP BY id
) b ON a.id = b.id AND a.rev = b.rev
*/

SELECT a.TripID, a.Longitude, a.Latitude, a.SOG, COUNT(a.TripID)
FROM data_prepared AS a
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM data_prepared
		WHERE Longitude < 9.0
		GROUP BY TripID) AS b ON a.TripID = b.TripID AND a.Longitude = b.LongitudeMax
GROUP BY a.TripID;

SELECT dp.TripID, dp.Longitude, dp.Latitude
FROM data_prepared AS dp
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM data_prepared
		WHERE Longitude < 9.0
		GROUP BY TripID) AS b ON dp.TripID = b.TripID AND dp.Longitude = b.LongitudeMax
GROUP BY dp.TripID;



UPDATE data_prepared, (SELECT dp.TripID AS ID, dp.Longitude, dp.Latitude AS LatitudeBorder
FROM data_prepared AS dp
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM data_prepared
		WHERE Longitude < 9.0
		GROUP BY TripID) AS b ON dp.TripID = b.TripID AND dp.Longitude = b.LongitudeMax
GROUP BY dp.TripID) AS t
SET data_prepared.LatitudeBorder = t.LatitudeBorder
WHERE data_prepared.TripID = t.ID AND data_prepared.Longitude < 9.0;



/*
SELECT a.TripID, a.Longitude, a.Latitude, a.SOG
FROM data_prepared AS a
INNER JOIN (
		SELECT TripID, MAX(Longitude) AS LongitudeMax
		FROM data_prepared
		WHERE Longitude < 9.0
		GROUP BY TripID) AS b ON a.TripID = b.TripID AND a.Longitude = b.LongitudeMax
WHERE a.TripID = 1288888;
*/

/*
UPDATE mytable SET lastLogDate = t.maxDateForUser  
FROM  
(  
    SELECT userid, MAX(logDate) as maxDateForUser  
    FROM mytable  
    GROUP BY userId  
) t  
WHERE mytable.userid = t.userid
*/

