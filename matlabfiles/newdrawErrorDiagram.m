function drawErrorDiagram(act, pred)

act = transpose(act);
pred = transpose(pred);
both = [act,pred];
both = sortrows(both,1,'descend');

both = condense(both,5);

actual = both(:,1);
predicted = both(:,2);


predicted = floor(predicted);
difference = predicted - actual;
relative = abs((difference ./ actual) * 100);

xlim([0,max(actual)]);

yyaxis left
set(gca, 'FontSize', 28);
plot(actual, difference,'LineWidth',2.0);
ylim([-250,250]);


refline(0,0);
ylabel('Absoluter Fehler in Minuten','FontSize',40,'FontWeight','bold')

yyaxis right
plot(actual, relative,'LineWidth',1.5,'LineStyle',':');
ylim([0,80]);
refline(0,0);
ylabel('Relativer Fehler in %','FontSize',40,'FontWeight','bold')
end