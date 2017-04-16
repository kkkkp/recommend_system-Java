CIT594 HW5-MS3

Team
		Xiaozhou Pu
		Han Zhu


Files

1. Submitted file: zhuhan.zip

2. 16 files in zhuhan.zip:
	Algorithm.java
	Algorithm2A.java
	Algorithm2B.java
	AlgorithmKNN.java
	BookFileReader.java
	DataCenter.java
	FileReader.java
	FileReaderFactory.java
	Item.java
	ItemContainer.java
	Main.java
	NewMovieFileReader.java
	OldMovieFileReader.java
	User.java
	UserContainer.java
	README.txt


Design changes

1. We add a file reader factory and corresponding interface for Part1 to make it more flexible to process data in different format
   - This is a relatively easy change because we only need to update DataCenter class which is the only class depends on FileReader
   - We should consider make file reading process more general in MS2

2. We change the names of Movie and MovieContainer class to Item and ItemContainer to make it more readable
   - This is a relatively easy change because we only need to refactor(rename) the file
   - All other components of the program will work as usual once all class name get updated

3. We update id in Item class from int data type to string data type as in some files item id contains letter
   - This is a relatively hard change because we need to update a lot of data type in other class which depend on Item(id)

4. We create Algorithm2A class and Algorithm2B class for Par2 A and Part2 B, we also add experiment method in main for Part2 C
   - This is a relatively easy change because we only need to update code where new algo instance is created to finish the work


Compile

1. Put all *.java into one working directory and compile. Import or create input files into the same directory.
   Then run Main.

2. By default the program will ask user to input the filename. Then the program will output all required answers.


P.S.

1.	Resize program heap size to 4096M
