function drawErrorDiagram(actual, predicted)

predicted = floor(predicted);
difference = predicted - actual;
scatter(actual, difference);
end

