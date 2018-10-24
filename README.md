# ACDC Project
## Class description

### SendPost
Main class that helps the user create and send a post.

- `void main(String args)`

### Post
Contains the data of the post that will be sent.

*Getters*

- `String getTitle()`
- `String getDate()`
- `String getCategory()`
- `String getContent()`
- `String getAuthor()`

*Setters*

- `setTitle(String title)`
- `setDate(String date)`
- `setCategory(String category)`
- `setContent(String content)`
- `setAuthor(String auth)`

*Methods*

- `writeFile()` - generates a .markdown file containing the post
- `seeDemo()` - shows a preview of the post 
- `send()` - sends the post to the git repository

### CategoryManager
Manages the list of categories of posts. The list is stored in a text file.

- `static ArrayList<String> getCategories()`
- `static addCategory(String cat)`

### MarkdownGenerator
Generates Markdown text from data it receives.

- `static String toMarkdown(Post post)`