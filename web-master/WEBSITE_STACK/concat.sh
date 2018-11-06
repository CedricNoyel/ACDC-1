cp CSS/* ./WEBSITE/.
cp PICTURES/FIXED/* ./WEBSITE/img/pictures/.
for f in PAGES/*.html ; do
  cat TEMPLATES/header.html $f TEMPLATES/footer.html > WEBSITE/$(basename "$f")
done
