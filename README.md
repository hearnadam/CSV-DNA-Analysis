# CSV-DNA-Analysis: CSV-DNA-Analysis: A faster & easier method to analyze genomic data.

## Note: This project is not complete & should not be used without significant modification.

**This project assumes the DNA data follows 23andme data format (As of December 2020).**

 An example of proper command-line usage follows:
 ```
 java DNAtool tab_seperated_data.txt DNAFile1.txt DNAFile2.txt
 ```

 The major limitation with this tool is the inability to analyze multiple SNPs at once.
 
 My solution to this problem would be to switch up the code quite a bit...
 Instead of parsing the CSV(TSV) file, inputting into a hashmap, then running through the keys of the hashmap, my future tool would digest a line of data at a time. The tool could recognize the length of the line based on seperating by tabs.

A single genome analyzed would have a length of 3 (2n + 1) - RSID, genotype, data

A multi genome anlysis would have a length of 2n + 1. RSID, genotype, RSID, genotype...data
