# Mobile Phone website crawling and analysis - Java

## Project Overview
This project aims to analyze and compare mobile phone offerings from a selected website, providing users with the best options based on their specified criteria. The system involves web crawling, HTML parsing, search frequency analysis, inverted indexing, word completion, spell checking, and page ranking.

## Components
1. Web Crawler:

The web crawler is designed to navigate through a mobile seller website, specifically www.bestbuy.ca. It extracts data based on user-defined parameters: depth, URL, and regular expression. The crawling process ensures that the system stays within the main website. The output includes 1123 URLs after crawling.
WebCrawler.java: Implements the web crawling functionality.

2. HTML Parser:

The HTML parser takes the output from the web crawler and extracts webpage content using the JSOUP library. Each URL's content is stored in individual text files, following a naming convention (e.g., 0.txt, 1.txt).
HTMLParser.java: Utilizes the output of the web crawler to extract webpage content.

3. Search Frequency Analysis:

This component allows users to input keywords, and the system maintains a search history with word frequencies using a hash map.
Search.java: Manages the search frequency analysis and returns a hash map of search history.

4. Inverted Indexing:

Inverted indexing involves creating a hash table to store word frequencies for each text file. If a word appears for the first time, it is added to the hash table; otherwise, its frequency is incremented.
InvertedIndexing.java: Implements inverted indexing and frequency count.

5. Word Completion:

This feature utilizes the output of inverted indexing to create a Trie data structure. It suggests the alphabetically first available word in the Trie as a completion for user input.
WordCompletion.java: Provides word completion suggestions based on the Trie structure.

6. Spell Checking:

Spell checking involves creating an inverted hash index for all text files. The system suggests corrections by comparing user input with words in the inverted hash map, considering edit distances.
SpellChecking.java: Implements spell checking and suggests corrections.

7. Page Ranking:

Page ranking utilizes a maximum heap data structure to rank pages based on word frequencies. The system uses a priority queue to retrieve the top N pages.
PageRanking.java: Implements page ranking using a maximum heap.

## Project Workflow
![image](https://github.com/ValiantDmello/Mobile-Site-Analysis/assets/49265710/9e912457-619b-4b4e-a69b-d545baf6c93a)

## Getting Started
* Clone the repository.
* Set up the project in your preferred Java IDE (Eclipse recommended).
* Run the main program, specifying your search criteria.
