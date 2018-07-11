Select StartLatitude, StartLongitude, Count(Distinct TripID)
From data_import_complete_copy
Group By StartLatitude, StartLongitude;

Select EndLatitude, EndLongitude, Count(Distinct TripID)
From data_import_complete_copy
Group By EndLatitude, EndLongitude;
