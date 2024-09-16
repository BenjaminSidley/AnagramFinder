
Program called MostAnagramsFinder that is as a command line java application that uses several data
structures and insertionSort, along with many helper methods to
find the word or words with the most anagrams in a given txt file
@author Benjamin Sidley, bms2227

reflection questions to answer:
How long does your program take to produce the answer when using the bst, rbt, and hash flags?
Copy the results into this readme file.
5 runs for bst - 1.601, 1.609, 1.554, 1.589, 1.569
avg for bst - 1.5844s

5 runs for rbt - 0.590, 0.593, 0.592, 0.575, 0.594
avg for rbt - 0.5888s

5 runs for hash - 0.334, 0.348, 0.329, 0.331, 0.330
avg for hash - .3344s

What data structure do you expect to have the best (fastest) performance?
    I expect the hash table to have the fastest performance as the average runtime
    for lookup and addition to the map is theta(1) which is much faster compared
    to the other structures. Given looking up and adding is a big part of the
    algorithm, i think hash tables would be the most optimal as the other structures
    runtimes for lookup and addition are around theta(log n) and O(n)
Which one do you expect to be the slowest?
    I think the binary search tree will have the slowest time. The time cost for the lookup
    and addition to the tree is much slower than hash tables and slower than a Red Black
    tree as the binary search tree is likely to be unbalanced, causing the lookup time and
    addition time costs to be more toward O(n) than O(log n), compared with the self-balancing
    tree of the Red Black Tree which will always have time costs of theta(log n) for
    lookup and addition. so overall, i think the binary search tree will be the slowest as it is slower
    than the red black tree and much slower than a hash tables theta(1) lookup and addition times

Do the results of timing your program’s execution match your expectations?
If so, briefly explain the correlation.
If not, what run times deviated and briefly explain why you think this is the case.
    It seems as though the results of my timing do match my expectations. the
    average time for hash was .3344 seconds, following the average time for red black tree being
    .5888 seconds, and lastly, the average time for binary search tree being 1.5844 seconds. Its clear to
    see the results match the explanations and expectations as the hash table was more than a full second
    faster than the BST on average and more than .2 seconds faster than the RBT on average. We can see BST didnt
    do so well as it was just around a second slower than the RBT and over a second slower than
    the hash table.
