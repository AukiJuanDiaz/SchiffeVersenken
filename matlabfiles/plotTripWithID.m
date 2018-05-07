
function plotTripWithID( id, valueMatrix)

s = valueMatrix( valueMatrix(:,1) == id, :);
plotTrip( s(:,2), s(:,3));

end