function drawErrorDiagram(actual, predicted)

predicted = floor(predicted);
difference = predicted - actual;
relative = (difference ./ actual) * 100;

yyaxis left
set(gca, 'FontSize', 28);
scatter(actual, difference);

refline(0,0);
ylabel('Absoluter Fehler','FontSize',40,'FontWeight','bold')

yyaxis right

scatter(actual, relative);
ylim([-70, 70]);
refline(0,0);
ylabel('Relativer Fehler','FontSize',40,'FontWeight','bold')
end