

UPDATE data_prepared
SET AgentRemainingTravelTimeInMinutes = HOUR(TIMEDIFF(AgentTime, TimeDate)) * 60 + MINUTE( TIMEDIFF(AgentTime, TimeDate))