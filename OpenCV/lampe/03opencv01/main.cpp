#include "opencv2/opencv.hpp"
using namespace cv;
int main(){
	// breite hoehe farbe
	Mat image(480, 640, CV_8UC3);
	// bild y x
	rectangle(image, Point(100,100), Point(200,200),
	// vektor der laenge 3 oder vier
	// bgr also hier blau und 3 ist die breite
	Scalar(255,0,0), 3);
	// name des fensters
	namedWindow( "opencv", 1 );
	// fenster anzeigen im opencv fesnter
	imshow( "opencv", image );
	waitKey(0);
	return 0;
}
