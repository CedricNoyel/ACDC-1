for f in RAW/* ; do
   convert $f  -geometry x450 FIXED/$(basename "$f")  
done
