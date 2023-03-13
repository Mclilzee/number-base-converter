# Number Base Converter

A program to convert between all kind of base numbers such as : Hex, Octal, Binary, Decimal.
You will be able to choose the source, and destination of the number type. Uses `BigDecimal` to be able to go with as high number as you have memory available in your pc.
More will be shown in the examples bellow. The program supports from base 2 up to base 36

# Requirement
- Java version 17+ <a href="https://www.oracle.com/de/java/technologies/downloads/">Java download Link</a>

# Build - Run Project
- Clone repository and navigate into repo's directory
- Run project with `$ ./gradlew run`

# Controls and Input
Once program is started, you will be prompt by the terminal for input, basically follow the terminal instructions.
- Source base, is the base the number originally in, examples base 2 is Binary, base 16 is Hex etc...
- Target base, is the base you wish your number converted to, example base 10 is Dec etc...
- You will then be prompt to enter your number to be converted, it has to be in correct format in that base

# Examples

### Converting from Binary to Dec
```console
Enter two numbers in format: {source base} {target base} (To quit type /exit) $ 2 10
Enter number in base 2 to convert to base 10 (To go back type /back) $ 1101
Conversion result: 13

Enter number in base 2 to convert to base 10 (To go back type /back) $ 1111
Conversion result: 15

Enter number in base 2 to convert to base 10 (To go back type /back) $ /back
Enter two numbers in format: {source base} {target base} (To quit type /exit) 
```

### Converting From Dec to Hex
```console
Enter two numbers in format: {source base} {target base} (To quit type /exit) $ 10 16
Enter number in base 10 to convert to base 16 (To go back type /back) $ 15
Conversion result: f

Enter number in base 10 to convert to base 16 (To go back type /back) $ 66545216
Conversion result: 3f76640

Enter number in base 10 to convert to base 16 (To go back type /back) $ /back
Enter two numbers in format: {source base} {target base} (To quit type /exit) 
```

### Converting Hex to 36 base
```console
Enter two numbers in format: {source base} {target base} (To quit type /exit) $ 16 36
Enter number in base 16 to convert to base 36 (To go back type /back) $ ff
Conversion result: 73

Enter number in base 16 to convert to base 36 (To go back type /back) $ 102fff
Conversion result: mqkf

Enter number in base 16 to convert to base 36 (To go back type /back) $ 10mn
Wrong number provided for given source base!
Enter number in base 16 to convert to base 36 (To go back type /back) $ /back
Enter two numbers in format: {source base} {target base} (To quit type /exit) $ /exit
```