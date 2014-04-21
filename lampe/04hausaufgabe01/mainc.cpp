#include "opencv2/opencv.hpp"
#include <iostream>
using namespace cv;
int main(){
	Mat image(480, 640, CV_8UC3);
	Scalar color1 (0, 0, 0);
	Scalar color2 (255, 255, 255);
	rectangle(image, Point (0,0), Point(80,480), color2,CV_FILLED);
	rectangle(image, Point (81,0), Point(160,480), color1,CV_FILLED);
	rectangle(image, Point (160,0), Point(240,480), color2,CV_FILLED);
	rectangle(image, Point (241,0), Point(320,480), color1,CV_FILLED);
	rectangle(image, Point (321,0), Point(400,480), color2,CV_FILLED);
	rectangle(image, Point (401,0), Point(480,480), color1,CV_FILLED);
	rectangle(image, Point (481,0), Point(560,480), color2,CV_FILLED);
	rectangle(image, Point (561,0), Point(640,480), color1,CV_FILLED);
	imshow( "opencv", image );
	waitKey(0);
	return 0;
}