%h = histogram(TripDuration.duration,500);
%p = prctile(TripDuration.duration, 95);

matrix = zeros(3, 340057);

worldmap([53.0 54.5],[7.0 10.5])
geoshow('landareas.shp', 'FaceColor', [0.15 0.5 0.15]) 


load geoid
geoshow(50, 10, 'DisplayType', 'Point', 'Marker', '.', 'Color', 'red');





condensed_trip_id = unique(condensed.id);
b = length(condensed_trip_id);
color = ['red' 'blue' 'green' 'yellow']

for n = 1:4
    trip_id = condensed_trip_id(n)
    rows = condensed.id == trip_id
    geoshow(condensed.lat(rows), condensed.lon(rows), 'DisplayType', 'Line', 'Marker', '.', 'Color', color(n));
end

%{
%hist2 = histogram(p,500);
%}