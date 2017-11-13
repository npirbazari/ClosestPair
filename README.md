# ClosestPair

### Running Time of the Algorithm

Brute force was used to tackle this problem therefore code runs at O(n2) time.

### Limitations

- Text files should contain 1 point per line.
-	Coordinate values should be separated by a tabulator character.
-	Coordinate values can be both integers and floating point numbers. For floating point values the decimal separator should be a period.

### Usage Instructions

To run the code from terminal 

1)	Set class path 

`export CLASSPATH= path to folders`

2)	Navigate to ClosestPoints folder
3)	Generate class files

`javac *.java`

4)	Run the code

`java ClosestPoints.Main`



After running the code, program shows the following menu

```
Welcome
1) Select input files.
2) Run file by path.
3) Generate new random test file.
4) Run test cases.
0) Exit.

```

User can enter 

1-	To select an input file from list

```
Please Select Input File:
1) sample_input_100_100.tsv
2) sample_input_10_100.tsv
3) sample_input_2_8.tsv
4) sample_input_4_4.tsv
5) test1.tsv
6) test2.tsv
7) test3.tsv
8) test4.tsv
0) Back 
```

Entering “0” goes back to previous menu and entering number on the left of the file name runs the program with the selected input file.

After selecting the input file program displays the following output which contains information about time taken for reading input file, time taken for finding closest points and result. 

```
Reading file took 1 milliseconds 

Result:
Point: lineNumber=40 ,with coordinates=[666271.5742697478, 527671.7910485603, 152967.0, 520179.98444839707, 670891.9331729594, 101834.18074930739, 800325.0, -292875.81550093705, -956374.0, 511727.61509977304]
Point: lineNumber=94 ,with coordinates=[969976.0, 293327.38285349007, 838906.508207679, 617179.0567598091, 491237.0, -236921.0, 416816.1974732606, -462136.77009611693, -782541.1487445169, 450974.14932926116]

Operation took 1 ms 
```

2-	To run program by manually entering the path to input file.

Please enter file path:
/Users/npirbazari/Desktop/test/InputFiles/sample_input_2_8.tsv

3-	To generate new random test file, program displays the following output when new test file is successfully created 

Successfully created test file.
Generated files will be stored in InputFiles folder under test folder that can be accessed by entering 1 in the menu.

4-	To run all of the previously generated test cases. Previously generated test files are:
a.	Empty file
b.	File with only 1 point
c.	File with 2 points
d.	File with points that don’t have same dimensions.

0-	To exit





 





