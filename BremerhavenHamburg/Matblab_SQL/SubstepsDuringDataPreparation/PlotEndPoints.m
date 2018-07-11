
ax = worldmap([53.0 54.5],[7.0 10.5])

land = shaperead('landareas', 'UseGeoCoords', true);
geoshow(ax, land, 'FaceColor', [0.15 0.5 0.15]);

geoshow(EndPortDistribution.EndLatitude, EndPortDistribution.EndLongitude, 'DisplayType', 'Point', 'Color', 'red', 'Marker', 'x');