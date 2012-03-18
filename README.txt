 * Responsibility: find the max top-to-bottom total in the triangle data structure (a 2D array
 * of Node's).
 * Implements a dynamic programming approach. For each Node visited, the top to bottom total of
 * each of its parents (previous line, indexes col-1, col, col+1) is retrieved and the max
 * found. Then its maxTopToBotTotal is simply maxTotalParents + val. 
 * This means that previous "route" or "path" calculations down the triangle are not 
 * repeated.  
