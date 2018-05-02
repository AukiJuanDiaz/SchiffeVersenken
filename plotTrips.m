%h = histogram(TripDuration.duration,500);
%p = prctile(TripDuration.duration, 95);

matrix = zeros(3, 340057);

worldmap('europe')
geoshow('landareas.shp', 'FaceColor', [0.15 0.5 0.15]) 
load geoid
geoshow(50, 10, 'DisplayType', 'Point', 'Marker', '.', 'Color', 'red');

c = length(condensed.lat);
geoshow(condensed.lat, condensed.lon, 'DisplayType', 'Point', 'Marker', '.', 'Color', 'red');
%{
%hist2 = histogram(p,500);
%}