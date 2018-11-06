# Stack website

## Root website is located at

WEBSITE_STACK/website/index.html

## Never do

- never delete the content of the directory `website` (contains css style, javascript files and images)

## How to update pages

- `make` will build the website (in directory `website`)

## How to modify pages, styles, and js

### Site content

1. To modify the content, do not modify the files in the `website` directory. Instead, modify the files in:
  - `template` to change something in the header, the footer, or the HTML headers of the pages
  - `pages` to modify the content of individual pages

2. Profile images used in the `members.html` page should be in a square format and located in `website/img/profiles`.

### CSS / Javascript

- The CSS and Javascript files are in the `website` directory.
- `publi.js` contains the code that makes the `papers.html` page work. The first line declares the main part of the API call URL (to which can be added constraints on the date of publications). The last function declared ($( document ).ready()) is automatically executed when the structure of the webpage has loaded. Among other things, it displays the list of buttons allowing to select the year for which one wants to see publications. When a new year comes, you may add a new button for the new year.

### How publishing your changes
The website is hosted at http://stack.gforge.inria.fr
To push your changes you have to synchronize the repo and the sftp server.
For this, please use `./publish.sh YOUR-GFORGE-LOGIN` script

Last but not the least, you also need to commit your changes in the gitlab of STACK where you currently are :

`git pull`

`git add yourchanges`

`git commit -m "your message"`

`git push`
