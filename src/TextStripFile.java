/*
 * This program strips the input text file with the specified strings
 * and writes these strings to an output text file.
 *
 * @author Rhene Munez
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TextStripFile
{
    static File inputFileName = new File("foo\\inputFile.txt");
    static String outputFileName = "outputFile.txt";

    public static void main(String[] args)
    {
        createFile(outputFileName);
        readFile(inputFileName, outputFileName);
    }

    /**
     * Reads from a text file and parses each line of text
     * unit it strips out only the wanted strings.
     * @param inFile Input text file
     * @param outFile List of strings
     */
    public static void readFile(File inFile, String outFile)
    {
        try
        {
            File inputFile = new File(String.valueOf(inFile));
            FileWriter writeFile = new FileWriter(outFile);
            Scanner readFile = new Scanner(inputFile);

            while (readFile.hasNextLine())
            {
                String data = readFile.nextLine();

                DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("M/d/yyyy");

                try
                {
                    String [] date = data.split(" ", 2);
                    String dateInString = date[0];
                    String fromDate = "09/01/2015";
                    String toDate = "06/07/2021";

                    LocalDate todaysDate = LocalDate.parse(toDate, datePattern);
                    LocalDate dateToDate = LocalDate.parse(dateInString, datePattern);
                    LocalDate startDate = LocalDate.parse(fromDate, datePattern);

                    if (dateToDate.isAfter(startDate) && dateToDate.isBefore(todaysDate))
                    {
                        if ((date[1].contains("account_name")) && (date[1].contains("7509") || date[1].contains("9809")))
                        {
                            String str = date[1];

                            writeFile.write(str.substring(28, 41) + "\r");
                        }
                        else
                        {
                            System.out.println("No string to strip from text file.");
                        }
                    }

                    writeFile.flush();
                }
                catch (DateTimeParseException dateTimeParseException)
                {
                    System.out.println("Line does not start with a date.");
                }
            }

            writeFile.close();
            readFile.close();
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println("A file error has occurred.");
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException)
        {
            System.out.println("An I/O error occurred.");
            ioException.printStackTrace();
        }
    }

    /**
     * Creates a text file
     * @param outputFileName Text file
     */
    public static void createFile(String outputFileName)
    {
        try
        {
            File outputFile = new File(outputFileName);

            if (outputFile.createNewFile())
            {
                System.out.println("File created: " + outputFile.getName());
            }
            else
            {
                System.out.println("File already exists");
            }
        }
        catch (IOException ioException)
        {
            System.out.println("An error occurred.");
            ioException.printStackTrace();
        }
    }
}
