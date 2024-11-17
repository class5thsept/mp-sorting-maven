# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Leonardo Alves Nunes
* Samuel A. Rebelsky (starter code)

Acknowledgements

* Instructions (Samuel Rebelsky):
* Readings on sorting: https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/mps/mp08.html
- https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/readings/sorting.html
- https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/readings/mergesort.html
- https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/readings/quicksort.html
* How to use random: https://docs.oracle.com/javase/7/docs/api/java/util/Random.html

This code may be found at <https://github.com/class5thsept/mp-sorting-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
---------------------------------------
I implemented a version of merge sort that changes to selection sort once the length is small (<10)
The reasoning behind it is that it would use less space than merge sort, which is its downside
Although I didn't know how to track how much memory each algorithm used, I tested merge sort vs my sort
My sort was not significantly faster than merge sort, but was not worse then O(n^2).

Notes on using Copilot (or other AI)
------------------------------------
I used ChatGPT to get ideas for a faster algorithm. He provided me an idea for using a
Hybrid algorithm, which I chose Merge Sort + Selection Sort



