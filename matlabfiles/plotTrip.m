%h = histogram(TripDuration.duration,500);
%p = prctile(TripDuration.duration, 95);


function plotTrip( latVec, lonVec )

color =  getNewColor();
geoshow(latVec, lonVec, 'DisplayType', 'Point', 'Marker', '.', 'MarkerEdgeColor', color);

end
