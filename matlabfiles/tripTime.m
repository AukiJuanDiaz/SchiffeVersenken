
function p = tripTime( duration )

histogram(duration,500);
p = prctile(duration, 95);

end