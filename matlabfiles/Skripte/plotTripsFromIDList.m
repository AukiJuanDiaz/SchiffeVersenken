function plotTripsFromIDList( ids, trips )

for i = 1:length(ids)
    plotTripWithID(ids(i), trips);
end

end