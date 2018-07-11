UPDATE data_prepared
SET RemainingTravelTimeInMinutes = HOUR(RemainingTravelTime) * 60 + MINUTE(RemainingTravelTime);
