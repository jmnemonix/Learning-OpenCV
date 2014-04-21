avprg
=====

##OpenCV unter Ubuntu Installieren:
<pre><code>
$ sudo apt-add-repository ppa:alexei.colin/opencv

$ sudo apt-get update

$ sudo apt-get install opencv oder <code> sudo apt-get install libopencv-* </code>

$ sudo apt-get install libopencv-dev
</code></pre>
Der header fuer opencv liegt unter:
<pre> 
<code> /usr/include/opencv2 </code> 
</pre>
Ein mit g++ compilieren:
<pre>
<code> g++ -I/usr/include/opencv main.cpp -o main -lopencv_core -lopencv_imgproc -lopencv_highgui -lopencv_ml -lopencv_video -lopencv_features2d -lopencv_calib3d -lopencv_objdetect -lopencv_contrib -lopencv_legacy -lopencv_flann
</code>
</pre>
Hier wuerde auch -lopencv_core angeblich fuer unseren kurs reichen.

im ordner 03opencv01 ist auch ein Makefile das man einfach mit 'make' ausfueheren kann.
