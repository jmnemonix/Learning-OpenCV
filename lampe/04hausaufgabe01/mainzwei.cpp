#include "opencv2/opencv.hpp"
#include <iostream>
using namespace cv;
int main(){
	Mat image(480, 640, CV_8UC3);
	Scalar color2 (255, 255, 255);
	int x = 0;
	int y = 0;
	int breite = 100;
	int hoehe = 200;
	Point linksOben(y,x);
	Point rechtsUnten(hoehe,breite);
	Point centrum(hoehe/2,breite/2); 
	rectangle(image, centrum, rechtsUnten, color2,CV_FILLED);
	double diagonale = norm(rechtsUnten);
	std::cout << diagonale;
	imshow( "opencv", image );
	waitKey(0);
	return 0;
}