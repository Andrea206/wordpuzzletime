# Word Puzzle Time

#### Word Puzzle Time! [@wordpuzzletime](https://twitter.com/wordpuzzletime) is a Twitterbot that generates word puzzles like anagrams and cryptograms. 

* The puzzles are created using a custom Java class, and intput into a SQLite database using Java JDBC API. 
* On a timed interval the TweetMain class queries the puzzles stored in the database, then constructs and tweets a custom post that includes a dynamic PHP link. 
* After a puzzle is posted it is marked as such in the database and will not show up in the query list again.

