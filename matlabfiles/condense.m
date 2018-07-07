function condensedmatrix = condense(matrix,interval)

size = ceil(max(matrix(:,1))/interval);
lowerbound = 0;
upperbound = interval - 1;

condensedmatrix = zeros(size + 1,2);

condensedmatrix(1,1) = matrix(length(matrix),1);
condensedmatrix(1,2) = matrix(length(matrix),2);


for n = 2 : size +1
       
    condensedmatrix(n,1) = (lowerbound+upperbound)/2;
    
    condIdx = matrix(:,1) >= lowerbound & matrix(:,1) < upperbound;
    subMatrix = matrix(condIdx, :);
    
    
    condensedmatrix(n,2) = mean(subMatrix(:,2));
    
    lowerbound = lowerbound + interval;
    upperbound = upperbound + interval;
end

end