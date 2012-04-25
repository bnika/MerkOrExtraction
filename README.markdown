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

One can define various rules for the initial extraction of patterns, based on what kind of relations are to be extracted.
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

    Output: [NP nxeng ] [PP með  aþ [NP nxeþ  fexeþ ]] => [NP markmiðið nheng NP] [PP með aþ [NP komu nveþ sinni feveþ NP] PP]

The 'x' in the pattern stands for the gender tag which has been neutralized.
 