# MerkOrExtraction

A package to extract semantic information from texts and create a semantic database from that information.

Preprocessing steps are included for Icelandic.

## Preprocessing

#### Prerequisites: 
1. an <i>IceNLP IceTagger</i> tagged text (or texts) (<http://sourceforge.net/projects/icenlp/>).
2. <i>Beygingarlýsing íslensks nútímamáls (BÍN)</i> in a PostgreSQL database (<http://bin.arnastofnun.is>).

#### Create a database table of wordforms with mapping between IceNLP format and BÍN format:

Run:

    java -jar MerkorExtraction.jar -bin_mapping -input <input_file_or_directory>


If -input is a directory, all files in that directory will be processed.
This program has the following output:

* wordforms\_nouns.sql
* wordforms\_l\_adjectives.sql
* wordforms\_s\_verbs.sql
* nonValidWords\_nouns.txt
* nonValidWords\_l\_adjectives.txt
* nonValidWords\_s\_verbs.txt

The .sql files contain sql-statements to insert into database. The statements have the following format:

   INSERT INTO wordforms\_nouns VALUES ('fólki', 'nheþ', 'ÞGFET')

where the first value is the <i>wordform</i>, the second value the <i>ice-nlp tag</i> and the third one the <i>bin-tag</i>.

During the process above, all so called non valid words tagged as nouns, adjectives or verbs are sorted out
and written to file 'nonValidWords\_wordclass.txt'.
Example:

    fl.
    iii.
    sl.
    ungrúísland.is
    floor
    ...

<i>Processing time on MacBook Pro for 53.7 MB input file: 6:14 minutes</i>.

Now perform steps 1 and 2 in the database construction, see <i>data/merkorDBconstruction.sql</i>.

Populate database from file:

Run:
    java -jar MerkorExtraction.jar -fill\_db -db\_name databasename -db\_conn databaseconnection -password yourpassword -input sql-file

You can also set the default db-connection in class is.merkor.util.database.DBConnection.java

<i>Processing time on MacBook Pro for 89 MB input file: 13:22 minutes</i>.
<i>Processing time on MacBook Pro - real data: nouns (4.67GB) - 13h10m; adj (1.22GB) - 2h51m; verbs (3.15GB) - 8h36m</i>

Improvement suggestion: use Postgresql 'COPY' instead of 'INSERT INTO' (see postgresql doc), much faster.

#### Lemmatizing

The MerkorLemmatizer is a standalone application, but also accessible from within MerkOrExtraction as BINLemmatizer.

TODO: define command line parameters for lemmatizer in MerkOrExtraction.

## Pattern Extraction

If you already have patterns for relation extraction, skip this step!

#### Define the rules for the extraction of patterns:

One can define various rules for the initial extraction of patterns, based on what kind of relations are to be extracted later.
In the following the rules for the extraction of noun phrases and prepositional phrases are described. The aim is to 
extract relations between nouns, using enumeration of nouns, genetive constructions, and prepositions, along with relations between
adjectives and adjectives and nouns.
Patterns containing verbs are excluded as a method of narrowing down the problem (such patterns would indeed most likely be very useful).

The extraction in MerkOrExtraction is based on the IceNLP tagset. If you want to use MerkOr to extract other kinds of patterns in other format,
please have a look at the classes is.merkor.patternextraction.PatternExtraction.java and is.merkor.patternextraction.PatternInfo.java.
Unfortunately many rules are hard coded, so you might even be better off writing your own pattern extraction class if many changes are needed.

Rules:

    Allowed phrases in a pattern:
        Noun phrase ([NP), a prepositional phrase ([PP), or an adjective phrase ([AP).
        Conjunctions are allowed, but a pattern may not end with a conjunction phrase ([CC).

    A pattern may not end with a NP or a PP that does not contain a noun.
    A pattern has to contain at least two nouns, or two adjectives, or one noun and one adjective.

For each extracted pattern an abstract version and the realisation is returned.

Example:  
    Input:  ...  
            [SCP að c SCP]  
            [NP markmiðið nheng NP]  
            [PP með aþ [NP komu nveþ sinni feveþ NP] PP]  
            [AdvP hingað aa AdvP]  
            ...  

    Output: [NP nxeng ] [PP með  aþ [NP nxeþ  fexeþ ]] =>   
    [NP markmiðið nheng NP] [PP með aþ [NP komu nveþ sinni feveþ NP] PP]

The 'x' in the pattern stands for the gender tag which has been neutralized.

##### To run the MerkOr pattern extraction as is:

    java -jar MerkorExtraction.jar -extract_patterns -input <inputfile_or_dir> -output <output.csv>

#### Patterns to database

The patterns should be written to a database of the form:

    Table pattern:  
    id (pk) - pattern - nr_of_occurrences - relation  
    Table pattern_realisation:  
    id (pk) - pattern_id (fk) - text  

In the present work there is only one db-table, storing all pattern realisation as an array of texts with the respective pattern.
This has to be re-organized, as well as the classes (in pckg is.merkor.patternextraction.patterns_to_db) that write the patterns into the db have to be completely rewritten.

#### Choose promising patterns (manual work!)

Having all patterns with their realisations in a database, the 'PatternVerificationTool' can be used to quickly scanning over the patterns with their realisations to asset if they are likely to represent a relation. To have a look at this run is.merkor.patternextraction.VerificationFrameTabbed.java.

After this work one can extract all classified patterns for further processing. For MerkOr all patterns occurring at least 10 times in the corpus (about 5300 patterns) were manually scanned, resulting in about 2300 potentially useful patterns.

#### Merging patterns - edit distance

All patterns classified as indicating some relation are merged using the Levenshtein edit distance measure.
Example:

    Original patterns:
    [PP í  aþ [NP nxeþ ][NP nxee-ö ]]
    [PP Í  aþ [NP nxeþ ][NP nxeeg ]]
    Normalized patterns:
    [pp í aþ [np nþ][np ne-s]]
    [pp í aþ [np nþ][np neg]] 
    Merged pattern:
    [pp í  aþ [np nþ ][np ne-s|neg ]]

If you have your patterns in a database, set the name of your database in is.merkor.patternextraction.PatternMerger#getPatternsWithRelation()  

Then run:

    java -jar MerkorExtraction.jar -merge_patterns -output <output_file>  
    -relation <relation_of_patterns_to_merge> -password <your_db_passwd>

Running this should show something like (results are written to output_file):

    connected to database: patterns
    --- merged 388 patterns for relation 'genitive'
    --- nr. of merged patterns: 114

This significantly reduces the number of patterns to handle in the relation extraction - additionally further manual mergin using more
complicated regular expressions was performed for MerkOr.  
NOTE: It is though also possible to leave this step out completely, that way more exact data about reliability of single patterns can be collected later on.

## Relation Extraction

Package <code>is.merkor.relationextraction</code>

### UIMA

For the relation extraction a UIMA pipeline was implemented. See [Apache UIMA](http://uima.apache.org/). 
The UIMA pipeline uses external <i>descriptors</i> that describe the annotation task for a given analysis process, and <i>resources</i> like for example
the patterns generated in the last step. In the following the UIMA pipeline for relation extraction is described.

#### Annotating words and part-of-speech tags

UIMA is a framework that allows developers to define annotation tasks and provides for example a mechanism that defines start and end positions of the annotations in the text.  
The first task is to annotate all valid word and pos-tags.
Example:

    ....  
    [VP Eiga sfg3fn VP]  
    [NP rúm nhfn 50% tp NP]  
    [PP í aþ [NP Keflavíkurverktökumundirfyrirsögn nkeþ-m NP] PP]  
    ...  

In this text there are three Word objects (begin and end positions refer to a larger text):

    Word ("Eiga"), begin = 227, end = 231, word_string = Eiga  
    Word ("rúm"), begin = 247, end = 250, word_string = rúm  
    Word ("í"), begin = 271, end = 272, word_string = í   

The other two potential word objects, '50%' and 'Keflavíkurverktökumundirfyrirsögn', are not valid lexical items and hence are ignored.
All POS-tags are analysed:

    POS ("sfg3fn"), begin = 232, end = 238, word_class = verb, casus = "", TreeTaggerTag = VV  
    POS ("nhfn"), begin = 251, end = 255, word_class = noun, casus = nominative, TreeTaggerTag = NNS  
    POS ("tp"), begin = 260, end = 262, word_class = number, casus = "", TreeTaggerTag = CD  
    POS ("aþ"), begin = 273, end = 275, word_class = adverb, casus = "", TreeTaggerTag = IN  
    POS ("nkeþ-m"), begin = 314, end = 320, word_class = noun_proper, casus = dative, TreeTaggerTag = NP

Note that a mapping to a common international tagset is included, here marked as 'TreeTaggerTag', referring to [TreeTagger](http://www.ims.uni-stuttgart.de/projekte/corplex/TreeTagger/).

Every valid Word-POS pair is finally combined to a PairWordPOS object (in these examples no valid lemma was found for the words):

    PairWordPOS ("Eiga sfg3fn"), begin = 227, end = 238, pos = POS ("sfg3fn"), word = Word ("Eiga"), lemma = null  
    PairWordPOS ("rúm nhfn"), begin = 247, end = 255, pos = POS ("nhfn"), word = Word ("rúm"), lemma = null    
    PairWordPOS ("í aþ"), begin = 271, end = 275, pos = POS ("aþ"), word = Word ("í"), lemma = null

  
The descriptor for this annotation task is <code>PairInOneDetector.xml</code> and necessary resource files are <code>nonWordRegEx.txt</code> and <code>posTagMapping.txt</code>.  To be able to view the annotations in the <i>UIMA Annotation Viewer</i> (found as a .launch file in UIMA run-configuration), <code>PairInOneDetectorStyleMap.xml</code> descriptor is also necessary. This annotation can of course be run on its own, but for MerkOr its main purpose is to prepare for relation extraction.  

#### Annotating semantic relations

The descriptor for the relation extraction is <code>RelationDetector.xml</code> and <code>RelationAnnotator.xml</code>, and the resource file containing the regular expressions for relation extraction is <code>ruleMapping.txt</code>. The files needed for the annotation of words and part-of-speech are also needed here, but the annotation process is included in the pipeline so the relation extraction is run directly on IceNLP parsed text.  
An example for annotated relations:  

    ...  
    [SCP að c SCP]  
    [NP breytingar nvfn NP]  
    [PP á aþ [NP eignarhaldi nheþ NP] PP]  
    [VP verði svg3en VP]  
    ...

The relation found in this text:

    SemRelation("[NP breytingar nvfn NP] [PP á aþ [NP eignarhaldi nheþ NP] PP]")  
    begin = 4923, end = 4984, relation = breytingar%áDat%eignarhaldi, word1 = breyting, word2 = eignarhald

Since the relation annotator has access to the annotations of the word-pos annotator, it is possible to detect lemmata for the relations.

#### Running annotators

At the moment it is not possible to run the annotators from the command line (coming up!), they have to be run from <code>is.merkor.cli.MainAnnotators</code>.
Running from Eclipse: set arguments in Run As -> Run Configurations ... as <code>descriptors/RelationDetector.xml directory-of-the-files-to-analyze</code>.  
In the command line interface there will also be a flag option for the output: the output for the UIMA Annotation Viewer (or some other .xmi consuming program) is very large and can be commented out for large input. At the moment the call to the write-output method has to be commented out in <code>is.merkor.relationextraction.MerkorEngine#processBuffer</code>, comment out the line <code>writeAnnotationsForFile()</code>.  
The results needed for further processing of MerkOr are written to a folder <code>relationDetectorResults</code>, each relation to its own file, e.g. <code>coordNouns.csv</code>. Note that the writing mode is appending - each run of the annotator appends to the already created relation files. An option to choose 'append' or 'overwrite' will be included in the command line interface when ready.

The format of the result files, example from coordNouns.csv:

    ýsa			steinbítur	[NPs [NP ýsu nveþ NP] , , [NP ufsa nkeþ NP] [CP og c CP] [NP steinbít nkeþ NP] NPs]  
	steinbítur	ufsi	[NPs [NP ýsu nveþ NP] , , [NP ufsa nkeþ NP] [CP og c CP] [NP steinbít nkeþ NP] NPs]  
	ýsa			ufsi	[NPs [NP ýsu nveþ NP] , , [NP ufsa nkeþ NP] [CP og c CP] [NP steinbít nkeþ NP] NPs]  

Relations are always binary, so patterns containing relations between more than two words are splitted to the necessary number of binary relations. Each line contains the first word of the relation, the second word, and the realisation of the pattern it was extracted from, all tab-separated.  


## Storing Relations in a database

All extracted relations are stored in a database.

    
  
 
