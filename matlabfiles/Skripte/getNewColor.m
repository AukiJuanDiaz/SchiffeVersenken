function c = getNewColor
persistent colors;

    if isempty(colors)
        %colors = [ 'Black'; 'Blue'; 'BlueViolet'; 'Brown'; 'Cyan'; 'DarkBlue'; 'DarkGreen';'DarkMagenta';'DarkSalmon';'DarkYellow';'Gray';'Green';'LightBlue';'LightSeaGreen';'Magenta';'Orange';'PaleYellow';'Purple';'Red';'SmahtGuide';'White';'Yellow' ];
        colors = [ 'y'; 'm'; 'c'; 'r'; 'g'; 'b'; 'w'; 'k' ];
    end
 
 c = colors(1);
 colors = colors(2:end);
 colors = cat(1, colors, [c]);
end