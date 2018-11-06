---
layout: post
title:  "Welcome to Jekyll!"
date:   2016-05-11 10:24:55 +0200
categories: jekyll update
---
This is a simple example of a post/news.
Posts/News should be written as a markdown format under `_posts` directory. 

To add new posts, simply add a file in the `_posts` directory that follows the convention `YYYY-MM-DD-name-of-post.ext` and includes the necessary front matter. Take a look at the source for this post to get an idea about how it works.

To see your changes, you need to rebuild the blog site.  
You can rebuild the site in many different ways, but the most common way is to use invoke the `jekyll build`.

Note that if you have some gem errors you can also run `bundle exec jekyll build` instead.

If you want to test it before on your machine, please run `jekyll serve`. 
This command will launches a web server and auto-regenerates your site when a file is updated.
You should be able to see the local website on http://localhost:4000. 

Please note that a specific category for open positions exists. Thus to create a new job please use the following yaml starting file :

`---
layout: post
title:  "New job"
date:   2018-04-27 10:00:00 +0200
categories: jobs
---`

the local page to check that your job offer appears in the job page is http://localhost:4000/blog/category/jobs.html

Jekyll also offers powerful support for code snippets:

{% highlight ruby %}
def print_hi(name)
  puts "Hi, #{name}"
end
print_hi('Tom')
#=> prints 'Hi, Tom' to STDOUT.
{% endhighlight %}

Check out the [Jekyll docs][jekyll-docs] for more info on how to get the most out of Jekyll. File all bugs/feature requests at [Jekyll’s GitHub repo][jekyll-gh]. If you have questions, you can ask them on [Jekyll Talk][jekyll-talk].

To publish your changes to the official website you should synchronize your directory with the `./publish.sh` command.

Last but not the least, you also need to commit your changes in the gitlab of STACK where you currently are :

`git pull`

`git add yourchanges`

`git commit -m "your message"`

`git push`

[jekyll-docs]: http://jekyllrb.com/docs/home
[jekyll-gh]:   https://github.com/jekyll/jekyll
[jekyll-talk]: https://talk.jekyllrb.com/
