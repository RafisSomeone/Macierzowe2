N = 6
x = rand(N,N+1);
x

#x = [999, 998, 1997; 1000, 999, 1999]

function matrix = perform_down_rows_subtraction(matrix, current_row)
  for j = current_row+1:rows(matrix)
    matrix(j, :) -= matrix(current_row, :) * ( matrix(j, current_row) / matrix(current_row, current_row) );
  endfor
end

#task 1
function result = gauss_without_ones(x)
  result(:, :) = x(:, :);
  for i = 1:rows(result)
    result = perform_down_rows_subtraction(result, i);
  endfor
end

#task 2
function result = gauss_with_ones(x)
  result(:, :) = x(:, :);
  for i = 1:rows(result)
     result(i, :) /= result(i, i);
     for j = i+1:rows(result)
       result(j, :) -= result(i, :) * result(j, i);
     endfor
  endfor
end

function matrix = perform_pivoting(matrix, i)
  if (i < rows(matrix))#if it is last row, the pivot should not be performed 
    down_rows_to_consider = matrix(i:rows(matrix), :);
    [_, max_abs_row_indeces] = max(abs(down_rows_to_consider));
    row_to_swap = max_abs_row_indeces(i) + i - 1;
    matrix([i row_to_swap], :) = matrix([row_to_swap i], :);
  endif
end

#task 3
function result = gauss_pivoting(x)
  result(:, :) = x(:, :);
  for i = 1:rows(result)
    result = perform_pivoting(result, i);
    result = perform_down_rows_subtraction(result, i);
  endfor
end

#task 4
function [L, U] = LU_factorization(x)
  L = eye(rows(x));
  U(:,:) = x(:,:);
  for i = 1:rows(U)
    for j = i+1:rows(U)
      L(j, i) = U(j, i) / U(i, i);
      U(j, :) -= U(i, :) * ( U(j, i) / U(i, i) );
    endfor
  endfor
end  


#Sprawdzenie poprawnosci. Liczone jest rozwiazanie ukladu rownan dla macierzy 
#wejsciowej, a nastepnie dla macierzy otrzymanych w wyniku kolejnych eliminacji
#Gaussa. Sam fakt tego, ze rozwiazania ukladu rownan za pomoca wbudowanej 
#funkcji sa takie same, oznacza, ze wlasnosc macierzy wejsciowej nie zostala
#zaburzona i macierz zostala dobrze przeksztalcona do macierzy o okreslonej wlasnosci
x1 = gauss_without_ones(x)
x2 = gauss_with_ones(x)
x3 = gauss_pivoting(x)
[l, u] = LU_factorization(x);
l * u;

A = x(:, 1:columns(x)-1);
b = x(:, columns(x));
[solv_correct, R] = linsolve(A, b);
solv_correct

[solv_1, R] = linsolve(x1(:, 1:columns(x1)-1), x1(:, columns(x1)));
solv_1

[solv_2, R] = linsolve(x2(:, 1:columns(x2)-1), x2(:, columns(x2)));
solv_2

[solv_3, R] = linsolve(x3(:, 1:columns(x3)-1), x3(:, columns(x3)));
solv_3

[solv_4, R] = linsolve(u(:, 1:columns(u)-1), u(:, columns(u)));
solv_4

