
function plotMultipleTrips(trips, startIndex, endIndex)

ids = unique(trips(:,1));

hold on
for i = startIndex:endIndex
    plotTripWithID(ids(i), trips);
end

end